package me.chanjar.weixin.common.util.http;

import me.chanjar.weixin.common.exception.WxErrorException;

import java.io.IOException;

/**
 * http请求执行器
 *
 * @param <T> 返回值类型
 * @param <E> 请求参数类型
 */
public interface RequestExecutor<T, E> {

  /**
   * @param uri  uri
   * @param data 数据
   * @throws WxErrorException
   * @throws IOException
   */
  T execute(String uri, E data) throws WxErrorException, IOException;

  /**
   * apache-http实现方式
   * @param httpclient
   * @param httpProxy
   * @param uri
   * @param data
   * @return
   * @throws WxErrorException
   * @throws IOException
   *//*
  T executeApache(CloseableHttpClient httpclient, HttpHost httpProxy, String uri, E data) throws WxErrorException, IOException;

    *//**
   * jodd-http实现方式
   * @param provider
   * @param proxyInfo
   * @param uri
   * @param data
   * @return
   * @throws WxErrorException
   * @throws IOException
   *//*
  T executeJodd(HttpConnectionProvider provider, ProxyInfo proxyInfo, String uri, E data) throws WxErrorException, IOException;


  *//** okhttp实现方式
   * @param pool
   * @param proxyInfo
   * @param uri
   * @param data
   * @return
   * @throws WxErrorException
   * @throws IOException
   *//*
  T executeOkhttp(ConnectionPool pool, final OkHttpProxyInfo proxyInfo, String uri, E data) throws WxErrorException, IOException;
*/
}
