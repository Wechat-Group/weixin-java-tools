package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopCatGetResponse;
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
public class WxMaShopCatServiceImplTest {

  @Inject
  private WxMaService wxService;

  @Test
  public void testGetCat() throws WxErrorException {
    WxMaShopCatGetResponse response = this.wxService.getShopCatService().getCat();
    assertThat(response).isNotNull();
  }
}
