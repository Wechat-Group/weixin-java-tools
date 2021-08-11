package me.chanjar.weixin.open.api.impl;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.api.WxOpenMaBatchCloudService;
import me.chanjar.weixin.open.bean.message.WxOpenMaBatchCloudBatchUpdateTriggerMessage;
import me.chanjar.weixin.open.bean.message.WxOpenMaBatchCloudShareEnvMessage;
import me.chanjar.weixin.open.bean.message.WxOpenMaBatchCloudUpdateCfConfigMessage;
import me.chanjar.weixin.open.bean.result.*;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量代云开发实现
 * <pre>
 *   https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/cloudbase/batch/batchCloudBase.html
 * </pre>
 *
 * @author caiqy
 */
public class WxOpenMaBatchCloudServiceImpl implements WxOpenMaBatchCloudService {

  private final WxOpenComponentService wxOpenComponentService;

  public WxOpenMaBatchCloudServiceImpl(WxOpenComponentService wxOpenComponentService) {
    this.wxOpenComponentService = wxOpenComponentService;
  }

  @Override
  public WxOpenResult createEnv(String alias) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("alias", alias);
    String response = wxOpenComponentService.post(API_CREATE_ENV, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult modifyEnv(String env) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("env", env);
    String response = wxOpenComponentService.post(API_MODIFY_ENV, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenMaBatchCloudDescribeEnvResult describeEnvs() throws WxErrorException {
    String response = wxOpenComponentService.post(API_DESCRIBE_ENVS, "{}");
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenMaBatchCloudDescribeEnvResult.class);
  }

  @Override
  public WxOpenMaBatchCloudShareEnvResult batchShareEnv(Collection<WxOpenMaBatchCloudShareEnvMessage> data) throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    map.put("data", data);
    String response = wxOpenComponentService.post(API_BATCH_SHARE_ENV, WxOpenGsonBuilder.create().toJson(map));
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenMaBatchCloudShareEnvResult.class);
  }

  @Override
  public WxOpenMaBatchCloudGetEnvIdResult batchGetEnvId(Collection<String> appIds) throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    map.put("appids", appIds);
    String response = wxOpenComponentService.post(API_BATCH_GET_ENV_ID, WxOpenGsonBuilder.create().toJson(map));
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenMaBatchCloudGetEnvIdResult.class);
  }

  @Override
  public WxOpenResult batchCreateCf(List<String> envs, String zipFile, String vpcId) throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    map.put("envs", envs);
    map.put("zipfile", zipFile);
    map.put("vpcid", vpcId);
    String response = wxOpenComponentService.post(API_BATCH_CREATE_CF, WxOpenGsonBuilder.create().toJson(map));
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult batchUpdateCf(List<String> envs, String zipFile, String functionName) throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    map.put("envs", envs);
    map.put("zipfile", zipFile);
    map.put("functionname", functionName);
    String response = wxOpenComponentService.post(API_BATCH_UPDATE_CF, WxOpenGsonBuilder.create().toJson(map));
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult updateCfConfig(WxOpenMaBatchCloudUpdateCfConfigMessage wxOpenMaBatchCloudUpdateCfConfigMessage) throws WxErrorException {
    String response = wxOpenComponentService.post(API_UPDATE_CF_CONFIG, WxOpenGsonBuilder.create().toJson(wxOpenMaBatchCloudUpdateCfConfigMessage));
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult deleteCf(String env, String functionName) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("functionname", functionName);
    jsonObject.addProperty("env", env);
    String response = wxOpenComponentService.post(API_DELETE_CF, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenMaBatchCloudGetCfListResult getCfListResult(String env, Long limit, Long offset, String searchKey) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("env", env);
    jsonObject.addProperty("limit", limit);
    jsonObject.addProperty("offset", offset);
    jsonObject.addProperty("searchKey", searchKey);
    String response = wxOpenComponentService.post(API_LIST_CF, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenMaBatchCloudGetCfListResult.class);
  }

  @Override
  public WxOpenMaBatchCloudGetTriggersResult getTriggers(String env, String functionName) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("env", env);
    jsonObject.addProperty("funcname", functionName);
    String response = wxOpenComponentService.post(API_GET_TRIGGERS, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenMaBatchCloudGetTriggersResult.class);
  }

  @Override
  public WxOpenMaBatchCloudBatchUpdateTriggerResult batchUpdateTrigger(WxOpenMaBatchCloudBatchUpdateTriggerMessage wxOpenMaBatchCloudBatchUpdateTriggerMessage) throws WxErrorException {
    String response = wxOpenComponentService.post(API_BATCH_UPDATE_TRIGGER, WxOpenGsonBuilder.create().toJson(wxOpenMaBatchCloudBatchUpdateTriggerMessage));
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenMaBatchCloudBatchUpdateTriggerResult.class);
  }

  @Override
  public WxOpenResult createStaticStore(String env) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("env", env);
    String response = wxOpenComponentService.post(API_CREATE_SS, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenMaBatchCloudDescribeStaticStoreResult describeStaticStore(String env) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("env", env);
    String response = wxOpenComponentService.post(API_DESCRIBE_SS, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenMaBatchCloudDescribeStaticStoreResult.class);
  }

  @Override
  public WxOpenMaBatchCloudStaticFileListResult staticFileList(String env, String prefix, String delimiter, String marker) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("env", env);
    jsonObject.addProperty("prefix", prefix);
    jsonObject.addProperty("delimiter", delimiter);
    jsonObject.addProperty("marker", marker);
    String response = wxOpenComponentService.post(API_LIST_STATIC_FILE, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenMaBatchCloudStaticFileListResult.class);
  }

  @Override
  public WxOpenMaBatchCloudStaticUploadFileResult staticUploadFile(String env, String fileName) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("env", env);
    jsonObject.addProperty("filename", fileName);
    String response = wxOpenComponentService.post(API_GET_STATIC_FILE_URL, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenMaBatchCloudStaticUploadFileResult.class);
  }
}
