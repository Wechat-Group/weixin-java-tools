package me.chanjar.weixin.mp.bean.message;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WxMpXmlOutVoiceMessageTest {

  public void test() {
    WxMpXmlOutVoiceMessage m = new WxMpXmlOutVoiceMessage();
    m.setMediaId("ddfefesfsdfef");
    m.setCreateTime(1122L);
    m.setFromUserName("from");
    m.setToUserName("to");

    String expected = "<xml>"
      + "<ToUserName><![CDATA[to]]></ToUserName>"
      + "<FromUserName><![CDATA[from]]></FromUserName>"
      + "<CreateTime>1122</CreateTime>"
      + "<MsgType><![CDATA[voice]]></MsgType>"
      + "<Voice><MediaId><![CDATA[ddfefesfsdfef]]></MediaId></Voice>"
      + "</xml>";
    System.out.println(m.toXml());
    Assert.assertEquals(m.toXml().replaceAll("\\s", ""), expected.replaceAll("\\s", ""));
  }

  public void testBuild() {
    WxMpXmlOutVoiceMessage m = WxMpXmlOutMessage.VOICE().mediaId("ddfefesfsdfef").fromUser("from").toUser("to").build();
    String expected = "<xml>"
      + "<ToUserName><![CDATA[to]]></ToUserName>"
      + "<FromUserName><![CDATA[from]]></FromUserName>"
      + "<CreateTime>1122</CreateTime>"
      + "<MsgType><![CDATA[voice]]></MsgType>"
      + "<Voice><MediaId><![CDATA[ddfefesfsdfef]]></MediaId></Voice>"
      + "</xml>";
    System.out.println(m.toXml());
    Assert.assertEquals(
      m
        .toXml()
        .replaceAll("\\s", "")
        .replaceAll("<CreateTime>.*?</CreateTime>", ""),
      expected
        .replaceAll("\\s", "")
        .replaceAll("<CreateTime>.*?</CreateTime>", "")
    );
  }

}
