package com.github.binarywang.wxpay.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import com.github.binarywang.utils.qrcode.QrcodeUtils;
import com.github.binarywang.wxpay.bean.coupon.WxPayCouponInfoQueryRequest;
import com.github.binarywang.wxpay.bean.coupon.WxPayCouponInfoQueryResult;
import com.github.binarywang.wxpay.bean.coupon.WxPayCouponSendRequest;
import com.github.binarywang.wxpay.bean.coupon.WxPayCouponSendResult;
import com.github.binarywang.wxpay.bean.coupon.WxPayCouponStockQueryRequest;
import com.github.binarywang.wxpay.bean.coupon.WxPayCouponStockQueryResult;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResultTest;
import com.github.binarywang.wxpay.bean.order.WxPayAppOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayNativeOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayAuthcode2OpenidRequest;
import com.github.binarywang.wxpay.bean.request.WxPayMicropayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayOrderReverseRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayReportRequest;
import com.github.binarywang.wxpay.bean.request.WxPaySendRedpackRequest;
import com.github.binarywang.wxpay.bean.request.WxPayShorturlRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayBillResult;
import com.github.binarywang.wxpay.bean.result.WxPayFundFlowResult;
import com.github.binarywang.wxpay.bean.result.WxPayMicropayResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderReverseResult;
import com.github.binarywang.wxpay.bean.result.WxPayRedpackQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.constant.WxPayConstants.AccountType;
import com.github.binarywang.wxpay.constant.WxPayConstants.BillType;
import com.github.binarywang.wxpay.constant.WxPayConstants.SignType;
import com.github.binarywang.wxpay.constant.WxPayConstants.TradeType;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.github.binarywang.wxpay.testbase.XmlWxPayConfig;
import com.google.inject.Inject;

import static com.github.binarywang.wxpay.constant.WxPayConstants.TarType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

