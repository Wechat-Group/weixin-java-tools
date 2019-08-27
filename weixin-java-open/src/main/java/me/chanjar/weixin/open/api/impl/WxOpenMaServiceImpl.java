package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.api.WxOpenMaService;
import me.chanjar.weixin.open.bean.ma.WxMaOpenCommitExtInfo;
import me.chanjar.weixin.open.bean.ma.WxMaQrcodeParam;
import me.chanjar.weixin.open.bean.message.WxOpenMaSubmitAuditMessage;
import me.chanjar.weixin.open.bean.result.*;
import me.chanjar.weixin.open.util.requestexecuter.ma.MaQrCodeRequestExecutor;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 * <pre>
 *     增加开放平台代小程序管理服务能力
 *     说明：这里让这个服务公开便于调用者模拟本地测试服务
 * </pre>
 * @author yqx
 * @date 2018-09-12
 */
public class WxOpenMaServiceImpl extends WxMaServiceImpl implements WxOpenMaService {
  private WxOpenComponentService wxOpenComponentService;
  private WxMaConfig wxMaConfig;
  private String appId;

  public WxOpenMaServiceImpl(WxOpenComponentService wxOpenComponentService, String appId, WxMaConfig wxMaConfig) {
    this.wxOpenComponentService = wxOpenComponentService;
    this.appId = appId;
    this.wxMaConfig = wxMaConfig;
    initHttp();
  }

  @Override
  public WxMaJscode2SessionResult jsCode2SessionInfo(String jsCode) throws WxErrorException {
    return wxOpenComponentService.miniappJscode2Session(appId, jsCode);
  }

  @Override
  public WxMaConfig getWxMaConfig() {
    return wxMaConfig;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    return wxOpenComponentService.getAuthorizerAccessToken(appId, forceRefresh);
  }

  /**
   * 获得小程序的域名配置信息
   *
   * @return
   */
  @Override
  public WxOpenMaDomainResult getDomain() throws WxErrorException {
    return modifyDomain("get", null, null, null, null);
  }

  /**
   * 修改服务器域名
   *
   * @param action              delete删除, set覆盖, get获取
   * @param requestdomainList
   * @param wsrequestdomainList
   * @param uploaddomainList
   * @param downloaddomainList
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenMaDomainResult modifyDomain(String action, List<String> requestdomainList, List<String> wsrequestdomainList, List<String> uploaddomainList, List<String> downloaddomainList) throws WxErrorException {

//    if (!"get".equals(action) && (requestdomainList == null || wsrequestdomainList == null || uploaddomainList == null || downloaddomainList == null)) {
//      throw new WxErrorException(WxError.builder().errorCode(44004).errorMsg("域名参数不能为空").build());
//    }
    JsonObject requestJson = new JsonObject();
    requestJson.addProperty("action", action);
    if (!"get".equals(action)) {
      requestJson.add("requestdomain", toJsonArray(requestdomainList));
      requestJson.add("wsrequestdomain", toJsonArray(wsrequestdomainList));
      requestJson.add("uploaddomain", toJsonArray(uploaddomainList));
      requestJson.add("downloaddomain", toJsonArray(downloaddomainList));
    }
    String response = post(API_MODIFY_DOMAIN, GSON.toJson(requestJson));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaDomainResult.class);
  }

  /**
   * 获取小程序的业务域名
   *
   * @return
   */
  @Override
  public String getWebViewDomain() throws WxErrorException {
    return setWebViewDomain("get", null);
  }

  /**
   * 获取小程序的业务域名
   *
   * @return
   */
  @Override
  public WxOpenResult getWebViewDomainInfo() throws WxErrorException {
    return setWebViewDomainInfo("get", null);
  }

  /**
   * 设置小程序的业务域名
   *
   * @param action     add添加, delete删除, set覆盖
   * @param domainList
   * @return
   */
  @Override
  public String setWebViewDomain(String action, List<String> domainList) throws WxErrorException {
    JsonObject requestJson = new JsonObject();
    requestJson.addProperty("action", action);
    if (!"get".equals(action)) {
      requestJson.add("webviewdomain", toJsonArray(domainList));
    }
    String response = post(API_SET_WEBVIEW_DOMAIN, GSON.toJson(requestJson));
    //TODO 转化为对象返回
    return response;
  }


  /**
   * 设置小程序的业务域名
   *
   * @param action add添加, delete删除, set覆盖
   * @return
   */
  @Override
  public WxOpenResult setWebViewDomainInfo(String action, List<String> domainList) throws WxErrorException {
    String response = this.setWebViewDomain(action, domainList);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }


  /**
   * 获取小程序的信息,GET请求
   * <pre>
   *     注意：这里不能直接用小程序的access_token
   *     //TODO 待调整
   * </pre>
   *
   * @return
   * @throws WxErrorException
   */
  @Override
  public String getAccountBasicInfo() throws WxErrorException {
    String response = get(API_GET_ACCOUNT_BASICINFO, "");
    return response;
  }


