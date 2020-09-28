package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.oa.calendar.WxCpOaCalendar;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Arrays;

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

  @Test
  public void testAdd() throws WxErrorException {
    this.wxService.getOaCalendarService().add(WxCpOaCalendar.builder()
      .organizer("userid1")
      .readonly(1)
      .setAsDefault(1)
      .summary("test_summary")
      .color("#FF3030")
      .description("test_describe")
      .shares(Arrays.asList(new WxCpOaCalendar.ShareInfo("userid2", null),
        new WxCpOaCalendar.ShareInfo("userid3", 1)))
      .build());
  }
}
