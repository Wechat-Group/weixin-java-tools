package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.WxCpLivingService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.living.*;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Living.*;

/**
 * 企业微信直播接口实现类.
 *
 * @author Wang_Wong
 * @date 2021-12-21
 */
@Slf4j
@RequiredArgsConstructor
public class WxCpLivingServiceImpl implements WxCpLivingService {
  private final WxCpService cpService;

  @Override
  public String getLivingCode(String openId, String livingId) throws WxErrorException {
    final String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_LIVING_CODE);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("openid", openId);
    jsonObject.addProperty("livingid", livingId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return GsonHelper.getString(GsonParser.parse(responseContent), "living_code");
  }

  @Override
  public WxCpLivingInfo getLivingInfo(String livingId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_LIVING_INFO) + livingId;
    String responseContent = this.cpService.get(apiUrl, null);
    return WxCpGsonBuilder.create()
      .fromJson(GsonParser.parse(responseContent).get("living_info"),
        new TypeToken<WxCpLivingInfo>() {
        }.getType()
      );
  }

  @Override
  public WxCpWatchStat getWatchStat(String livingId, Integer nextKey) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_WATCH_STAT);
    JsonObject jsonObject = new JsonObject();
    if (nextKey != null) {
      jsonObject.addProperty("next_key", String.valueOf(nextKey));
    }
    jsonObject.addProperty("livingid", livingId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpWatchStat.fromJson(responseContent);
  }

  @Override
  public WxCpLivingResult.LivingIdResult getUserAllLivingId(String userId, String cursor, Integer limit) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_USER_ALL_LIVINGID);
    JsonObject jsonObject = new JsonObject();
    if (cursor != null) {
      jsonObject.addProperty("cursor", cursor);
    }
    if (limit != null) {
      jsonObject.addProperty("limit", limit);
    }
    jsonObject.addProperty("userid", userId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpLivingResult.LivingIdResult.fromJson(responseContent);
  }

  @Override
  public WxCpLivingShareInfo getLivingShareInfo(String wwShareCode) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_LIVING_SHARE_INFO);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("ww_share_code", wwShareCode);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpLivingShareInfo.fromJson(responseContent);
  }

  @Override
  public String livingCreate(WxCpLivingCreateRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(CREATE);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return GsonHelper.getString(GsonParser.parse(responseContent), "livingid");
  }

  @Override
  public WxCpLivingResult livingModify(WxCpLivingModifyRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(MODIFY);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpLivingResult.fromJson(responseContent);
  }

  @Override
  public WxCpLivingResult livingCancel(@NonNull String livingId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(CANCEL);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("livingid", livingId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpLivingResult.fromJson(responseContent);
  }

  @Override
  public WxCpLivingResult deleteReplayData(@NonNull String livingId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(DELETE_REPLAY_DATA);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("livingid", livingId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpLivingResult.fromJson(responseContent);
  }

}