  /**
   * 绑定小程序体验者
   *
   * @param wechatid 体验者微信号（不是openid）
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult bindTester(String wechatid) throws WxErrorException {
    JsonObject paramJson = new JsonObject();
    paramJson.addProperty("wechatid", wechatid);
    String response = post(API_BIND_TESTER, GSON.toJson(paramJson));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  /**
   * 解除绑定小程序体验者
   *
   * @param wechatid 体验者微信号（不是openid）
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult unbindTester(String wechatid) throws WxErrorException {
    JsonObject paramJson = new JsonObject();
    paramJson.addProperty("wechatid", wechatid);
    String response = post(API_UNBIND_TESTER, GSON.toJson(paramJson));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  /**
   * 获得体验者列表
   *
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenMaTesterListResult getTesterList() throws WxErrorException {
    JsonObject paramJson = new JsonObject();
    paramJson.addProperty("action", "get_experiencer");
    String response = post(API_GET_TESTERLIST, GSON.toJson(paramJson));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaTesterListResult.class);
  }


  /**
   * 设置小程序隐私设置（是否可被搜索）
   *
   * @param status 1表示不可搜索，0表示可搜索
   */
  @Override
  public WxOpenResult changeWxaSearchStatus(Integer status) throws WxErrorException {
    JsonObject paramJson = new JsonObject();
    paramJson.addProperty("status", status);
    String response = post(API_CHANGE_WXA_SEARCH_STATUS, GSON.toJson(paramJson));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }


