package me.chanjar.weixin.open.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.open.bean.mp.FastRegisterResult;

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
   * 取复用公众号快速注册小程序的授权链接
   * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/Official_Accounts/fast_registration_of_mini_program.html
   *
   * @param redirectUri  用户扫码授权后，MP 扫码页面将跳转到该地址(注:1.链接需 urlencode 2.Host 需和第三方平台在微信开放平台上面填写的登 录授权的发起页域名一致)
   * @param copyWxVerify 是否复用公众号的资质进行微信认证,可空,默认false
   * @return 返回授权链接，注意：由于微信开放平台限制，此链接直接使用后端301重定向微信会报错，必须是在第三方平台所在域名的页面的html或js触发跳转才能成功
   */
  String getFastRegisterAuthUrl(String redirectUri, Boolean copyWxVerify);

  /**
   * 复用公众号快速注册小程序
   * 注意：调用本接口的第三方平台必须是已经全网发布的，否则微信会报-1服务器繁忙错误，然后再报ticket无效错误，并且接口的使用次数会增加，同时还会生成一个废小程序
   * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/Official_Accounts/fast_registration_of_mini_program.html
   *
   * @param ticket 公众号扫码授权的凭证(公众平台扫码页面回跳到第三方平台时携带)
   * @return 返回授权码, 然后请使用第三方平台的sdk获得授权, 参考: WxOpenService.getWxOpenComponentService().getQueryAuth( fastRegisterResult.getAuthorizationCode() );
   */
  FastRegisterResult fastRegister(String ticket) throws WxErrorException;
}
