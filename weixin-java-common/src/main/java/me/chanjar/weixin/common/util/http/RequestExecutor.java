package me.chanjar.weixin.common.util.http;

import jodd.http.HttpConnectionProvider;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.exception.WxErrorException;

import me.chanjar.weixin.common.util.http.okhttp.OkhttpProxyInfo;
import okhttp3.ConnectionPool;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;

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
  T execute(RequestHttp requestHttp, String uri, E data) throws WxErrorException, IOException;

  /**
   * apache-http实现方式
   * @param httpclient
   * @param httpProxy
   * @param uri
   * @param data
   * @return
   * @throws WxErrorException
   * @throws IOException
   */
  T executeApache(CloseableHttpClient httpclient, HttpHost httpProxy, String uri, E data) throws WxErrorException, IOException;

    /**
     * jodd-http实现方式
     * @param provider
     * @param proxyInfo
     * @param uri
     * @param data
     * @return
     * @throws WxErrorException
     * @throws IOException
     */
  T executeJodd(HttpConnectionProvider provider, ProxyInfo proxyInfo, String uri, E data) throws WxErrorException, IOException;


  /** okhttp实现方式
   * @param pool
   * @param proxyInfo
   * @param uri
   * @param data
   * @return
   * @throws WxErrorException
   * @throws IOException
   */
  T executeOkhttp(ConnectionPool pool, final OkhttpProxyInfo proxyInfo, String uri, E data) throws WxErrorException, IOException;

}
