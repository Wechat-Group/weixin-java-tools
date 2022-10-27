package me.chanjar.weixin.open.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.bean.result.WxOpenHaveResult;
import me.chanjar.weixin.open.bean.result.WxOpenResult;
import me.chanjar.weixin.open.bean.tcb.ShareCloudBaseEnvRequest;
import me.chanjar.weixin.open.bean.tcb.ShareCloudBaseEnvResponse;
import me.chanjar.weixin.open.bean.tcbComponent.GetShareCloudBaseEnvResponse;
import me.chanjar.weixin.open.bean.tcbComponent.GetTcbEnvListResponse;
import me.chanjar.weixin.open.test.ApiTestModule;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * 单元测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2020-06-06
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
  public void testHaveOpen() throws WxErrorException {
    WxOpenHaveResult wxOpenHaveResult = wxOpenComponentService.haveOpen();
    Assert.assertNotNull(wxOpenHaveResult);
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

  @Test
  public void testGetShareCloudBaseEnv() throws WxErrorException {
    GetShareCloudBaseEnvResponse response = wxOpenComponentService.getShareCloudBaseEnv(Arrays.asList("wxad2ee6fa2df2c46d"));
    Assert.assertNotNull(response);
  }

  @Test
  public void testGetTcbEnvListv() throws WxErrorException {
    GetTcbEnvListResponse response = wxOpenComponentService.getTcbEnvList();
    Assert.assertNotNull(response);
  }

  @Test
  public void testChangeTcbEnv() throws WxErrorException {
    WxOpenResult response = wxOpenComponentService.changeTcbEnv("test");
    Assert.assertNotNull(response);
  }

  @Test
  public void testShareCloudBaseEnv() throws WxErrorException {
    ShareCloudBaseEnvRequest request = ShareCloudBaseEnvRequest.builder()
      .data(Arrays.asList(ShareCloudBaseEnvRequest.DataDTO.builder()
        .env("test-env-6gni9ity244a6ea3").appids(Arrays.asList("wx5fe6bb43205e9e07")).build()))
      .build();
    ShareCloudBaseEnvResponse response = wxOpenComponentService.shareCloudBaseEnv(request);
    Assert.assertNotNull(response);
  }


  @Test
  public void testClearQuotaV2() throws WxErrorException {
    WxOpenResult wxOpenResult = wxOpenComponentService.clearQuotaV2("");
    Assert.assertNotNull(wxOpenResult);
  }
}
