package me.chanjar.weixin.open.api;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.open.bean.WxOpenMaCodeTemplate;
import me.chanjar.weixin.open.bean.message.WxOpenXmlMessage;
import me.chanjar.weixin.open.bean.result.WxOpenAuthorizerInfoResult;
import me.chanjar.weixin.open.bean.result.WxOpenAuthorizerOptionResult;
import me.chanjar.weixin.open.bean.result.WxOpenQueryAuthResult;

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
  String OAUTH2_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/component/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s&component_appid=%s";

  String MINIAPP_JSCODE_2_SESSION = "https://api.weixin.qq.com/sns/component/jscode2session?appid=%s&js_code=%s&grant_type=authorization_code&component_appid=%s";

  WxMpService getWxMpServiceByAppid(String appid);

  WxMaService getWxMaServiceByAppid(String appid);

  WxOpenConfigStorage getWxOpenConfigStorage();

  boolean checkSignature(String timestamp, String nonce, String signature);

  String getComponentAccessToken(boolean forceRefresh) throws WxErrorException;

  /**
   * 获取用户授权页URL（来路URL和成功跳转URL 的域名都需要为三方平台设置的 登录授权的发起页域名）
   */
  String getPreAuthUrl(String redirectURI) throws WxErrorException;
  /**
   * authType 要授权的帐号类型：1则商户点击链接后，手机端仅展示公众号、2表示仅展示小程序，3表示公众号和小程序都展示。如果为未指定，则默认小程序和公众号都展示。第三方平台开发者可以使用本字段来控制授权的帐号类型。
   * bizAppid 指定授权唯一的小程序或公众号
   * 注：auth_type、biz_appid两个字段互斥。
  */
  String getPreAuthUrl(String redirectURI,String authType, String bizAppid) throws WxErrorException;

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
  void setAuthorizerOption(String authorizerAppid, String optionName, String optionValue) throws WxErrorException;

  String getAuthorizerAccessToken(String appid, boolean forceRefresh) throws WxErrorException;

  WxMpOAuth2AccessToken oauth2getAccessToken(String appid, String code) throws WxErrorException;

  boolean checkSignature(String appId, String timestamp, String nonce, String signature);

  WxMpOAuth2AccessToken oauth2refreshAccessToken(String appid, String refreshToken) throws WxErrorException;

  String oauth2buildAuthorizationUrl(String appid, String redirectURI, String scope, String state);

  WxMaJscode2SessionResult miniappJscode2Session(String appId, String jsCode) throws WxErrorException;

  /**
   * 代小程序实现业务
   * <p>
   * 小程序代码模版库管理：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1506504150_nMMh6&token=&lang=zh_CN
   * access_token 为 component_access_token
   */
  String GET_TEMPLATE_DRAFT_LIST_URL = "https://api.weixin.qq.com/wxa/gettemplatedraftlist";
  String GET_TEMPLATE_LIST_URL = "https://api.weixin.qq.com/wxa/gettemplatelist";
  String ADD_TO_TEMPLATE_URL = "https://api.weixin.qq.com/wxa/addtotemplate";
  String DELETE_TEMPLATE_URL = "https://api.weixin.qq.com/wxa/deletetemplate";

  /**
   * 获取草稿箱内的所有临时代码草稿
   *
   * @return 草稿箱代码模板列表（draftId）
   * @throws WxErrorException 获取失败时返回，具体错误码请看此接口的注释文档
   */
  List<WxOpenMaCodeTemplate> getTemplateDraftList() throws WxErrorException;

  /**
   * 获取代码模版库中的所有小程序代码模版
   *
   * @return 小程序代码模版列表（templateId）
   * @throws WxErrorException 获取失败时返回，具体错误码请看此接口的注释文档
   */
  List<WxOpenMaCodeTemplate> getTemplateList() throws WxErrorException;

  /**
   * 将草稿箱的草稿选为小程序代码模版
   *
   * @param draftId 草稿ID，本字段可通过“获取草稿箱内的所有临时代码草稿”接口获得
   * @throws WxErrorException 操作失败时抛出，具体错误码请看此接口的注释文档
   * @see #getTemplateDraftList
   */
  void addToTemplate(long draftId) throws WxErrorException;

  /**
   * 删除指定小程序代码模版
   *
   * @param templateId 要删除的模版ID
   * @throws WxErrorException 操作失败时抛出，具体错误码请看此接口的注释文档
   * @see #getTemplateList
   */
  void deleteTemplate(long templateId) throws WxErrorException;
}
