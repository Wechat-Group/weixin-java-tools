package me.chanjar.weixin.common.util.http;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;

import java.io.IOException;

/**
 * http请求执行器.
 *
 * @param <T> 返回值类型
 * @param <E> 请求参数类型
 * @author Daniel Qian
 */
public interface RequestExecutor<T, E> {

  /**
   * 执行http请求.
   *
   * @param uri    uri
   * @param data   数据
   * @param wxType 微信模块类型
   * @return 响应结果
   * @throws WxErrorException 自定义异常
   * @throws IOException      io异常
   */
  T execute(String uri, E data, WxType wxType) throws WxErrorException, IOException;

  /**
   * 执行http请求.
   *
   * @param uri     uri
   * @param data    数据
   * @param handler http响应处理器
   * @param wxType  微信模块类型
   * @throws WxErrorException 自定义异常
   * @throws IOException      io异常
   */
  void execute(String uri, E data, ResponseHandler<T> handler, WxType wxType) throws WxErrorException, IOException;
}
