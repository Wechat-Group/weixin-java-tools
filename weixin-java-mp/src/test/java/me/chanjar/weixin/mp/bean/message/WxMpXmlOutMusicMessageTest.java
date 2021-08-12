package me.chanjar.weixin.mp.bean.message;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WxMpXmlOutMusicMessageTest {

  public void test() {
    WxMpXmlOutMusicMessage m = new WxMpXmlOutMusicMessage();
    m.setTitle("title");
    m.setDescription("ddfff");
    m.setHqMusicUrl("hQMusicUrl");
    m.setMusicUrl("musicUrl");
    m.setThumbMediaId("thumbMediaId");
    m.setCreateTime(1122L);
    m.setFromUserName("fromUser");
    m.setToUserName("toUser");

    String expected = "<xml>"
      + "<ToUserName><![CDATA[toUser]]></ToUserName>"
      + "<FromUserName><![CDATA[fromUser]]></FromUserName>"
      + "<CreateTime>1122</CreateTime>"
      + "<MsgType><![CDATA[music]]></MsgType>"
      + "<Music>"
      + "        <Title><![CDATA[title]]></Title>"
      + "        <Description><![CDATA[ddfff]]></Description>"
      + "        <ThumbMediaId><![CDATA[thumbMediaId]]></ThumbMediaId>"
      + "        <MusicUrl><![CDATA[musicUrl]]></MusicUrl>"
      + "        <HQMusicUrl><![CDATA[hQMusicUrl]]></HQMusicUrl>"
      + "    </Music>"
      + "</xml>";
    System.out.println(m.toXml());
    Assert.assertEquals(m.toXml().replaceAll("\\s", ""), expected.replaceAll("\\s", ""));
  }

  public void testBuild() {
    WxMpXmlOutMusicMessage m = WxMpXmlOutMessage.MUSIC()
      .fromUser("fromUser")
      .toUser("toUser")
      .title("title")
      .description("ddfff")
      .hqMusicUrl("hQMusicUrl")
      .musicUrl("musicUrl")
      .thumbMediaId("thumbMediaId")
      .build();
    String expected = "<xml>"
      + "<ToUserName><![CDATA[toUser]]></ToUserName>"
      + "<FromUserName><![CDATA[fromUser]]></FromUserName>"
      + "<CreateTime>1122</CreateTime>"
      + "<MsgType><![CDATA[music]]></MsgType>"
      + "<Music>"
      + "        <Title><![CDATA[title]]></Title>"
      + "        <Description><![CDATA[ddfff]]></Description>"
      + "        <ThumbMediaId><![CDATA[thumbMediaId]]></ThumbMediaId>"
      + "        <MusicUrl><![CDATA[musicUrl]]></MusicUrl>"
      + "        <HQMusicUrl><![CDATA[hQMusicUrl]]></HQMusicUrl>"
      + "    </Music>"
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
