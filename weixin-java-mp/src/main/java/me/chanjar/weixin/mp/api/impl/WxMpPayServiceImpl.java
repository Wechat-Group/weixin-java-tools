package me.chanjar.weixin.mp.api.impl;

import com.github.binarywang.utils.qrcode.QrcodeUtils;
import com.google.common.collect.Maps;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.BeanUtils;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpPayService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.pay.request.*;
import me.chanjar.weixin.mp.bean.pay.result.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.client.config.RequestConfig;
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
import java.io.IOException;
import java.util.*;

/**
 * Created by Binary Wang on 2016/7/28.
 *
 * @author binarywang (https://github.com/binarywang)
 */
public class WxMpPayServiceImpl implements WxMpPayService {

  private static final String PAY_BASE_URL = "https://api.mch.weixin.qq.com";
  private static final String[] TRADE_TYPES = new String[]{"JSAPI", "NATIVE", "APP"};
  private static final String[] REFUND_ACCOUNT = new String[]{"REFUND_SOURCE_RECHARGE_FUNDS", "REFUND_SOURCE_UNSETTLED_FUNDS"};
  private static final String[] BILL_TYPE = new String[]{"ALL","REFUND","SUCCESS"};;
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private WxMpService wxMpService;

  public WxMpPayServiceImpl(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  private WxMpConfigStorage getConfig() {
    return wxMpService.getWxMpConfigStorage();
  }

  private String getPayBaseUrl(){
    if(this.getConfig().useSandboxForWxPay()){
      return PAY_BASE_URL + "/sandboxnew";
    }

    return PAY_BASE_URL;
  }

  @Override
  public WxPayRefundResult refund(WxPayRefundRequest request) throws WxErrorException {
    this.initRequest(request);
    if (StringUtils.isBlank(request.getOpUserId())) {
      request.setOpUserId(this.getConfig().getPartnerId());
    }
    this.checkParameters(request);
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/secapi/pay/refund";
    String responseContent = this.executeWithKey(url, request.toXML());
    WxPayRefundResult result = WxPayRefundResult.fromXML(responseContent, WxPayRefundResult.class);
    this.checkResult(result);
    return result;
  }

  @Override
  public WxPayRefundQueryResult refundQuery(String transactionId, String outTradeNo, String outRefundNo, String refundId) throws WxErrorException {
    if ((StringUtils.isBlank(transactionId) && StringUtils.isBlank(outTradeNo) && StringUtils.isBlank(outRefundNo) && StringUtils.isBlank(refundId)) ||
      (StringUtils.isNotBlank(transactionId) && StringUtils.isNotBlank(outTradeNo) && StringUtils.isNotBlank(outRefundNo) && StringUtils.isNotBlank(refundId))) {
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
    String responseContent = this.executeRequest(url, request.toXML());
    WxPayRefundQueryResult result = WxPayRefundQueryResult.fromXML(responseContent, WxPayRefundQueryResult.class);
    result.composeRefundRecords();
    this.checkResult(result);
    return result;
  }


  private void checkResult(WxPayBaseResult result) throws WxErrorException {
    //校验返回结果签名
    Map<String, String> map = result.toMap();
    if (result.getSign() != null &&!this.checkSign(map)) {
      log.debug("校验结果签名失败，参数：{}", map);
      throw new WxErrorException(WxError.newBuilder().setErrorCode(-1).setErrorMsg("参数格式校验错误！").build());
    }

    //校验结果是否成功
    if (!"SUCCESS".equalsIgnoreCase(result.getReturnCode())
      || !"SUCCESS".equalsIgnoreCase(result.getResultCode())) {
      WxError error = WxError.newBuilder().setErrorCode(-1)
        .setErrorMsg("返回代码: " + result.getReturnCode() + ", 返回信息: "
          + result.getReturnMsg() + ", 结果代码: " + result.getResultCode() + ", 错误代码: "
          + result.getErrCode() + ", 错误详情: " + result.getErrCodeDes())
        .build();
      log.error("结果业务代码异常，参数：{},详细：{}", map, error);
      throw new WxErrorException(error);
    }
  }

  private void checkParameters(WxPayDownloadBillRequest request) throws WxErrorException {
    BeanUtils.checkRequiredFields(request);

    if (StringUtils.isNotBlank(request.getTarType()) && !"GZIP".equals(request.getTarType())) {
      throw new IllegalArgumentException("tar_type值如果存在，只能为GZIP");
    }

    if ( !ArrayUtils.contains(BILL_TYPE, request.getBillType())) {
        throw new IllegalArgumentException("bill_tpye目前必须为" + Arrays.toString(BILL_TYPE) + "其中之一,实际值：" + request.getBillType());
    }

  }

  private void checkParameters(WxPayRefundRequest request) throws WxErrorException {
    BeanUtils.checkRequiredFields(request);

    if (StringUtils.isNotBlank(request.getRefundAccount())) {
      if (!ArrayUtils.contains(REFUND_ACCOUNT, request.getRefundAccount())) {
        throw new IllegalArgumentException("refund_account目前必须为" + Arrays.toString(REFUND_ACCOUNT) + "其中之一,实际值：" + request.getRefundAccount());
      }
    }

    if (StringUtils.isBlank(request.getOutTradeNo()) && StringUtils.isBlank(request.getTransactionId())) {
      throw new IllegalArgumentException("transaction_id 和 out_trade_no 不能同时为空，必须提供一个");
    }
  }

  @Override
  public WxPayOrderNotifyResult getOrderNotifyResult(String xmlData) throws WxErrorException {
    try {
      log.trace("微信支付回调参数详细：{}", xmlData);
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
    String responseContent = this.executeWithKey(url, request.toXML());
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
    String responseContent = this.executeWithKey(url, request.toXML());
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
    String responseContent = this.executeRequest(url, request.toXML());
    WxPayOrderQueryResult result = WxPayOrderQueryResult.fromXML(responseContent, WxPayOrderQueryResult.class);
    result.composeCoupons();
    this.checkResult(result);
    return result;
  }

  @Override
  public WxPayOrderCloseResult closeOrder(String outTradeNo) throws WxErrorException {
    if (StringUtils.isBlank(outTradeNo)) {
      throw new IllegalArgumentException("out_trade_no 不能为空");
    }

    WxPayOrderCloseRequest request = new WxPayOrderCloseRequest();
    request.setOutTradeNo(StringUtils.trimToNull(outTradeNo));
    initRequest(request);
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/pay/closeorder";
    String responseContent = this.executeRequest(url, request.toXML());
    WxPayOrderCloseResult result = WxPayOrderCloseResult.fromXML(responseContent, WxPayOrderCloseResult.class);
    this.checkResult(result);

    return result;
  }

  @Override
  public WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest request)
    throws WxErrorException {

    this.initRequest(request);
    if (StringUtils.isBlank(request.getNotifyURL())) {
      request.setNotifyURL(getConfig().getNotifyURL());
    }
    if (StringUtils.isBlank(request.getTradeType())) {
      request.setTradeType(getConfig().getTradeType());
    }
    checkParameters(request);//校验参数
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/pay/unifiedorder";
    String xmlParam = request.toXML();
    log.debug("微信统一下单接口，URL:{},参数：{}", url, xmlParam);

    String responseContent = this.executeRequest(url, xmlParam);
    log.debug("微信统一下单接口，URL:{},结果：{}", url, responseContent);
    WxPayUnifiedOrderResult result = WxPayUnifiedOrderResult.fromXML(responseContent, WxPayUnifiedOrderResult.class);
    this.checkResult(result);
    return result;
  }

  private void initRequest(WxPayBaseRequest request) {
    if (StringUtils.isBlank(request.getAppid())) {
      request.setAppid(getConfig().getAppId());
    }

    if (StringUtils.isBlank(request.getMchId())) {
      request.setMchId(getConfig().getPartnerId());
    }

    if (StringUtils.isBlank(request.getSubAppId())) {
      request.setAppid(getConfig().getSubAppId());
    }

    if (StringUtils.isBlank(request.getSubMchId())) {
      request.setMchId(getConfig().getSubMchId());
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
      throw new RuntimeException(String.format("Failed to get prepay id due to error code '%s'(%s).",
        unifiedOrderResult.getErrCode(), unifiedOrderResult.getErrCodeDes()));
    }

    Map<String, String> payInfo = new HashMap<>();
    payInfo.put("appId", getConfig().getAppId());
    // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
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

    String responseContent = this.executeWithKey(url, request.toXML());
    WxEntPayResult result = WxEntPayResult.fromXML(responseContent, WxEntPayResult.class);
    this.checkResult(result);
    return result;
  }

  @Override
  public WxEntPayQueryResult queryEntPay(String partnerTradeNo) throws WxErrorException {
    WxEntPayQueryRequest request = new WxEntPayQueryRequest();
    this.initRequest(request);
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/mmpaymkttransfers/gettransferinfo";
    String responseContent = this.executeWithKey(url, request.toXML());
    WxEntPayQueryResult result = WxEntPayQueryResult.fromXML(responseContent, WxEntPayQueryResult.class);
    this.checkResult(result);
    return result;
  }

  @Override
  public byte[] createScanPayQrcodeMode1(String productId, File logoFile, Integer sideLength) {
    //weixin://wxpay/bizpayurl?sign=XXXXX&appid=XXXXX&mch_id=XXXXX&product_id=XXXXXX&time_stamp=XXXXXX&nonce_str=XXXXX
    StringBuilder codeUrl = new StringBuilder("weixin://wxpay/bizpayurl?");
    Map<String, String> params = Maps.newHashMap();
    params.put("appid", this.getConfig().getAppId());
    params.put("mch_id", this.getConfig().getPartnerId());
    params.put("product_id", productId);
    params.put("time_stamp", String.valueOf(System.currentTimeMillis()));
    params.put("nonce_str", String.valueOf(System.currentTimeMillis()));

    String sign = this.createSign(params);
    params.put("sign", sign);

    for (String key : params.keySet()) {
      codeUrl.append(key + "=" + params.get(key) + "&");
    }

    String content = codeUrl.toString().substring(0, codeUrl.length() - 1);
    if (sideLength == null || sideLength < 1) {
      return QrcodeUtils.createQrcode(content, logoFile);
    }

    return QrcodeUtils.createQrcode(content, sideLength, logoFile);
  }

  @Override
  public byte[] createScanPayQrcodeMode2(String codeUrl, File logoFile, Integer sideLength) {
    if (sideLength == null || sideLength < 1) {
      return QrcodeUtils.createQrcode(codeUrl, logoFile);
    }

    return QrcodeUtils.createQrcode(codeUrl, sideLength, logoFile);
  }

  public void report(WxPayReportRequest request) throws WxErrorException {
    BeanUtils.checkRequiredFields(request);
    this.initRequest(request);
    request.setSign(this.createSign(request));

    String url = this.getPayBaseUrl() + "/payitil/report";
    String responseContent = this.wxMpService.post(url, request.toXML());
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
    String responseContent = this.wxMpService.post(url, request.toXML());

    WxPayCommonResult result = WxPayBaseResult.fromXML(responseContent, WxPayCommonResult.class);
    this.checkResult(result);
    //TODO 待实现，暂时无测试帐号，无法调试
    return null;
  }

  private String executeRequest(String url, String requestStr) throws WxErrorException {
    HttpPost httpPost = new HttpPost(url);
    if (this.wxMpService.getHttpProxy() != null) {
      httpPost.setConfig(RequestConfig.custom().setProxy(this.wxMpService.getHttpProxy()).build());
    }

    try (CloseableHttpClient httpclient = HttpClients.custom().build()) {
      httpPost.setEntity(new StringEntity(new String(requestStr.getBytes("UTF-8"), "ISO-8859-1")));

      try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
        String result = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
        this.log.debug("\n[URL]:  {}\n[PARAMS]: {}\n[RESPONSE]: {}", url, requestStr, result);
        return result;
      }
    } catch (IOException e) {
      this.log.error("\n[URL]:  {}\n[PARAMS]: {}\n[EXCEPTION]: {}", url, requestStr, e.getMessage());
      throw new WxErrorException(WxError.newBuilder().setErrorCode(-1).setErrorMsg(e.getMessage()).build(), e);
    } finally {
      httpPost.releaseConnection();
    }
  }

  private String executeWithKey(String url, String requestStr) throws WxErrorException {
    try {
      SSLContext sslContext = getConfig().getSslContext();
      if (null == sslContext) {
        throw new IllegalArgumentException("请先初始化配置类（即WxMpConfigStorage的实现类）中的SSLContext！");
      }

      SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null,
        new DefaultHostnameVerifier());

      HttpPost httpPost = new HttpPost(url);
      if (this.wxMpService.getHttpProxy() != null) {
        httpPost.setConfig(RequestConfig.custom().setProxy(this.wxMpService.getHttpProxy()).build());
      }

      try (CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build()) {
        httpPost.setEntity(new StringEntity(new String(requestStr.getBytes("UTF-8"), "ISO-8859-1")));
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
    return this.createSign(BeanUtils.xmlBean2Map(xmlBean), getConfig().getPartnerKey());
  }

  @Override
  public String createSign(Object xmlBean, String signKey) {
    return this.createSign(BeanUtils.xmlBean2Map(xmlBean), signKey);
  }

  @Override
  public String createSign(Map<String, String> params) {
    return this.createSign(params, this.getConfig().getPartnerKey());
  }

  @Override
  public String createSign(Map<String, String> params, String signKey) {
    if(this.getConfig().useSandboxForWxPay()){
      //使用仿真测试环境
      //TODO 目前测试发现，以下两行代码都会出问题，所以暂不建议使用这个仿真测试环境
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
    return this.checkSign(BeanUtils.xmlBean2Map(xmlBean), getConfig().getPartnerKey());
  }

  @Override
  public boolean checkSign(Object xmlBean, String signKey) {
    return this.checkSign(BeanUtils.xmlBean2Map(xmlBean), signKey);
  }

  @Override
  public boolean checkSign(Map<String, String> params) {
    return this.checkSign(params, getConfig().getPartnerKey());
  }

  @Override
  public boolean checkSign(Map<String, String> params, String signKey) {
    String sign = this.createSign(params, signKey);
    return sign.equals(params.get("sign"));
  }
}
