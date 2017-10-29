package me.chanjar.weixin.open.api.test;

import com.thoughtworks.xstream.XStream;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;

import com.google.inject.Binder;
import com.google.inject.Module;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.WxOpenInMemoryConfigStorage;
import me.chanjar.weixin.open.api.WxOpenService;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ApiTestModule implements Module {

  @Override
  public void configure(Binder binder) {
    try (InputStream is1 = ClassLoader.getSystemResourceAsStream("test-config.xml")) {
      TestConfigStorage testConfig = this.fromXml(TestConfigStorage.class, is1);

      Map<String, String> refreshTokenMap = new HashMap<>();
      refreshTokenMap.put(testConfig.getAuthorizerAppid(), testConfig.getAuthorizerRefreshToken());

      WxOpenInMemoryConfigStorage wxOpenConfigStorage = new WxOpenInMemoryConfigStorage();
      wxOpenConfigStorage.setAppId(testConfig.getComponentAppid());
      wxOpenConfigStorage.setSecret(testConfig.getComponentAppsecret());
      wxOpenConfigStorage.setToken(testConfig.getComponentToken());
      wxOpenConfigStorage.setAesKey(testConfig.getComponentAesKey());

      WxOpenServiceImpl wxOpenService = new WxOpenServiceImpl();
      wxOpenService.setWxOpenConfigStorage(wxOpenConfigStorage);
      wxOpenService.setRefreshTokenMap(refreshTokenMap);
      wxOpenService.setComponentVerifyTicket(testConfig.getComponentVerifyTicket());

      binder.bind(WxOpenService.class).toInstance(wxOpenService);
      binder.bind(WxOpenConfigStorage.class).toInstance(wxOpenConfigStorage);
      binder.bind(TestConfigStorage.class).toInstance(testConfig);
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
