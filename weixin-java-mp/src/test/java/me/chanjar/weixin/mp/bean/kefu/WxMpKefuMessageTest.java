package me.chanjar.weixin.mp.bean.kefu;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage.WxArticle;
import org.testng.*;
import org.testng.annotations.*;

@Test
public class WxMpKefuMessageTest {

  public void testTextReply() {
    WxMpKefuMessage reply = new WxMpKefuMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.KefuMsgType.TEXT);
    reply.setContent("sfsfdsdf");
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"sfsfdsdf\"}}");
  }

  public void testTextBuild() {
    WxMpKefuMessage reply = WxMpKefuMessage.TEXT().toUser("OPENID").content("sfsfdsdf").build();
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"sfsfdsdf\"}}");
  }

  public void testImageReply() {
    WxMpKefuMessage reply = new WxMpKefuMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.KefuMsgType.IMAGE);
    reply.setMediaId("MEDIA_ID");
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testImageBuild() {
    WxMpKefuMessage reply = WxMpKefuMessage.IMAGE().toUser("OPENID").mediaId("MEDIA_ID").build();
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testVoiceReply() {
    WxMpKefuMessage reply = new WxMpKefuMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.KefuMsgType.VOICE);
    reply.setMediaId("MEDIA_ID");
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testVoiceBuild() {
    WxMpKefuMessage reply = WxMpKefuMessage.VOICE().toUser("OPENID").mediaId("MEDIA_ID").build();
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testVideoReply() {
    WxMpKefuMessage reply = new WxMpKefuMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.KefuMsgType.VIDEO);
    reply.setMediaId("MEDIA_ID");
    reply.setThumbMediaId("MEDIA_ID");
    reply.setTitle("TITLE");
    reply.setDescription("DESCRIPTION");
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"MEDIA_ID\",\"thumb_media_id\":\"MEDIA_ID\",\"title\":\"TITLE\",\"description\":\"DESCRIPTION\"}}");
  }

  public void testVideoBuild() {
    WxMpKefuMessage reply = WxMpKefuMessage.VIDEO().toUser("OPENID").title("TITLE").mediaId("MEDIA_ID").thumbMediaId("MEDIA_ID").description("DESCRIPTION").build();
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"MEDIA_ID\",\"thumb_media_id\":\"MEDIA_ID\",\"title\":\"TITLE\",\"description\":\"DESCRIPTION\"}}");
  }

  public void testMusicReply() {
    WxMpKefuMessage reply = new WxMpKefuMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.KefuMsgType.MUSIC);
    reply.setThumbMediaId("MEDIA_ID");
    reply.setDescription("DESCRIPTION");
    reply.setTitle("TITLE");
    reply.setMusicUrl("MUSIC_URL");
    reply.setHqMusicUrl("HQ_MUSIC_URL");
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"music\",\"music\":{\"title\":\"TITLE\",\"description\":\"DESCRIPTION\",\"thumb_media_id\":\"MEDIA_ID\",\"musicurl\":\"MUSIC_URL\",\"hqmusicurl\":\"HQ_MUSIC_URL\"}}");
  }

  public void testMusicBuild() {
    WxMpKefuMessage reply = WxMpKefuMessage.MUSIC()
      .toUser("OPENID")
      .title("TITLE")
      .thumbMediaId("MEDIA_ID")
      .description("DESCRIPTION")
      .musicUrl("MUSIC_URL")
      .hqMusicUrl("HQ_MUSIC_URL")
      .build();
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"music\",\"music\":{\"title\":\"TITLE\",\"description\":\"DESCRIPTION\",\"thumb_media_id\":\"MEDIA_ID\",\"musicurl\":\"MUSIC_URL\",\"hqmusicurl\":\"HQ_MUSIC_URL\"}}");
  }

  public void testNewsReply() {
    WxMpKefuMessage reply = new WxMpKefuMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.KefuMsgType.NEWS);

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

    WxMpKefuMessage reply = WxMpKefuMessage.NEWS().toUser("OPENID").addArticle(article1).addArticle(article2).build();

    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"news\",\"news\":{\"articles\":[{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"},{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"}]}}");
  }

}
