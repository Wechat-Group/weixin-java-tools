package me.chanjar.weixin.cp.robot.api.impl;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.DataUtils;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.SimpleGetRequestExecutor;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import me.chanjar.weixin.cp.robot.api.WxCpRobotService;
import me.chanjar.weixin.cp.robot.bean.WxCpRobotMessage;
import me.chanjar.weixin.cp.robot.bean.WxCpRobotMessageSendResult;
import me.chanjar.weixin.cp.robot.config.WxCpRobotConfigStorage;
import me.chanjar.weixin.cp.robot.constant.WxCpRobotApiPathConsts;

import java.io.IOException;

/**
 * @author linfeng-eqxiu
 * @date 2019/12/4
 */
@Slf4j
public abstract class BaseWxCpRobotServiceImpl<H, P> implements WxCpRobotService, RequestHttp<H, P> {

  private int retrySleepMillis = 1000;
  private int maxRetryTimes = 5;
  protected WxCpRobotConfigStorage configStorage;


  protected <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {

    E dataForLog = DataUtils.handleDataWithSecret(data);

    try {
      T result = executor.execute(uri, data, WxType.CP);
      log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uri, dataForLog, result);
      return result;
    } catch (WxErrorException e) {
      WxError error = e.getError();
      /*
       * 发生以下情况时尝试刷新access_token
       * 40001 获取access_token时AppSecret错误，或者access_token无效
       * 42001 access_token超时
       * 40014 不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口
       */
      if (error.getErrorCode() == 42001 || error.getErrorCode() == 40001 || error.getErrorCode() == 40014) {
        // 强制设置wxCpConfigStorage它的access token过期了，这样在下一次请求里就会刷新access token
        return execute(executor, uri, data);
      }

      if (error.getErrorCode() != 0) {
        log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uri, dataForLog, error);
        throw new WxErrorException(error, e);
      }
      return null;
    } catch (IOException e) {
      log.error("\n【请求地址】: {}\n【请求参数】：{}\n【异常信息】：{}", uri, dataForLog, e.getMessage());
      throw new RuntimeException(e);
    }
  }

  @Override
  public WxCpRobotMessageSendResult sendRobotMessage(WxCpRobotMessage message) throws WxErrorException {
    String robotKey = message.getKey();
    if (null == robotKey) {
      robotKey = this.getWxCpRobotConfigStorage().getRobotKey();
    }

    String uri = this.configStorage.getApiUrl(WxCpRobotApiPathConsts.Robot.ROBOT_SEND_MESSAGE);
    if (uri.contains("key=")) {
      throw new IllegalArgumentException("uri参数中不允许有key: " + uri);
    }
    String uriWithKey = uri + (uri.contains("?") ? "&" : "?") + "key=" + robotKey;
    return WxCpRobotMessageSendResult.fromJson(this.post(uriWithKey, message.toJson()));

  }

  @Override
  public String get(String url, String queryParam) throws WxErrorException {
    return execute(SimpleGetRequestExecutor.create(this), url, queryParam);
  }

  @Override
  public String post(String url, String postData) throws WxErrorException {
    return execute(SimplePostRequestExecutor.create(this), url, postData);
  }

  @Override
  public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    int retryTimes = 0;
    do {
      try {
        return this.executeInternal(executor, uri, data);
      } catch (WxErrorException e) {
        if (retryTimes + 1 > this.maxRetryTimes) {
          log.warn("重试达到最大次数【{}】", this.maxRetryTimes);
          //最后一次重试失败后，直接抛出异常，不再等待
          throw new RuntimeException("微信服务端异常，超出重试次数");
        }

        WxError error = e.getError();
        /*
         * -1 系统繁忙, 1000ms后重试
         */
        if (error.getErrorCode() == -1) {
          int sleepMillis = this.retrySleepMillis * (1 << retryTimes);
          try {
            log.debug("微信系统繁忙，{} ms 后重试(第{}次)", sleepMillis, retryTimes + 1);
            Thread.sleep(sleepMillis);
          } catch (InterruptedException e1) {
            Thread.currentThread().interrupt();
          }
        } else {
          throw e;
        }
      }
    } while (retryTimes++ < this.maxRetryTimes);

    log.warn("重试达到最大次数【{}】", this.maxRetryTimes);
    throw new RuntimeException("微信服务端异常，超出重试次数");
  }

  @Override
  public WxCpRobotConfigStorage getWxCpRobotConfigStorage() {
    return configStorage;
  }

  @Override
  public void setWxCpRobotConfigStorage(WxCpRobotConfigStorage wxConfigProvider) {
    this.configStorage = wxConfigProvider;
    this.initHttp();
  }

  @Override
  public void setRetrySleepMillis(int retrySleepMillis) {
    this.retrySleepMillis = retrySleepMillis;
  }

  @Override
  public void setMaxRetryTimes(int maxRetryTimes) {
    this.maxRetryTimes = maxRetryTimes;
  }
}
