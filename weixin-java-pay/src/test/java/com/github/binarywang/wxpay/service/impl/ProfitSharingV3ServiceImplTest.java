package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 测试类
 *
 * @author yuanbo
 * @since 2022-04-26-22:33 PM
 */
@Test
@Slf4j
@Guice(modules = ApiTestModule.class)
public class ProfitSharingV3ServiceImplTest {
  @Inject
  private WxPayService payService;

  @Test
  public void testProfitSharingNotifyData() throws WxPayException {
    SignatureHeader header = new SignatureHeader();
    header.setSerial("Wechatpay-Serial");
    header.setTimeStamp("Wechatpay-Timestamp");
    header.setNonce("Wechatpay-Nonce");
    header.setSignature("Wechatpay-Signature");
    String data = "body";
    log.info(this.payService.getProfitSharingV3Service().getProfitSharingNotifyResult(data,header).toString());
  }
}
