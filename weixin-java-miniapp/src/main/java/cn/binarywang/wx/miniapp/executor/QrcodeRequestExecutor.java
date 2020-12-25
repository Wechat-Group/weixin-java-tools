package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.AbstractWxMaQrcodeWrapper;
import cn.binarywang.wx.miniapp.bean.WxMaAuditMediaUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.common.util.http.apache.InputStreamResponseHandler;
import me.chanjar.weixin.common.util.http.apache.Utf8ResponseHandler;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public abstract class QrcodeRequestExecutor<H, P> implements RequestExecutor<File, AbstractWxMaQrcodeWrapper> {
  protected RequestHttp<H, P> requestHttp;

  public QrcodeRequestExecutor(RequestHttp<H, P> requestHttp) {
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
      case JODD_HTTP:
        return null;
      case OK_HTTP:
        return new OkHttpQrcodeFileRequestExecutor(requestHttp, path);
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
