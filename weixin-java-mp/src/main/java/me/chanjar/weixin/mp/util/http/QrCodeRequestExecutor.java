package me.chanjar.weixin.mp.util.http;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.util.http.apache.ApacheQrCodeRequestExecutor;
import me.chanjar.weixin.mp.util.http.jodd.JoddQrCodeRequestExecutor;
import me.chanjar.weixin.mp.util.http.okhttp.OkhttpQrCodeRequestExecutor;

import java.io.File;

/**
 * 获得QrCode图片 请求执行器
 *
 * @author chanjarster
 */
public abstract class QrCodeRequestExecutor<H, P> implements RequestExecutor<File, WxMpQrCodeTicket> {
  protected RequestHttp<H, P> requestHttp;

  public QrCodeRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  public static RequestExecutor<File, WxMpQrCodeTicket> create(RequestHttp requestHttp) throws WxErrorException {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheQrCodeRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddQrCodeRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkhttpQrCodeRequestExecutor(requestHttp);
      default:
        throw new WxErrorException(WxError.newBuilder().setErrorMsg("不支持的http框架").build());
    }
  }

}
