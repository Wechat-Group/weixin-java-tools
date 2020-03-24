package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaCloudService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.cloud.*;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 云开发相关接口实现类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-01-22
 */
@Slf4j
@RequiredArgsConstructor
public class WxMaCloudServiceImpl implements WxMaCloudService {
  private static final JsonParser JSON_PARSER = new JsonParser();
  private final WxMaService wxMaService;

  @Override
  public String invokeCloudFunction(String env, String name, String body) throws WxErrorException {
    final String response = this.wxMaService.post(String.format(INVOKE_CLOUD_FUNCTION_URL, env, name), body);
    return JSON_PARSER.parse(response).getAsJsonObject().get("resp_data").getAsString();
  }

  @Override
  public JsonArray databaseAdd(String env, String query) throws WxErrorException {
    String response = this.wxMaService.post(DATABASE_ADD_URL, ImmutableMap.of("env", env, "query", query));
    return JSON_PARSER.parse(response).getAsJsonObject().get("id_list").getAsJsonArray();
  }

  @Override
  public int databaseDelete(String env, String query) throws WxErrorException {
    String response = this.wxMaService.post(DATABASE_DELETE_URL, ImmutableMap.of("env", env, "query", query));
    return JSON_PARSER.parse(response).getAsJsonObject().get("deleted").getAsInt();
  }

  @Override
  public WxCloudDatabaseUpdateResult databaseUpdate(String env, String query) throws WxErrorException {
    String response = this.wxMaService.post(DATABASE_UPDATE_URL, ImmutableMap.of("env", env, "query", query));
    return WxGsonBuilder.create().fromJson(response, WxCloudDatabaseUpdateResult.class);
  }

  @Override
  public WxCloudDatabaseQueryResult databaseQuery(String env, String query) throws WxErrorException {
    String response = this.wxMaService.post(DATABASE_QUERY_URL, ImmutableMap.of("env", env, "query", query));
    return WxGsonBuilder.create().fromJson(response, WxCloudDatabaseQueryResult.class);
  }

  @Override
  public JsonArray databaseAggregate(String env, String query) throws WxErrorException {
    String response = this.wxMaService.post(DATABASE_AGGREGATE_URL, ImmutableMap.of("env", env, "query", query));
    return JSON_PARSER.parse(response).getAsJsonObject().get("data").getAsJsonArray();
  }

  @Override
  public Long databaseCount(String env, String query) throws WxErrorException {
    String response = this.wxMaService.post(DATABASE_COUNT_URL, ImmutableMap.of("env", env, "query", query));
    return JSON_PARSER.parse(response).getAsJsonObject().get("count").getAsLong();
  }

  @Override
  public void updateIndex(String env, String collectionName, List<WxCloudDatabaseCreateIndexRequest> createIndexes,
                          List<String> dropIndexNames) throws WxErrorException {
    List<Map<String, String>> dropIndexes = Lists.newArrayList();
    if (dropIndexNames != null) {
      for (String index : dropIndexNames) {
        dropIndexes.add(ImmutableMap.of("name", index));
      }
    }

    this.wxMaService.post(UPDATE_INDEX_URL, ImmutableMap.of("env", env,
      "collection_name", collectionName, "create_indexes", createIndexes, "drop_indexes", dropIndexes));
  }

  @Override
  public Long databaseMigrateImport(String env, String collectionName, String filePath, int fileType,
                                    boolean stopOnError, int conflictMode) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("env", env);
    params.addProperty("collection_name", collectionName);
    params.addProperty("file_path", filePath);
    params.addProperty("file_type", fileType);
    params.addProperty("stop_on_error", stopOnError);
    params.addProperty("conflict_mode", conflictMode);

    String response = this.wxMaService.post(DATABASE_MIGRATE_IMPORT_URL, params.toString());
    return JSON_PARSER.parse(response).getAsJsonObject().get("job_id").getAsLong();
  }

  @Override
  public Long databaseMigrateExport(String env, String filePath, int fileType, String query) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("env", env);
    params.addProperty("file_path", filePath);
    params.addProperty("file_type", fileType);
    params.addProperty("query", query);

    String response = this.wxMaService.post(DATABASE_MIGRATE_EXPORT_URL, params.toString());
    return JSON_PARSER.parse(response).getAsJsonObject().get("job_id").getAsLong();
  }

  @Override
  public WxCloudCloudDatabaseMigrateQueryInfoResult databaseMigrateQueryInfo(String env, Long jobId) throws WxErrorException {
    String response = this.wxMaService.post(DATABASE_MIGRATE_QUERY_INFO_URL, ImmutableMap.of("env", env, "job_id", jobId));
    return WxGsonBuilder.create().fromJson(response, WxCloudCloudDatabaseMigrateQueryInfoResult.class);
  }

  @Override
  public WxCloudUploadFileResult uploadFile(String env, String path) throws WxErrorException {
    String response = this.wxMaService.post(UPLOAD_FILE_URL, ImmutableMap.of("env", env, "path", path));
    return WxGsonBuilder.create().fromJson(response, WxCloudUploadFileResult.class);
  }

  @Override
  public WxCloudBatchDownloadFileResult batchDownloadFile(String env, String[] fileIds, long[] maxAges) throws WxErrorException {
    List<Map<String, Serializable>> fileList = Lists.newArrayList();
    int i = 0;
    for (String fileId : fileIds) {
      fileList.add(ImmutableMap.of("fileid", fileId, "max_age", (Serializable) maxAges[i++]));
    }

    String response = this.wxMaService.post(GET_QCLOUD_TOKEN_URL, ImmutableMap.of("env", env, "file_list", fileList));
    return WxGsonBuilder.create().fromJson(response, WxCloudBatchDownloadFileResult.class);
  }

  @Override
  public WxCloudBatchDeleteFileResult batchDeleteFile(String env, String[] fileIds) throws WxErrorException {
    String response = this.wxMaService.post(BATCH_DELETE_FILE_URL, ImmutableMap.of("env", env, "fileid_list", fileIds));
    return WxGsonBuilder.create().fromJson(response, WxCloudBatchDeleteFileResult.class);
  }

  @Override
  public WxCloudGetQcloudTokenResult getQcloudToken(long lifeSpan) throws WxErrorException {
    String response = this.wxMaService.post(GET_QCLOUD_TOKEN_URL, ImmutableMap.of("lifespan", lifeSpan));
    return WxGsonBuilder.create().fromJson(response, WxCloudGetQcloudTokenResult.class);
  }

  @Override
  public void databaseCollectionAdd(String env, String collectionName) throws WxErrorException {
    this.wxMaService.post(DATABASE_COLLECTION_ADD_URL, ImmutableMap.of("env", env, "collection_name", collectionName));
  }

  @Override
  public void databaseCollectionDelete(String env, String collectionName) throws WxErrorException {
    this.wxMaService.post(DATABASE_COLLECTION_DELETE_URL, ImmutableMap.of("env", env, "collection_name", collectionName));
  }

  @Override
  public WxCloudDatabaseCollectionGetResult databaseCollectionGet(String env, Long limit, Long offset) throws WxErrorException {
    Map<String, Object> params = new HashMap<>(2);
    params.put("env", env);
    if (limit != null) {
      params.put("limit", limit);
    }

    if (offset != null) {
      params.put("offset", offset);
    }

    String response = this.wxMaService.post(DATABASE_COLLECTION_GET_URL, params);
    return WxGsonBuilder.create().fromJson(response, WxCloudDatabaseCollectionGetResult.class);
  }

}
