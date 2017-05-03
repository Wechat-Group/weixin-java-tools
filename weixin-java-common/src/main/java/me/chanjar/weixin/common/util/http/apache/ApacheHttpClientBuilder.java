package me.chanjar.weixin.common.util.http.apache;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * httpclient build interface
 * @author kakotor
 */
public interface ApacheHttpClientBuilder {

  /**
   * 构建httpclient实例
   *
   * @return new instance of CloseableHttpClient
   */
  CloseableHttpClient build();

  /**
   * 代理服务器地址
   *
   * @param httpProxyHost
   */
  ApacheHttpClientBuilder httpProxyHost(String httpProxyHost);

  /**
   * 代理服务器端口
   *
   * @param httpProxyPort
   */
  ApacheHttpClientBuilder httpProxyPort(int httpProxyPort);

  /**
   * 代理服务器用户名
   *
   * @param httpProxyUsername
   */
  ApacheHttpClientBuilder httpProxyUsername(String httpProxyUsername);

  /**
   * 代理服务器密码
   *
   * @param httpProxyPassword
   */
  ApacheHttpClientBuilder httpProxyPassword(String httpProxyPassword);

  /**
   * ssl连接socket工厂
   *
   * @param sslConnectionSocketFactory
   */
  ApacheHttpClientBuilder sslConnectionSocketFactory(SSLConnectionSocketFactory sslConnectionSocketFactory);
}
