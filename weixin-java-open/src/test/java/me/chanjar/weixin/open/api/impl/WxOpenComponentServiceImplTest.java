package me.chanjar.weixin.open.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.test.ApiTestModule;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 单元测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-06-06
 */
@Guice(modules = ApiTestModule.class)
public class WxOpenComponentServiceImplTest {

  @Inject
  WxOpenComponentService wxOpenComponentService;

  @Test
  public void testGetWxMpServiceByAppid() {
  }

  @Test
  public void testGetWxMaServiceByAppid() {
  }

  @Test
  public void testGetWxFastMaServiceByAppid() {
  }

  @Test
  public void testGetWxOpenService() {
  }

  @Test
  public void testGetWxOpenConfigStorage() {
  }

  @Test
  public void testCheckSignature() {
  }

  @Test
  public void testGetComponentAccessToken() {
  }

  @Test
  public void testPost() {
  }

  @Test
  public void testTestPost() {
  }

  @Test
  public void testGet() {
  }

  @Test
  public void testTestGet() {
  }

  @Test
  public void testGetPreAuthUrl() {
  }

  @Test
  public void testTestGetPreAuthUrl() {
  }

  @Test
  public void testGetMobilePreAuthUrl() {
  }

  @Test
  public void testTestGetMobilePreAuthUrl() {
  }

  @Test
  public void testRoute() {
  }

  @Test
  public void testGetQueryAuth() {
  }

  @Test
  public void testGetAuthorizerInfo() {
  }

  @Test
  public void testGetAuthorizerList() {
  }

  @Test
  public void testGetAuthorizerOption() {
  }

  @Test
  public void testSetAuthorizerOption() {
  }

  @Test
  public void testGetAuthorizerAccessToken() {
  }

  @Test
  public void testOauth2getAccessToken() {
  }

  @Test
  public void testTestCheckSignature() {
  }

  @Test
  public void testOauth2refreshAccessToken() {
  }

  @Test
  public void testOauth2buildAuthorizationUrl() {
  }

  @Test
  public void testMiniappJscode2Session() {
  }

  @Test
  public void testGetTemplateDraftList() {
  }

  @Test
  public void testGetTemplateList() {
  }

  @Test
  public void testAddToTemplate() {
  }

  @Test
  public void testDeleteTemplate() {
  }

  @Test
  public void testCreateOpenAccount() {
  }

  @Test
  public void testBindOpenAccount() {
  }

  @Test
  public void testUnbindOpenAccount() {
  }

  @Test
  public void testGetOpenAccount() {
  }

  @Test
  public void testFastRegisterWeapp() {
  }

  @Test
  public void testFastRegisterWeappSearch() {
  }

  @Test
  public void testStartPushTicket() throws WxErrorException {
    wxOpenComponentService.startPushTicket();
  }
}
