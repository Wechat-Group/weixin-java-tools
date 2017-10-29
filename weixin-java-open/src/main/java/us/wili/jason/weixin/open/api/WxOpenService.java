package us.wili.jason.weixin.open.api;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.mp.api.*;
import me.chanjar.weixin.mp.bean.*;
import me.chanjar.weixin.mp.bean.result.*;
import org.apache.http.HttpHost;
import us.wili.jason.weixin.open.bean.resp.AuthorizerInfoResp;
import us.wili.jason.weixin.open.bean.resp.QueryAuthResp;

/**
 * Created by JasonY on 17/10/23.
 */
public interface WxOpenService {

  String COMPONENT_API_COMPONENT_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";

  String COMPONENT_API_CREATE_PREAUTHCODE_URL = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode";

  String COMPONENT_API_QUERY_AUTH_URL = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth";

  String COMPONENT_API_AUTHORIZER_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token";

  String COMPONENT_API_AUTHORIZER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info";

  String COMPONENT_CONNECT_OAUTH2_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&component_appid=%s#wechat_redirect";

  String COMPONENT_CONNECT_OAUTH2_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=%s&code=%s&grant_type=authorization_code&component_appid=%s&component_access_token=%s";

  String COMPONENT_CONNECT_OAUTH2_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/component/refresh_token?appid=%s&grant_type=refresh_token&component_appid=%s&component_access_token=%s&refresh_token=%s";

  String COMPONENT_LOGIN_PAGE_URL = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=%s&pre_auth_code=%s&redirect_uri=%s";

  void setComponentVerifyTicket(String componentVerifyTicket);
  String getComponentVerifyTicket();

  /**
   * component_access_token, component_access_token
   *
   * @see #getComponentAccessToken(boolean)
   */
  String getComponentAccessToken() throws WxErrorException;

  /**
   * <pre>
   * 获取component_access_token，本方法线程安全
   * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
   *
   * 另：本service的所有方法都会在access_token过期是调用此方法
   *
   * 程序员在非必要情况下尽量不要主动调用此方法
   *
   * 详情请见: https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1453779503&token=&lang=zh_CN
   * </pre>
   *
   * @param forceRefresh 强制刷新
   */
  String getComponentAccessToken(boolean forceRefresh) throws WxErrorException;

  String getPreAuthCode() throws WxErrorException;
  String getPreAuthCode(boolean forceRefresh) throws WxErrorException;


  /**
   * authorizer_access_token
   *
   * @see #getComponentAccessToken(boolean)
   */
  String getAuthorizerAccessToken(String appid) throws WxErrorException;

  /**
   * <pre>
   * 获取authorizer_access_token，本方法线程安全
   * 且在多线程同时刷新时只刷新一次，避免超出调用次数上限
   *
   * 另：本service的所有方法都会在authorizer_access_token过期是调用此方法
   *
   * 程序员在非必要情况下尽量不要主动调用此方法
   *
   * 详情请见: https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1453779503&token=&lang=zh_CN
   * </pre>
   *
   * @param forceRefresh 强制刷新
   */
  String getAuthorizerAccessToken(String appid, boolean forceRefresh) throws WxErrorException;

  /**
   * 获取刷新授权公众号或小程序的接口调用凭据
   * @param appid
   * @return
   */
  String getAuthorizerRefreshToken(String appid);

  /**
   * 获得jsapi_ticket,不强制刷新jsapi_ticket
   *
   * @see #getJsapiTicket(String, boolean)
   */
  String getJsapiTicket(String appid) throws WxErrorException;

  /**
   * <pre>
   * 获得jsapi_ticket
   * 获得时会检查jsapiToken是否过期，如果过期了，那么就刷新一下，否则就什么都不干
   *
   * 详情请见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115&token=&lang=zh_CN
   * </pre>
   *
   * @param forceRefresh 强制刷新
   */
  String getJsapiTicket(String appid, boolean forceRefresh) throws WxErrorException;

  /**
   * <pre>
   * 创建调用jsapi时所需要的签名
   *
   * 详情请见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115&token=&lang=zh_CN
   * </pre>
   */
  WxJsapiSignature createJsapiSignature(String appid, String url) throws WxErrorException;

