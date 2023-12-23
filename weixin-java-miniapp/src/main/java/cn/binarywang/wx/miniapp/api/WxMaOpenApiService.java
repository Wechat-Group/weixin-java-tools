package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.openapi.WxMiniGetApiQuotaResult;
import cn.binarywang.wx.miniapp.bean.openapi.WxMiniGetRidInfoResult;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * openApi管理
 *
 * @author shuiyihan12
 * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/openApi-mgnt/clearQuota.html">openApi管理 微信文档</a>
 * @since 2023/7/7 17:07
 */
public interface WxMaOpenApiService {

  /**
   * 本接口用于清空公众号/小程序/第三方平台等接口的每日调用接口次数
   *
   * @return 是否成功
   * @throws WxErrorException the wx error exception
   * @apiNote !!! 单小程序直接调用该方法 , 如多个appid调用此方法前请调用 {@link WxMaService#switchoverTo} 切换appid !!!
   * @code wxMaService.getWxMaOpenApiService().clearQuota() //单个
   * @code wxMaService.switchoverTo(" appid ").getWxMaOpenApiService().clearQuota() //多个
   * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/openApi-mgnt/clearQuota.html">注意事项参考微信文档</a>
   */
  boolean clearQuota() throws WxErrorException;

  /**
   * 查询API调用额度
   *
   * @param cgiPath api的请求地址，
   *                例如"/cgi-bin/message/custom/send";不要前缀“https://api.weixin.qq.com” ，也不要漏了"/",否则都会76003的报错;
   * @return 额度详情
   * @throws WxErrorException 微信异常
   * @apiNote "/xxx/sns/xxx" 这类接口不支持使用该接口，会出现76022报错。
   * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/openApi-mgnt/getApiQuota.html">注意事项参考微信文档</a>
   */
  WxMiniGetApiQuotaResult getApiQuota(String cgiPath) throws WxErrorException;

  /**
   * 查询rid信息
   *
   * @param rid 调用接口报错返回的rid
   * @return 该rid对应的请求详情
   * @throws WxErrorException 微信异常
   * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/openApi-mgnt/getRidInfo.html">注意事项参考微信文档</a>
   */
  WxMiniGetRidInfoResult getRidInfo(String rid) throws WxErrorException;


  /**
   * 使用AppSecret重置 API 调用次数
   *
   * @return 是否成功
   * @throws WxErrorException 微信异常
   * @apiNote !!! 单小程序直接调用该方法 , 如多个appid调用此方法前请调用 {@link WxMaService#switchoverTo} 切换appid!!!
   * 参考示例
   * @code wxMaService.getWxMaOpenApiService().clearQuotaByAppSecret() //单个
   * @code wxMaService.switchoverTo(" appid ").getWxMaOpenApiService().clearQuotaByAppSecret() //多个
   * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/openApi-mgnt/clearQuotaByAppSecret.html">注意事项参考微信文档</a>
   */
  boolean clearQuotaByAppSecret() throws WxErrorException;
}
