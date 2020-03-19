package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.cloud.*;
import com.google.gson.JsonArray;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

/**
 * 云开发相关接口.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-01-22
 */
public interface WxMaCloudService {
  String INVOKE_CLOUD_FUNCTION_URL = "https://api.weixin.qq.com/tcb/invokecloudfunction?env=%s&name=%s";
  String DATABASE_COLLECTION_GET_URL = "https://api.weixin.qq.com/tcb/databasecollectionget";
  String DATABASE_COLLECTION_DELETE_URL = "https://api.weixin.qq.com/tcb/databasecollectiondelete";
  String DATABASE_COLLECTION_ADD_URL = "https://api.weixin.qq.com/tcb/databasecollectionadd";
  String GET_QCLOUD_TOKEN_URL = "https://api.weixin.qq.com/tcb/getqcloudtoken";
  String BATCH_DELETE_FILE_URL = "https://api.weixin.qq.com/tcb/batchdeletefile";
  String BATCH_DOWNLOAD_FILE_URL = "https://api.weixin.qq.com/tcb/batchdownloadfile";
  String UPLOAD_FILE_URL = "https://api.weixin.qq.com/tcb/uploadfile";
  String DATABASE_MIGRATE_QUERY_INFO_URL = "https://api.weixin.qq.com/tcb/databasemigratequeryinfo";
  String DATABASE_MIGRATE_EXPORT_URL = "https://api.weixin.qq.com/tcb/databasemigrateexport";
  String DATABASE_MIGRATE_IMPORT_URL = "https://api.weixin.qq.com/tcb/databasemigrateimport";
  String UPDATE_INDEX_URL = "https://api.weixin.qq.com/tcb/updateindex";
  String DATABASE_COUNT_URL = "https://api.weixin.qq.com/tcb/databasecount";
  String DATABASE_AGGREGATE_URL = "https://api.weixin.qq.com/tcb/databaseaggregate";
  String DATABASE_QUERY_URL = "https://api.weixin.qq.com/tcb/databasequery";
  String DATABASE_UPDATE_URL = "https://api.weixin.qq.com/tcb/databaseupdate";
  String DATABASE_DELETE_URL = "https://api.weixin.qq.com/tcb/databasedelete";
  String DATABASE_ADD_URL = "https://api.weixin.qq.com/tcb/databaseadd";

  /**
   * <pre>
   * 触发云函数。注意：HTTP API 途径触发云函数不包含用户信息。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/functions/invokeCloudFunction.html
   *
   * 请求地址
   * POST https://api.weixin.qq.com/tcb/invokecloudfunction?access_token=ACCESS_TOKEN&env=ENV&name=FUNCTION_NAME
   *
   * </pre>
   *
   * @param env  string		是	云开发环境ID
   * @param name string		是	云函数名称
   * @param body string		是	云函数的传入参数，具体结构由开发者定义。
   * @return resp_data  string	云函数返回的buffer
   * @throws WxErrorException .
   */
  String invokeCloudFunction(String env, String name, String body) throws WxErrorException;

  /**
   * <pre>
   * 数据库插入记录
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseAdd.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databaseadd?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env   云环境ID
   * @param query 数据库操作语句
   * @return 插入成功的数据集合主键_id
   * @throws WxErrorException .
   */
  JsonArray databaseAdd(String env, String query) throws WxErrorException;

  /**
   * <pre>
   * 数据库删除记录
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseDelete.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databasedelete?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env   云环境ID
   * @param query 数据库操作语句
   * @return 删除记录数量
   * @throws WxErrorException .
   */
  int databaseDelete(String env, String query) throws WxErrorException;

  /**
   * <pre>
   * 数据库更新记录
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseUpdate.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databaseupdate?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env   云环境ID
   * @param query 数据库操作语句
   * @return .
   * @throws WxErrorException .
   */
  WxCloudDatabaseUpdateResult databaseUpdate(String env, String query) throws WxErrorException;

  /**
   * <pre>
   * 数据库查询记录
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseQuery.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databasequery?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env   云环境ID
   * @param query 数据库操作语句
   * @return .
   * @throws WxErrorException .
   */
  WxCloudDatabaseQueryResult databaseQuery(String env, String query) throws WxErrorException;

  /**
   * <pre>
   * 数据库聚合记录
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseAggregate.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databaseaggregate?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env   云环境ID
   * @param query 数据库操作语句
   * @return .
   * @throws WxErrorException .
   */
  JsonArray databaseAggregate(String env, String query) throws WxErrorException;

  /**
   * <pre>
   * 统计集合记录数或统计查询语句对应的结果记录数
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseCount.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databasecount?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env   云环境ID
   * @param query 数据库操作语句
   * @return 记录数量
   * @throws WxErrorException .
   */
  Long databaseCount(String env, String query) throws WxErrorException;

