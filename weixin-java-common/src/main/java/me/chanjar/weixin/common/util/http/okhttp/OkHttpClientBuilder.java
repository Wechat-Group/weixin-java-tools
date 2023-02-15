package me.chanjar.weixin.common.util.http.okhttp;

import okhttp3.*;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * @author wulang
 **/
public interface OkHttpClientBuilder {
  /**
   * 构建OkHttpClient实例.
   *
   * @return OkHttpClient
   */
  OkHttpClient build();

  /**
   * 代理
   *
   * @param proxy Proxy
   * @return OkHttpClientBuilder
   */
  OkHttpClientBuilder proxy(Proxy proxy);

  /**
   * 授权
   *
   * @param authenticator Authenticator
   * @return OkHttpClientBuilder
   */
  OkHttpClientBuilder authenticator(Authenticator authenticator);

  /**
   * 拦截器
   *
   * @param interceptor Interceptor
   * @return OkHttpClientBuilder
   */
  OkHttpClientBuilder addInterceptor(Interceptor interceptor);

  /**
   * 请求调度管理
   *
   * @param dispatcher Dispatcher
   * @return OkHttpClientBuilder
   */
  OkHttpClientBuilder setDispatcher(Dispatcher dispatcher);

  /**
   * 连接池
   *
   * @param connectionPool ConnectionPool
   * @return OkHttpClientBuilder
   */
  OkHttpClientBuilder setConnectionPool(ConnectionPool connectionPool);

  /**
   * 监听网络请求过程
   *
   * @param eventListenerFactory EventListener
   * @return OkHttpClientBuilder
   */
  OkHttpClientBuilder setEventListenerFactory(EventListener.Factory eventListenerFactory);

  /**
   * 是否支持失败重连
   *
   * @param retryOnConnectionFailure retryOnConnectionFailure
   * @return OkHttpClientBuilder
   */
  OkHttpClientBuilder setRetryOnConnectionFailure(Boolean retryOnConnectionFailure);

  /**
   * 是否允许重定向操作
   *
   * @param followRedirects followRedirects
   * @return OkHttpClientBuilder
   */
  OkHttpClientBuilder setFollowRedirects(Boolean followRedirects);

  /**
   * 连接建立的超时时间
   *
   * @param timeout 时长
   * @param unit    时间单位
   * @return OkHttpClientBuilder
   */
  OkHttpClientBuilder connectTimeout(Long timeout, TimeUnit unit);

  /**
   * 完整的请求过程超时时间
   *
   * @param timeout 时长
   * @param unit    时间单位
   * @return OkHttpClientBuilder
   */
  OkHttpClientBuilder callTimeout(Long timeout, TimeUnit unit);

  /**
   * 连接的IO读操作超时时间
   *
   * @param timeout 时长
   * @param unit    时间单位
   * @return OkHttpClientBuilder
   */
  OkHttpClientBuilder readTimeout(Long timeout, TimeUnit unit);

  /**
   * 连接的IO写操作超时时间
   *
   * @param timeout 时长
   * @param unit    时间单位
   * @return OkHttpClientBuilder
   */
  OkHttpClientBuilder writeTimeout(Long timeout, TimeUnit unit);

  /**
   * ping的时间间隔
   *
   * @param pingInterval ping的时间间隔
   * @return OkHttpClientBuilder
   */
  OkHttpClientBuilder setPingInterval(Integer pingInterval);
}
