package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.utils.qrcode.QrcodeUtils;
import com.github.binarywang.wxpay.bean.request.*;
import com.github.binarywang.wxpay.bean.result.*;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.common.collect.Maps;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Binary Wang on 2016/7/28.
 *
 * @author binarywang (https://github.com/binarywang)
 */
public class WxPayServiceImpl implements WxPayService {

  private static final String PAY_BASE_URL = "https://api.mch.weixin.qq.com";
  private static final String[] TRADE_TYPES = new String[]{"JSAPI", "NATIVE", "APP"};
  private static final String[] REFUND_ACCOUNT = new String[]{"REFUND_SOURCE_RECHARGE_FUNDS", "REFUND_SOURCE_UNSETTLED_FUNDS"};
  private static final String[] BILL_TYPE = new String[]{"ALL", "REFUND", "SUCCESS"};

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private WxPayConfig config;

  @Override
  public WxPayConfig getConfig() {
    return this.config;
  }

  @Override
  public void setConfig(WxPayConfig config) {
    this.config = config;
  }

  private String getPayBaseUrl() {
    if (this.getConfig().useSandboxForWxPay()) {
      return PAY_BASE_URL + "/sandboxnew";
    }
    return PAY_BASE_URL;
  }

  @Override
  public WxPayRefundResult refund(WxPayRefundRequest request) throws WxErrorException {
    this.initRequest(request);
    if (StringUtils.isBlank(request.getOpUserId())) {
      request.setOpUserId(this.getConfig().getMchId());
    }

    this.checkParameters(request);
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/secapi/pay/refund";
    String responseContent = this.postWithKey(url, request.toXML());
    WxPayRefundResult result = WxPayRefundResult.fromXML(responseContent, WxPayRefundResult.class);
    this.checkResult(result);
    return result;
  }

  @Override
  public WxPayRefundQueryResult refundQuery(String transactionId, String outTradeNo,
                                            String outRefundNo, String refundId)
    throws WxErrorException {
    if ((StringUtils.isBlank(transactionId) && StringUtils.isBlank(outTradeNo)
      && StringUtils.isBlank(outRefundNo) && StringUtils.isBlank(refundId)) ||
      (StringUtils.isNotBlank(transactionId) && StringUtils.isNotBlank(outTradeNo)
        && StringUtils.isNotBlank(outRefundNo) && StringUtils.isNotBlank(refundId))) {
      throw new IllegalArgumentException("transaction_id ， out_trade_no，out_refund_no， refund_id 必须四选一");
    }

    WxPayRefundQueryRequest request = new WxPayRefundQueryRequest();
    this.initRequest(request);
    request.setOutTradeNo(StringUtils.trimToNull(outTradeNo));
    request.setTransactionId(StringUtils.trimToNull(transactionId));
    request.setOutRefundNo(StringUtils.trimToNull(outRefundNo));
    request.setRefundId(StringUtils.trimToNull(refundId));
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/pay/refundquery";
    String responseContent = this.post(url, request.toXML());
    WxPayRefundQueryResult result = WxPayRefundQueryResult.fromXML(responseContent, WxPayRefundQueryResult.class);
    result.composeRefundRecords();
    this.checkResult(result);
    return result;
  }

  private void checkResult(WxPayBaseResult result) throws WxErrorException {
    //校验返回结果签名
    Map<String, String> map = result.toMap();
    if (result.getSign() != null && !this.checkSign(map)) {
      log.debug("校验结果签名失败，参数：{}", map);
      throw new WxErrorException(WxError.newBuilder().setErrorCode(-1).setErrorMsg("参数格式校验错误！").build());
    }

    //校验结果是否成功
    if (!"SUCCESS".equalsIgnoreCase(result.getReturnCode())
      || !"SUCCESS".equalsIgnoreCase(result.getResultCode())) {
      StringBuilder errorMsg = new StringBuilder();
      if (result.getReturnCode() != null) {
        errorMsg.append("返回代码：").append(result.getReturnCode());
      }
      if (result.getReturnMsg() != null) {
        errorMsg.append("，返回信息：").append(result.getReturnMsg());
      }
      if (result.getResultCode() != null) {
        errorMsg.append("，结果代码：").append(result.getResultCode());
      }
      if (result.getErrCode() != null) {
        errorMsg.append("，错误代码：").append(result.getErrCode());
      }
      if (result.getErrCodeDes() != null) {
        errorMsg.append("，错误详情：").append(result.getErrCodeDes());
      }

      WxError error = WxError.newBuilder()
        .setErrorCode(-1)
        .setErrorMsg(errorMsg.toString())
        .build();
      log.error("\n结果业务代码异常，返回結果：{},\n{}", map, error);
      throw new WxErrorException(error);
    }
  }

  private void checkParameters(WxPayDownloadBillRequest request) throws WxErrorException {
    BeanUtils.checkRequiredFields(request);

    if (StringUtils.isNotBlank(request.getTarType()) && !"GZIP".equals(request.getTarType())) {
      throw new IllegalArgumentException("tar_type值如果存在，只能为GZIP");
    }

    if (!ArrayUtils.contains(BILL_TYPE, request.getBillType())) {
      throw new IllegalArgumentException("bill_tpye目前必须为" + Arrays.toString(BILL_TYPE)
        + "其中之一,实际值：" + request.getBillType());
    }

  }

  private void checkParameters(WxPayRefundRequest request) throws WxErrorException {
    BeanUtils.checkRequiredFields(request);

    if (StringUtils.isNotBlank(request.getRefundAccount())) {
      if (!ArrayUtils.contains(REFUND_ACCOUNT, request.getRefundAccount())) {
        throw new IllegalArgumentException("refund_account目前必须为" + Arrays.toString(REFUND_ACCOUNT)
          + "其中之一,实际值：" + request.getRefundAccount());
      }
    }

    if (StringUtils.isBlank(request.getOutTradeNo()) && StringUtils.isBlank(request.getTransactionId())) {
      throw new IllegalArgumentException("transaction_id 和 out_trade_no 不能同时为空，必须提供一个");
    }
  }

  @Override
  public WxPayOrderNotifyResult getOrderNotifyResult(String xmlData) throws WxErrorException {
    try {
      log.debug("微信支付回调参数详细：{}", xmlData);
      WxPayOrderNotifyResult result = WxPayOrderNotifyResult.fromXML(xmlData);
      log.debug("微信支付回调结果对象：{}", result);
      this.checkResult(result);
      return result;
    } catch (WxErrorException e) {
      log.error(e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("发生异常" + e.getMessage()).build());
    }
  }

  @Override
  public WxPaySendRedpackResult sendRedpack(WxPaySendRedpackRequest request)
    throws WxErrorException {
    this.initRequest(request);
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/mmpaymkttransfers/sendredpack";
    if (request.getAmtType() != null) {
      //裂变红包
      url = this.getPayBaseUrl() + "/mmpaymkttransfers/sendgroupredpack";
    }
    String responseContent = this.postWithKey(url, request.toXML());
    WxPaySendRedpackResult result = WxPaySendRedpackResult.fromXML(responseContent, WxPaySendRedpackResult.class);
    //毋须校验，因为没有返回签名信息
    // this.checkResult(result);
    return result;
  }

  @Override
  public WxPayRedpackQueryResult queryRedpack(String mchBillNo) throws WxErrorException {
    WxPayRedpackQueryRequest request = new WxPayRedpackQueryRequest();
    request.setMchBillNo(mchBillNo);
    request.setBillType("MCHT");
    initRequest(request);
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/mmpaymkttransfers/gethbinfo";
    String responseContent = this.postWithKey(url, request.toXML());
    WxPayRedpackQueryResult result = WxPayRedpackQueryResult.fromXML(responseContent, WxPayRedpackQueryResult.class);
    this.checkResult(result);
    return result;
  }

  @Override
  public WxPayOrderQueryResult queryOrder(String transactionId, String outTradeNo) throws WxErrorException {
    if ((StringUtils.isBlank(transactionId) && StringUtils.isBlank(outTradeNo)) ||
      (StringUtils.isNotBlank(transactionId) && StringUtils.isNotBlank(outTradeNo))) {
      throw new IllegalArgumentException("transaction_id 和 out_trade_no 不能同时存在或同时为空，必须二选一");
    }

    WxPayOrderQueryRequest request = new WxPayOrderQueryRequest();
    request.setOutTradeNo(StringUtils.trimToNull(outTradeNo));
    request.setTransactionId(StringUtils.trimToNull(transactionId));
    initRequest(request);
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/pay/orderquery";
    String responseContent = this.post(url, request.toXML());
    if (StringUtils.isBlank(responseContent)) {
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("无响应结果").build());
    }

    WxPayOrderQueryResult result = WxPayOrderQueryResult.fromXML(responseContent, WxPayOrderQueryResult.class);
    result.composeCoupons();
    this.checkResult(result);
    return result;
  }

  @Override
  public WxPayOrderCloseResult closeOrder(String outTradeNo) throws WxErrorException {
    if (StringUtils.isBlank(outTradeNo)) {
      throw new IllegalArgumentException("out_trade_no不能为空");
    }

    WxPayOrderCloseRequest request = new WxPayOrderCloseRequest();
    request.setOutTradeNo(StringUtils.trimToNull(outTradeNo));
    this.initRequest(request);
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/pay/closeorder";
    String responseContent = this.post(url, request.toXML());
    WxPayOrderCloseResult result = WxPayBaseResult.fromXML(responseContent, WxPayOrderCloseResult.class);
    this.checkResult(result);

    return result;
  }

  @Override
  public WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest request)
    throws WxErrorException {
    this.initRequest(request);
    if (StringUtils.isBlank(request.getNotifyURL())) {
      request.setNotifyURL(getConfig().getNotifyUrl());
    }

    if (StringUtils.isBlank(request.getTradeType())) {
      request.setTradeType(getConfig().getTradeType());
    }

    this.checkParameters(request);//校验参数
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/pay/unifiedorder";
    String responseContent = this.post(url, request.toXML());
    WxPayUnifiedOrderResult result = WxPayUnifiedOrderResult.fromXML(responseContent, WxPayUnifiedOrderResult.class);
    this.checkResult(result);
    return result;
  }

  private void initRequest(WxPayBaseRequest request) {
    if (StringUtils.isBlank(request.getAppid())) {
      request.setAppid(this.getConfig().getAppId());
    }

    if (StringUtils.isBlank(request.getMchId())) {
      request.setMchId(this.getConfig().getMchId());
    }

    if (StringUtils.isBlank(request.getSubAppId())) {
      request.setSubAppId(this.getConfig().getSubAppId());
    }

    if (StringUtils.isBlank(request.getSubMchId())) {
      request.setSubMchId(this.getConfig().getSubMchId());
    }

    if (StringUtils.isBlank(request.getNonceStr())) {
      request.setNonceStr(String.valueOf(System.currentTimeMillis()));
    }
  }

  private void checkParameters(WxPayUnifiedOrderRequest request) throws WxErrorException {
    BeanUtils.checkRequiredFields(request);

    if (!ArrayUtils.contains(TRADE_TYPES, request.getTradeType())) {
      throw new IllegalArgumentException("trade_type目前必须为" + Arrays.toString(TRADE_TYPES) + "其中之一,实际值：" + request.getTradeType());
    }

    if ("JSAPI".equals(request.getTradeType()) && request.getOpenid() == null) {
      throw new IllegalArgumentException("当 trade_type是'JSAPI'时未指定openid");
    }

    if ("NATIVE".equals(request.getTradeType()) && request.getProductId() == null) {
      throw new IllegalArgumentException("当 trade_type是'NATIVE'时未指定product_id");
    }
  }

  @Override
  public Map<String, String> getPayInfo(WxPayUnifiedOrderRequest request) throws WxErrorException {
    WxPayUnifiedOrderResult unifiedOrderResult = this.unifiedOrder(request);
    String prepayId = unifiedOrderResult.getPrepayId();
    if (StringUtils.isBlank(prepayId)) {
      throw new RuntimeException(String.format("无法获取prepay id，错误代码： '%s'，信息：%s。",
        unifiedOrderResult.getErrCode(), unifiedOrderResult.getErrCodeDes()));
    }

    Map<String, String> payInfo = new HashMap<>();
    payInfo.put("appId", getConfig().getAppId());
    // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。
    // 但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
    payInfo.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
    payInfo.put("nonceStr", String.valueOf(System.currentTimeMillis()));
    payInfo.put("package", "prepay_id=" + prepayId);
    payInfo.put("signType", "MD5");
    if ("NATIVE".equals(request.getTradeType())) {
      payInfo.put("codeUrl", unifiedOrderResult.getCodeURL());
    }
    payInfo.put("paySign", this.createSign(payInfo));
    return payInfo;
  }

  @Override
  public WxEntPayResult entPay(WxEntPayRequest request) throws WxErrorException {
    this.initRequest(request);
    BeanUtils.checkRequiredFields(request);
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/mmpaymkttransfers/promotion/transfers";

    String responseContent = this.postWithKey(url, request.toXML());
    WxEntPayResult result = WxEntPayResult.fromXML(responseContent, WxEntPayResult.class);
    this.checkResult(result);
    return result;
  }

  @Override
  public WxEntPayQueryResult queryEntPay(String partnerTradeNo) throws WxErrorException {
    WxEntPayQueryRequest request = new WxEntPayQueryRequest();
    request.setPartnerTradeNo(partnerTradeNo);
    this.initRequest(request);
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/mmpaymkttransfers/gettransferinfo";
    String responseContent = this.postWithKey(url, request.toXML());
    WxEntPayQueryResult result = WxEntPayQueryResult.fromXML(responseContent, WxEntPayQueryResult.class);
    this.checkResult(result);
    return result;
  }

  @Override
  public byte[] createScanPayQrcodeMode1(String productId, File logoFile, Integer sideLength) {
    String content = createScanPayQrcodeMode1(productId);
    return createQrcode(content, logoFile, sideLength);
  }

  @Override
  public String createScanPayQrcodeMode1(String productId){
	  //weixin://wxpay/bizpayurl?sign=XXXXX&appid=XXXXX&mch_id=XXXXX&product_id=XXXXXX&time_stamp=XXXXXX&nonce_str=XXXXX
    StringBuilder codeUrl = new StringBuilder("weixin://wxpay/bizpayurl?");
    Map<String, String> params = Maps.newHashMap();
    params.put("appid", this.getConfig().getAppId());
    params.put("mch_id", this.getConfig().getMchId());
    params.put("product_id", productId);
    params.put("time_stamp", String.valueOf(System.currentTimeMillis() / 1000));//这里需要秒，10位数字
    params.put("nonce_str", String.valueOf(System.currentTimeMillis()));

    String sign = this.createSign(params);
    params.put("sign", sign);


    for (String key : params.keySet()) {
      codeUrl.append(key + "=" + params.get(key) + "&");
    }

    String content = codeUrl.toString().substring(0, codeUrl.length() - 1);
    log.debug("扫码支付模式一生成二维码的URL:{}",content);
    return  content;
  }

  @Override
  public byte[] createScanPayQrcodeMode2(String codeUrl, File logoFile, Integer sideLength) {
    return createQrcode(codeUrl, logoFile, sideLength);
  }

  private byte[] createQrcode(String content, File logoFile, Integer sideLength) {
	if (sideLength == null || sideLength < 1) {
      return QrcodeUtils.createQrcode(content, logoFile);
    }
    return QrcodeUtils.createQrcode(content, sideLength, logoFile);
  }

  public void report(WxPayReportRequest request) throws WxErrorException {
    BeanUtils.checkRequiredFields(request);
    this.initRequest(request);
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/payitil/report";
    String responseContent = this.post(url, request.toXML());
    WxPayCommonResult result = WxPayBaseResult.fromXML(responseContent, WxPayCommonResult.class);
    this.checkResult(result);
  }

  @Override
  public File downloadBill(String billDate, String billType, String tarType, String deviceInfo) throws WxErrorException {
    WxPayDownloadBillRequest request = new WxPayDownloadBillRequest();
    this.initRequest(request);
    request.setBillType(billType);
    request.setBillDate(billDate);
    request.setTarType(tarType);
    request.setDeviceInfo(deviceInfo);
    this.checkParameters(request);
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/pay/downloadbill";
    //TODO 返回的内容可能是文件流，也有可能是xml，需要区分对待
    String responseContent = this.post(url, request.toXML());

    WxPayCommonResult result = WxPayBaseResult.fromXML(responseContent, WxPayCommonResult.class);
    this.checkResult(result);
    //TODO 待实现，暂时无测试帐号，无法调试
    return null;
  }

  @Override
  public WxPayMicropayResult micropay(WxPayMicropayRequest request) throws WxErrorException {
    this.initRequest(request);
    BeanUtils.checkRequiredFields(request);
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/pay/micropay";
    String responseContent = this.post(url, request.toXML());
    WxPayMicropayResult result = WxPayBaseResult.fromXML(responseContent, WxPayMicropayResult.class);
    this.checkResult(result);
    return result;
  }

  private String post(String url, String xmlParam) {
    String requestString = null;
    try {
      requestString = new String(xmlParam.getBytes(CharEncoding.UTF_8), CharEncoding.ISO_8859_1);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    HttpRequest request = HttpRequest.post(url).body(requestString);
    HttpResponse response = request.send();
    String responseString = null;
    try {
      responseString = new String(response.bodyText().getBytes(CharEncoding.ISO_8859_1), CharEncoding.UTF_8);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    this.log.debug("\n[URL]: {}\n[PARAMS]: {}\n[RESPONSE]: {}", url, xmlParam, responseString);
    return responseString;
  }

  /**
   * 由于暂时未找到使用jodd-http实现证书配置的办法，故而暂时使用httpclient
   */
  private String postWithKey(String url, String requestStr) throws WxErrorException {
    try {
      SSLContext sslContext = this.getConfig().getSslContext();
      if (null == sslContext) {
        sslContext = this.getConfig().initSSLContext();
      }

      SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
        new String[]{"TLSv1"}, null, new DefaultHostnameVerifier());

      HttpPost httpPost = new HttpPost(url);

      try (CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build()) {
        httpPost.setEntity(new StringEntity(new String(requestStr.getBytes(CharEncoding.UTF_8), CharEncoding.ISO_8859_1)));
        try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
          String result = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
          this.log.debug("\n[URL]:  {}\n[PARAMS]: {}\n[RESPONSE]: {}", url, requestStr, result);
          return result;
        }
      } finally {
        httpPost.releaseConnection();
      }
    } catch (Exception e) {
      this.log.error("\n[URL]:  {}\n[PARAMS]: {}\n[EXCEPTION]: {}", url, requestStr, e.getMessage());
      throw new WxErrorException(WxError.newBuilder().setErrorCode(-1).setErrorMsg(e.getMessage()).build(), e);
    }
  }

  @Override
  public String createSign(Object xmlBean) {
    return this.createSign(BeanUtils.xmlBean2Map(xmlBean), this.getConfig().getMchKey());
  }

  @Override
  public String createSign(Object xmlBean, String signKey) {
    return this.createSign(BeanUtils.xmlBean2Map(xmlBean), signKey);
  }

  @Override
  public String createSign(Map<String, String> params) {
    return this.createSign(params, this.getConfig().getMchKey());
  }

  @Override
  public String createSign(Map<String, String> params, String signKey) {
    if (this.getConfig().useSandboxForWxPay()) {
      //使用仿真测试环境
      //TODO 目前测试发现，以下两行代码都会出问题，所以暂不建议使用仿真测试环境
      signKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456";
      //return "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456";
    }

    SortedMap<String, String> sortedMap = new TreeMap<>(params);

    StringBuilder toSign = new StringBuilder();
    for (String key : sortedMap.keySet()) {
      String value = params.get(key);
      if (StringUtils.isNotEmpty(value) && !"sign".equals(key) && !"key".equals(key)) {
        toSign.append(key + "=" + value + "&");
      }
    }

    toSign.append("key=" + signKey);
    return DigestUtils.md5Hex(toSign.toString()).toUpperCase();
  }

  @Override
  public boolean checkSign(Object xmlBean) {
    return this.checkSign(BeanUtils.xmlBean2Map(xmlBean), getConfig().getMchKey());
  }

  @Override
  public boolean checkSign(Object xmlBean, String signKey) {
    return this.checkSign(BeanUtils.xmlBean2Map(xmlBean), signKey);
  }

  @Override
  public boolean checkSign(Map<String, String> params) {
    return this.checkSign(params, getConfig().getMchKey());
  }

  @Override
  public boolean checkSign(Map<String, String> params, String signKey) {
    String sign = this.createSign(params, signKey);
    return sign.equals(params.get("sign"));
  }
}
