package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.bean.*;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import org.testng.annotations.*;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import cn.binarywang.wx.miniapp.test.TestConfig;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;

import static org.assertj.core.api.Assertions.assertThat;

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

  @Test
  public void testSendKefuMsg() throws WxErrorException {
    TestConfig config = (TestConfig) this.wxService.getWxMaConfig();
    WxMaKefuMessage message = WxMaKefuMessage.newTextBuilder()
      .toUser(config.getOpenid())
      .content("欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>")
      .build();

    this.wxService.getMsgService().sendKefuMsg(message);
  }

  @Test
  public void testSendSubscribeMsg() throws WxErrorException {
    TestConfig config = (TestConfig) this.wxService.getWxMaConfig();

    WxMaSubscribeMessage message = new WxMaSubscribeMessage();
    message.setTemplateId(config.getTemplateId());
    message.setToUser(config.getOpenid());
    message.setLang(WxMaConstants.MiniProgramLang.ZH_CN);
    message.setMiniprogramState(WxMaConstants.MiniProgramState.FORMAL);
    message.addData(new WxMaSubscribeMessage.Data("thing1", "苹果到货啦"));
    message.addData(new WxMaSubscribeMessage.Data("amount3", "¥5"));
    message.addData(new WxMaSubscribeMessage.Data("thing5", "记得领取哦"));
    this.wxService.getMsgService().sendSubscribeMsg(message);
  }


  @Test
  public void testSendUniformMsg() throws WxErrorException {
    TestConfig config = (TestConfig) this.wxService.getWxMaConfig();
    WxMaUniformMessage message = WxMaUniformMessage.builder()
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

  @Test
  public void testCreateUpdatableMessageActivityId() throws WxErrorException {
    final JsonObject jsonObject = this.wxService.getMsgService().createUpdatableMessageActivityId();
    assertThat(jsonObject).isNotNull();
    assertThat(jsonObject.get("activity_id")).isNotNull();
    System.out.println(jsonObject.get("activity_id"));
    assertThat(jsonObject.get("expiration_time")).isNotNull();
  }

  @Test
  public void testSetUpdatableMsg() throws WxErrorException {
    this.wxService.getMsgService().setUpdatableMsg(new WxMaUpdatableMsg()
      .setActivityId("1048_4f61uDloWPZl9pAs1dGx07vDiHKZ7FwJ0suohS1iMH5z8zhFktYk4nRqqBY~")
      .setTargetState(1)
      .setTemplateInfo(new WxMaUpdatableMsg.TemplateInfo()
        .setParameterList(Lists.newArrayList(new WxMaUpdatableMsg.Parameter().setName("member_count").setValue("1")))));
  }
}
