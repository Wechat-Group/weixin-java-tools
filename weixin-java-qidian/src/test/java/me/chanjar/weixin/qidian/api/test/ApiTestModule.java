package me.chanjar.weixin.qidian.api.test;

import com.google.inject.Binder;
import com.google.inject.Module;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.xml.XmlUtil;
import me.chanjar.weixin.qidian.api.WxQidianService;
import me.chanjar.weixin.qidian.api.impl.WxQidianServiceHttpClientImpl;
import me.chanjar.weixin.qidian.config.WxQidianConfigStorage;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ApiTestModule implements Module {
  private static final String TEST_CONFIG_XML = "test-config.xml";

  @Override
  public void configure(Binder binder) {
    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(TEST_CONFIG_XML)) {
      if (inputStream == null) {
        throw new WxRuntimeException("测试配置文件【" + TEST_CONFIG_XML + "】未找到，请参照test-config-sample.xml文件生成");
      }

      TestConfigStorage config = XmlUtil.toObject(inputStream, TestConfigStorage.class);
      config.setAccessTokenLock(new ReentrantLock());
      WxQidianService mpService = new WxQidianServiceHttpClientImpl();

      mpService.setWxMpConfigStorage(config);
      mpService.addConfigStorage("another", config);

      binder.bind(WxQidianConfigStorage.class).toInstance(config);
      binder.bind(WxQidianService.class).toInstance(mpService);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
  }


}
