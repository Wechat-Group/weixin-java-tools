package cn.binarywang.wx.miniapp.api;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * <pre>
 *  jsapi相关接口
 *  Created by BinaryWang on 2018/8/5.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaJsapiService {
  /**
   * 获得卡券api_ticket,不强制刷新api_ticket
   *
   * @return the card api ticket
   * @throws WxErrorException the wx error exception
   * @see #getJsapiTicket(boolean) #getJsapiTicket(boolean)
   */
  String getCardApiTicket() throws WxErrorException;

  /**
   * <pre>
   * 获得卡券api_ticket
   * 获得时会检查apiToken是否过期，如果过期了，那么就刷新一下，否则就什么都不干
   *
   * 详情请见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115&token=&lang=zh_CN
   * </pre>
   *
   * @param forceRefresh 强制刷新
   * @return the card api ticket
   * @throws WxErrorException the wx error exception
   */
  String getCardApiTicket(boolean forceRefresh) throws WxErrorException;

  /**
   * 获得jsapi_ticket,不强制刷新jsapi_ticket
   *
   * @return the jsapi ticket
   * @throws WxErrorException the wx error exception
   * @see #getJsapiTicket(boolean) #getJsapiTicket(boolean)
   */
  String getJsapiTicket() throws WxErrorException;

  /**
   * <pre>
   * 获得jsapi_ticket
   * 获得时会检查jsapiToken是否过期，如果过期了，那么就刷新一下，否则就什么都不干
   *
   * 详情请见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115&token=&lang=zh_CN
   * </pre>
   *
   * @param forceRefresh 强制刷新
   * @return the jsapi ticket
   * @throws WxErrorException the wx error exception
   */
  String getJsapiTicket(boolean forceRefresh) throws WxErrorException;

  /**
   * <pre>
   * 创建调用jsapi时所需要的签名
   *
   * 详情请见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115&token=&lang=zh_CN
   * </pre>
   *
   * @param url the url
   * @return the wx jsapi signature
   * @throws WxErrorException the wx error exception
   */
  WxJsapiSignature createJsapiSignature(String url) throws WxErrorException;

}
