package me.chanjar.weixin.cp.api.impl;

import com.google.gson.Gson;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpApprovalInfo;
import me.chanjar.weixin.cp.bean.WxCpCheckinData;
import me.chanjar.weixin.cp.bean.WxCpCheckinOption;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Element
 * @Package me.chanjar.weixin.cp.api.impl
 * @date 2019-04-20 13:46
 * @Description: TODO
 */

@Guice(modules = ApiTestModule.class)
public class WxCpOAServiceImplTest {

  @Inject
  protected WxCpService wxService;

  @Inject
  protected Gson gson;

  @Test
  public void testGetCheckinData() throws ParseException, WxErrorException {
    Date startTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2019-04-11");
    Date endTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2019-05-10");

    List<WxCpCheckinData> results = wxService.getOAService()
      .getCheckinData(1, startTime, endTime, Lists.newArrayList("binary"));

    assertThat(results).isNotNull();

    System.out.println("results ");
    System.out.println(gson.toJson(results));

  }

  @Test
  public void testGetCheckinOption() throws WxErrorException {

    Date now = new Date();
    List<WxCpCheckinOption> results = wxService.getOAService().getCheckinOption(now, Lists.newArrayList("binary"));
    assertThat(results).isNotNull();
    System.out.println("results ");
    System.out.println(gson.toJson(results));
  }

  @Test
  public void testGetApprovalInfo() throws WxErrorException, ParseException {
    Date startTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2019-04-11");
    Date endTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2019-05-10");
    WxCpApprovalInfo result = wxService.getOAService().getApprovalInfo(startTime,endTime,null,null,null);

    assertThat(result).isNotNull();

    System.out.println("result ");
    System.out.println(gson.toJson(result));
  }

}
