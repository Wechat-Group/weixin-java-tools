package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.ecommerce.*;
import com.github.binarywang.wxpay.bean.ecommerce.enums.SpAccountTypeEnum;
import com.github.binarywang.wxpay.bean.ecommerce.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.EcommerceService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Objects;

@RequiredArgsConstructor
public class EcommerceServiceImpl implements EcommerceService {
  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  @Override
  public ApplymentsResult createApply(ApplymentsRequest request) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/applyments/", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());

    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ApplymentsResult.class);
  }

  @Override
  public ApplymentsStatusResult queryApplyStatusByApplymentId(String applymentId) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/applyments/%s", this.payService.getPayBaseUrl(), applymentId);
    String result = this.payService.getV3(URI.create(url));
    return GSON.fromJson(result, ApplymentsStatusResult.class);
  }

  @Override
  public ApplymentsStatusResult queryApplyStatusByOutRequestNo(String outRequestNo) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/applyments/out-request-no/%s", this.payService.getPayBaseUrl(), outRequestNo);
    String result = this.payService.getV3(URI.create(url));
    return GSON.fromJson(result, ApplymentsStatusResult.class);
  }

  @Override
  public TransactionsResult combine(TradeTypeEnum tradeType, CombineTransactionsRequest request) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + tradeType.getCombineUrl();
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, TransactionsResult.class);
  }

  @Override
  public <T> T combineTransactions(TradeTypeEnum tradeType, CombineTransactionsRequest request) throws WxPayException {
    TransactionsResult result = this.combine(tradeType, request);
    return result.getPayInfo(tradeType, request.getCombineAppid(),
      request.getCombineMchid(), payService.getConfig().getPrivateKey());
  }

  @Override
  public CombineTransactionsNotifyResult parseCombineNotifyResult(String notifyData, SignatureHeader header) throws WxPayException {
    if(Objects.nonNull(header) && !this.verifyNotifySign(header, notifyData)){
      throw new WxPayException("非法请求，头部信息验证失败");
    }
    NotifyResponse response = GSON.fromJson(notifyData, NotifyResponse.class);
    NotifyResponse.Resource resource = response.getResource();
    String cipherText = resource.getCiphertext();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key = this.payService.getConfig().getApiV3Key();
    try {
      String result = AesUtils.decryptToString(associatedData, nonce,cipherText, apiV3Key);
      CombineTransactionsNotifyResult notifyResult = GSON.fromJson(result, CombineTransactionsNotifyResult.class);
      notifyResult.setRawData(response);
      return notifyResult;
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }

  @Override
  public TransactionsResult partner(TradeTypeEnum tradeType, PartnerTransactionsRequest request) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + tradeType.getPartnerUrl();
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, TransactionsResult.class);
  }

  @Override
  public <T> T partnerTransactions(TradeTypeEnum tradeType, PartnerTransactionsRequest request) throws WxPayException {
    TransactionsResult result = this.partner(tradeType, request);
    return result.getPayInfo(tradeType, request.getSpAppid(),
      request.getSpMchid(), payService.getConfig().getPrivateKey());
  }

  @Override
  public PartnerTransactionsNotifyResult parsePartnerNotifyResult(String notifyData, SignatureHeader header) throws WxPayException {
    if(Objects.nonNull(header) && !this.verifyNotifySign(header, notifyData)){
      throw new WxPayException("非法请求，头部信息验证失败");
    }
    NotifyResponse response = GSON.fromJson(notifyData, NotifyResponse.class);
    NotifyResponse.Resource resource = response.getResource();
    String cipherText = resource.getCiphertext();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key = this.payService.getConfig().getApiV3Key();
    try {
      String result = AesUtils.decryptToString(associatedData, nonce,cipherText, apiV3Key);
      PartnerTransactionsNotifyResult notifyResult = GSON.fromJson(result, PartnerTransactionsNotifyResult.class);
      notifyResult.setRawData(response);
      return notifyResult;
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }

  @Override
  public FundBalanceResult spNowBalance(SpAccountTypeEnum accountType) throws WxPayException {
    String url = String.format("%s/v3/merchant/fund/balance/%s", this.payService.getPayBaseUrl(), accountType);
    URI uri = URI.create(url);
    String response = this.payService.getV3(uri);
    return GSON.fromJson(response, FundBalanceResult.class);
  }

  @Override
  public FundBalanceResult spDayEndBalance(SpAccountTypeEnum accountType, String date) throws WxPayException {
    String url = String.format("%s/v3/merchant/fund/dayendbalance/%s?date=%s", this.payService.getPayBaseUrl(), accountType, date);
    URI uri = URI.create(url);
    String response = this.payService.getV3(uri);
    return GSON.fromJson(response, FundBalanceResult.class);
  }

  @Override
  public FundBalanceResult subNowBalance(String subMchid) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/fund/balance/%s", this.payService.getPayBaseUrl(), subMchid);
    URI uri = URI.create(url);
    String response = this.payService.getV3(uri);
    return GSON.fromJson(response, FundBalanceResult.class);
  }

  @Override
  public FundBalanceResult subDayEndBalance(String subMchid, String date) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/fund/enddaybalance/%s?date=%s", this.payService.getPayBaseUrl(), subMchid, date);
    URI uri = URI.create(url);
    String response = this.payService.getV3(uri);
    return GSON.fromJson(response, FundBalanceResult.class);
  }

  @Override
  public ProfitSharingResult profitSharing(ProfitSharingRequest request) throws WxPayException {
    String url = String.format("%s/v3/ecommerce/profitsharing/orders", this.payService.getPayBaseUrl());
    String response = this.payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(response, ProfitSharingResult.class);
  }

  private boolean verifyNotifySign(SignatureHeader header, String data) {
    String beforeSign = String.format("%s\n%s\n%s\n",
      header.getTimeStamp(),
      header.getNonce(),
      data);
    return payService.getConfig().getVerifier().verify(header.getSerialNo(),
      beforeSign.getBytes(StandardCharsets.UTF_8), header.getSigned());
  }
}
