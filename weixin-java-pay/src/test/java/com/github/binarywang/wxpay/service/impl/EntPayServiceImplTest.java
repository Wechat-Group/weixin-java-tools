package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.entpay.EntPayRequest;
import com.github.binarywang.wxpay.bean.entpay.EntPayBankRequest;
import com.github.binarywang.wxpay.bean.entpay.EntPayBankResult;
import com.github.binarywang.wxpay.bean.request.WxEntPayRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * <pre>
 *  企业付款测试类.
 *  Created by BinaryWang on 2017/12/19.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class EntPayServiceImplTest {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Inject
  private WxPayService payService;

  @Test
  public void testEntPay_old() throws WxPayException {
    this.logger.info(this.payService.entPay(WxEntPayRequest.builder()
      .partnerTradeNo("Eb6Aep7uVTdbkJqrP4")
      .openid("ojOQA0y9o-Eb6Aep7uVTdbkJqrP4")
      .amount(1)
      .spbillCreateIp("10.10.10.10")
      .checkName(WxPayConstants.CheckNameOption.NO_CHECK)
      .description("描述信息")
      .build()).toString());
  }

  @Test
  public void testEntPay() throws WxPayException {
    EntPayRequest request = EntPayRequest.newBuilder()
      .partnerTradeNo("Eb6Aep7uVTdbkJqrP4")
      .openid("ojOQA0y9o-Eb6Aep7uVTdbkJqrP4")
      .amount(1)
      .spbillCreateIp("10.10.10.10")
      .checkName(WxPayConstants.CheckNameOption.NO_CHECK)
      .description("描述信息")
      .build();

    this.logger.info(this.payService.getEntPayService().entPay(request).toString());
  }

  @Test
  public void testQueryEntPay_old() throws WxPayException {
    this.logger.info(this.payService.queryEntPay("11212121").toString());
  }

  @Test
  public void testQueryEntPay() throws WxPayException {
    this.logger.info(this.payService.getEntPayService().queryEntPay("11212121").toString());
  }

  @Test
  public void testGetPublicKey() throws Exception {
    this.logger.info(this.payService.getEntPayService().getPublicKey());
  }

  @Test
  public void testPayBank() throws Exception {
    EntPayBankResult result = this.payService.getEntPayService().payBank(EntPayBankRequest.builder()
      .bankCode("aa")
      .amount(1)
      .encBankNo("1")
      .encTrueName("2")
      .partnerTradeNo("3")
      .description("11")
      .build());
    this.logger.info(result.toString());
  }

  @Test
  public void testQueryPayBank() throws Exception {
    this.logger.info(this.payService.getEntPayService().queryPayBank("123").toString());
  }
}
