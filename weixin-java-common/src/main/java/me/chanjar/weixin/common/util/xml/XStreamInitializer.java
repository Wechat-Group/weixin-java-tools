package me.chanjar.weixin.common.util.xml;

import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

public class XStreamInitializer {

  public static XStream getInstance() {
    XStream xstream = new XStream(new PureJavaReflectionProvider(), new XppDriver() {

      @Override
      public HierarchicalStreamWriter createWriter(Writer out) {
        return new PrettyPrintWriter(out, getNameCoder()) {
          protected String PREFIX_CDATA = "<![CDATA[";
          protected String SUFFIX_CDATA = "]]>";
          protected String PREFIX_MEDIA_ID = "<MediaId>";
          protected String SUFFIX_MEDIA_ID = "</MediaId>";

          @Override
          protected void writeText(QuickWriter writer, String text) {
            if (text.startsWith(this.PREFIX_CDATA) && text.endsWith(this.SUFFIX_CDATA)) {
              writer.write(text);
            } else if (text.startsWith(this.PREFIX_MEDIA_ID) && text.endsWith(this.SUFFIX_MEDIA_ID)) {
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
    });

    xstream.ignoreUnknownElements();
    xstream.setMode(XStream.NO_REFERENCES);
    xstream.addPermission(NullPermission.NULL);
    xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
    xstream.setClassLoader(Thread.currentThread().getContextClassLoader());
    return xstream;
  }

}
