package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.vod.*;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertNotNull;

@Test
@Guice(modules = ApiTestModule.class)
public class WxMaVodServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testListMedia() throws Exception {
    WxMaVodListMediaRequest request = WxMaVodListMediaRequest.builder()
      .dramaId(100000)
      .offset(0)
      .limit(100)
      .build();
    List<WxMaVodMediaInfo> response = this.wxService.getWxMaVodService().listMedia(request);
    assertNotNull(response);
  }

  @Test
  public void testListDrama() throws Exception {
    WxMaVodListDramaRequest request = WxMaVodListDramaRequest.builder()
      .offset(0)
      .limit(100)
      .build();
    List<WxMaVodDramaInfo> response = this.wxService.getWxMaVodService().listDrama(request);
    assertNotNull(response);

  }

  @Test
  public void testGetMediaLink() throws Exception {
    WxMaVodGetMediaLinkRequest request = WxMaVodGetMediaLinkRequest.builder()
      .mediaId(10000)
      .build();
    WxMaVodMediaPlaybackInfo response = this.wxService.getWxMaVodService().getMediaLink(request);
    assertNotNull(response);
  }

  @Test
  public void testGetMedia() throws Exception {
    WxMaVodGetMediaRequest request = WxMaVodGetMediaRequest.builder()
      .mediaId(0)
      .build();
    WxMaVodMediaInfo response = this.wxService.getWxMaVodService().getMedia(request);
    assertNotNull(response);
  }

  @Test
  public void testDeleteMedia() throws Exception {
    WxMaVodDeleteMediaRequest request = WxMaVodDeleteMediaRequest.builder()
      .mediaId(0)
      .build();
    boolean response = this.wxService.getWxMaVodService().deleteMedia(request);
    assertNotNull(response);
  }

  @Test
  public void testGetDrama() throws Exception {
    WxMaVodGetDramaRequest request = WxMaVodGetDramaRequest.builder()
      .dramaId(0)
      .build();
    WxMaVodDramaInfo response = this.wxService.getWxMaVodService().getDrama(request);
    assertNotNull(response);
  }

  @Test
  public void testAuditDrama() throws Exception {
    WxMaVodAuditDramaRequest request = WxMaVodAuditDramaRequest.builder()
      .dramaId(0)
      .name("name")
      .build();
    Integer response = this.wxService.getWxMaVodService().auditDrama(request);
    assertNotNull(response);
  }

  @Test
  public void testGetTask() throws Exception {
    WxMaVodGetTaskRequest request = WxMaVodGetTaskRequest.builder()
      .taskId(0)
      .build();
    WxMaVodGetTaskResponse response = this.wxService.getWxMaVodService().getTask(request);
    assertNotNull(response);
  }

  @Test
  public void testPullUpload() throws Exception {
    WxMaVodPullUploadRequest request = WxMaVodPullUploadRequest.builder()
      .coverUrl("")
      .mediaUrl("")
      .mediaName("我的中国梦 - 第1集")
      .sourceContext("")
      .build();
    WxMaVodPullUploadResponse response = this.wxService.getWxMaVodService().pullUpload(request);
    assertNotNull(response);
  }
  @Test
  public void testGetCdnUsageData() throws Exception {
    WxMaVodGetCdnUsageRequest request = WxMaVodGetCdnUsageRequest.builder()
      .startTime(0L)
      .endTime(0L)
      .dataInterval(1440)
      .build();
    WxMaVodGetCdnUsageResponse response = this.wxService.getWxMaVodService().getCdnUsageData(request);
    assertNotNull(response);
  }

  @Test
  public void testGetCdnLogs() throws Exception {
    WxMaVodGetCdnLogRequest request = WxMaVodGetCdnLogRequest.builder()
      .startTime(0L).endTime(0L)
      .offset(0).limit(100)
      .build();
    WxMaVodGetCdnLogResponse response = this.wxService.getWxMaVodService().getCdnLogs(request);
    assertNotNull(response);
  }

}
