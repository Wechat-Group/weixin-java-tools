package me.chanjar.weixin.open.api;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.open.bean.message.WxOpenXmlMessage;
import me.chanjar.weixin.open.bean.result.*;

import java.util.List;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public interface WxOpenComponentService {

  String API_COMPONENT_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
  String API_CREATE_PREAUTHCODE_URL = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode";
  String API_QUERY_AUTH_URL = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth";
  String API_AUTHORIZER_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token";
  String API_GET_AUTHORIZER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info";
  String API_GET_AUTHORIZER_OPTION_URL = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_option";
  String API_SET_AUTHORIZER_OPTION_URL = "https://api.weixin.qq.com/cgi-bin/component/api_set_authorizer_option";


  String COMPONENT_LOGIN_PAGE_URL = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=%s&pre_auth_code=%s&redirect_uri=%s";
  String CONNECT_OAUTH2_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&component_appid=%s#wechat_redirect";

  /**
   * 用code换取oauth2的access token
   */
  String OAUTH2_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=%s&code=%s&grant_type=authorization_code&component_appid=%s";
  /**
   * 刷新oauth2的access token
   */
  String OAUTH2_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/component/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s&component_appid==%s";

  /**
   * 拉取当前所有已授权的帐号基本信息
   */
  public static final String API_API_GET_AUTHORIZER_LIST = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_list?component_access_token=%s";
  /**
   * 修改服务器地址
   */
  public static final String API_MODIFY_DOMAIN = "https://api.weixin.qq.com/wxa/modify_domain?access_token=%s";
  /**
   * 绑定微信用户为小程序体验者
   */
  public static final String API_BIND_TESTER = "https://api.weixin.qq.com/wxa/bind_tester?lang=zh_CN&access_token=%s";
  /**
   * 解除绑定小程序的体验者
   */
  public static final String API_UNBIND_TESTER = "https://api.weixin.qq.com/wxa/unbind_tester?lang=zh_CN&access_token=%s";
  /**
   * 为授权的小程序帐号上传小程序代码
   */
  public static final String API_COMMIT = "https://api.weixin.qq.com/wxa/commit?lang=zh_CN&access_token=%s";
  /**
   * 获取体验小程序的体验二维码
   */
  public static final String API_GET_QRCODE = "https://api.weixin.qq.com/wxa/get_qrcode?lang=zh_CN&access_token=%s";
  /**
   * 获取授权小程序帐号的可选类目
   */
  public static final String API_GET_CATEGORY = "https://api.weixin.qq.com/wxa/get_category?lang=zh_CN&access_token=%s";
  /**
   * 获取小程序的第三方提交代码的页面配置（仅供第三方开发者代小程序调用）
   */
  public static final String API_GET_PAGE = "https://api.weixin.qq.com/wxa/get_page?lang=zh_CN&access_token=%s";
  /**
   * 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）
   */
  public static final String API_SUBMIT_AUDIT = "https://api.weixin.qq.com/wxa/submit_audit?lang=zh_CN&access_token=%s";
  /**
   * 查询某个指定版本的审核状态（仅供第三方代小程序调用）
   */
  public static final String API_GET_AUDITSTATUS = "https://api.weixin.qq.com/wxa/get_auditstatus?lang=zh_CN&access_token=%s";
  /**
   * 查询最新一次提交的审核状态（仅供第三方代小程序调用）
   */
  public static final String API_GET_LATEST_AUDITSTATUS = "https://api.weixin.qq.com/wxa/get_latest_auditstatus?lang=zh_CN&access_token=%s";
  /**
   * 发布已通过审核的小程序（仅供第三方代小程序调用）
   */
  public static final String API_RELEASE = "https://api.weixin.qq.com/wxa/release?lang=zh_CN&access_token=%s";
  /**
   * 修改小程序线上代码的可见状态（仅供第三方代小程序调用）
   */
  public static final String API_CHANGE_VISITSTATUS = "https://api.weixin.qq.com/wxa/change_visitstatus?lang=zh_CN&access_token=%s";
  /**
   * 将公众号/小程序从开放平台帐号下解绑
   */
  public static final String API_UNBIND = "https://api.weixin.qq.com/cgi-bin/open/unbind?lang=zh_CN&access_token=%s";

  /**
   * 获取小程序模板库标题列表
   */
  public static final String API_TEMPLATE_LIBRARY_LIST = "https://api.weixin.qq.com/cgi-bin/wxopen/template/library/list?access_token=%s";
  /**
   * 获取模板库某个模板标题下关键词库
   */
  public static final String API_TEMPLATE_LIBRARY_GET = "https://api.weixin.qq.com/cgi-bin/wxopen/template/library/get?access_token=%s";
  /**
   * 组合模板并添加至帐号下的个人模板库
   */
  public static final String API_TEMPLATE_ADD = "https://api.weixin.qq.com/cgi-bin/wxopen/template/add?access_token=%s";
  /**
   * 获取帐号下已存在的模板列表
   */
  public static final String API_TEMPLATE_LIST = "https://api.weixin.qq.com/cgi-bin/wxopen/template/list?access_token=%s";
  /**
   * 删除帐号下的某个模板
   */
  public static final String API_TEMPLATE_DEL = "https://api.weixin.qq.com/cgi-bin/wxopen/template/del?access_token=%s";
  /**
   * 获取草稿箱内的所有临时代码草稿
   */
  public static final String API_GET_TEMPLATE_DRAFT_LIST = "https://api.weixin.qq.com/wxa/gettemplatedraftlist?access_token=%s";
  /**
   * 获取代码模版库中的所有小程序代码模版
   */
  public static final String API_GET_TEMPLATE_LIST = "https://api.weixin.qq.com/wxa/gettemplatelist?access_token=%s";
  /**
   * 将草稿箱的草稿选为小程序代码模版
   */
  public static final String API_ADD_TO_TEMPLATE = "https://api.weixin.qq.com/wxa/addtotemplate?access_token=%s";
  /**
   * 删除指定小程序代码模版
   */
  public static final String API_DELETE_TEMPLATE = "https://api.weixin.qq.com/wxa/deletetemplate?access_token=%s";

  WxMpService getWxMpServiceByAppid(String appid);

  WxOpenConfigStorage getWxOpenConfigStorage();

  boolean checkSignature(String timestamp, String nonce, String signature);

  String getComponentAccessToken(boolean forceRefresh) throws WxErrorException;

  /**
   * 获取用户授权页URL（来路URL和成功跳转URL 的域名都需要为三方平台设置的 登录授权的发起页域名）
   */
  String getPreAuthUrl(String redirectURI) throws WxErrorException;

  String route(WxOpenXmlMessage wxMessage) throws WxErrorException;

  /**
   * 使用授权码换取公众号或小程序的接口调用凭据和授权信息
   */
  WxOpenQueryAuthResult getQueryAuth(String authorizationCode) throws WxErrorException;

  /**
   * 获取授权方的帐号基本信息
   */
  WxOpenAuthorizerInfoResult getAuthorizerInfo(String authorizerAppid) throws WxErrorException;

  /**
   * 获取授权方的选项设置信息
   */
  WxOpenAuthorizerOptionResult getAuthorizerOption(String authorizerAppid, String optionName) throws WxErrorException;

  /**
   * 设置授权方的选项信息
   */
  WxError setAuthorizerOption(String authorizerAppid, String optionName, String optionValue) throws WxErrorException;

  String getAuthorizerAccessToken(String appid, boolean forceRefresh) throws WxErrorException;

  WxMpOAuth2AccessToken oauth2getAccessToken(String appid, String code) throws WxErrorException;

  boolean checkSignature(String appId, String timestamp, String nonce, String signature);

  WxMpOAuth2AccessToken oauth2refreshAccessToken(String appid, String refreshToken) throws WxErrorException;

  String oauth2buildAuthorizationUrl(String appid, String redirectURI, String scope, String state);


  /**
   * 拉取当前已授权的帐号基本信息
   *
   * @param offset
   * @param count
   * @return
   * @throws WxErrorException
   */
  WxOpenAuthorizerListResult getAuthorizerList(Integer offset, Integer count) throws WxErrorException;

  /**
   * 修改服务器地址
   *
   * @param appId
   * @param action
   * @param domain
   * @return
   * @throws WxErrorException
   */
  WxOpenWxaDomain modifyDomain(String appId, String action, WxOpenWxaDomain domain) throws WxErrorException;

  /**
   * 绑定微信用户为小程序体验者
   *
   * @param appId
   * @param wechatid
   * @return
   * @throws WxErrorException
   */
  void bindUester(String appId, String wechatid) throws WxErrorException;

  /**
   * 解除绑定小程序的体验者
   *
   * @param appId
   * @param wechatid
   * @throws WxErrorException
   */
  void unbindTester(String appId, String wechatid) throws WxErrorException;

  /**
   * 为授权的小程序帐号上传小程序代码
   *
   * @param appId
   * @param templateId
   * @param extJson
   * @param userVersion
   * @param userDesc
   * @throws WxErrorException
   */
  void commit(String appId, String templateId, String extJson, String userVersion, String userDesc)
    throws WxErrorException;

  /**
   * 获取体验小程序的体验二维码
   *
   * @param appId
   * @return
   */
  String getQrcode(String appId);

  /**
   * 获取授权小程序帐号的可选类目
   *
   * @return
   * @throws WxErrorException
   */
  WxOpenWxaCategoryResult getCategory(String appId) throws WxErrorException;

  /**
   * 获取小程序的第三方提交代码的页面配置（仅供第三方开发者代小程序调用）
   *
   * @param appId
   * @return
   * @throws WxErrorException
   */
  WxOpenWxaPageResult getPage(String appId) throws WxErrorException;

  /**
   * 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）
   *
   * @param appId
   * @param audit
   * @return
   * @throws WxErrorException
   */
  WxOpenWxaAuditResult submitAudit(String appId, WxOpenWxaSubmitAudit audit) throws WxErrorException;

  /**
   * 查询某个指定版本的审核状态（仅供第三方代小程序调用）
   *
   * @param appId
   * @param auditid
   * @return
   * @throws WxErrorException
   */
  WxOpenWxaAuditStatusResult getAuditstatus(String appId, String auditid) throws WxErrorException;

  /**
   * 查询最新一次提交的审核状态（仅供第三方代小程序调用）
   *
   * @param appId
   * @return
   * @throws WxErrorException
   */
  WxOpenWxaAuditStatusResult getLatestAuditstatus(String appId) throws WxErrorException;

  /**
   * 发布已通过审核的小程序（仅供第三方代小程序调用）
   *
   * @param appId
   * @throws WxErrorException
   */
  void release(String appId) throws WxErrorException;

  /**
   * 修改小程序线上代码的可见状态（仅供第三方代小程序调用）
   *
   * @param appId
   * @param action
   * @throws WxErrorException
   */
  void changeVisitstatus(String appId, String action) throws WxErrorException;

  /**
   * 获取小程序模板库标题列表
   *
   * @param appId
   * @param offset
   * @param count
   * @return
   * @throws WxErrorException
   */
  WxOpenWxaTemplateLibraryListResult libraryList(String appId, Integer offset, Integer count) throws WxErrorException;

  /**
   * 获取模板库某个模板标题下关键词库
   *
   * @param id
   * @return
   * @throws WxErrorException
   */
  WxOpenWxaTemplateLibraryResult libraryGet(String id) throws WxErrorException;

  /**
   * 组合模板并添加至帐号下的个人模板库
   *
   * @param appId
   * @param id
   * @param keywordIdList
   * @return
   * @throws WxErrorException
   */
  WxOpenWxaAddTemplateResult templateAdd(String appId, String id, List<String> keywordIdList) throws WxErrorException;

  /**
   * 获取帐号下已存在的模板列表
   *
   * @param appId
   * @param offset
   * @param count
   * @throws WxErrorException
   */
  WxOpenWxaTemplateListResult templateList(String appId, Integer offset, Integer count) throws WxErrorException;

  /**
   * 删除帐号下的某个模板
   *
   * @param appId
   * @param templateId
   * @return
   * @throws WxErrorException
   */
  void templateDel(String appId, String templateId) throws WxErrorException;

  /**
   * 获取草稿箱内的所有临时代码草稿
   *
   * @throws WxErrorException
   */
  public WxOpenWxaCodeTemplateDraftListResult getTemplateDraftList() throws WxErrorException;

  /**
   * 获取代码模版库中的所有小程序代码模版
   *
   * @throws WxErrorException
   */
  public WxOpenWxaCodeTemplateListResult getTemplateList() throws WxErrorException;

  /**
   * 将草稿箱的草稿选为小程序代码模版
   *
   * @param draftId
   * @throws WxErrorException
   */
  public void addToTemplate(String draftId) throws WxErrorException;

  /**
   * 删除指定小程序代码模版
   *
   * @param templateId
   * @throws WxErrorException
   */
  public void deleteTemplate(String templateId) throws WxErrorException;

}
