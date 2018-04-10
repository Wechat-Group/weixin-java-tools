package me.chanjar.weixin.cp.api;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.WxCpInMemoryConfigStorage;

import java.io.IOException;
import java.io.InputStream;

public class ApiTestModule implements Module {

  public static <T> T fromXml(Class<T> clazz, InputStream is) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.alias("xml", clazz);
    xstream.processAnnotations(clazz);
    return (T) xstream.fromXML(is);
  }

  @Override
  public void configure(Binder binder) {
    try (InputStream is1 = ClassLoader
      .getSystemResourceAsStream("test-config.xml")) {
      WxXmlCpInMemoryConfigStorage config = fromXml(
        WxXmlCpInMemoryConfigStorage.class, is1);
      WxCpService wxService = new WxCpServiceImpl();
      wxService.setWxCpConfigStorage(config);

      binder.bind(WxCpService.class).toInstance(wxService);
      binder.bind(WxXmlCpInMemoryConfigStorage.class).toInstance(config);
    } catch (IOException e) {
      e.printStackTrace();
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
