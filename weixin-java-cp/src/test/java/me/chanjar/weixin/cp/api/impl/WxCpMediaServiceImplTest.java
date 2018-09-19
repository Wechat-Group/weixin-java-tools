package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.TestConstants;
import me.chanjar.weixin.cp.api.WxCpService;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * <pre>
 *
 * Created by Binary Wang on 2017-6-25.
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * </pre>
 */
@Guice(modules = ApiTestModule.class)
public class WxCpMediaServiceImplTest {
  @Inject
  private WxCpService wxService;

  private List<String> mediaIds = new ArrayList<>();

  @DataProvider
  public Object[][] mediaData() {
    return new Object[][]{
      new Object[]{WxConsts.MediaFileType.IMAGE, TestConstants.FILE_JPG, "mm.jpeg"},
      new Object[]{WxConsts.MediaFileType.VOICE, TestConstants.FILE_MP3, "mm.mp3"},
      new Object[]{WxConsts.MediaFileType.VOICE, TestConstants.FILE_AMR, "mm.amr"},//{"errcode":301017,"errmsg":"voice file only support amr like myvoice.amr"}
      new Object[]{WxConsts.MediaFileType.VIDEO, TestConstants.FILE_MP4, "mm.mp4"},
      new Object[]{WxConsts.MediaFileType.FILE, TestConstants.FILE_JPG, "mm.jpeg"}
    };
  }

  @Test(dataProvider = "mediaData")
  public void testUploadMedia(String mediaType, String fileType, String fileName) throws WxErrorException, IOException {
    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName)) {
      WxMediaUploadResult res = this.wxService.getMediaService().upload(mediaType, fileType, inputStream);
      assertNotNull(res.getType());
      assertNotNull(res.getCreatedAt());
      assertTrue(res.getMediaId() != null || res.getThumbMediaId() != null);

      if (res.getMediaId() != null) {
        this.mediaIds.add(res.getMediaId());
      }
      if (res.getThumbMediaId() != null) {
        this.mediaIds.add(res.getThumbMediaId());
      }
    }
  }

  @DataProvider
  public Object[][] downloadMedia() {
    Object[][] params = new Object[this.mediaIds.size()][];
    for (int i = 0; i < this.mediaIds.size(); i++) {
      params[i] = new Object[]{this.mediaIds.get(i)};
    }
    return params;
  }

  @Test(dependsOnMethods = {"testUploadMedia"}, dataProvider = "downloadMedia")
  public void testDownloadMedia(String media_id) throws WxErrorException {
    File file = this.wxService.getMediaService().download(media_id);
    assertNotNull(file);
    System.out.println(file);
  }

}