  /**
   * <pre>
   * 长链接转短链接接口
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=长链接转短链接接口
   * </pre>
   */
  String shortUrl(String appid, String long_url) throws WxErrorException;

  String buildComponentLoginPageUrl(String redirectURI);
  QueryAuthResp queryAuth(String authCode) throws WxErrorException;
  AuthorizerInfoResp getAuthorizerInfo(String authorizerAppid) throws WxErrorException;

  /**
   * <pre>
   * 构造oauth2授权的url连接
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=网页授权获取用户基本信息
   * </pre>
   *
   * @param redirectURI 用户授权完成后的重定向链接，无需urlencode, 方法内会进行encode
   * @return url
   */
  String oauth2buildAuthorizationUrl(String appid, String redirectURI, String scope, String state);

  /**
   * <pre>
   * 用code换取oauth2的access token
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=网页授权获取用户基本信息
   * </pre>
   */
  WxMpOAuth2AccessToken oauth2getAccessToken(String appid, String code) throws WxErrorException;

  /**
   * <pre>
   * 刷新oauth2的access token
   * </pre>
   */
  WxMpOAuth2AccessToken oauth2refreshAccessToken(String appid, String refreshToken) throws WxErrorException;

  /**
   * <pre>
   * 用oauth2获取用户信息, 当前面引导授权时的scope是snsapi_userinfo的时候才可以
   * </pre>
   *
   * @param lang zh_CN, zh_TW, en
   */
  WxMpUser oauth2getUserInfo(WxMpOAuth2AccessToken oAuth2AccessToken, String lang) throws WxErrorException;

  /**
   * <pre>
   * 验证oauth2的access token是否有效
   * </pre>
   */
  boolean oauth2validateAccessToken(WxMpOAuth2AccessToken oAuth2AccessToken);

  /**
   * <pre>
   * 获取公众号的自动回复规则
   * http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751299&token=&lang=zh_CN
   * 开发者可以通过该接口，获取公众号当前使用的自动回复规则，包括关注后自动回复、消息自动回复（60分钟内触发一次）、关键词自动回复。
   * 请注意：
   * 1、第三方平台开发者可以通过本接口，在旗下公众号将业务授权给你后，立即通过本接口检测公众号的自动回复配置，并通过接口再次给公众号设置好自动回复规则，以提升公众号运营者的业务体验。
   * 2、本接口仅能获取公众号在公众平台官网的自动回复功能中设置的自动回复规则，若公众号自行开发实现自动回复，或通过第三方平台开发者来实现，则无法获取。
   * 3、认证/未认证的服务号/订阅号，以及接口测试号，均拥有该接口权限。
   * 4、从第三方平台的公众号登录授权机制上来说，该接口从属于消息与菜单权限集。
   * 5、本接口中返回的图片/语音/视频为临时素材（临时素材每次获取都不同，3天内有效，通过素材管理-获取临时素材接口来获取这些素材），本接口返回的图文消息为永久素材素材（通过素材管理-获取永久素材接口来获取这些素材）。
   * 接口调用请求说明
   * http请求方式: GET（请使用https协议）
   * https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info?access_token=ACCESS_TOKEN
   * </pre>
   */
  WxMpCurrentAutoReplyInfo getCurrentAutoReplyInfo(String appid) throws WxErrorException;

  /**
   * 主要用于调用 微信开放平台第三方应用 接口
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信开放平台第三方应用API中的GET请求
   */
  String get(String url, String queryParam) throws WxErrorException;

  /**
   * 主要用于调用 微信开放平台第三方应用 接口
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信开放平台第三方应用API中的POST请求
   */
  String post(String url, String postData) throws WxErrorException;

  /**
   * <pre>
   * 主要用于调用 微信开放平台接口，Service没有实现某个API的时候，可以用这个，
   * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
   * 可以参考，{@link me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor}的实现方法
   * </pre>
   */
  <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException;

  /**
   * 主要用于 第三方平台代公众号实现接口调用
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的GET请求
   */
  String get(String appid, String url, String queryParam) throws WxErrorException;

