package me.chanjar.weixin.mp.api.impl;

import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.api.WxMpGuideMassedJobService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideMassed;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideMassedInfo;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideMaterialInfo;
import me.chanjar.weixin.mp.enums.WxMpApiUrl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/13/013
 */
@AllArgsConstructor
public class WxMpGuideMassedJobServiceImpl implements WxMpGuideMassedJobService {
  private static final String ACCOUNT = "guide_account";
  private static final String OPENID = "guide_openid";
  private final WxMpService mpService;

  @Override
  public WxMpGuideMassed addGuideMassedJob(String account, String openid, String taskName, String taskRemark, Long pushTime, List<String> userOpenIds, List<WxMpGuideMaterialInfo> materialInfos) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(ACCOUNT, account);
    body.put(OPENID, openid);
    body.put("task_name", taskName);
    body.put("task_remark", taskRemark);
    body.put("push_time", pushTime);
    body.put("openid", userOpenIds);
    body.put("material", materialInfos);
    String returnString = this.mpService.post(WxMpApiUrl.Guide.ADD_GUIDE_MASSED_JOB, body);
    return WxMpGuideMassed.fromJson(GsonParser.parse(returnString).getAsJsonArray("task_result").get(0));
  }

  @Override
  public List<WxMpGuideMassedInfo> getGuideMassedJobList(String account, String openid, List<Integer> taskStatus, Integer offset, Integer limit) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(ACCOUNT, account);
    body.put(OPENID, openid);
    body.put("task_status", taskStatus);
    body.put("offset", offset);
    body.put("limit", limit);
    String returnString = this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_MASSED_JOB_LIST, body);
    return WxGsonBuilder.create().fromJson(GsonParser.parse(returnString).getAsJsonArray("list"),
      new TypeToken<List<WxMpGuideMassedInfo>>() {
      }.getType());
  }

  @Override
  public WxMpGuideMassedInfo getGuideMassedJob(String taskId) throws WxErrorException {
    String returnString = this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_MASSED_JOB, GsonHelper.buildJsonObject("task_id", taskId));
    return WxMpGuideMassedInfo.fromJson(GsonParser.parse(returnString).get("job"));
  }

  @Override
  public void updateGuideMassedJob(String taskId, String taskName, String taskRemark, Long pushTime, List<String> userOpenIds, List<WxMpGuideMaterialInfo> materialInfos) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("task_id", taskId);
    body.put("task_name", taskName);
    body.put("task_remark", taskRemark);
    body.put("push_time", pushTime);
    body.put("openid", userOpenIds);
    body.put("material", materialInfos);
    this.mpService.post(WxMpApiUrl.Guide.UPDATE_GUIDE_MASSED_JOB, body);
  }

  @Override
  public void cancelGuideMassedJob(String taskId) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.CANCEL_GUIDE_MASSED_JOB, GsonHelper.buildJsonObject("task_id", taskId));
  }
}
