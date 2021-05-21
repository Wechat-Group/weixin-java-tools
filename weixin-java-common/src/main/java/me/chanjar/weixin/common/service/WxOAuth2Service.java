package me.chanjar.weixin.common.service;

import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * oauth2 相关接口.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-08-08
 */
public interface WxOAuth2Service {
  /**
   * <pre>
   * 构造oauth2授权的url连接.
   * 详情请见: https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/Wechat_webpage_authorization.html
   * </pre>
   *
   * @param redirectUri 用户授权完成后的重定向链接，无需urlencode, 方法内会进行encode
   * @param scope       scope,静默:snsapi_base, 带信息授权:snsapi_userinfo
   * @param state       state
   * @return url
   */
  String buildAuthorizationUrl(String redirectUri, String scope, String state);

  /**
   * <pre>
   * 用code换取oauth2的access token.
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=网页授权获取用户基本信息
   * </pre>
   *
   * @param code code
   * @return token对象
   * @throws WxErrorException .
   */
  WxOAuth2AccessToken getAccessToken(String code) throws WxErrorException;

  /**
   * 用code换取oauth2的access token.
   *
   * @param appId     the appid
   * @param appSecret the secret
   * @param code      code
   * @return token对象
   * @throws WxErrorException .
   */
  WxOAuth2AccessToken getAccessToken(String appId, String appSecret, String code) throws WxErrorException;

  /**
   * <pre>
   * 刷新oauth2的access token.
   * </pre>
   *
   * @param refreshToken 刷新token
   * @return 新的token对象
   * @throws WxErrorException .
   */
  WxOAuth2AccessToken refreshAccessToken(String refreshToken) throws WxErrorException;

  /**
   * <pre>
   * 用oauth2获取用户信息, 当前面引导授权时的scope是snsapi_userinfo的时候才可以.
   * </pre>
   *
   * @param oAuth2AccessToken token对象
   * @param lang              zh_CN, zh_TW, en
   * @return 用户对象
   * @throws WxErrorException .
   */
  WxOAuth2UserInfo getUserInfo(WxOAuth2AccessToken oAuth2AccessToken, String lang) throws WxErrorException;

  /**
   * <pre>
   * 验证oauth2的access token是否有效.
   * </pre>
   *
   * @param oAuth2AccessToken token对象
   * @return 是否有效
   */
  boolean validateAccessToken(WxOAuth2AccessToken oAuth2AccessToken);

}
