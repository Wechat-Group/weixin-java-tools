package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxService;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaService extends WxService {
  /**
   * 获取access_token.
   */
  String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

  String JSCODE_TO_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";
  /**
   * getPaidUnionId
   */
  String GET_PAID_UNION_ID_URL = "https://api.weixin.qq.com/wxa/getpaidunionid";

  /**
   * 导入抽样数据
   */
  String SET_DYNAMIC_DATA_URL = "https://api.weixin.qq.com/wxa/setdynamicdata";

  /**
   * 获取登录后的session信息.
   *
   * @param jsCode 登录时获取的 code
   */
  WxMaJscode2SessionResult jsCode2SessionInfo(String jsCode) throws WxErrorException;

  /**
   * 导入抽样数据
   * <pre>
   * 第三方通过调用微信API，将数据写入到setdynamicdata这个API。每个Post数据包不超过5K，若数据过多可开多进（线）程并发导入数据（例如：数据量为十万量级可以开50个线程并行导数据）。
   * 文档地址：https://wsad.weixin.qq.com/wsad/zh_CN/htmledition/widget-docs-v3/html/custom/quickstart/implement/import/index.html
   * http请求方式：POST http(s)://api.weixin.qq.com/wxa/setdynamicdata?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param data     推送到微信后台的数据列表，该数据被微信用于流量分配，注意该字段为string类型而不是object
   * @param lifespan 数据有效时间，秒为单位，一般为86400，一天一次导入的频率
   * @param scene    1代表用于搜索的数据
   * @param type     用于标识数据所属的服务类目
   * @throws WxErrorException .
   */
  void setDynamicData(int lifespan, String type, int scene, String data) throws WxErrorException;

  /**
   * <pre>
   * 验证消息的确来自微信服务器.
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319&token=&lang=zh_CN
   * </pre>
   */
  boolean checkSignature(String timestamp, String nonce, String signature);

  /**
   * 获取access_token, 不强制刷新access_token.
   *
   * @see #getAccessToken(boolean)
   */
  String getAccessToken() throws WxErrorException;

  /**
   * <pre>
   * 获取access_token，本方法线程安全.
   * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
   *
   * 另：本service的所有方法都会在access_token过期是调用此方法
   *
   * 程序员在非必要情况下尽量不要主动调用此方法
   *
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183&token=&lang=zh_CN
   * </pre>
   *
   * @param forceRefresh 强制刷新
   */
  String getAccessToken(boolean forceRefresh) throws WxErrorException;

  /**
   * <pre>
   * 用户支付完成后，获取该用户的 UnionId，无需用户授权。本接口支持第三方平台代理查询。
   *
   * 注意：调用前需要用户完成支付，且在支付后的五分钟内有效。
   * 请求地址： GET https://api.weixin.qq.com/wxa/getpaidunionid?access_token=ACCESS_TOKEN&openid=OPENID
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api/getPaidUnionId.html
   * </pre>
   *
   * @param openid        必填 支付用户唯一标识
   * @param transactionId 非必填 微信支付订单号
   * @param mchId         非必填 微信支付分配的商户号，和商户订单号配合使用
   * @param outTradeNo    非必填  微信支付商户订单号，和商户号配合使用
   * @return UnionId.
   * @throws WxErrorException .
   */
  String getPaidUnionId(String openid, String transactionId, String mchId, String outTradeNo) throws WxErrorException;

  /**
   * <pre>
   * Service没有实现某个API的时候，可以用这个，
   * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
   * 可以参考，{@link MediaUploadRequestExecutor}的实现方法
   * </pre>
   *
   * @param <E>      .
   * @param <T>      .
   * @param data     参数或请求数据
   * @param executor 执行器
   * @param uri      接口请求地址
   * @return .
   */
  <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException;

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试.
   * 默认：1000ms
   * </pre>
   *
   * @param retrySleepMillis 重试等待毫秒数
   */
  void setRetrySleepMillis(int retrySleepMillis);

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，最大重试次数.
   * 默认：5次
   * </pre>
   *
   * @param maxRetryTimes 最大重试次数
   */
  void setMaxRetryTimes(int maxRetryTimes);

  /**
   * 获取WxMaConfig 对象.
   *
   * @return WxMaConfig
   */
  WxMaConfig getWxMaConfig();

  /**
   * 注入 {@link WxMaConfig} 的实现.
   *
   * @param wxConfigProvider config
   */
  void setWxMaConfig(WxMaConfig wxConfigProvider);

  /**
   * 返回消息（客服消息和模版消息）发送接口方法实现类，以方便调用其各个接口.
   *
   * @return WxMaMsgService
   */
  WxMaMsgService getMsgService();

  /**
   * 返回素材相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMaMediaService
   */
  WxMaMediaService getMediaService();

  /**
   * 返回用户相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMaUserService
   */
  WxMaUserService getUserService();

  /**
   * 返回二维码相关接口方法的实现类对象，以方便调用其各个接口.
   *
   * @return WxMaQrcodeService
   */
  WxMaQrcodeService getQrcodeService();

  /**
   * 返回模板配置相关接口方法的实现类对象, 以方便调用其各个接口.
   *
   * @return WxMaTemplateService
   */
  WxMaTemplateService getTemplateService();

  /**
   * 返回订阅消息配置相关接口方法的实现类对象, 以方便调用其各个接口.
   *
   * @return WxMaSubscribeService
   */
  WxMaSubscribeService getSubscribeService();

  /**
   * 数据分析相关查询服务.
   *
   * @return WxMaAnalysisService
   */
  WxMaAnalysisService getAnalysisService();

  /**
   * 返回代码操作相关的 API.
   *
   * @return WxMaCodeService
   */
  WxMaCodeService getCodeService();

  /**
   * 返回jsapi操作相关的 API服务类对象.
   *
   * @return WxMaJsapiService
   */
  WxMaJsapiService getJsapiService();

  /**
   * 小程序修改服务器地址、成员管理 API.
   *
   * @return WxMaSettingService
   */
  WxMaSettingService getSettingService();

  /**
   * 返回分享相关查询服务.
   *
   * @return WxMaShareService
   */
  WxMaShareService getShareService();

  /**
   * 返回微信运动相关接口服务对象.
   *
   * @return WxMaShareService
   */
  WxMaRunService getRunService();

  /**
   * 返回内容安全相关接口服务对象.
   *
   * @return WxMaShareService
   */
  WxMaSecCheckService getSecCheckService();

  /**
   * 返回插件相关接口服务对象.
   *
   * @return WxMaPluginService
   */
  WxMaPluginService getPluginService();

  /**
   * 初始化http请求对象.
   */
  void initHttp();

  /**
   * 请求http请求相关信息.
   *
   * @return .
   */
  RequestHttp getRequestHttp();

  /**
   * 获取物流助手接口服务对象
   *
   * @return .
   */
  WxMaExpressService getExpressService();

  /**
   * 获取云开发接口服务对象
   *
   * @return .
   */
  WxMaCloudService getCloudService();

  /**
   * 获取直播接口服务对象
   *
   * @return .
   */
  WxMaLiveService getLiveService();

  /**
   * 获取直播间商品服务对象
   *
   * @return .
   */
  WxMaLiveGoodsService getLiveGoodsService();

  /**
   * 获取小程序素材管理服务对象
   *
   * @return .
   */
  WxMaMaterialService getMaterialService();
}
