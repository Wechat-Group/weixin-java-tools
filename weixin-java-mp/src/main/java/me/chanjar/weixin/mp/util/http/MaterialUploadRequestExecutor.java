package me.chanjar.weixin.mp.util.http;

import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import me.chanjar.weixin.mp.util.http.apache.ApacheMaterialUploadRequestExecutor;
import me.chanjar.weixin.mp.util.http.jodd.JoddMaterialUploadRequestExecutor;
import me.chanjar.weixin.mp.util.http.okhttp.OkhttpMaterialUploadRequestExecutor;

public abstract class MaterialUploadRequestExecutor<H, P> implements RequestExecutor<WxMpMaterialUploadResult, WxMpMaterial> {
  protected RequestHttp<H, P> requestHttp;

  public MaterialUploadRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  public static RequestExecutor<WxMpMaterialUploadResult, WxMpMaterial> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMaterialUploadRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddMaterialUploadRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkhttpMaterialUploadRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}
