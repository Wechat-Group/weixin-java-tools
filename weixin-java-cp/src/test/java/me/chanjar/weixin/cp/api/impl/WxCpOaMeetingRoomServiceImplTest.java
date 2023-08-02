package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.oa.meetingroom.WxCpOaMeetingRoom;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 单元测试.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020-09-20
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxCpOaMeetingRoomServiceImplTest {
  /**
   * The Wx service.
   */
  @Inject
  protected WxCpService wxService;

  /**
   * Test add.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testAdd() throws WxErrorException {
    this.wxService.getOaMeetingRoomService().addMeetingRoom(WxCpOaMeetingRoom.builder()
      .building("腾讯大厦")
      .capacity(10)
      .city("深圳")
      .name("18F-会议室")
      .floor("18F")
      .equipment(Arrays.asList(1, 2))
//        .coordinate()
      .build());

  }

  /**
   * Test update.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testUpdate() throws WxErrorException {
    this.wxService.getOaMeetingRoomService().editMeetingRoom(WxCpOaMeetingRoom.builder()
      .building("腾讯大厦")
      .capacity(10)
      .city("深圳")
      .name("16F-会议室")
      .floor("16F")
      .equipment(Arrays.asList(1, 2, 3))
      .meetingroomId(1)
      .build());
  }

  /**
   * Test get.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGet() throws WxErrorException {
    final List<WxCpOaMeetingRoom> meetingRooms =
      this.wxService.getOaMeetingRoomService().listMeetingRoom(WxCpOaMeetingRoom.builder()
      .building("腾讯大厦")
      .city("深圳")
      .equipment(Arrays.asList(1, 2))
      .build());
    assertThat(meetingRooms).isNotEmpty();
  }

  /**
   * Test delete.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testDelete() throws WxErrorException {
    Integer calId = 1;
    this.wxService.getOaMeetingRoomService().deleteMeetingRoom(calId);
  }
}
