package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.vod.WxMaVodUploadPartResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;

import java.io.File;
import java.io.IOException;

/**
 */
public abstract class VodUploadPartRequestExecutor<H, P> implements RequestExecutor<WxMaVodUploadPartResult, File> {

  protected RequestHttp<H, P> requestHttp;
  protected String uploadId;
  protected Integer partNumber;
  protected Integer resourceType;

  public VodUploadPartRequestExecutor(RequestHttp requestHttp, String uploadId, Integer partNumber, Integer resourceType) {
    this.requestHttp = requestHttp;
    this.uploadId = uploadId;
    this.partNumber = partNumber;
    this.resourceType = resourceType;

  }

  public static RequestExecutor<WxMaVodUploadPartResult, File> create(RequestHttp requestHttp, String uploadId, Integer partNumber, Integer resourceType) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheVodUploadPartRequestExecutor(requestHttp, uploadId, partNumber, resourceType);
      case JODD_HTTP:
        return new JoddHttpVodUploadPartRequestExecutor(requestHttp, uploadId, partNumber, resourceType);
      case OK_HTTP:
        return new OkHttpVodUploadPartRequestExecutor(requestHttp, uploadId, partNumber, resourceType);
      default:
        return null;
    }
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<WxMaVodUploadPartResult> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }


}
