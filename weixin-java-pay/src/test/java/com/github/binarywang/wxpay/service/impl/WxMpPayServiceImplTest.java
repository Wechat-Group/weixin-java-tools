package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.utils.qrcode.QrcodeUtils;
import com.github.binarywang.wxpay.bean.request.*;
import com.github.binarywang.wxpay.bean.result.*;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.github.binarywang.wxpay.testbase.TestPayConfig;
import com.google.inject.Inject;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.testng.Assert.*;

/**
 * 测试支付相关接口
 * Created by Binary Wang on 2016/7/28.
 *
 * @author binarywang (https://github.com/binarywang)
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpPayServiceImplTest {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Inject
  protected WxPayService wxService;

  @Test
  public void testGetPayInfo() throws Exception {

  }

  @Test
  public void testDownloadBill() throws Exception {
    File file = this.wxService.downloadBill("20170101", "ALL", "GZIP", "1111111");
    assertNotNull(file);
    //必填字段为空时，抛出异常
    this.wxService.downloadBill("", "", "", null);
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
    this.wxService.report(request);
  }

  /**
   * Test method for {@link WxPayService#refund(WxPayRefundRequest)} .
   */
  @Test//(dependsOnMethods = {"setSSLKey"})
  public void testRefund() throws Exception {
    WxPayRefundResult result = this.wxService.refund(
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

    result = this.wxService.refundQuery("1", "", "", "");
    this.logger.info(result.toString());

    result = this.wxService.refundQuery("", "2", "", "");
    this.logger.info(result.toString());

    result = this.wxService.refundQuery("", "", "3", "");
    this.logger.info(result.toString());

    result = this.wxService.refundQuery("", "", "", "4");
    this.logger.info(result.toString());

    //测试四个参数都填的情况，应该报异常的
    result = this.wxService.refundQuery("1", "2", "3", "4");
    this.logger.info(result.toString());
  }

  /**
   * Test method for {@link WxPayService#sendRedpack(WxPaySendRedpackRequest)} .
   */
  @Test//(dependsOnMethods = {"setSSLKey"})
  public void testSendRedpack() throws Exception {
    WxPaySendRedpackRequest request = new WxPaySendRedpackRequest();
    request.setActName("abc");
    request.setClientIp("aaa");
    request.setMchBillNo("aaaa");
    request.setReOpenid(((TestPayConfig) this.wxService.getConfig()).getOpenid());
    WxPaySendRedpackResult redpackResult = this.wxService.sendRedpack(request);
    this.logger.info(redpackResult.toString());
  }

  /**
   * Test method for {@link WxPayService#queryRedpack(java.lang.String)}.
   */
  @Test//(dependsOnMethods = {"setSSLKey"})
  public void testQueryRedpack() throws Exception {
    WxPayRedpackQueryResult redpackResult = this.wxService.queryRedpack("aaaa");
    this.logger.info(redpackResult.toString());
  }

  /**
   * Test method for {@link WxPayService#unifiedOrder(WxPayUnifiedOrderRequest)}.
   */
  @Test
  public void testUnifiedOrder() throws WxErrorException {
    WxPayUnifiedOrderResult result = this.wxService
      .unifiedOrder(WxPayUnifiedOrderRequest.builder()
        .body("我去")
        .totalFee(1)
        .spbillCreateIp("111111")
        .notifyURL("111111")
        .tradeType("JSAPI")
        .openid("122")
        .outTradeNo("111111")
        .build());
    this.logger.info(result.toString());
  }

  /**
   * Test method for {@link WxPayService#queryOrder(java.lang.String, java.lang.String)} .
   */
  @Test
  public void testQueryOrder() throws WxErrorException {
    this.logger.info(this.wxService.queryOrder("11212121", null).toString());
    this.logger.info(this.wxService.queryOrder(null, "11111").toString());
  }

  /**
   * Test method for {@link WxPayService#closeOrder(java.lang.String)} .
   */
  @Test
  public void testCloseOrder() throws WxErrorException {
    this.logger.info(this.wxService.closeOrder("11212121").toString());
  }

  /**
   * Test method for {@link WxPayService#entPay(WxEntPayRequest)}.
   */
  @Test//(dependsOnMethods = {"setSSLKey"})
  public void testEntPay() throws WxErrorException {
    WxEntPayRequest request = new WxEntPayRequest();
    this.logger.info(this.wxService.entPay(request).toString());
  }

  /**
   * Test method for {@link WxPayService#queryEntPay(java.lang.String)}.
   */
  @Test//(dependsOnMethods = {"setSSLKey"})
  public void testQueryEntPay() throws WxErrorException {
    this.logger.info(this.wxService.queryEntPay("11212121").toString());
  }

  @Test
  public void testCreateScanPayQrcodeMode1() throws Exception {
    String productId = "abc";
    byte[] bytes = this.wxService.createScanPayQrcodeMode1(productId, null, null);
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
    byte[] bytes = this.wxService.createScanPayQrcodeMode2(qrcodeContent, null, null);
    Path qrcodeFilePath = Files.createTempFile("qrcode_", ".jpg");
    Files.write(qrcodeFilePath, bytes);
    assertEquals(QrcodeUtils.decodeQrcode(qrcodeFilePath.toFile()), qrcodeContent);
  }

}
