package me.chanjar.weixin.mp.api.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.ReentrantLock;

import me.chanjar.weixin.mp.api.impl.WxMpServiceHttpClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.thoughtworks.xstream.XStream;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;

public class ApiTestModule implements Module {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private static final String TEST_CONFIG_XML = "test-config.xml";

  @Override
  public void configure(Binder binder) {
    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(TEST_CONFIG_XML)) {
      if (inputStream == null) {
        throw new RuntimeException("测试配置文件【" + TEST_CONFIG_XML + "】未找到，请参照test-config-sample.xml文件生成");
      }

      TestConfigStorage config = this.fromXml(TestConfigStorage.class, inputStream);
      config.setAccessTokenLock(new ReentrantLock());
      WxMpService mpService = new WxMpServiceHttpClientImpl();

      mpService.setWxMpConfigStorage(config);
      mpService.addConfigStorage("another", config);

      binder.bind(WxMpConfigStorage.class).toInstance(config);
      binder.bind(WxMpService.class).toInstance(mpService);
    } catch (IOException e) {
      this.log.error(e.getMessage(), e);
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
