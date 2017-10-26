package cn.binarywang.wx.miniapp.bean;

import me.chanjar.weixin.common.api.WxConsts;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
public class WxMaKefuMessageTest {

  public void testTextReply() {
    WxMaKefuMessage reply = new WxMaKefuMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.CUSTOM_MSG_TEXT);
    reply.setContent("sfsfdsdf");
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"sfsfdsdf\"}}");
  }

  public void testTextBuild() {
    WxMaKefuMessage reply = WxMaKefuMessage.newTextBuilder().toUser("OPENID").content("sfsfdsdf").build();
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"sfsfdsdf\"}}");
  }

  public void testImageReply() {
    WxMaKefuMessage reply = new WxMaKefuMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WxConsts.CUSTOM_MSG_IMAGE);
    reply.setMediaId("MEDIA_ID");
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testImageBuild() {
    WxMaKefuMessage reply = WxMaKefuMessage.newImageBuilder().toUser("OPENID").mediaId("MEDIA_ID").build();
    Assert.assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"MEDIA_ID\"}}");
  }


}
