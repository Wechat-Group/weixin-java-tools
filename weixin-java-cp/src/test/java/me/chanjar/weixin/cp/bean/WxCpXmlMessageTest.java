package me.chanjar.weixin.cp.bean;

import me.chanjar.weixin.common.api.WxConsts;
import org.testng.annotations.*;

import static org.testng.Assert.*;

@Test
public class WxCpXmlMessageTest {

  public void testFromXml() {

    String xml = "<xml>"
      + "<ToUserName><![CDATA[toUser]]></ToUserName>"
      + "<FromUserName><![CDATA[fromUser]]></FromUserName> "
      + "<CreateTime>1348831860</CreateTime>"
      + "<MsgType><![CDATA[text]]></MsgType>"
      + "<Content><![CDATA[this is a test]]></Content>"
      + "<MsgId>1234567890123456</MsgId>"
      + "<PicUrl><![CDATA[this is a url]]></PicUrl>"
      + "<MediaId><![CDATA[media_id]]></MediaId>"
      + "<Format><![CDATA[Format]]></Format>"
      + "<ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>"
      + "<Location_X>23.134521</Location_X>"
      + "<Location_Y>113.358803</Location_Y>"
      + "<Scale>20</Scale>"
      + "<Label><![CDATA[位置信息]]></Label>"
      + "<Description><![CDATA[公众平台官网链接]]></Description>"
      + "<Url><![CDATA[url]]></Url>"
      + "<Title><![CDATA[公众平台官网链接]]></Title>"
      + "<Event><![CDATA[subscribe]]></Event>"
      + "<EventKey><![CDATA[qrscene_123123]]></EventKey>"
      + "<Ticket><![CDATA[TICKET]]></Ticket>"
      + "<Latitude>23.137466</Latitude>"
      + "<Longitude>113.352425</Longitude>"
      + "<Precision>119.385040</Precision>"
      + "<ScanCodeInfo>"
      + " <ScanType><![CDATA[qrcode]]></ScanType>"
      + " <ScanResult><![CDATA[1]]></ScanResult>"
      + "</ScanCodeInfo>"
      + "<SendPicsInfo>"
      + " <Count>1</Count>\n"
      + " <PicList>"
      + "  <item>"
      + "   <PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum>"
      + "  </item>"
      + " </PicList>"
      + "</SendPicsInfo>"
      + "<SendLocationInfo>"
      + "  <Location_X><![CDATA[23]]></Location_X>\n"
      + "  <Location_Y><![CDATA[113]]></Location_Y>\n"
      + "  <Scale><![CDATA[15]]></Scale>\n"
      + "  <Label><![CDATA[ 广州市海珠区客村艺苑路 106号]]></Label>\n"
      + "  <Poiname><![CDATA[wo de poi]]></Poiname>\n"
      + "</SendLocationInfo>"
      + "</xml>";
    WxCpXmlMessage wxMessage = WxCpXmlMessage.fromXml(xml);
    assertEquals(wxMessage.getToUserName(), "toUser");
    assertEquals(wxMessage.getFromUserName(), "fromUser");
    assertEquals(wxMessage.getCreateTime(), new Long(1348831860l));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.TEXT);
    assertEquals(wxMessage.getContent(), "this is a test");
    assertEquals(wxMessage.getMsgId(), new Long(1234567890123456l));
    assertEquals(wxMessage.getPicUrl(), "this is a url");
    assertEquals(wxMessage.getMediaId(), "media_id");
    assertEquals(wxMessage.getFormat(), "Format");
    assertEquals(wxMessage.getThumbMediaId(), "thumb_media_id");
    assertEquals(wxMessage.getLocationX(), 23.134521d);
    assertEquals(wxMessage.getLocationY(), 113.358803d);
    assertEquals(wxMessage.getScale(), 20d);
    assertEquals(wxMessage.getLabel(), "位置信息");
    assertEquals(wxMessage.getDescription(), "公众平台官网链接");
    assertEquals(wxMessage.getUrl(), "url");
    assertEquals(wxMessage.getTitle(), "公众平台官网链接");
    assertEquals(wxMessage.getEvent(), "subscribe");
    assertEquals(wxMessage.getEventKey(), "qrscene_123123");
    assertEquals(wxMessage.getTicket(), "TICKET");
    assertEquals(wxMessage.getLatitude(), 23.137466);
    assertEquals(wxMessage.getLongitude(), 113.352425);
    assertEquals(wxMessage.getPrecision(), 119.385040);
    assertEquals(wxMessage.getScanCodeInfo().getScanType(), "qrcode");
    assertEquals(wxMessage.getScanCodeInfo().getScanResult(), "1");
    assertEquals(wxMessage.getSendPicsInfo().getCount(), new Long(1l));
    assertEquals(wxMessage.getSendPicsInfo().getPicList().get(0).getPicMd5Sum(), "1b5f7c23b5bf75682a53e7b6d163e185");
    assertEquals(wxMessage.getSendLocationInfo().getLocationX(), "23");
    assertEquals(wxMessage.getSendLocationInfo().getLocationY(), "113");
    assertEquals(wxMessage.getSendLocationInfo().getScale(), "15");
    assertEquals(wxMessage.getSendLocationInfo().getLabel(), " 广州市海珠区客村艺苑路 106号");
    assertEquals(wxMessage.getSendLocationInfo().getPoiName(), "wo de poi");
  }

  public void testSendPicsInfo() {
    String xml = "<xml>" +
      "<ToUserName><![CDATA[wx45a0972125658be9]]></ToUserName>" +
      "<FromUserName><![CDATA[xiaohe]]></FromUserName>" +
      "<CreateTime>1502012364</CreateTime>" +
      "<MsgType><![CDATA[event]]></MsgType>" +
      "<AgentID>1000004</AgentID>" +
      "<Event><![CDATA[pic_weixin]]></Event>" +
      "<EventKey><![CDATA[faceSimilarity]]></EventKey>" +
      "<SendPicsInfo>" +
      "<PicList><item><PicMd5Sum><![CDATA[aef52ae501537e552725c5d7f99c1741]]></PicMd5Sum></item></PicList>" +
      "<PicList><item><PicMd5Sum><![CDATA[c4564632a4fab91378c39bea6aad6f9e]]></PicMd5Sum></item></PicList>" +
      "<Count>2</Count>" +
      "</SendPicsInfo>" +
      "</xml>";
    WxCpXmlMessage wxMessage = WxCpXmlMessage.fromXml(xml.replace("</PicList><PicList>",""));
    assertEquals(wxMessage.getToUserName(), "wx45a0972125658be9");
    assertEquals(wxMessage.getFromUserName(), "xiaohe");
    assertEquals(wxMessage.getCreateTime(), new Long(1502012364L));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.EVENT);
    assertEquals(wxMessage.getAgentId(), Integer.valueOf(1000004));
    assertEquals(wxMessage.getEvent(), "pic_weixin");
    assertEquals(wxMessage.getEventKey(), "faceSimilarity");
    assertNotNull(wxMessage.getSendPicsInfo());
    assertEquals(wxMessage.getSendPicsInfo().getCount(), new Long(2L));
    assertEquals(wxMessage.getSendPicsInfo().getPicList().get(0).getPicMd5Sum(), "aef52ae501537e552725c5d7f99c1741");
    assertEquals(wxMessage.getSendPicsInfo().getPicList().get(1).getPicMd5Sum(), "c4564632a4fab91378c39bea6aad6f9e");
  }
}
