package me.chanjar.weixin.open.api.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.bean.WxOpenAuthorizerAccessToken;
import me.chanjar.weixin.open.bean.WxOpenComponentAccessToken;
import me.chanjar.weixin.open.bean.auth.WxOpenAuthorizationInfo;
import me.chanjar.weixin.open.bean.message.WxOpenXmlMessage;
import me.chanjar.weixin.open.bean.result.*;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenComponentServiceImpl implements WxOpenComponentService {
  private static final Map<String, WxMpService> WX_OPEN_MP_SERVICE_MAP = new Hashtable<>();
  protected final Logger log = LoggerFactory.getLogger(this.getClass());
  private WxOpenService wxOpenService;

  public WxOpenComponentServiceImpl(WxOpenService wxOpenService) {
    this.wxOpenService = wxOpenService;
  }

  @Override
  public WxMpService getWxMpServiceByAppid(String appId) {
    WxMpService wxMpService = WX_OPEN_MP_SERVICE_MAP.get(appId);
    if (wxMpService == null) {
      synchronized (WX_OPEN_MP_SERVICE_MAP) {
        wxMpService = WX_OPEN_MP_SERVICE_MAP.get(appId);
        if (wxMpService == null) {
          wxMpService = new WxOpenMpServiceImpl(this, appId, getWxOpenConfigStorage().getWxMpConfigStorage(appId));

          WX_OPEN_MP_SERVICE_MAP.put(appId, wxMpService);
        }
      }
    }
    return wxMpService;
  }

  public WxOpenService getWxOpenService() {
    return wxOpenService;
  }

  @Override
  public WxOpenConfigStorage getWxOpenConfigStorage() {
    return wxOpenService.getWxOpenConfigStorage();
  }

  @Override
  public boolean checkSignature(String timestamp, String nonce, String signature) {
    try {
      return SHA1.gen(getWxOpenConfigStorage().getComponentToken(), timestamp, nonce)
        .equals(signature);
    } catch (Exception e) {
      this.log.error("Checking signature failed, and the reason is :" + e.getMessage());
      return false;
    }
  }

  @Override
  public String getComponentAccessToken(boolean forceRefresh) throws WxErrorException {

    if (this.getWxOpenConfigStorage().isComponentAccessTokenExpired() || forceRefresh) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
      jsonObject.addProperty("component_appsecret", getWxOpenConfigStorage().getComponentAppSecret());
      jsonObject.addProperty("component_verify_ticket", getWxOpenConfigStorage().getComponentVerifyTicket());

      String responseContent = this.getWxOpenService().post(API_COMPONENT_TOKEN_URL, jsonObject.toString());
      WxOpenComponentAccessToken componentAccessToken = WxOpenComponentAccessToken.fromJson(responseContent);
      getWxOpenConfigStorage().updateComponentAccessTokent(componentAccessToken);
    }
    return this.getWxOpenConfigStorage().getComponentAccessToken();
  }

  private String post(String uri, String postData) throws WxErrorException {
    String componentAccessToken = getComponentAccessToken(false);
    String uriWithComponentAccessToken = uri + (uri.contains("?") ? "&" : "?") + "component_access_token=" + componentAccessToken;
    return getWxOpenService().post(uriWithComponentAccessToken, postData);
  }

  private String get(String uri) throws WxErrorException {
    String componentAccessToken = getComponentAccessToken(false);
    String uriWithComponentAccessToken = uri + (uri.contains("?") ? "&" : "?") + "component_access_token=" + componentAccessToken;
    return getWxOpenService().get(uriWithComponentAccessToken, null);
  }

  @Override
  public String getPreAuthUrl(String redirectURI) throws WxErrorException {

    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    String responseContent = post(API_CREATE_PREAUTHCODE_URL, jsonObject.toString());
    jsonObject = WxGsonBuilder.create().fromJson(responseContent, JsonObject.class);
    return String.format(COMPONENT_LOGIN_PAGE_URL, getWxOpenConfigStorage().getComponentAppId(), jsonObject.get("pre_auth_code").getAsString(), URIUtil.encodeURIComponent(redirectURI));
  }

  @Override
  public String route(final WxOpenXmlMessage wxMessage) throws WxErrorException {
    if (wxMessage == null) {
      throw new NullPointerException("message is empty");
    }
    if (StringUtils.equalsIgnoreCase(wxMessage.getInfoType(), "component_verify_ticket")) {
      getWxOpenConfigStorage().setComponentVerifyTicket(wxMessage.getComponentVerifyTicket());
      return "success";
    }
    //新增、跟新授权
    if (StringUtils.equalsAnyIgnoreCase(wxMessage.getInfoType(), "authorized", "updateauthorized")) {
      WxOpenQueryAuthResult queryAuth = wxOpenService.getWxOpenComponentService().getQueryAuth(wxMessage.getAuthorizationCode());
      if (queryAuth == null || queryAuth.getAuthorizationInfo() == null || queryAuth.getAuthorizationInfo().getAuthorizerAppid() == null) {
        throw new NullPointerException("getQueryAuth");
      }
      WxOpenAuthorizationInfo authorizationInfo = queryAuth.getAuthorizationInfo();
      if (authorizationInfo.getAuthorizerAccessToken() != null) {
        getWxOpenConfigStorage().updateAuthorizerAccessToken(authorizationInfo.getAuthorizerAppid(),
          authorizationInfo.getAuthorizerAccessToken(), authorizationInfo.getExpiresIn());
      }
      if (authorizationInfo.getAuthorizerRefreshToken() != null) {
        getWxOpenConfigStorage().setAuthorizerRefreshToken(authorizationInfo.getAuthorizerAppid(), authorizationInfo.getAuthorizerRefreshToken());
      }
      return "success";
    }
    return null;
  }

  @Override
  public WxOpenQueryAuthResult getQueryAuth(String authorizationCode) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    jsonObject.addProperty("authorization_code", authorizationCode);
    String responseContent = post(API_QUERY_AUTH_URL, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(responseContent, WxOpenQueryAuthResult.class);
  }

  @Override
  public WxOpenAuthorizerInfoResult getAuthorizerInfo(String authorizerAppid) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    jsonObject.addProperty("authorizer_appid", authorizerAppid);
    String responseContent = post(API_GET_AUTHORIZER_INFO_URL, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(responseContent, WxOpenAuthorizerInfoResult.class);
  }

  @Override
  public WxOpenAuthorizerOptionResult getAuthorizerOption(String authorizerAppid, String optionName) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    jsonObject.addProperty("authorizer_appid", authorizerAppid);
    jsonObject.addProperty("option_name", optionName);
    String responseContent = post(API_GET_AUTHORIZER_OPTION_URL, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(responseContent, WxOpenAuthorizerOptionResult.class);
  }

  @Override
  public WxError setAuthorizerOption(String authorizerAppid, String optionName, String optionValue) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    jsonObject.addProperty("authorizer_appid", authorizerAppid);
    jsonObject.addProperty("option_name", optionName);
    jsonObject.addProperty("option_value", optionValue);
    String responseContent = post(API_SET_AUTHORIZER_OPTION_URL, jsonObject.toString());
    return WxGsonBuilder.create().fromJson(responseContent, WxError.class);
  }

  @Override
  public String getAuthorizerAccessToken(String appId, boolean forceRefresh) throws WxErrorException {

    if (this.getWxOpenConfigStorage().isAuthorizerAccessTokenExpired(appId) || forceRefresh) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
      jsonObject.addProperty("authorizer_appid", appId);
      jsonObject.addProperty("authorizer_refresh_token", getWxOpenConfigStorage().getAuthorizerRefreshToken(appId));
      String responseContent = post(API_AUTHORIZER_TOKEN_URL, jsonObject.toString());

      WxOpenAuthorizerAccessToken wxOpenAuthorizerAccessToken = WxOpenAuthorizerAccessToken.fromJson(responseContent);
      getWxOpenConfigStorage().updateAuthorizerAccessToken(appId, wxOpenAuthorizerAccessToken);
    }
    return this.getWxOpenConfigStorage().getAuthorizerAccessToken(appId);
  }

  @Override
  public WxMpOAuth2AccessToken oauth2getAccessToken(String appId, String code) throws WxErrorException {
    String url = String.format(OAUTH2_ACCESS_TOKEN_URL, appId, code, getWxOpenConfigStorage().getComponentAppId());
    String responseContent = get(url);
    return WxMpOAuth2AccessToken.fromJson(responseContent);
  }

  @Override
  public boolean checkSignature(String appid, String timestamp, String nonce, String signature) {
    return false;
  }

  @Override
  public WxMpOAuth2AccessToken oauth2refreshAccessToken(String appId, String refreshToken) throws WxErrorException {
    String url = String.format(OAUTH2_REFRESH_TOKEN_URL, appId, refreshToken, getWxOpenConfigStorage().getComponentAppId());
    String responseContent = get(url);
    return WxMpOAuth2AccessToken.fromJson(responseContent);
  }

  @Override
  public String oauth2buildAuthorizationUrl(String appId, String redirectURI, String scope, String state) {
    return String.format(CONNECT_OAUTH2_AUTHORIZE_URL,
      appId, URIUtil.encodeURIComponent(redirectURI), scope, StringUtils.trimToEmpty(state), getWxOpenConfigStorage().getComponentAppId());
  }

  /**
   * 拉取当前已授权的帐号基本信息
   *
   * @param offset
   * @param count
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenAuthorizerListResult getAuthorizerList(Integer offset, Integer count) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("component_appid", this.wxOpenService.getWxOpenConfigStorage().getComponentAppId());
    json.addProperty("offset", offset);
    json.addProperty("count", count);
    String responseContent = this.wxOpenService.post(String.format(API_API_GET_AUTHORIZER_LIST,
      this.wxOpenService.getWxOpenConfigStorage().getComponentAccessToken()), json.toString());
    return WxOpenAuthorizerListResult.fromJson(responseContent);
  }

  /**
   * 修改服务器地址
   *
   * @param appId
   * @param domain
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenWxaDomain modifyDomain(String appId, String action, WxOpenWxaDomain domain) throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    map.put("action", action);
    if (domain.getRequestdomain() != null) {
      map.put("requestdomain", domain.getRequestdomain());
    }
    if (domain.getWsrequestdomain() != null) {
      map.put("wsrequestdomain", domain.getWsrequestdomain());
    }
    if (domain.getUploaddomain() != null) {
      map.put("uploaddomain", domain.getUploaddomain());
    }
    if (domain.getDownloaddomain() != null) {
      map.put("downloaddomain", domain.getDownloaddomain());
    }

    String responseContent = this.wxOpenService.post(
      String.format(API_MODIFY_DOMAIN, this.wxOpenService.getWxOpenConfigStorage().getAuthorizerAccessToken(appId)),
      new Gson().toJson(map));
    return WxOpenWxaDomain.fromJson(responseContent);
  }

  /**
   * 绑定微信用户为小程序体验者
   *
   * @param appId
   * @param wechatid
   * @return
   * @throws WxErrorException -1 :系统繁忙; 85001: 微信号不存在或微信号设置为不可搜索 ;85002: 小程序绑定的体验者数量达到上限;
   *                          85003: 微信号绑定的小程序体验者达到上限 ;85004: 微信号已经绑定
   */
  @Override
  public void bindUester(String appId, String wechatid) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("wechatid", wechatid);
    this.wxOpenService.post(
      String.format(API_BIND_TESTER, this.wxOpenService.getWxOpenConfigStorage().getAuthorizerAccessToken(appId)),
      json.toString());
  }

  /**
   * 解除绑定小程序的体验者
   *
   * @param appId
   * @param wechatid
   * @throws WxErrorException
   */
  @Override
  public void unbindTester(String appId, String wechatid) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("wechatid", wechatid);
    this.wxOpenService.post(
      String.format(API_UNBIND_TESTER, this.wxOpenService.getWxOpenConfigStorage().getAuthorizerAccessToken(appId)),
      json.toString());
  }

  /**
   * 为授权的小程序帐号上传小程序代码
   *
   * @param appId
   * @param templateId  代码库中的代码模版ID
   * @param extJson     第三方自定义的配置
   * @param userVersion 代码版本号，开发者可自定义
   * @param userDesc    代码描述，开发者可自定义
   * @return
   * @throws WxErrorException -1： 系统繁忙， 85013： 无效的自定义配置， 85014： 无效的模版编号
   */
  @Override
  public void commit(String appId, String templateId, String extJson, String userVersion, String userDesc) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("template_id", templateId);
    json.addProperty("ext_json", extJson);
    json.addProperty("user_version", userVersion);
    json.addProperty("user_desc", userDesc);
    this.wxOpenService.post(String.format(API_COMMIT, this.wxOpenService.getWxOpenConfigStorage().getAuthorizerAccessToken(appId)),
      json.toString());
  }

  /**
   * 获取体验小程序的体验二维码
   *
   * @param appId
   * @return
   */
  @Override
  public String getQrcode(String appId) {
    return API_GET_QRCODE.concat(this.wxOpenService.getWxOpenConfigStorage().getAuthorizerAccessToken(appId));
  }

  /**
   * 获取授权小程序帐号的可选类目
   *
   * @param appId
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenWxaCategoryResult getCategory(String appId) throws WxErrorException {
    String responseContent = this.wxOpenService.get(
      String.format(API_GET_CATEGORY, this.wxOpenService.getWxOpenConfigStorage().getAuthorizerAccessToken(appId)),
      new JsonObject().toString());

    return WxOpenWxaCategoryResult.fromJson(responseContent);
  }

  /**
   * 获取小程序的第三方提交代码的页面配置（仅供第三方开发者代小程序调用）
   *
   * @param appId
   * @return
   * @throws WxErrorException -1 :系统繁忙; 86000 : 不是由第三方代小程序进行调用; 86001 : 不存在第三方的已经提交的代码
   */
  @Override
  public WxOpenWxaPageResult getPage(String appId) throws WxErrorException {
    String responseContent = this.wxOpenService.get(
      String.format(API_GET_PAGE, this.wxOpenService.getWxOpenConfigStorage().getAuthorizerAccessToken(appId)),
      new JsonObject().toString());

    return WxOpenWxaPageResult.fromJson(responseContent);
  }

  /**
   * 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）
   *
   * @param appId
   * @param audit
   * @return
   * @throws WxErrorException -1 : 系统繁忙; 86000 : 不是由第三方代小程序进行调用; 86001 : 不存在第三方的已经提交的代码; 85006:
   *                          标签格式错误; 85007 : 页面路径错误; 85008 : 类目填写错误; 85009 : 已经有正在审核的版本; 85010
   *                          : item_list有项目为空; 85011 : 标题填写错误; 85023 :审核列表填写的项目数不在1-5以内; 86002
   *                          : 小程序还未设置昵称、头像、简介。请先设置完后再重新提交。
   */
  @Override
  public WxOpenWxaAuditResult submitAudit(String appId, WxOpenWxaSubmitAudit audit) throws WxErrorException {
    String responseContent = this.wxOpenService.post(
      String.format(API_SUBMIT_AUDIT, this.wxOpenService.getWxOpenConfigStorage().getAuthorizerAccessToken(appId)),
      audit.toJson());

    return WxOpenWxaAuditResult.fromJson(responseContent);
  }

  /**
   * 查询某个指定版本的审核状态（仅供第三方代小程序调用）
   *
   * @param appId
   * @param auditid
   * @return
   * @throws WxErrorException -1 :系统繁忙; 86000 :不是由第三方代小程序进行调用; 86001 : 不存在第三方的已经提交的代码; 85012 :
   *                          无效的审核id
   */
  @Override
  public WxOpenWxaAuditStatusResult getAuditstatus(String appId, String auditid) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("auditid", auditid);
    String responseContent = this.wxOpenService.post(
      String.format(API_GET_AUDITSTATUS, this.wxOpenService.getWxOpenConfigStorage().getAuthorizerAccessToken(appId)),
      json.toString());
    return WxOpenWxaAuditStatusResult.fromJson(responseContent);
  }

  /**
   * 查询最新一次提交的审核状态（仅供第三方代小程序调用）
   *
   * @param appId
   * @return
   * @throws WxErrorException -1 :系统繁忙; 86000 :不是由第三方代小程序进行调用; 86001 : 不存在第三方的已经提交的代码; 85012 :
   *                          无效的审核id
   */
  @Override
  public WxOpenWxaAuditStatusResult getLatestAuditstatus(String appId) throws WxErrorException {
    String responseContent = this.wxOpenService.post(
      String.format(API_GET_LATEST_AUDITSTATUS, this.wxOpenService.getWxOpenConfigStorage().getAuthorizerAccessToken(appId)),
      new JsonObject().toString());
    return WxOpenWxaAuditStatusResult.fromJson(responseContent);
  }

  /**
   * 发布已通过审核的小程序（仅供第三方代小程序调用）
   *
   * @return
   * @throws WxErrorException -1: 系统繁忙; 85019 :没有审核版本; 85020 : 审核状态未满足发布
   */
  @Override
  public void release(String appId) throws WxErrorException {
    this.wxOpenService.post(String.format(API_RELEASE, this.wxOpenService.getWxOpenConfigStorage().getAuthorizerAccessToken(appId)),
      new JsonObject().toString());
  }

  /**
   * 修改小程序线上代码的可见状态（仅供第三方代小程序调用）
   *
   * @param appId
   * @param action 设置可访问状态，发布后默认可访问，close为不可见，open为可见
   * @return
   * @throws WxErrorException -1 :系统繁忙; 85021 :状态不可变; 85022 :action非法
   */
  @Override
  public void changeVisitstatus(String appId, String action) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("action", action);
    this.wxOpenService.post(
      String.format(API_CHANGE_VISITSTATUS, this.wxOpenService.getWxOpenConfigStorage().getAuthorizerAccessToken(appId)),
      json.toString());

  }

  /**
   * 获取小程序模板库标题列表
   * offset和count用于分页，表示从offset开始，拉取count条记录，offset从0开始，count最大为20。
   *
   * @param appId
   * @param offset
   * @param count
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenWxaTemplateLibraryListResult libraryList(String appId, Integer offset, Integer count) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("offset", offset);
    json.addProperty("count", count);
    String responseContent = this.wxOpenService.post(
      String.format(API_TEMPLATE_LIBRARY_LIST, this.wxOpenService.getWxOpenConfigStorage().getAuthorizerAccessToken(appId)),
      json.toString());
    return WxOpenWxaTemplateLibraryListResult.fromJson(responseContent);
  }

  /**
   * @param id
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenWxaTemplateLibraryResult libraryGet(String id) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("id", id);
    String responseContent = this.wxOpenService.post(
      String.format(API_TEMPLATE_LIBRARY_GET, this.wxOpenService.getWxOpenConfigStorage().getComponentAccessToken()),
      json.toString());
    return WxOpenWxaTemplateLibraryResult.fromJson(responseContent);
  }

  /**
   * @param appId
   * @param id
   * @param keywordIdList
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenWxaAddTemplateResult templateAdd(String appId, String id, List<String> keywordIdList) throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    map.put("id", id);
    map.put("keyword_id_list", keywordIdList);
    String responseContent = this.wxOpenService.post(
      String.format(API_TEMPLATE_ADD, this.wxOpenService.getWxOpenConfigStorage().getAuthorizerAccessToken(appId)),
      new Gson().toJson(map));
    return WxOpenWxaAddTemplateResult.fromJson(responseContent);
  }

  /**
   * @param appId
   * @param offset
   * @param count
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenWxaTemplateListResult templateList(String appId, Integer offset, Integer count) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("offset", offset);
    json.addProperty("count", count);
    String responseContent = this.wxOpenService.post(
      String.format(API_TEMPLATE_LIBRARY_GET, this.wxOpenService.getWxOpenConfigStorage().getAuthorizerAccessToken(appId)),
      json.toString());
    return WxOpenWxaTemplateListResult.fromJson(responseContent);

  }

  /**
   * @param appId
   * @param templateId
   * @return
   * @throws WxErrorException
   */
  @Override
  public void templateDel(String appId, String templateId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("template_id", templateId);
    this.wxOpenService.post(
      String.format(API_TEMPLATE_LIBRARY_GET, this.wxOpenService.getWxOpenConfigStorage().getAuthorizerAccessToken(appId)),
      json.toString());
  }

  /**
   * 获取草稿箱内的所有临时代码草稿
   *
   * @throws WxErrorException
   */
  @Override
  public WxOpenWxaCodeTemplateDraftListResult getTemplateDraftList() throws WxErrorException {
    String responseContent = this.wxOpenService.get(
      String.format(API_GET_TEMPLATE_DRAFT_LIST, this.wxOpenService.getWxOpenConfigStorage().getComponentAccessToken()),
      new JsonObject().toString());

    return WxOpenWxaCodeTemplateDraftListResult.fromJson(responseContent);
  }

  /**
   * 获取代码模版库中的所有小程序代码模版
   *
   * @throws WxErrorException
   */
  @Override
  public WxOpenWxaCodeTemplateListResult getTemplateList() throws WxErrorException {
    String responseContent = this.wxOpenService.get(
      String.format(API_GET_TEMPLATE_LIST, this.wxOpenService.getWxOpenConfigStorage().getComponentAccessToken()),
      new JsonObject().toString());

    return WxOpenWxaCodeTemplateListResult.fromJson(responseContent);
  }

  /**
   * 将草稿箱的草稿选为小程序代码模版
   *
   * @throws WxErrorException
   */
  @Override
  public void addToTemplate(String draftId) throws WxErrorException {
    this.wxOpenService.post(String.format(API_ADD_TO_TEMPLATE, this.wxOpenService.getWxOpenConfigStorage().getComponentAccessToken()),
      new JsonObject().toString());
  }

  /**
   * 删除指定小程序代码模版
   *
   * @param templateId
   * @throws WxErrorException
   */
  @Override
  public void deleteTemplate(String templateId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("template_id", templateId);
    this.wxOpenService.post(String.format(API_DELETE_TEMPLATE, this.wxOpenService.getWxOpenConfigStorage().getComponentAccessToken()),
      json.toString());
  }

}
