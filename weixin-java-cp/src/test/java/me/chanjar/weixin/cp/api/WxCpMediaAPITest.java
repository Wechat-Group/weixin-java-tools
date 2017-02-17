package me.chanjar.weixin.cp.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.google.inject.Inject;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * 测试多媒体文件上传下载
 *
 * @author Daniel Qian
 */
//@Test(groups="mediaAPI", dependsOnGroups="baseAPI")
@Test
@Guice(modules = ApiTestModule.class)
public class WxCpMediaAPITest {

  @Inject
  protected WxCpServiceImpl wxService;

  private List<String> media_ids = new ArrayList<>();

  @Test(dataProvider = "uploadMedia")
  public void testUploadMedia(String mediaType, String fileType, String fileName) throws WxErrorException, IOException {
    try (InputStream inputStream = ClassLoader
        .getSystemResourceAsStream(fileName);) {
      WxMediaUploadResult res = this.wxService.mediaUpload(mediaType, fileType,
          inputStream);
      Assert.assertNotNull(res.getType());
      Assert.assertNotNull(res.getCreatedAt());
      Assert.assertTrue(
          res.getMediaId() != null || res.getThumbMediaId() != null);

      if (res.getMediaId() != null) {
        this.media_ids.add(res.getMediaId());
      }
      if (res.getThumbMediaId() != null) {
        this.media_ids.add(res.getThumbMediaId());
      }
    }
  }

  @DataProvider
  public Object[][] uploadMedia() {
    return new Object[][]{
            new Object[]{WxConsts.MEDIA_IMAGE, WxConsts.FILE_JPG, "mm.jpeg"},
            new Object[]{WxConsts.MEDIA_VOICE, WxConsts.FILE_MP3, "mm.mp3"},
            new Object[]{WxConsts.MEDIA_VIDEO, WxConsts.FILE_MP4, "mm.mp4"},
            new Object[]{WxConsts.MEDIA_FILE, WxConsts.FILE_JPG, "mm.jpeg"}
    };
  }

  @Test(dependsOnMethods = {"testUploadMedia"}, dataProvider = "downloadMedia")
  public void testDownloadMedia(String media_id) throws WxErrorException {
    this.wxService.mediaDownload(media_id);
  }

  @DataProvider
  public Object[][] downloadMedia() {
    Object[][] params = new Object[this.media_ids.size()][];
    for (int i = 0; i < this.media_ids.size(); i++) {
      params[i] = new Object[]{this.media_ids.get(i)};
    }
    return params;
  }

}