  /**
   * <pre>
   * 变更数据库索引
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/updateIndex.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/updateindex?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env            云环境ID
   * @param collectionName 集合名称
   * @param createIndexes  新增索引对象
   * @param dropIndexNames 要删除的索引的名字
   * @throws WxErrorException .
   */
  void updateIndex(String env, String collectionName, List<WxCloudDatabaseCreateIndexRequest> createIndexes,
                   List<String> dropIndexNames) throws WxErrorException;

  /**
   * <pre>
   * 数据库导入
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseMigrateImport.html
   * 请求地址： POST https://api.weixin.qq.com/tcb/databasemigrateimport?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env            云环境ID
   * @param collectionName 导入collection名
   * @param filePath       导入文件路径(导入文件需先上传到同环境的存储中，可使用开发者工具或 HTTP API的上传文件 API上传）
   * @param fileType       导入文件类型， 1	JSON， 2	CSV
   * @param stopOnError    是否在遇到错误时停止导入
   * @param conflictMode   冲突处理模式 ： 1	INSERT	,    2	UPSERT
   * @return jobId
   * @throws WxErrorException .
   */
  Long databaseMigrateImport(String env, String collectionName, String filePath, int fileType, boolean stopOnError,
                             int conflictMode) throws WxErrorException;

  /**
   * <pre>
   * 数据库导出
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseMigrateExport.html
   * 请求地址： POST https://api.weixin.qq.com/tcb/databasemigrateexport?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env      云环境ID
   * @param filePath 导出文件路径（文件会导出到同环境的云存储中，可使用获取下载链接 API 获取下载链接）
   * @param fileType 导出文件类型， 1	JSON， 2	CSV
   * @param query    导出条件
   * @return jobId
   * @throws WxErrorException .
   */
  Long databaseMigrateExport(String env, String filePath, int fileType, String query) throws WxErrorException;

  /**
   * <pre>
   *   数据库迁移状态查询
   *
   *  文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseMigrateQueryInfo.html
   *  请求地址：POST https://api.weixin.qq.com/tcb/databasemigratequeryinfo?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env   云环境ID
   * @param jobId 迁移任务ID
   * @return .
   * @throws WxErrorException .
   */
  WxCloudCloudDatabaseMigrateQueryInfoResult databaseMigrateQueryInfo(String env, Long jobId) throws WxErrorException;

  /**
   * <pre>
   * 获取文件上传链接
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/storage/uploadFile.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/uploadfile?access_token=ACCESS_TOKEN
   *
   * </pre>
   *
   * @param env  云环境ID
   * @param path 上传路径
   * @return 上传结果
   * @throws WxErrorException .
   */
  WxCloudUploadFileResult uploadFile(String env, String path) throws WxErrorException;

  /**
   * <pre>
   * 获取文件下载链接
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/storage/batchDownloadFile.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/batchdownloadfile?access_token=ACCESS_TOKEN
   *
   * </pre>
   *
   * @param env     云环境ID
   * @param fileIds 文件ID列表
   * @param maxAges 下载链接有效期列表，对应文件id列表
   * @return 下载链接信息
   * @throws WxErrorException .
   */
  WxCloudBatchDownloadFileResult batchDownloadFile(String env, String[] fileIds, long[] maxAges) throws WxErrorException;

  /**
   * <pre>
   * 删除文件
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/storage/batchDeleteFile.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/batchdeletefile?access_token=ACCESS_TOKEN
   *
   * </pre>
   *
   * @param env     云环境ID
   * @param fileIds 文件ID列表
   * @return 下载链接信息
   * @throws WxErrorException .
   */
  WxCloudBatchDeleteFileResult batchDeleteFile(String env, String[] fileIds) throws WxErrorException;

  /**
   * <pre>
   *  获取腾讯云API调用凭证
   *
   *  文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/utils/getQcloudToken.html
   *  请求地址：POST https://api.weixin.qq.com/tcb/getqcloudtoken?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param lifeSpan 有效期（单位为秒，最大7200）
   * @return .
   * @throws WxErrorException .
   */
  WxCloudGetQcloudTokenResult getQcloudToken(long lifeSpan) throws WxErrorException;

  /**
   * <pre>
   * 新增集合
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseCollectionAdd.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databasecollectionadd?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env            云环境ID
   * @param collectionName 集合名称
   * @throws WxErrorException .
   */
  void databaseCollectionAdd(String env, String collectionName) throws WxErrorException;

  /**
   * <pre>
   * 删除集合
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseCollectionDelete.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databasecollectionadd?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env            云环境ID
   * @param collectionName 集合名称
   * @throws WxErrorException .
   */
  void databaseCollectionDelete(String env, String collectionName) throws WxErrorException;

  /**
   * <pre>
   * 获取特定云环境下集合信息
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseCollectionGet.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databasecollectionget?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env            云环境ID
   * @param limit          获取数量限制，默认值：10
   * @param offset         偏移量,默认值：0
   * @return .
   * @throws WxErrorException .
   */
  WxCloudDatabaseCollectionGetResult databaseCollectionGet(String env, Long limit, Long offset) throws WxErrorException;
}
