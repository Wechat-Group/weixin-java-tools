package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * <pre>
 * OAuth2相关管理接口
 *  Created by BinaryWang on 2017/6/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxCpOAuth2Service {
  /**
   * <pre>
   * 构造oauth2授权的url连接
   * </pre>
   *
   * @param state 状态码
   * @return url
   */
  String buildAuthorizationUrl(String state);

  /**
   * <pre>
   * 构造oauth2授权的url连接
   * 详情请见: http://qydev.weixin.qq.com/wiki/index.php?title=企业获取code
   * </pre>
   *
   * @param redirectUri 跳转链接地址
   * @param state       状态码
   * @return url
   */
  String buildAuthorizationUrl(String redirectUri, String state);

  /**
   * <pre>
   * 用oauth2获取用户信息
   * http://qydev.weixin.qq.com/wiki/index.php?title=根据code获取成员信息
   * 因为企业号oauth2.0必须在应用设置里设置通过ICP备案的可信域名，所以无法测试，因此这个方法很可能是坏的。
   *
   * 注意: 这个方法使用WxCpConfigStorage里的agentId
   * </pre>
   *
   * @param code 微信oauth授权返回的代码
   * @return [userid, deviceid]
   * @see #getUserInfo(Integer, String)
   */
  String[] getUserInfo(String code) throws WxErrorException;

  /**
   * <pre>
   * 用oauth2获取用户信息
   * http://qydev.weixin.qq.com/wiki/index.php?title=根据code获取成员信息
   * 因为企业号oauth2.0必须在应用设置里设置通过ICP备案的可信域名，所以无法测试，因此这个方法很可能是坏的。
   *
   * 注意: 这个方法不使用WxCpConfigStorage里的agentId，需要开发人员自己给出
   * </pre>
   *
   * @param agentId 企业号应用的id
   * @param code    微信oauth授权返回的代码
   * @return [userid, deviceid]
   * @see #getUserInfo(String)
   */
  String[] getUserInfo(Integer agentId, String code) throws WxErrorException;

}
