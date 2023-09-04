package me.chanjar.weixin.cp.api;

import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.XmlUtils;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.oa.selfagent.WxCpOpenApprovalData;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.constant.WxCpConsts;
import me.chanjar.weixin.cp.demo.WxCpDemoInMemoryConfigStorage;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import me.chanjar.weixin.cp.util.xml.XStreamTransformer;
import org.testng.annotations.Test;

import java.io.InputStream;

/**
 * 企业微信自建应用接口测试类.
 * https://developer.work.weixin.qq.com/document/path/90269
 * https://developer.work.weixin.qq.com/document/path/90240#%E5%AE%A1%E6%89%B9%E7%8A%B6%E6%80%81%E9%80%9A%E7%9F%A5%E4
 * %BA%8B%E4%BB%B6
 *
 * @author <a href="https://gitee.com/Wang_Wong/">Wang_Wong</a> created on  2022-04-06
 */
@Slf4j
public class WxCpOaAgentTest {

  // extends WxCpBaseResp
  private static WxCpConfigStorage wxCpConfigStorage;
  private static WxCpService cpService;

  /**
   * Test.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void test() throws WxErrorException {

    InputStream inputStream = ClassLoader.getSystemResourceAsStream("test-config.xml");
    WxCpDemoInMemoryConfigStorage config = WxCpDemoInMemoryConfigStorage.fromXml(inputStream);

    wxCpConfigStorage = config;
    cpService = new WxCpServiceImpl();
    cpService.setWxCpConfigStorage(config);


    /**
     * 测试 审批状态通知事件
     */
    String testXml2 = "<xml>\n" +
      " <ToUserName><![CDATA[wwddddccc7775555aaa]]></ToUserName>\n" +
      "  <FromUserName><![CDATA[sys]]></FromUserName>\n" +
      "  <CreateTime>1527838022</CreateTime>\n" +
      "  <MsgType><![CDATA[event]]></MsgType>\n" +
      "  <Event><![CDATA[open_approval_change]]></Event>\n" +
      "  <AgentID>1</AgentID>\n" +
      "  <ApprovalInfo>\n" +
      "    <ThirdNo><![CDATA[201806010001]]></ThirdNo>\n" +
      "    <OpenSpName><![CDATA[付款]]></OpenSpName>\n" +
      "    <OpenTemplateId><![CDATA[1234567890]]></OpenTemplateId>\n" +
      "    <OpenSpStatus>1</OpenSpStatus>\n" +
      "    <ApplyTime>1527837645</ApplyTime>\n" +
      "    <ApplyUserName><![CDATA[xiaoming]]></ApplyUserName>\n" +
      "    <ApplyUserId><![CDATA[1]]></ApplyUserId>\n" +
      "    <ApplyUserParty><![CDATA[产品部]]></ApplyUserParty>\n" +
      "    <ApplyUserImage><![CDATA[http://www.qq.com/xxx.png]]></ApplyUserImage>\n" +
      "    <ApprovalNodes>\n" +
      "      <ApprovalNode>\n" +
      "        <NodeStatus>1</NodeStatus>\n" +
      "        <NodeAttr>1</NodeAttr>\n" +
      "        <NodeType>1</NodeType>\n" +
      "        <Items>\n" +
      "          <Item>\n" +
      "            <ItemName><![CDATA[xiaohong]]></ItemName>\n" +
      "            <ItemUserId><![CDATA[2]]></ItemUserId>\n" +
      "            <ItemImage><![CDATA[http://www.qq.com/xxx.png]]></ItemImage>\n" +
      "            <ItemStatus>1</ItemStatus>\n" +
      "            <ItemSpeech><![CDATA[]]></ItemSpeech>\n" +
      "            <ItemOpTime>0</ItemOpTime>\n" +
      "          </Item>\n" +
      "        </Items>\n" +
      "      </ApprovalNode>\n" +
      "    </ApprovalNodes>\n" +
      "    <NotifyNodes>\n" +
      "      <NotifyNode>\n" +
      "        <ItemName><![CDATA[xiaogang]]></ItemName>\n" +
      "        <ItemUserId><![CDATA[3]]></ItemUserId>\n" +
      "        <ItemImage><![CDATA[http://www.qq.com/xxx.png]]></ItemImage>\n" +
      "      </NotifyNode>\n" +
      "    </NotifyNodes>\n" +
      "    <approverstep>0</approverstep>\n" +
      "  </ApprovalInfo>\n" +
      "</xml>\n";
    final WxCpXmlMessage mess2 = XStreamTransformer.fromXml(WxCpXmlMessage.class, testXml2);
    mess2.setAllFieldsMap(XmlUtils.xml2Map(testXml2));
    log.info("xmlJson: {}", WxCpGsonBuilder.create().toJson(mess2));


    /**
     * 测试 弹出微信相册发图器的事件推送
     *
     * https://developer.work.weixin.qq.com/document/path/90240
     */
    String testXml = "<xml>\n" +
      "\t<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "\t<FromUserName><![CDATA[FromUser]]></FromUserName>\n" +
      "\t<CreateTime>1408090816</CreateTime>\n" +
      "\t<MsgType><![CDATA[event]]></MsgType>\n" +
      "\t<Event><![CDATA[pic_weixin]]></Event>\n" +
      "\t<EventKey><![CDATA[6]]></EventKey>\n" +
      "\t<SendPicsInfo><Count>1</Count>\n" +
      "\t<PicList><item><PicMd5Sum><![CDATA[5a75aaca956d97be686719218f275c6b]]></PicMd5Sum>\n" +
      "\t</item>\n" +
      "\t</PicList>\n" +
      "\t</SendPicsInfo>\n" +
      "\t<AgentID>1</AgentID>\n" +
      "</xml>\n";

