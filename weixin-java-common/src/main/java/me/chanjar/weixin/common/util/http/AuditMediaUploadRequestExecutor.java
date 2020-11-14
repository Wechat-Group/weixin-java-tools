package me.chanjar.weixin.common.util.http;

import java.io.File;
import java.io.IOException;

import me.chanjar.weixin.common.bean.result.WxAuditMediaUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.apache.ApacheAuditMediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.jodd.JoddHttpAuditMediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpAuditMediaUploadRequestExecutor;

/**
 * 小程序 提审素材上传接口
 * 上传媒体文件请求执行器.
 * 请求的参数是File, 返回的结果是String
 *
 * @author yangyh22
 * @since 2020/11/14
 */
public abstract class AuditMediaUploadRequestExecutor<H, P> implements RequestExecutor<WxAuditMediaUploadResult, File> {

  protected RequestHttp<H, P> requestHttp;

  public AuditMediaUploadRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<WxAuditMediaUploadResult> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<WxAuditMediaUploadResult, File> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheAuditMediaUploadRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddHttpAuditMediaUploadRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkHttpAuditMediaUploadRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}
