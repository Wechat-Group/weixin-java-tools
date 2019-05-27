package me.chanjar.weixin.cp.api.impl;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpCheckinData;
import me.chanjar.weixin.cp.bean.WxCpCheckinOption;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Element
 * @date 2019-04-20 13:46
 */
@Guice(modules = ApiTestModule.class)
public class WxCpOAServiceImplTest {
  @Inject
  protected WxCpService wxService;

  @Test
  public void testGetCheckinData() throws ParseException, WxErrorException {
    Date startTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2019-04-11");
    Date endTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2019-05-10");

    List<WxCpCheckinData> results = wxService.getOAService()
      .getCheckinData(1, startTime, endTime, Lists.newArrayList("binary"));

    assertThat(results).isNotNull();
  }

  @Test
  public void testGetCheckinOption() throws WxErrorException {
    Date now = new Date();
    List<WxCpCheckinOption> results = wxService.getOAService()
      .getCheckinOption(now, Lists.newArrayList("binary"));
    assertThat(results).isNotNull();
  }
}
