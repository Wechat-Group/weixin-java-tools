package com.binarywang.spring.demo.wxjava.cp.test;

import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.xml.XmlUtil;
import me.chanjar.weixin.cp.bean.WxCpTpXmlPackage;
import org.junit.Test;

/**
 * @author caiqy
 */
public class TestCheck {

  @Test
  public void testCheck() {
    String msg_signature = "0364c44a4988644e843205660a969963994e58f9";
    String nonce = "1628640452";
    String timestamp = "1628758028";
    String body = "<xml><ToUserName><![CDATA[ww530a1c681ffac763]]></ToUserName><Encrypt><![CDATA[rwifXwhiyPjsSTK13BzDJ5amfFqVY90xwSR7zxbzdk66veb9nx/66/k53apz+gI4dfTBal4X+rtp/BpQ9JcuySMXmTEmn98TdQlmlmfc3WUTXZtDt0L5LULFOa1xb0/CjpR/Bz41JRBtKqDsqfwjHP63GbReLr7cRN3T8v+gGiMIKphCAE9qEnsZe2NpI2mw+ATeuiIGBg3q2fhUU4u3ccM647jkLdSQvxyl5WvWMRZ+zHeWcB+EFivpdTJbJ847J2OczochfRZRGB9p3Aare2tRXzJpfnGWiUbaAawC+tcstP9d20Xyx9XqfJqy2FAlysDu7uGrqBCM7GIp36sRnIA0b7oQRByW4iOisqlfMED9U+T8ox3fbMeYtQfmKpHX]]></Encrypt><AgentID><![CDATA[]]></AgentID></xml>";

    WxCpTpXmlPackage wxCpTpXmlMessage = XmlUtil.toObject(body, WxCpTpXmlPackage.class);
    System.out.println(SHA1.gen("KUc6rUB2", timestamp, nonce, wxCpTpXmlMessage.getMsgEncrypt())
      .equals(msg_signature));
  }


}
