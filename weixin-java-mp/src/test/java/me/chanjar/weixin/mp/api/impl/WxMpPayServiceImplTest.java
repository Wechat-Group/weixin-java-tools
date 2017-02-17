package me.chanjar.weixin.mp.api.impl;

import com.github.binarywang.utils.qrcode.QrcodeUtils;
import com.google.inject.Inject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConfigStorage;
import me.chanjar.weixin.mp.bean.pay.request.*;
import me.chanjar.weixin.mp.bean.pay.result.*;
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

  @Inject
  protected WxMpService wxService;

  @Test
  public void testGetPayInfo() throws Exception {

  }

  @Test
  public void testDownloadBill() throws Exception {
    File file = this.wxService.getPayService().downloadBill("20170101", "ALL", "GZIP", "1111111");
    assertNotNull(file);
    //必填字段为空时，抛出异常
    this.wxService.getPayService().downloadBill("", "", "", null);
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
    this.wxService.getPayService().report(request);
  }

  /**
   * 需要证书的接口需要先执行该方法
   */
  @Test
  public void setSSLKey() {
    TestConfigStorage config = (TestConfigStorage) this.wxService.getWxMpConfigStorage();
    config.setSslContextFilePath(config.getKeyPath());
  }

  /**
   * Test method for {@link me.chanjar.weixin.mp.api.impl.WxMpPayServiceImpl#refund(WxPayRefundRequest)} .
   */
  @Test(dependsOnMethods = {"setSSLKey"})
  public void testRefund() throws Exception {
    WxPayRefundRequest request = new WxPayRefundRequest();
    request.setOutRefundNo("aaa");
    request.setOutTradeNo("1111");
    request.setTotalFee(1222);
    request.setRefundFee(111);
    WxPayRefundResult result = this.wxService.getPayService().refund(request);
    System.err.println(result);
  }

  /**
   * Test method for {@link me.chanjar.weixin.mp.api.impl.WxMpPayServiceImpl#refundQuery(String, String, String, String)} .
   */
  @Test
  public void testRefundQuery() throws Exception {
    WxPayRefundQueryResult result;

    result = this.wxService.getPayService().refundQuery("1", "", "", "");
    System.err.println(result);
    result = this.wxService.getPayService().refundQuery("", "2", "", "");
    System.err.println(result);
    result = this.wxService.getPayService().refundQuery("", "", "3", "");
    System.err.println(result);
    result = this.wxService.getPayService().refundQuery("", "", "", "4");
    System.err.println(result);
    //测试四个参数都填的情况，应该报异常的
    result = this.wxService.getPayService().refundQuery("1", "2", "3", "4");
    System.err.println(result);
  }

  /**
   * Test method for {@link me.chanjar.weixin.mp.api.impl.WxMpPayServiceImpl#sendRedpack(WxPaySendRedpackRequest)} .
   */
  @Test(dependsOnMethods = {"setSSLKey"})
  public void testSendRedpack() throws Exception {
    WxPaySendRedpackRequest request = new WxPaySendRedpackRequest();
    request.setActName("abc");
    request.setClientIp("aaa");
    request.setMchBillNo("aaaa");
    request
      .setReOpenid(((TestConfigStorage) this.wxService.getWxMpConfigStorage()).getOpenid());
    WxPaySendRedpackResult redpackResult = this.wxService.getPayService().sendRedpack(request);
    System.err.println(redpackResult);
  }

  /**
   * Test method for {@link me.chanjar.weixin.mp.api.impl.WxMpPayServiceImpl#queryRedpack(String)}.
   */
  @Test(dependsOnMethods = {"setSSLKey"})
  public void testQueryRedpack() throws Exception {
    WxPayRedpackQueryResult redpackResult = this.wxService.getPayService().queryRedpack("aaaa");
    System.err.println(redpackResult);
  }

  /**
   * Test method for {@link me.chanjar.weixin.mp.api.impl.WxMpPayServiceImpl#unifiedOrder(WxPayUnifiedOrderRequest)}.
   */
  @Test
  public void testUnifiedOrder() throws WxErrorException {
    WxPayUnifiedOrderResult result = this.wxService.getPayService()
      .unifiedOrder(WxPayUnifiedOrderRequest.builder().body("1111111")
        .totalFee(1).spbillCreateIp("111111").notifyURL("111111")
        .tradeType("JSAPI").openid("122").outTradeNo("111111").build());
    System.err.println(result);
  }

  /**
   * Test method for {@link me.chanjar.weixin.mp.api.impl.WxMpPayServiceImpl#queryOrder(String, String)} .
   */
  @Test
  public final void testQueryOrder() throws WxErrorException {
    //System.err.println(this.wxService.getPayService().queryOrder(null, null));
    System.err.println(this.wxService.getPayService().queryOrder("11212121", null));
    System.err.println(this.wxService.getPayService().queryOrder(null, "11111"));
  }

  /**
   * Test method for {@link me.chanjar.weixin.mp.api.impl.WxMpPayServiceImpl#closeOrder(String)} .
   */
  @Test
  public final void testCloseOrder() throws WxErrorException {
    System.err.println(this.wxService.getPayService().closeOrder("11212121"));
  }

  /**
   * Test method for {@link me.chanjar.weixin.mp.api.impl.WxMpPayServiceImpl#entPay(WxEntPayRequest)}.
   */
  @Test(dependsOnMethods = {"setSSLKey"})
  public final void testEntPay() throws WxErrorException {
    WxEntPayRequest request = new WxEntPayRequest();
    System.err.println(this.wxService.getPayService().entPay(request));
  }

  /**
   * Test method for {@link me.chanjar.weixin.mp.api.impl.WxMpPayServiceImpl#queryEntPay(String)}.
   */
  @Test(dependsOnMethods = {"setSSLKey"})
  public final void testQueryEntPay() throws WxErrorException {
    System.err.println(this.wxService.getPayService().queryEntPay("11212121"));
  }

  @Test
  public void testCreateScanPayQrcodeMode1() throws Exception {
    String productId = "abc";
    byte[] bytes = this.wxService.getPayService().createScanPayQrcodeMode1(productId, null, null);
    Path qrcodeFilePath = Files.createTempFile("qrcode_", ".jpg");
    Files.write(qrcodeFilePath, bytes);
    String qrcodeContent = QrcodeUtils.decodeQrcode(qrcodeFilePath.toFile());
    System.out.println(qrcodeContent);

    assertTrue(qrcodeContent.startsWith("weixin://wxpay/bizpayurl?"));
    assertTrue(qrcodeContent.contains("product_id=" + productId));
  }

  @Test
  public void testCreateScanPayQrcodeMode2() throws Exception {
    String qrcodeContent = "abc";
    byte[] bytes = this.wxService.getPayService().createScanPayQrcodeMode2(qrcodeContent, null, null);
    Path qrcodeFilePath = Files.createTempFile("qrcode_", ".jpg");
    Files.write(qrcodeFilePath, bytes);

    assertEquals(QrcodeUtils.decodeQrcode(qrcodeFilePath.toFile()), qrcodeContent);
  }


}
