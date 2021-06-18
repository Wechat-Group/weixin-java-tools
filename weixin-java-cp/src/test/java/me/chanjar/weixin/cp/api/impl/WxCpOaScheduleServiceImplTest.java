package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.oa.WxCpOaSchedule;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Arrays;


/**
 * 单元测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-12-25
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxCpOaScheduleServiceImplTest {
  @Inject
  protected WxCpService wxService;

  @Test
  public void testAdd() throws WxErrorException {
    this.wxService.getOaScheduleService().add(new WxCpOaSchedule().setOrganizer("userid1")
      .setDescription("description").setStartTime(111111111111L).setEndTime(222222222222L)
      .setSummary("summary"), null);
  }

  @Test
  public void testUpdate() throws WxErrorException {
    this.wxService.getOaScheduleService().update(new WxCpOaSchedule().setScheduleId("2222").setOrganizer("userid1")
      .setDescription("description").setStartTime(111111111111L).setEndTime(222222222222L)
      .setSummary("summary"));
  }

  @Test
  public void testGetDetails() throws WxErrorException {
    this.wxService.getOaScheduleService().getDetails(Arrays.asList("11111"));
  }

  @Test
  public void testDelete() throws WxErrorException {
    this.wxService.getOaScheduleService().delete("111");
  }

  @Test
  public void testListByCalendar() throws WxErrorException {
    this.wxService.getOaScheduleService().listByCalendar("111", null, null);
  }
}
