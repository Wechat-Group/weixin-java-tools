package me.chanjar.weixin.cp.bean;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WxCpXmlOutNewsMessageTest {

  public void test() {
    WxCpXmlOutNewsMessage m = new WxCpXmlOutNewsMessage();
    m.setCreateTime(1122L);
    m.setFromUserName("fromUser");
    m.setToUserName("toUser");

    WxCpXmlOutNewsMessage.Item item = new WxCpXmlOutNewsMessage.Item();
    item.setDescription("description");
    item.setPicUrl("picUrl");
    item.setTitle("title");
    item.setUrl("url");
    m.addArticle(item);
    m.addArticle(item);

    String expected = "<xml>"
      + "<ToUserName><![CDATA[toUser]]></ToUserName>"
      + "<FromUserName><![CDATA[fromUser]]></FromUserName>"
      + "<CreateTime>1122</CreateTime>"
      + "<MsgType><![CDATA[news]]></MsgType>"
      + "    <Articles>"
      + "        <item>"
      + "            <Title><![CDATA[title]]></Title>"
      + "            <Description><![CDATA[description]]></Description>"
      + "            <PicUrl><![CDATA[picUrl]]></PicUrl>"
      + "            <Url><![CDATA[url]]></Url>"
      + "        </item>"
      + "        <item>"
      + "            <Title><![CDATA[title]]></Title>"
      + "            <Description><![CDATA[description]]></Description>"
      + "            <PicUrl><![CDATA[picUrl]]></PicUrl>"
      + "            <Url><![CDATA[url]]></Url>"
      + "        </item>"
      + "    </Articles>"
      + "    <ArticleCount>2</ArticleCount>"
      + "</xml>";
    System.out.println(m.toXml());
    Assert.assertEquals(m.toXml().replaceAll("\\s", ""), expected.replaceAll("\\s", ""));
  }

  public void testBuild() {
    WxCpXmlOutNewsMessage.Item item = new WxCpXmlOutNewsMessage.Item();
    item.setDescription("description");
    item.setPicUrl("picUrl");
    item.setTitle("title");
    item.setUrl("url");

    WxCpXmlOutNewsMessage m = WxCpXmlOutMessage.NEWS()
      .fromUser("fromUser")
      .toUser("toUser")
      .addArticle(item)
      .addArticle(item)
      .build();
    String expected = "<xml>"
      + "<ToUserName><![CDATA[toUser]]></ToUserName>"
      + "<FromUserName><![CDATA[fromUser]]></FromUserName>"
      + "<CreateTime>1122</CreateTime>"
      + "<MsgType><![CDATA[news]]></MsgType>"
      + "    <Articles>"
      + "        <item>"
      + "            <Title><![CDATA[title]]></Title>"
      + "            <Description><![CDATA[description]]></Description>"
      + "            <PicUrl><![CDATA[picUrl]]></PicUrl>"
      + "            <Url><![CDATA[url]]></Url>"
      + "        </item>"
      + "        <item>"
      + "            <Title><![CDATA[title]]></Title>"
      + "            <Description><![CDATA[description]]></Description>"
      + "            <PicUrl><![CDATA[picUrl]]></PicUrl>"
      + "            <Url><![CDATA[url]]></Url>"
      + "        </item>"
      + "    </Articles>"
      + "    <ArticleCount>2</ArticleCount>"
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
