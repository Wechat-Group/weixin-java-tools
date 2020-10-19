package me.chanjar.weixin.cp.api.impl;

import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.WxCpOaCalendarService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.oa.calendar.WxCpOaCalendar;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.*;

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
    return this.wxCpService.post(this.wxCpService.getWxCpConfigStorage().getApiUrl(CALENDAR_ADD), calendar);
  }

  @Override
  public void update(WxCpOaCalendar calendar) throws WxErrorException {
    this.wxCpService.post(this.wxCpService.getWxCpConfigStorage().getApiUrl(CALENDAR_UPDATE), calendar);
  }

  @Override
  public List<WxCpOaCalendar> get(List<String> calIds) throws WxErrorException {
    String response = this.wxCpService.post(this.wxCpService.getWxCpConfigStorage().getApiUrl(CALENDAR_GET),
      GsonHelper.buildJsonObject("cal_id_list", calIds));
    return WxCpGsonBuilder.create().fromJson(GsonParser.parse(response).get("calendar_list").getAsJsonArray().toString(),
      new TypeToken<List<WxCpOaCalendar>>() {
      }.getType());
  }

  @Override
  public void delete(String calId) throws WxErrorException {
    this.wxCpService.post(this.wxCpService.getWxCpConfigStorage().getApiUrl(CALENDAR_DEL),
      GsonHelper.buildJsonObject("cal_id", calId));
  }
}
