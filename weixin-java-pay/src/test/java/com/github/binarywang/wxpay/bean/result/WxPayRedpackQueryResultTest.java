package com.github.binarywang.wxpay.bean.result;

import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 *
 * Created by Binary Wang on 2018/1/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxPayRedpackQueryResultTest {
  /**
   * Test from xml.
   */
  @Test
  public void testFromXML() {
    String xmlString = "<xml>\n" +
      "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
      "<return_msg><![CDATA[OK]]></return_msg>\n" +
      "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
      "<err_code><![CDATA[SUCCESS]]></err_code>\n" +
      "<err_code_des><![CDATA[OK]]></err_code_des>\n" +
      "<mch_billno><![CDATA[1473919402201801230145075410]]></mch_billno>\n" +
      "<mch_id><![CDATA[1497236182]]></mch_id>\n" +
      "<detail_id><![CDATA[1000041701201801233000139830103]]></detail_id>\n" +
      "<status><![CDATA[RECEIVED]]></status>\n" +
      "<send_type><![CDATA[API]]></send_type>\n" +
      "<hb_type><![CDATA[NORMAL]]></hb_type>\n" +
      "<total_num>1</total_num>\n" +
      "<total_amount>100</total_amount>\n" +
      "<send_time><![CDATA[2018-01-23 13:45:08]]></send_time>\n" +
      "<hblist>\n" +
      "<hbinfo>\n" +
      "<openid><![CDATA[o3yHF0uHuckI3yE6lwWiFQBQdVDI]]></openid>\n" +
      "<amount>100</amount>\n" +
      "<rcv_time><![CDATA[2018-01-23 13:45:31]]></rcv_time>\n" +
      "</hbinfo>\n" +
      "</hblist>\n" +
      "</xml>";

    WxPayRedpackQueryResult orderQueryResult = WxPayRedpackQueryResult.fromXML(xmlString, WxPayRedpackQueryResult.class);
    System.out.println(orderQueryResult);
    assertThat(orderQueryResult).isNotNull();

    assertThat(orderQueryResult.getRedpackList()).isNotEmpty();
    assertThat(orderQueryResult.getRedpackList().get(0).getAmount()).isEqualTo(100);
    assertThat(orderQueryResult.getRedpackList().get(0).getOpenid()).isEqualTo("o3yHF0uHuckI3yE6lwWiFQBQdVDI");
    assertThat(orderQueryResult.getRedpackList().get(0).getReceiveTime()).isEqualTo("2018-01-23 13:45:31");
  }
}
