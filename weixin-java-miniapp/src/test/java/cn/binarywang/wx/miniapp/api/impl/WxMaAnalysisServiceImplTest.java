package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaAnalysisService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.analysis.WxMaRetainInfo;
import cn.binarywang.wx.miniapp.bean.analysis.WxMaSummaryTrend;
import cn.binarywang.wx.miniapp.bean.analysis.WxMaUserPortrait;
import cn.binarywang.wx.miniapp.bean.analysis.WxMaVisitDistribution;
import cn.binarywang.wx.miniapp.bean.analysis.WxMaVisitPage;
import cn.binarywang.wx.miniapp.bean.analysis.WxMaVisitTrend;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-28
 */
@Guice(modules = ApiTestModule.class)
public class WxMaAnalysisServiceImplTest {
  @Inject
  private WxMaService wxMaService;

  @Test
  public void testGetDailySummaryTrend() throws Exception {
    final WxMaAnalysisService service = wxMaService.getAnalysisService();
    Date twoDaysAgo = DateUtils.addDays(new Date(), -2);
    List<WxMaSummaryTrend> trends = service.getDailySummaryTrend(twoDaysAgo, twoDaysAgo);
    assertEquals(1, trends.size());
    System.out.println(trends);
  }

  @Test
  public void testGetDailyVisitTrend() throws Exception {
    final WxMaAnalysisService service = wxMaService.getAnalysisService();
    Date twoDaysAgo = DateUtils.addDays(new Date(), -2);
    List<WxMaVisitTrend> trends = service.getDailyVisitTrend(twoDaysAgo, twoDaysAgo);
    assertEquals(1, trends.size());
    System.out.println(trends);
  }

  @Test
  public void testGetWeeklyVisitTrend() throws Exception {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    Date now = new Date();
    Date lastSunday = calendar.getTime();
    if (DateUtils.isSameDay(lastSunday, now)) {
      lastSunday = DateUtils.addDays(lastSunday, -7);
    }
    Date lastMonday = DateUtils.addDays(lastSunday, -6);

    final WxMaAnalysisService service = wxMaService.getAnalysisService();
    List<WxMaVisitTrend> trends = service.getWeeklyVisitTrend(lastMonday, lastSunday);
    assertEquals(1, trends.size());
    System.out.println(trends);
  }

  @Test
  public void testGetMonthlyVisitTrend() throws Exception {
    Date now = new Date();
    Date firstDayOfThisMonth = DateUtils.setDays(now, 1);
    Date lastDayOfLastMonth = DateUtils.addDays(firstDayOfThisMonth, -1);
    Date firstDayOfLastMonth = DateUtils.addMonths(firstDayOfThisMonth, -1);

    final WxMaAnalysisService service = wxMaService.getAnalysisService();
    List<WxMaVisitTrend> trends = service.getMonthlyVisitTrend(firstDayOfLastMonth, lastDayOfLastMonth);
    assertEquals(1, trends.size());
    System.out.println(trends);
  }

  @Test
  public void testGetVisitDistribution() throws Exception {
    final WxMaAnalysisService service = wxMaService.getAnalysisService();
    Date twoDaysAgo = DateUtils.addDays(new Date(), -2);
    WxMaVisitDistribution distribution = service.getVisitDistribution(twoDaysAgo, twoDaysAgo);
    assertNotNull(distribution);
    String date = DateFormatUtils.format(twoDaysAgo, "yyyyMMdd");
    assertEquals(date, distribution.getRefDate());
    assertTrue(distribution.getList().containsKey("access_source_session_cnt"));
    assertTrue(distribution.getList().containsKey("access_staytime_info"));
    assertTrue(distribution.getList().containsKey("access_depth_info"));
    System.out.println(distribution);
  }

  @Test
  public void testGetDailyRetainInfo() throws Exception {
    final WxMaAnalysisService service = wxMaService.getAnalysisService();
    Date twoDaysAgo = DateUtils.addDays(new Date(), -2);
    WxMaRetainInfo retainInfo = service.getDailyRetainInfo(twoDaysAgo, twoDaysAgo);
    assertNotNull(retainInfo);
    System.out.println(retainInfo);
  }

  @Test
  public void testGetWeeklyRetainInfo() throws Exception {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    Date now = new Date();
    Date lastSunday = calendar.getTime();
    if (DateUtils.isSameDay(lastSunday, now)) {
      lastSunday = DateUtils.addDays(lastSunday, -7);
    }
    Date lastMonday = DateUtils.addDays(lastSunday, -6);

    final WxMaAnalysisService service = wxMaService.getAnalysisService();
    WxMaRetainInfo retainInfo = service.getWeeklyRetainInfo(lastMonday, lastSunday);
    assertNotNull(retainInfo);
    System.out.println(retainInfo);
  }

  @Test
  public void testGetMonthlyRetainInfo() throws Exception {
    Date now = new Date();
    Date firstDayOfThisMonth = DateUtils.setDays(now, 1);
    Date lastDayOfLastMonth = DateUtils.addDays(firstDayOfThisMonth, -1);
    Date firstDayOfLastMonth = DateUtils.addMonths(firstDayOfThisMonth, -1);

    final WxMaAnalysisService service = wxMaService.getAnalysisService();
    WxMaRetainInfo retainInfo = service.getMonthlyRetainInfo(firstDayOfLastMonth, lastDayOfLastMonth);
    assertNotNull(retainInfo);
    System.out.println(retainInfo);
  }

  @Test
  public void testGetVisitPage() throws Exception {
    final WxMaAnalysisService service = wxMaService.getAnalysisService();
    Date twoDaysAgo = DateUtils.addDays(new Date(), -2);
    List<WxMaVisitPage> visitPages = service.getVisitPage(twoDaysAgo, twoDaysAgo);
    assertNotNull(visitPages);
    System.out.println(visitPages);
    System.out.println(visitPages.get(0).getPagePath());
    System.out.println(visitPages.get(0).getPageVisitPv());
  }

  @Test
  public void testGetUserPortrait() throws Exception {
    Date twoDaysAgo = DateUtils.addDays(new Date(), -2);
    Date eightDaysAgo = DateUtils.addDays(new Date(), -8);

    final WxMaAnalysisService service = wxMaService.getAnalysisService();
    WxMaUserPortrait portrait = service.getUserPortrait(eightDaysAgo, twoDaysAgo);
    assertNotNull(portrait);
    System.out.println(portrait);
  }
}
