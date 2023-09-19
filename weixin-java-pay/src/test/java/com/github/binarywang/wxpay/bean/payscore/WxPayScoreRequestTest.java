package com.github.binarywang.wxpay.bean.payscore;

import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2020-07-11
 */
public class WxPayScoreRequestTest {
  @Test
  public void testToJson() {
    WxPayScoreRequest request = WxPayScoreRequest.builder()
      .outOrderNo("QLS202005201058000201")
      .appid("123")
      .serviceId("345")
      .serviceIntroduction("租借服务")
      .timeRange(new TimeRange("20230901011023", "20230930235959","开始时间","结束时间"))
      .build();
    System.out.println(request.toJson());
    /*    {
      "out_order_no":"QLS202005201058000201",
      "appid":"123",
      "service_id":"345",
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
  }
}
