package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.school.health.WxCpGetHealthReportStat;
import me.chanjar.weixin.cp.bean.school.health.WxCpGetReportAnswer;
import me.chanjar.weixin.cp.bean.school.health.WxCpGetReportJobIds;
import me.chanjar.weixin.cp.bean.school.health.WxCpGetReportJobInfo;

/**
 * 企业微信家校应用 健康上报接口.
 * https://developer.work.weixin.qq.com/document/path/93676
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a>
 * @date: 2022/5/31 9:10
 */
public interface WxCpSchoolHealthService {

  /**
   * 获取健康上报使用统计
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/health/get_health_report_stat?access_token=ACCESS_TOKEN
   *
   * @param date 具体某天的使用统计，最长支持获取30天前数据
   * @return
   * @throws WxErrorException
   */
  WxCpGetHealthReportStat getHealthReportStat(@NonNull String date) throws WxErrorException;

  /**
   * 获取健康上报任务ID列表
   * 通过此接口可以获取企业当前正在运行的上报任务ID列表。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/health/get_report_jobids?access_token=ACCESS_TOKEN
   *
   * @param offset 否	分页，偏移量, 默认为0
   * @param limit  否	分页，预期请求的数据量，默认为100，取值范围 1 ~ 100
   * @return
   * @throws WxErrorException
   */
  WxCpGetReportJobIds getReportJobIds(Integer offset, Integer limit) throws WxErrorException;

  /**
   * 获取健康上报任务详情
   * 通过此接口可以获取指定的健康上报任务详情。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/health/get_report_job_info?access_token=ACCESS_TOKEN
   *
   * @param jobId 是	任务ID
   * @param date  是	具体某天任务详情，仅支持获取最近14天数据
   * @return
   * @throws WxErrorException
   */
  WxCpGetReportJobInfo getReportJobInfo(@NonNull String jobId, @NonNull String date) throws WxErrorException;

  /**
   * 获取用户填写答案
   * 通过此接口可以获取指定的健康上报任务详情。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/health/get_report_answer?access_token=ACCESS_TOKEN
   *
   * @param jobId
   * @param date
   * @param offset
   * @param limit
   * @return
   * @throws WxErrorException
   */
  WxCpGetReportAnswer getReportAnswer(@NonNull String jobId, @NonNull String date, Integer offset, Integer limit) throws WxErrorException;

}
