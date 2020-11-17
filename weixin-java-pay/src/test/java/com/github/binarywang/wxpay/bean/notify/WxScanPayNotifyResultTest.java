package com.github.binarywang.wxpay.bean.notify;

import com.github.binarywang.wxpay.util.XmlConfig;
import org.testng.annotations.*;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 *
 * Created by Binary Wang on 2018/2/2.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxScanPayNotifyResultTest {

  /**
   * Test to map.
   */
  @Test
  public void testToMap() {
  }

  /**
   * Test from xml.
   */
  @Test
  public void testFromXML() {
    String xmlString = "<xml>\n" +
      "  <appid><![CDATA[wx8888888888888888]]></appid>\n" +
      "  <openid><![CDATA[o8GeHuLAsgefS_80exEr1cTqekUs]]></openid>\n" +
      "  <mch_id><![CDATA[1900000109]]></mch_id>\n" +
      "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
      "  <nonce_str><![CDATA[5K8264ILTKCH16CQ2502SI8ZNMTM67VS]]></nonce_str>\n" +
      "  <product_id><![CDATA[88888]]></product_id>\n" +
      "  <sign><![CDATA[C380BEC2BFD727A4B6845133519F3AD6]]></sign>\n" +
      "</xml>";

    WxScanPayNotifyResult result = BaseWxPayResult.fromXML(xmlString, WxScanPayNotifyResult.class);

    assertThat(result).isNotNull();

    assertThat(result.getAppid()).isEqualTo("wx8888888888888888");
    assertThat(result.getOpenid()).isEqualTo("o8GeHuLAsgefS_80exEr1cTqekUs");
    assertThat(result.getMchId()).isEqualTo("1900000109");
    assertThat(result.getNonceStr()).isEqualTo("5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
    assertThat(result.getProductId()).isEqualTo("88888");
    assertThat(result.getSign()).isEqualTo("C380BEC2BFD727A4B6845133519F3AD6");
  }


  /**
   * Test from xml.
   * fast mode.
   */
  @Test
  public void testFromXMLFastMode() {
    String xmlString = "<xml>\n" +
      "  <appid><![CDATA[wx8888888888888888]]></appid>\n" +
      "  <openid><![CDATA[o8GeHuLAsgefS_80exEr1cTqekUs]]></openid>\n" +
      "  <mch_id><![CDATA[1900000109]]></mch_id>\n" +
      "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
      "  <nonce_str><![CDATA[5K8264ILTKCH16CQ2502SI8ZNMTM67VS]]></nonce_str>\n" +
      "  <product_id><![CDATA[88888]]></product_id>\n" +
      "  <sign><![CDATA[C380BEC2BFD727A4B6845133519F3AD6]]></sign>\n" +
      "</xml>";

    XmlConfig.fastMode = true;
    try {
      WxScanPayNotifyResult result = BaseWxPayResult.fromXML(xmlString, WxScanPayNotifyResult.class);

      assertThat(result).isNotNull();

      assertThat(result.getAppid()).isEqualTo("wx8888888888888888");
      assertThat(result.getOpenid()).isEqualTo("o8GeHuLAsgefS_80exEr1cTqekUs");
      assertThat(result.getMchId()).isEqualTo("1900000109");
      assertThat(result.getNonceStr()).isEqualTo("5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
      assertThat(result.getProductId()).isEqualTo("88888");
      assertThat(result.getSign()).isEqualTo("C380BEC2BFD727A4B6845133519F3AD6");
    } finally {
      XmlConfig.fastMode = false;
    }
  }

}
