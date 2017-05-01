package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.utils.qrcode.QrcodeUtils;
import com.github.binarywang.wxpay.bean.request.*;
import com.github.binarywang.wxpay.bean.result.*;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.github.binarywang.wxpay.testbase.XmlWxPayConfig;
import com.google.inject.Inject;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * 测试支付相关接口
 * Created by Binary Wang on 2016/7/28.
 *
 * @author binarywang (https://github.com/binarywang)
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxPayServiceImplTest {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Inject
  private WxPayService payService;

  @Test
  public void testGetPayInfo() throws Exception {
    Map<String, String> payInfo = this.payService.getPayInfo(WxPayUnifiedOrderRequest.builder()
      .body("我去")
      .totalFee(1)
      .spbillCreateIp("111111")
      .notifyURL("111111")
      .tradeType("JSAPI")
      .outTradeNo("111111")
      .openid(((XmlWxPayConfig) this.payService.getConfig()).getOpenid())
      .build());
    this.logger.info(payInfo.toString());
  }

  @Test
  public void testDownloadBill() throws Exception {
    File file = this.payService.downloadBill("20170101", "ALL", "GZIP", "1111111");
    assertNotNull(file);
    //必填字段为空时，抛出异常
    this.payService.downloadBill("", "", "", null);
  }

  @Test
  public void testReport() throws Exception {
    WxPayReportRequest request = new WxPayReportRequest();
    request.setInterfaceUrl("hahahah");
    request.setSignType("HMAC-SHA256");//貌似接口未校验此字段
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
   * Test method for {@link WxPayService#refundQuery(java.lang.String, java.lang.String, java.lang.String, java.lang.String)} .
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

  /**
   * Test method for {@link WxPayService#sendRedpack(WxPaySendRedpackRequest)} .
   */
  @Test
  public void testSendRedpack() throws Exception {
    WxPaySendRedpackRequest request = new WxPaySendRedpackRequest();
    request.setActName("abc");
    request.setClientIp("aaa");
    request.setMchBillNo("aaaa");
    request.setReOpenid(((XmlWxPayConfig) this.payService.getConfig()).getOpenid());
    WxPaySendRedpackResult redpackResult = this.payService.sendRedpack(request);
    this.logger.info(redpackResult.toString());
  }

  /**
   * Test method for {@link WxPayService#queryRedpack(java.lang.String)}.
   */
  @Test
  public void testQueryRedpack() throws Exception {
    WxPayRedpackQueryResult redpackResult = this.payService.queryRedpack("aaaa");
    this.logger.info(redpackResult.toString());
  }

  /**
   * Test method for {@link WxPayService#unifiedOrder(WxPayUnifiedOrderRequest)}.
   */
  @Test
  public void testUnifiedOrder() throws WxErrorException {
    WxPayUnifiedOrderResult result = this.payService
      .unifiedOrder(WxPayUnifiedOrderRequest.builder()
        .body("我去")
        .totalFee(1)
        .spbillCreateIp("111111")
        .notifyURL("111111")
        .tradeType("JSAPI")
        .openid(((XmlWxPayConfig) this.payService.getConfig()).getOpenid())
        .outTradeNo("111111")
        .build());
    this.logger.info(result.toString());
  }

  /**
   * Test method for {@link WxPayService#queryOrder(java.lang.String, java.lang.String)} .
   */
  @Test
  public void testQueryOrder() throws WxErrorException {
    this.logger.info(this.payService.queryOrder("11212121", null).toString());
    this.logger.info(this.payService.queryOrder(null, "11111").toString());
  }

  /**
   * Test method for {@link WxPayService#closeOrder(java.lang.String)} .
   */
  @Test
  public void testCloseOrder() throws WxErrorException {
    this.logger.info(this.payService.closeOrder("11212121").toString());
  }

  /**
   * Test method for {@link WxPayService#entPay(WxEntPayRequest)}.
   */
  @Test
  public void testEntPay() throws WxErrorException {
    WxEntPayRequest request = new WxEntPayRequest();
    this.logger.info(this.payService.entPay(request).toString());
  }

  /**
   * Test method for {@link WxPayService#queryEntPay(java.lang.String)}.
   */
  @Test
  public void testQueryEntPay() throws WxErrorException {
    this.logger.info(this.payService.queryEntPay("11212121").toString());
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
  public void testGetOrderNotifyResult() throws Exception {
  }

  @Test
  public void testMicropay() throws Exception {
    WxPayMicropayResult result = this.payService.micropay(WxPayMicropayRequest.newBuilder()
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
    WxPayOrderReverseResult result = this.payService.reverseOrder(WxPayOrderReverseRequest.newBuilder()
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

}
