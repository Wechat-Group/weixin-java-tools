package com.github.binarywang.wxpay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.payscore.NotifyData;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreRequest;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.PayScoreService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.AesUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author doger.wang
 * @date 2020/5/14 9:43
 */

public class PayScoreServiceImpl implements PayScoreService {
  private WxPayService payService;

  public PayScoreServiceImpl(WxPayService payService) {
    this.payService = payService;
  }


  @Override
  public WxPayScoreResult createServiceOrder(WxPayScoreRequest request) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/serviceorder";
    request.setAppid(config.getAppId());
    request.setService_id(config.getServiceId());
    request.setNotify_url(config.getPayScoreNotifyUrl());
    String result = payService.postV3(url, JSONObject.toJSONString(request));
    WxPayScoreResult wxPayScoreCreateResult = JSONObject.parseObject(result, WxPayScoreResult.class);
    //补充算一下签名给小程序跳转用
    String currentTimeMillis = System.currentTimeMillis() + "";
    Map<String, String> signMap = new HashMap<>();
    signMap.put("mch_id", config.getMchId());
    signMap.put("service_id", config.getServiceId());
    signMap.put("out_order_no", request.getOut_order_no());
    signMap.put("timestamp", currentTimeMillis);
    signMap.put("nonce_str", currentTimeMillis);
    signMap.put("sign_type", "HMAC-SHA256");
    String sign = AesUtil.createSign(signMap, config.getMchKey());
    signMap.put("sign", sign);
    wxPayScoreCreateResult.setPayScoreSignInfo(signMap);
    return wxPayScoreCreateResult;
  }

  @Override
  public WxPayScoreResult queryServiceOrder(String out_order_no, String query_id) throws WxPayException, URISyntaxException {
    WxPayConfig config = this.payService.getConfig();
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/serviceorder";
    URIBuilder uriBuilder =   new URIBuilder(url);
    if (StringUtils.isAllEmpty(out_order_no,query_id) || !StringUtils.isAnyEmpty(out_order_no,query_id)){
      throw new WxPayException("out_order_no,query_id不允许都填写或都不填写");
    }
    if (StringUtils.isNotEmpty(out_order_no)){
      uriBuilder.setParameter("out_order_no", out_order_no);
    }
    if (StringUtils.isNotEmpty(query_id)){
      uriBuilder.setParameter("query_id", query_id);
    }
    uriBuilder.setParameter("service_id", config.getServiceId());
    uriBuilder.setParameter("appid", config.getAppId());
    URI build = uriBuilder.build();
    String result = payService.getV3(build);
    WxPayScoreResult wxPayScoreCreateResult = JSONObject.parseObject(result, WxPayScoreResult.class);
    //补充一下加密跳转信息
    String currentTimeMillis = System.currentTimeMillis() + "";
    Map<String, String> signMap = new HashMap();
    signMap.put("mch_id", config.getMchId());
    signMap.put("service_id", config.getServiceId());
    signMap.put("out_order_no", out_order_no);
    signMap.put("timestamp", currentTimeMillis);
    signMap.put("nonce_str", currentTimeMillis);
    signMap.put("sign_type", "HMAC-SHA256");
    String sign = AesUtil.createSign(signMap, config.getMchKey());
    signMap.put("sign", sign);
    wxPayScoreCreateResult.setPayScoreSignInfo(signMap);
    return wxPayScoreCreateResult;

  }



  @Override
  public WxPayScoreResult cancelServiceOrder(String out_order_no, String reason) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/serviceorder/"+out_order_no+"/cancel";
    HashMap<String, Object> map = new HashMap<>();
    map.put("appid",config.getAppId());
    map.put("service_id",config.getServiceId());
    map.put("reason",reason);
    String result = payService.postV3(url, JSONObject.toJSONString(map));
    WxPayScoreResult wxPayScoreCreateResult = JSONObject.parseObject(result, WxPayScoreResult.class);
    return wxPayScoreCreateResult;

  }

  @Override
  public WxPayScoreResult modifyServiceOrder(WxPayScoreRequest request) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String out_order_no = request.getOut_order_no();
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/serviceorder/"+out_order_no+"/modify";
    request.setAppid(config.getAppId());
    request.setService_id(config.getServiceId());
    //request.setNotify_url(config.getPayScoreNotifyUrl());
    String result = payService.postV3(url, JSONObject.toJSONString(request));
    WxPayScoreResult wxPayScoreCreateResult = JSONObject.parseObject(result, WxPayScoreResult.class);
    return wxPayScoreCreateResult;


  }

  @Override
  public WxPayScoreResult completeServiceOrder(WxPayScoreRequest request) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String out_order_no = request.getOut_order_no();
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/serviceorder/"+out_order_no+"/complete";
    request.setAppid(config.getAppId());
    request.setService_id(config.getServiceId());
    //request.setNotify_url(config.getPayScoreNotifyUrl());
    String result = payService.postV3(url, JSONObject.toJSONString(request));
    WxPayScoreResult wxPayScoreCreateResult = JSONObject.parseObject(result, WxPayScoreResult.class);
    return wxPayScoreCreateResult;

  }

  @Override
  public WxPayScoreResult payServiceOrder(String out_order_no) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/serviceorder/"+out_order_no+"/pay";
    HashMap<String, Object> map = new HashMap<>();
    map.put("appid",config.getAppId());
    map.put("service_id",config.getServiceId());
    String result = payService.postV3(url, JSONObject.toJSONString(map));
    WxPayScoreResult wxPayScoreCreateResult = JSONObject.parseObject(result, WxPayScoreResult.class);
    return wxPayScoreCreateResult;

  }

  @Override
  public WxPayScoreResult syncServiceOrder(WxPayScoreRequest request) throws WxPayException {
    WxPayConfig config = this.payService.getConfig();
    String out_order_no = request.getOut_order_no();
    String url = this.payService.getPayBaseUrl() + "/v3/payscore/serviceorder/"+out_order_no+"/sync";
    request.setAppid(config.getAppId());
    request.setService_id(config.getServiceId());
    //request.setNotify_url(config.getPayScoreNotifyUrl());
    String result = payService.postV3(url, JSONObject.toJSONString(request));
    WxPayScoreResult wxPayScoreCreateResult = JSONObject.parseObject(result, WxPayScoreResult.class);
    return wxPayScoreCreateResult;

  }

  @Override
  public WxPayScoreResult decryptNotifyData(NotifyData data) throws WxPayException{
    NotifyData.Resource resource = data.getResource();
    String ciphertext = resource.getCiphertext();
    String associated_data = resource.getAssociated_data();
    String nonce = resource.getNonce();
    String apiv3Key = this.payService.getConfig().getApiv3Key();
    try {
      String s = AesUtil.decryptToString(associated_data, nonce, ciphertext, apiv3Key);
      WxPayScoreResult wxPayScoreCreateResult = JSONObject.parseObject(s, WxPayScoreResult.class);
      return wxPayScoreCreateResult;
    } catch (GeneralSecurityException e) {
      throw new WxPayException("解析报文异常",e);
    } catch (IOException e) {
      throw new WxPayException("解析报文异常",e);
    }

  }
}
