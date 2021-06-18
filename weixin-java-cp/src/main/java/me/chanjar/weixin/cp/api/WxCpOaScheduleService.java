package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.oa.WxCpOaSchedule;

import java.util.List;

/**
 * 企业微信日程接口.
 * 官方文档：https://work.weixin.qq.com/api/doc/90000/90135/93648
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020 -12-25
 */
public interface WxCpOaScheduleService {
  /**
   * 创建日程
   * <p>
   * 该接口用于在日历中创建一个日程。
   * <p>
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/schedule/add?access_token=ACCESS_TOKEN
   *
   * @param schedule the schedule
   * @param agentId  授权方安装的应用agentid。仅旧的第三方多应用套件需要填此参数
   * @return 日程ID string
   * @throws WxErrorException the wx error exception
   */
  String add(WxCpOaSchedule schedule, Integer agentId) throws WxErrorException;

  /**
   * 更新日程
   * <p>
   * 该接口用于在日历中更新指定的日程。
   * <p>
   * 注意，更新操作是覆盖式，而不是增量式
   * 不可更新组织者和日程所属日历ID
   * <p>
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/schedule/update?access_token=ACCESS_TOKEN
   *
   * @param schedule the schedule
   * @throws WxErrorException the wx error exception
   */
  void update(WxCpOaSchedule schedule) throws WxErrorException;

  /**
   * 获取日程详情
   * <p>
   * 该接口用于获取指定的日程详情。
   * <p>
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/schedule/get?access_token=ACCESS_TOKEN
   *
   * @param scheduleIds the schedule ids
   * @return the details
   * @throws WxErrorException the wx error exception
   */
  List<WxCpOaSchedule> getDetails(List<String> scheduleIds) throws WxErrorException;

  /**
   * 取消日程
   * 该接口用于取消指定的日程。
   * <p>
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/schedule/del?access_token=ACCESS_TOKEN
   *
   * @param scheduleId 日程id
   * @throws WxErrorException the wx error exception
   */
  void delete(String scheduleId) throws WxErrorException;

  /**
   * 获取日历下的日程列表
   * 该接口用于获取指定的日历下的日程列表。
   * 仅可获取应用自己创建的日历下的日程。
   * <p>
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/schedule/get_by_calendar?access_token=ACCESS_TOKEN
   *
   * @param calId  日历ID
   * @param offset 分页，偏移量, 默认为0
   * @param limit  分页，预期请求的数据量，默认为500，取值范围 1 ~ 1000
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  List<WxCpOaSchedule> listByCalendar(String calId, Integer offset, Integer limit) throws WxErrorException;
}
