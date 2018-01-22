package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConfigStorage;
import me.chanjar.weixin.mp.bean.result.WxMpCurrentAutoReplyInfo;
import org.testng.*;
import org.testng.annotations.*;

import static org.testng.Assert.*;

@Test
@Guice(modules = ApiTestModule.class)
public class WxMpServiceImplTest {
  @Inject
  private WxMpService wxService;

  @Test
  public void testGetCurrentAutoReplyInfo() throws WxErrorException {
    WxMpCurrentAutoReplyInfo autoReplyInfo = this.wxService.getCurrentAutoReplyInfo();

    assertNotNull(autoReplyInfo);
    System.out.println(autoReplyInfo);
  }

  @Test
  public void testClearQuota() throws WxErrorException {
    this.wxService.clearQuota(wxService.getWxMpConfigStorage().getAppId());
  }

  @Test
  public void testBuildQrConnectUrl() {
    String qrconnectRedirectUrl = ((TestConfigStorage) this.wxService.getWxMpConfigStorage()).getQrconnectRedirectUrl();
    String qrConnectUrl = this.wxService.buildQrConnectUrl(qrconnectRedirectUrl,
      WxConsts.QrConnectScope.SNSAPI_LOGIN, null);
    Assert.assertNotNull(qrConnectUrl);
    System.out.println(qrConnectUrl);
  }

}
