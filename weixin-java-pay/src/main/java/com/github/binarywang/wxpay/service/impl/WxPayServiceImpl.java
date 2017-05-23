package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.utils.qrcode.QrcodeUtils;
import com.github.binarywang.wxpay.bean.request.*;
import com.github.binarywang.wxpay.bean.result.*;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.google.common.collect.Maps;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.net.SSLSocketHttpConnectionProvider;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLContext;

/**
 * Created by Binary Wang on 2016/7/28.
 *
 * @author binarywang (https://github.com/binarywang)
 */
public class WxPayServiceImpl implements WxPayService {
  private static final String PAY_BASE_URL = "https://api.mch.weixin.qq.com";
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
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/secapi/pay/refund";
    String responseContent = this.postWithKey(url, request.toXML());
    WxPayRefundResult result = WxPayBaseResult.fromXML(responseContent, WxPayRefundResult.class);
    result.checkResult(this);
    return result;
  }

  @Override
  public WxPayRefundQueryResult refundQuery(String transactionId, String outTradeNo, String outRefundNo, String refundId)
    throws WxErrorException {
    WxPayRefundQueryRequest request = new WxPayRefundQueryRequest();
    request.setOutTradeNo(StringUtils.trimToNull(outTradeNo));
    request.setTransactionId(StringUtils.trimToNull(transactionId));
    request.setOutRefundNo(StringUtils.trimToNull(outRefundNo));
    request.setRefundId(StringUtils.trimToNull(refundId));

    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/pay/refundquery";
    String responseContent = this.post(url, request.toXML());
    WxPayRefundQueryResult result = WxPayBaseResult.fromXML(responseContent, WxPayRefundQueryResult.class);
    result.composeRefundRecords();
    result.checkResult(this);
    return result;
  }

  @Override
  public WxPayOrderNotifyResult getOrderNotifyResult(String xmlData) throws WxErrorException {
    try {
      log.debug("微信支付回调参数详细：{}", xmlData);
      WxPayOrderNotifyResult result = WxPayOrderNotifyResult.fromXML(xmlData);
      log.debug("微信支付回调结果对象：{}", result);
      result.checkResult(this);
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
  public WxPaySendRedpackResult sendRedpack(WxPaySendRedpackRequest request) throws WxErrorException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/mmpaymkttransfers/sendredpack";
    if (request.getAmtType() != null) {
      //裂变红包
      url = this.getPayBaseUrl() + "/mmpaymkttransfers/sendgroupredpack";
    }

    String responseContent = this.postWithKey(url, request.toXML());
    WxPaySendRedpackResult result = WxPayBaseResult.fromXML(responseContent, WxPaySendRedpackResult.class);
    //毋须校验，因为没有返回签名信息
    // this.checkResult(result);
    return result;
  }

  @Override
  public WxPayRedpackQueryResult queryRedpack(String mchBillNo) throws WxErrorException {
    WxPayRedpackQueryRequest request = new WxPayRedpackQueryRequest();
    request.setMchBillNo(mchBillNo);
    request.setBillType("MCHT");
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/mmpaymkttransfers/gethbinfo";
    String responseContent = this.postWithKey(url, request.toXML());
    WxPayRedpackQueryResult result = WxPayBaseResult.fromXML(responseContent, WxPayRedpackQueryResult.class);
    result.checkResult(this);
    return result;
  }

  @Override
  public WxPayOrderQueryResult queryOrder(String transactionId, String outTradeNo) throws WxErrorException {
    WxPayOrderQueryRequest request = new WxPayOrderQueryRequest();
    request.setOutTradeNo(StringUtils.trimToNull(outTradeNo));
    request.setTransactionId(StringUtils.trimToNull(transactionId));
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/pay/orderquery";
    String responseContent = this.post(url, request.toXML());
    if (StringUtils.isBlank(responseContent)) {
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("无响应结果").build());
    }

    WxPayOrderQueryResult result = WxPayBaseResult.fromXML(responseContent, WxPayOrderQueryResult.class);
    result.composeCoupons();
    result.checkResult(this);
    return result;
  }

  @Override
  public WxPayOrderCloseResult closeOrder(String outTradeNo) throws WxErrorException {
    if (StringUtils.isBlank(outTradeNo)) {
      throw new IllegalArgumentException("out_trade_no不能为空");
    }

    WxPayOrderCloseRequest request = new WxPayOrderCloseRequest();
    request.setOutTradeNo(StringUtils.trimToNull(outTradeNo));
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/pay/closeorder";
    String responseContent = this.post(url, request.toXML());
    WxPayOrderCloseResult result = WxPayBaseResult.fromXML(responseContent, WxPayOrderCloseResult.class);
    result.checkResult(this);

    return result;
  }

  @Override
  public WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest request) throws WxErrorException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/pay/unifiedorder";
    String responseContent = this.post(url, request.toXML());
    WxPayUnifiedOrderResult result = WxPayBaseResult.fromXML(responseContent, WxPayUnifiedOrderResult.class);
    result.checkResult(this);
    return result;
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
    if ("NATIVE".equals(request.getTradeType())) {
      payInfo.put("codeUrl", unifiedOrderResult.getCodeURL());
    } else if ("APP".equals(request.getTradeType())) {
      // APP支付绑定的是微信开放平台上的账号，APPID为开放平台上绑定APP后发放的参数
      String appId = getConfig().getAppId();
      Map<String, String> configMap = new HashMap<>();
      // 此map用于参与调起sdk支付的二次签名,格式全小写，timestamp只能是10位,格式固定，切勿修改
      String partnerid = getConfig().getMchId();
      configMap.put("prepayid", prepayId);
      configMap.put("partnerid", partnerid);
      configMap.put("package", "Sign=WXPay");
      configMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
      configMap.put("noncestr", String.valueOf(System.currentTimeMillis()));
      configMap.put("appid", appId);
      // 此map用于客户端与微信服务器交互
      payInfo.put("sign", SignUtils.createSign(configMap, this.getConfig().getMchKey()));
      payInfo.put("prepayId", prepayId);
      payInfo.put("partnerId", partnerid);
      payInfo.put("appId", appId);
      payInfo.put("packageValue", "Sign=WXPay");
      payInfo.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
      payInfo.put("nonceStr", String.valueOf(System.currentTimeMillis()));
    } else if ("JSAPI".equals(request.getTradeType())) {
      payInfo.put("appId", unifiedOrderResult.getAppid());
      // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
      payInfo.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
      payInfo.put("nonceStr", String.valueOf(System.currentTimeMillis()));
      payInfo.put("package", "prepay_id=" + prepayId);
      payInfo.put("signType", "MD5");
      payInfo.put("paySign", SignUtils.createSign(payInfo, this.getConfig().getMchKey()));
    }
    return payInfo;
  }

  @Override
  public WxEntPayResult entPay(WxEntPayRequest request) throws WxErrorException {
    request.checkAndSign(this.getConfig());
    String url = this.getPayBaseUrl() + "/mmpaymkttransfers/promotion/transfers";

    String responseContent = this.postWithKey(url, request.toXML());
    WxEntPayResult result = WxPayBaseResult.fromXML(responseContent, WxEntPayResult.class);
    result.checkResult(this);
    return result;
  }

  @Override
  public WxEntPayQueryResult queryEntPay(String partnerTradeNo) throws WxErrorException {
    WxEntPayQueryRequest request = new WxEntPayQueryRequest();
    request.setPartnerTradeNo(partnerTradeNo);
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/mmpaymkttransfers/gettransferinfo";
    String responseContent = this.postWithKey(url, request.toXML());
    WxEntPayQueryResult result = WxPayBaseResult.fromXML(responseContent, WxEntPayQueryResult.class);
    result.checkResult(this);
    return result;
  }

  @Override
  public byte[] createScanPayQrcodeMode1(String productId, File logoFile, Integer sideLength) {
    String content = this.createScanPayQrcodeMode1(productId);
    return this.createQrcode(content, logoFile, sideLength);
  }

  @Override
  public String createScanPayQrcodeMode1(String productId) {
    //weixin://wxpay/bizpayurl?sign=XXXXX&appid=XXXXX&mch_id=XXXXX&product_id=XXXXXX&time_stamp=XXXXXX&nonce_str=XXXXX
    StringBuilder codeUrl = new StringBuilder("weixin://wxpay/bizpayurl?");
    Map<String, String> params = Maps.newHashMap();
    params.put("appid", this.getConfig().getAppId());
    params.put("mch_id", this.getConfig().getMchId());
    params.put("product_id", productId);
    params.put("time_stamp", String.valueOf(System.currentTimeMillis() / 1000));//这里需要秒，10位数字
    params.put("nonce_str", String.valueOf(System.currentTimeMillis()));

    String sign = SignUtils.createSign(params, this.getConfig().getMchKey());
    params.put("sign", sign);


    for (String key : params.keySet()) {
      codeUrl.append(key + "=" + params.get(key) + "&");
    }

    String content = codeUrl.toString().substring(0, codeUrl.length() - 1);
    log.debug("扫码支付模式一生成二维码的URL:{}", content);
    return content;
  }

  @Override
  public byte[] createScanPayQrcodeMode2(String codeUrl, File logoFile, Integer sideLength) {
    return this.createQrcode(codeUrl, logoFile, sideLength);
  }

  private byte[] createQrcode(String content, File logoFile, Integer sideLength) {
    if (sideLength == null || sideLength < 1) {
      return QrcodeUtils.createQrcode(content, logoFile);
    }

    return QrcodeUtils.createQrcode(content, sideLength, logoFile);
  }

  public void report(WxPayReportRequest request) throws WxErrorException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/payitil/report";
    String responseContent = this.post(url, request.toXML());
    WxPayCommonResult result = WxPayBaseResult.fromXML(responseContent, WxPayCommonResult.class);
    result.checkResult(this);
  }

  @Override
  public WxPayBillResult downloadBill(String billDate, String billType, String tarType, String deviceInfo) throws WxErrorException {
    WxPayDownloadBillRequest request = new WxPayDownloadBillRequest();
    request.setBillType(billType);
    request.setBillDate(billDate);
    request.setTarType(tarType);
    request.setDeviceInfo(deviceInfo);

    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/pay/downloadbill";
    //TODO 返回的内容可能是文件流，也有可能是xml，需要区分对待
    String responseContent = this.post(url, request.toXML());
    if (responseContent.startsWith("<")){
      WxPayCommonResult result = WxPayBaseResult.fromXML(responseContent, WxPayCommonResult.class);
      result.checkResult(this);
      return null;
    }else{
      WxPayBillResult wxPayBillResult = billInformationDeal(responseContent);
      return wxPayBillResult;
    }
  }
  
    private WxPayBillResult billInformationDeal(String responseContent){
    WxPayBillResult wxPayBillResult = new WxPayBillResult();

    String listStr = "";
    String objStr = "";
    if (responseContent.indexOf("总交易单数") >= 0){
      listStr = responseContent.substring(0, responseContent.indexOf("总交易单数"));
      objStr = responseContent.substring(responseContent.indexOf("总交易单数"));
    }

    /*
     * 交易时间:2017-04-06 01:00:02 公众账号ID: 商户号: 子商户号:0 设备号:WEB 微信订单号: 商户订单号:2017040519091071873216 用户标识: 交易类型:NATIVE
     * 交易状态:REFUND 付款银行:CFT 货币种类:CNY 总金额:0.00 企业红包金额:0.00 微信退款单号: 商户退款单号:20170406010000933 退款金额:0.01 企业红包退款金额:0.00
     * 退款类型:ORIGINAL 退款状态:SUCCESS 商品名称: 商户数据包: 手续费:0.00000 费率 :0.60%
     */

    // 参考以上格式进行取值

    List<WxPayBillBaseResult> wxPayBillBaseResultLst = new LinkedList<WxPayBillBaseResult>();
    String newStr = listStr.replaceAll(",", " "); // 去空格
    String[] tempStr = newStr.split("`"); // 数据分组
    String[] t = tempStr[0].split(" ");// 分组标题
    int j = tempStr.length / t.length; // 计算循环次数
    int k = 1; // 纪录数组下标
    for (int i = 0; i < j; i++){
      WxPayBillBaseResult wxPayBillBaseResult = new WxPayBillBaseResult();

      wxPayBillBaseResult.setTradeTime(tempStr[k]);
      wxPayBillBaseResult.setAppId(tempStr[k + 1]);
      wxPayBillBaseResult.setMchId(tempStr[k + 2]);
      wxPayBillBaseResult.setSubMchId(tempStr[k + 3]);
      wxPayBillBaseResult.setDeviceInfo(tempStr[k + 4]);
      wxPayBillBaseResult.setTransationId(tempStr[k + 5]);
      wxPayBillBaseResult.setOutTradeNo(tempStr[k + 6]);
      wxPayBillBaseResult.setOpenId(tempStr[k + 7]);
      wxPayBillBaseResult.setTradeType(tempStr[k + 8]);
      wxPayBillBaseResult.setTradeState(tempStr[k + 9]);
      wxPayBillBaseResult.setBankType(tempStr[k + 10]);
      wxPayBillBaseResult.setFeeType(tempStr[k + 11]);
      wxPayBillBaseResult.setTotalFee(tempStr[k + 12]);
      wxPayBillBaseResult.setCouponFee(tempStr[k + 13]);
      wxPayBillBaseResult.setRefundId(tempStr[k + 14]);
      wxPayBillBaseResult.setOutRefundNo(tempStr[k + 15]);
      wxPayBillBaseResult.setSettlementRefundFee(tempStr[k + 16]);
      wxPayBillBaseResult.setCouponRefundFee(tempStr[k + 17]);
      wxPayBillBaseResult.setRefundChannel(tempStr[k + 18]);
      wxPayBillBaseResult.setRefundState(tempStr[k + 19]);
      wxPayBillBaseResult.setBody(tempStr[k + 20]);
      wxPayBillBaseResult.setAttach(tempStr[k + 21]);
      wxPayBillBaseResult.setPoundage(tempStr[k + 22]);
      wxPayBillBaseResult.setPoundageRate(tempStr[k + 23]);
      wxPayBillBaseResultLst.add(wxPayBillBaseResult);
      k += t.length;
    }
    /*
     * 总交易单数,总交易额,总退款金额,总代金券或立减优惠退款金额,手续费总金额 `2,`0.02,`0.0,`0.0,`0
     */

    // 参考以上格式进行取值

    String totalStr = objStr.replaceAll(",", " ");
    String[] totalTempStr = totalStr.split("`");
    wxPayBillResult.setTotalRecord(totalTempStr[1]);
    wxPayBillResult.setTotalFee(totalTempStr[2]);
    wxPayBillResult.setTotalRefundFee(totalTempStr[3]);
    wxPayBillResult.setTotalCouponFee(totalTempStr[4]);
    wxPayBillResult.setTotalPoundageFee(totalTempStr[5]);
    
    return wxPayBillResult;
  }

  @Override
  public WxPayMicropayResult micropay(WxPayMicropayRequest request) throws WxErrorException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/pay/micropay";
    String responseContent = this.post(url, request.toXML());
    WxPayMicropayResult result = WxPayBaseResult.fromXML(responseContent, WxPayMicropayResult.class);
    result.checkResult(this);
    return result;
  }

  @Override
  public WxPayOrderReverseResult reverseOrder(WxPayOrderReverseRequest request) throws WxErrorException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/secapi/pay/reverse";
    String responseContent = this.postWithKey(url, request.toXML());
    WxPayOrderReverseResult result = WxPayBaseResult.fromXML(responseContent, WxPayOrderReverseResult.class);
    result.checkResult(this);
    return result;
  }

  @Override
  public String shorturl(WxPayShorturlRequest request) throws WxErrorException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/tools/shorturl";
    String responseContent = this.post(url, request.toXML());
    WxPayShorturlResult result = WxPayBaseResult.fromXML(responseContent, WxPayShorturlResult.class);
    result.checkResult(this);
    return result.getShortUrl();
  }

  @Override
  public String shorturl(String longUrl) throws WxErrorException {
    return this.shorturl(new WxPayShorturlRequest(longUrl));
  }

  @Override
  public String authcode2Openid(WxPayAuthcode2OpenidRequest request) throws WxErrorException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/tools/authcodetoopenid";
    String responseContent = this.post(url, request.toXML());
    WxPayAuthcode2OpenidResult result = WxPayBaseResult.fromXML(responseContent, WxPayAuthcode2OpenidResult.class);
    result.checkResult(this);
    return result.getOpenid();
  }

  @Override
  public String authcode2Openid(String authCode) throws WxErrorException {
    return this.authcode2Openid(new WxPayAuthcode2OpenidRequest(authCode));
  }

  private String post(String url, String xmlParam) {
    String requestString = xmlParam;
    try {
      requestString = new String(xmlParam.getBytes(CharEncoding.UTF_8), CharEncoding.ISO_8859_1);
    } catch (UnsupportedEncodingException e) {
      //实际上不会发生该异常
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
   * ecoolper(20170418)，修改为jodd-http方式
   */
  private String postWithKey(String url, String requestStr) throws WxErrorException {
    try {
      SSLContext sslContext = this.getConfig().getSslContext();
      if (null == sslContext) {
        sslContext = this.getConfig().initSSLContext();
      }

      HttpRequest request = HttpRequest.post(url).withConnectionProvider(new SSLSocketHttpConnectionProvider(sslContext));
      request.bodyText(requestStr);
      HttpResponse response = request.send();
      String result = response.bodyText();
      this.log.debug("\n[URL]:  {}\n[PARAMS]: {}\n[RESPONSE]: {}", url, requestStr, result);
      return result;
    } catch (Exception e) {
      this.log.error("\n[URL]:  {}\n[PARAMS]: {}\n[EXCEPTION]: {}", url, requestStr, e.getMessage());
      throw new WxErrorException(WxError.newBuilder().setErrorCode(-1).setErrorMsg(e.getMessage()).build(), e);
    }
  }

}
