package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.applyment.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.Applyment4SubService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.cert.X509Certificate;

@Slf4j
@RequiredArgsConstructor
public class Applyment4SubServiceImpl implements Applyment4SubService {
  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  private void encryptFiled(Object request) throws WxPayException {

    X509Certificate validCertificate = payService.getConfig().getVerifier().getValidCertificate();

    RsaCryptoUtil.encryptFields(request, validCertificate);
  }


  @Override
  public WxPayApplymentCreateResult createApply(WxPayApplyment4SubCreateRequest request) throws WxPayException {
    String url = String.format("%s/v3/applyment4sub/applyment/", this.payService.getPayBaseUrl());

    encryptFiled(request);

    String result = payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, WxPayApplymentCreateResult.class);
  }

  @Override
  public ApplymentStateQueryResult queryApplyStatusByBusinessCode(String businessCode) throws WxPayException {
    String url = String.format("%s/v3/applyment4sub/applyment/business_code/%s", this.payService.getPayBaseUrl(), businessCode);
    String result = payService.getV3(url);
    return GSON.fromJson(result, ApplymentStateQueryResult.class);
  }

  @Override
  public ApplymentStateQueryResult queryApplyStatusByApplymentId(String applymentId) throws WxPayException {
    String url = String.format("%s/v3/applyment4sub/applyment/applyment_id/%s", this.payService.getPayBaseUrl(), applymentId);
    String result = payService.getV3(url);
    return GSON.fromJson(result, ApplymentStateQueryResult.class);
  }

  @Override
  public SettlementInfoResult querySettlementBySubMchid(String subMchid) throws WxPayException {
    String url = String.format("%s/v3/apply4sub/sub_merchants/%s/settlement", this.payService.getPayBaseUrl(), subMchid);
    String result = payService.getV3(url);
    return GSON.fromJson(result, SettlementInfoResult.class);
  }

  @Override
  public String modifySettlement(String subMchid, ModifySettlementRequest request) throws WxPayException {
    String url = String.format("%s/v3/apply4sub/sub_merchants/%s/modify-settlement", this.payService.getPayBaseUrl(), subMchid);
    encryptFiled(request);
    return payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
  }

  @Override
  public SettlementApplicationResult settlementApplication(String subMchid, String applicationNo) throws WxPayException {
    String url = String.format("%s/v3/apply4sub/sub_merchants/%s/application/%s", this.payService.getPayBaseUrl(), subMchid, applicationNo);
    String result = payService.getV3(url);
    return GSON.fromJson(result, SettlementApplicationResult.class);
  }
}
