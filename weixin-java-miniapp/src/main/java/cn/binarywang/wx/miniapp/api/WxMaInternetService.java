package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.internet.WxMaInternetResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * <pre>
 * 【小程序-服务端-网络】网络相关接口.
 *  文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/internet/internet.getUserEncryptKey.html
 * </pre>
 *
 * @author <a href="https://github.com/chutian0124">chutian0124</a>
 */
public interface WxMaInternetService {
  /**
   * <pre>
   * 获取用户encryptKey。 会获取用户最近3次的key，每个key的存活时间为3600s。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/internet/internet.getUserEncryptKey.html
   * 接口地址：POST https://api.weixin.qq.com/wxa/business/getuserencryptkey?access_token=ACCESS_TOKEN&openid=OPENID&signature=SIGNATURE&sig_method=hmac_sha256
   * @param openid 用户的openid
   * @param signature 用sessionkey对空字符串签名得到的结果
   * @param sigMethod 签名方法，只支持 hmac_sha256
   * </pre>
   *
   * @return {@link WxMaInternetResponse}
   * @throws WxErrorException
   * @apiNote 推荐使用  {@link #getUserEncryptKey(java.lang.String, java.lang.String)}
   */
  @Deprecated
  WxMaInternetResponse getUserEncryptKey(String openid, String signature, String sigMethod) throws WxErrorException;

  /**
   * <pre>
   * 获取用户encryptKey。
   * @implNote 会获取用户最近3次的key，每个key的存活时间为3600s。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/internet/internet.getUserEncryptKey.html
   * 接口地址：POST https://api.weixin.qq.com/wxa/business/getuserencryptkey?access_token=ACCESS_TOKEN&openid=OPENID&signature=SIGNATURE&sig_method=hmac_sha256
   * @param openid 用户的openid
   * @param sessionKey 用户的sessionKey
   * </pre>
   *
   * @return {@link WxMaInternetResponse}
   * @throws WxErrorException
   */
  WxMaInternetResponse getUserEncryptKey(String openid, String sessionKey) throws WxErrorException;
}
