package me.chanjar.weixin.mp.util.http;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MaterialUploadRequestExecutor implements RequestExecutor<WxMpMaterialUploadResult, WxMpMaterial> {

  @Override
  public WxMpMaterialUploadResult execute(String uri, WxMpMaterial material) throws WxErrorException, IOException {
    if (material == null) {
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("非法请求，material参数为空").build());
    }

    File file = material.getFile();
    if (file == null || !file.exists()) {
      throw new FileNotFoundException();
    }

    HttpRequest request = HttpRequest.post(uri);
    request.form("media", file);
    if (material.getForm() != null) {
      request.query("description", WxGsonBuilder.create().toJson(material.getForm()));
    }
    HttpResponse response =request.send();
    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    } else {
      return WxMpMaterialUploadResult.fromJson(responseContent);
    }
  }


}
