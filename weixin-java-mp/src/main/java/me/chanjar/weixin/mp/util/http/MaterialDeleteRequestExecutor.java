package me.chanjar.weixin.mp.util.http;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;

import java.io.IOException;

public class MaterialDeleteRequestExecutor implements RequestExecutor<Boolean, String> {


  public MaterialDeleteRequestExecutor() {
    super();
  }

  @Override
  public Boolean execute(String uri, String materialId) throws WxErrorException, IOException {
    HttpRequest request =HttpRequest.post(uri);
    request.query("media_id", materialId);
    HttpResponse response =request.send();
    String responseContent =response.bodyText();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    } else {
      return true;
    }
  }


}
