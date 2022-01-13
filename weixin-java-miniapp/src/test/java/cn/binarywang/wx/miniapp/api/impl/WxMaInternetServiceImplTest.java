package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.internet.WxMaInternetResponse;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 服务端网络相关接口测试
 *
 * @author <a href="https://github.com/chutian0124">chutian0124</a>
 * @date 2021-09-06
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaInternetServiceImplTest {
  @Inject
  private WxMaService wxService;

  private static String HMACSHA256(String data, String key) throws Exception {
    Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
    SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
    sha256_HMAC.init(secret_key);
    byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
    StringBuilder sb = new StringBuilder();
    for (byte item : array) {
      sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
    }
    return sb.toString().toUpperCase();
  }

  @Test
  public void testGetUserEncryptKey() throws Exception {
    String openid = "ogu-84hVFTbTt-myGisQESoDJ6BM";
    String signature = HMACSHA256("", "9ny8n3t0KULoi0deF7T9pw==");
    String sigMethod = "hmac_sha256";
    WxMaInternetResponse response = this.wxService.getInternetService().getUserEncryptKey(openid, signature, sigMethod);
    assertThat(response).isNotNull();
  }

  @Test
  public void testGetUserEncryptKey2() throws Exception {
    String openid = "ogu-84hVFTbTt-myGisQESoDJ6BM";
    String sessionKey = "9ny8n3t0KULoi0deF7T9pw==";
    WxMaInternetResponse response = this.wxService.getInternetService().getUserEncryptKey(openid, sessionKey);
    assertThat(response).isNotNull();
  }
}
