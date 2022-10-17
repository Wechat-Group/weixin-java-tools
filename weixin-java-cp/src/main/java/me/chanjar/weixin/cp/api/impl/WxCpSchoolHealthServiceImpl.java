package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpSchoolHealthService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.school.health.WxCpGetHealthReportStat;
import me.chanjar.weixin.cp.bean.school.health.WxCpGetReportAnswer;
import me.chanjar.weixin.cp.bean.school.health.WxCpGetReportJobIds;
import me.chanjar.weixin.cp.bean.school.health.WxCpGetReportJobInfo;

import java.util.Optional;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.School.*;

/**
 * 企业微信家校应用 健康上报接口实现类.
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a> created on : 2022/5/31 9:16
 */
@Slf4j
@RequiredArgsConstructor
public class WxCpSchoolHealthServiceImpl implements WxCpSchoolHealthService {

  private final WxCpService cpService;

  @Override
  public WxCpGetHealthReportStat getHealthReportStat(@NonNull String date) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_HEALTH_REPORT_STAT);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("date", date);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpGetHealthReportStat.fromJson(responseContent);
  }

  @Override
  public WxCpGetReportJobIds getReportJobIds(Integer offset, Integer limit) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_REPORT_JOBIDS);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("offset", Optional.ofNullable(offset).orElse(0));
    jsonObject.addProperty("limit", Optional.ofNullable(limit).orElse(100));
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpGetReportJobIds.fromJson(responseContent);
  }

  @Override
  public WxCpGetReportJobInfo getReportJobInfo(@NonNull String jobId, @NonNull String date) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_REPORT_JOB_INFO);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("jobid", jobId);
    jsonObject.addProperty("date", date);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpGetReportJobInfo.fromJson(responseContent);
  }

  @Override
  public WxCpGetReportAnswer getReportAnswer(@NonNull String jobId, @NonNull String date, Integer offset,
                                             Integer limit) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_REPORT_ANSWER);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("jobid", jobId);
    jsonObject.addProperty("date", date);
    if (offset != null) {
      jsonObject.addProperty("offset", offset);
    }
    if (limit != null) {
      jsonObject.addProperty("limit", limit);
    }
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpGetReportAnswer.fromJson(responseContent);
  }

}
