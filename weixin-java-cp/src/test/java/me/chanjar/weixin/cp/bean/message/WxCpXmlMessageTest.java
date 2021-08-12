package me.chanjar.weixin.cp.bean.message;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.xml.XmlUtil;
import me.chanjar.weixin.cp.constant.WxCpConsts;
import org.testng.annotations.Test;

import static me.chanjar.weixin.cp.constant.WxCpConsts.EventType.TASKCARD_CLICK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

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
    WxCpXmlMessage wxMessage = XmlUtil.toObject(xml,WxCpXmlMessage.class);
    assertEquals(wxMessage.getToUserName(), "toUser");
    assertEquals(wxMessage.getFromUserName(), "fromUser");
    assertEquals(wxMessage.getCreateTime(), new Long(1348831860));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.TEXT);
    assertEquals(wxMessage.getContent(), "this is a test");
    assertEquals(wxMessage.getMsgId(), new Long(1234567890123456L));
    assertEquals(wxMessage.getPicUrl(), "this is a url");
    assertEquals(wxMessage.getMediaId(), "media_id");
    assertEquals(wxMessage.getFormat(), "Format");
    assertEquals(wxMessage.getThumbMediaId(), "thumb_media_id");
    assertEquals(wxMessage.getLocationX().doubleValue(), 23.134521d);
    assertEquals(wxMessage.getLocationY().doubleValue(), 113.358803d);
    assertEquals(wxMessage.getScale().doubleValue(), 20d);
    assertEquals(wxMessage.getLabel(), "位置信息");
    assertEquals(wxMessage.getDescription(), "公众平台官网链接");
    assertEquals(wxMessage.getUrl(), "url");
    assertEquals(wxMessage.getTitle(), "公众平台官网链接");
    assertEquals(wxMessage.getEvent(), "subscribe");
    assertEquals(wxMessage.getEventKey(), "qrscene_123123");
    assertEquals(wxMessage.getTicket(), "TICKET");
    assertEquals(wxMessage.getLatitude().doubleValue(), 23.137466);
    assertEquals(wxMessage.getLongitude().doubleValue(), 113.352425);
    assertEquals(wxMessage.getPrecision().doubleValue(), 119.385040);
    assertEquals(wxMessage.getScanCodeInfo().getScanType(), "qrcode");
    assertEquals(wxMessage.getScanCodeInfo().getScanResult(), "1");
    assertEquals(wxMessage.getSendPicsInfo().getCount(), new Long(1));
    assertEquals(wxMessage.getSendPicsInfo().getPicList().get(0).getItem().getPicMd5Sum(), "1b5f7c23b5bf75682a53e7b6d163e185");
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
    WxCpXmlMessage wxMessage = WxCpXmlMessage.fromXml(xml.replace("</PicList><PicList>", ""));
    assertEquals(wxMessage.getToUserName(), "wx45a0972125658be9");
    assertEquals(wxMessage.getFromUserName(), "xiaohe");
    assertEquals(wxMessage.getCreateTime(), new Long(1502012364L));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.EVENT);
    assertEquals(wxMessage.getAgentId(), Integer.valueOf(1000004));
    assertEquals(wxMessage.getEvent(), "pic_weixin");
    assertEquals(wxMessage.getEventKey(), "faceSimilarity");
    assertNotNull(wxMessage.getSendPicsInfo());
    assertEquals(wxMessage.getSendPicsInfo().getCount(), new Long(2L));
    assertEquals(wxMessage.getSendPicsInfo().getPicList().get(0).getItem().getPicMd5Sum(), "aef52ae501537e552725c5d7f99c1741");
    assertEquals(wxMessage.getSendPicsInfo().getPicList().get(1).getItem().getPicMd5Sum(), "c4564632a4fab91378c39bea6aad6f9e");
  }

  public void testExtAttr() {

    String xml = "<xml>" +
      "    <ToUserName><![CDATA[w56c9fe3d50ad1ea2]]></ToUserName>" +
      "    <FromUserName><![CDATA[sys]]></FromUserName>" +
      "    <CreateTime>1557241961</CreateTime>" +
      "    <MsgType><![CDATA[event]]></MsgType>" +
      "    <Event><![CDATA[change_contact]]></Event>" +
      "    <ChangeType><![CDATA[update_user]]></ChangeType>" +
      "    <UserID><![CDATA[zhangsan]]></UserID>" +
      "    <ExtAttr>" +
      "        <Item><Name><![CDATA[爱好]]></Name><Value><![CDATA[111]]></Value><Text><Value><![CDATA[111]]></Value></Text></Item>" +
      "        <Item><Name><![CDATA[入职时间]]></Name><Value><![CDATA[11111]]></Value><Text><Value><![CDATA[11111]]></Value></Text></Item>" +
      "        <Item><Name><![CDATA[城市]]></Name><Value><![CDATA[11111]]></Value><Text><Value><![CDATA[11111]]></Value></Text></Item>" +
      "    </ExtAttr>" +
      "    <Address><![CDATA[11111]]></Address>" +
      "</xml>";
    WxCpXmlMessage wxMessage = WxCpXmlMessage.fromXml(xml);
    assertEquals(wxMessage.getToUserName(), "w56c9fe3d50ad1ea2");
    assertEquals(wxMessage.getFromUserName(), "sys");
    assertEquals(wxMessage.getCreateTime(), new Long(1557241961));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.EVENT);
    assertEquals(wxMessage.getEvent(), "change_contact");
    assertEquals(wxMessage.getChangeType(), "update_user");
    assertEquals(wxMessage.getUserId(), "zhangsan");
    assertNotNull(wxMessage.getExtAttrs());
    assertNotNull(wxMessage.getExtAttrs().getItems());
    assertEquals(wxMessage.getExtAttrs().getItems().size(), 3);
    assertEquals(wxMessage.getExtAttrs().getItems().get(0).getName(), "爱好");

  }

  public void testTaskCardEvent() {
    String xml = "<xml>" +
      "<ToUserName><![CDATA[toUser]]></ToUserName>" +
      "<FromUserName><![CDATA[FromUser]]></FromUserName>" +
      "<CreateTime>123456789</CreateTime>" +
      "<MsgType><![CDATA[event]]></MsgType>" +
      "<Event><![CDATA[taskcard_click]]></Event>" +
      "<EventKey><![CDATA[key111]]></EventKey>" +
      "<TaskId><![CDATA[taskid111]]></TaskId >" +
      "<AgentID>1</AgentID>" +
      "</xml>";
    WxCpXmlMessage wxMessage = WxCpXmlMessage.fromXml(xml);
    assertEquals(wxMessage.getToUserName(), "toUser");
    assertEquals(wxMessage.getFromUserName(), "FromUser");
    assertEquals(wxMessage.getCreateTime(), Long.valueOf(123456789L));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.EVENT);
    assertEquals(wxMessage.getAgentId(), Integer.valueOf(1));
    assertEquals(wxMessage.getEvent(), TASKCARD_CLICK);
    assertEquals(wxMessage.getEventKey(), "key111");
    assertEquals(wxMessage.getTaskId(), "taskid111");
  }

  public void testAddExternalUserEvent() {
    String xml = "<xml>" +
      "<ToUserName><![CDATA[toUser]]></ToUserName>" +
      "<FromUserName><![CDATA[sys]]></FromUserName>" +
      "<CreateTime>1403610513</CreateTime>" +
      "<MsgType><![CDATA[event]]></MsgType>" +
      "<Event><![CDATA[change_external_contact]]></Event>" +
      "<ChangeType><![CDATA[add_external_contact]]></ChangeType>" +
      "<UserID><![CDATA[zhangsan]]></UserID>" +
      "<ExternalUserID><![CDATA[woAJ2GCAAAXtWyujaWJHDDGi0mACH71w]]></ExternalUserID>" +
      "<State><![CDATA[teststate]]></State>" +
      "<WelcomeCode><![CDATA[WELCOMECODE]]></WelcomeCode>" +
      "</xml >";
    WxCpXmlMessage wxMessage = WxCpXmlMessage.fromXml(xml);
    assertEquals(wxMessage.getToUserName(), "toUser");
    assertEquals(wxMessage.getFromUserName(), "sys");
    assertEquals(wxMessage.getCreateTime(), Long.valueOf(1403610513L));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.EVENT);
    assertEquals(wxMessage.getEvent(), WxCpConsts.EventType.CHANGE_EXTERNAL_CONTACT);
    assertEquals(wxMessage.getChangeType(), WxCpConsts.ExternalContactChangeType.ADD_EXTERNAL_CONTACT);
    assertEquals(wxMessage.getExternalUserId(), "woAJ2GCAAAXtWyujaWJHDDGi0mACH71w");
    assertEquals(wxMessage.getState(), "teststate");
    assertEquals(wxMessage.getWelcomeCode(), "WELCOMECODE");

  }

  public void testDelExternalUserEvent() {
    String xml = "<xml>" +
      "<ToUserName><![CDATA[toUser]]></ToUserName>" +
      "<FromUserName><![CDATA[sys]]></FromUserName>" +
      "<CreateTime>1403610513</CreateTime>" +
      "<MsgType><![CDATA[event]]></MsgType>" +
      "<Event><![CDATA[change_external_contact]]></Event>" +
      "<ChangeType><![CDATA[del_external_contact]]></ChangeType>" +
      "<UserID><![CDATA[zhangsan]]></UserID>" +
      "<ExternalUserID><![CDATA[woAJ2GCAAAXtWyujaWJHDDGi0mACH71w]]></ExternalUserID>" +
      "</xml>";
    WxCpXmlMessage wxMessage = WxCpXmlMessage.fromXml(xml);
    assertEquals(wxMessage.getToUserName(), "toUser");
    assertEquals(wxMessage.getFromUserName(), "sys");
    assertEquals(wxMessage.getCreateTime(), Long.valueOf(1403610513L));
    assertEquals(wxMessage.getMsgType(), WxConsts.XmlMsgType.EVENT);
    assertEquals(wxMessage.getEvent(), WxCpConsts.EventType.CHANGE_EXTERNAL_CONTACT);
    assertEquals(wxMessage.getChangeType(), WxCpConsts.ExternalContactChangeType.DEL_EXTERNAL_CONTACT);
    assertEquals(wxMessage.getUserId(), "zhangsan");
    assertEquals(wxMessage.getExternalUserId(), "woAJ2GCAAAXtWyujaWJHDDGi0mACH71w");
  }

  public void testChangeContact() {
    String xml = "<xml>\n" +
      "    <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "    <FromUserName><![CDATA[sys]]></FromUserName> \n" +
      "    <CreateTime>1403610513</CreateTime>\n" +
      "    <MsgType><![CDATA[event]]></MsgType>\n" +
      "    <Event><![CDATA[change_contact]]></Event>\n" +
      "    <ChangeType>update_user</ChangeType>\n" +
      "    <UserID><![CDATA[zhangsan]]></UserID>\n" +
      "    <NewUserID><![CDATA[zhangsan001]]></NewUserID>\n" +
      "    <Name><![CDATA[张三]]></Name>\n" +
      "    <Department><![CDATA[1,2,3]]></Department>\n" +
      "    <IsLeaderInDept><![CDATA[1,0,0]]></IsLeaderInDept>\n" +
      "    <Position><![CDATA[产品经理]]></Position>\n" +
      "    <Mobile>15913215421</Mobile>\n" +
      "    <Gender>1</Gender>\n" +
      "    <Email><![CDATA[zhangsan@gzdev.com]]></Email>\n" +
      "    <Status>1</Status>\n" +
      "    <Avatar><![CDATA[http://wx.qlogo.cn/mmopen/ajNVdqHZLLA3WJ6DSZUfiakYe37PKnQhBIeOQBO4czqrnZDS79FH5Wm5m4X69TBicnHFlhiafvDwklOpZeXYQQ2icg/0]]></Avatar>\n" +
      "    <Alias><![CDATA[zhangsan]]></Alias>\n" +
      "    <Telephone><![CDATA[020-3456788]]></Telephone>\n" +
      "    <Address><![CDATA[广州市]]></Address>\n" +
      "    <ExtAttr>\n" +
      "        <Item>\n" +
      "        <Name><![CDATA[爱好]]></Name>\n" +
      "        <Type>0</Type>\n" +
      "        <Text>\n" +
      "            <Value><![CDATA[旅游]]></Value>\n" +
      "        </Text>\n" +
      "        </Item>\n" +
      "        <Item>\n" +
      "        <Name><![CDATA[卡号]]></Name>\n" +
      "        <Type>1</Type>\n" +
      "        <Web>\n" +
      "            <Title><![CDATA[企业微信]]></Title>\n" +
      "            <Url><![CDATA[https://work.weixin.qq.com]]></Url>\n" +
      "        </Web>\n" +
      "        </Item>\n" +
      "    </ExtAttr>\n" +
      "</xml>";

    WxCpXmlMessage wxCpXmlMessage = WxCpXmlMessage.fromXml(xml);
    assertThat(wxCpXmlMessage).isNotNull();
    assertThat(wxCpXmlMessage.getDepartments()).isNotEmpty();

    System.out.println(XmlUtil.toXml(wxCpXmlMessage));
  }
}
