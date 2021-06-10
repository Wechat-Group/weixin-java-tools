package me.chanjar.weixin.open.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.message.WxOpenMaBatchCloudBatchUpdateTriggerMessage;
import me.chanjar.weixin.open.bean.message.WxOpenMaBatchCloudShareEnvMessage;
import me.chanjar.weixin.open.bean.message.WxOpenMaBatchCloudUpdateCfConfigMessage;
import me.chanjar.weixin.open.bean.result.*;

import java.util.Collection;
import java.util.List;

/**
 * 批量代云开发
 * <pre>
 *   文档: https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/cloudbase/batch/batchCloudBase.html
 * </pre>
 *
 * @author caiqy
 */
public interface WxOpenMaBatchCloudService {

  //---------------------------------------------------------------------
  // Define api url
  // 定义一下URL,不是很想写注释,看文档吧,是按照文档的顺序一个个加下来的
  //---------------------------------------------------------------------

  /**
   * The constant API_CREATE_ENV.
   */
// 环境管理 env
  String API_CREATE_ENV = "https://api.weixin.qq.com/componenttcb/createenv";

  /**
   * The constant API_MODIFY_ENV.
   */
  String API_MODIFY_ENV = "https://api.weixin.qq.com/tcb/modifyenv";

  /**
   * The constant API_DESCRIBE_ENVS.
   */
  String API_DESCRIBE_ENVS = "https://api.weixin.qq.com/componenttcb/describeenvs";

  /**
   * The constant API_BATCH_SHARE_ENV.
   */
  String API_BATCH_SHARE_ENV = "https://api.weixin.qq.com/componenttcb/batchshareenv";

  /**
   * The constant API_BATCH_GET_ENV_ID.
   */
  String API_BATCH_GET_ENV_ID = "https://api.weixin.qq.com/componenttcb/batchgetenvid";

  // 云函数管理 cloud function  (aka cf)

  /**
   * The constant API_BATCH_CREATE_CF.
   */
  String API_BATCH_CREATE_CF = "https://api.weixin.qq.com/componenttcb/batchuploadscf";

  /**
   * The constant API_BATCH_UPDATE_CF.
   */
  String API_BATCH_UPDATE_CF = "https://api.weixin.qq.com/componenttcb/batchuploadscfcode";

  /**
   * The constant API_UPDATE_CF_CONFIG.
   */
  String API_UPDATE_CF_CONFIG = "https://api.weixin.qq.com/componenttcb/updatescfconfig";

  /**
   * The constant API_DELETE_CF.
   */
  String API_DELETE_CF = "https://api.weixin.qq.com/componenttcb/deletescf";

  /**
   * The constant API_LIST_CF.
   */
  String API_LIST_CF = "https://api.weixin.qq.com/componenttcb/getscflist";

  /**
   * The constant API_GET_TRIGGER.
   */
  String API_GET_TRIGGERS = "https://api.weixin.qq.com/componenttcb/gettriggers";

  /**
   * The constant API_BATCH_UPDATE_TRIGGER.
   */
  String API_BATCH_UPDATE_TRIGGER = "https://api.weixin.qq.com/componenttcb/batchupdatetriggers";

  // 云托管管理 container service (aka cs)

  /**
   * The constant API_OPEN_CS.
   */
  String API_OPEN_CS = "https://api.weixin.qq.com/componenttcb/opencontainerservice";

  /**
   * The constant API_USE_CLOUD_ACCESS_TOKEN.
   */
  String API_USE_CLOUD_ACCESS_TOKEN = "https://api.weixin.qq.com/tcb/usecloudaccesstoken";

  /**
   * The constant API_CREATE_CS.
   */
  String API_CREATE_CS = "https://api.weixin.qq.com/componenttcb/createcontainerservice";

  /**
   * The constant API_LIST_CS.
   */
  String API_LIST_CS = "https://api.weixin.qq.com/componenttcb/getcslist";

  /**
   * The constant API_DELETE_CS.
   */
  String API_DELETE_CS = "https://api.weixin.qq.com/componenttcb/delcontainerservice";

  /**
   * The constant API_CREATE_CS_VERSION.
   */
  String API_CREATE_CS_VERSION = "https://api.weixin.qq.com/componenttcb/createcsversion";

  /**
   * The constant API_BATCH_CREATE_CS_VERSION.
   */
  String API_BATCH_CREATE_CS_VERSION = "https://api.weixin.qq.com/componenttcb/batchcreatecsversion";

  /**
   * The constant API_DELETE_CS_VERSION.
   */
  String API_DELETE_CS_VERSION = "https://api.weixin.qq.com/componenttcb/delcsversion";

  /**
   * The constant API_BATCH_DELETE_CS_VERSION.
   */
  String API_BATCH_DELETE_CS_VERSION = "https://api.weixin.qq.com/componenttcb/batchdelcsversion";

  /**
   * The constant API_LIST_CS_VERSION.
   */
  String API_LIST_CS_VERSION = "https://api.weixin.qq.com/componenttcb/getcsversionlist";

  /**
   * The constant API_GET_CS_VERSION_DETAIL.
   */
  String API_GET_CS_VERSION_DETAIL = "https://api.weixin.qq.com/componenttcb/getcsversiondetail";

