package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.payscore.PayScoreNotifyData;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreRequest;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.PayScoreService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author doger.wang
 * @date 2020/5/14 9:43
 */
@RequiredArgsConstructor
public class PayScoreServiceImpl implements PayScoreService {
  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  @Override
  public WxPayScoreResult createServiceOrder(WxPayScoreRequest request) throws WxPayException {
    boolean needUserConfirm = request.isNeedUserConfirm();
    WxPayConfig config = this.payService.getConfig();
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/serviceorder";
    request.setAppid(config.getAppId());
    request.setServiceId(config.getServiceId());
    request.setNotifyUrl(config.getPayScoreNotifyUrl());
    String result = payService.postV3(url, GSON.toJson(request));
    WxPayScoreResult wxPayScoreCreateResult = GSON.fromJson(result, WxPayScoreResult.class);

    //补充算一下签名给小程序跳转用
    String currentTimeMillis = System.currentTimeMillis() + "";
    Map<String, String> signMap = new HashMap<>(8);
    signMap.put("mch_id", config.getMchId());
    if (needUserConfirm) {
      signMap.put("package", wxPayScoreCreateResult.getPackageX());
    } else {
      signMap.put("service_id", config.getServiceId());
      signMap.put("out_order_no", request.getOutOrderNo());
    }
    signMap.put("timestamp", currentTimeMillis);
    signMap.put("nonce_str", currentTimeMillis);
    signMap.put("sign_type", "HMAC-SHA256");
    String sign = AesUtils.createSign(signMap, config.getMchKey());
    signMap.put("sign", sign);
    wxPayScoreCreateResult.setPayScoreSignInfo(signMap);
    return wxPayScoreCreateResult;
  }

  @Override
  public WxPayScoreResult queryServiceOrder(String outOrderNo, String queryId) throws WxPayException, URISyntaxException {
    WxPayConfig config = this.payService.getConfig();
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/serviceorder";
    URIBuilder uriBuilder = new URIBuilder(url);
    if (StringUtils.isAllEmpty(outOrderNo, queryId) || !StringUtils.isAnyEmpty(outOrderNo, queryId)) {
      throw new WxPayException("out_order_no,query_id不允许都填写或都不填写");
    }
    if (StringUtils.isNotEmpty(outOrderNo)) {
      uriBuilder.setParameter("out_order_no", outOrderNo);
    }
    if (StringUtils.isNotEmpty(queryId)) {
      uriBuilder.setParameter("query_id", queryId);
    }
    uriBuilder.setParameter("service_id", config.getServiceId());
    uriBuilder.setParameter("appid", config.getAppId());
    String result = payService.getV3(uriBuilder.build());
    return GSON.fromJson(result, WxPayScoreResult.class);
  }

  @Override
  public WxPayScoreResult cancelServiceOrder(String outOrderNo, String reason) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String url = String.format("%s/v3/payscore/serviceorder/%s/cancel", this.payService.getPayBaseUrl(), outOrderNo);
    HashMap<String, Object> map = new HashMap<>(4);
    map.put("appid", config.getAppId());
    map.put("service_id", config.getServiceId());
    map.put("reason", reason);
    String result = payService.postV3(url, GSON.toJson(map));
    return GSON.fromJson(result, WxPayScoreResult.class);
  }

  @Override
  public WxPayScoreResult modifyServiceOrder(WxPayScoreRequest request) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String outOrderNo = request.getOutOrderNo();
    String url = String.format("%s/v3/payscore/serviceorder/%s/modify", this.payService.getPayBaseUrl(), outOrderNo);
    request.setAppid(config.getAppId());
    request.setServiceId(config.getServiceId());
    request.setOutOrderNo(null);
    String result = payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(result, WxPayScoreResult.class);
  }

  @Override
  public WxPayScoreResult completeServiceOrder(WxPayScoreRequest request) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String outOrderNo = request.getOutOrderNo();
    String url = String.format("%s/v3/payscore/serviceorder/%s/complete", this.payService.getPayBaseUrl(), outOrderNo);
    request.setAppid(config.getAppId());
    request.setServiceId(config.getServiceId());
    request.setOutOrderNo(null);
    String result = payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(result, WxPayScoreResult.class);
  }

  @Override
  public WxPayScoreResult payServiceOrder(String outOrderNo) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String url = String.format("%s/v3/payscore/serviceorder/%s/pay", this.payService.getPayBaseUrl(), outOrderNo);
    HashMap<String, Object> map = new HashMap<>(2);
    map.put("appid", config.getAppId());
    map.put("service_id", config.getServiceId());
    String result = payService.postV3(url, GSON.toJson(map));
    return GSON.fromJson(result, WxPayScoreResult.class);
  }

  @Override
  public WxPayScoreResult syncServiceOrder(WxPayScoreRequest request) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String outOrderNo = request.getOutOrderNo();
    String url = String.format("%s/v3/payscore/serviceorder/%s/sync", this.payService.getPayBaseUrl(), outOrderNo);
    request.setAppid(config.getAppId());
    request.setServiceId(config.getServiceId());
    request.setOutOrderNo(null);
    String result = payService.postV3(url, GSON.toJson(request));
    return GSON.fromJson(result, WxPayScoreResult.class);
  }

  @Override
  public PayScoreNotifyData parseNotifyData(String data){
    return GSON.fromJson(data, PayScoreNotifyData.class);

  }

  @Override
  public WxPayScoreResult decryptNotifyDataResource(PayScoreNotifyData data) throws WxPayException {
    PayScoreNotifyData.Resource resource = data.getResource();
    String cipherText = resource.getCipherText();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key = this.payService.getConfig().getApiV3Key();
    try {
      String s = AesUtils.decryptToString(associatedData, nonce, cipherText, apiV3Key);
      return GSON.fromJson(s, WxPayScoreResult.class);
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }
}
