package cn.binarywang.wx.miniapp.config.impl;

import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author yqx
 * @date 2020/5/3
 */
public class WxMaRedissonConfigImplTest {

  WxMaDefaultConfigImpl wxMaConfig;

  @BeforeMethod
  public void setUp() {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://127.0.0.1:6379")
      .setDatabase(0);
    config.setTransportMode(TransportMode.NIO);
    RedissonClient redisson = Redisson.create(config);
    wxMaConfig = new WxMaRedissonConfigImpl(redisson);
    wxMaConfig.setAppid("appid12345678");
    wxMaConfig.updateAccessToken("accessToken", 5); //有效期5秒
    wxMaConfig.updateJsapiTicket("jsapiTicket", 5);
    wxMaConfig.updateCardApiTicket("cardApiTicket", 5);
  }

  @SneakyThrows
  @Test
  public void testGetAccessToken() {
    String accessToken = wxMaConfig.getAccessToken();
    Assert.assertEquals(accessToken, "accessToken");
    Assert.assertFalse(wxMaConfig.isAccessTokenExpired());
    Thread.sleep(6000);//休眠6s
    Assert.assertTrue(wxMaConfig.isAccessTokenExpired());
  }

  @SneakyThrows
  @Test
  public void testGetJsapiTicket() {
    String jsapiTicket = wxMaConfig.getJsapiTicket();
    Assert.assertEquals(jsapiTicket, "jsapiTicket");
    Assert.assertFalse(wxMaConfig.isJsapiTicketExpired());
    Thread.sleep(6000);//休眠6s
    Assert.assertTrue(wxMaConfig.isJsapiTicketExpired());
  }

  @SneakyThrows
  @Test
  public void testGetCardApiTicket() {
    String cardApiTicket = wxMaConfig.getCardApiTicket();
    Assert.assertEquals(cardApiTicket, "cardApiTicket");
    Assert.assertFalse(wxMaConfig.isCardApiTicketExpired());
    Thread.sleep(6000);//休眠6s
    Assert.assertTrue(wxMaConfig.isCardApiTicketExpired());
  }

  @Test
  public void testIsAccessTokenExpired() {
    Assert.assertFalse(wxMaConfig.isAccessTokenExpired());
    wxMaConfig.expireAccessToken();
    Assert.assertTrue(wxMaConfig.isAccessTokenExpired());
  }
}