/**
 * 测试支付相关接口
 * Created by Binary Wang on 2016/7/28.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class BaseWxPayServiceImplTest {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Inject
  private WxPayService payService;

  /**
   * Test method for {@link WxPayService#unifiedOrder(WxPayUnifiedOrderRequest)}.
   */
  @Test
  public void testUnifiedOrder() throws WxPayException {
    WxPayUnifiedOrderRequest request = WxPayUnifiedOrderRequest.newBuilder()
      .body("我去")
      .totalFee(1)
      .spbillCreateIp("11.1.11.1")
      .notifyUrl("111111")
      .tradeType(TradeType.JSAPI)
      .openid(((XmlWxPayConfig) this.payService.getConfig()).getOpenid())
      .outTradeNo("1111112")
      .build();
    request.setSignType(SignType.HMAC_SHA256);
    WxPayUnifiedOrderResult result = this.payService.unifiedOrder(request);
    this.logger.info(result.toString());
    this.logger.warn(this.payService.getWxApiData().toString());
  }

  @Test
  public void testCreateOrder() throws Exception {
    //see other tests with method name starting with 'testCreateOrder_'
  }

  @Test
  public void testCreateOrder_jsapi() throws Exception {
    WxPayMpOrderResult result = this.payService
      .createOrder(WxPayUnifiedOrderRequest.newBuilder()
        .body("我去")
        .totalFee(1)
        .spbillCreateIp("11.1.11.1")
        .notifyUrl("111111")
        .tradeType(TradeType.JSAPI)
        .openid(((XmlWxPayConfig) this.payService.getConfig()).getOpenid())
        .outTradeNo("1111112")
        .build());
    this.logger.info(result.toString());
    this.logger.warn(this.payService.getWxApiData().toString());
  }

  @Test
  public void testCreateOrder_app() throws Exception {
    WxPayAppOrderResult result = this.payService
      .createOrder(WxPayUnifiedOrderRequest.newBuilder()
        .body("我去")
        .totalFee(1)
        .spbillCreateIp("11.1.11.1")
        .notifyUrl("111111")
        .tradeType(TradeType.APP)
        .outTradeNo("1111112")
        .build());
    this.logger.info(result.toString());
    this.logger.warn(this.payService.getWxApiData().toString());
  }

  @Test
  public void testCreateOrder_native() throws Exception {
    WxPayNativeOrderResult result = this.payService
      .createOrder(WxPayUnifiedOrderRequest.newBuilder()
        .body("我去")
        .totalFee(1)
        .productId("aaa")
        .spbillCreateIp("11.1.11.1")
        .notifyUrl("111111")
        .tradeType(TradeType.NATIVE)
        .outTradeNo("111111290")
        .build());
    this.logger.info(result.toString());
    this.logger.warn(this.payService.getWxApiData().toString());
  }

  @Test
  public void testGetPayInfo() throws Exception {
    //please use createOrder instead
  }

  /**
   * Test method for {@link WxPayService#queryOrder(String, String)} .
   */
  @Test
  public void testQueryOrder() throws WxPayException {
    this.logger.info(this.payService.queryOrder("11212121", null).toString());
    this.logger.info(this.payService.queryOrder(null, "11111").toString());
  }

  /**
   * Test method for {@link WxPayService#closeOrder(String)} .
   */
  @Test
  public void testCloseOrder() throws WxPayException {
    this.logger.info(this.payService.closeOrder("11212121").toString());
  }

  @DataProvider
  public Object[][] billingData() {
    return new Object[][]{
      {"20170831", BillType.ALL, TarType.GZIP, "deviceInfo"},
      {"20170831", BillType.RECHARGE_REFUND, TarType.GZIP, "deviceInfo"},
      {"20170831", BillType.REFUND, TarType.GZIP, "deviceInfo"},
      {"20170831", BillType.SUCCESS, TarType.GZIP, "deviceInfo"},
      {"20170831", BillType.ALL, null, "deviceInfo"},
      {"20170831", BillType.RECHARGE_REFUND, null, "deviceInfo"},
      {"20170831", BillType.REFUND, null, "deviceInfo"},
      {"20170831", BillType.SUCCESS, null, "deviceInfo"}
    };
  }

  @Test(dataProvider = "billingData")
  public void testDownloadBill(String billDate, String billType,
                               String tarType, String deviceInfo) throws Exception {
    WxPayBillResult billResult = this.payService.downloadBill(billDate, billType, tarType, deviceInfo);
    assertThat(billResult).isNotNull();
    this.logger.info(billResult.toString());
  }

  @Test(expectedExceptions = WxPayException.class)
  public void testDownloadBill_withNoParams() throws Exception {
    //必填字段为空时，抛出异常
    this.payService.downloadBill("", "", "", null);
  }

  @DataProvider
  public Object[][] fundFlowData() {
    return new Object[][]{
      {"20180819", AccountType.BASIC, TarType.GZIP},
      {"20180819", AccountType.OPERATION, TarType.GZIP},
      {"20180819", AccountType.FEES, TarType.GZIP},
      {"20180819", AccountType.BASIC, null},
      {"20180819", AccountType.OPERATION, null},
      {"20180819", AccountType.FEES, null}
    };
  }

  @Test(dataProvider = "fundFlowData")
  public void testDownloadFundFlow(String billDate, String accountType, String tarType) throws Exception {
    WxPayFundFlowResult fundFlowResult = this.payService.downloadFundFlow(billDate, accountType, tarType);
    assertThat(fundFlowResult).isNotNull();
    this.logger.info(fundFlowResult.toString());
  }

  @Test(expectedExceptions = WxPayException.class)
  public void testDownloadFundFlow_withNoParams() throws Exception {
    //必填字段为空时，抛出异常
    this.payService.downloadFundFlow("", "", null);
  }

  @Test
  public void testReport() throws Exception {
    WxPayReportRequest request = new WxPayReportRequest();
    request.setInterfaceUrl("hahahah");
    request.setSignType(SignType.HMAC_SHA256);//貌似接口未校验此字段
    request.setExecuteTime(1000);
    request.setReturnCode("aaa");
    request.setResultCode("aaa");
    request.setUserIp("8.8.8");
    this.payService.report(request);
  }

  /**
   * Test method for {@link WxPayService#refund(WxPayRefundRequest)} .
   */
  @Test
  public void testRefund() throws Exception {
    WxPayRefundResult result = this.payService.refund(
      WxPayRefundRequest.newBuilder()
        .outRefundNo("aaa")
        .outTradeNo("1111")
        .totalFee(1222)
        .refundFee(111)
        .build());
    this.logger.info(result.toString());
  }

  /**
   * Test method for {@link WxPayService#refundQuery(String, String, String, String)} .
   */
  @Test
  public void testRefundQuery() throws Exception {
    WxPayRefundQueryResult result;

    result = this.payService.refundQuery("1", "", "", "");
    this.logger.info(result.toString());

    result = this.payService.refundQuery("", "2", "", "");
    this.logger.info(result.toString());

    result = this.payService.refundQuery("", "", "3", "");
    this.logger.info(result.toString());

    result = this.payService.refundQuery("", "", "", "4");
    this.logger.info(result.toString());

    //测试四个参数都填的情况，应该报异常的
    result = this.payService.refundQuery("1", "2", "3", "4");
    this.logger.info(result.toString());
  }

  @Test
  public void testParseRefundNotifyResult() throws Exception {
    // 请参考com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResultTest里的单元测试
  }

  /**
   * Test method for {@link WxPayService#sendRedpack(WxPaySendRedpackRequest)} .
   */
  @Test
  public void testSendRedpack() throws Exception {
    WxPaySendRedpackRequest request = new WxPaySendRedpackRequest();
    request.setActName("abc");
    request.setClientIp("aaa");
    request.setMchBillNo("aaaa");
    request.setWishing("what");
    request.setSendName("111");
    request.setTotalAmount(1);
    request.setTotalNum(1);
    request.setReOpenid(((XmlWxPayConfig) this.payService.getConfig()).getOpenid());
    WxPaySendRedpackResult redpackResult = this.payService.sendRedpack(request);
    this.logger.info(redpackResult.toString());
  }

  /**
   * Test method for {@link WxPayService#queryRedpack(String)}.
   */
  @Test
  public void testQueryRedpack() throws Exception {
    WxPayRedpackQueryResult redpackResult = this.payService.queryRedpack("aaaa");
    this.logger.info(redpackResult.toString());
  }

  @Test
  public void testCreateScanPayQrcodeMode1() throws Exception {
    String productId = "abc";
    byte[] bytes = this.payService.createScanPayQrcodeMode1(productId, null, null);
    Path qrcodeFilePath = Files.createTempFile("qrcode_", ".jpg");
    Files.write(qrcodeFilePath, bytes);
    String qrcodeContent = QrcodeUtils.decodeQrcode(qrcodeFilePath.toFile());
    this.logger.info(qrcodeContent);

    assertTrue(qrcodeContent.startsWith("weixin://wxpay/bizpayurl?"));
    assertTrue(qrcodeContent.contains("product_id=" + productId));
  }

  @Test
  public void testCreateScanPayQrcodeMode2() throws Exception {
    String qrcodeContent = "abc";
    byte[] bytes = this.payService.createScanPayQrcodeMode2(qrcodeContent, null, null);
    Path qrcodeFilePath = Files.createTempFile("qrcode_", ".jpg");
    Files.write(qrcodeFilePath, bytes);
    assertEquals(QrcodeUtils.decodeQrcode(qrcodeFilePath.toFile()), qrcodeContent);
  }

  @Test
  public void testMicropay() throws Exception {
    WxPayMicropayResult result = this.payService.micropay(
      WxPayMicropayRequest
        .newBuilder()
        .body("body")
        .outTradeNo("aaaaa")
        .totalFee(123)
        .spbillCreateIp("127.0.0.1")
        .authCode("aaa")
        .build());
    this.logger.info(result.toString());
  }

  @Test
  public void testGetConfig() throws Exception {
    // no need to test
  }

  @Test
  public void testSetConfig() throws Exception {
    // no need to test
  }

  @Test
  public void testReverseOrder() throws Exception {
    WxPayOrderReverseResult result = this.payService.reverseOrder(
      WxPayOrderReverseRequest
        .newBuilder()
        .outTradeNo("1111")
        .build());
    assertNotNull(result);
    this.logger.info(result.toString());
  }

  @Test
  public void testShorturl() throws Exception {
    String longUrl = "weixin://wxpay/bizpayurl?sign=XXXXX&appid=XXXXX&mch_id=XXXXX&product_id=XXXXXX&time_stamp=XXXXXX&nonce_str=XXXXX";

    String result = this.payService.shorturl(new WxPayShorturlRequest(longUrl));
    assertNotNull(result);
    this.logger.info(result);

    result = this.payService.shorturl(longUrl);
    assertNotNull(result);
    this.logger.info(result);
  }

  @Test
  public void testAuthcode2Openid() throws Exception {
    String authCode = "11111";

    String result = this.payService.authcode2Openid(new WxPayAuthcode2OpenidRequest(authCode));
    assertNotNull(result);
    this.logger.info(result);

    result = this.payService.authcode2Openid(authCode);
    assertNotNull(result);
    this.logger.info(result);
  }

  @Test
  public void testGetSandboxSignKey() throws Exception {
    final String signKey = this.payService.getSandboxSignKey();
    assertNotNull(signKey);
    this.logger.info(signKey);
  }

  @Test
  public void testSendCoupon() throws Exception {
    WxPayCouponSendResult result = this.payService.sendCoupon(WxPayCouponSendRequest.newBuilder()
      .couponStockId("123")
      .openid("122")
      .partnerTradeNo("1212")
      .openidCount(1)
      .build());
    this.logger.info(result.toString());
  }

  @Test
  public void testQueryCouponStock() throws Exception {
    WxPayCouponStockQueryResult result = this.payService.queryCouponStock(
      WxPayCouponStockQueryRequest
        .newBuilder()
        .couponStockId("123")
        .build());
    this.logger.info(result.toString());
  }

  @Test
  public void testQueryCouponInfo() throws Exception {
    WxPayCouponInfoQueryResult result = this.payService.queryCouponInfo(
      WxPayCouponInfoQueryRequest
        .newBuilder()
        .openid("ojOQA0y9o-Eb6Aep7uVTdbkJqrP4")
        .couponId("11")
        .stockId("1121")
        .build());
    this.logger.info(result.toString());
  }

  /**
   * 只支持拉取90天内的评论数据
   */
  @Test
  public void testQueryComment() throws Exception {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, -1);
    Date endDate = calendar.getTime();
    calendar.add(Calendar.DAY_OF_MONTH, -88);
    Date beginDate = calendar.getTime();
    String result = this.payService.queryComment(beginDate, endDate, 0, 1);
    this.logger.info(result);
  }

  /**
   * @see WxPayOrderNotifyResultTest#testFromXML()
   */
  @Test
  public void testParseOrderNotifyResult() throws Exception {
    // 请参考com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResultTest 里的单元测试

    String xmlString = "<xml>\n" +
      "  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
      "  <attach><![CDATA[支付测试]]></attach>\n" +
      "  <bank_type><![CDATA[CFT]]></bank_type>\n" +
      "  <fee_type><![CDATA[CNY]]></fee_type>\n" +
      "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
      "  <mch_id><![CDATA[10000100]]></mch_id>\n" +
      "  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" +
      "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
      "  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" +
      "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
      "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
      "  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>\n" +
      "  <sub_mch_id><![CDATA[10000100]]></sub_mch_id>\n" +
      "  <time_end><![CDATA[20140903131540]]></time_end>\n" +
      "  <total_fee>1</total_fee>\n" +
      "  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
      "  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" +
      "   <coupon_count>2</coupon_count>\n" +
      "   <coupon_type_0><![CDATA[CASH]]></coupon_type_0>\n" +
      "   <coupon_id_0>10000</coupon_id_0>\n" +
      "   <coupon_fee_0>100</coupon_fee_0>\n" +
      "   <coupon_type_1><![CDATA[NO_CASH]]></coupon_type_1>\n" +
      "   <coupon_id_1>10001</coupon_id_1>\n" +
      "   <coupon_fee_1>200</coupon_fee_1>\n" +
      "</xml>";

    WxPayOrderNotifyResult result = this.payService.parseOrderNotifyResult(xmlString);
    System.out.println(result);
  }

  @Test
  public void testGetWxApiData() throws Exception {
    //see test in testUnifiedOrder()
  }

}
