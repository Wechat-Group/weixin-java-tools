package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.entpay.*;
import com.github.binarywang.wxpay.constant.WxPayConstants.CheckNameOption;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

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

  /**
   * Test ent pay.
   *
   * @throws WxPayException the wx pay exception
   */
  @Test
  public void testEntPay() throws WxPayException {
    EntPayRequest request = EntPayRequest.newBuilder()
      .partnerTradeNo("Eb6Aep7uVTdbkJqrP4")
      .openid("ojOQA0y9o-Eb6Aep7uVTdbkJqrP5")
      .amount(100)
      .spbillCreateIp("10.10.10.10")
      .checkName(CheckNameOption.NO_CHECK)
      .description("描述信息")
      .build();

    this.logger.info(this.payService.getEntPayService().entPay(request).toString());
  }

  /**
   * Test query ent pay.
   *
   * @throws WxPayException the wx pay exception
   */
  @Test
  public void testQueryEntPay() throws WxPayException {
    this.logger.info(this.payService.getEntPayService().queryEntPay("11212121").toString());
  }

  /**
   * Test get public key.
   *
   * @throws Exception the exception
   */
  @Test
  public void testGetPublicKey() throws Exception {
    this.logger.info(this.payService.getEntPayService().getPublicKey());
  }

  /**
   * Test pay bank.
   *
   * @throws Exception the exception
   */
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

  /**
   * Test query pay bank.
   *
   * @throws Exception the exception
   */
  @Test
  public void testQueryPayBank() throws Exception {
    this.logger.info(this.payService.getEntPayService().queryPayBank("123").toString());
  }



  /**
   * 发送企业红包
   * @throws Exception the exception
   */
  @Test
  public void testSendEnterpriseRedpack() {
    EntPayRedpackRequest request = new EntPayRedpackRequest();
    request.setMchId("1");
    //商户单号
    request.setMchBillNo(request.getMchId()+"20191202"+"1");
    //企业微信corpid即为此appId
    request.setWxAppId("1");
//    request.setSenderName("1");
//    request.setSenderHeaderMediaId("2");
    request.setAgentId("1");
    request.setReOpenid("1");
    //目前企业微信api红包最低1块钱
    request.setTotalAmount(1000);
    request.setWishing("1");
    request.setActName("1");
    request.setRemark("1");

    EntPayRedpackResult redpackResult = null;
    try {
      redpackResult = this.payService.getEntPayService().sendEnterpriseRedpack(request);
    } catch (WxPayException e) {
    }
    this.logger.info(redpackResult.toString());
  }

  /**
   * 查询企业红包
   * @throws Exception
   */
  @Test
  public void testQueryEnterpriseRedpack() throws Exception {
    while (true) {
      EntPayRedpackQueryRequest request = new EntPayRedpackQueryRequest();
      request.setAppid("1");
      request.setMchId("1");
      request.setMchBillNo("1");

      try {
        EntPayRedpackQueryResult result = this.payService.getEntPayService().queryEnterpriseRedpack(request);
        this.logger.info(result.toString());
      } catch (Exception e) {
      }
      TimeUnit.SECONDS.sleep(3);
    }
  }
}
