package me.chanjar.weixin.cp.bean;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.article.MpnewsArticle;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class WxCpMessageTest {

  public void testTextReply() {
    WxCpMessage reply = new WxCpMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.CUSTOM_MSG_TEXT);
    reply.setContent("sfsfdsdf");
    assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"sfsfdsdf\"}}");
  }

  public void testTextBuild() {
    WxCpMessage reply = WxCpMessage.TEXT().toUser("OPENID").content("sfsfdsdf").build();
    assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"sfsfdsdf\"}}");
  }

  public void testImageReply() {
    WxCpMessage reply = new WxCpMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.CUSTOM_MSG_IMAGE);
    reply.setMediaId("MEDIA_ID");
    assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testImageBuild() {
    WxCpMessage reply = WxCpMessage.IMAGE().toUser("OPENID").mediaId("MEDIA_ID").build();
    assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testVoiceReply() {
    WxCpMessage reply = new WxCpMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.CUSTOM_MSG_VOICE);
    reply.setMediaId("MEDIA_ID");
    assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testVoiceBuild() {
    WxCpMessage reply = WxCpMessage.VOICE().toUser("OPENID").mediaId("MEDIA_ID").build();
    assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testVideoReply() {
    WxCpMessage reply = new WxCpMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.CUSTOM_MSG_VIDEO);
    reply.setMediaId("MEDIA_ID");
    reply.setThumbMediaId("MEDIA_ID");
    reply.setTitle("TITLE");
    reply.setDescription("DESCRIPTION");
    assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"MEDIA_ID\",\"thumb_media_id\":\"MEDIA_ID\",\"title\":\"TITLE\",\"description\":\"DESCRIPTION\"}}");
  }

  public void testVideoBuild() {
    WxCpMessage reply = WxCpMessage.VIDEO().toUser("OPENID").title("TITLE").mediaId("MEDIA_ID").thumbMediaId("MEDIA_ID").description("DESCRIPTION").build();
    assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"MEDIA_ID\",\"thumb_media_id\":\"MEDIA_ID\",\"title\":\"TITLE\",\"description\":\"DESCRIPTION\"}}");
  }

  public void testNewsReply() {
    WxCpMessage reply = new WxCpMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.CUSTOM_MSG_NEWS);

    NewArticle article1 = new NewArticle();
    article1.setUrl("URL");
    article1.setPicUrl("PIC_URL");
    article1.setDescription("Is Really A Happy Day");
    article1.setTitle("Happy Day");
    reply.getArticles().add(article1);

    NewArticle article2 = new NewArticle();
    article2.setUrl("URL");
    article2.setPicUrl("PIC_URL");
    article2.setDescription("Is Really A Happy Day");
    article2.setTitle("Happy Day");
    reply.getArticles().add(article2);


    assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"news\",\"news\":{\"articles\":[{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"},{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"}]}}");
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

    assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"news\",\"news\":{\"articles\":[{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"},{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"}]}}");
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

    WxCpMessage reply = WxCpMessage.MPNEWS().toUser("OPENID").addArticle(article1).addArticle(article2).build();

    assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"mpnews\"," +
      "\"mpnews\":{\"articles\":[{\"title\":\"Happy Day\",\"thumb_media_id\":\"thumb\",\"author\":\"aaaaaa\",\"content_source_url\":\"nice url\",\"content\":\"hahaha\",\"digest\":\"digest\",\"show_cover_pic\":\"heihei\"}," +
      "{\"title\":\"Happy Day\",\"thumb_media_id\":\"thumb\",\"author\":\"aaaaaa\",\"content_source_url\":\"nice url\",\"content\":\"hahaha\",\"digest\":\"digest\",\"show_cover_pic\":\"heihei\"}]}}");
  }

  public void testMpnewsBuild_with_media_id() {
    WxCpMessage reply = WxCpMessage.MPNEWS().toUser("OPENID").mediaId("mmm").build();

    assertEquals(reply.toJson(),
      "{\"touser\":\"OPENID\",\"msgtype\":\"mpnews\",\"mpnews\":{\"media_id\":\"mmm\"}}");
  }

}
