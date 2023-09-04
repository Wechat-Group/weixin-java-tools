package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import cn.binarywang.wx.miniapp.api.WxMaXPayService;
import cn.binarywang.wx.miniapp.bean.xpay.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.XPay.*;


@RequiredArgsConstructor
@Slf4j
public class WxMaXPayServiceImpl implements WxMaXPayService {

  private final WxMaService service;

  @Override
  public WxMaXPayQueryUserBalanceResponse queryUserBalance(WxMaXPayQueryUserBalanceRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithBoth(QUERY_USER_BALANCE_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayQueryUserBalanceResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayQueryUserBalanceResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaXPayCurrencyPayResponse currencyPay(WxMaXPayCurrencyPayRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithBoth(CURRENCY_PAY_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayCurrencyPayResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayCurrencyPayResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaXPayQueryOrderResponse queryOrder(WxMaXPayQueryOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(QUERY_ORDER_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayQueryOrderResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayQueryOrderResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return getDetailResponse;
  }

  @Override
  public WxMaXPayCancelCurrencyPayResponse cancelCurrencyPay(WxMaXPayCancelCurrencyPayRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithBoth(CANCEL_CURRENCY_PAY_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayCancelCurrencyPayResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayCancelCurrencyPayResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return getDetailResponse;
  }

  @Override
  public boolean notifyProvideGoods(WxMaXPayNotifyProvideGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(NOTIFY_PROVIDE_GOODS_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaBaseResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaBaseResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return true;
  }

  @Override
  public WxMaXPayPresentCurrencyResponse presentCurrency(WxMaXPayPresentCurrencyRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(PRESENT_CURRENCY_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayPresentCurrencyResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayPresentCurrencyResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaXPayDownloadBillResponse downloadBill(WxMaXPayDownloadBillRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(DOWNLOAD_BILL_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayDownloadBillResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayDownloadBillResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaXPayRefundOrderResponse refundOrder(WxMaXPayRefundOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(REFUND_ORDER_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayRefundOrderResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayRefundOrderResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaXPayCreateWithdrawOrderResponse createWithdrawOrder(WxMaXPayCreateWithdrawOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(CREATE_WITHDRAW_ORDER_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayCreateWithdrawOrderResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayCreateWithdrawOrderResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaXPayQueryWithdrawOrderResponse queryWithdrawOrder(WxMaXPayQueryWithdrawOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(QUERY_WITHDRAW_ORDER_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayQueryWithdrawOrderResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayQueryWithdrawOrderResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse;
  }

  @Override
  public boolean startUploadGoods(WxMaXPayStartUploadGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(START_UPLOAD_GOODS_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaBaseResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaBaseResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return true;
  }

  @Override
  public WxMaXPayQueryUploadGoodsResponse queryUploadGoods(WxMaXPayQueryUploadGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(QUERY_UPLOAD_GOODS_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayQueryUploadGoodsResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayQueryUploadGoodsResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse;
  }

  @Override
  public boolean startPublishGoods(WxMaXPayStartPublishGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(START_PUBLISH_GOODS_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaBaseResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaBaseResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return true;
  }

  @Override
  public WxMaXPayQueryPublishGoodsResponse queryPublishGoods(WxMaXPayQueryPublishGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException {
    final String postBody = request.toJson();
    final String uri = sigParams.signUriWithPay(QUERY_PUBLISH_GOODS_URL, postBody);
    String responseContent = this.service.post(uri, postBody);
    WxMaXPayQueryPublishGoodsResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaXPayQueryPublishGoodsResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse;
  }
}
