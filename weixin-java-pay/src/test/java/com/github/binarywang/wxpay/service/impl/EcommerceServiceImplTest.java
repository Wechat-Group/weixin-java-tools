package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.ecommerce.PartnerTransactionsQueryRequest;
import com.github.binarywang.wxpay.bean.ecommerce.PartnerTransactionsResult;
import com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class EcommerceServiceImplTest {

  @Inject
  private  WxPayService wxPayService;

  @Test
  public void testNotifySign(){
    //通知报文主体
    String notifyData = "";
    //请求头  Wechatpay-Timestamp
    String timeStamp = "";
    //请求头  Wechatpay-Nonce
    String nonce = "";
    //请求头  Wechatpay-Signature
    String signed = "";
    //请求头  Wechatpay-Serial
    String serialNo = "";

    SignatureHeader header = new SignatureHeader();
    header.setNonce(nonce);
    header.setSerialNo(serialNo);
    header.setTimeStamp(timeStamp);
    header.setSigned(signed);

    String beforeSign = String.format("%s\n%s\n%s\n",
      header.getTimeStamp(),
      header.getNonce(),
      notifyData);
    boolean signResult = wxPayService.getConfig().getVerifier().verify(header.getSerialNo(),
      beforeSign.getBytes(StandardCharsets.UTF_8), header.getSigned());
    log.info("签名结果:{} \nheader:{} \ndata:{}", signResult, header, notifyData);
  }

  @Test
  public void testQueryPartnerTransactions() throws WxPayException {
    PartnerTransactionsQueryRequest request = new PartnerTransactionsQueryRequest();
    //服务商商户号
    request.setSpMchid("");
    //二级商户号
    request.setSubMchid("");
    //商户订单号
    request.setOutTradeNo("");
    //微信订单号
    request.setTransactionId("");
    wxPayService.getEcommerceService().queryPartnerTransactions(request);
  }

  @Test
  public void testSubNowBalance() throws WxPayException {
    String subMchid = "";
    wxPayService.getEcommerceService().subNowBalance(subMchid);
  }

  @Test
  public void testSubDayEndBalance() throws WxPayException {
    String subMchid = "";
    String date = "";
    wxPayService.getEcommerceService().subDayEndBalance(subMchid,date);
  }
}
