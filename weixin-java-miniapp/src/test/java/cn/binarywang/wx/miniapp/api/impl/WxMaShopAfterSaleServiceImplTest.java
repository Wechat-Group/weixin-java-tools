package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAfterSaleAddRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAfterSaleGetRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAfterSaleUpdateRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAfterSaleGetResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author liming1019
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaShopAfterSaleServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testAdd() throws WxErrorException {
    WxMaShopAfterSaleAddRequest.ProductInfosBean productInfosBean = WxMaShopAfterSaleAddRequest.ProductInfosBean.builder()
      .outProductId("19030")
      .outSkuId("123266")
      .productCnt(1)
      .build();
    WxMaShopAfterSaleAddRequest request = WxMaShopAfterSaleAddRequest.builder()
      .outOrderId("318070290792415232X")
      .outAftersaleId("318092069606883328X")
      .openid("odIi15CuQ0IQviqsnUMy6CKNetrMX")
      .type(1)
      .createTime("2021-08-20 00:00:00")
      .status(1)
      .finishAllAftersale(0)
      .path("/pages/aftersale.html?out_aftersale_id=318092069606883328X")
      .refund(100L)
      .productInfos(new ArrayList<>(Arrays.asList(productInfosBean)))
      .build();
    WxMaShopBaseResponse response = wxService.getShopAfterSaleService().add(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testGet() throws WxErrorException {
    WxMaShopAfterSaleGetRequest request = WxMaShopAfterSaleGetRequest.builder()
      .openid("oTVP50O53a7jgmawAmxKukNlq3XI")
      .orderId(32434234L)
      .outOrderId("xxxxx")
      .build();
    WxMaShopAfterSaleGetResponse response = wxService.getShopAfterSaleService().get(request);
    assertThat(response).isNotNull();
  }

  @Test
  public void testUpdate() throws WxErrorException {
    WxMaShopAfterSaleUpdateRequest request = WxMaShopAfterSaleUpdateRequest.builder()
      .outOrderId("xxxxx")
      .openid("oTVP50O53a7jgmawAmxKukNlq3XI")
      .outAftersaleId("xxxxxx")
      .status(1)
      .finishAllAftersale(0)
      .build();
    WxMaShopBaseResponse response = wxService.getShopAfterSaleService().update(request);
    assertThat(response).isNotNull();
  }
}
