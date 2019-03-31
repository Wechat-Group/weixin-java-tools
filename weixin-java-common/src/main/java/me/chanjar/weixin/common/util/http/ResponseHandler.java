package me.chanjar.weixin.common.util.http;

/**
 * <pre>
 * http请求响应回调处理接口.
 * Created by Binary Wang on 2018/12/8.
 * </pre>
 *
 * @param <T> 返回值类型
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface ResponseHandler<T> {
  /**
   * 响应结果处理.
   *
   * @param t 要处理的对象
   */
  void handle(T t);
}
