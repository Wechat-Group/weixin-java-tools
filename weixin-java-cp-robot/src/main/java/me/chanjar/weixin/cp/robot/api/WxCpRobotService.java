package me.chanjar.weixin.cp.robot.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.cp.robot.bean.WxCpRobotMessage;
import me.chanjar.weixin.cp.robot.bean.WxCpRobotMessageSendResult;
import me.chanjar.weixin.cp.robot.config.WxCpRobotConfigStorage;

/**
 * <pre>
 *  微信机器人发送消息
 * </pre>
 *
 * @author linfeng-eqxiu
 */
public interface WxCpRobotService {

  /**
   * 发送机器人消息
   *
   * @param message 要发送的消息对象
   * @return 发送结果
   * @throws WxErrorException
   */
  WxCpRobotMessageSendResult sendRobotMessage(WxCpRobotMessage message) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的GET请求
   *
   * @param url        接口地址
   * @param queryParam 请求参数
   */
  String get(String url, String queryParam) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求
   *
   * @param url      接口地址
   * @param postData 请求body字符串
   */
  String post(String url, String postData) throws WxErrorException;

  /**
   * <pre>
   * Service没有实现某个API的时候，可以用这个，
   * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
   * 可以参考，{@link MediaUploadRequestExecutor}的实现方法
   * </pre>
   *
   * @param executor 执行器
   * @param uri      请求地址
   * @param data     参数
   * @param <T>      请求值类型
   * @param <E>      返回值类型
   */
  <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException;

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试
   * 默认：1000ms
   * </pre>
   *
   * @param retrySleepMillis 重试休息时间
   */
  void setRetrySleepMillis(int retrySleepMillis);

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，最大重试次数
   * 默认：5次
   * </pre>
   *
   * @param maxRetryTimes 最大重试次数
   */
  void setMaxRetryTimes(int maxRetryTimes);

  /**
   * 获取WxCpRobotConfigStorage 对象
   *
   * @return WxCpRobotConfigStorage
   */
  WxCpRobotConfigStorage getWxCpRobotConfigStorage();

  /**
   * 注入 {@link WxCpRobotConfigStorage} 的实现
   *
   * @param wxConfigProvider 配置对象
   */
  void setWxCpRobotConfigStorage(WxCpRobotConfigStorage wxConfigProvider);

  /**
   * 初始化http请求对象
   */
  void initHttp();

}
