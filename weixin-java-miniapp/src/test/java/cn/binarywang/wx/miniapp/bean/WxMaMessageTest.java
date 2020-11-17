package cn.binarywang.wx.miniapp.bean;

import me.chanjar.weixin.common.api.WxConsts;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
public class WxMaMessageTest {

  public void testFromXml() {
    String xml = "<xml>\n" +
      "   <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "   <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
      "   <CreateTime>1482048670</CreateTime>\n" +
      "   <MsgType><![CDATA[text]]></MsgType>\n" +
      "   <Content><![CDATA[this is a test]]></Content>\n" +
      "   <MsgId>1234567890123456</MsgId>\n" +
      "   <PicUrl><![CDATA[this is a url]]></PicUrl>\n" +
      "   <MediaId><![CDATA[media_id]]></MediaId>\n" +
      "   <Title><![CDATA[Title]]></Title>\n" +
      "   <AppId><![CDATA[AppId]]></AppId>\n" +
      "   <PagePath><![CDATA[PagePath]]></PagePath>\n" +
      "   <ThumbUrl><![CDATA[ThumbUrl]]></ThumbUrl>\n" +
      "   <ThumbMediaId><![CDATA[ThumbMediaId]]></ThumbMediaId>\n" +
      "   <Event><![CDATA[user_enter_tempsession]]></Event>\n" +
      "   <SessionFrom><![CDATA[sessionFrom]]></SessionFrom>\n" +
      "</xml>";
    WxMaMessage wxMessage = WxMaMessage.fromXml(xml);
    assertEquals(wxMessage.getToUser(), "toUser");
    assertEquals(wxMessage.getFromUser(), "fromUser");
    assertEquals(wxMessage.getCreateTime(),new Integer(1482048670));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.TEXT);
    assertEquals(wxMessage.getContent(), "this is a test");
    assertEquals(wxMessage.getMsgId(), new Long(1234567890123456L));
    assertEquals(wxMessage.getPicUrl(), "this is a url");
    assertEquals(wxMessage.getMediaId(), "media_id");
    assertEquals(wxMessage.getTitle(), "Title");
    assertEquals(wxMessage.getPagePath(), "PagePath");
    assertEquals(wxMessage.getThumbUrl(), "ThumbUrl");
    assertEquals(wxMessage.getThumbMediaId(), "ThumbMediaId");
    assertEquals(wxMessage.getAppId(), "AppId");
    assertEquals(wxMessage.getEvent(), "user_enter_tempsession");
    assertEquals(wxMessage.getSessionFrom(), "sessionFrom");
  }

}
