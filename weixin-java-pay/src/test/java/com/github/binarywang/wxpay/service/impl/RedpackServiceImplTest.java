package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.request.WxPaySendMiniProgramRedpackRequest;
import com.github.binarywang.wxpay.bean.request.WxPaySendRedpackRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRedpackQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendMiniProgramRedpackResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.github.binarywang.wxpay.testbase.XmlWxPayConfig;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-12-26
 */
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class RedpackServiceImplTest {
  @Inject
  private WxPayService payService;

  @Test
  public void testSendRedpack() throws Exception {
    WxPaySendRedpackRequest request = new WxPaySendRedpackRequest();
    request.setActName("abc");
    request.setClientIp("aaa");
    request.setMchBillNo("aaaa");
    request.setWishing("what");
    request.setSendName("111");
    request.setTotalAmount(1);
    request.setTotalNum(1);
    request.setReOpenid(((XmlWxPayConfig) this.payService.getConfig()).getOpenid());
    WxPaySendRedpackResult redpackResult = this.payService.getRedpackService().sendRedpack(request);
    log.info(redpackResult.toString());
  }

  @Test
  public void testQueryRedpack() throws Exception {
    WxPayRedpackQueryResult redpackResult = this.payService.getRedpackService().queryRedpack("aaaa");
    log.info(redpackResult.toString());
  }

  @Test
  public void testSendMiniProgramRedpack() throws WxPayException {
    final WxPaySendMiniProgramRedpackResult result = this.payService.getRedpackService()
      .sendMiniProgramRedpack(new WxPaySendMiniProgramRedpackRequest()
        .setReOpenid("ojOQA0y9o-Eb6Aep7uVTdbkJqrP4")
        .setWishing("haha")
        .setMchBillNo("123")
        .setActName("11")
        .setSendName("111")
        .setTotalAmount(1));
    System.out.println(result);
  }
}
