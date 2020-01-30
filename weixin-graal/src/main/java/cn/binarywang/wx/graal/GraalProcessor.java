package cn.binarywang.wx.graal;

import lombok.Data;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

// 目前仅仅处理@Data，且必须在lombok自己的processor之前执行，千万注意！！！！！
@SupportedAnnotationTypes("lombok.Data")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class GraalProcessor extends AbstractProcessor {

  private static final String REFLECTION_CONFIG_JSON = "reflection-config.json";
  private static final String NATIVE_IMAGE_PROPERTIES = "native-image.properties";

  private SortedSet<String> classSet = new TreeSet<>();
  private String shortestPackageName = null;

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    for (TypeElement annotatedClass : ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(Data.class))) {

      registerClass(annotatedClass.getQualifiedName().toString());
      handleSuperClass(annotatedClass);
    }

    //只有最后一轮才可以写文件，否则文件会被重复打开，报错！
    if (!roundEnv.processingOver()) return false;

    // 如果没有文件要写，跳过
    if (classSet.isEmpty()) return false;

    writeFiles();

    //必须返回false，以便让lombok能继续处理。
    return false;
  }

  /**
   * 设置当前最短的package名称
   *
   * @param packageName 包名
   */
  private void setShortestPackageName(String packageName) {
    if (shortestPackageName == null) {
      shortestPackageName = packageName;
    } else if (packageName.length() < shortestPackageName.length()) {
      shortestPackageName = packageName;
    }
  }

  /**
   * 更加完整的类名来获取package名称
   *
   * @param fullClassName 完整的类名
   * @return package name
   */
  private String getPackageName(String fullClassName) {
    int last = fullClassName.lastIndexOf('.');
    if (last == -1) return fullClassName;
    return fullClassName.substring(0, last);
  }

  /**
   * 保存文件
   * META-INF/native-image/.../reflection-config.json
   * META-INF/native-image/.../native-image.properties
   */
  private void writeFiles() {
    String basePackage = shortestPackageName;

    String module;
    if (basePackage.contains(".")) {
      final int i = basePackage.lastIndexOf('.');
      module = basePackage.substring(i + 1);
      basePackage = basePackage.substring(0, i);
    } else {
      module = basePackage;
    }

    String path = "META-INF/native-image/" + basePackage + "/" + module + "/";
    String reflectFile = path + REFLECTION_CONFIG_JSON;
    String propsFile = path + NATIVE_IMAGE_PROPERTIES;
    try {
      FileObject fileObject = processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", propsFile);
      Writer writer = fileObject.openWriter();
      writer.append("Args = -H:ReflectionConfigurationResources=${.}/" + REFLECTION_CONFIG_JSON);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      FileObject fileObject = processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", reflectFile);
      Writer writer = fileObject.openWriter();
      writer.write("[\n");
      boolean first = true;
      for (String name : classSet) {
        if (first) {
          first = false;
        } else {
          writer.write(",");
        }
        writer.write(assetGraalJsonElement(name));
        writer.append('\n');
      }
      writer.write("]");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private String assetGraalJsonElement(String className) {
    return "{\n" +
      "  \"name\" : \"" + className + "\",\n" +
      "  \"allDeclaredFields\":true,\n" +
      "  \"allDeclaredMethods\":true,\n" +
      "  \"allDeclaredConstructors\":true,\n" +
      "  \"allPublicMethods\" : true\n" +
      "}";
  }

  /**
   * 登记一个class
   *
   * @param className 完整的类名
   */
  private void registerClass(String className) {
    classSet.add(className);
    setShortestPackageName(getPackageName(className));
  }

  /**
   * 获取一个类型的所有的父类，并登记
   *
   * @param typeElement 类型元素
   */
  private void handleSuperClass(TypeElement typeElement) {
    TypeMirror superclass = typeElement.getSuperclass();
    if (superclass.getKind() == TypeKind.DECLARED) {
      TypeElement s = (TypeElement) ((DeclaredType) superclass).asElement();
      String sName = s.toString();
      // ignore java.**/javax.**
      if (sName.startsWith("java.") || sName.startsWith("javax.")) return;
      registerClass(sName);
      handleSuperClass(s);
    }
  }

}
