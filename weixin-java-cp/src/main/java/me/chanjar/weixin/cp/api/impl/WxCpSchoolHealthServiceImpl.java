package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpSchoolHealthService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.school.health.WxCpGetHealthReportStat;
import me.chanjar.weixin.cp.bean.school.health.WxCpGetReportJobids;

import java.util.Optional;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.School.*;

/**
 * 企业微信家校应用 健康上报接口实现类.
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a>
 * @date: 2022/5/31 9:16
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
  public WxCpGetReportJobids getReportJobids(Integer offset, Integer limit) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_REPORT_JOBIDS);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("offset", Optional.ofNullable(offset).orElse(0));
    jsonObject.addProperty("limit", Optional.ofNullable(limit).orElse(100));
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpGetReportJobids.fromJson(responseContent);
  }

}
