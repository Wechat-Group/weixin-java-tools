package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopPayCreateOrderRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopPayCreateOrderResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopPayGetOrderResponse;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Test
@Guice(modules = ApiTestModule.class)
public class WxMaShopPayServiceImplTest {

  @Inject
  private WxMaService wxService;

  @Test
  public void testCreateOrder() throws Exception {
    WxMaShopPayCreateOrderRequest request =
      WxMaShopPayCreateOrderRequest.builder()
        .openid("")
        .combineTradeNo("")
        .expireTime(1234L)
        .subOrders(Arrays.asList(WxMaShopPayCreateOrderRequest.SubOrdersDTO.builder()
            .mchid("")
            .amount(0)
            .tradeNo("")
            .description("")
          .build()
        ))
        .build();
    WxMaShopPayCreateOrderResponse response = wxService.getWxMaShopPayService().createOrder(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testGetOrder() throws Exception {
    WxMaShopPayGetOrderResponse response = wxService.getWxMaShopPayService().getOrder("457243057210572800");
    assertThat(response).isNotNull();
  }
}
