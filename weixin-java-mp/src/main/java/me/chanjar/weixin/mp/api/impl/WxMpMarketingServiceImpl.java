package me.chanjar.weixin.mp.api.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMarketingService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.marketing.WxMpAdLeadFilter;
import me.chanjar.weixin.mp.bean.marketing.WxMpAdLeadResult;
import me.chanjar.weixin.mp.bean.marketing.WxMpUserAction;
import me.chanjar.weixin.mp.bean.marketing.WxMpUserActionSet;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxMpMarketingServiceImpl implements WxMpMarketingService {
  protected final Logger log = LoggerFactory.getLogger(this.getClass());
  private WxMpService wxMpService;

  public WxMpMarketingServiceImpl(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  @Override
  public long addUserActionSets(String type, String name, String description) throws WxErrorException {
    String url = API_URL_PREFIX + "user_action_sets/add?version=v1.0";
    JsonObject json = new JsonObject();
    json.addProperty("type", type);
    json.addProperty("name", name);
    json.addProperty("description", description);
    String responseContent = wxMpService.post(url, json.toString());
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    return tmpJsonElement.getAsJsonObject().get("data").getAsJsonObject().get("user_action_set_id").getAsLong();
  }

  @Override
  public List<WxMpUserActionSet> getUserActionSets(Long userActionSetId) throws WxErrorException {
    String url = API_URL_PREFIX + "user_action_sets/get";
    String responseContent = wxMpService.get(url, "version=v1.0&user_action_set_id=" + userActionSetId);
    return WxMpUserActionSet.fromJson(responseContent);
  }

  @Override
  public void addUserAction(List<WxMpUserAction> actions) throws WxErrorException {
    String url = API_URL_PREFIX + "user_actions/add?version=v1.0";
    JsonArray json = new JsonArray();
    for (WxMpUserAction action : actions) {
      json.add(action.toJsonObject());
    }
    wxMpService.post(url, json.toString());
  }

  @Override
  public WxMpAdLeadResult getAdLeads(Date beginDate, Date endDate, List<WxMpAdLeadFilter> filtering, Integer page, Integer page_size) throws WxErrorException, IOException {
    Date today = new Date();
    if (beginDate == null) {
      beginDate = today;
    }
    if (endDate == null) {
      endDate = today;
    }
    String url = API_URL_PREFIX + "wechat_ad_leads/get";
    String params = "version=v1.0";
    JsonObject dateRange = new JsonObject();
    dateRange.addProperty("begin_date", DateFormatUtils.format(beginDate, "yyyy-MM-dd"));
    dateRange.addProperty("end_date", DateFormatUtils.format(endDate, "yyyy-MM-dd"));
    params += "&date_range=" + URLEncoder.encode(dateRange.toString(), StandardCharsets.UTF_8.name());
    params += "&page=" + page;
    params += "&page_size=" + page_size;
    if (filtering != null) {
      JsonArray filterJson = new JsonArray();
      for (WxMpAdLeadFilter filter : filtering) {
        filterJson.add(filter.toJsonObject());
      }
      params += "&filtering=" + URLEncoder.encode(filterJson.toString(), StandardCharsets.UTF_8.name());
      ;
    }
    String responseContent = wxMpService.get(url, params);
    return WxMpAdLeadResult.fromJson(responseContent);
  }
}
