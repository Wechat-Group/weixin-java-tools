package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.safety.request.WxMaUserSafetyRiskRankRequest;
import cn.binarywang.wx.miniapp.bean.safety.response.WxMaUserSafetyRiskRankResponse;
import cn.binarywang.wx.miniapp.bean.security.WxMaMsgSecCheckCheckRequest;
import cn.binarywang.wx.miniapp.bean.security.WxMaMsgSecCheckCheckResponse;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * <pre>
 *
 * Created by Binary Wang on 2018/11/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaSecurityServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testCheckImage() throws WxErrorException {
    boolean result = this.wxService.getSecurityService()
      .checkImage(new File(ClassLoader.getSystemResource("tmp.png").getFile()));
    assertTrue(result);
  }

  @DataProvider
  public Object[][] secData() {
    return new Object[][]{
      {"特3456书yuuo莞6543李zxcz蒜7782法fgnv级", false},
      {"完2347全dfji试3726测asad感3847知qwez到", false},
      {"提现&下载&棋牌游戏&网页", false},
      {"hello world!", true}
    };
  }

  @Test(dataProvider = "secData")
  public void testCheckMessage(String msg, boolean result) throws WxErrorException {
    assertThat(this.wxService.getSecurityService()
      .checkMessage(msg))
      .isEqualTo(result);
  }

  @Test(dataProvider = "secData")
  public void testCheckMessage2(String msg, boolean result) throws WxErrorException {
    WxMaMsgSecCheckCheckRequest request = WxMaMsgSecCheckCheckRequest.builder()
      .content(msg)
      .scene(1)
      .version("2")
      .openid("xxx")
      .build();
    WxMaMsgSecCheckCheckResponse response = this.wxService.getSecurityService().checkMessage(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testGetUserRiskRank() throws WxErrorException {
    WxMaUserSafetyRiskRankRequest wxMaUserSafetyRiskRankRequest = WxMaUserSafetyRiskRankRequest.builder()
      .appid("")
      .openid("")
      .scene(1)
      .isTest(true)
      .build();
    WxMaUserSafetyRiskRankResponse wxMaUserSafetyRiskRankResponse = this.wxService.getSecurityService().getUserRiskRank(wxMaUserSafetyRiskRankRequest);
    assertNotNull(wxMaUserSafetyRiskRankResponse);
  }
}
