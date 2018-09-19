package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.datacube.*;
import org.apache.commons.lang3.time.FastDateFormat;
import org.testng.*;
import org.testng.annotations.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 测试统计分析相关的接口
 * Created by Binary Wang on 2016/8/23.
 *
 * @author binarywang (https://github.com/binarywang)
 */
@Guice(modules = ApiTestModule.class)
public class WxMpDataCubeServiceImplTest {
  @Inject
  protected WxMpService wxService;
  private FastDateFormat simpleDateFormat = FastDateFormat
    .getInstance("yyyy-MM-dd");

  @DataProvider
  public Object[][] oneDay() throws ParseException {
    return new Object[][]{{this.simpleDateFormat.parse("2016-08-22")}};
  }

  @DataProvider
  public Object[][] threeDays() throws ParseException {
    return new Object[][]{{this.simpleDateFormat.parse("2016-08-20"),
      this.simpleDateFormat.parse("2016-08-22")}};
  }

  @DataProvider
  public Object[][] sevenDays() throws ParseException {
    return new Object[][]{{this.simpleDateFormat.parse("2016-08-16"),
      this.simpleDateFormat.parse("2016-08-22")}};
  }

  @DataProvider
  public Object[][] fifteenDays() throws ParseException {
    return new Object[][]{{this.simpleDateFormat.parse("2016-08-14"),
      this.simpleDateFormat.parse("2016-08-27")}};
  }

  @DataProvider
  public Object[][] thirtyDays() throws ParseException {
    return new Object[][]{{this.simpleDateFormat.parse("2016-07-30"),
      this.simpleDateFormat.parse("2016-08-27")}};
  }

  @Test(dataProvider = "sevenDays")
  public void testGetUserSummary(Date beginDate, Date endDate)
    throws WxErrorException {
    List<WxDataCubeUserSummary> summaries = this.wxService.getDataCubeService()
      .getUserSummary(beginDate, endDate);
    Assert.assertNotNull(summaries);
    System.out.println(summaries);
  }

  @Test(dataProvider = "sevenDays")
  public void testGetUserCumulate(Date beginDate, Date endDate)
    throws WxErrorException {
    List<WxDataCubeUserCumulate> result = this.wxService.getDataCubeService()
      .getUserCumulate(beginDate, endDate);
    Assert.assertNotNull(result);
    System.out.println(result);
  }

  @Test(dataProvider = "oneDay")
  public void testGetArticleSummary(Date date) throws WxErrorException {
    List<WxDataCubeArticleResult> results = this.wxService.getDataCubeService()
      .getArticleSummary(date, date);
    Assert.assertNotNull(results);
    System.out.println(results);
  }

  /**
   * TODO 该接口，暂时没找到有数据的日期，无法测试验证
   */
  @Test(dataProvider = "oneDay")
  public void testGetArticleTotal(Date date) throws WxErrorException {
    List<WxDataCubeArticleTotal> results = this.wxService.getDataCubeService()
      .getArticleTotal(date, date);
    Assert.assertNotNull(results);
    System.out.println(results);
  }

  @Test(dataProvider = "threeDays")
  public void testGetUserRead(Date beginDate, Date endDate)
    throws WxErrorException {
    List<WxDataCubeArticleResult> results = this.wxService.getDataCubeService()
      .getUserRead(beginDate, endDate);
    Assert.assertNotNull(results);
    System.out.println(results);
  }

  @Test(dataProvider = "oneDay")
  public void testGetUserReadHour(Date date) throws WxErrorException {
    List<WxDataCubeArticleResult> results = this.wxService.getDataCubeService()
      .getUserReadHour(date, date);
    Assert.assertNotNull(results);
    System.out.println(results);
  }

  @Test(dataProvider = "sevenDays")
  public void testGetUserShare(Date beginDate, Date endDate)
    throws WxErrorException {
    List<WxDataCubeArticleResult> results = this.wxService.getDataCubeService()
      .getUserShare(beginDate, endDate);
    Assert.assertNotNull(results);
    System.out.println(results);
  }

  @Test(dataProvider = "oneDay")
  public void testGetUserShareHour(Date date) throws WxErrorException {
    List<WxDataCubeArticleResult> results = this.wxService.getDataCubeService()
      .getUserShareHour(date, date);
    Assert.assertNotNull(results);
    System.out.println(results);
  }

  @Test(dataProvider = "sevenDays")
  public void testGetUpstreamMsg(Date beginDate, Date endDate) throws WxErrorException {
    List<WxDataCubeMsgResult> results = this.wxService.getDataCubeService()
      .getUpstreamMsg(beginDate, endDate);
    Assert.assertNotNull(results);
    System.out.println(results);
  }

  @Test(dataProvider = "oneDay")
  public void testGetUpstreamMsgHour(Date date) throws WxErrorException {
    List<WxDataCubeMsgResult> results = this.wxService.getDataCubeService()
      .getUpstreamMsgHour(date, date);
    Assert.assertNotNull(results);
    System.out.println(results);
  }

  @Test(dataProvider = "thirtyDays")
  public void testGetUpstreamMsgWeek(Date beginDate, Date endDate) throws WxErrorException {
    List<WxDataCubeMsgResult> results = this.wxService.getDataCubeService()
      .getUpstreamMsgWeek(beginDate, endDate);
    Assert.assertNotNull(results);
    System.out.println(results);
  }

  /**
   * TODO 该接口，暂时没找到有数据的日期，无法测试验证
   */
  @Test(dataProvider = "thirtyDays")
  public void testGetUpstreamMsgMonth(Date beginDate, Date endDate) throws WxErrorException {
    List<WxDataCubeMsgResult> results = this.wxService.getDataCubeService()
      .getUpstreamMsgMonth(beginDate, endDate);
    Assert.assertNotNull(results);
    System.out.println(results);
  }

  @Test(dataProvider = "fifteenDays")
  public void testGetUpstreamMsgDist(Date beginDate, Date endDate) throws WxErrorException {
    List<WxDataCubeMsgResult> results = this.wxService.getDataCubeService()
      .getUpstreamMsgDist(beginDate, endDate);
    Assert.assertNotNull(results);
    System.out.println(results);
  }

  @Test(dataProvider = "thirtyDays")
  public void testGetUpstreamMsgDistWeek(Date beginDate, Date endDate) throws WxErrorException {
    List<WxDataCubeMsgResult> results = this.wxService.getDataCubeService()
      .getUpstreamMsgDistWeek(beginDate, endDate);
    Assert.assertNotNull(results);
    System.out.println(results);
  }

  /**
   * TODO 该接口，暂时没找到有数据的日期，无法测试验证
   */
  @Test(dataProvider = "thirtyDays")
  public void testGetUpstreamMsgDistMonth(Date beginDate, Date endDate) throws WxErrorException {
    List<WxDataCubeMsgResult> results = this.wxService.getDataCubeService()
      .getUpstreamMsgDistMonth(beginDate, endDate);
    Assert.assertNotNull(results);
    System.out.println(results);
  }

  @Test(dataProvider = "thirtyDays")
  public void testGetInterfaceSummary(Date beginDate, Date endDate) throws WxErrorException {
    List<WxDataCubeInterfaceResult> results = this.wxService.getDataCubeService()
      .getInterfaceSummary(beginDate, endDate);
    Assert.assertNotNull(results);
    System.out.println(results);
  }

  @Test(dataProvider = "oneDay")
  public void testGetInterfaceSummaryHour(Date date) throws WxErrorException {
    List<WxDataCubeInterfaceResult> results = this.wxService.getDataCubeService()
      .getInterfaceSummaryHour(date, date);
    Assert.assertNotNull(results);
    System.out.println(results);
  }
}