  /**
   * 主要用于 第三方平台代公众号实现接口调用
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求
   */
  String post(String appid, String url, String postData) throws WxErrorException;

  /**
   * <pre>
   * Service没有实现某个API的时候，可以用这个，
   * 主要用于 第三方平台代公众号实现接口调用
   * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
   * 可以参考，{@link me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor}的实现方法
   * </pre>
   */
  <T, E> T execute(String appid, RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException;

  /**
   * 初始化http请求对象
   */
  void initHttp();

  /**
   * @return RequestHttp对象
   */
  RequestHttp getRequestHttp();


  /**
   * 注入 {@link WxMpConfigStorage} 的实现
   */
  void setWxOpenConfigStorage(us.wili.jason.weixin.open.api.WxOpenConfigStorage wxOpenConfigStorage);

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试
   * 默认：1000ms
   * </pre>
   */
  void setRetrySleepMillis(int retrySleepMillis);

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，最大重试次数
   * 默认：5次
   * </pre>
   */
  void setMaxRetryTimes(int maxRetryTimes);

  /**
   * 获取WxOpenConfigStorage 对象
   *
   * @return WxOpenConfigStorage
   */
  WxOpenConfigStorage getWxOpenConfigStorage();

  /**
   * 返回客服接口方法实现类，以方便调用其各个接口
   *
   * @return WxOpenKefuService
   */
  WxOpenKefuService getKefuService();

  /**
   * 返回素材相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpenMaterialService
   */
  WxOpenMaterialService getMaterialService();

  /**
   * 返回菜单相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpenMenuService
   */
  WxOpenMenuService getMenuService();

  /**
   * 返回用户相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpenUserService
   */
  WxOpenUserService getUserService();

  /**
   * 返回用户标签相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpenUserTagService
   */
  WxOpenUserTagService getUserTagService();

  /**
   * 返回二维码相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpenQrcodeService
   */
  WxOpenQrcodeService getQrcodeService();

  /**
   * 返回卡券相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpenCardService
   */
  WxOpenCardService getCardService();

  /**
   * 返回数据分析统计相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpenDataCubeService
   */
  WxOpenDataCubeService getDataCubeService();

  /**
   * 返回用户黑名单管理相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpenUserBlacklistService
   */
  WxOpenUserBlacklistService getBlackListService();

  /**
   * 返回门店管理相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpenStoreService
   */
  WxOpenStoreService getStoreService();

  /**
   * 返回模板消息相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpenTemplateMsgService
   */
  WxOpenTemplateMsgService getTemplateMsgService();

  /**
   * 返回硬件平台相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpenDeviceService
   */
  WxOpenDeviceService getDeviceService();

  /**
   * 返回摇一摇周边相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpenShakeService
   */
  WxOpenShakeService getShakeService();

  /**
   * 返回会员卡相关接口方法的实现类对象，以方便调用其各个接口
   *
   * @return WxOpenMemberCardService
   */
  WxOpenMemberCardService getMemberCardService();

  /**
   * 返回群发消息相关接口方法的实现类对象，以方便调用其各个接口
   * @return WxOpenMassMessageService
   */
  WxOpenMassMessageService getMassMessageService();

  void setKefuService(WxOpenKefuService kefuService);

  void setMaterialService(WxOpenMaterialService materialService);

  void setMenuService(WxOpenMenuService menuService);

  void setUserService(WxOpenUserService userService);

  void setTagService(WxOpenUserTagService tagService);

  void setQrCodeService(WxOpenQrcodeService qrCodeService);

  void setCardService(WxOpenCardService cardService);

  void setStoreService(WxOpenStoreService storeService);

  void setDataCubeService(WxOpenDataCubeService dataCubeService);

  void setBlackListService(WxOpenUserBlacklistService blackListService);

  void setTemplateMsgService(WxOpenTemplateMsgService templateMsgService);

  void setDeviceService(WxOpenDeviceService deviceService);

  void setShakeService(WxOpenShakeService shakeService);

  void setMemberCardService(WxOpenMemberCardService memberCardService);

  void setMassMessageService(WxOpenMassMessageService massMessageService);
}
