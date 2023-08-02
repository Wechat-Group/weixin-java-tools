package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopRegisterApplySceneRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopRegisterFinishAccessInfoRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopRegisterCheckResponse;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author liming1019
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaShopRegisterServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testRegisterApply() throws Exception {
    WxMaShopBaseResponse response = this.wxService.getShopRegisterService().registerApply();
    assertThat(response).isNotNull();
  }

  @Test
  public void testRegisterCheck() throws Exception {
    WxMaShopRegisterCheckResponse response = this.wxService.getShopRegisterService().registerCheck();
    assertThat(response).isNotNull();
  }

  @Test
  public void testRegisterFinishAccessInfo() throws Exception {
    WxMaShopRegisterFinishAccessInfoRequest request = new WxMaShopRegisterFinishAccessInfoRequest();
    request.setAccessInfoItem(6L);
    WxMaShopBaseResponse response = this.wxService.getShopRegisterService().registerFinishAccessInfo(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testRegisterApplyScene() throws Exception {
    WxMaShopRegisterApplySceneRequest request = new WxMaShopRegisterApplySceneRequest();
    request.setSceneGroupId(1L);
    WxMaShopBaseResponse response = this.wxService.getShopRegisterService().registerApplyScene(request);
    assertThat(response).isNotNull();
  }
}
