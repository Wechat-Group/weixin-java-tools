package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.bean.message.WxCpTpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.tp.message.WxCpTpMessageHandler;
import me.chanjar.weixin.cp.tp.message.WxCpTpMessageRouter;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.tp.service.impl.WxCpTpServiceApacheHttpClientImpl;
import me.chanjar.weixin.cp.tp.service.impl.WxCpTpServiceImpl;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

public class WxCpTpMessageRouterTest {


  @Test
  public void testMessageRouter() {
    WxCpTpService service = new WxCpTpServiceApacheHttpClientImpl();
    WxCpTpMessageRouter router = new WxCpTpMessageRouter(service);

    String xml = "<xml>\n" +
      "    <SuiteId><![CDATA[ww4asffe99e54cxxxx]]></SuiteId>\n" +
      "    <AuthCorpId><![CDATA[wxf8b4f85f3a79xxxx]]></AuthCorpId>\n" +
      "    <InfoType><![CDATA[change_contact]]></InfoType>\n" +
      "    <TimeStamp>1403610513</TimeStamp>\n" +
      "    <ChangeType><![CDATA[update_tag]]></ChangeType>\n" +
      "    <TagId>1</TagId>\n" +
      "    <AddUserItems><![CDATA[zhangsan,lisi]]></AddUserItems>\n" +
      "    <DelUserItems><![CDATA[zhangsan1,lisi1]]></DelUserItems>\n" +
      "    <AddPartyItems><![CDATA[1,2]]></AddPartyItems>\n" +
      "    <DelPartyItems><![CDATA[3,4]]></DelPartyItems>\n" +
      "</xml>";

    WxCpTpXmlMessage wxXmlMessage = WxCpTpXmlMessage.fromXml(xml);

    router.rule().infoType("change_contact").changeType("update_tag").handler(new WxCpTpMessageHandler() {
      @Override
      public WxCpXmlOutMessage handle(WxCpTpXmlMessage wxMessage, Map<String, Object> context, WxCpTpService wxCpService, WxSessionManager sessionManager) throws WxErrorException {
        System.out.println("handler enter");
        assertNotNull(wxCpService);
        return null;
      }
    }).end();

    assertNull(router.route(wxXmlMessage));


    System.out.println("over");
  }

}
