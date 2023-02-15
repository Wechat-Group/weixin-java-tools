package me.chanjar.weixin.common.util.http.okhttp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import javax.annotation.concurrent.NotThreadSafe;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author wulang
 **/
@Slf4j
@Data
@NotThreadSafe
public class DefaultOkHttpClientBuilder implements OkHttpClientBuilder {

  private final AtomicBoolean prepared = new AtomicBoolean(false);

  /**
   * 代理
   */
  private Proxy proxy;

  /**
   * 授权
   */
  private Authenticator authenticator;

  /**
   * 拦截器
   */
  private final List<Interceptor> interceptorList = new ArrayList<>();

  /**
   * 请求调度管理
   */
  private Dispatcher dispatcher;

  /**
   * 连接池
   */
  private ConnectionPool connectionPool;

  /**
   * 监听网络请求过程
   */
  private EventListener.Factory eventListenerFactory;

  /**
   * 是否支持失败重连
   */
  private Boolean retryOnConnectionFailure;

  /**
   * 是否允许重定向操作
   */
  private Boolean followRedirects;

  /**
   * 连接建立的超时时长
   */
  private Long connectTimeout;

  /**
   * 连接建立的超时时间单位
   */
  private TimeUnit connectTimeUnit;

  /**
   * 完整的请求过程超时时长
   */
  private Long callTimeout;

  /**
   * 完整的请求过程超时时间单位
   */
  private TimeUnit callTimeUnit;

  /**
   * 连接的IO读操作超时时长
   */
  private Long readTimeout;

  /**
   * 连接的IO读操作超时时间单位
   */
  private TimeUnit readTimeUnit;

  /**
   * 连接的IO写操作超时时长
   */
  private Long writeTimeout;

  /**
   * 连接的IO写操作超时时间单位
   */
  private TimeUnit writeTimeUnit;

  /**
   * ping的时间间隔
   */
  private Integer pingInterval;

  /**
   * 持有client对象,仅初始化一次,避免多service实例的时候造成重复初始化的问题
   */
  private OkHttpClient okHttpClient;

  private DefaultOkHttpClientBuilder() {

  }

  public static DefaultOkHttpClientBuilder get() {
    return DefaultOkHttpClientBuilder.SingletonHolder.INSTANCE;
  }

  @Override
  public OkHttpClient build() {
    if (!prepared.get()) {
      prepare();
    }
    return this.okHttpClient;
  }

  @Override
  public OkHttpClientBuilder proxy(Proxy proxy) {
    this.proxy = proxy;
    return this;
  }

  @Override
  public OkHttpClientBuilder authenticator(Authenticator authenticator) {
    this.authenticator = authenticator;
    return this;
  }

  @Override
  public OkHttpClientBuilder addInterceptor(Interceptor interceptor) {
    this.interceptorList.add(interceptor);
    return this;
  }

  @Override
  public OkHttpClientBuilder setDispatcher(Dispatcher dispatcher) {
    this.dispatcher = dispatcher;
    return this;
  }

  @Override
  public OkHttpClientBuilder setConnectionPool(ConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
    return this;
  }

  @Override
  public OkHttpClientBuilder setEventListenerFactory(EventListener.Factory eventListenerFactory) {
    this.eventListenerFactory = eventListenerFactory;
    return this;
  }

  @Override
  public OkHttpClientBuilder setRetryOnConnectionFailure(Boolean retryOnConnectionFailure) {
    this.retryOnConnectionFailure = retryOnConnectionFailure;
    return this;
  }

  @Override
  public OkHttpClientBuilder setFollowRedirects(Boolean followRedirects) {
    this.followRedirects = followRedirects;
    return this;
  }

  @Override
  public OkHttpClientBuilder connectTimeout(Long timeout, TimeUnit unit) {
    this.connectTimeout = timeout;
    this.connectTimeUnit = unit;
    return this;
  }

  @Override
  public OkHttpClientBuilder callTimeout(Long timeout, TimeUnit unit) {
    this.callTimeout = timeout;
    this.callTimeUnit = unit;
    return this;
  }

  @Override
  public OkHttpClientBuilder readTimeout(Long timeout, TimeUnit unit) {
    this.readTimeout = timeout;
    this.readTimeUnit = unit;
    return this;
  }

  @Override
  public OkHttpClientBuilder writeTimeout(Long timeout, TimeUnit unit) {
    this.writeTimeout = timeout;
    this.writeTimeUnit = unit;
    return this;
  }

  @Override
  public OkHttpClientBuilder setPingInterval(Integer pingInterval) {
    this.pingInterval = pingInterval;
    return this;
  }

  private synchronized void prepare() {
    if (prepared.get()) {
      return;
    }
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    if (this.authenticator != null) {
      builder.authenticator(this.authenticator);
    }
    if (this.proxy != null) {
      builder.proxy(this.proxy);
    }
    for (Interceptor interceptor : this.interceptorList) {
      builder.addInterceptor(interceptor);
    }
    if (this.dispatcher != null) {
      builder.dispatcher(dispatcher);
    }
    if (this.connectionPool != null) {
      builder.connectionPool(connectionPool);
    }
    if (this.eventListenerFactory != null) {
      builder.eventListenerFactory(this.eventListenerFactory);
    }
    if (this.retryOnConnectionFailure != null) {
      builder.setRetryOnConnectionFailure$okhttp(this.retryOnConnectionFailure);
    }
    if (this.followRedirects != null) {
      builder.followRedirects(this.followRedirects);
    }
    if (this.connectTimeout != null && this.connectTimeUnit != null) {
      builder.connectTimeout(this.connectTimeout, this.connectTimeUnit);
    }
    if (this.callTimeout != null && this.callTimeUnit != null) {
      builder.callTimeout(this.callTimeout, this.callTimeUnit);
    }
    if (this.readTimeout != null && this.readTimeUnit != null) {
      builder.readTimeout(this.readTimeout, this.readTimeUnit);
    }
    if (this.writeTimeout != null && this.writeTimeUnit != null) {
      builder.writeTimeout(this.writeTimeout, this.writeTimeUnit);
    }
    if (this.pingInterval != null) {
      builder.setPingInterval$okhttp(this.pingInterval);
    }
    this.okHttpClient = builder.build();
    prepared.set(true);
  }

  private static class SingletonHolder {
    private static final DefaultOkHttpClientBuilder INSTANCE = new DefaultOkHttpClientBuilder();
  }
}
