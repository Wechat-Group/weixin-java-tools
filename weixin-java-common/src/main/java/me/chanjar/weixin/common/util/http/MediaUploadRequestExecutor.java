package me.chanjar.weixin.common.util.http;

import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxService;
import me.chanjar.weixin.common.util.http.apache.ApacheMediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.jodd.JoddHttpMediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpMediaUploadRequestExecutor;

import java.io.File;
import java.io.IOException;

/**
 * 上传媒体文件请求执行器.
 * 请求的参数是File, 返回的结果是String
 *
 * @author Daniel Qian
 * @see WxService#upload(String, CommonUploadParam) 通用的上传，封装接口是推荐调用此方法
 * @see CommonUploadParam 通用的上传参数
 * @deprecated 不应该继续使用执行器的方式上传文件，封装上传接口时应调用通用的文件上传，而旧代码也应该逐步迁移为新的上传方式
 */
@Deprecated
public abstract class MediaUploadRequestExecutor<H, P> implements RequestExecutor<WxMediaUploadResult, File> {
  protected RequestHttp<H, P> requestHttp;

  public MediaUploadRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<WxMediaUploadResult> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<WxMediaUploadResult, File> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMediaUploadRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddHttpMediaUploadRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkHttpMediaUploadRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}
