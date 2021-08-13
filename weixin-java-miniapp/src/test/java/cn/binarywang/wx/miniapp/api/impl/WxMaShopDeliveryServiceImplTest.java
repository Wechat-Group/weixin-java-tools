package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopDeliveryRecieveRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopDeliverySendRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopDeliveryGetCompanyListResponse;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.Assert.assertNotNull;

/**
 * @author liming1019
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaShopDeliveryServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testGetCompanyList() throws WxErrorException {
    WxMaShopDeliveryGetCompanyListResponse response = wxService.getShopDeliveryService().getCompanyList();
    assertNotNull(response);
  }

  @Test
  public void testSend() throws WxErrorException {
    WxMaShopDeliverySendRequest.DeliveryListBean deliveryListBean = WxMaShopDeliverySendRequest.DeliveryListBean.builder()
      .deliveryId("SF")
      .waybillId("23424324253")
      .build();
    WxMaShopDeliverySendRequest request = WxMaShopDeliverySendRequest.builder()
      .orderId(123456L)
      .outOrderId("xxxxx")
      .openid("oTVP50O53a7jgmawAmxKukNlq3XI")
      .finishAllDelivery(0)
      .deliveryList(new ArrayList<>(Arrays.asList(deliveryListBean)))
      .build();
    WxMaShopBaseResponse response = wxService.getShopDeliveryService().send(request);
    assertNotNull(response);
  }

  @Test
  public void testReceive() throws WxErrorException {
    WxMaShopDeliveryRecieveRequest request = WxMaShopDeliveryRecieveRequest.builder()
      .openid("oTVP50O53a7jgmawAmxKukNlq3XI")
      .orderId(123456L)
      .outOrderId("xxxxx")
      .build();
    WxMaShopBaseResponse response = wxService.getShopDeliveryService().receive(request);
    assertNotNull(response);
  }
}
