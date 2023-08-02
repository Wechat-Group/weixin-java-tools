package me.chanjar.weixin.cp.api.impl;

import com.google.gson.Gson;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.*;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 企业微信 OA数据接口 测试用例
 *
 * @author Element & Wang_Wong
 */
@Slf4j
@Guice(modules = ApiTestModule.class)
public class WxCpOaServiceImplTest {

  /**
   * The Wx service.
   */
  @Inject
  protected WxCpService wxService;

  /**
   * The Gson.
   */
  @Inject
  protected Gson gson;

  /**
   * Test get checkin data.
   *
   * @throws ParseException   the parse exception
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCheckinData() throws ParseException, WxErrorException {
    Date startTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2019-04-11");
    Date endTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2019-05-10");

    List<WxCpCheckinData> results = wxService.getOaService()
      .getCheckinData(1, startTime, endTime, Lists.newArrayList("binary"));

    assertThat(results).isNotNull();

    System.out.println("results ");
    System.out.println(gson.toJson(results));

  }

  /**
   * Test get checkin day data.
   *
   * @throws ParseException   the parse exception
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCheckinDayData() throws ParseException, WxErrorException {
    Date startTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2021-06-30");
    Date endTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2021-07-31");

    List<WxCpCheckinDayData> results = wxService.getOaService()
      .getCheckinDayData(startTime, endTime, Lists.newArrayList("12003648"));

    assertThat(results).isNotNull();


    System.out.println("results ");
    System.out.println(gson.toJson(results));

  }

  /**
   * Test get checkin month data.
   *
   * @throws ParseException   the parse exception
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCheckinMonthData() throws ParseException, WxErrorException {
    Date startTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2021-07-01");
    Date endTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2021-07-31");

    List<WxCpCheckinMonthData> results = wxService.getOaService()
      .getCheckinMonthData(startTime, endTime, Lists.newArrayList("12003648"));

    assertThat(results).isNotNull();
    System.out.println("results ");
    System.out.println(gson.toJson(results));
  }

  /**
   * Test get checkin schedule data.
   *
   * @throws ParseException   the parse exception
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCheckinScheduleData() throws ParseException, WxErrorException {
    Date startTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2021-07-01");
    Date endTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2021-07-31");

    List<WxCpCheckinSchedule> results = wxService.getOaService()
      .getCheckinScheduleList(startTime, endTime, Lists.newArrayList("12003648"));

    assertThat(results).isNotNull();
    System.out.println("results ");
    System.out.println(gson.toJson(results));
  }

  /**
   * Test set checkin schedule list.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSetCheckinScheduleList() throws WxErrorException {
    WxCpSetCheckinSchedule wxCpSetCheckinSchedule = new WxCpSetCheckinSchedule();
    wxCpSetCheckinSchedule.setGroupId(3);
    wxCpSetCheckinSchedule.setYearmonth(202108);
    WxCpSetCheckinSchedule.Item item = new WxCpSetCheckinSchedule.Item();
    item.setScheduleId(0);
    item.setDay(20);
    item.setUserid("12003648");
    wxCpSetCheckinSchedule.setItems(Collections.singletonList(item));
    wxService.getOaService().setCheckinScheduleList(wxCpSetCheckinSchedule);
  }

  /**
   * Test get checkin option.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCheckinOption() throws WxErrorException {

    Date now = new Date();
    List<WxCpCheckinOption> results = wxService.getOaService().getCheckinOption(now, Lists.newArrayList("binary"));
    assertThat(results).isNotNull();
    System.out.println("results ");
    System.out.println(gson.toJson(results));
  }

  /**
   * Test get crop checkin option.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCropCheckinOption() throws WxErrorException {

    Date now = new Date();
    List<WxCpCropCheckinOption> results = wxService.getOaService().getCropCheckinOption();
    assertThat(results).isNotNull();
    System.out.println("results ");
    System.out.println(gson.toJson(results));
  }

  /**
   * Test get approval info.
   *
   * @throws WxErrorException the wx error exception
   * @throws ParseException   the parse exception
   */
  @Test
  public void testGetApprovalInfo() throws WxErrorException, ParseException {
    Date startTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2019-12-01");
    Date endTime = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.parse("2019-12-31");
    WxCpApprovalInfo result = wxService.getOaService().getApprovalInfo(startTime, endTime);

    assertThat(result).isNotNull();

    System.out.println("result ");
    System.out.println(gson.toJson(result));
  }

