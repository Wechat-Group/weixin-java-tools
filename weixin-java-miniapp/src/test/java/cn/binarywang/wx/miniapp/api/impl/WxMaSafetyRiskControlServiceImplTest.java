package cn.binarywang.wx.miniapp.api.impl;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.safety.request.WxMaUserSafetyRiskRankRequest;
import cn.binarywang.wx.miniapp.bean.safety.response.WxMaUserSafetyRiskRankResponse;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;

@Test
@Guice(modules = ApiTestModule.class)
public class WxMaSafetyRiskControlServiceImplTest {

  @Inject
  protected WxMaService wxService;

  @Test
  public void testGetUserRiskRank() throws WxErrorException {
    WxMaUserSafetyRiskRankRequest wxMaUserSafetyRiskRankRequest = WxMaUserSafetyRiskRankRequest.builder()
      .appid("")
      .openid("")
      .scene(1)
      .isTest(true)
      .build();
    WxMaUserSafetyRiskRankResponse wxMaUserSafetyRiskRankResponse = this.wxService.getSafetyRiskControlService().getUserRiskRank(wxMaUserSafetyRiskRankRequest);
    assertNotNull(wxMaUserSafetyRiskRankResponse);
  }

}
