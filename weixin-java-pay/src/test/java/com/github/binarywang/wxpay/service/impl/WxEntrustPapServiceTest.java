package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.request.*;
import com.github.binarywang.wxpay.bean.result.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.common.base.Joiner;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author chenliang
 * created on  2021-08-02 6:45 下午
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxEntrustPapServiceTest {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());


  @Inject
  private WxPayService payService;

  /**
   * 公众号纯签约
   */
  @Test
  public void testMpSign() {

    String contractCode = "222200002222";
    String displayAccount = Joiner.on("").join("陈*", "(", "10000014", ")");
    WxMpEntrustRequest wxMpEntrust = WxMpEntrustRequest.newBuilder()
      .planId("142323") //模板ID：跟微信申请
      .contractCode(contractCode)
      .contractDisplayAccount(displayAccount)
      .notifyUrl("http://domain.com/api/wxpay/sign/callback.do")
      .requestSerial(6L)
      //.returnWeb(1)
      .version("1.0")
      .timestamp(String.valueOf(System.currentTimeMillis() / 1000))
      .outerId(displayAccount)
      .build();

    String url = null;
    try {
      url = this.payService.getWxEntrustPapService().mpSign(wxMpEntrust);
    } catch (WxPayException e) {
      e.printStackTrace();
    }
    logger.info(url);
  }

  /**
   * 小程序纯签约
   */
  @Test
  public void testMaSign() {
    String contractCode = "222220000022222";
    String displayAccount = Joiner.on("").join("陈*", "(", "10000001", ")");

    WxMaEntrustRequest wxMaEntrustRequest = WxMaEntrustRequest.newBuilder()
      .contractCode(contractCode)
      .contractDisplayAccount(contractCode)
      .notifyUrl("http://domain.com/api/wxpay/sign/callback.do")
      .outerId(displayAccount)
      .planId("141535")
      .requestSerial(2L)
      .timestamp(String.valueOf(System.currentTimeMillis() / 1000))
      .build();

    try {
      String url = this.payService.getWxEntrustPapService().maSign(wxMaEntrustRequest);
      logger.info(url);
    } catch (WxPayException e) {
      e.printStackTrace();
    }
  }

  /**
   * h5纯签约
   */
  @Test
  public void testH5Sign() {
    String contractCode = "222111122222";
    String displayAccount = Joiner.on("").join("陈*", "(", "100000000", ")");

    WxH5EntrustRequest wxH5EntrustRequest = WxH5EntrustRequest.newBuilder()
      .requestSerial(2L)
      .clientIp("127.0.0.1")
      .contractCode(contractCode)
      .contractDisplayAccount(displayAccount)
      .notifyUrl("http://domain.com/api/wxpay/sign/callback.do")
      .planId("141535")
      .returnAppid("1")
      .timestamp(String.valueOf(System.currentTimeMillis() / 1000))
      .version("1.0")
      .outerId(displayAccount)
      .build();

    try {
      WxH5EntrustResult wxH5EntrustResult = this.payService.getWxEntrustPapService().h5Sign(wxH5EntrustRequest);
      logger.info(wxH5EntrustResult.toString());
    } catch (WxPayException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testPaySign() {
    String contractCode = "2222211110000222";
    String displayAccount = Joiner.on("").join("陈*", "(", "10000005", ")");
    String outTradeNo = "11100111101";

    WxPayEntrustRequest wxPayEntrustRequest = WxPayEntrustRequest.newBuilder()
      .attach("local")
      .body("产品名字")
      .contractAppId(this.payService.getConfig().getAppId())
      .contractCode(contractCode)
      .contractDisplayAccount(displayAccount)
      .contractMchId(this.payService.getConfig().getMchId())
      //签约回调
      .contractNotifyUrl("http://domain.com/api/wxpay/sign/callback.do")
      .detail("产品是好")
      .deviceInfo("oneplus 7 pro")
      //.goodsTag()
      //.limitPay()
      //支付回调
      .notifyUrl("http://domain.com/api/wxpay/pay/callback.do")
      .openId("oIvLdt8Q-_aKy4Vo6f4YI6gsIhMc") //openId
      .outTradeNo(outTradeNo)
      .planId("141535")
      //.productId()
      .requestSerial(3L)
      .spbillCreateIp("127.0.0.1")
      //.timeExpire()
      //.timeStart()
      .totalFee(1)
      .tradeType("MWEB")
      .contractOuterId(displayAccount)
      .build();

    try {
      WxPayEntrustResult wxPayEntrustResult = this.payService.getWxEntrustPapService().paySign(wxPayEntrustRequest);
      logger.info(wxPayEntrustResult.toString());
    } catch (WxPayException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testWithhold() {
    String outTradeNo = "101010101";
    WxWithholdRequest withholdRequest = WxWithholdRequest.newBuilder()
      .attach("local")
      .body("产品名字")
      .contractId("202011065409471222") //  微信返回的签约协议号
      .detail("产品描述")
      .feeType("CNY")
      //.goodsTag()
      .notifyUrl("http://domain.com/api/wxpay/withhold/callback.do")
      .outTradeNo(outTradeNo)
      .spbillCreateIp("127.0.0.1")
      .totalFee(1)
      .tradeType("PAP")
      .build();

    try {
      WxWithholdResult wxWithholdResult = this.payService.getWxEntrustPapService().withhold(withholdRequest);
      logger.info(wxWithholdResult.toString());
    } catch (WxPayException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testWithholdPartner() {
    String outTradeNo = "101010101";
    WxWithholdRequest withholdRequest = WxWithholdRequest.newBuilder()
      .attach("local")
      .body("产品名字")
      .contractId("202011065409471222") //  微信返回的签约协议号
      .detail("产品描述")
      .feeType("CNY")
      //.goodsTag()
      .notifyUrl("http://domain.com/api/wxpay/withhold/callback.do")
      .outTradeNo(outTradeNo)
      .spbillCreateIp("127.0.0.1")
      .totalFee(1)
      .tradeType("PAP")
      .build();

    try {
      WxPayCommonResult wxPayCommonResult = this.payService.getWxEntrustPapService().withholdPartner(withholdRequest);
      logger.info(wxPayCommonResult.toString());
    } catch (WxPayException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testPreWithhold() {
    WxPreWithholdRequest.EstimateAmount estimateAmount = new WxPreWithholdRequest.EstimateAmount();
    estimateAmount.setAmount(1);
    estimateAmount.setCurrency("CNY");

    WxPreWithholdRequest wxPreWithholdRequest = WxPreWithholdRequest.newBuilder()
      .appId("wx73dssxxxxxx")
      .contractId("202010275173070001")
      .estimateAmount(estimateAmount)
      .mchId("1600010102")
      .build();

    try {
      String httpResponseModel = this.payService.getWxEntrustPapService().preWithhold(wxPreWithholdRequest);
      logger.info(httpResponseModel);
    } catch (WxPayException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testQuerySign() {
    String outTradeNo = "1212121212";

    WxSignQueryRequest wxSignQueryRequest = WxSignQueryRequest.newBuilder()
      //.contractId("202010275173073211")
      .contractCode(outTradeNo)
      .planId(1432112)
      .version("1.0")
      .build();

    try {
      WxSignQueryResult wxSignQueryResult = this.payService.getWxEntrustPapService().querySign(wxSignQueryRequest);
      logger.info(wxSignQueryResult.toString());
    } catch (WxPayException e) {
      logger.info("异常码:" + e.getErrCode());
      logger.info("异常：" + e);
    }
  }

  @Test
  public void testTerminationContract() {
    WxTerminatedContractRequest wxTerminatedContractRequest = WxTerminatedContractRequest.newBuilder()
      .contractId("202010275173070231")
      .contractTerminationRemark("测试解约")
      .version("1.0")
      .build();

    try {
      WxTerminationContractResult wxTerminationContractResult = this.payService.getWxEntrustPapService().terminationContract(wxTerminatedContractRequest);
      logger.info(wxTerminationContractResult.toString());
    } catch (WxPayException e) {
      logger.error(e.getMessage());
    }
  }
}
