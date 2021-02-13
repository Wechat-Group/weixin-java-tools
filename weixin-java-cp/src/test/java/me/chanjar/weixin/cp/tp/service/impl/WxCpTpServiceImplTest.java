package me.chanjar.weixin.cp.tp.service.impl;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.redis.RedissonWxRedisOps;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpTpRedissonConfigImpl;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WxCpTpServiceImplTest {

  private WxCpTpService wxCpTpService;

  @BeforeMethod
  public void setUp() {
    wxCpTpService = new WxCpTpServiceImpl();
    wxCpTpService.setWxCpTpConfigStorage(wxCpTpConfigStorage());
  }

  public WxCpTpConfigStorage wxCpTpConfigStorage() {
    // 此处参数请在企业微信第三方开发者后台自行查找
    return WxCpTpRedissonConfigImpl.builder().baseApiUrl("https://qyapi.weixin.qq.com").suiteId("xxx")
      .suiteSecret("xxx").token("xxx").aesKey("xxx").corpId("xxx").corpSecret("xxx").providerSecret("xxx")
      .wxRedisOps(new RedissonWxRedisOps(redissonClient())).build();
  }

  public RedissonClient redissonClient() {
    Config config = new Config();
    // 此处输入redis地址和密码
    config.useSingleServer().setAddress("redis://xxx.xxx.xxx.xxx:6379").setConnectTimeout(10 * 1000).setDatabase(6)
      .setPassword("xxx").setConnectionMinimumIdleSize(2).setConnectionPoolSize(2);
    return Redisson.create(config);
  }

  @AfterMethod
  public void tearDown() {
  }

  @Test
  public void testGetSuiteAccessTokenEntity() throws WxErrorException {
    WxAccessToken suiteAccessTokenEntity = wxCpTpService.getSuiteAccessTokenEntity();
    System.out.println(suiteAccessTokenEntity);
  }

  @Test
  public void testTestGetSuiteAccessTokenEntity() {
  }

  @Test
  public void testGetSuiteAccessToken() {
  }

  @Test
  public void testGetWxCpProviderTokenEntity() {
  }

  @Test
  public void testTestGetWxCpProviderTokenEntity() {
  }

  @Test
  public void testGetCorpToken() {
  }

  @Test
  public void testTestGetCorpToken() {
  }

  @Test
  public void testGetAuthCorpJsApiTicket() {
  }

  @Test
  public void testTestGetAuthCorpJsApiTicket() {
  }

  @Test
  public void testGetSuiteJsApiTicket() {
  }

  @Test
  public void testTestGetSuiteJsApiTicket() {
  }
}
