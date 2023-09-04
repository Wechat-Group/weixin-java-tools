package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.xpay.*;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertNotNull;

@Test
@Guice(modules = ApiTestModule.class)
public class WxMaXPayServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testQueryUserBalance() throws Exception {
    WxMaXPayQueryUserBalanceRequest request = WxMaXPayQueryUserBalanceRequest.builder()
      .openid("")
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .userIp("127.0.0.1")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayQueryUserBalanceResponse response = this.wxService.getWxMaXPayService().queryUserBalance(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testCurrencyPay() throws Exception {
    WxMaXPayCurrencyPayRequest request = WxMaXPayCurrencyPayRequest.builder()
      .openid("")
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .userIp("127.0.0.1")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayCurrencyPayResponse response = this.wxService.getWxMaXPayService().currencyPay(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testQueryOrder() throws Exception {
    WxMaXPayQueryOrderRequest request = WxMaXPayQueryOrderRequest.builder()
      .openid("")
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .orderId("")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayQueryOrderResponse response = this.wxService.getWxMaXPayService().queryOrder(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testCancelCurrencyPay() throws Exception {
    WxMaXPayCancelCurrencyPayRequest request = WxMaXPayCancelCurrencyPayRequest.builder()
      .openid("")
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .userIp("127.0.0.1")
      .orderId("")
      .payOrderId("")
      .amount(1000L)
      .deviceType(WxMaConstants.XPayDeviceType.ANDROID)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayCancelCurrencyPayResponse response = this.wxService.getWxMaXPayService().cancelCurrencyPay(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testNotifyProvideGoods() throws Exception {
    WxMaXPayNotifyProvideGoodsRequest request = WxMaXPayNotifyProvideGoodsRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .orderId("")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    boolean response = this.wxService.getWxMaXPayService().notifyProvideGoods(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testPresentCurrency() throws Exception {
    WxMaXPayPresentCurrencyRequest request = WxMaXPayPresentCurrencyRequest.builder()
      .openid("")
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .orderId("").deviceType(WxMaConstants.XPayDeviceType.ANDROID)
      .amount(100L)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayPresentCurrencyResponse response = this.wxService.getWxMaXPayService().presentCurrency(request, sigParams);
    assertNotNull(response);
  }


  @Test
  public void testDownloadBill() throws Exception {
    WxMaXPayDownloadBillRequest request = WxMaXPayDownloadBillRequest.builder()
      .beginDs(20230801)
      .endDs(20230810)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayDownloadBillResponse response = this.wxService.getWxMaXPayService().downloadBill(request, sigParams);
    assertNotNull(response);
  }
  @Test
  public void testRefundOrder() throws Exception {
    WxMaXPayRefundOrderRequest request = WxMaXPayRefundOrderRequest.builder()
      .openid("")
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .orderId("")
      .refundOrderId("")
      .leftFee(100L)
      .refundFee(10L)
      .bizMeta("").refundReason("").reqFrom("")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayRefundOrderResponse response = this.wxService.getWxMaXPayService().refundOrder(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testCreateWithdrawOrder() throws Exception {
    WxMaXPayCreateWithdrawOrderRequest request = WxMaXPayCreateWithdrawOrderRequest.builder()
      .withdrawNo("")
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .withdrawAmount("0.01")
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayCreateWithdrawOrderResponse response = this.wxService.getWxMaXPayService().createWithdrawOrder(request, sigParams);
    assertNotNull(response);
  }


  @Test
  public void testQueryWithdrawOrder() throws Exception {
    WxMaXPayQueryWithdrawOrderRequest request = WxMaXPayQueryWithdrawOrderRequest.builder()
      .withdrawNo("")
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayQueryWithdrawOrderResponse response = this.wxService.getWxMaXPayService().queryWithdrawOrder(request, sigParams);
    assertNotNull(response);
  }


  @Test
  public void testStartUploadGoods() throws Exception {
    WxMaXPayStartUploadGoodsRequest request = WxMaXPayStartUploadGoodsRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .uploadItem(new ArrayList<>())
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    boolean response = this.wxService.getWxMaXPayService().startUploadGoods(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testQueryUploadGoods() throws Exception {
    WxMaXPayQueryUploadGoodsRequest request = WxMaXPayQueryUploadGoodsRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayQueryUploadGoodsResponse response = this.wxService.getWxMaXPayService().queryUploadGoods(request, sigParams);
    assertNotNull(response);
  }


  @Test
  public void testStartPublishGoods() throws Exception {
    WxMaXPayStartPublishGoodsRequest request = WxMaXPayStartPublishGoodsRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .publishItem(new ArrayList<>())
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    boolean response = this.wxService.getWxMaXPayService().startPublishGoods(request, sigParams);
    assertNotNull(response);
  }

  @Test
  public void testQueryPublishGoods() throws Exception {
    WxMaXPayQueryPublishGoodsRequest request = WxMaXPayQueryPublishGoodsRequest.builder()
      .env(WxMaConstants.XPayEnv.PRODUCT)
      .build();
    WxMaXPaySigParams sigParams = new WxMaXPaySigParams();
    sigParams.setSessionKey("");
    sigParams.setAppKey("");
    WxMaXPayQueryPublishGoodsResponse response = this.wxService.getWxMaXPayService().queryPublishGoods(request, sigParams);
    assertNotNull(response);
  }

}
