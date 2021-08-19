package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.TestConstants;
import me.chanjar.weixin.cp.api.WxCpService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

/**
 * Created by Binary Wang on 2017-6-25.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
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
      //new Object[]{WxConsts.MediaFileType.VOICE, TestConstants.FILE_MP3, "mm.mp3"},
      // {"errcode":301017,"errmsg":"voice file only support amr like myvoice.amr"}
      new Object[]{WxConsts.MediaFileType.VOICE, TestConstants.FILE_AMR, "mm.amr"},
      new Object[]{WxConsts.MediaFileType.VIDEO, TestConstants.FILE_MP4, "mm.mp4"},
      new Object[]{WxConsts.MediaFileType.FILE, TestConstants.FILE_JPG, "mm.jpeg"}
    };
  }

  @Test(dataProvider = "mediaData")
  public void testUploadMedia(String mediaType, String fileType, String fileName) throws WxErrorException, IOException {
    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName)) {
      WxMediaUploadResult res = this.wxService.getMediaService().upload(mediaType, fileType, inputStream);
      assertThat(res).isNotNull();
      assertThat(res.getType()).isNotEmpty();
      assertThat(res.getCreatedAt()).isGreaterThan(0);
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
  public void testDownload(String mediaId) throws WxErrorException {
    byte[] bytes = this.wxService.getMediaService().download(mediaId);
    assertThat(bytes).isNotNull();
    System.out.println(new String(bytes));
  }

  @Test
  public void testUploadImg() throws WxErrorException {
    URL url = ClassLoader.getSystemResource("mm.jpeg");
    String res = this.wxService.getMediaService().uploadImg(new File(url.getFile()));
    assertThat(res).isNotEmpty();
  }

  @Test
  public void testGetJssdkFile() throws WxErrorException {
    byte[] bytes = this.wxService.getMediaService().getJssdkFile("....");
    assertThat(bytes).isNotNull();
    System.out.println(new String(bytes));
  }
}
