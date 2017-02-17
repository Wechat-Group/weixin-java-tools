package me.chanjar.weixin.mp.api;

import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpMassNews;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.WxMpMassTagMessage;
import me.chanjar.weixin.mp.bean.WxMpMassVideo;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.bean.result.WxMpMassUploadResult;
import org.testng.*;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * 测试群发消息
 *
 * @author chanjarster
 */
@Test(groups = "massAPI", dependsOnGroups = {"baseAPI", "mediaAPI", "groupAPI"})
@Guice(modules = ApiTestModule.class)
public class WxMpMassMessageAPITest {

  @Inject
  protected WxMpService wxService;

  @Test
  public void testTextMassOpenIdsMessageSend() throws WxErrorException {
    // 发送群发消息
    TestConfigStorage configProvider = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();
    WxMpMassOpenIdsMessage massMessage = new WxMpMassOpenIdsMessage();
    massMessage.setMsgType(WxConsts.MASS_MSG_TEXT);
    massMessage.setContent("测试群发消息\n欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>");
    massMessage.getToUsers().add(configProvider.getOpenid());

    WxMpMassSendResult massResult = this.wxService
      .massOpenIdsMessageSend(massMessage);
    Assert.assertNotNull(massResult);
    Assert.assertNotNull(massResult.getMsgId());
  }

  @Test(dataProvider = "massMessages")
  public void testMediaMassOpenIdsMessageSend(String massMsgType, String mediaId) throws WxErrorException {
    // 发送群发消息
    TestConfigStorage configProvider = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();
    WxMpMassOpenIdsMessage massMessage = new WxMpMassOpenIdsMessage();
    massMessage.setMsgType(massMsgType);
    massMessage.setMediaId(mediaId);
    massMessage.getToUsers().add(configProvider.getOpenid());

    WxMpMassSendResult massResult = this.wxService
      .massOpenIdsMessageSend(massMessage);
    Assert.assertNotNull(massResult);
    Assert.assertNotNull(massResult.getMsgId());
  }

  @Test
  public void testTextMassGroupMessageSend() throws WxErrorException {
    WxMpMassTagMessage massMessage = new WxMpMassTagMessage();
    massMessage.setMsgType(WxConsts.MASS_MSG_TEXT);
    massMessage.setContent("测试群发消息\n欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>");
    massMessage
      .setTagId(this.wxService.getUserTagService().tagGet().get(0).getId());

    WxMpMassSendResult massResult = this.wxService
      .massGroupMessageSend(massMessage);
    Assert.assertNotNull(massResult);
    Assert.assertNotNull(massResult.getMsgId());
  }

  @Test(dataProvider = "massMessages")
  public void testMediaMassGroupMessageSend(String massMsgType, String mediaId)
    throws WxErrorException {
    WxMpMassTagMessage massMessage = new WxMpMassTagMessage();
    massMessage.setMsgType(massMsgType);
    massMessage.setMediaId(mediaId);
    massMessage.setTagId(this.wxService.getUserTagService().tagGet().get(0).getId());

    WxMpMassSendResult massResult = this.wxService
      .massGroupMessageSend(massMessage);
    Assert.assertNotNull(massResult);
    Assert.assertNotNull(massResult.getMsgId());
  }

  @DataProvider
  public Object[][] massMessages() throws WxErrorException, IOException {
    Object[][] messages = new Object[4][];

    /*
     * 视频素材
     */
    try (InputStream inputStream = ClassLoader
      .getSystemResourceAsStream("mm.mp4")) {
      // 上传视频到媒体库
      WxMediaUploadResult uploadMediaRes = this.wxService.getMaterialService()
        .mediaUpload(WxConsts.MEDIA_VIDEO, WxConsts.FILE_MP4, inputStream);
      Assert.assertNotNull(uploadMediaRes);
      Assert.assertNotNull(uploadMediaRes.getMediaId());

      // 把视频变成可被群发的媒体
      WxMpMassVideo video = new WxMpMassVideo();
      video.setTitle("测试标题");
      video.setDescription("测试描述");
      video.setMediaId(uploadMediaRes.getMediaId());
      WxMpMassUploadResult uploadResult = this.wxService.massVideoUpload(video);
      Assert.assertNotNull(uploadResult);
      Assert.assertNotNull(uploadResult.getMediaId());
      messages[0] = new Object[]{WxConsts.MASS_MSG_VIDEO, uploadResult.getMediaId()};
    }

    /*
     * 图片素材
     */
    try (InputStream inputStream = ClassLoader
      .getSystemResourceAsStream("mm.jpeg")) {
      WxMediaUploadResult uploadMediaRes = this.wxService.getMaterialService()
        .mediaUpload(WxConsts.MEDIA_IMAGE, WxConsts.FILE_JPG, inputStream);
      Assert.assertNotNull(uploadMediaRes);
      Assert.assertNotNull(uploadMediaRes.getMediaId());
      messages[1] = new Object[]{WxConsts.MASS_MSG_IMAGE, uploadMediaRes.getMediaId()};
    }

    /*
     * 语音素材
     */
    try (InputStream inputStream = ClassLoader
      .getSystemResourceAsStream("mm.mp3")) {
      WxMediaUploadResult uploadMediaRes = this.wxService.getMaterialService()
        .mediaUpload(WxConsts.MEDIA_VOICE, WxConsts.FILE_MP3, inputStream);
      Assert.assertNotNull(uploadMediaRes);
      Assert.assertNotNull(uploadMediaRes.getMediaId());
      messages[2] = new Object[]{WxConsts.MASS_MSG_VOICE, uploadMediaRes.getMediaId()};
    }

    /*
     * 图文素材
     */
    try (InputStream inputStream = ClassLoader
      .getSystemResourceAsStream("mm.jpeg")) {
      // 上传照片到媒体库
      WxMediaUploadResult uploadMediaRes = this.wxService.getMaterialService()
        .mediaUpload(WxConsts.MEDIA_IMAGE, WxConsts.FILE_JPG, inputStream);
      Assert.assertNotNull(uploadMediaRes);
      Assert.assertNotNull(uploadMediaRes.getMediaId());

      // 上传图文消息
      WxMpMassNews news = new WxMpMassNews();
      WxMpMassNews.WxMpMassNewsArticle article1 = new WxMpMassNews.WxMpMassNewsArticle();
      article1.setTitle("标题1");
      article1.setContent("内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1");
      article1.setThumbMediaId(uploadMediaRes.getMediaId());
      news.addArticle(article1);

      WxMpMassNews.WxMpMassNewsArticle article2 = new WxMpMassNews.WxMpMassNewsArticle();
      article2.setTitle("标题2");
      article2.setContent("内容2内容2内容2内容2内容2内容2内容2内容2内容2内容2内容2内容2内容2内容2内容2内容2内容2内容2内容2内容2内容2");
      article2.setThumbMediaId(uploadMediaRes.getMediaId());
      article2.setShowCoverPic(true);
      article2.setAuthor("作者2");
      article2.setContentSourceUrl("www.baidu.com");
      article2.setDigest("摘要2");
      news.addArticle(article2);

      WxMpMassUploadResult massUploadResult = this.wxService
        .massNewsUpload(news);
      Assert.assertNotNull(massUploadResult);
      Assert.assertNotNull(uploadMediaRes.getMediaId());
      messages[3] = new Object[]{WxConsts.MASS_MSG_NEWS, massUploadResult.getMediaId()};
    }

    return messages;
  }

}
