package me.chanjar.weixin.cp.api.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpOaCalendarService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.oa.calendar.WxCpOaCalendar;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.CALENDAR_ADD;

/**
 * .
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-09-20
 */
@RequiredArgsConstructor
public class WxCpOaCalendarServiceImpl implements WxCpOaCalendarService {
  private final WxCpService wxCpService;

  @Override
  public String add(WxCpOaCalendar calendar) throws WxErrorException {
    return this.wxCpService.post(this.wxCpService.getWxCpConfigStorage().getApiUrl(CALENDAR_ADD),calendar.toJson());
  }
}
