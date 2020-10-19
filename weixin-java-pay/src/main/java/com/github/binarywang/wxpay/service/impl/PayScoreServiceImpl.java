package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.payscore.PayScoreNotifyData;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreRequest;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.PayScoreService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
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
  private final WxPayService payService;

  @Override
  public WxPayScoreResult permissions(WxPayScoreRequest request) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/permissions";
    request.setAppid(config.getAppId());
    request.setServiceId(config.getServiceId());
    String permissionNotifyUrl = config.getPayScorePermissionNotifyUrl();
    if (StringUtils.isBlank(permissionNotifyUrl)){
      throw new WxPayException("授权回调地址未配置");
    }
    String authorizationCode = request.getAuthorizationCode();
    if (StringUtils.isBlank(authorizationCode)){
      throw new WxPayException("authorizationCode不允许为空");
    }
    request.setNotifyUrl(permissionNotifyUrl);
    String result = this.payService.postV3(url, request.toJson());
   return WxPayScoreResult.fromJson(result);

  }

  @Override
  public WxPayScoreResult permissionsQueryByAuthorizationCode(String authorizationCode) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    if (StringUtils.isBlank(authorizationCode)){
      throw new WxPayException("authorizationCode不允许为空");
    }
    String url = String.format("%s/v3/payscore/permissions/authorization-code/%s", this.payService.getPayBaseUrl(), authorizationCode);
    URIBuilder uriBuilder;
    try {
      uriBuilder = new URIBuilder(url);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }

    uriBuilder.setParameter("service_id", config.getServiceId());
    try {
      String result = payService.getV3(uriBuilder.build());
      return WxPayScoreResult.fromJson(result);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }

  }

  @Override
  public WxPayScoreResult permissionsTerminateByAuthorizationCode(String authorizationCode,String reason) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    if (StringUtils.isBlank(authorizationCode)){
      throw new WxPayException("authorizationCode不允许为空");
    }
    String url = String.format("%s/v3/payscore/permissions/authorization-code/%s/terminate", this.payService.getPayBaseUrl(), authorizationCode);
    Map<String, Object> map = new HashMap<>(4);
    map.put("service_id", config.getServiceId());
    map.put("reason", reason);
    String result = payService.postV3(url, WxGsonBuilder.create().toJson(map));
    return WxPayScoreResult.fromJson(result);

  }

  @Override
  public WxPayScoreResult permissionsQueryByOpenId(String openId) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    if (StringUtils.isBlank(openId)){
      throw new WxPayException("openId不允许为空");
    }
    String url = String.format("%s/v3/payscore/permissions/openid/%s", this.payService.getPayBaseUrl(), openId);
    URIBuilder uriBuilder;
    try {
      uriBuilder = new URIBuilder(url);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }

    uriBuilder.setParameter("appid", config.getAppId());
    uriBuilder.setParameter("service_id", config.getServiceId());
    try {
      String result = payService.getV3(uriBuilder.build());
      return WxPayScoreResult.fromJson(result);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }

  }

  @Override
  public WxPayScoreResult permissionsTerminateByOpenId(String openId, String reason) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    if (StringUtils.isBlank(openId)){
      throw new WxPayException("openId不允许为空");
    }
    String url = String.format("%s/v3/payscore/permissions/openid/%s/terminate", this.payService.getPayBaseUrl(), openId);
    Map<String, Object> map = new HashMap<>(4);
    map.put("service_id", config.getServiceId());
    map.put("appid", config.getAppId());
    map.put("reason", reason);
    String result = payService.postV3(url, WxGsonBuilder.create().toJson(map));
    return WxPayScoreResult.fromJson(result);

  }

  @Override
  public WxPayScoreResult createServiceOrder(WxPayScoreRequest request) throws WxPayException {
    boolean needUserConfirm = request.isNeedUserConfirm();
    WxPayConfig config = this.payService.getConfig();
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/serviceorder";
    request.setAppid(config.getAppId());
    request.setServiceId(config.getServiceId());
    request.setNotifyUrl(config.getPayScoreNotifyUrl());
    String result = this.payService.postV3(url, request.toJson());
    WxPayScoreResult wxPayScoreCreateResult = WxPayScoreResult.fromJson(result);

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
  public WxPayScoreResult queryServiceOrder(String outOrderNo, String queryId) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/serviceorder";

    URIBuilder uriBuilder;
    try {
      uriBuilder = new URIBuilder(url);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }

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
    try {
      String result = payService.getV3(uriBuilder.build());
      return WxPayScoreResult.fromJson(result);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }

  }

  @Override
  public WxPayScoreResult cancelServiceOrder(String outOrderNo, String reason) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String url = String.format("%s/v3/payscore/serviceorder/%s/cancel", this.payService.getPayBaseUrl(), outOrderNo);
    Map<String, Object> map = new HashMap<>(4);
    map.put("appid", config.getAppId());
    map.put("service_id", config.getServiceId());
    map.put("reason", reason);
    String result = payService.postV3(url, WxGsonBuilder.create().toJson(map));
    return WxPayScoreResult.fromJson(result);
  }

  @Override
  public WxPayScoreResult modifyServiceOrder(WxPayScoreRequest request) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String outOrderNo = request.getOutOrderNo();
    String url = String.format("%s/v3/payscore/serviceorder/%s/modify", this.payService.getPayBaseUrl(), outOrderNo);
    request.setAppid(config.getAppId());
    request.setServiceId(config.getServiceId());
    request.setOutOrderNo(null);
    String result = payService.postV3(url, request.toJson());
    return WxPayScoreResult.fromJson(result);
  }

  @Override
  public WxPayScoreResult completeServiceOrder(WxPayScoreRequest request) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String outOrderNo = request.getOutOrderNo();
    String url = String.format("%s/v3/payscore/serviceorder/%s/complete", this.payService.getPayBaseUrl(), outOrderNo);
    request.setAppid(config.getAppId());
    request.setServiceId(config.getServiceId());
    request.setOutOrderNo(null);
    String result = payService.postV3(url, request.toJson());
    return WxPayScoreResult.fromJson(result);
  }

  @Override
  public WxPayScoreResult payServiceOrder(String outOrderNo) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String url = String.format("%s/v3/payscore/serviceorder/%s/pay", this.payService.getPayBaseUrl(), outOrderNo);
    Map<String, Object> map = new HashMap<>(2);
    map.put("appid", config.getAppId());
    map.put("service_id", config.getServiceId());
    String result = payService.postV3(url, WxGsonBuilder.create().toJson(map));
    return WxPayScoreResult.fromJson(result);
  }

  @Override
  public WxPayScoreResult syncServiceOrder(WxPayScoreRequest request) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String outOrderNo = request.getOutOrderNo();
    String url = String.format("%s/v3/payscore/serviceorder/%s/sync", this.payService.getPayBaseUrl(), outOrderNo);
    request.setAppid(config.getAppId());
    request.setServiceId(config.getServiceId());
    request.setOutOrderNo(null);
    String result = payService.postV3(url, request.toJson());
    return WxPayScoreResult.fromJson(result);
  }

  @Override
  public PayScoreNotifyData parseNotifyData(String data) {
    return WxGsonBuilder.create().fromJson(data, PayScoreNotifyData.class);
  }

  @Override
  public WxPayScoreResult decryptNotifyDataResource(PayScoreNotifyData data) throws WxPayException {
    PayScoreNotifyData.Resource resource = data.getResource();
    String cipherText = resource.getCipherText();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key = this.payService.getConfig().getApiV3Key();
    try {
      return WxPayScoreResult.fromJson(AesUtils.decryptToString(associatedData, nonce, cipherText, apiV3Key));
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }
}
