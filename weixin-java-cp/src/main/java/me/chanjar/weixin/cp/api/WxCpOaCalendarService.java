package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.oa.calendar.WxCpOaCalendar;

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
}
