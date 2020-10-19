package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.oa.calendar.WxCpOaCalendar;

import java.util.List;

/**
 * 企业微信日历接口.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-09-20
 */
public interface WxCpOaCalendarService {
  /**
   * 创建日历.
   * <pre>
   * 该接口用于通过应用在企业内创建一个日历。
   * 注: 企业微信需要更新到3.0.2及以上版本
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/calendar/add?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://work.weixin.qq.com/api/doc/90000/90135/92618
   * </pre>
   *
   * @param calendar 日历对象
   * @return 日历ID
   * @throws WxErrorException .
   */
  String add(WxCpOaCalendar calendar) throws WxErrorException;

  /**
   * 更新日历.
   * <pre>
   * 该接口用于修改指定日历的信息。
   * 注意，更新操作是覆盖式，而不是增量式
   * 企业微信需要更新到3.0.2及以上版本
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/calendar/update?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://work.weixin.qq.com/api/doc/90000/90135/92619
   * </pre>
   *
   * @param calendar 日历对象
   * @throws WxErrorException .
   */
  void update(WxCpOaCalendar calendar) throws WxErrorException;

  /**
   * 获取日历.
   * <pre>
   * 该接口用于获取应用在企业内创建的日历信息。
   *
   * 注: 企业微信需要更新到3.0.2及以上版本
   *
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/calendar/get?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://work.weixin.qq.com/api/doc/90000/90135/92621
   * </pre>
   *
   * @param calIds 日历id列表
   * @return 日历对象列表
   * @throws WxErrorException .
   */
  List<WxCpOaCalendar> get(List<String> calIds) throws WxErrorException;

  /**
   * 删除日历.
   * <pre>
   * 该接口用于删除指定日历。
   * 注: 企业微信需要更新到3.0.2及以上版本
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/oa/calendar/del?access_token=ACCESS_TOKEN
   *
   * 文档地址：https://work.weixin.qq.com/api/doc/90000/90135/92620
   * </pre>
   *
   * @param calId 日历id
   * @throws WxErrorException .
   */
  void delete(String calId) throws WxErrorException;
}
