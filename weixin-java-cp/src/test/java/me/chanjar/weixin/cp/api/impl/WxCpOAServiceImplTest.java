package me.chanjar.weixin.cp.api.impl;

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
    Date starttime,endtime;
    List<String> userLists = new ArrayList<>();

    starttime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2019-01-01");
    endtime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2019-01-20");

    userLists.add("1");
    userLists.add("2");
    userLists.add("3");

    List<WxCpCheckinData> results = wxService.getOAService().getCheckinData(1, starttime,endtime,userLists);

    System.out.println("results ");
    System.out.println(gson.toJson(results));

  }

  @Test
  public void testGetCheckinOption() throws WxErrorException{

    Date now = new Date();
    List<String> userLists = new ArrayList<>();

    userLists.add("user@aliyun.com");

    List<WxCpCheckinOption> results = wxService.getOAService().getCheckinOption(now,userLists);

    System.out.println("results ");
    System.out.println(gson.toJson(results));
  }
}
