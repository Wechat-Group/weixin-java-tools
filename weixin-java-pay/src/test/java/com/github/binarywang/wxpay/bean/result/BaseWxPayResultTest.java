package com.github.binarywang.wxpay.bean.result;

import org.testng.*;
import org.testng.annotations.*;

import java.util.Map;

/**
 * <pre>
 * Created by Binary Wang on 2017-01-04.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
public class BaseWxPayResultTest {

  @Test
  public void testGetXmlValue() throws Exception {
  }

  @Test
  public void testXml2Doc() throws Exception {
  }

  @Test
  public void testToMap() throws Exception {
    WxPayOrderQueryResult result = new WxPayOrderQueryResult();
    result.setXmlString("<xml>\n" +
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
      "</xml>");
    Map<String, String> map = result.toMap();
    System.out.println(map);

    Assert.assertEquals(map.get("return_code"), "SUCCESS");
    Assert.assertEquals(map.get("attach"), "订单额外描述");

  }

  @Test(expectedExceptions = {RuntimeException.class})
  public void testToMap_with_empty_xmlString() {
    WxPayOrderQueryResult result = new WxPayOrderQueryResult();
    result.setXmlString(" ");
    Map<String, String> map = result.toMap();
    System.out.println(map);
  }

}
