package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.oa.calendar.WxCpOaCalendar;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 单元测试.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-09-20
 */

@Test
@Guice(modules = ApiTestModule.class)
public class WxCpOaCalendarServiceImplTest {
  @Inject
  protected WxCpService wxService;
  private final String calId = "wcbBJNCQAARipW967iE8DKPAp5Kb96qQ";

  @Test
  public void testAdd() throws WxErrorException {
    this.wxService.getOaCalendarService().add(WxCpOaCalendar.builder()
      .organizer("binary")
      .readonly(1)
      .setAsDefault(1)
      .summary("test_summary")
      .color("#FF3030")
      .description("test_describe")
      .shares(Arrays.asList(new WxCpOaCalendar.ShareInfo("binary", null),
        new WxCpOaCalendar.ShareInfo("binary", 1)))
      .build());
  }

  @Test
  public void testUpdate() throws WxErrorException {
    this.wxService.getOaCalendarService().update(WxCpOaCalendar.builder()
      .calId(calId)
      .organizer("binary")
      .readonly(1)
      .setAsDefault(1)
      .summary("test_summary")
      .color("#FF3030")
      .description("test_describe")
      .shares(Arrays.asList(new WxCpOaCalendar.ShareInfo("binary", null),
        new WxCpOaCalendar.ShareInfo("binary", 1)))
      .build());
  }

  @Test
  public void testGet() throws WxErrorException {
    final List<WxCpOaCalendar> calendars = this.wxService.getOaCalendarService().get(Arrays.asList(calId));
    assertThat(calendars).isNotEmpty();
  }

  @Test
  public void testDelete() throws WxErrorException {
    this.wxService.getOaCalendarService().delete(calId);
  }
}
