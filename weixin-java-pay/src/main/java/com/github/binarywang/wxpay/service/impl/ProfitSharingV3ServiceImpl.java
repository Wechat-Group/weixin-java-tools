package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.profitsharingV3.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.ProfitSharingV3Service;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信支付V3-资金应用-分账Service
 *
 * @author pg 2021-6-23
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
public class ProfitSharingV3ServiceImpl implements ProfitSharingV3Service {
  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  @Override
  public ProfitSharingResult profitSharing(ProfitSharingRequest request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/orders", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ProfitSharingResult.class);
  }

  @Override
  public ProfitSharingResult getProfitSharingResult(String outOrderNo, String transactionId) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/orders/%s?transaction_id=%s", this.payService.getPayBaseUrl(), outOrderNo, transactionId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ProfitSharingResult.class);
  }

  @Override
  public ProfitSharingReturnResult profitSharingReturn(ProfitSharingReturnRequest request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/return-orders", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ProfitSharingReturnResult.class);
  }

  @Override
  public ProfitSharingReturnResult getProfitSharingReturnResult(String outOrderNo, String outReturnNo) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/return-orders/%s?out_order_no=%s", this.payService.getPayBaseUrl(), outReturnNo, outOrderNo);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ProfitSharingReturnResult.class);
  }

  @Override
  public ProfitSharingUnfreezeResult profitSharingUnfreeze(ProfitSharingUnfreezeRequest request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/orders/unfreeze", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ProfitSharingUnfreezeResult.class);
  }

  @Override
  public ProfitSharingUnsplitResult getProfitSharingUnsplitAmount(String transactionId) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/transactions/%s/amounts", this.payService.getPayBaseUrl(), transactionId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ProfitSharingUnsplitResult.class);
  }

  @Override
  public ProfitSharingReceiver addProfitSharingReceiver(ProfitSharingReceiver request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/receivers/add", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ProfitSharingReceiver.class);
  }

  @Override
  public ProfitSharingReceiver deleteProfitSharingReceiver(ProfitSharingReceiver request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/receivers/delete", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ProfitSharingReceiver.class);
  }
}
