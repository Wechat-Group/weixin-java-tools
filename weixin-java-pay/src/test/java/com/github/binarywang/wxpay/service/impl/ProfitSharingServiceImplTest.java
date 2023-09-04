package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.profitsharing.*;
import com.github.binarywang.wxpay.bean.profitsharing.request.*;
import com.github.binarywang.wxpay.bean.profitsharing.result.ProfitSharingMerchantRatioQueryResult;
import com.github.binarywang.wxpay.bean.profitsharing.result.ProfitSharingOrderAmountQueryResult;
import com.github.binarywang.wxpay.bean.profitsharing.result.ProfitSharingQueryResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Test
@Slf4j
@Guice(modules = ApiTestModule.class)
public class ProfitSharingServiceImplTest {
  @Inject
  private WxPayService payService;

  @Test
  public void testProfitSharing() throws WxPayException {
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
    log.info(this.payService.getProfitSharingService().profitSharing(request).toString());
  }

  @Test
  public void testMultiProfitSharing() throws WxPayException {
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
    log.info(this.payService.getProfitSharingService().multiProfitSharing(request).toString());
  }

  @Test
  public void testProfitSharingFinish() throws WxPayException {
    ProfitSharingUnfreezeRequest request = ProfitSharingUnfreezeRequest
      .newBuilder()
      .outOrderNo("20191023103251431856285")
      .transactionId("4200000441201910238267278073")
      .description("分账完成")
      .build();
    log.info(this.payService.getProfitSharingService().profitSharingFinish(request).toString());
  }

  @Test
  public void testAddReceiver() throws WxPayException {
    Receiver receiver = new Receiver(WxPayConstants.ReceiverType.PERSONAL_OPENID,
      "oyOUE5ql4TtzrBg5cVOwxq6tbjOs",
      "***",
      "STORE_OWNER",
      null);
    ProfitSharingReceiverRequest request = ProfitSharingReceiverRequest
      .newBuilder()
      .receiver(receiver.toJSONString())
      .build();
    log.info(this.payService.getProfitSharingService().addReceiver(request).toString());
  }

  @Test
  public void testRemoveReceiver() throws WxPayException {
    Receiver receiver = new Receiver(WxPayConstants.ReceiverType.PERSONAL_OPENID,
      "oyOUE5ql4TtzrBg5cVOwxq6tbjOs");
    ProfitSharingReceiverRequest request = ProfitSharingReceiverRequest
      .newBuilder()
      .receiver(receiver.toJSONString())
      .build();
    log.info(this.payService.getProfitSharingService().removeReceiver(request).toString());
  }

  @Test
  public void testProfitSharingQuery() throws WxPayException {
    ProfitSharingQueryRequest request = ProfitSharingQueryRequest
      .newBuilder()
      .outOrderNo("20191023112023031060677")
      .transactionId("4200000431201910234736634272")
      .build();
    ProfitSharingQueryResult result = this.payService.getProfitSharingService().profitSharingQuery(request);
    log.info(result.formatReceivers().toString());
    log.info(result.toString());
  }

  @Test
  public void testProfitSharingMerchantRatioQuery() throws WxPayException {
    final String subMchId = "subMchid";
    final ProfitSharingMerchantRatioQueryRequest request = new ProfitSharingMerchantRatioQueryRequest(subMchId);
    final ProfitSharingMerchantRatioQueryResult result = payService.getProfitSharingService().profitSharingMerchantRatioQuery(request);
    log.info(result.toString());
  }

  @Test
  public void testProfitSharingOrderAmountQuery() throws WxPayException {
    final String transactionId = "4200000916202012281633853127";
    final ProfitSharingOrderAmountQueryRequest request = ProfitSharingOrderAmountQueryRequest.newBuilder()
      .transactionId(transactionId)
      .build();
    final ProfitSharingOrderAmountQueryResult result = payService.getProfitSharingService().profitSharingOrderAmountQuery(request);
    log.info(result.toString());
  }

  @Test
  public void testProfitSharingReturn() throws WxPayException {
    ProfitSharingReturnRequest request = ProfitSharingReturnRequest
      .newBuilder()
      .outOrderNo("20191023154723316420060")
      .outReturnNo("R2019102315")
      .returnAccountType("MERCHANT_ID")
      .returnAccount("86693852")
      .returnAmount(2)
      .description("用户退款")
      .build();
    log.info(this.payService.getProfitSharingService().profitSharingReturn(request).toString());
  }

  @Test
  public void testProfitSharingReturnQuery() throws WxPayException {
    ProfitSharingReturnQueryRequest request = ProfitSharingReturnQueryRequest
      .newBuilder()
      .outOrderNo("20191023154723316420060")
      .outReturnNo("R2019102315")
      .build();
    log.info(this.payService.getProfitSharingService().profitSharingReturnQuery(request).toString());
  }

  @Test
  public void testProfitSharingNotifyData() throws WxPayException {
    SignatureHeader header = new SignatureHeader();
    header.setSerial("Wechatpay-Serial");
    header.setTimeStamp("Wechatpay-Timestamp");
    header.setNonce("Wechatpay-Nonce");
    header.setSignature("Wechatpay-Signature");
    String data = "body";
    log.info(this.payService.getProfitSharingService().parseProfitSharingNotifyResult(data,header).toString());
  }

}
