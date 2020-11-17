package com.github.binarywang.wxpay.bean.request;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-06-07
 */
public class WxPayRefundRequestTest {

  @Test
  public void testToXML() {
    WxPayRefundRequest refundRequest = new WxPayRefundRequest();
    refundRequest.setAppid("wx2421b1c4370ec43b");
    refundRequest.setMchId("10000100");
    refundRequest.setNonceStr("6cefdb308e1e2e8aabd48cf79e546a02");
    refundRequest.setNotifyUrl("https://weixin.qq.com/");
    refundRequest.setOutRefundNo("1415701182");
    refundRequest.setOutTradeNo("1415757673");
    refundRequest.setRefundFee(1);
    refundRequest.setTotalFee(1);
    refundRequest.setTransactionId("");
    refundRequest.setDetail("{\"goods_detail\":[{\"goods_id\":\"商品编码\",\"wxpay_goods_id\":\"1001\",\"goods_name\":\"iPhone6s\n" +
      "16G\",\"refund_amount\":528800,\"refund_quantity\":1,\"price\":528800},{\"goods_id\":\"商品编码\",\"wxpay_goods_id\":\"1001\",\"goods_name\":\"iPhone6s\n" +
      "16G\",\"refund_amount\"\":528800,\"refund_quantity\":1,\"price\":608800}]}");
    refundRequest.setSign("FE56DD4AA85C0EECA82C35595A69E153");

    assertThat(refundRequest.toXML())
      .isEqualTo("<xml>\n" +
        "  <appid>wx2421b1c4370ec43b</appid>\n" +
        "  <mch_id>10000100</mch_id>\n" +
        "  <nonce_str>6cefdb308e1e2e8aabd48cf79e546a02</nonce_str>\n" +
        "  <sign>FE56DD4AA85C0EECA82C35595A69E153</sign>\n" +
        "  <transaction_id></transaction_id>\n" +
        "  <out_trade_no>1415757673</out_trade_no>\n" +
        "  <out_refund_no>1415701182</out_refund_no>\n" +
        "  <total_fee>1</total_fee>\n" +
        "  <refund_fee>1</refund_fee>\n" +
        "  <notify_url>https://weixin.qq.com/</notify_url>\n" +
        "  <detail><![CDATA[{\"goods_detail\":[{\"goods_id\":\"商品编码\",\"wxpay_goods_id\":\"1001\",\"goods_name\":\"iPhone6s\n" +
        "16G\",\"refund_amount\":528800,\"refund_quantity\":1,\"price\":528800},{\"goods_id\":\"商品编码\",\"wxpay_goods_id\":\"1001\",\"goods_name\":\"iPhone6s\n" +
        "16G\",\"refund_amount\"\":528800,\"refund_quantity\":1,\"price\":608800}]}]]></detail>\n" +
        "</xml>");
  }
}
