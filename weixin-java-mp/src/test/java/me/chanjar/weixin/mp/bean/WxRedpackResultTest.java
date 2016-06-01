package me.chanjar.weixin.mp.bean;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import me.chanjar.weixin.mp.bean.result.WxRedpackResult;

public class WxRedpackResultTest {

  private XStream xstream;

  @Before
  public void setup() {
     xstream = XStreamInitializer.getInstance();
     xstream.processAnnotations(WxRedpackResult.class);
  }
  
  @Test public void loadSuccessResult() {
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
    WxRedpackResult wxMpRedpackResult = (WxRedpackResult) xstream.fromXML(successSample);
    assertEquals("SUCCESS", wxMpRedpackResult.getReturnCode());
    assertEquals("SUCCESS", wxMpRedpackResult.getResultCode());
    assertEquals("20150520102602", wxMpRedpackResult.getSendTime());
  }
  
  @Test public void loadFailureResult() {
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
    WxRedpackResult wxMpRedpackResult = (WxRedpackResult) xstream.fromXML(failureSample);
    assertEquals("FAIL", wxMpRedpackResult.getReturnCode());
    assertEquals("FAIL", wxMpRedpackResult.getResultCode());
    assertEquals("onqOjjmM1tad-3ROpncN-yUfa6uI", wxMpRedpackResult.getReOpenid());
    assertEquals(1, wxMpRedpackResult.getTotalAmount());
  }
}
