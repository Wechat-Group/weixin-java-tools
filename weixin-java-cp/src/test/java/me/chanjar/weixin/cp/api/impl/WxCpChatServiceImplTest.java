package me.chanjar.weixin.cp.api.impl;

import java.util.Arrays;

import org.testng.*;
import org.testng.annotations.*;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.constant.WxCpConsts.AppChatMsgType;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpAppChatMessage;
import me.chanjar.weixin.cp.bean.WxCpChat;
import me.chanjar.weixin.cp.bean.article.MpnewsArticle;
import me.chanjar.weixin.cp.bean.article.NewArticle;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试群聊服务
 *
 * @author gaigeshen
 */
@Guice(modules = ApiTestModule.class)
public class WxCpChatServiceImplTest {
  private String chatId;
  private String userId;

  @Inject
  private WxCpService cpService;

  @BeforeTest
  public void init() {
    this.chatId = "mychatid";
    this.userId = ((ApiTestModule.WxXmlCpInMemoryConfigStorage) this.cpService.getWxCpConfigStorage()).getUserId();
  }

  @Test
  public void testCreate() throws Exception {
    final String result = cpService.getChatService().create("测试群聊", userId,
      Arrays.asList(userId, userId), chatId);
    assertThat(result).isNotEmpty();
    assertThat(result).isEqualTo(chatId);
  }

  @Test
  public void testGet() throws Exception {
    WxCpChat chat = this.cpService.getChatService().get(chatId);
    System.out.println(chat);
    Assert.assertEquals(chat.getName(), "测试群聊");
  }

  @Test
  public void testUpdate() throws Exception {
    this.cpService.getChatService().update(chatId, "", "", Arrays.asList("ZhengWuYao"), null);
    WxCpChat chat = this.cpService.getChatService().get(chatId);
    System.out.println(chat);
    Assert.assertEquals(chat.getUsers().size(), 3);
  }

  @DataProvider
  public Object[][] messages() {
    return new Object[][]{
      {WxCpAppChatMessage.builder()
        .msgType(AppChatMsgType.TEXT.toString())
        .chatId(chatId)
        .content("你的快递已到\n请携带工卡前往邮件中心领取")
        .build()
      },
      {WxCpAppChatMessage.builder()
        .msgType(AppChatMsgType.IMAGE.toString())
        .chatId(chatId)
        .mediaId("3_xWGPXZhpOKZrlRISWrjhPrDUZqZ-jIEVzxd56jLuqM")
        .build()
      },
      {WxCpAppChatMessage.builder()
        .msgType(AppChatMsgType.VOICE.toString())
        .chatId(chatId)
        .mediaId("3X5t6HkdN1hUgB7OzrdRnc8v0yI0CqlAxFxnCkS3msTnTLanpYrV4esLv4foZVnlf")
        .build()
      },
      {WxCpAppChatMessage.builder()
        .msgType(AppChatMsgType.VIDEO.toString())
        .chatId(chatId)
        .mediaId("3otWyy_acbID8fyltmCOW5hGVD8oa0_p0za5jhukxKTUDoGT71lqTvtQAWoycXpQf")
        .title("aaaa")
        .description("ddddd")
        .build()
      },
      {WxCpAppChatMessage.builder()
        .msgType(AppChatMsgType.FILE.toString())
        .chatId(chatId)
        .mediaId("34AyVyDdndVhB4Z2tT-_FYKZ7Xqrr47LPC11GHH4oy7o")
        .build()
      },
      {WxCpAppChatMessage.builder()
        .msgType(AppChatMsgType.TEXTCARD.toString())
        .chatId(chatId)
        .btnTxt("更多")
        .title("领奖通知")
        .url("https://zhidao.baidu.com/question/2073647112026042748.html")
        .description("<div class=\"gray\">2016年9月26日</div> <div class=\"normal\"> 恭喜你抽中iPhone 7一台，领奖码:520258</div><div class=\"highlight\">请于2016年10月10日前联系行 政同事领取</div>")
        .build()
      },
      {WxCpAppChatMessage.builder()
        .msgType(AppChatMsgType.NEWS.toString())
        .chatId(chatId)
        .articles(Lists.newArrayList(NewArticle.builder()
          .title("领奖通知")
          .url("https://zhidao.baidu.com/question/2073647112026042748.html")
          .description("今年中秋节公司有豪礼相送")
          .picUrl("http://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1.png")
          .build()
        ))
        .build()
      },
      {WxCpAppChatMessage.builder()
        .msgType(AppChatMsgType.MPNEWS.toString())
        .chatId(chatId)
        .mpnewsArticles(Lists.newArrayList(MpnewsArticle.newBuilder()
          .title("地球一小时")
          .thumbMediaId("3_xWGPXZhpOKZrlRISWrjhPrDUZqZ-jIEVzxd56jLuqM")
          .author("Author")
          .contentSourceUrl("https://work.weixin.qq.com")
          .content("3月24日20:30-21:30 \n办公区将关闭照明一小时，请各部门同事相互转告")
          .digest("3月24日20:30-21:30 \n办公区将关闭照明一小时")
          .build()
        ))
        .build()
      },
      {WxCpAppChatMessage.builder()
        .msgType(AppChatMsgType.MARKDOWN.toString())
        .chatId(chatId)
        .content("您的会议室已经预定，稍后会同步到`邮箱` \n" +
          "                >**事项详情** \n" +
          "                >事　项：<font color=\\\"info\\\">开会</font> \n" +
          "                >组织者：@miglioguan \n" +
          "                >参与者：@miglioguan、@kunliu、@jamdeezhou、@kanexiong、@kisonwang \n" +
          "                > \n" +
          "                >会议室：<font color=\\\"info\\\">广州TIT 1楼 301</font> \n" +
          "                >日　期：<font color=\\\"warning\\\">2018年5月18日</font> \n" +
          "                >时　间：<font color=\\\"comment\\\">上午9:00-11:00</font> \n" +
          "                > \n" +
          "                >请准时参加会议。 \n" +
          "                > \n" +
          "                >如需修改会议信息，请点击：[修改会议信息](https://work.weixin.qq.com)")
        .build()
      },
    };
  }

  @Test(dataProvider = "messages")
  public void testSendMsg(WxCpAppChatMessage message) throws WxErrorException {
    this.cpService.getChatService().sendMsg(message);
  }
}