    final WxCpXmlMessage mess = XStreamTransformer.fromXml(WxCpXmlMessage.class, testXml);
    mess.setAllFieldsMap(XmlUtils.xml2Map(testXml));
    log.info("xmlJson: {}", WxCpGsonBuilder.create().toJson(mess));


    /**
     * 审批流程引擎
     * 自建应用审批状态变化通知回调
     *
     * https://developer.work.weixin.qq.com/document/path/90269
     */
    String approvalInfoXml = "<xml>\n" +
      " <ToUserName>wwd08c8e7c775abaaa</ToUserName>  \n" +
      "  <FromUserName>sys</FromUserName>  \n" +
      "  <CreateTime>1527838022</CreateTime>  \n" +
      "  <MsgType>event</MsgType>  \n" +
      "  <Event>open_approval_change</Event>\n" +
      "  <AgentID>1</AgentID>\n" +
      "  <ApprovalInfo> \n" +
      "    <ThirdNo>thirdNoxxx</ThirdNo>  \n" +
      "    <OpenSpName>付款</OpenSpName>  \n" +
      "    <OpenTemplateId>1234567111</OpenTemplateId> \n" +
      "    <OpenSpStatus>1</OpenSpStatus>  \n" +
      "    <ApplyTime>1527837645</ApplyTime>  \n" +
      "    <ApplyUserName>jackiejjwu</ApplyUserName>  \n" +
      "    <ApplyUserId>WuJunJie</ApplyUserId>  \n" +
      "    <ApplyUserParty>产品部</ApplyUserParty>  \n" +
      "    <ApplyUserImage>http://www.qq.com/xxx.png</ApplyUserImage>  \n" +
      "    <ApprovalNodes> \n" +
      "      <ApprovalNode> \n" +
      "        <NodeStatus>1</NodeStatus>  \n" +
      "        <NodeAttr>1</NodeAttr> \n" +
      "        <NodeType>1</NodeType>  \n" +
      "        <Items> \n" +
      "          <Item> \n" +
      "            <ItemName>chauvetxiao</ItemName>  \n" +
      "            <ItemUserid>XiaoWen</ItemUserid> \n" +
      "            <ItemParty>产品部</ItemParty>  \n" +
      "            <ItemImage>http://www.qq.com/xxx.png</ItemImage>  \n" +
      "            <ItemStatus>1</ItemStatus>  \n" +
      "            <ItemSpeech></ItemSpeech>  \n" +
      "            <ItemOpTime>0</ItemOpTime> \n" +
      "          </Item> \n" +
      "        </Items> \n" +
      "      </ApprovalNode> \n" +
      "    </ApprovalNodes>  \n" +
      "    <NotifyNodes> \n" +
      "      <NotifyNode> \n" +
      "        <ItemName>jinhuiguo</ItemName>  \n" +
      "        <ItemUserid>GuoJinHui</ItemUserid> \n" +
      "        <ItemParty>行政部</ItemParty>  \n" +
      "        <ItemImage>http://www.qq.com/xxx.png</ItemImage>  \n" +
      "      </NotifyNode> \n" +
      "    </NotifyNodes> \n" +
      "    <ApproverStep>0</ApproverStep> \n" +
      "  </ApprovalInfo> \n" +
      "</xml>\n";

    final WxCpXmlMessage msg = XStreamTransformer.fromXml(WxCpXmlMessage.class, approvalInfoXml);
    msg.setAllFieldsMap(XmlUtils.xml2Map(approvalInfoXml));
    log.info("xmlJson: {}", WxCpGsonBuilder.create().toJson(msg));

    /**
     * 增加
     * 自建应用审批状态变化通知回调类型
     */
    String openApprovalChange = WxCpConsts.EventType.OPEN_APPROVAL_CHANGE;


    /**
     * Test
     */
    String test = "{\"errcode\":0,\"errmsg\":\"ok\",\"data\":{\"ThirdNo\":\"thirdNoxxx\"," +
      "\"OpenTemplateId\":\"1234567111\",\"OpenSpName\":\"付款\",\"OpenSpstatus\":1,\"ApplyTime\":1527837645," +
      "\"ApplyUsername\":\"jackiejjwu\",\"ApplyUserParty\":\"产品部\",\"ApplyUserImage\":\"http://www.qq.com/xxx.png\"," +
      "\"ApplyUserId\":\"WuJunJie\",\"ApprovalNodes\":{\"ApprovalNode\":[{\"NodeStatus\":1,\"NodeAttr\":1," +
      "\"NodeType\":1,\"Items\":{\"Item\":[{\"ItemName\":\"chauvetxiao\",\"ItemParty\":\"产品部\"," +
      "\"ItemImage\":\"http://www.qq.com/xxx.png\",\"ItemUserId\":\"XiaoWen\",\"ItemStatus\":1,\"ItemSpeech\":\"\"," +
      "\"ItemOpTime\":0}]}}]},\"NotifyNodes\":{\"NotifyNode\":[{\"ItemName\":\"jinhuiguo\",\"ItemParty\":\"行政部\"," +
      "\"ItemImage\":\"http://www.qq.com/xxx.png\",\"ItemUserId\":\"GuoJinHui\"}]},\"ApproverStep\":0}}";

    final WxCpOpenApprovalData data = WxCpGsonBuilder.create()
      .fromJson(GsonParser.parse(test).get("data"),
        new TypeToken<WxCpOpenApprovalData>() {
        }.getType()
      );

    log.info(data.toJson());


    WxCpOpenApprovalData openApprovalData = cpService.getOaAgentService().getOpenApprovalData("943225459735269376");
    log.info(openApprovalData.toJson());

  }

}
