package me.chanjar.weixin.open.api.impl;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.WxOpenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public abstract class WxOpenServiceAbstractImpl<H, P> implements WxOpenService, RequestHttp<H, P> {
  protected final Logger log = LoggerFactory.getLogger(this.getClass());
  protected WxOpenComponentService wxOpenComponentService = new WxOpenComponentServiceImpl(this);
  private WxOpenConfigStorage wxOpenConfigStorage;

  @Override
  public WxOpenComponentService getWxOpenComponentService() {
    return wxOpenComponentService;
  }

  @Override
  public WxOpenConfigStorage getWxOpenConfigStorage() {
    return wxOpenConfigStorage;
  }

  @Override
  public void setWxOpenConfigStorage(WxOpenConfigStorage wxOpenConfigStorage) {
    this.wxOpenConfigStorage = wxOpenConfigStorage;
  }

  protected synchronized <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    try {
      T result = executor.execute(uri, data);
      this.log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uri, data, result);
      return result;
    } catch (WxErrorException e) {
      WxError error = e.getError();
//      /*
//       * 发生以下情况时尝试刷新access_token
//       * 40001 获取access_token时AppSecret错误，或者access_token无效
//       * 42001 access_token超时
//       * 40014 不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口
//       */
//      if (error.getErrorCode() == 42001 || error.getErrorCode() == 40001 || error.getErrorCode() == 40014) {
//        // 强制设置wxCpConfigStorage它的access token过期了，这样在下一次请求里就会刷新access token
//        this.configStorage.expireAccessToken();
//        return execute(executor, uri, data);
//      }

      if (error.getErrorCode() != 0) {
        this.log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uri, data, error);
        throw new WxErrorException(error, e);
      }
      return null;
    } catch (IOException e) {
      this.log.error("\n【请求地址】: {}\n【请求参数】：{}\n【异常信息】：{}", uri, data, e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
