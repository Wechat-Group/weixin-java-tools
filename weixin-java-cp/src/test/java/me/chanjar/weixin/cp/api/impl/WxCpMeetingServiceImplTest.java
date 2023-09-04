package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpMeetingService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.oa.meeting.WxCpMeeting;
import me.chanjar.weixin.cp.bean.oa.meeting.WxCpMeetingUpdateResult;
import me.chanjar.weixin.cp.bean.oa.meeting.WxCpUserMeetingIdResult;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;


/**
 * 单元测试类.
 *
 * @author <a href="https://github.com/wangmeng3486">wangmeng3486</a> created on  2023-02-03
 */
@Test
@Slf4j
@Guice(modules = ApiTestModule.class)
public class WxCpMeetingServiceImplTest {
  /**
   * The Wx service.
   */
  @Inject
  private WxCpService wxCpService;
  /**
   * The Config storage.
   */
  @Inject
  protected ApiTestModule.WxXmlCpInMemoryConfigStorage configStorage;

  /**
   * Test
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testAdd() throws WxErrorException {
    WxCpMeetingService wxCpMeetingService = this.wxCpService.getMeetingService();
    /*
      测试 创建会议
     */
    long startTime = System.currentTimeMillis() / 1000 + 30 * 60L;
    List<String> userIds = Lists.newArrayList(this.configStorage.getUserId(), "lisi");
    WxCpMeeting wxCpMeeting = new WxCpMeeting().setAdminUserId(this.configStorage.getUserId()).setTitle("新建会议")
      .setMeetingStart(startTime).setMeetingDuration(3600).setDescription("新建会议描述").setLocation("广州媒体港")
      .setAttendees(new WxCpMeeting.Attendees().setUserId(userIds))
      .setSettings(new WxCpMeeting.Setting().setRemindScope(1).setPassword("1234").setEnableWaitingRoom(false)
        .setAllowEnterBeforeHost(true).setEnableEnterMute(1).setAllowExternalUser(false).setEnableScreenWatermark(false)
        .setHosts(new WxCpMeeting.Attendees().setUserId(userIds)).setRingUsers(new WxCpMeeting.Attendees().setUserId(userIds)))
      .setReminders(new WxCpMeeting.Reminder().setIsRepeat(1).setRepeatType(0).setRepeatUntil(startTime + 24 * 60 * 60L)
        .setRepeatInterval(1).setRemindBefore(Lists.newArrayList(0, 900)));
    String meetingId = "hyXG0RCQAAogMgFb9Tx_b-1-lhJRWvvg";// wxCpMeetingService.create(wxCpMeeting);
    assertThat(meetingId).isNotNull();
    /*
      测试 获取用户会议列表
     */
    wxCpMeeting.setMeetingId(meetingId);
    WxCpUserMeetingIdResult wxCpUserMeetingIdResult = wxCpMeetingService.getUserMeetingIds(this.configStorage.getUserId(), null, null, startTime, null);
    assertThat(wxCpUserMeetingIdResult.getMeetingIdList()).isNotNull();
    log.info("获取用户会议列表: {}", wxCpUserMeetingIdResult.toJson());
    /*
      测试 修改会议
     */
    wxCpMeeting.setTitle("修改会议");
    wxCpMeeting.setDescription("修改会议描述");
    WxCpMeetingUpdateResult wxCpMeetingUpdateResult = wxCpMeetingService.update(wxCpMeeting);
    assertEquals(wxCpMeetingUpdateResult.getErrcode().longValue(), 0L);
    /*
      测试 获取会议详情
     */
    WxCpMeeting wxCpMeetingResult = wxCpMeetingService.getDetail(meetingId);
    log.info("获取会议详情: {}", wxCpMeetingResult.toJson());
      /*
      测试 取消会议
     */
    wxCpMeetingService.cancel(meetingId);
  }
}
