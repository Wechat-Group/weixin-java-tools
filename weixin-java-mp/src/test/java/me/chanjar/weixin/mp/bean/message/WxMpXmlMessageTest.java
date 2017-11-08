package me.chanjar.weixin.mp.bean.message;

import me.chanjar.weixin.common.api.WxConsts;
import org.testng.annotations.*;

import static org.testng.Assert.*;

@Test
public class WxMpXmlMessageTest {

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
    WxMpXmlMessage wxMessage = WxMpXmlMessage.fromXml(xml);
    assertEquals(wxMessage.getToUser(), "toUser");
    assertEquals(wxMessage.getFromUser(), "fromUser");
    assertEquals(wxMessage.getCreateTime(), new Long(1348831860L));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.TEXT);
    assertEquals(wxMessage.getContent(), "this is a test");
    assertEquals(wxMessage.getMsgId(), new Long(1234567890123456L));
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
    assertEquals(wxMessage.getSendPicsInfo().getCount(), new Long(1L));
    assertEquals(wxMessage.getSendPicsInfo().getPicList().get(0).getPicMd5Sum(), "1b5f7c23b5bf75682a53e7b6d163e185");
    assertEquals(wxMessage.getSendLocationInfo().getLocationX(), "23");
    assertEquals(wxMessage.getSendLocationInfo().getLocationY(), "113");
    assertEquals(wxMessage.getSendLocationInfo().getScale(), "15");
    assertEquals(wxMessage.getSendLocationInfo().getLabel(), " 广州市海珠区客村艺苑路 106号");
    assertEquals(wxMessage.getSendLocationInfo().getPoiName(), "wo de poi");
  }

  public void testFromXml2() {

    String xml = "<xml>"
      + "<ToUserName><![CDATA[toUser]]></ToUserName>"
      + "<FromUserName><![CDATA[fromUser]]></FromUserName> "
      + "<CreateTime>1348831860</CreateTime>"
      + "<MsgType><![CDATA[text]]></MsgType>"
      + "<Content><![CDATA[this is a test]]></Content>"
      + "<MsgID>1234567890123456</MsgID>"
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
    WxMpXmlMessage wxMessage = WxMpXmlMessage.fromXml(xml);
    assertEquals(wxMessage.getToUser(), "toUser");
    assertEquals(wxMessage.getFromUser(), "fromUser");
    assertEquals(wxMessage.getCreateTime(), new Long(1348831860L));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.TEXT);
    assertEquals(wxMessage.getContent(), "this is a test");
    assertEquals(wxMessage.getMsgId(), new Long(1234567890123456L));
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
    assertEquals(wxMessage.getSendPicsInfo().getCount(), new Long(1L));
    assertEquals(wxMessage.getSendPicsInfo().getPicList().get(0).getPicMd5Sum(), "1b5f7c23b5bf75682a53e7b6d163e185");
    assertEquals(wxMessage.getSendLocationInfo().getLocationX(), "23");
    assertEquals(wxMessage.getSendLocationInfo().getLocationY(), "113");
    assertEquals(wxMessage.getSendLocationInfo().getScale(), "15");
    assertEquals(wxMessage.getSendLocationInfo().getLabel(), " 广州市海珠区客村艺苑路 106号");
    assertEquals(wxMessage.getSendLocationInfo().getPoiName(), "wo de poi");
  }

}
