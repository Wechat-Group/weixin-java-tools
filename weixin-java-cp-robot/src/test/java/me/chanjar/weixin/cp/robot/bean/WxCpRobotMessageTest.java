package me.chanjar.weixin.cp.robot.bean;

import me.chanjar.weixin.cp.bean.article.NewArticle;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author linfeng-eqxiu
 * @date 2019/12/4
 */
@Test
public class WxCpRobotMessageTest {

  public void testTextBuild() {
    WxCpRobotMessage reply = WxCpRobotMessage.TEXT().addMobile("17711367871").content("Hello World").build();
    assertThat(reply.toJson())
      .isEqualTo("{\"msgtype\":\"text\",\"text\":{\"content\":\"Hello World\",\"mentioned_mobile_list\":[\"17711367871\"]}}");
  }

  public void testNewsBuild() {
    NewArticle article1 = new NewArticle();
    article1.setUrl("URL");
    article1.setPicUrl("PIC_URL");
    article1.setDescription("Is Really A Happy Day");
    article1.setTitle("Happy Day");

    NewArticle article2 = new NewArticle();
    article2.setUrl("URL");
    article2.setPicUrl("PIC_URL");
    article2.setDescription("Is Really A Happy Day");
    article2.setTitle("Happy Day");

    WxCpRobotMessage reply = WxCpRobotMessage.NEWS().addArticle(article1).addArticle(article2).build();

    assertThat(reply.toJson())
      .isEqualTo("{\"msgtype\":\"news\",\"news\":{\"articles\":" +
        "[{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"}," +
        "{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"}]}}");
  }

  public void testMarkdownBuild() {

    WxCpRobotMessage reply = WxCpRobotMessage.MARKDOWN().content("实时新增用户反馈<font color=\"warning\">132例</font>，请相关同事注意。\n> 类型:<font color=\"comment\">用户反馈</font>\n> 普通用户反馈:<font color=\"comment\">117例</font>\n> VIP用户反馈:<font color=\"comment\">15例</font>").build();

    assertThat(reply.toJson())
      .isEqualTo("{\"msgtype\":\"markdown\",\"markdown\":{\"content\":\"实时新增用户反馈<font color=\\\"warning\\\">132例</font>，请相关同事注意。\\n> 类型:<font color=\\\"comment\\\">用户反馈</font>\\n> 普通用户反馈:<font color=\\\"comment\\\">117例</font>\\n> VIP用户反馈:<font color=\\\"comment\\\">15例</font>\"}}");
  }
}
