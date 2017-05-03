package me.chanjar.weixin.common.util.http;

/**
 * Created by ecoolper on 2017/4/22.
 */
public interface RequestHttp<H,P> {

  /**
   * 返回httpClient
   * @return
   */
  H getRequestHttpClient();

  /**
   * 返回httpProxy
   * @return
   */
  P getRequestHttpProxy();

}
