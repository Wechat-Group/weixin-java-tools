package me.chanjar.weixin.common.util.http;

import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.apache.ApacheMediaUploadInputStreamRequestExecutor;

import java.io.IOException;
import java.io.InputStream;

/**
 * 上传媒体文件请求执行器.
 * 请求的参数是File, 返回的结果是String
 *
 * @author Daniel Qian
 */
public abstract class MediaUploadInputStreamRequestExecutor<H, P> implements RequestExecutor<WxMediaUploadResult, InputStream> {
  protected RequestHttp<H, P> requestHttp;

  public MediaUploadInputStreamRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, InputStream data, ResponseHandler<WxMediaUploadResult> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<WxMediaUploadResult, InputStream> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMediaUploadInputStreamRequestExecutor(requestHttp);
      case JODD_HTTP:
        throw new UnsupportedOperationException("暂不支持创建jodd形式stream上传");

      case OK_HTTP:
        throw new UnsupportedOperationException("暂不支持创建ok http形式stream上传");
      default:
        return null;
    }
  }

}
