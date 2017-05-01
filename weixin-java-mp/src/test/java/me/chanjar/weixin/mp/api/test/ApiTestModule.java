package me.chanjar.weixin.mp.api.test;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.thoughtworks.xstream.XStream;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.ReentrantLock;

public class ApiTestModule implements Module {

  @Override
  public void configure(Binder binder) {
    try (InputStream is1 = ClassLoader.getSystemResourceAsStream("test-config.xml")) {
      TestConfigStorage config = this.fromXml(TestConfigStorage.class, is1);
      config.setAccessTokenLock(new ReentrantLock());
      WxMpService wxService = new WxMpServiceImpl();
      wxService.setWxMpConfigStorage(config);

      binder.bind(WxMpService.class).toInstance(wxService);
      binder.bind(WxMpConfigStorage.class).toInstance(config);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private <T> T fromXml(Class<T> clazz, InputStream is) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.alias("xml", clazz);
    xstream.processAnnotations(clazz);
    return (T) xstream.fromXML(is);
  }

}
