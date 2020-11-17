package com.github.binarywang.wxpay.bean.notify;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * WxPayNotifyResponse 测试.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-06-30
 */
public class WxPayNotifyResponseTest {

  @Test
  public void testSuccess() {
    final String result = WxPayNotifyResponse.success("OK");
    assertThat(result).isEqualTo("<xml>" +
      "<return_code><![CDATA[SUCCESS]]></return_code>" +
      "<return_msg><![CDATA[OK]]></return_msg>" +
      "</xml>");
  }

  @Test
  public void testSuccessResp() {
    final String result = WxPayNotifyResponse.successResp("OK");
    assertThat(result).isEqualTo("<xml>" +
      "<return_code><![CDATA[SUCCESS]]></return_code>" +
      "<return_msg><![CDATA[OK]]></return_msg>" +
      "</xml>");
  }

  @Test
  public void testFailResp() {
    final String result = WxPayNotifyResponse.failResp("500");
    assertThat(result).isEqualTo("<xml>" +
      "<return_code><![CDATA[FAIL]]></return_code>" +
      "<return_msg><![CDATA[500]]></return_msg>" +
      "</xml>");
  }
}
