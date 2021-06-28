package me.chanjar.weixin.open.api;

import cn.binarywang.wx.miniapp.api.WxMaService;

/**
 * <pre>
 *     微信开放平台【快速创建小程序】的专用接口
 *     https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=21528465979XX32V&token=&lang=zh_CN
 *    注意：该类的接口仅限通过快速创建小程序接口的小程序使用
 * </pre>
 *
 * @author Hipple
 * @date 2019/01/23
 * @deprecated 2021-06-23 本接口原有方法并非仅快速创建小程序的专用接口，普通小程序授权到第三方平台皆可使用，所以请使用 {@link WxOpenMaBasicService} 类替代。获取方法: WxOpenComponentService.getWxMaServiceByAppid(maApppId).getBasicService()
 */
@Deprecated
public interface WxOpenFastMaService extends WxOpenMaBasicService, WxMaService {

}
