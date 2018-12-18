package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.bean.WxCpMessageSendResult;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;

/**
 * 微信API的Service
 * @author chanjaster
 */
public interface WxCpService {
  /**
   * <pre>
   * 验证推送过来的消息的正确性
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=验证消息真实性
   * </pre>
   *
   * @param msgSignature 消息签名
   * @param timestamp    时间戳
   * @param nonce        随机数
   * @param data         微信传输过来的数据，有可能是echoStr，有可能是xml消息
   */
  boolean checkSignature(String msgSignature, String timestamp, String nonce, String data);

  /**
   * 获取access_token, 不强制刷新access_token
   *
   * @see #getAccessToken(boolean)
   */
  String getAccessToken() throws WxErrorException;

  /**
   * <pre>
   * 获取access_token，本方法线程安全
   * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
   * 另：本service的所有方法都会在access_token过期是调用此方法
   * 程序员在非必要情况下尽量不要主动调用此方法
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=获取access_token
   * </pre>
   *
   * @param forceRefresh 强制刷新
   */
  String getAccessToken(boolean forceRefresh) throws WxErrorException;

  /**
   * 获得jsapi_ticket,不强制刷新jsapi_ticket
   *
   * @see #getJsapiTicket(boolean)
   */
  String getJsapiTicket() throws WxErrorException;

  /**
   * <pre>
   * 获得jsapi_ticket
   * 获得时会检查jsapiToken是否过期，如果过期了，那么就刷新一下，否则就什么都不干
   *
   * 详情请见：http://qydev.weixin.qq.com/wiki/index.php?title=微信JS接口#.E9.99.84.E5.BD.951-JS-SDK.E4.BD.BF.E7.94.A8.E6.9D.83.E9.99.90.E7.AD.BE.E5.90.8D.E7.AE.97.E6.B3.95
   * </pre>
   *
   * @param forceRefresh 强制刷新
   */
  String getJsapiTicket(boolean forceRefresh) throws WxErrorException;

  /**
   * <pre>
   * 创建调用jsapi时所需要的签名
   *
   * 详情请见：http://qydev.weixin.qq.com/wiki/index.php?title=微信JS接口#.E9.99.84.E5.BD.951-JS-SDK.E4.BD.BF.E7.94.A8.E6.9D.83.E9.99.90.E7.AD.BE.E5.90.8D.E7.AE.97.E6.B3.95
   * </pre>
   *
   * @param url url
   */
  WxJsapiSignature createJsapiSignature(String url) throws WxErrorException;

  /**
   * <pre>
   * 发送消息
   * 详情请见: http://qydev.weixin.qq.com/wiki/index.php?title=%E5%8F%91%E9%80%81%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E
   * </pre>
   *
   * @param message 要发送的消息对象
   */
  WxCpMessageSendResult messageSend(WxCpMessage message) throws WxErrorException;

  /**
   * <pre>
   * 获取微信服务器的ip段
   * http://qydev.weixin.qq.com/wiki/index.php?title=回调模式#.E8.8E.B7.E5.8F.96.E5.BE.AE.E4.BF.A1.E6.9C.8D.E5.8A.A1.E5.99.A8.E7.9A.84ip.E6.AE.B5
   * </pre>
   *
   * @return { "ip_list": ["101.226.103.*", "101.226.62.*"] }
   */
  String[] getCallbackIp() throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的GET请求
   *
   * @param url        接口地址
   * @param queryParam 请求参数
   */
  String get(String url, String queryParam) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求
   *
   * @param url      接口地址
   * @param postData 请求body字符串
   */
  String post(String url, String postData) throws WxErrorException;

  /**
   * <pre>
   * Service没有实现某个API的时候，可以用这个，
   * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
   * 可以参考，{@link MediaUploadRequestExecutor}的实现方法
   * </pre>
   *
   * @param executor 执行器
   * @param uri      请求地址
   * @param data     参数
   * @param <T>      请求值类型
   * @param <E>      返回值类型
   */
  <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException;

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试
   * 默认：1000ms
   * </pre>
   *
   * @param retrySleepMillis 重试休息时间
   */
  void setRetrySleepMillis(int retrySleepMillis);

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，最大重试次数
   * 默认：5次
   * </pre>
   *
   * @param maxRetryTimes 最大重试次数
   */
  void setMaxRetryTimes(int maxRetryTimes);

  /**
   * 获取某个sessionId对应的session,如果sessionId没有对应的session，则新建一个并返回。
   *
   * @param id id可以为任意字符串，建议使用FromUserName作为id
   */
  WxSession getSession(String id);

  /**
   * 获取某个sessionId对应的session,如果sessionId没有对应的session，若create为true则新建一个，否则返回null。
   *
   * @param id     id可以为任意字符串，建议使用FromUserName作为id
   * @param create 是否新建
   */
  WxSession getSession(String id, boolean create);

  /**
   * <pre>
   * 设置WxSessionManager，只有当需要使用个性化的WxSessionManager的时候才需要调用此方法，
   * WxCpService默认使用的是{@link me.chanjar.weixin.common.session.StandardSessionManager}
   * </pre>
   *
   * @param sessionManager 会话管理器
   */
  void setSessionManager(WxSessionManager sessionManager);

  /**
   * 上传部门列表覆盖企业号上的部门信息
   *
   * @param mediaId 媒体id
   */
  String replaceParty(String mediaId) throws WxErrorException;

  /**
   * 上传用户列表覆盖企业号上的用户信息
   *
   * @param mediaId 媒体id
   */
  String replaceUser(String mediaId) throws WxErrorException;

  /**
   * 获取异步任务结果
   */
  String getTaskResult(String joinId) throws WxErrorException;

  /**
   * 初始化http请求对象
   */
  void initHttp();

  /**
   * 获取WxMpConfigStorage 对象
   *
   * @return WxMpConfigStorage
   */
  WxCpConfigStorage getWxCpConfigStorage();

  /**
   * 注入 {@link WxCpConfigStorage} 的实现
   *
   * @param wxConfigProvider 配置对象
   */
  void setWxCpConfigStorage(WxCpConfigStorage wxConfigProvider);

  /**
   * 获取部门相关接口的服务类对象
   */
  WxCpDepartmentService getDepartmentService();

  /**
   * 获取媒体相关接口的服务类对象
   */
  WxCpMediaService getMediaService();

  /**
   * 获取菜单相关接口的服务类对象
   */
  WxCpMenuService getMenuService();

  /**
   * 获取Oauth2相关接口的服务类对象
   */
  WxCpOAuth2Service getOauth2Service();

  /**
   * 获取标签相关接口的服务类对象
   */
  WxCpTagService getTagService();

  /**
   * 获取用户相关接口的服务类对象
   */
  WxCpUserService getUserService();
  
  /**
   * 获取群聊服务
   * 
   * @return 群聊服务
   */
  WxCpChatService getChatService();

  WxCpAgentService getAgentService();

  /**
   * http请求对象
   */
  RequestHttp<?, ?> getRequestHttp();

  void setUserService(WxCpUserService userService);

  void setDepartmentService(WxCpDepartmentService departmentService);

  void setMediaService(WxCpMediaService mediaService);

  void setMenuService(WxCpMenuService menuService);

  void setOauth2Service(WxCpOAuth2Service oauth2Service);

  void setTagService(WxCpTagService tagService);
}
