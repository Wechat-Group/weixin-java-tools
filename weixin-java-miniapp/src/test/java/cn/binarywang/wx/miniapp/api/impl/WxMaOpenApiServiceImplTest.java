package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.openapi.WxMiniGetApiQuotaResult;
import cn.binarywang.wx.miniapp.bean.openapi.WxMiniGetRidInfoResult;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.gson.Gson;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

/**
 * openApi管理测试
 *
 * @author shuiyihan12
 * @since 2023/7/7 17:08
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaOpenApiServiceImplTest {

  @Inject
  private WxMaService wxMaService;


  @Test
  public void clearQuota() throws WxErrorException {
    final boolean result = wxMaService.getWxMaOpenApiService().clearQuota();
    assertTrue(result);
  }

  @Test
  public void getApiQuota() throws WxErrorException {
    String cgiPath = "/cgi-bin/openapi/quota/get";
    final WxMiniGetApiQuotaResult apiQuota = wxMaService.getWxMaOpenApiService().getApiQuota(cgiPath);
    assertNotNull(apiQuota);
    System.out.println(new Gson().toJson(apiQuota));
  }

  @Test
  public void getApiQuotaInfo() throws WxErrorException {
    String rid = "658723fa-2d3a0086-64bc7215";
    final WxMiniGetRidInfoResult ridInfo = wxMaService.getWxMaOpenApiService().getRidInfo(rid);
    assertNotNull(ridInfo);
    System.out.println(new Gson().toJson(ridInfo));
  }

  @Test
  public void clearQuotaByAppSecret() throws WxErrorException {
    final boolean result = wxMaService.getWxMaOpenApiService().clearQuotaByAppSecret();
    assertTrue(result);
  }

}
