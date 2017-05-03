package me.chanjar.weixin.common.util.http;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;

import jodd.http.HttpConnectionProvider;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.okhttp.OkhttpProxyInfo;
import okhttp3.ConnectionPool;

/**
 * Created by ecoolper on 2017/4/27.
 */
public abstract class AbstractRequestExecutor<T, E> implements RequestExecutor<T, E> {

  @Override
  public T execute(RequestHttp requestHttp, String uri, E data) throws WxErrorException, IOException{
    if (requestHttp.getRequestHttpClient() instanceof CloseableHttpClient) {
      //apache-http请求
      CloseableHttpClient httpClient = (CloseableHttpClient) requestHttp.getRequestHttpClient();
      HttpHost httpProxy = (HttpHost) requestHttp.getRequestHttpProxy();
      return executeApache(httpClient, httpProxy, uri, data);
    }
    if (requestHttp.getRequestHttpClient() instanceof HttpConnectionProvider) {
      //jodd-http请求
      HttpConnectionProvider provider = (HttpConnectionProvider) requestHttp.getRequestHttpClient();
      ProxyInfo proxyInfo = (ProxyInfo) requestHttp.getRequestHttpProxy();
      return executeJodd(provider, proxyInfo, uri, data);
    } else if (requestHttp.getRequestHttpClient() instanceof ConnectionPool) {
      //okhttp请求
      ConnectionPool pool = (ConnectionPool) requestHttp.getRequestHttpClient();
      OkhttpProxyInfo proxyInfo = (OkhttpProxyInfo) requestHttp.getRequestHttpProxy();
      return executeOkhttp(pool, proxyInfo, uri, data);
    } else {
      //TODO 这里需要抛出异常，需要优化
      return null;
    }
  }

}
