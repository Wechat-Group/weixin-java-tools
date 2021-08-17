package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.AbstractWxMaQrcodeWrapper;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;

import java.io.File;
import java.io.IOException;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public abstract class QrcodeRequestExecutor<H, P> implements RequestExecutor<File, AbstractWxMaQrcodeWrapper> {
  protected RequestHttp<H, P> requestHttp;

  protected QrcodeRequestExecutor(RequestHttp<H, P> requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, AbstractWxMaQrcodeWrapper data, ResponseHandler<File> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }


  public static RequestExecutor<File, AbstractWxMaQrcodeWrapper> create(RequestHttp requestHttp, String path) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheQrcodeFileRequestExecutor(requestHttp, path);
      case OK_HTTP:
        return new OkHttpQrcodeFileRequestExecutor(requestHttp, path);
      case JODD_HTTP:
      default:
        return null;
    }
  }

  public static RequestExecutor<File, AbstractWxMaQrcodeWrapper> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheQrcodeFileRequestExecutor(requestHttp, null);
      case JODD_HTTP:
        return null;
      case OK_HTTP:
        return new OkHttpQrcodeFileRequestExecutor(requestHttp, null);
      default:
        return null;
    }
  }
}
