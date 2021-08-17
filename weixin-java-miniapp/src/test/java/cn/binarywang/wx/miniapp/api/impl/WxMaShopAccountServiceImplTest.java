package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAccountUpdateInfoRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopRegisterApplySceneRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopRegisterFinishAccessInfoRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.*;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author liming1019
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaShopAccountServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testGetCategoryList() throws WxErrorException {
    WxMaShopAccountGetCategoryListResponse response = this.wxService.getShopAccountService().getCategoryList();
    assertThat(response).isNotNull();
  }

  @Test
  public void testGetBrandList() throws WxErrorException {
    WxMaShopAccountGetBrandListResponse response = this.wxService.getShopAccountService().getBrandList();
    assertThat(response).isNotNull();
  }

  @Test
  public void testUpdateInfo() throws WxErrorException {
    WxMaShopAccountUpdateInfoRequest request = new WxMaShopAccountUpdateInfoRequest();
    request.setServiceAgentPhone("020-888888");
    request.setServiceAgentPath("https://www.web.com");
    WxMaShopBaseResponse response = this.wxService.getShopAccountService().updateInfo(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testGetInfo() throws WxErrorException {
    WxMaShopAccountGetInfoResponse response = this.wxService.getShopAccountService().getInfo();
    assertThat(response).isNotNull();
  }
}
