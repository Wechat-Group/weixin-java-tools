package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.profitsharing.ProfitsharingRequest;
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
      100,
      "分到用户"));
    ProfitsharingRequest request = ProfitsharingRequest
      .newBuilder()
      .outOrderNo("P20150806125346")
      .transactionId("4208450740201411110007820472")
//      .receivers("[{\"type\": \"PERSONAL_OPENID\",\"account\":\"oyOUE5ql4TtzrBg5cVOwxq6tbjOs\",\"amount\":100,\"description\": \"分到用户\"}]")
      .receivers(instance.toJSONString())
      .build();
    this.logger.info(this.payService.getProfitSharingService().profitsharing(request).toString());
  }
}
