package com.github.binarywang.wxpay.bean.result;

import org.testng.*;
import org.testng.annotations.*;

/**
 * <pre>
 * Created by Binary Wang on 2017-01-04.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
public class WxPayOrderQueryResultTest {
  @Test
  public void testComposeCoupons() throws Exception {
    /**
     * xml样例字符串来自于官方文档，并稍加改造加入了coupon相关的数据便于测试
     */
    String xmlString = "<xml>\n" +
      "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
      "   <return_msg><![CDATA[OK]]></return_msg>\n" +
      "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
      "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
      "   <device_info><![CDATA[1000]]></device_info>\n" +
      "   <nonce_str><![CDATA[TN55wO9Pba5yENl8]]></nonce_str>\n" +
      "   <sign><![CDATA[BDF0099C15FF7BC6B1585FBB110AB635]]></sign>\n" +
      "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
      "   <openid><![CDATA[oUpF8uN95-Ptaags6E_roPHg7AG0]]></openid>\n" +
      "   <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
      "   <trade_type><![CDATA[MICROPAY]]></trade_type>\n" +
      "   <bank_type><![CDATA[CCB_DEBIT]]></bank_type>\n" +
      "   <total_fee>1</total_fee>\n" +
      "   <fee_type><![CDATA[CNY]]></fee_type>\n" +
      "   <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>\n" +
      "   <out_trade_no><![CDATA[1415757673]]></out_trade_no>\n" +
      "   <attach><![CDATA[订单额外描述]]></attach>\n" +
      "   <time_end><![CDATA[20141111170043]]></time_end>\n" +
      "   <trade_state><![CDATA[SUCCESS]]></trade_state>\n" +
      "   <coupon_count>2</coupon_count>\n" +
      "   <coupon_type_0><![CDATA[CASH]]></coupon_type_0>\n" +
      "   <coupon_id_0>10000</coupon_id_0>\n" +
      "   <coupon_fee_0>100</coupon_fee_0>\n" +
      "   <coupon_type_1><![CDATA[NO_CASH]]></coupon_type_1>\n" +
      "   <coupon_id_1>10001</coupon_id_1>\n" +
      "   <coupon_fee_1>200</coupon_fee_1>\n" +
      "</xml>";

    WxPayOrderQueryResult orderQueryResult = WxPayOrderQueryResult.fromXML(xmlString, WxPayOrderQueryResult.class);
    orderQueryResult.composeCoupons();

    Assert.assertEquals(orderQueryResult.getCouponCount().intValue(), 2);
    Assert.assertNotNull(orderQueryResult.getCoupons());
    Assert.assertEquals(orderQueryResult.getCoupons().size(), 2);

    Assert.assertEquals(orderQueryResult.getCoupons().get(0).getCouponFee().intValue(), 100);
    Assert.assertEquals(orderQueryResult.getCoupons().get(1).getCouponFee().intValue(), 200);

    Assert.assertEquals(orderQueryResult.getCoupons().get(0).getCouponType(), "CASH");
    Assert.assertEquals(orderQueryResult.getCoupons().get(1).getCouponType(), "NO_CASH");

    Assert.assertEquals(orderQueryResult.getCoupons().get(0).getCouponId(), "10000");
    Assert.assertEquals(orderQueryResult.getCoupons().get(1).getCouponId(), "10001");

  }

}
