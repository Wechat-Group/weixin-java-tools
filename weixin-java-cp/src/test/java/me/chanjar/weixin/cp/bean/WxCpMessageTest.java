package me.chanjar.weixin.cp.bean;

import me.chanjar.weixin.cp.bean.article.MpnewsArticle;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.bean.taskcard.TaskCardButton;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class WxCpMessageTest {

  public void testTextBuild() {
    WxCpMessage reply = WxCpMessage.TEXT().toUser("OPENID").content("sfsfdsdf").build();
    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"sfsfdsdf\"},\"safe\":\"0\"}");
  }

  public void testTextCardBuild() {
    WxCpMessage reply = WxCpMessage.TEXTCARD().toUser("OPENID")
      .title("领奖通知")
      .description("<div class=\"gray\">2016年9月26日</div> <div class=\"normal\">恭喜你抽中iPhone 7一台，" +
        "领奖码：xxxx</div><div class=\"highlight\">请于2016年10月10日前联系行政同事领取</div>")
      .url("http://www.qq.com")
      .btnTxt("更多")
      .build();
    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"textcard\",\"textcard\":{\"title\":\"领奖通知\"," +
        "\"description\":\"<div class=\\\"gray\\\">2016年9月26日</div> <div class=\\\"normal\\\">" +
        "恭喜你抽中iPhone 7一台，领奖码：xxxx</div><div class=\\\"highlight\\\">请于2016年10月10日前联系行政同事领取</div>\"," +
        "\"url\":\"http://www.qq.com\",\"btntxt\":\"更多\"},\"safe\":\"0\"}");
  }

  public void testImageBuild() {
    WxCpMessage reply = WxCpMessage.IMAGE().toUser("OPENID").mediaId("MEDIA_ID").build();
    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"MEDIA_ID\"},\"safe\":\"0\"}");
  }

  public void testVoiceBuild() {
    WxCpMessage reply = WxCpMessage.VOICE().toUser("OPENID").mediaId("MEDIA_ID").build();
    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"MEDIA_ID\"},\"safe\":\"0\"}");
  }

  public void testVideoBuild() {
    WxCpMessage reply = WxCpMessage.VIDEO().toUser("OPENID").title("TITLE").mediaId("MEDIA_ID").thumbMediaId("MEDIA_ID")
      .description("DESCRIPTION").build();
    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"MEDIA_ID\"," +
        "\"thumb_media_id\":\"MEDIA_ID\",\"title\":\"TITLE\",\"description\":\"DESCRIPTION\"},\"safe\":\"0\"}");
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

    WxCpMessage reply = WxCpMessage.NEWS().toUser("OPENID").addArticle(article1).addArticle(article2).build();

    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"news\",\"news\":{\"articles\":" +
        "[{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"}," +
        "{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"}]}," +
        "\"safe\":\"0\"}");
  }

  public void testMpnewsBuild_with_articles() {
    MpnewsArticle article1 = MpnewsArticle.newBuilder()
      .title("Happy Day")
      .author("aaaaaa")
      .content("hahaha")
      .contentSourceUrl("nice url")
      .digest("digest")
      .showCoverPic("heihei")
      .thumbMediaId("thumb")
      .build();

    MpnewsArticle article2 = MpnewsArticle.newBuilder()
      .title("Happy Day")
      .author("aaaaaa")
      .content("hahaha")
      .contentSourceUrl("nice url")
      .digest("digest")
      .showCoverPic("heihei")
      .thumbMediaId("thumb")
      .build();

    WxCpMessage reply = WxCpMessage.MPNEWS().toUser("OPENID").addArticle(article1, article2).build();

    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"mpnews\",\"mpnews\":{\"articles\":" +
        "[{\"title\":\"Happy Day\",\"thumb_media_id\":\"thumb\",\"author\":\"aaaaaa\"," +
        "\"content_source_url\":\"nice url\",\"content\":\"hahaha\",\"digest\":\"digest\",\"show_cover_pic\":\"heihei\"}" +
        ",{\"title\":\"Happy Day\",\"thumb_media_id\":\"thumb\",\"author\":\"aaaaaa\"," +
        "\"content_source_url\":\"nice url\",\"content\":\"hahaha\",\"digest\":\"digest\",\"show_cover_pic\":\"heihei\"}]}," +
        "\"safe\":\"0\"}");
  }

  public void testMpnewsBuild_with_media_id() {
    WxCpMessage reply = WxCpMessage.MPNEWS().toUser("OPENID").mediaId("mmm").build();

    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"mpnews\",\"mpnews\":{\"media_id\":\"mmm\"},\"safe\":\"0\"}");
  }

  public void testTaskCardBuilder() {
    TaskCardButton button1 = TaskCardButton.builder()
      .key("yes")
      .name("批准")
      .replaceName("已批准")
      .color("blue")
      .bold(true)
      .build();
    TaskCardButton button2 = TaskCardButton.builder()
      .key("yes")
      .name("拒绝")
      .replaceName("已拒绝")
      .color("red")
      .bold(false)
      .build();
    WxCpMessage reply = WxCpMessage.TASKCARD().toUser("OPENID")
      .title("任务卡片")
      .description("有一条待处理任务")
      .url("http://www.qq.com")
      .taskId("task_123")
      .buttons(Arrays.asList(button1, button2))
      .build();
    assertThat(reply.toJson())
      .isEqualTo("{\"touser\":\"OPENID\",\"msgtype\":\"taskcard\",\"taskcard\":{\"title\":\"任务卡片\",\"description\":\"有一条待处理任务\",\"url\":\"http://www.qq.com\",\"task_id\":\"task_123\",\"btn\":[{\"key\":\"yes\",\"name\":\"批准\",\"replace_name\":\"已批准\",\"color\":\"blue\",\"is_bold\":true},{\"key\":\"yes\",\"name\":\"拒绝\",\"replace_name\":\"已拒绝\",\"color\":\"red\",\"is_bold\":false}]}}");
  }

}
