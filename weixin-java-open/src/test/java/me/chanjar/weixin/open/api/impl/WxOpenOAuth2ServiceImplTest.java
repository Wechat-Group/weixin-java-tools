package me.chanjar.weixin.open.api.impl;

import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * 单元测试.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2020-10-19
 */
public class WxOpenOAuth2ServiceImplTest {
  private final WxOpenOAuth2ServiceImpl service = new WxOpenOAuth2ServiceImpl("123", "",
    new WxOpenInMemoryConfigStorage());

  @BeforeTest
  public void init() {
//    this.service.setWxOpenConfigStorage(new WxOpenInMemoryConfigStorage());
  }

  @Test
  public void testBuildAuthorizationUrl() {
    this.service.buildAuthorizationUrl("", "", "");
  }

  @Test
  public void testGetAccessToken() throws WxErrorException {
    this.service.getAccessToken("a");
  }

  @Test
  public void testTestGetAccessToken() throws WxErrorException {
    this.service.getAccessToken("", "", "");
  }

  @Test
  public void testRefreshAccessToken() throws WxErrorException {
    this.service.refreshAccessToken("");
  }

  @Test
  public void testGetUserInfo() throws WxErrorException {
    this.service.getUserInfo(new WxOAuth2AccessToken(), "");
  }

  @Test
  public void testValidateAccessToken() {
    this.service.validateAccessToken(new WxOAuth2AccessToken());
  }
}
