package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaGetLiveInfo;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

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
  public void getLiveInfo() throws Exception {
    WxMaGetLiveInfo list = this.wxService.getLiveService().getLiveInfo(0,10);
    assertNotNull(list);
    System.out.println(list.toString());
  }

  @Test
  public void getLiveReplay() throws Exception {
    // [12, 11, 10, 9, 8, 7, 6, 5, 3, 2]
    WxMaGetLiveInfo list = this.wxService.getLiveService().getLiveReplay(11,0,10);
    assertNotNull(list);
    System.out.println(list.toString());
  }

  @Test
  public void getLiveinfos() throws Exception {
    List<WxMaGetLiveInfo.RoomInfo> list = this.wxService.getLiveService().getLiveinfos();
    assertNotNull(list);
    System.out.println(list.toString());
  }
}
