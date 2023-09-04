package me.chanjar.weixin.open.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.open.bean.mp.FastRegisterResult;
import me.chanjar.weixin.open.bean.result.WxAmpLinkResult;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

/**
 * <pre>
 *     微信开放平台代公众号实现服务能力
 *     https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1489144594_DhNoV&token=&lang=zh_CN
 * </pre>
 * <p>
 * Created by zpf on 2020/10/15
 */
public interface WxOpenMpService extends WxMpService {

  /**
   * 取复用公众号快速注册小程序的授权链接.
   */
  String URL_FAST_REGISTER_AUTH = "https://mp.weixin.qq.com/cgi-bin/fastregisterauth?appid=%s&component_appid=%s&copy_wx_verify=%s&redirect_uri=%s";

  /**
   * 复用公众号快速注册小程序
   */
  String API_FAST_REGISTER = "https://api.weixin.qq.com/cgi-bin/account/fastregister";

  /**
   * 小程序管理-获取公众号关联的小程序
   */
  String API_WX_AMP_LINK_GET = "https://api.weixin.qq.com/cgi-bin/wxopen/wxamplinkget";
  /**
   * 小程序管理-关联小程序
   */
  String API_WX_AMP_LINK_CREATE = "https://api.weixin.qq.com/cgi-bin/wxopen/wxamplink";
  /**
   * 小程序管理-解除已关联的小程序
   */
  String API_WX_AMP_LINK_UN = "https://api.weixin.qq.com/cgi-bin/wxopen/wxampunlink";

  /**
   * 取复用公众号快速注册小程序的授权链接
   * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/Official_Accounts/fast_registration_of_mini_program.html
   *
   * @param redirectUri  用户扫码授权后，MP 扫码页面将跳转到该地址(注:1.链接需 urlencode 2.Host 需和第三方平台在微信开放平台上面填写的登 录授权的发起页域名一致)
   * @param copyWxVerify 是否复用公众号的资质进行微信认证,可空,默认false
   * @return 返回授权链接 ，注意：由于微信开放平台限制，此链接直接使用后端301重定向微信会报错，必须是在第三方平台所在域名的页面的html或js触发跳转才能成功
   */
  String getFastRegisterAuthUrl(String redirectUri, Boolean copyWxVerify);

  /**
   * 复用公众号快速注册小程序
   * 注意：调用本接口的第三方平台必须是已经全网发布的，否则微信会报-1服务器繁忙错误，然后再报ticket无效错误，并且接口的使用次数会增加，同时还会生成一个废小程序
   * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/Official_Accounts/fast_registration_of_mini_program.html
   *
   * @param ticket 公众号扫码授权的凭证(公众平台扫码页面回跳到第三方平台时携带)
   * @return 返回授权码, 然后请使用第三方平台的sdk获得授权, 参考: WxOpenService.getWxOpenComponentService().getQueryAuth( fastRegisterResult.getAuthorizationCode() );
   * @throws WxErrorException the wx error exception
   */
  FastRegisterResult fastRegister(String ticket) throws WxErrorException;

  /**
   * <pre>
   * 获取公众号关联的小程序
   * 请求方式：POST(HTTPS)
   * 请求地址：
   * <a href="https://api.weixin.qq.com/cgi-bin/wxopen/wxamplinkget?access_token=TOKEN">https://api.weixin.qq.com/cgi-bin/wxopen/wxamplinkget?access_token=TOKEN</a>
   * 文档地址：
   * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/Official__Accounts/Mini_Program_Management_Permission.html">https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/Official__Accounts/Mini_Program_Management_Permission.html</a>
   * <pre>
   * @return 公众号关联的小程序
   */
  WxAmpLinkResult getWxAmpLink() throws WxErrorException;

  /**
   * <pre>
   * 关联小程序
   * 关联流程（需要公众号和小程序管理员双方确认）：
   * 1、第三方平台调用接口发起关联
   * 2、公众号管理员收到模板消息，同意关联小程序。
   * 3、小程序管理员收到模板消息，同意关联公众号。
   * 4、关联成功
   * 等待管理员同意的中间状态可使用“获取公众号关联的小程序”接口进行查询。
   * 请求方式：POST(HTTPS)
   * 请求地址：
   * <a href="https://api.weixin.qq.com/cgi-bin/wxopen/wxamplink?access_token=TOKEN">https://api.weixin.qq.com/cgi-bin/wxopen/wxamplink?access_token=TOKEN</a>
   * 文档地址：
   * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/Official__Accounts/Mini_Program_Management_Permission.html">https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/Official__Accounts/Mini_Program_Management_Permission.html</a>
   * <pre>
   * @param appid 小程序 appid
   * @param notifyUsers 是否发送模板消息通知公众号粉丝
   * @param showProfile 是否展示公众号主页中
   * @return 响应结果
   */
  WxOpenResult wxAmpLink(String appid, String notifyUsers, String showProfile) throws WxErrorException;

  /**
   * <pre>
   * 解除已关联的小程序
   * 请求方式：POST(HTTPS)
   * 请求地址：
   * <a href="https://api.weixin.qq.com/cgi-bin/wxopen/wxampunlink?access_token=TOKEN">https://api.weixin.qq.com/cgi-bin/wxopen/wxampunlink?access_token=TOKEN</a>
   * 文档地址：
   * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/Official__Accounts/Mini_Program_Management_Permission.html">https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/Official__Accounts/Mini_Program_Management_Permission.html</a>
   * <pre>
   * @param appid 小程序 appid
   * @return 响应结果
   */
  WxOpenResult wxAmpUnLink(String appid) throws WxErrorException;
}
