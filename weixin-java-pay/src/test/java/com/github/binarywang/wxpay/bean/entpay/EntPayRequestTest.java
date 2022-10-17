package com.github.binarywang.wxpay.bean.entpay;

import org.testng.annotations.Test;

/**
 * .
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2019-08-18
 */
public class EntPayRequestTest {

  @Test
  public void testToString() {
    System.out.println(EntPayRequest.newBuilder().mchId("123").build().toString());
  }
}
