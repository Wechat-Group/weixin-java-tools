package cn.binarywang.wx.miniapp.api.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.binarywang.wx.miniapp.bean.*;
import org.testng.annotations.*;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import cn.binarywang.wx.miniapp.test.TestConfig;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 测试消息相关接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaMsgServiceImplTest {

  @Inject
  private WxMaService wxService;

  public void testSendKefuMessage() throws WxErrorException {
    TestConfig config = (TestConfig) this.wxService.getWxMaConfig();
    WxMaKefuMessage message = WxMaKefuMessage.newTextBuilder()
      .toUser(config.getOpenid())
      .content("欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>")
      .build();

    this.wxService.getMsgService().sendKefuMsg(message);
  }

  @Test(invocationCount = 5, threadPoolSize = 3)
  public void testSendTemplateMsg() throws WxErrorException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    TestConfig config = (TestConfig) this.wxService.getWxMaConfig();

    WxMaTemplateMessage templateMessage = WxMaTemplateMessage.builder()
      .toUser(config.getOpenid())
      .formId("FORMID")
      .page("index")
      .data(Lists.newArrayList(
        new WxMaTemplateData("keyword1", "339208499"),
        new WxMaTemplateData("keyword2", dateFormat.format(new Date())),
        new WxMaTemplateData("keyword3", "粤海喜来登酒店"),
        new WxMaTemplateData("keyword4", "广州市天河区天河路208号")))
      .templateId(config.getTemplateId())
      .emphasisKeyword("keyword1.DATA")
      .build();
    //templateMessage.addData( new WxMaTemplateData("keyword1", "339208499", "#173177"));
    this.wxService.getMsgService().sendTemplateMsg(templateMessage);
  }


  @Test
  public void testSendSubscribeMsg() throws WxErrorException {
    TestConfig config = (TestConfig) this.wxService.getWxMaConfig();

    WxMaSubscribeMessage message = new WxMaSubscribeMessage();
    message.setTemplateId(config.getTemplateId());
    message.setToUser(config.getOpenid());
    message.addData(new WxMaSubscribeData("thing1", "苹果到货啦"));
    message.addData(new WxMaSubscribeData("amount3", "¥5"));
    message.addData(new WxMaSubscribeData("thing5", "记得领取哦"));
    this.wxService.getMsgService().sendSubscribeMsg(message);
  }


  @Test
  public void testSendUniformMsg() throws WxErrorException {
    TestConfig config = (TestConfig) this.wxService.getWxMaConfig();
    WxMaUniformMessage message =  WxMaUniformMessage.builder()
      .isMpTemplateMsg(false)
      .toUser(config.getOpenid())
      .page("page/page/index")
      .templateId("TEMPLATE_ID")
      .formId("FORMID")
      .emphasisKeyword("keyword1.DATA")
      .build();
    message.addData(new WxMaTemplateData("keyword1", "339208499"))
      .addData(new WxMaTemplateData("keyword2", "2015年01月05日 12:30"))
      .addData(new WxMaTemplateData("keyword3", "腾讯微信总部"))
      .addData(new WxMaTemplateData("keyword4", "广州市海珠区新港中路397号"));

    this.wxService.getMsgService().sendUniformMsg(message);
  }
}
