package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.marketing.*;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MarketingBusiFavorService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付-营销商家券接口
 *
 * @author yujam
 */
@Slf4j
@RequiredArgsConstructor
public class MarketingBusiFavorServiceImpl implements MarketingBusiFavorService {

  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  @Override
  public BusiFavorStocksCreateResult createBusiFavorStocksV3(BusiFavorStocksCreateRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/busifavor/stocks", this.payService.getPayBaseUrl());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, BusiFavorStocksCreateResult.class);
  }

  @Override
  public BusiFavorStocksGetResult getBusiFavorStocksV3(String stockId) throws WxPayException {
    String url = String.format("%s/v3/marketing/busifavor/stocks/%s", this.payService.getPayBaseUrl(), stockId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, BusiFavorStocksGetResult.class);
  }

  @Override
  public BusiFavorCouponsUseResult verifyBusiFavorCouponsUseV3(BusiFavorCouponsUseRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/busifavor/coupons/use", this.payService.getPayBaseUrl());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, BusiFavorCouponsUseResult.class);
  }

  @Override
  public String buildBusiFavorCouponinfoUrl(BusiFavorCouponsUrlRequest request) throws WxPayException {
    Map<String, String> signMap = new HashMap<>(8);
    signMap.put("out_request_no", request.getOutRequestNo());
    signMap.put("stock_id", request.getStockId());
    signMap.put("send_coupon_merchant", request.getSendCouponMerchant());
    signMap.put("open_id", request.getOpenid());

    String sign = SignUtils.createSign(signMap, WxPayConstants.SignType.HMAC_SHA256, this.payService.getConfig().getMchKey(), null);
    String actionBaseUrl = "https://action.weixin.qq.com";
    return String.format("%s/busifavor/getcouponinfo?stock_id=%s&out_request_no=%s&sign=%s&send_coupon_merchant=%s&open_id=%s#wechat_redirect",
      actionBaseUrl, request.getStockId(), request.getOutRequestNo(), sign, request.getSendCouponMerchant(), request.getOpenid());
  }

  @Override
  public BusiFavorQueryUserCouponsResult queryBusiFavorUsersCoupons(BusiFavorQueryUserCouponsRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/busifavor/users/%s/coupons", this.payService.getPayBaseUrl(), request.getOpenid());

    if (request.getOffset() == null) {
      request.setOffset(0);
    }

    if (request.getLimit() == null || request.getLimit() <= 0) {
      request.setLimit(20);
    }

    String query = String.format("?appid=%s&offset=%s&limit=%s", request.getAppid(), request.getOffset(), request.getLimit());

    if (StringUtils.isNotBlank(request.getStockId())) {
      query += "&stock_id=" + request.getStockId();
    }
    if (StringUtils.isNotBlank(request.getCouponState())) {
      query += "&coupon_state=" + request.getCouponState();
    }
    if (StringUtils.isNotBlank(request.getCreatorMerchant())) {
      query += "&creator_merchant=" + request.getCreatorMerchant();
    }
    if (StringUtils.isNotBlank(request.getBelongMerchant())) {
      query += "&belong_merchant=" + request.getBelongMerchant();
    }
    if (StringUtils.isNotBlank(request.getSenderMerchant())) {
      query += "&sender_merchant=" + request.getSenderMerchant();
    }

    String result = this.payService.getV3(url + query);
    return GSON.fromJson(result, BusiFavorQueryUserCouponsResult.class);
  }

  @Override
  public BusiFavorQueryOneUserCouponsResult queryOneBusiFavorUsersCoupons(BusiFavorQueryOneUserCouponsRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/busifavor/users/%s/coupons/%s/appids/%s", this.payService.getPayBaseUrl(), request.getOpenid(), request.getCouponCode(), request.getAppid());
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, BusiFavorQueryOneUserCouponsResult.class);
  }

  @Override
  public BusiFavorCouponCodeResult uploadBusiFavorCouponCodes(String stockId, BusiFavorCouponCodeRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/busifavor/stocks/%s/couponcodes", this.payService.getPayBaseUrl(), stockId);
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, BusiFavorCouponCodeResult.class);
  }

  @Override
  public BusiFavorCallbacksResult createBusiFavorCallbacks(BusiFavorCallbacksRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/busifavor/callbacks", this.payService.getPayBaseUrl());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, BusiFavorCallbacksResult.class);
  }

  @Override
  public BusiFavorCallbacksResult queryBusiFavorCallbacks(BusiFavorCallbacksRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/busifavor/callbacks", this.payService.getPayBaseUrl());
    if (StringUtils.isNotBlank(request.getMchid())) {
      url += "?mchid=" + request.getMchid();
    }
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, BusiFavorCallbacksResult.class);
  }

  @Override
  public BusiFavorCouponsAssociateResult queryBusiFavorCouponsAssociate(BusiFavorCouponsAssociateRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/busifavor/coupons/associate", this.payService.getPayBaseUrl());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, BusiFavorCouponsAssociateResult.class);
  }

  @Override
  public BusiFavorCouponsAssociateResult queryBusiFavorCouponsDisAssociate(BusiFavorCouponsAssociateRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/busifavor/coupons/disassociate", this.payService.getPayBaseUrl());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, BusiFavorCouponsAssociateResult.class);
  }

  @Override
  public BusiFavorStocksBudgetResult updateBusiFavorStocksBudget(String stockId, BusiFavorStocksBudgetRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/busifavor/stocks/%s/budget", this.payService.getPayBaseUrl(), stockId);
    String result = payService.patchV3(url, GSON.toJson(request));
    return GSON.fromJson(result, BusiFavorStocksBudgetResult.class);
  }

  @Override
  public String updateBusiFavorStocksV3(String stockId, BusiFavorStocksCreateRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/busifavor/stocks/%s", this.payService.getPayBaseUrl(), stockId);
    return this.payService.patchV3(url, GSON.toJson(request));
  }

  @Override
  public BusiFavorCouponsReturnResult returnBusiFavorCoupons(BusiFavorCouponsReturnRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/busifavor/coupons/return", this.payService.getPayBaseUrl());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, BusiFavorCouponsReturnResult.class);
  }

  @Override
  public BusiFavorCouponsDeactivateResult deactiveBusiFavorCoupons(BusiFavorCouponsDeactivateRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/busifavor/coupons/deactivate", this.payService.getPayBaseUrl());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, BusiFavorCouponsDeactivateResult.class);
  }

  @Override
  public BusiFavorSubsidyResult subsidyBusiFavorPayReceipts(BusiFavorSubsidyRequest request) throws WxPayException {
    String url = String.format("%s/v3/marketing/busifavor/subsidy/pay-receipts", this.payService.getPayBaseUrl());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, BusiFavorSubsidyResult.class);
  }

  @Override
  public BusiFavorSubsidyResult queryBusiFavorSubsidyPayReceipts(String subsidyReceiptId) throws WxPayException {
    String url = String.format("%s/v3/marketing/busifavor/subsidy/pay-receipts/%s", this.payService.getPayBaseUrl(), subsidyReceiptId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, BusiFavorSubsidyResult.class);
  }

  @Override
  public BusiFavorNotifyResult notifyBusiFavor(String url, BusiFavorNotifyRequest request) throws WxPayException {
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, BusiFavorNotifyResult.class);
  }
}
