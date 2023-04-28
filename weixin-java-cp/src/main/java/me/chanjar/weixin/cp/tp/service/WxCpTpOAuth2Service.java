package me.chanjar.weixin.cp.tp.service;

/**
 * <pre>
 *   构造第三方应用oauth2链接
 *   Created by feidian108 on 2023/3/24.
 * </pre>
 * <p>
 * <a href="https://developer.work.weixin.qq.com/document/path/91120">企业微信服务商文档</a>
 */
public interface WxCpTpOAuth2Service {

  /**
   * <pre>
   *   构造第三方应用oauth2链接(静默授权)
   * </pre>
   * @param redirectUri  授权后重定向的回调链接地址
   * @param state        重定向后state参数
   * @return             url string
   */
  String buildAuthorizeUrl(String redirectUri, String state);


  /**
   * <pre>
   *   构造第三方应用oauth2链接
   * </pre>
   * @param redirectUri   授权后重定向的回调链接地址
   * @param state         重定向后state参数
   * @param scope         应用授权作用域,snsapi_base：静默授权,snsapi_privateinfo：手动授权
   * @return              url string
   */
  String buildAuthorizeUrl(String redirectUri, String state, String scope);
}
