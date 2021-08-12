package me.chanjar.weixin.mp.util.requestexecuter.material;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public abstract class MaterialVoiceAndImageDownloadRequestExecutor<H, P> implements RequestExecutor<InputStream, String> {
  protected RequestHttp<H, P> requestHttp;
  protected File tmpDirFile;

  public MaterialVoiceAndImageDownloadRequestExecutor(RequestHttp requestHttp, File tmpDirFile) {
    this.requestHttp = requestHttp;
    this.tmpDirFile = tmpDirFile;
  }

  @Override
  public void execute(String uri, String data, ResponseHandler<InputStream> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<InputStream, String> create(RequestHttp requestHttp, File tmpDirFile) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new MaterialVoiceAndImageDownloadApacheHttpRequestExecutor(requestHttp, tmpDirFile);
      case JODD_HTTP:
        return new MaterialVoiceAndImageDownloadJoddHttpRequestExecutor(requestHttp, tmpDirFile);
      case OK_HTTP:
        return new MaterialVoiceAndImageDownloadOkhttpRequestExecutor(requestHttp, tmpDirFile);
      default:
        return null;
    }
  }


}
