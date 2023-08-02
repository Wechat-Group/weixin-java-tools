package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.live.WxMaCreateRoomResult;
import cn.binarywang.wx.miniapp.bean.live.WxMaLiveResult;
import cn.binarywang.wx.miniapp.bean.live.WxMaLiveRoomInfo;
import cn.binarywang.wx.miniapp.bean.live.WxMaLiveSharedCode;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertNotNull;

/**
 * 测试直播相关的接口
 *
 * @author <a href="https://github.com/yjwang3300300">yjwang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaLiveServiceImplTest {

  @Inject
  private WxMaService wxService;

  @Test
  public void createRoom() throws Exception {
    //上传临时素材
    WxMediaUploadResult mediaUpload = this.wxService.getMediaService().uploadMedia("image", new File("E:\\1.png"));

    WxMaLiveRoomInfo roomInfo = new WxMaLiveRoomInfo();
    roomInfo.setName("订阅通知直播间");
    roomInfo.setCoverImg(mediaUpload.getMediaId());
    Calendar c = Calendar.getInstance();
    c.set(2023, Calendar.FEBRUARY, 10, 8, 0);
    roomInfo.setStartTime(c.getTimeInMillis() / 1000);
    c.set(2023, Calendar.FEBRUARY, 10, 12, 0);
    roomInfo.setEndTime(c.getTimeInMillis() / 1000);
    roomInfo.setAnchorName("鹏军_专业小程序开发");
    roomInfo.setAnchorWechat("pengjun939961241");
    roomInfo.setShareImg(mediaUpload.getMediaId());
    roomInfo.setFeedsImg(mediaUpload.getMediaId());
    roomInfo.setType(1);
    roomInfo.setScreenType(1);
    roomInfo.setCloseLike(0);
    roomInfo.setCloseGoods(0);
    roomInfo.setCloseComment(0);
    WxMaCreateRoomResult result = this.wxService.getLiveService().createRoom(roomInfo);
    assertNotNull(result);
    assertThat(result.getRoomId()).isNotNull();
  }

  @Test
  public void deletRoom() throws Exception {
    this.wxService.getLiveService().deleteRoom(29);
  }

  @Test
  public void editRoom() throws Exception {
    //上传临时素材
//    WxMediaUploadResult mediaUpload = this.wxService.getMediaService().uploadMedia("image", new File("E:\\1.png"));

    WxMaLiveRoomInfo roomInfo = new WxMaLiveRoomInfo();
    roomInfo.setId(39);
    roomInfo.setName("修改订阅通知直播间");
    roomInfo.setCoverImg("http://mmbiz.qpic.cn/mmbiz_png/omYktZNGamuBLBYlP2FjpIL2AHoiayH8HXeZRibtXDMesHn5aevEaM4etUVwfnX1HHqrXBDY3KPgT8MIlqbtqX8Q/0");
    Calendar c = Calendar.getInstance();
    c.set(2021, Calendar.SEPTEMBER, 10, 8, 0);
    roomInfo.setStartTime(c.getTimeInMillis() / 1000);
    c.set(2021, Calendar.SEPTEMBER, 10, 12, 0);
    roomInfo.setEndTime(c.getTimeInMillis() / 1000);
    roomInfo.setAnchorName("鹏军_专业小程序开发");
    roomInfo.setAnchorWechat("pengjun939961241");
    roomInfo.setShareImg("http://mmbiz.qpic.cn/mmbiz_png/omYktZNGamuBLBYlP2FjpIL2AHoiayH8HXeZRibtXDMesHn5aevEaM4etUVwfnX1HHqrXBDY3KPgT8MIlqbtqX8Q/0");
    roomInfo.setType(1);
    roomInfo.setScreenType(1);
    roomInfo.setCloseLike(0);
    roomInfo.setCloseGoods(0);
    roomInfo.setCloseComment(0);
    boolean editRoom = this.wxService.getLiveService().editRoom(roomInfo);
    System.out.println(editRoom);
  }
  @Test
  public void getPushUrl() throws Exception {
    String result = this.wxService.getLiveService().getPushUrl(39);
    System.out.println(result);
  }

  @Test
  public void getSharedCode() throws Exception {
    WxMaLiveSharedCode result = this.wxService.getLiveService().getSharedCode(39, null);
    System.out.println(result);
  }

  @Test
  public void getLiveInfo() throws Exception {
    WxMaLiveResult list = this.wxService.getLiveService().getLiveInfo(0, 10);
    assertNotNull(list);
    System.out.println(list.toString());
  }

  @Test
  public void getLiveReplay() throws Exception {
    // [12, 11, 10, 9, 8, 7, 6, 5, 3, 2]
    WxMaLiveResult list = this.wxService.getLiveService().getLiveReplay(3, 0, 10);
    assertNotNull(list);
    System.out.println(list.toString());
  }

  @Test
  public void getLiveinfos() throws Exception {
    List<WxMaLiveResult.RoomInfo> list = this.wxService.getLiveService().getLiveInfos();
    assertNotNull(list);
    System.out.println(list.toString());
  }

  @Test
  public void addGoodsToRoom() throws Exception {
    boolean result = this.wxService.getLiveService().addGoodsToRoom(5, Arrays.asList(8, 7, 5, 4, 10));
    System.out.println(result);
  }
}
