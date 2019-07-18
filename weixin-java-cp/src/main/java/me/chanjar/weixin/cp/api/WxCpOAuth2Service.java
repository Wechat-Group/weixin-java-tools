package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpUserDetail;

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
   * 构造oauth2授权的url连接
   * 详情请见: http://qydev.weixin.qq.com/wiki/index.php?title=企业获取code
   * </pre>
   *
   * @param redirectUri 跳转链接地址
   * @param state       状态码
   * @param scope       取值参考me.chanjar.weixin.common.api.WxConsts.OAuth2Scope类
   * @return url
   */
  String buildAuthorizationUrl(String redirectUri, String state, String scope);

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
   * 根据code获取成员信息
   * http://qydev.weixin.qq.com/wiki/index.php?title=根据code获取成员信息
   * https://work.weixin.qq.com/api/doc#10028/根据code获取成员信息
   * 因为企业号oauth2.0必须在应用设置里设置通过ICP备案的可信域名，所以无法测试，因此这个方法很可能是坏的。
   *
   * 注意: 这个方法不使用WxCpConfigStorage里的agentId，需要开发人员自己给出
   * </pre>
   *
   * @param agentId 企业号应用的id
   * @param code    通过成员授权获取到的code，最大为512字节。每次成员授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
   * @return [UserId, DeviceId, OpenId, user_ticket, expires_in]
   * @see #getUserInfo(String)
   */
  String[] getUserInfo(Integer agentId, String code) throws WxErrorException;

  /**
   * <pre>
   * 使用user_ticket获取成员详情.
   *
   * 文档地址：https://work.weixin.qq.com/api/doc#10028/%E4%BD%BF%E7%94%A8user_ticket%E8%8E%B7%E5%8F%96%E6%88%90%E5%91%98%E8%AF%A6%E6%83%85
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail?access_token=ACCESS_TOKEN
   *
   * 权限说明：
   * 需要有对应应用的使用权限，且成员必须在授权应用的可见范围内。
   * </pre>
   *
   * @param userTicket 成员票据
   */
  WxCpUserDetail getUserDetail(String userTicket) throws WxErrorException;
}
