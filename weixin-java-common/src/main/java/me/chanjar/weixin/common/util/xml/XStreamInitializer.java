package me.chanjar.weixin.common.util.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.*;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.WildcardTypePermission;

import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * The type X stream initializer.
 *
 * @author Daniel Qian
 */
public class XStreamInitializer {

  private static final Map<String, XStream> X_STREAM_MAP = new ConcurrentHashMap<>();

  private static final XppDriver XPP_DRIVER = new XppDriver() {
    @Override
    public HierarchicalStreamWriter createWriter(Writer out) {
      return new PrettyPrintWriter(out, getNameCoder()) {
        private static final String PREFIX_CDATA = "<![CDATA[";
        private static final String SUFFIX_CDATA = "]]>";
        private static final String PREFIX_MEDIA_ID = "<MediaId>";
        private static final String SUFFIX_MEDIA_ID = "</MediaId>";
        private static final String PREFIX_REPLACE_NAME = "<ReplaceName>";
        private static final String SUFFIX_REPLACE_NAME = "</ReplaceName>";

        @Override
        protected void writeText(QuickWriter writer, String text) {
          if (text.startsWith(PREFIX_CDATA) && text.endsWith(SUFFIX_CDATA)) {
            writer.write(text);
          } else if (text.startsWith(PREFIX_MEDIA_ID) && text.endsWith(SUFFIX_MEDIA_ID)) {
            writer.write(text);
          } else if (text.startsWith(PREFIX_REPLACE_NAME) && text.endsWith(SUFFIX_REPLACE_NAME)) {
            writer.write(text);
          } else {
            super.writeText(writer, text);
          }

        }

        @Override
        public String encodeNode(String name) {
          //防止将_转换成__
          return name;
        }
      };
    }
  };


  public static XStream getInstance(Class<?> clz) {
    return getInstance(clz, null);
  }

  public static XStream getInstance(Class<?> clz, Consumer<XStream> consumer) {
    return X_STREAM_MAP.computeIfAbsent(clz.getName(), key -> {
      XStream xStream = getInstance(consumer);
      Set<Class<?>> classSet = new HashSet<>();
      getRelatedClasses(clz, classSet);
      for (Class<?> aClass : classSet) {
        xStream.processAnnotations(aClass);
      }
      return xStream;
    });
  }

  private static void getRelatedClasses(Class<?> clz, Set<Class<?>> classSet) {
    classSet.add(clz);
    for (Field declaredField : clz.getDeclaredFields()) {
      try {
        if (declaredField.getGenericType() instanceof ParameterizedType) {
          Type[] types = ((ParameterizedType) declaredField.getGenericType()).getActualTypeArguments();
          for (Type type : types) {
            String fieldGenericTypeClassName = type.getTypeName();
            if (!fieldGenericTypeClassName.startsWith("java.lang")) {
              Class<?> fieldGenericTypeClass = Class.forName(type.getTypeName());
              if (!fieldGenericTypeClass.isPrimitive()) {
                getRelatedClasses(fieldGenericTypeClass, classSet);
              }
            }
          }
        } else {
          String fieldGenericTypeClassName = declaredField.getGenericType().getTypeName();
          if (!fieldGenericTypeClassName.startsWith("java.lang")) {
            Class<?> fieldGenericTypeClass = Class.forName(declaredField.getGenericType().getTypeName());
            if (!fieldGenericTypeClass.isPrimitive()) {
              getRelatedClasses(fieldGenericTypeClass, classSet);
            }
          }
        }

        String fieldGenericTypeClassName = declaredField.getGenericType().getTypeName();
        if (!fieldGenericTypeClassName.startsWith("java.lang")) {
          Class<?> fieldGenericTypeClass = Class.forName(declaredField.getGenericType().getTypeName());
          if (!fieldGenericTypeClass.isPrimitive()) {
            getRelatedClasses(fieldGenericTypeClass, classSet);
          }
        }
      } catch (ClassNotFoundException ignore) {

      }
    }
    Class<?> tmp = clz;
    while (tmp.getSuperclass() != null && !tmp.getSuperclass().equals(Object.class)) {
      if (!tmp.getSuperclass().isPrimitive()) {
        classSet.add(tmp.getSuperclass());
      }
      tmp = tmp.getSuperclass();
    }
  }


  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static XStream getInstance(Consumer<XStream> consumer) {
    XStream xstream = new XStream(new PureJavaReflectionProvider(), XPP_DRIVER) {
      // only register the converters we need; other converters generate a private access warning in the console on Java9+...
      @Override
      protected void setupConverters() {
        registerConverter(new NullConverter(), PRIORITY_VERY_HIGH);
        registerConverter(new IntConverter(), PRIORITY_NORMAL);
        registerConverter(new FloatConverter(), PRIORITY_NORMAL);
        registerConverter(new DoubleConverter(), PRIORITY_NORMAL);
        registerConverter(new LongConverter(), PRIORITY_NORMAL);
        registerConverter(new ShortConverter(), PRIORITY_NORMAL);
        registerConverter(new BooleanConverter(), PRIORITY_NORMAL);
        registerConverter(new ByteConverter(), PRIORITY_NORMAL);
        registerConverter(new XStreamCDataConverter(), 1);
        registerConverter(new StringConverter(), PRIORITY_NORMAL);
        registerConverter(new DateConverter(), PRIORITY_NORMAL);
        registerConverter(new CollectionConverter(getMapper()), PRIORITY_NORMAL);
        registerConverter(new ReflectionConverter(getMapper(), getReflectionProvider()), PRIORITY_VERY_LOW);
      }
    };
    xstream.ignoreUnknownElements();
    xstream.setMode(XStream.NO_REFERENCES);
    XStream.setupDefaultSecurity(xstream);
    xstream.autodetectAnnotations(true);

    // setup proper security by limiting which classes can be loaded by XStream
    xstream.addPermission(NoTypePermission.NONE);
    xstream.addPermission(new WildcardTypePermission(new String[]{
      "me.chanjar.weixin.**", "cn.binarywang.wx.**", "com.github.binarywang.**"
    }));
    xstream.setClassLoader(Thread.currentThread().getContextClassLoader());
    if (consumer != null) {
      consumer.accept(xstream);
    }
    return xstream;
  }
}
