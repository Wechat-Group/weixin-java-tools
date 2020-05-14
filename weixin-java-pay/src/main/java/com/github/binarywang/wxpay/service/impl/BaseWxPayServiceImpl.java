package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.utils.qrcode.QrcodeUtils;
import com.github.binarywang.wxpay.bean.WxPayApiData;
import com.github.binarywang.wxpay.bean.coupon.*;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxScanPayNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayAppOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayMwebOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayNativeOrderResult;
import com.github.binarywang.wxpay.bean.request.*;
import com.github.binarywang.wxpay.bean.result.*;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.constant.WxPayConstants.SignType;
import com.github.binarywang.wxpay.constant.WxPayConstants.TradeType;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.*;
import com.github.binarywang.wxpay.util.SignUtils;
import com.github.binarywang.wxpay.util.XmlConfig;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import jodd.io.ZipUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipException;

import static com.github.binarywang.wxpay.constant.WxPayConstants.QUERY_COMMENT_DATE_FORMAT;
import static com.github.binarywang.wxpay.constant.WxPayConstants.TarType;

/**
 * <pre>
 *  微信支付接口请求抽象实现类
 * Created by Binary Wang on 2017-7-8.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public abstract class BaseWxPayServiceImpl implements WxPayService {
  private static final String TOTAL_FUND_COUNT = "资金流水总笔数";

  /**
   * The Log.
   */
  final Logger log = LoggerFactory.getLogger(this.getClass());
  /**
   * The constant wxApiData.
   */
  static ThreadLocal<WxPayApiData> wxApiData = new ThreadLocal<>();

  private EntPayService entPayService = new EntPayServiceImpl(this);
  private ProfitSharingService profitSharingService = new ProfitSharingServiceImpl(this);
  private RedpackService redpackService = new RedpackServiceImpl(this);
  private PayScoreService payScoreService = new PayScoreServiceImpl(this);

  /**
   * The Config.
   */
  protected WxPayConfig config;

  @Override
  public EntPayService getEntPayService() {
    return entPayService;
  }

  @Override
  public ProfitSharingService getProfitSharingService() {
    return profitSharingService;
  }

  @Override
  public PayScoreService getPayScoreService() {
    return payScoreService;
  }

  @Override
  public RedpackService getRedpackService() {
    return this.redpackService;
  }

  @Override
  public void setEntPayService(EntPayService entPayService) {
    this.entPayService = entPayService;
  }

  @Override
  public WxPayConfig getConfig() {
    return this.config;
  }

  @Override
  public void setConfig(WxPayConfig config) {
    this.config = config;
  }

  @Override
  public String getPayBaseUrl() {
    if (this.getConfig().isUseSandboxEnv()) {
      return this.getConfig().getPayBaseUrl() + "/sandboxnew";
    }

    return this.getConfig().getPayBaseUrl();
  }

  @Override
  public WxPayRefundResult refund(WxPayRefundRequest request) throws WxPayException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/secapi/pay/refund";
    if (this.getConfig().isUseSandboxEnv()) {
      url = this.getConfig().getPayBaseUrl() + "/sandboxnew/pay/refund";
    }

    String responseContent = this.post(url, request.toXML(), true);
    WxPayRefundResult result = BaseWxPayResult.fromXML(responseContent, WxPayRefundResult.class);
    result.composeRefundCoupons();
    result.checkResult(this, request.getSignType(), true);
    return result;
  }

  @Override
  public WxPayRefundQueryResult refundQuery(String transactionId, String outTradeNo, String outRefundNo, String refundId)
    throws WxPayException {
    WxPayRefundQueryRequest request = new WxPayRefundQueryRequest();
    request.setOutTradeNo(StringUtils.trimToNull(outTradeNo));
    request.setTransactionId(StringUtils.trimToNull(transactionId));
    request.setOutRefundNo(StringUtils.trimToNull(outRefundNo));
    request.setRefundId(StringUtils.trimToNull(refundId));

    return this.refundQuery(request);
  }

  @Override
  public WxPayRefundQueryResult refundQuery(WxPayRefundQueryRequest request) throws WxPayException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/pay/refundquery";
    String responseContent = this.post(url, request.toXML(), false);
    WxPayRefundQueryResult result = BaseWxPayResult.fromXML(responseContent, WxPayRefundQueryResult.class);
    result.composeRefundRecords();
    result.checkResult(this, request.getSignType(), true);
    return result;
  }

  @Override
  public WxPayOrderNotifyResult parseOrderNotifyResult(String xmlData) throws WxPayException {
    try {
      log.debug("微信支付异步通知请求参数：{}", xmlData);
      WxPayOrderNotifyResult result = WxPayOrderNotifyResult.fromXML(xmlData);
      log.debug("微信支付异步通知请求解析后的对象：{}", result);
      result.checkResult(this, result.getSignType(), false);
      return result;
    } catch (WxPayException e) {
      throw e;
    } catch (Exception e) {
      throw new WxPayException("发生异常！", e);
    }
  }

  @Override
  public WxPayRefundNotifyResult parseRefundNotifyResult(String xmlData) throws WxPayException {
    try {
      log.debug("微信支付退款异步通知参数：{}", xmlData);
      WxPayRefundNotifyResult result;
      if (XmlConfig.fastMode) {
        result = BaseWxPayResult.fromXML(xmlData, WxPayRefundNotifyResult.class);
        result.decryptReqInfo(this.getConfig().getMchKey());
      } else {
        result = WxPayRefundNotifyResult.fromXML(xmlData, this.getConfig().getMchKey());
      }
      log.debug("微信支付退款异步通知解析后的对象：{}", result);
      return result;
    } catch (Exception e) {
      throw new WxPayException("发生异常，" + e.getMessage(), e);
    }
  }

  @Override
  public WxScanPayNotifyResult parseScanPayNotifyResult(String xmlData) throws WxPayException {
    try {
      log.debug("扫码支付回调通知请求参数：{}", xmlData);
      WxScanPayNotifyResult result = BaseWxPayResult.fromXML(xmlData, WxScanPayNotifyResult.class);
      log.debug("扫码支付回调通知解析后的对象：{}", result);
      result.checkResult(this, this.getConfig().getSignType(), false);
      return result;
    } catch (WxPayException e) {
      throw e;
    } catch (Exception e) {
      throw new WxPayException("发生异常，" + e.getMessage(), e);
    }

  }

  @Override
  public WxPaySendMiniProgramRedpackResult sendMiniProgramRedpack(WxPaySendMiniProgramRedpackRequest request)
    throws WxPayException {
    return this.redpackService.sendMiniProgramRedpack(request);
  }

  @Override
  public WxPaySendRedpackResult sendRedpack(WxPaySendRedpackRequest request) throws WxPayException {
    return this.redpackService.sendRedpack(request);
  }

  @Override
  public WxPayRedpackQueryResult queryRedpack(String mchBillNo) throws WxPayException {
    return this.redpackService.queryRedpack(mchBillNo);
  }

  @Override
  public WxPayRedpackQueryResult queryRedpack(WxPayRedpackQueryRequest request) throws WxPayException {
    return this.redpackService.queryRedpack(request);
  }

  @Override
  public WxPayOrderQueryResult queryOrder(String transactionId, String outTradeNo) throws WxPayException {
    WxPayOrderQueryRequest request = new WxPayOrderQueryRequest();
    request.setOutTradeNo(StringUtils.trimToNull(outTradeNo));
    request.setTransactionId(StringUtils.trimToNull(transactionId));

    return this.queryOrder(request);
  }

  @Override
  public WxPayOrderQueryResult queryOrder(WxPayOrderQueryRequest request) throws WxPayException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/pay/orderquery";
    String responseContent = this.post(url, request.toXML(), false);
    if (StringUtils.isBlank(responseContent)) {
      throw new WxPayException("无响应结果");
    }

    WxPayOrderQueryResult result = BaseWxPayResult.fromXML(responseContent, WxPayOrderQueryResult.class);
    result.composeCoupons();
    result.checkResult(this, request.getSignType(), true);
    return result;
  }

  @Override
  public WxPayOrderCloseResult closeOrder(String outTradeNo) throws WxPayException {
    if (StringUtils.isBlank(outTradeNo)) {
      throw new WxPayException("out_trade_no不能为空");
    }

    WxPayOrderCloseRequest request = new WxPayOrderCloseRequest();
    request.setOutTradeNo(StringUtils.trimToNull(outTradeNo));

    return this.closeOrder(request);
  }

  @Override
  public WxPayOrderCloseResult closeOrder(WxPayOrderCloseRequest request) throws WxPayException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/pay/closeorder";
    String responseContent = this.post(url, request.toXML(), false);
    WxPayOrderCloseResult result = BaseWxPayResult.fromXML(responseContent, WxPayOrderCloseResult.class);
    result.checkResult(this, request.getSignType(), true);

    return result;
  }

  @Override
  public <T> T createOrder(WxPayUnifiedOrderRequest request) throws WxPayException {
    WxPayUnifiedOrderResult unifiedOrderResult = this.unifiedOrder(request);
    String prepayId = unifiedOrderResult.getPrepayId();
    if (StringUtils.isBlank(prepayId)) {
      throw new WxPayException(String.format("无法获取prepay id，错误代码： '%s'，信息：%s。",
        unifiedOrderResult.getErrCode(), unifiedOrderResult.getErrCodeDes()));
    }

    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String nonceStr = String.valueOf(System.currentTimeMillis());
    switch (request.getTradeType()) {
      case TradeType.MWEB: {
        return (T) new WxPayMwebOrderResult(unifiedOrderResult.getMwebUrl());
      }

      case TradeType.NATIVE: {
        return (T) new WxPayNativeOrderResult(unifiedOrderResult.getCodeURL());
      }

      case TradeType.APP: {
        // APP支付绑定的是微信开放平台上的账号，APPID为开放平台上绑定APP后发放的参数
        String appId = unifiedOrderResult.getAppid();
        if (StringUtils.isNotEmpty(unifiedOrderResult.getSubAppId())) {
          appId = unifiedOrderResult.getSubAppId();
        }

        Map<String, String> configMap = new HashMap<>(8);
        // 此map用于参与调起sdk支付的二次签名,格式全小写，timestamp只能是10位,格式固定，切勿修改
        String partnerId = unifiedOrderResult.getMchId();
        if (StringUtils.isNotEmpty(unifiedOrderResult.getSubMchId())) {
          partnerId = unifiedOrderResult.getSubMchId();
        }

        configMap.put("prepayid", prepayId);
        configMap.put("partnerid", partnerId);
        String packageValue = "Sign=WXPay";
        configMap.put("package", packageValue);
        configMap.put("timestamp", timestamp);
        configMap.put("noncestr", nonceStr);
        configMap.put("appid", appId);

        final WxPayAppOrderResult result = WxPayAppOrderResult.builder()
          .sign(SignUtils.createSign(configMap, request.getSignType(), this.getConfig().getMchKey(), null))
          .prepayId(prepayId)
          .partnerId(partnerId)
          .appId(appId)
          .packageValue(packageValue)
          .timeStamp(timestamp)
          .nonceStr(nonceStr)
          .build();
        return (T) result;
      }

      case TradeType.JSAPI: {
        String signType = request.getSignType();
        if (signType == null) {
          signType = SignType.MD5;
        }

        String appid = unifiedOrderResult.getAppid();
        if (StringUtils.isNotEmpty(unifiedOrderResult.getSubAppId())) {
          appid = unifiedOrderResult.getSubAppId();
        }

        WxPayMpOrderResult payResult = WxPayMpOrderResult.builder()
          .appId(appid)
          .timeStamp(timestamp)
          .nonceStr(nonceStr)
          .packageValue("prepay_id=" + prepayId)
          .signType(signType)
          .build();

        payResult.setPaySign(SignUtils.createSign(payResult, signType, this.getConfig().getMchKey(), null));
        return (T) payResult;
      }

      default: {
        throw new WxPayException("该交易类型暂不支持");
      }
    }

  }

  @Override
  public <T> T createOrder(TradeType.Specific<T> specificTradeType, WxPayUnifiedOrderRequest request) throws WxPayException {
    if (specificTradeType == null) {
      throw new IllegalArgumentException("specificTradeType 不能为 null");
    }
    request.setTradeType(specificTradeType.getType());
    return createOrder(request);
  }

  @Override
  public WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest request) throws WxPayException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/pay/unifiedorder";
    String responseContent = this.post(url, request.toXML(), false);
    WxPayUnifiedOrderResult result = BaseWxPayResult.fromXML(responseContent, WxPayUnifiedOrderResult.class);
    result.checkResult(this, request.getSignType(), true);
    return result;
  }

  @Override
  @Deprecated
  public Map<String, String> getPayInfo(WxPayUnifiedOrderRequest request) throws WxPayException {
    WxPayUnifiedOrderResult unifiedOrderResult = this.unifiedOrder(request);
    String prepayId = unifiedOrderResult.getPrepayId();
    if (StringUtils.isBlank(prepayId)) {
      throw new RuntimeException(String.format("无法获取prepay id，错误代码： '%s'，信息：%s。",
        unifiedOrderResult.getErrCode(), unifiedOrderResult.getErrCodeDes()));
    }

    Map<String, String> payInfo = new HashMap<>();
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String nonceStr = String.valueOf(System.currentTimeMillis());
    if (TradeType.NATIVE.equals(request.getTradeType())) {
      payInfo.put("codeUrl", unifiedOrderResult.getCodeURL());
    } else if (TradeType.APP.equals(request.getTradeType())) {
      // APP支付绑定的是微信开放平台上的账号，APPID为开放平台上绑定APP后发放的参数
      String appId = getConfig().getAppId();
      Map<String, String> configMap = new HashMap<>();
      // 此map用于参与调起sdk支付的二次签名,格式全小写，timestamp只能是10位,格式固定，切勿修改
      String partnerId = getConfig().getMchId();
      configMap.put("prepayid", prepayId);
      configMap.put("partnerid", partnerId);
      String packageValue = "Sign=WXPay";
      configMap.put("package", packageValue);
      configMap.put("timestamp", timestamp);
      configMap.put("noncestr", nonceStr);
      configMap.put("appid", appId);
      // 此map用于客户端与微信服务器交互
      payInfo.put("sign", SignUtils.createSign(configMap, request.getSignType(), this.getConfig().getMchKey(), null));
      payInfo.put("prepayId", prepayId);
      payInfo.put("partnerId", partnerId);
      payInfo.put("appId", appId);
      payInfo.put("packageValue", packageValue);
      payInfo.put("timeStamp", timestamp);
      payInfo.put("nonceStr", nonceStr);
    } else if (TradeType.JSAPI.equals(request.getTradeType())) {
      payInfo.put("appId", unifiedOrderResult.getAppid());
      // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
      payInfo.put("timeStamp", timestamp);
      payInfo.put("nonceStr", nonceStr);
      payInfo.put("package", "prepay_id=" + prepayId);
      payInfo.put("signType", request.getSignType());
      payInfo.put("paySign", SignUtils.createSign(payInfo, request.getSignType(), this.getConfig().getMchKey(), null));
    }

    return payInfo;
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
    //这里需要秒，10位数字
    params.put("time_stamp", String.valueOf(System.currentTimeMillis() / 1000));
    params.put("nonce_str", String.valueOf(System.currentTimeMillis()));

    String sign = SignUtils.createSign(params, SignType.MD5, this.getConfig().getMchKey(), null);
    params.put("sign", sign);

    for (String key : params.keySet()) {
      codeUrl.append(key).append("=").append(params.get(key)).append("&");
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

  @Override
  public void report(WxPayReportRequest request) throws WxPayException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/payitil/report";
    String responseContent = this.post(url, request.toXML(), false);
    WxPayCommonResult result = BaseWxPayResult.fromXML(responseContent, WxPayCommonResult.class);
    result.checkResult(this, request.getSignType(), true);
  }

  @Override
  public String downloadRawBill(String billDate, String billType, String tarType, String deviceInfo)
    throws WxPayException {
    return this.downloadRawBill(this.buildDownloadBillRequest(billDate, billType, tarType, deviceInfo));
  }

  @Override
  public WxPayBillResult downloadBill(String billDate, String billType, String tarType, String deviceInfo)
    throws WxPayException {
    return this.downloadBill(this.buildDownloadBillRequest(billDate, billType, tarType, deviceInfo));
  }

  private WxPayDownloadBillRequest buildDownloadBillRequest(String billDate, String billType, String tarType,
                                                            String deviceInfo) {
    WxPayDownloadBillRequest request = new WxPayDownloadBillRequest();
    request.setBillType(billType);
    request.setBillDate(billDate);
    request.setTarType(tarType);
    request.setDeviceInfo(deviceInfo);
    return request;
  }

  @Override
  public WxPayBillResult downloadBill(WxPayDownloadBillRequest request) throws WxPayException {
    String responseContent = this.downloadRawBill(request);

    if (StringUtils.isEmpty(responseContent)) {
      return null;
    }

    return this.handleBill(request.getBillType(), responseContent);
  }

  @Override
  public String downloadRawBill(WxPayDownloadBillRequest request) throws WxPayException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/pay/downloadbill";

    String responseContent;
    if (TarType.GZIP.equals(request.getTarType())) {
      responseContent = this.handleGzipBill(url, request.toXML());
    } else {
      responseContent = this.post(url, request.toXML(), false);
      if (responseContent.startsWith("<")) {
        throw WxPayException.from(BaseWxPayResult.fromXML(responseContent, WxPayCommonResult.class));
      }
    }
    return responseContent;
  }

  private WxPayBillResult handleBill(String billType, String responseContent) {
    return WxPayBillResult.fromRawBillResultString(responseContent, billType);
  }

  private String handleGzipBill(String url, String requestStr) throws WxPayException {
    try {
      byte[] responseBytes = this.postForBytes(url, requestStr, false);
      Path tempDirectory = Files.createTempDirectory("bill");
      Path path = Paths.get(tempDirectory.toString(), System.currentTimeMillis() + ".gzip");
      Files.write(path, responseBytes);
      try {
        List<String> allLines = Files.readAllLines(ZipUtil.ungzip(path.toFile()).toPath(), StandardCharsets.UTF_8);
        return Joiner.on("\n").join(allLines);
      } catch (ZipException e) {
        if (e.getMessage().contains("Not in GZIP format")) {
          throw WxPayException.from(BaseWxPayResult.fromXML(new String(responseBytes, StandardCharsets.UTF_8),
            WxPayCommonResult.class));
        } else {
          throw new WxPayException("解压zip文件出错！", e);
        }
      }
    } catch (Exception e) {
      throw new WxPayException("解析对账单文件时出错！", e);
    }
  }

  @Override
  public WxPayFundFlowResult downloadFundFlow(String billDate, String accountType, String tarType) throws WxPayException {

    WxPayDownloadFundFlowRequest request = new WxPayDownloadFundFlowRequest();
    request.setBillDate(billDate);
    request.setAccountType(accountType);
    request.setTarType(tarType);

    return this.downloadFundFlow(request);
  }

  @Override
  public WxPayFundFlowResult downloadFundFlow(WxPayDownloadFundFlowRequest request) throws WxPayException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/pay/downloadfundflow";

    String responseContent;
    if (TarType.GZIP.equals(request.getTarType())) {
      responseContent = this.handleGzipFundFlow(url, request.toXML());
    } else {
      responseContent = this.post(url, request.toXML(), true);
      if (responseContent.startsWith("<")) {
        throw WxPayException.from(BaseWxPayResult.fromXML(responseContent, WxPayCommonResult.class));
      }
    }

    return this.handleFundFlow(responseContent);
  }

  private String handleGzipFundFlow(String url, String requestStr) throws WxPayException {
    try {
      byte[] responseBytes = this.postForBytes(url, requestStr, true);
      Path tempDirectory = Files.createTempDirectory("fundFlow");
      Path path = Paths.get(tempDirectory.toString(), System.currentTimeMillis() + ".gzip");
      Files.write(path, responseBytes);

      try {
        List<String> allLines = Files.readAllLines(ZipUtil.ungzip(path.toFile()).toPath(), StandardCharsets.UTF_8);
        return Joiner.on("\n").join(allLines);
      } catch (ZipException e) {
        if (e.getMessage().contains("Not in GZIP format")) {
          throw WxPayException.from(BaseWxPayResult.fromXML(new String(responseBytes, StandardCharsets.UTF_8),
            WxPayCommonResult.class));
        } else {
          throw new WxPayException("解压zip文件出错", e);
        }
      }
    } catch (WxPayException wxPayException) {
      throw wxPayException;
    } catch (Exception e) {
      throw new WxPayException("解压zip文件出错", e);
    }
  }

  private WxPayFundFlowResult handleFundFlow(String responseContent) {
    WxPayFundFlowResult wxPayFundFlowResult = new WxPayFundFlowResult();

    String listStr = "";
    String objStr = "";

    if (StringUtils.isNotBlank(responseContent) && responseContent.contains(TOTAL_FUND_COUNT)) {
      listStr = responseContent.substring(0, responseContent.indexOf(TOTAL_FUND_COUNT));
      objStr = responseContent.substring(responseContent.indexOf(TOTAL_FUND_COUNT));
    }
    /*
     * 记账时间:2018-02-01 04:21:23 微信支付业务单号:50000305742018020103387128253 资金流水单号:1900009231201802015884652186 业务名称:退款
     * 业务类型:退款 收支类型:支出 收支金额（元）:0.02 账户结余（元）:0.17 资金变更提交申请人:system 备注:缺货 业务凭证号:REF4200000068201801293084726067
     * 参考以上格式进行取值
     */
    List<WxPayFundFlowBaseResult> wxPayFundFlowBaseResultList = new LinkedList<>();
    // 去空格
    String newStr = listStr.replaceAll(",", " ");
    // 数据分组
    String[] tempStr = newStr.split("`");
    // 分组标题
    String[] t = tempStr[0].split(" ");
    // 计算循环次数
    int j = tempStr.length / t.length;
    // 纪录数组下标
    int k = 1;
    for (int i = 0; i < j; i++) {
      WxPayFundFlowBaseResult wxPayFundFlowBaseResult = new WxPayFundFlowBaseResult();

      wxPayFundFlowBaseResult.setBillingTime(tempStr[k].trim());
      wxPayFundFlowBaseResult.setBizTransactionId(tempStr[k + 1].trim());
      wxPayFundFlowBaseResult.setFundFlowId(tempStr[k + 2].trim());
      wxPayFundFlowBaseResult.setBizName(tempStr[k + 3].trim());
      wxPayFundFlowBaseResult.setBizType(tempStr[k + 4].trim());
      wxPayFundFlowBaseResult.setFinancialType(tempStr[k + 5].trim());
      wxPayFundFlowBaseResult.setFinancialFee(tempStr[k + 6].trim());
      wxPayFundFlowBaseResult.setAccountBalance(tempStr[k + 7].trim());
      wxPayFundFlowBaseResult.setFundApplicant(tempStr[k + 8].trim());
      wxPayFundFlowBaseResult.setMemo(tempStr[k + 9].trim());
      wxPayFundFlowBaseResult.setBizVoucherId(tempStr[k + 10].trim());

      wxPayFundFlowBaseResultList.add(wxPayFundFlowBaseResult);
      k += t.length;
    }
    wxPayFundFlowResult.setWxPayFundFlowBaseResultList(wxPayFundFlowBaseResultList);

    /*
     * 资金流水总笔数,收入笔数,收入金额,支出笔数,支出金额 `20.0,`17.0,`0.35,`3.0,`0.18
     * 参考以上格式进行取值
     */
    String totalStr = objStr.replaceAll(",", " ");
    String[] totalTempStr = totalStr.split("`");
    wxPayFundFlowResult.setTotalRecord(totalTempStr[1]);
    wxPayFundFlowResult.setIncomeRecord(totalTempStr[2]);
    wxPayFundFlowResult.setIncomeAmount(totalTempStr[3]);
    wxPayFundFlowResult.setExpenditureRecord(totalTempStr[4]);
    wxPayFundFlowResult.setExpenditureAmount(totalTempStr[5]);

    return wxPayFundFlowResult;

  }

  @Override
  public WxPayMicropayResult micropay(WxPayMicropayRequest request) throws WxPayException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/pay/micropay";
    String responseContent = this.post(url, request.toXML(), false);
    WxPayMicropayResult result = BaseWxPayResult.fromXML(responseContent, WxPayMicropayResult.class);
    result.checkResult(this, request.getSignType(), true);
    return result;
  }

  @Override
  public WxPayOrderReverseResult reverseOrder(WxPayOrderReverseRequest request) throws WxPayException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/secapi/pay/reverse";
    String responseContent = this.post(url, request.toXML(), true);
    WxPayOrderReverseResult result = BaseWxPayResult.fromXML(responseContent, WxPayOrderReverseResult.class);
    result.checkResult(this, request.getSignType(), true);
    return result;
  }

  @Override
  public String shorturl(WxPayShorturlRequest request) throws WxPayException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/tools/shorturl";
    String responseContent = this.post(url, request.toXML(), false);
    WxPayShorturlResult result = BaseWxPayResult.fromXML(responseContent, WxPayShorturlResult.class);
    result.checkResult(this, request.getSignType(), true);
    return result.getShortUrl();
  }

  @Override
  public String shorturl(String longUrl) throws WxPayException {
    return this.shorturl(new WxPayShorturlRequest(longUrl));
  }

  @Override
  public String authcode2Openid(WxPayAuthcode2OpenidRequest request) throws WxPayException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/tools/authcodetoopenid";
    String responseContent = this.post(url, request.toXML(), false);
    WxPayAuthcode2OpenidResult result = BaseWxPayResult.fromXML(responseContent, WxPayAuthcode2OpenidResult.class);
    result.checkResult(this, request.getSignType(), true);
    return result.getOpenid();
  }

  @Override
  public String authcode2Openid(String authCode) throws WxPayException {
    return this.authcode2Openid(new WxPayAuthcode2OpenidRequest(authCode));
  }

  @Override
  public String getSandboxSignKey() throws WxPayException {
    WxPayDefaultRequest request = new WxPayDefaultRequest();
    request.checkAndSign(this.getConfig());

    String url = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";
    String responseContent = this.post(url, request.toXML(), false);
    WxPaySandboxSignKeyResult result = BaseWxPayResult.fromXML(responseContent, WxPaySandboxSignKeyResult.class);
    result.checkResult(this, request.getSignType(), true);
    return result.getSandboxSignKey();
  }

  @Override
  public WxPayCouponSendResult sendCoupon(WxPayCouponSendRequest request) throws WxPayException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/mmpaymkttransfers/send_coupon";
    String responseContent = this.post(url, request.toXML(), true);
    WxPayCouponSendResult result = BaseWxPayResult.fromXML(responseContent, WxPayCouponSendResult.class);
    result.checkResult(this, request.getSignType(), true);
    return result;
  }

  @Override
  public WxPayCouponStockQueryResult queryCouponStock(WxPayCouponStockQueryRequest request) throws WxPayException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/mmpaymkttransfers/query_coupon_stock";
    String responseContent = this.post(url, request.toXML(), false);
    WxPayCouponStockQueryResult result = BaseWxPayResult.fromXML(responseContent, WxPayCouponStockQueryResult.class);
    result.checkResult(this, request.getSignType(), true);
    return result;
  }

  @Override
  public WxPayCouponInfoQueryResult queryCouponInfo(WxPayCouponInfoQueryRequest request) throws WxPayException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/mmpaymkttransfers/querycouponsinfo";
    String responseContent = this.post(url, request.toXML(), false);
    WxPayCouponInfoQueryResult result = BaseWxPayResult.fromXML(responseContent, WxPayCouponInfoQueryResult.class);
    result.checkResult(this, request.getSignType(), true);
    return result;
  }

  @Override
  public WxPayApiData getWxApiData() {
    try {
      return wxApiData.get();
    } finally {
      //一般来说，接口请求会在一个线程内进行，这种情况下，每个线程get的会是之前所存入的数据，
      // 但以防万一有同一线程多次请求的问题，所以每次获取完数据后移除对应数据
      wxApiData.remove();
    }
  }

  @Override
  public String queryComment(Date beginDate, Date endDate, Integer offset, Integer limit) throws WxPayException {
    WxPayQueryCommentRequest request = new WxPayQueryCommentRequest();
    request.setBeginTime(QUERY_COMMENT_DATE_FORMAT.format(beginDate));
    request.setEndTime(QUERY_COMMENT_DATE_FORMAT.format(endDate));
    request.setOffset(offset);
    request.setLimit(limit);

    return this.queryComment(request);
  }

  @Override
  public String queryComment(WxPayQueryCommentRequest request) throws WxPayException {
    request.setSignType(SignType.HMAC_SHA256);// 签名类型，目前仅支持HMAC-SHA256，默认就是HMAC-SHA256
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/billcommentsp/batchquerycomment";
    String responseContent = this.post(url, request.toXML(), true);
    if (responseContent.startsWith("<")) {
      throw WxPayException.from(BaseWxPayResult.fromXML(responseContent, WxPayCommonResult.class));
    }

    return responseContent;
  }

  @Override
  public WxPayFaceAuthInfoResult getWxPayFaceAuthInfo(WxPayFaceAuthInfoRequest request) throws WxPayException {
    if (StringUtils.isEmpty(request.getSignType())) {
      request.setSignType(WxPayConstants.SignType.MD5);
    }

    request.checkAndSign(this.getConfig());
    String url = "https://payapp.weixin.qq.com/face/get_wxpayface_authinfo";
    String responseContent = this.post(url, request.toXML(), false);
    WxPayFaceAuthInfoResult result = BaseWxPayResult.fromXML(responseContent, WxPayFaceAuthInfoResult.class);
    result.checkResult(this, request.getSignType(), true);
    return result;
  }

  @Override
  public WxPayFacepayResult facepay(WxPayFacepayRequest request) throws WxPayException {
    request.checkAndSign(this.getConfig());

    String url = this.getPayBaseUrl() + "/pay/facepay";
    String responseContent = this.post(url, request.toXML(), false);
    WxPayFacepayResult result = BaseWxPayResult.fromXML(responseContent, WxPayFacepayResult.class);
    result.checkResult(this, request.getSignType(), true);
    return result;
  }

}