  /**
   * Test get approval detail.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetApprovalDetail() throws WxErrorException {
    String spNo = "201912020001";
    WxCpApprovalDetailResult result = wxService.getOaService().getApprovalDetail(spNo);

    assertThat(result).isNotNull();

    System.out.println("result ");
    System.out.println(gson.toJson(result));
  }

  /**
   * Test get template detail.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetTemplateDetail() throws WxErrorException {
    String templateId = "3TkZjxugodbqpEMk9j7X6h6zKqYkc7MxQrrFmT7H";
    WxCpOaApprovalTemplateResult result = wxService.getOaService().getTemplateDetail(templateId);
    assertThat(result).isNotNull();
    System.out.println("result ");
    System.out.println(gson.toJson(result));
  }

  /**
   * Test apply.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testApply() throws WxErrorException {
    this.wxService.getOaService().apply(new WxCpOaApplyEventRequest().setCreatorUserId("123"));
  }

  /**
   * 获取审批数据（旧）
   * https://developer.work.weixin.qq.com/document/path/91530
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetApprovalData() throws WxErrorException {

    // 提示：推荐使用新接口“批量获取审批单号”及“获取审批申请详情”，此接口后续将不再维护、逐步下线。
//    WxCpGetApprovalData approvalData = this.wxService.getOaService().getApprovalData(System.currentTimeMillis(),
//    System.currentTimeMillis() + 3600L, null);
//    log.info("返回数据：{}", approvalData.toJson());

    String text = "{\"errcode\":0,\"errmsg\":\"ok\",\"count\":3,\"total\":5,\"next_spnum\":201704240001," +
      "\"data\":[{\"spname\":\"报销\",\"apply_name\":\"报销测试\",\"apply_org\":\"报销测试企业\",\"approval_name\":[\"审批人测试\"]," +
      "\"notify_name\":[\"抄送人测试\"],\"sp_status\":1,\"sp_num\":201704200001," +
      "\"mediaids\":[\"WWCISP_G8PYgRaOVHjXWUWFqchpBqqqUpGj0OyR9z6WTwhnMZGCPHxyviVstiv_2fTG8YOJq8L8zJT2T2OvTebANV-2MQ" +
      "\"],\"apply_time\":1499153693,\"apply_user_id\":\"testuser\",\"expense\":{\"expense_type\":1,\"reason\":\"\"," +
      "\"item\":[{\"expenseitem_type\":6,\"time\":1492617600,\"sums\":9900,\"reason\":\"\"}]}," +
      "\"comm\":{\"apply_data\":\"{\\\"item-1492610773696\\\":{\\\"title\\\":\\\"abc\\\",\\\"type\\\":\\\"text\\\"," +
      "\\\"value\\\":\\\"\\\"}}\"}},{\"spname\":\"请假\",\"apply_name\":\"请假测试\",\"apply_org\":\"请假测试企业\"," +
      "\"approval_name\":[\"审批人测试\"],\"notify_name\":[\"抄送人测试\"],\"sp_status\":1,\"sp_num\":201704200004," +
      "\"apply_time\":1499153693,\"apply_user_id\":\"testuser\",\"leave\":{\"timeunit\":0,\"leave_type\":4," +
      "\"start_time\":1492099200,\"end_time\":1492790400,\"duration\":144,\"reason\":\"\"}," +
      "\"comm\":{\"apply_data\":\"{\\\"item-1492610773696\\\":{\\\"title\\\":\\\"abc\\\",\\\"type\\\":\\\"text\\\"," +
      "\\\"value\\\":\\\"\\\"}}\"}},{\"spname\":\"自定义审批\",\"apply_name\":\"自定义\",\"apply_org\":\"自定义测试企业\"," +
      "\"approval_name\":[\"自定义审批人\"],\"notify_name\":[\"自定义抄送人\"],\"sp_status\":1,\"sp_num\":201704240001," +
      "\"apply_time\":1499153693,\"apply_user_id\":\"testuser\"," +
      "\"comm\":{\"apply_data\":\"{\\\"item-1492610773696\\\":{\\\"title\\\":\\\"abc\\\",\\\"type\\\":\\\"text\\\"," +
      "\\\"value\\\":\\\"\\\"}}\"}}]}";
    WxCpGetApprovalData wxCpGetApprovalData = WxCpGetApprovalData.fromJson(text);
    log.info("返回数据2：{}", wxCpGetApprovalData.toJson());

  }

  /**
   * Test get dial record.
   */
  @Test
  public void testGetDialRecord() {
  }

  /**
   * 获取企业假期管理配置
   * https://developer.work.weixin.qq.com/document/path/93375
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetCorpConf() throws WxErrorException {
    WxCpCorpConfInfo corpConf = this.wxService.getOaService().getCorpConf();
    log.info(corpConf.toJson());
  }

  /**
   * 获取成员假期余额
   * https://developer.work.weixin.qq.com/document/path/93376
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetUserVacationQuota() throws WxErrorException {
    WxCpUserVacationQuota vacationQuota = this.wxService.getOaService().getUserVacationQuota("WangKai");
    log.info(vacationQuota.toJson());

    String text =
      "{\"errcode\":0,\"errmsg\":\"ok\",\"lists\":[{\"id\":1,\"assignduration\":0,\"usedduration\":0," +
        "\"leftduration\":604800,\"vacationname\":\"年假\"},{\"id\":2,\"assignduration\":0,\"usedduration\":0," +
        "\"leftduration\":1296000,\"vacationname\":\"事假\"},{\"id\":3,\"assignduration\":0,\"usedduration\":0," +
        "\"leftduration\":0,\"vacationname\":\"病假\"}]}";
    WxCpUserVacationQuota json = WxCpUserVacationQuota.fromJson(text);
    log.info("数据为：{}", json.toJson());

  }

  /**
   * 修改成员假期余额
   * https://developer.work.weixin.qq.com/document/path/93377
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSetOneUserQuota() throws WxErrorException {

    String text = "{\"errcode\":0,\"errmsg\":\"ok\"}";
    WxCpBaseResp resp = WxCpBaseResp.fromJson(text);
    log.info("返回结果为：{}", resp.toJson());

//    WxCpBaseResp wxCpBaseResp = this.wxService.getOaService().setOneUserQuota(, , , , );

  }

}
