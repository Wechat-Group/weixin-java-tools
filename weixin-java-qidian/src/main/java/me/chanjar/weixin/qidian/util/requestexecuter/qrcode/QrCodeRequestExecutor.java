package me.chanjar.weixin.qidian.util.requestexecuter.qrcode;

import java.io.File;
import java.io.IOException;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.qidian.bean.result.WxMpQrCodeTicket;

/**
 * 获得QrCode图片 请求执行器.
 *
 * @author chanjarster
 */
public abstract class QrCodeRequestExecutor<H, P> implements RequestExecutor<File, WxMpQrCodeTicket> {
  protected RequestHttp<H, P> requestHttp;

  public QrCodeRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, WxMpQrCodeTicket data, ResponseHandler<File> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<File, WxMpQrCodeTicket> create(RequestHttp requestHttp) throws WxErrorException {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new QrCodeApacheHttpRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new QrCodeJoddHttpRequestExecutor(requestHttp);
      case OK_HTTP:
        return new QrCodeOkhttpRequestExecutor(requestHttp);
      default:
        throw new WxErrorException("不支持的http框架");
    }
  }

}
