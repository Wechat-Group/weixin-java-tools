package me.chanjar.weixin.mp.bean.message;

import org.testng.*;
import org.testng.annotations.*;

@Test
public class WxMpXmlOutVideoMessageTest {

  public void test() {
    WxMpXmlOutVideoMessage m = new WxMpXmlOutVideoMessage();
    m.setMediaId("media_id");
    m.setTitle("title");
    m.setDescription("ddfff");
    m.setCreateTime(1122l);
    m.setFromUserName("fromUser");
    m.setToUserName("toUser");

    String expected = "<xml>"
      + "<ToUserName><![CDATA[toUser]]></ToUserName>"
      + "<FromUserName><![CDATA[fromUser]]></FromUserName>"
      + "<CreateTime>1122</CreateTime>"
      + "<MsgType><![CDATA[video]]></MsgType>"
      + "<Video>"
      + "<MediaId><![CDATA[media_id]]></MediaId>"
      + "<Title><![CDATA[title]]></Title>"
      + "<Description><![CDATA[ddfff]]></Description>"
      + "</Video> "
      + "</xml>";
    System.out.println(m.toXml());
    Assert.assertEquals(m.toXml().replaceAll("\\s", ""), expected.replaceAll("\\s", ""));
  }

  public void testBuild() {
    WxMpXmlOutVideoMessage m = WxMpXmlOutMessage.VIDEO()
      .mediaId("media_id")
      .fromUser("fromUser")
      .toUser("toUser")
      .title("title")
      .description("ddfff")
      .build();
    String expected = "<xml>"
      + "<ToUserName><![CDATA[toUser]]></ToUserName>"
      + "<FromUserName><![CDATA[fromUser]]></FromUserName>"
      + "<CreateTime>1122</CreateTime>"
      + "<MsgType><![CDATA[video]]></MsgType>"
      + "<Video>"
      + "<MediaId><![CDATA[media_id]]></MediaId>"
      + "<Title><![CDATA[title]]></Title>"
      + "<Description><![CDATA[ddfff]]></Description>"
      + "</Video> "
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