  /**
   * 2. 查询小程序当前隐私设置（是否可被搜索）
   */
  @Override
  public WxOpenMaSearchStatusResult getWxaSearchStatus() throws WxErrorException {
    JsonObject paramJson = new JsonObject();
    String response = post(API_GET_WXA_SEARCH_STATUS, GSON.toJson(paramJson));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaSearchStatusResult.class);
  }


  /**
   * 3.1 获取展示的公众号信息
   */
  @Override
  public WxOpenMaShowItemResult getShowWxaItem() throws WxErrorException {
    String response = get(API_GET_SHOW_WXA_ITEM, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaShowItemResult.class);
  }


  /**
   * 3.2 设置展示的公众号
   *
   * @param flag    0 关闭，1 开启
   * @param mpappid 如果开启，需要传新的公众号appid
   */
  @Override
  public WxOpenResult updateShowwxaitem(Integer flag, String mpappid) throws WxErrorException {
    JsonObject paramJson = new JsonObject();
    paramJson.addProperty("wxa_subscribe_biz_flag", flag);
    paramJson.addProperty("appid", mpappid);
    String response = post(API_UPDATE_SHOW_WXA_ITEM, GSON.toJson(paramJson));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }


  /**
   * 1、为授权的小程序帐号上传小程序代码
   *
   * @param templateId  代码模板ID
   * @param userVersion 用户定义版本
   * @param userDesc    用户定义版本描述
   * @param extInfo     第三方自定义的配置
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult codeCommit(Long templateId, String userVersion, String userDesc, WxMaOpenCommitExtInfo extInfo) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("template_id", templateId);
    params.addProperty("user_version", userVersion);
    params.addProperty("user_desc", userDesc);
    //注意：ext_json必须是字符串类型
    params.addProperty("ext_json", GSON.toJson(extInfo));
    String response = post(API_CODE_COMMIT, GSON.toJson(params));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  /**
   * 获取体验小程序的体验二维码
   *
   * @param pagePath
   * @param params
   * @return
   */
  @Override
  public File getTestQrcode(String pagePath, Map<String, String> params) throws WxErrorException {
    WxMaQrcodeParam qrcodeParam = WxMaQrcodeParam.create(pagePath);
    qrcodeParam.addPageParam(params);
    return execute(MaQrCodeRequestExecutor.create(getRequestHttp()), API_TEST_QRCODE, qrcodeParam);
  }

  /**
   * 获取授权小程序帐号的可选类目
   * <p>
   * 注意：该接口可获取已设置的二级类目及用于代码审核的可选三级类目。
   * </p>
   *
   * @return WxOpenMaCategoryListResult
   * @throws WxErrorException
   */
  @Override
  public WxOpenMaCategoryListResult getCategoryList() throws WxErrorException {
    String response = get(API_GET_CATEGORY, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaCategoryListResult.class);
  }

  /**
   * 获取小程序的第三方提交代码的页面配置（仅供第三方开发者代小程序调用）
   *
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenMaPageListResult getPageList() throws WxErrorException {
    String response = get(API_GET_PAGE, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaPageListResult.class);
  }

  /**
   * 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）
   *
   * @param submitAuditMessage
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenMaSubmitAuditResult submitAudit(WxOpenMaSubmitAuditMessage submitAuditMessage) throws WxErrorException {
    String response = post(API_SUBMIT_AUDIT, GSON.toJson(submitAuditMessage));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaSubmitAuditResult.class);
  }

  /**
   * 7. 查询某个指定版本的审核状态（仅供第三方代小程序调用）
   *
   * @param auditid
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenMaQueryAuditResult getAuditStatus(Long auditid) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("auditid", auditid);
    String response = post(API_GET_AUDIT_STATUS, GSON.toJson(params));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaQueryAuditResult.class);
  }

  /**
   * 8. 查询最新一次提交的审核状态（仅供第三方代小程序调用）
   *
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenMaQueryAuditResult getLatestAuditStatus() throws WxErrorException {
    String response = get(API_GET_LATEST_AUDIT_STATUS, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaQueryAuditResult.class);
  }

  /**
   * 9. 发布已通过审核的小程序（仅供第三方代小程序调用）
   * <p>
   * 请填写空的数据包，POST的json数据包为空即可。
   * </p>
   *
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult releaesAudited() throws WxErrorException {
    JsonObject params = new JsonObject();
    String response = post(API_RELEASE, GSON.toJson(params));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }


  /**
   * 10. 修改小程序线上代码的可见状态（仅供第三方代小程序调用）
   *
   * @param action 设置可访问状态，发布后默认可访问，close为不可见，open为可见
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult changeVisitstatus(String action) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("action", action);
    String response = post(API_CHANGE_VISITSTATUS, GSON.toJson(params));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }


  /**
   * 11. 小程序版本回退（仅供第三方代小程序调用）
   *
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult revertCodeReleaes() throws WxErrorException {
    String response = get(API_REVERT_CODE_RELEASE, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }


  /**
   * 15. 小程序审核撤回
   * <p>
   * 单个帐号每天审核撤回次数最多不超过1次，一个月不超过10次。
   * </p>
   *
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult undoCodeAudit() throws WxErrorException {
    String response = get(API_UNDO_CODE_AUDIT, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  /**
   * 12. 查询当前设置的最低基础库版本及各版本用户占比 （仅供第三方代小程序调用）
   *
   * @return
   * @throws WxErrorException
   */
  @Override
  public String getSupportVersion() throws WxErrorException {
    JsonObject params = new JsonObject();
    String response = post(API_GET_WEAPP_SUPPORT_VERSION, GSON.toJson(params));
    return response;
  }


  /**
   * 12. 查询当前设置的最低基础库版本及各版本用户占比 （仅供第三方代小程序调用）
   *
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenMaWeappSupportVersionResult getSupportVersionInfo() throws WxErrorException {
    String response = this.getSupportVersion();
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaWeappSupportVersionResult.class);
  }

  /**
   * 13. 设置最低基础库版本（仅供第三方代小程序调用）
   *
   * @param version
   * @return
   * @throws WxErrorException
   */
  @Override
  public String setSupportVersion(String version) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("version", version);
    String response = post(API_SET_WEAPP_SUPPORT_VERSION, GSON.toJson(params));
    return response;
  }


  /**
   * 13. 设置最低基础库版本（仅供第三方代小程序调用）
   *
   * @param version
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult setSupportVersionInfo(String version) throws WxErrorException {
    String response = this.setSupportVersion(version);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }


  /**
   * 16. 小程序分阶段发布 - 1)分阶段发布接口
   *
   * @param grayPercentage 灰度的百分比，1到100的整数
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult grayrelease(Integer grayPercentage) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("gray_percentage", grayPercentage);
    String response = post(API_GRAY_RELEASE, GSON.toJson(params));
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }


  /**
   * 16. 小程序分阶段发布 - 2)取消分阶段发布
   *
   * @return
   * @throws WxErrorException
   */
  @Override

  public WxOpenResult revertgrayrelease() throws WxErrorException {
    String response = get(API_REVERT_GRAY_RELEASE, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }


  /**
   * 16. 小程序分阶段发布 - 3)查询当前分阶段发布详情
   *
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenMaGrayReleasePlanResult getgrayreleaseplan() throws WxErrorException {
    String response = get(API_GET_GRAY_RELEASE_PLAN, null);
    return WxMaGsonBuilder.create().fromJson(response, WxOpenMaGrayReleasePlanResult.class);
  }


  /**
   * 将字符串对象转化为GsonArray对象
   *
   * @param strList
   * @return
   */
  private JsonArray toJsonArray(List<String> strList) {
    JsonArray jsonArray = new JsonArray();
    if (strList != null && !strList.isEmpty()) {
      for (String str : strList) {
        jsonArray.add(str);
      }
    }
    return jsonArray;
  }
}
