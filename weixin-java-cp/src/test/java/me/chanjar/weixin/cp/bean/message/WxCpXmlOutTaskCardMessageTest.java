package me.chanjar.weixin.cp.bean.message;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WxCpXmlOutTaskCardMessageTest {

  public void test() {
    WxCpXmlOutTaskCardMessage m = new WxCpXmlOutTaskCardMessage();
    m.setReplaceName("已驳回");
    m.setCreateTime(1122L);
    m.setFromUserName("from");
    m.setToUserName("to");

    String expected = "<xml>"
      + "<ToUserName><![CDATA[to]]></ToUserName>"
      + "<FromUserName><![CDATA[from]]></FromUserName>"
      + "<CreateTime>1122</CreateTime>"
      + "<MsgType><![CDATA[update_taskcard]]></MsgType>"
      + "<TaskCard><ReplaceName><![CDATA[已驳回]]></ReplaceName></TaskCard>"
      + "</xml>";
    System.out.println(m.toXml());
    Assert.assertEquals(m.toXml().replaceAll("\\s", ""), expected.replaceAll("\\s", ""));
  }

  public void testBuild() {
    WxCpXmlOutTaskCardMessage m = WxCpXmlOutMessage.TASK_CARD().replaceName("已驳回").fromUser("from").toUser("to").build();
    String expected = "<xml>"
      + "<ToUserName><![CDATA[to]]></ToUserName>"
      + "<FromUserName><![CDATA[from]]></FromUserName>"
      + "<CreateTime>1122</CreateTime>"
      + "<MsgType><![CDATA[update_taskcard]]></MsgType>"
      + "<TaskCard><ReplaceName><![CDATA[已驳回]]></ReplaceName></TaskCard>"
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
