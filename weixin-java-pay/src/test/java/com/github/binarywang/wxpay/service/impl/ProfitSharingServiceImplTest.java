package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.profitsharing.*;
import com.github.binarywang.wxpay.bean.profitsharing.ProfitSharingRequest;
import com.github.binarywang.wxpay.bean.profitsharing.Receiver;
import com.github.binarywang.wxpay.bean.profitsharing.ReceiverList;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Test
@Guice(modules = ApiTestModule.class)
public class ProfitSharingServiceImplTest {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Inject
  private WxPayService payService;

  @Test
  public void testProfitsharing() throws WxPayException {
    ReceiverList instance = ReceiverList.getInstance();
    instance.add(new Receiver(WxPayConstants.ReceiverType.PERSONAL_OPENID,
      "oyOUE5ql4TtzrBg5cVOwxq6tbjOs",
      20,
      "***"));
    //30000002922019102310811092093
    ProfitSharingRequest request = ProfitSharingRequest
      .newBuilder()
      .outOrderNo("20191023112023031060677")
      .transactionId("4200000431201910234736634272")
      .receivers(instance.toJSONString())
      .build();
    this.logger.info(this.payService.getProfitSharingService().profitsharing(request).toString());
  }

  @Test
  public void testMultiprofitsharing() throws WxPayException {
    ReceiverList instance = ReceiverList.getInstance();
    instance.add(new Receiver(WxPayConstants.ReceiverType.MERCHANT_ID,
      "86693852",
      1,
      "***"));
    ProfitSharingRequest request = ProfitSharingRequest
      .newBuilder()
      .outOrderNo("20191023154723316420060")
      .transactionId("4200000448201910238249687345")//order_id=30000102922019102310821824010
      .receivers(instance.toJSONString())
      .build();
    this.logger.info(this.payService.getProfitSharingService().multiprofitsharing(request).toString());
  }

  @Test
  public void testProfitsharingFinish() throws WxPayException {
    ProfitSharingFinishRequest request = ProfitSharingFinishRequest
      .newBuilder()
      .outOrderNo("20191023103251431856285")
      .transactionId("4200000441201910238267278073")
      .description("分账完成")
      .build();
    this.logger.info(this.payService.getProfitSharingService().profitsharingfinish(request).toString());
  }

  @Test
  public void testAddreceiver() throws WxPayException {
    Receiver receiver = new Receiver(WxPayConstants.ReceiverType.PERSONAL_OPENID,
      "oyOUE5ql4TtzrBg5cVOwxq6tbjOs",
      "***",
      "STORE_OWNER",
      null);
    ProfitSharingReceiverRequest request = ProfitSharingReceiverRequest
      .newBuilder()
      .receiver(receiver.toJSONString())
      .build();
    this.logger.info(this.payService.getProfitSharingService().addReceiver(request).toString());
  }

  @Test
  public void testRemoveReceiver() throws WxPayException {
    Receiver receiver = new Receiver(WxPayConstants.ReceiverType.PERSONAL_OPENID,
      "oyOUE5ql4TtzrBg5cVOwxq6tbjOs");
    ProfitSharingReceiverRequest request = ProfitSharingReceiverRequest
      .newBuilder()
      .receiver(receiver.toJSONString())
      .build();
    this.logger.info(this.payService.getProfitSharingService().removeReceiver(request).toString());
  }

  @Test
  public void testProfitsharingQuery() throws WxPayException {
    ProfitSharingQueryRequest request = ProfitSharingQueryRequest
      .newBuilder()
      .outOrderNo("20191023112023031060677")
      .transactionId("4200000431201910234736634272")
      .build();
    ProfitSharingQueryResult result = this.payService.getProfitSharingService().profitsharingQuery(request);
    this.logger.info(result.formatReceivers().toString());
    this.logger.info(result.toString());
  }

  @Test
  public void testProfitsharingReturn() throws WxPayException {
    ProfitSharingReturnRequest request = ProfitSharingReturnRequest
      .newBuilder()
      .outOrderNo("20191023154723316420060")
      .outReturnNo("R2019102315")
      .returnAccountType("MERCHANT_ID")
      .returnAccount("86693852")
      .returnAmount(2)
      .description("用户退款")
      .build();
    this.logger.info(this.payService.getProfitSharingService().profitsharingReturn(request).toString());
  }

  @Test
  public void testProfitsharingReturnQuery() throws WxPayException {
    ProfitSharingReturnQueryRequest request = ProfitSharingReturnQueryRequest
      .newBuilder()
      .outOrderNo("20191023154723316420060")
      .outReturnNo("R2019102315")
      .build();
    this.logger.info(this.payService.getProfitSharingService().profitsharingReturnQuery(request).toString());
  }


}
