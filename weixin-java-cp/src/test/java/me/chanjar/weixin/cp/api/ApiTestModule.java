package me.chanjar.weixin.cp.api;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.WxCpInMemoryConfigStorage;

public class ApiTestModule implements Module {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private static final String TEST_CONFIG_XML = "test-config.xml";

  private static <T> T fromXml(Class<T> clazz, InputStream is) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.alias("xml", clazz);
    xstream.processAnnotations(clazz);
    return (T) xstream.fromXML(is);
  }

  @Override
  public void configure(Binder binder) {
    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(TEST_CONFIG_XML)) {
      if (inputStream == null) {
        throw new RuntimeException("测试配置文件【" + TEST_CONFIG_XML + "】未找到，请参照test-config-sample.xml文件生成");
      }

      WxXmlCpInMemoryConfigStorage config = fromXml(WxXmlCpInMemoryConfigStorage.class, inputStream);
      WxCpService wxService = new WxCpServiceImpl();
      wxService.setWxCpConfigStorage(config);

      binder.bind(WxCpService.class).toInstance(wxService);
      binder.bind(WxXmlCpInMemoryConfigStorage.class).toInstance(config);
    } catch (IOException e) {
      this.log.error(e.getMessage(), e);
    }
  }

  @XStreamAlias("xml")
  public static class WxXmlCpInMemoryConfigStorage extends WxCpInMemoryConfigStorage {

    protected String userId;

    protected String departmentId;

    protected String tagId;

    public String getUserId() {
      return this.userId;
    }

    public void setUserId(String userId) {
      this.userId = userId;
    }

    public String getDepartmentId() {
      return this.departmentId;
    }

    public void setDepartmentId(String departmentId) {
      this.departmentId = departmentId;
    }

    public String getTagId() {
      return this.tagId;
    }

    public void setTagId(String tagId) {
      this.tagId = tagId;
    }

    @Override
    public String toString() {
      return super.toString() + " > WxXmlCpConfigStorage{" +
        "userId='" + this.userId + '\'' +
        ", departmentId='" + this.departmentId + '\'' +
        ", tagId='" + this.tagId + '\'' +
        '}';
    }
  }

}
