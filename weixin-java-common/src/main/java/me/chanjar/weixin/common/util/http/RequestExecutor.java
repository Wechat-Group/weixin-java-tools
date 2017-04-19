package me.chanjar.weixin.common.util.http;

import java.io.IOException;

import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * http请求执行器
 *
 * @param <T> 返回值类型
 * @param <E> 请求参数类型
 */
public interface RequestExecutor<T, E> {

  /**
   * @param uri        uri
   * @param data       数据
   * @throws WxErrorException
   * @throws IOException
   */
  T execute(String uri, E data) throws WxErrorException, IOException;

}
