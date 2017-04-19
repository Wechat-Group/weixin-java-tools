package me.chanjar.weixin.mp.util.http;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialVideoInfoResult;

import java.io.IOException;

public class MaterialVideoInfoRequestExecutor implements RequestExecutor<WxMpMaterialVideoInfoResult, String> {

  public MaterialVideoInfoRequestExecutor() {
    super();
  }

  @Override
  public WxMpMaterialVideoInfoResult execute(String uri, String materialId) throws WxErrorException, IOException {
    HttpRequest request =HttpRequest.post(uri);
    request.query("media_id", materialId);
    HttpResponse response =request.send();
    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    } else {
      return WxMpMaterialVideoInfoResult.fromJson(responseContent);
    }
  }


}
