package com.github.binarywang.wxpay.bean.result;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 *  Created by BinaryWang on 2018/4/22.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxPayRefundResultTest {

  @Test
  public void testComposeRefundCoupons() {
    /*
      该xml字符串来自于官方文档示例，稍加改造，加上代金卷
      refund_channel 是个什么鬼，官方文档只字不提
     */
    String xmlString = "<xml>\n" +
      "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
      "   <return_msg><![CDATA[OK]]></return_msg>\n" +
      "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
      "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
      "   <nonce_str><![CDATA[NfsMFbUFpdbEhPXP]]></nonce_str>\n" +
      "   <sign><![CDATA[B7274EB9F8925EB93100DD2085FA56C0]]></sign>\n" +
      "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
      "   <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>\n" +
      "   <out_trade_no><![CDATA[1415757673]]></out_trade_no>\n" +
      "   <out_refund_no><![CDATA[1415701182]]></out_refund_no>\n" +
      "   <refund_id><![CDATA[2008450740201411110000174436]]></refund_id>\n" +
      "   <refund_channel><![CDATA[]]></refund_channel>\n" +
      "   <coupon_refund_fee>1</coupon_refund_fee>\n" +
      "   <coupon_refund_count>1</coupon_refund_count>\n" +
      "   <coupon_refund_id_0>123</coupon_refund_id_0>\n" +
      "   <coupon_refund_fee_0>1</coupon_refund_fee_0>\n" +
      "   <coupon_type_0><![CDATA[CASH]]></coupon_type_0>\n" +
      "   <refund_fee>2</refund_fee> \n" +
      "</xml>";

    WxPayRefundResult result = WxPayRefundResult.fromXML(xmlString, WxPayRefundResult.class);
    result.composeRefundCoupons();

    assertThat(result.getRefundCoupons()).isNotEmpty();
    assertThat(result.getRefundCoupons().get(0).getCouponRefundId()).isEqualTo("123");
    assertThat(result.getRefundCoupons().get(0).getCouponType()).isEqualTo("CASH");
    assertThat(result.getRefundCoupons().get(0).getCouponRefundFee()).isEqualTo(1);
  }
}