  /**
   * The constant API_SET_CS_VERSION_CONFIG.
   */
  String API_SET_CS_VERSION_CONFIG = "https://api.weixin.qq.com/componenttcb/setcsversionconfig";

  /**
   * The constant API_SET_CS_PROPOTION.
   */
  String API_SET_CS_PROPOTION = "https://api.weixin.qq.com/componenttcb/setcspropotion";

  /**
   * The constant API_BATCH_SET_CS_PROPOTION.
   */
  String API_BATCH_SET_CS_PROPOTION = "https://api.weixin.qq.com/componenttcb/batchsetcspropotion";

  // 数据库管理 database  (aka db)

  // 存储管理

  // 静态网站管理 static store  (aka ss)

  /**
   * The constant API_CREATE_SS.
   */
  String API_CREATE_SS = "https://api.weixin.qq.com/componenttcb/createstaticstore";

  /**
   * The constant API_DESCRIBE_SS.
   */
  String API_DESCRIBE_SS = "https://api.weixin.qq.com/componenttcb/describestaticstore";

  /**
   * The constant API_LIST_STATIC_FILE.
   */
  String API_LIST_STATIC_FILE = "https://api.weixin.qq.com/componenttcb/staticfilelist";

  /**
   * The constant API_GET_STATIC_FILE_URL.
   */
  String API_GET_STATIC_FILE_URL = "https://api.weixin.qq.com/componenttcb/staticuploadfile";

  //---------------------------------------------------------------------
  // Define interfaces
  //---------------------------------------------------------------------

  /**
   * Create env wx open result.
   *
   * @param alias the alias
   * @return the wx open result
   * @throws WxErrorException the wx error exception
   */
// env
  WxOpenResult createEnv(String alias) throws WxErrorException;

  /**
   * Modify env wx open result.
   *
   * @param env the env
   * @return the wx open result
   * @throws WxErrorException the wx error exception
   */
  WxOpenResult modifyEnv(String env) throws WxErrorException;

  /**
   * Describe envs wx open ma batch cloud describe env result.
   *
   * @return the wx open ma batch cloud describe env result
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaBatchCloudDescribeEnvResult describeEnvs() throws WxErrorException;

  /**
   * Batch share env wx open ma batch cloud share env result.
   *
   * @param data the data
   * @return the wx open ma batch cloud share env result
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaBatchCloudShareEnvResult batchShareEnv(Collection<WxOpenMaBatchCloudShareEnvMessage> data) throws WxErrorException;

  /**
   * Batch get env id wx open ma batch cloud get env id result.
   *
   * @param appIds the app ids
   * @return the wx open ma batch cloud get env id result
   * @throws WxErrorException the wx error exception
   */
  WxOpenMaBatchCloudGetEnvIdResult batchGetEnvId(Collection<String> appIds) throws WxErrorException;

  // cf

  /**
   * Batch create cf wx open result.
   *
   * @param envs    the envs
   * @param zipFile 包含函数代码文件的 zip 格式文件，使用该接口时要求将 zip 文件的内容转成 base64 编码，最大支持20M
   * @param vpcId   VPC唯一标识
   * @return the wx open result
   */
  WxOpenResult batchCreateCf(List<String> envs, String zipFile, String vpcId) throws WxErrorException;

  /**
   * Batch update cf wx open result.
   *
   * @param envs         the envs
   * @param zipFile      the zip file
   * @param functionName the function name
   * @return the wx open result
   */
  WxOpenResult batchUpdateCf(List<String> envs, String zipFile, String functionName) throws WxErrorException;

  /**
   * Update cf config wx open result.
   *
   * @param wxOpenMaBatchCloudUpdateCfConfigMessage the wx open ma batch cloud update cf config message
   * @return the wx open result
   */
  WxOpenResult updateCfConfig(WxOpenMaBatchCloudUpdateCfConfigMessage wxOpenMaBatchCloudUpdateCfConfigMessage) throws WxErrorException;

  /**
   * Delete cf wx open result.
   *
   * @param env          the env
   * @param functionName the function name
   * @return the wx open result
   */
  WxOpenResult deleteCf(String env, String functionName) throws WxErrorException;

  /**
   * Gets cf list result.
   *
   * @param env       the env
   * @param limit     the limit
   * @param offset    the offset
   * @param searchKey the search key
   * @return the cf list result
   */
  WxOpenMaBatchCloudGetCfListResult getCfListResult(String env, Long limit, Long offset, String searchKey) throws WxErrorException;

  /**
   * Gets triggers.
   *
   * @param env          the env
   * @param functionName the function name
   * @return the triggers
   */
  WxOpenMaBatchCloudGetTriggersResult getTriggers(String env, String functionName) throws WxErrorException;

  /**
   * Batch update trigger wx open ma batch cloud batch update trigger result.
   *
   * @param wxOpenMaBatchCloudBatchUpdateTriggerMessage the wx open ma batch cloud batch update trigger message
   * @return the wx open ma batch cloud batch update trigger result
   */
  WxOpenMaBatchCloudBatchUpdateTriggerResult batchUpdateTrigger(WxOpenMaBatchCloudBatchUpdateTriggerMessage wxOpenMaBatchCloudBatchUpdateTriggerMessage) throws WxErrorException;
  // ss

}
