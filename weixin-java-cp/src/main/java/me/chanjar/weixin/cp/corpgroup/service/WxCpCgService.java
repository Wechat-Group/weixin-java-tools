package me.chanjar.weixin.cp.corpgroup.service;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.corpgroup.WxCpCorpGroupCorpGetTokenReq;
import me.chanjar.weixin.cp.bean.corpgroup.WxCpMaTransferSession;
import me.chanjar.weixin.cp.config.WxCpCorpGroupConfigStorage;

/**
 * 企业微信企业互联API的Service.
 *
 * @author libo
 */
public interface WxCpCgService {
  /**
   * Update corp access token.
   *
   * @param corpId .
   * @param agentId .
   * @param corpAccessToken  the corp access token
   * @param expiresInSeconds the expires in seconds
   */
  void updateCorpAccessToken(String corpId, Integer agentId, String corpAccessToken, int expiresInSeconds);

  String getCorpAccessToken(String corpId, Integer agentId, Integer businessType) throws WxErrorException;

  String getCorpAccessToken(String corpId, Integer agentId, Integer businessType, boolean forceRefresh) throws WxErrorException;

  /**
   * 授权企业的access token相关
   *
   * @param corpId       the corp id
   * @param agentId
   * @param businessType
   * @return the access token
   */
  WxAccessToken getCorpAccessTokenEntity(String corpId, Integer agentId, Integer businessType) throws WxErrorException;

  /**
   * Gets access token entity.
   *
   * @param corpId       the  corp id
   * @param agentId
   * @param businessType
   * @return the access token entity
   */
  WxAccessToken getCorpAccessTokenEntity(String corpId, Integer agentId, Integer businessType, boolean forceRefresh) throws WxErrorException;

  /**
   * Is access token expired boolean.
   *
   * @param corpId  the  corp id
   * @param agentId
   * @return the boolean
   */
  boolean isCorpAccessTokenExpired(String corpId, Integer agentId);

  /**
   * Expire access token.
   *
   * @param corpId  the  corp id
   * @param agentId
   */
  void expireCorpAccessToken(String corpId, Integer agentId);

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的GET请求.
   *
   * @param url        接口地址
   * @param queryParam 请求参数
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String get(String url, String queryParam, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的GET请求.
   *
   * @param url                    接口地址
   * @param queryParam             请求参数
   * @param withoutCorpAccessToken 请求是否忽略CorpAccessToken 默认不忽略-false
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String get(String url, String queryParam, boolean withoutCorpAccessToken, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求.
   *
   * @param url      接口地址
   * @param postData 请求body字符串
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String post(String url, String postData, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException;

  /**
   * <pre>
   * Service没有实现某个API的时候，可以用这个，
   * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
   * 可以参考，{@link MediaUploadRequestExecutor}的实现方法
   * </pre>
   *
   * @param <T>      请求值类型
   * @param <E>      返回值类型
   * @param executor 执行器
   * @param uri      请求地址
   * @param data     参数
   * @return the t
   * @throws WxErrorException the wx error exception
   */
  <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException;

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试.
   * 默认：1000ms
   * </pre>
   *
   * @param retrySleepMillis 重试休息时间
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
   * 初始化http请求对象
   */
  void initHttp();

  void setWxCpCorpGroupConfigStorage(WxCpCorpGroupConfigStorage wxCpCorpGroupConfigStorage);

  WxCpCorpGroupConfigStorage getWxCpCorpGroupConfigStorage();

  /**
   * http请求对象.
   *
   * @return the request http
   */
  RequestHttp<?, ?> getRequestHttp();

  void setWxCpService(WxCpService wxCpService);

  /**
   * 互联企业的服务类对象
   *
   * @return
   */
  WxCpLinkedCorpService getLinkedCorpService();

  /**
   * 获取下级/下游企业小程序session
   * https://developer.work.weixin.qq.com/document/path/93355
   *
   * @param userId
   * @param sessionKey
   * @return
   * @throws WxErrorException
   */
  WxCpMaTransferSession getCorpTransferSession(String userId, String sessionKey, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException;
}
