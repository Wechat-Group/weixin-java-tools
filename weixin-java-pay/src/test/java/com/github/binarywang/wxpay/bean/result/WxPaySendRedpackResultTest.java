package com.github.binarywang.wxpay.bean.result;

import org.testng.*;
import org.testng.annotations.*;

import com.thoughtworks.xstream.XStream;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;

/**
 * The type Wx pay send redpack result test.
 */
public class WxPaySendRedpackResultTest {

  private XStream xstream;

  /**
   * Sets .
   */
  @BeforeTest
  public void setup() {
    this.xstream = XStreamInitializer.getInstance();
    this.xstream.processAnnotations(WxPaySendRedpackResult.class);
  }

  /**
   * Load success result.
   */
  @Test
  public void loadSuccessResult() {
    final String successSample = "<xml>\n" +
      "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
      "<return_msg><![CDATA[发放成功.]]></return_msg>\n" +
      "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
      "<err_code><![CDATA[0]]></err_code>\n" +
      "<err_code_des><![CDATA[发放成功.]]></err_code_des>\n" +
      "<mch_billno><![CDATA[0010010404201411170000046545]]></mch_billno>\n" +
      "<mch_id>10010404</mch_id>\n" +
      "<wxappid><![CDATA[wx6fa7e3bab7e15415]]></wxappid>\n" +
      "<re_openid><![CDATA[onqOjjmM1tad-3ROpncN-yUfa6uI]]></re_openid>\n" +
      "<total_amount>1</total_amount>\n" +
      "<send_listid>100000000020150520314766074200</send_listid>\n" +
      "<send_time>20150520102602</send_time>\n" +
      "</xml>";
    WxPaySendRedpackResult wxMpRedpackResult = (WxPaySendRedpackResult) this.xstream.fromXML(successSample);
    Assert.assertEquals("SUCCESS", wxMpRedpackResult.getReturnCode());
    Assert.assertEquals("SUCCESS", wxMpRedpackResult.getResultCode());
    Assert.assertEquals("20150520102602", wxMpRedpackResult.getSendTime());
  }

  /**
   * Load failure result.
   */
  @Test
  public void loadFailureResult() {
    final String failureSample = "<xml>\n" +
      "<return_code><![CDATA[FAIL]]></return_code>\n" +
      "<return_msg><![CDATA[系统繁忙,请稍后再试.]]></return_msg>\n" +
      "<result_code><![CDATA[FAIL]]></result_code>\n" +
      "<err_code><![CDATA[268458547]]></err_code>\n" +
      "<err_code_des><![CDATA[系统繁忙,请稍后再试.]]></err_code_des>\n" +
      "<mch_billno><![CDATA[0010010404201411170000046542]]></mch_billno>\n" +
      "<mch_id>10010404</mch_id>\n" +
      "<wxappid><![CDATA[wx6fa7e3bab7e15415]]></wxappid>\n" +
      "<re_openid><![CDATA[onqOjjmM1tad-3ROpncN-yUfa6uI]]></re_openid>\n" +
      "<total_amount>1</total_amount>\n" +
      "</xml>";
    WxPaySendRedpackResult wxMpRedpackResult = (WxPaySendRedpackResult) this.xstream.fromXML(failureSample);
    Assert.assertEquals("FAIL", wxMpRedpackResult.getReturnCode());
    Assert.assertEquals("FAIL", wxMpRedpackResult.getResultCode());
    Assert.assertEquals("onqOjjmM1tad-3ROpncN-yUfa6uI", wxMpRedpackResult.getReOpenid());
    Assert.assertEquals(1, wxMpRedpackResult.getTotalAmount());
  }
}
