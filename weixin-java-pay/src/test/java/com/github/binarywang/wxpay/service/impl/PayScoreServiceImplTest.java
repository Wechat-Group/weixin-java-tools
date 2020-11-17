package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.payscore.WxPayScoreRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

/**
 * 测试代码，待补充完善.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-05-19
 */
@Test
@Guice(modules = ApiTestModule.class)
public class PayScoreServiceImplTest {
  @Inject
  private WxPayService payService;

  @Test

  public void testCreateServiceOrder() throws WxPayException {
    //测试数据
/*    {
      "out_order_no":"QLS202005201058000201",
      "appid":"",
      "service_id":"",
      "service_introduction":"租借服务",
      "time_range":{
      "start_time":"OnAccept",
        "end_time":"20200520225840"
    },
      "location":{
      "start_location":"山",
        "end_location":"山"
    },
      "risk_fund":{
      "name":"DEPOSIT",
        "amount":200,
        "description":"丢失偿还费用2元/台"
    },
      "attach":"",
      "notify_url":"/pay/notify/payScore",
      "openid":"",
      "need_user_confirm":true,
      "profit_sharing":false,
      "post_payments":[
      {
        "name":"租借服务",
        "amount":100,
        "description":"服务费：1元/台",
        "count":1
      }
    ],
      "total_amount":0
    }*/

    this.payService.getPayScoreService().createServiceOrder(WxPayScoreRequest.builder().build());
  }

  @Test
  public void testQueryServiceOrder() throws WxPayException {
    //两个参数选填一个
    this.payService.getPayScoreService().queryServiceOrder("11", "");
  }

  @Test
  public void testCancelServiceOrder() throws WxPayException {
    this.payService.getPayScoreService().cancelServiceOrder("11", "测试取消");
  }

  @Test
  public void testModifyServiceOrder() {
  }

  @Test
  public void testCompleteServiceOrder() throws WxPayException {
/*    {
      "appid":"",
      "service_id":"",
      "time_range":{
      "end_time":"20200520111702"
    },
      "need_user_confirm":false,
      "profit_sharing":false,
      "post_payments":[
      {
        "name":"租借服务",
        "amount":100,
        "description":"服务费：1.0000元/台",
        "count":1
      }
    ],
      "total_amount":100
    }
*/
    this.payService.getPayScoreService().completeServiceOrder(WxPayScoreRequest.builder().build());
  }

  @Test
  public void testPayServiceOrder() {
  }

  @Test
  public void testSyncServiceOrder() {
  }

  @Test
  public void testDecryptNotifyData() {
  }
}
