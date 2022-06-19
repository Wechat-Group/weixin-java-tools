package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader;
import com.github.binarywang.wxpay.bean.payscore.PayScoreNotifyData;
import com.github.binarywang.wxpay.bean.payscore.UserAuthorizationStatusNotifyResult;
import com.github.binarywang.wxpay.bean.payscore.WxPartnerPayScoreRequest;
import com.github.binarywang.wxpay.bean.payscore.WxPartnerPayScoreResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.PartnerPayScoreService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author hallkk
 * @date 2022/05/18
 */
@RequiredArgsConstructor
public class PartnerPayScoreServiceImpl implements PartnerPayScoreService {
  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  @Override
  public WxPartnerPayScoreResult permissions(WxPartnerPayScoreRequest request) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/partner/permissions";
    request.setAppid(request.getAppid());
    request.setServiceId(request.getServiceId());
    WxPayConfig config = this.payService.getConfig();
    String permissionNotifyUrl = config.getPayScorePermissionNotifyUrl();
    if (StringUtils.isBlank(permissionNotifyUrl)) {
      throw new WxPayException("授权回调地址未配置");
    }
    String authorizationCode = request.getAuthorizationCode();
    if (StringUtils.isBlank(authorizationCode)) {
      throw new WxPayException("authorizationCode不允许为空");
    }
    request.setNotifyUrl(permissionNotifyUrl);
    String result = this.payService.postV3(url, request.toJson());
    return WxPartnerPayScoreResult.fromJson(result);
  }

  @Override
  public WxPartnerPayScoreResult permissionsQueryByAuthorizationCode(String serviceId, String subMchid, String authorizationCode)
    throws WxPayException {
    if (StringUtils.isBlank(authorizationCode)) {
      throw new WxPayException("authorizationCode不允许为空");
    }
    String url = String.format("%s/v3/payscore/partner/permissions/authorization-code/%s", this.payService.getPayBaseUrl(), authorizationCode);
    URIBuilder uriBuilder;
    try {
      uriBuilder = new URIBuilder(url);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }

    uriBuilder.setParameter("service_id", serviceId);
    uriBuilder.setParameter("sub_mchid", subMchid);
    try {
      String result = payService.getV3(uriBuilder.build().toString());
      return WxPartnerPayScoreResult.fromJson(result);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }
  }

  @Override
  public WxPartnerPayScoreResult permissionsTerminateByAuthorizationCode(String serviceId, String subMchid,
                                                                         String authorizationCode, String reason) throws WxPayException {
    if (StringUtils.isBlank(authorizationCode)) {
      throw new WxPayException("authorizationCode不允许为空");
    }
    String url = String.format(
      "%s/v3/payscore/partner/permissions/authorization-code/%s/terminate",
      this.payService.getPayBaseUrl(),
      authorizationCode
    );
    Map<String, Object> map = new HashMap<>(4);
    map.put("service_id", serviceId);
    map.put("sub_mchid", subMchid);
    map.put("reason", reason);
    String result = payService.postV3(url, WxGsonBuilder.create().toJson(map));
    return WxPartnerPayScoreResult.fromJson(result);
  }

  @Override
  public WxPartnerPayScoreResult permissionsQueryByOpenId(String serviceId, String appId, String subMchid,
                                                          String subAppid, String openId, String subOpenid) throws WxPayException {
    if (StringUtils.isAllEmpty(openId, subOpenid) || !StringUtils.isAnyEmpty(openId, subOpenid)) {
      throw new WxPayException("open_id,sub_openid不允许都填写或都不填写");
    }
    if (StringUtils.isBlank(subMchid)) {
      throw new WxPayException("sub_mchid不允许都为空");
    }
    String url = String.format("%s/v3/payscore/partner/permissions/openid/%s", this.payService.getPayBaseUrl(), openId);
    URIBuilder uriBuilder;
    try {
      uriBuilder = new URIBuilder(url);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }

    uriBuilder.setParameter("appid", appId);
    uriBuilder.setParameter("service_id", serviceId);
    uriBuilder.setParameter("sub_mchid", subMchid);
    uriBuilder.setParameter("sub_appid", subAppid);
    uriBuilder.setParameter("openid", openId);
    uriBuilder.setParameter("sub_openid", subOpenid);

    if (StringUtils.isNotEmpty(openId)) {
      uriBuilder.setParameter("openid", openId);
    }
    if (StringUtils.isNotEmpty(subOpenid)) {
      uriBuilder.setParameter("sub_openid", subOpenid);
    }

    try {
      String result = payService.getV3(uriBuilder.build().toString());
      return WxPartnerPayScoreResult.fromJson(result);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }
  }

  @Override
  public WxPartnerPayScoreResult permissionsTerminateByOpenId(String serviceId, String appId, String subMchid, String subAppid, String openId, String subOpenid, String reason) throws WxPayException {
    if (StringUtils.isAllEmpty(openId, subOpenid) || !StringUtils.isAnyEmpty(openId, subOpenid)) {
      throw new WxPayException("open_id,sub_openid不允许都填写或都不填写");
    }
    String url = String.format("%s/v3/payscore/partner/permissions/openid/%s/terminate", this.payService.getPayBaseUrl(), openId);
    Map<String, Object> map = new HashMap<>(4);
    map.put("appid", appId);
    map.put("sub_appid", subAppid);
    map.put("service_id", serviceId);
    if (StringUtils.isNotEmpty(openId)) {
      map.put("openid", openId);
    }
    if (StringUtils.isNotEmpty(subOpenid)) {
      map.put("sub_openid", subOpenid);
    }
    map.put("sub_mchid", subMchid);
    map.put("reason", reason);
    String result = payService.postV3(url, WxGsonBuilder.create().toJson(map));
    return WxPartnerPayScoreResult.fromJson(result);
  }

  @Override
  public WxPartnerPayScoreResult createServiceOrder(WxPartnerPayScoreRequest request) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/partner/serviceorder";

    WxPayConfig config = this.payService.getConfig();
    request.setNotifyUrl(config.getPayScoreNotifyUrl());
    String result = this.payService.postV3(url, request.toJson());

    return WxPartnerPayScoreResult.fromJson(result);
  }

  @Override
  public WxPartnerPayScoreResult queryServiceOrder(String serviceId, String subMchid, String outOrderNo,
                                                   String queryId) throws WxPayException {
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/partner/serviceorder";

    URIBuilder uriBuilder;
    try {
      uriBuilder = new URIBuilder(url);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }
    uriBuilder.setParameter("service_id", serviceId);
    uriBuilder.setParameter("sub_mchid", subMchid);
    if (StringUtils.isAllEmpty(outOrderNo, queryId) || !StringUtils.isAnyEmpty(outOrderNo, queryId)) {
      throw new WxPayException("out_order_no,query_id不允许都填写或都不填写");
    }
    if (StringUtils.isNotEmpty(outOrderNo)) {
      uriBuilder.setParameter("out_order_no", outOrderNo);
    }
    if (StringUtils.isNotEmpty(queryId)) {
      uriBuilder.setParameter("query_id", queryId);
    }

    try {
      String result = payService.getV3(uriBuilder.build().toString());
      return WxPartnerPayScoreResult.fromJson(result);
    } catch (URISyntaxException e) {
      throw new WxPayException("未知异常！", e);
    }
  }

  @Override
  public WxPartnerPayScoreResult cancelServiceOrder(String serviceId, String appId, String subMchid, String outOrderNo, String reason) throws WxPayException {
    String url = String.format("%s/v3/payscore/partner/serviceorder/%s/cancel", this.payService.getPayBaseUrl(), outOrderNo);
    Map<String, Object> map = new HashMap<>(4);
    map.put("appid", appId);
    map.put("service_id", serviceId);
    map.put("sub_mchid", subMchid);
    map.put("reason", reason);
    if (StringUtils.isAnyEmpty(appId, serviceId, subMchid, reason)) {
      throw new WxPayException("appid, service_id, sub_mchid, reason都不能为空");
    }
    String result = payService.postV3(url, WxGsonBuilder.create().toJson(map));
    return WxPartnerPayScoreResult.fromJson(result);
  }

  @Override
  public WxPartnerPayScoreResult modifyServiceOrder(WxPartnerPayScoreRequest request) throws WxPayException {
    String outOrderNo = request.getOutOrderNo();
    String url = String.format("%s/v3/payscore/partner/serviceorder/%s/modify", this.payService.getPayBaseUrl(), outOrderNo);
    request.setAppid(this.payService.getConfig().getAppId());
    request.setOutOrderNo(null);
    String result = payService.postV3(url, request.toJson());
    return WxPartnerPayScoreResult.fromJson(result);
  }

  @Override
  public void completeServiceOrder(WxPartnerPayScoreRequest request) throws WxPayException {
    String outOrderNo = request.getOutOrderNo();
    String url = String.format("%s/v3/payscore/partner/serviceorder/%s/complete", this.payService.getPayBaseUrl(), outOrderNo);
    request.setAppid(request.getAppid());
    request.setServiceId(request.getServiceId());
    request.setOutOrderNo(null);
    request.setSubMchid(request.getSubMchid());
    this.payService.postV3(url, request.toJson());
  }

  @Override
  public WxPartnerPayScoreResult payServiceOrder(String serviceId, String appId, String subMchid, String outOrderNo) throws WxPayException {
    String url = String.format("%s/v3/payscore/partner/serviceorder/%s/pay", this.payService.getPayBaseUrl(), outOrderNo);
    Map<String, Object> map = new HashMap<>(3);
    map.put("appid", appId);
    map.put("service_id", serviceId);
    map.put("sub_mchid", subMchid);
    if (StringUtils.isAnyEmpty(appId, serviceId, subMchid)) {
      throw new WxPayException("appid, service_id, sub_mchid都不能为空");
    }
    String result = payService.postV3(url, WxGsonBuilder.create().toJson(map));
    return WxPartnerPayScoreResult.fromJson(result);
  }

  @Override
  public WxPartnerPayScoreResult syncServiceOrder(WxPartnerPayScoreRequest request) throws WxPayException {
    String outOrderNo = request.getOutOrderNo();
    String url = String.format("%s/v3/payscore/partner/serviceorder/%s/sync", this.payService.getPayBaseUrl(), outOrderNo);
    request.setAppid(this.payService.getConfig().getAppId());
    request.setOutOrderNo(null);
    String result = payService.postV3(url, request.toJson());
    return WxPartnerPayScoreResult.fromJson(result);
  }

  @Override
  public WxPartnerPayScoreResult applyServiceAccount(WxPartnerPayScoreRequest request) throws WxPayException {
    String url = String.format("%s/v3/payscore/partner/service-account-applications", this.payService.getPayBaseUrl());
    Map<String, String> params = Maps.newHashMap();
    params.put("service_id", request.getServiceId());
    params.put("appid", request.getAppid());
    params.put("sub_mchid", request.getSubMchid());
    params.put("sub_appid", request.getSubAppid());
    params.put("out_apply_no", request.getOutApplyNo());
    params.put("result_notify_url", request.getResultNotifyUrl());

    String result = payService.postV3(url, WxGsonBuilder.create().toJson(params));
    return WxPartnerPayScoreResult.fromJson(result);

  }

  @Override
  public WxPartnerPayScoreResult queryServiceAccountState(String outApplyNo) throws WxPayException {
    String url = String.format("%s/v3/payscore/partner/service-account-applications/%s", this.payService.getPayBaseUrl(), outApplyNo);
    String result = payService.getV3(url);
    return WxPartnerPayScoreResult.fromJson(result);
  }

  @Override
  public UserAuthorizationStatusNotifyResult parseUserAuthorizationStatusNotifyResult(String notifyData, SignatureHeader header) throws WxPayException {
    PayScoreNotifyData response = parseNotifyData(notifyData, header);
    PayScoreNotifyData.Resource resource = response.getResource();
    String cipherText = resource.getCipherText();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key = this.payService.getConfig().getApiV3Key();
    try {
      String result = AesUtils.decryptToString(associatedData, nonce, cipherText, apiV3Key);
      UserAuthorizationStatusNotifyResult notifyResult = GSON.fromJson(result, UserAuthorizationStatusNotifyResult.class);
      notifyResult.setRawData(response);
      return notifyResult;
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }

  @Override
  public PayScoreNotifyData parseNotifyData(String data, SignatureHeader header) throws WxPayException {
    if (Objects.nonNull(header) && !this.verifyNotifySign(header, data)) {
      throw new WxPayException("非法请求，头部信息验证失败");
    }
    return GSON.fromJson(data, PayScoreNotifyData.class);
  }

  @Override
  public WxPartnerPayScoreResult decryptNotifyDataResource(PayScoreNotifyData data) throws WxPayException {
    PayScoreNotifyData.Resource resource = data.getResource();
    String cipherText = resource.getCipherText();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key = this.payService.getConfig().getApiV3Key();
    try {
      return WxPartnerPayScoreResult.fromJson(AesUtils.decryptToString(associatedData, nonce, cipherText, apiV3Key));
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }

  /**
   * 校验通知签名
   *
   * @param header 通知头信息
   * @param data   通知数据
   * @return true:校验通过 false:校验不通过
   */
  private boolean verifyNotifySign(SignatureHeader header, String data) {
    String beforeSign = String.format("%s\n%s\n%s\n", header.getTimeStamp(), header.getNonce(), data);
    return this.payService.getConfig().getVerifier().verify(
      header.getSerialNo(),
      beforeSign.getBytes(StandardCharsets.UTF_8),
      header.getSigned()
    );
  }
}
