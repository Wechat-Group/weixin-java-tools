package me.chanjar.weixin.mp.util.requestexecuter.material;

import java.io.IOException;

import me.chanjar.weixin.common.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;

/**
 * @author codepiano
 */
public abstract class MaterialUploadRequestExecutor<H, P> implements RequestExecutor<WxMpMaterialUploadResult, WxMpMaterial> {
  protected RequestHttp<H, P> requestHttp;

  public MaterialUploadRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, WxMpMaterial data, ResponseHandler<WxMpMaterialUploadResult> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<WxMpMaterialUploadResult, WxMpMaterial> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new MaterialUploadApacheHttpRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new MaterialUploadJoddHttpRequestExecutor(requestHttp);
      case OK_HTTP:
        return new MaterialUploadOkhttpRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}
