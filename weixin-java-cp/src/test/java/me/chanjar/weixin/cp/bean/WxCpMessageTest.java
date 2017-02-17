package me.chanjar.weixin.cp.bean;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.WxCpMessage.WxArticle;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WxCpMessageTest {

  public void testTextReply() {
    WxCpMessage reply = new WxCpMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.CUSTOM_MSG_TEXT);
    reply.setContent("sfsfdsdf");
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"sfsfdsdf\"}}");
  }

  public void testTextBuild() {
    WxCpMessage reply = WxCpMessage.TEXT().toUser("OPENID").content("sfsfdsdf").build();
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"sfsfdsdf\"}}");
  }

  public void testImageReply() {
    WxCpMessage reply = new WxCpMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.CUSTOM_MSG_IMAGE);
    reply.setMediaId("MEDIA_ID");
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testImageBuild() {
    WxCpMessage reply = WxCpMessage.IMAGE().toUser("OPENID").mediaId("MEDIA_ID").build();
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testVoiceReply() {
    WxCpMessage reply = new WxCpMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.CUSTOM_MSG_VOICE);
    reply.setMediaId("MEDIA_ID");
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testVoiceBuild() {
    WxCpMessage reply = WxCpMessage.VOICE().toUser("OPENID").mediaId("MEDIA_ID").build();
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testVideoReply() {
    WxCpMessage reply = new WxCpMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.CUSTOM_MSG_VIDEO);
    reply.setMediaId("MEDIA_ID");
    reply.setThumbMediaId("MEDIA_ID");
    reply.setTitle("TITLE");
    reply.setDescription("DESCRIPTION");
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"MEDIA_ID\",\"thumb_media_id\":\"MEDIA_ID\",\"title\":\"TITLE\",\"description\":\"DESCRIPTION\"}}");
  }

  public void testVideoBuild() {
    WxCpMessage reply = WxCpMessage.VIDEO().toUser("OPENID").title("TITLE").mediaId("MEDIA_ID").thumbMediaId("MEDIA_ID").description("DESCRIPTION").build();
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"MEDIA_ID\",\"thumb_media_id\":\"MEDIA_ID\",\"title\":\"TITLE\",\"description\":\"DESCRIPTION\"}}");
  }

  public void testNewsReply() {
    WxCpMessage reply = new WxCpMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.CUSTOM_MSG_NEWS);

    WxArticle article1 = new WxArticle();
    article1.setUrl("URL");
    article1.setPicUrl("PIC_URL");
    article1.setDescription("Is Really A Happy Day");
    article1.setTitle("Happy Day");
    reply.getArticles().add(article1);

    WxArticle article2 = new WxArticle();
    article2.setUrl("URL");
    article2.setPicUrl("PIC_URL");
    article2.setDescription("Is Really A Happy Day");
    article2.setTitle("Happy Day");
    reply.getArticles().add(article2);


    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"news\",\"news\":{\"articles\":[{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"},{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"}]}}");
  }

  public void testNewsBuild() {
    WxArticle article1 = new WxArticle();
    article1.setUrl("URL");
    article1.setPicUrl("PIC_URL");
    article1.setDescription("Is Really A Happy Day");
    article1.setTitle("Happy Day");

    WxArticle article2 = new WxArticle();
    article2.setUrl("URL");
    article2.setPicUrl("PIC_URL");
    article2.setDescription("Is Really A Happy Day");
    article2.setTitle("Happy Day");

    WxCpMessage reply = WxCpMessage.NEWS().toUser("OPENID").addArticle(article1).addArticle(article2).build();

    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"news\",\"news\":{\"articles\":[{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"},{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"}]}}");
  }

}
