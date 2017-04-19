package me.chanjar.weixin.mp.util.http;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.IOException;

public class MaterialNewsInfoRequestExecutor implements RequestExecutor<WxMpMaterialNews, String> {

  public MaterialNewsInfoRequestExecutor() {
    super();
  }

  @Override
  public WxMpMaterialNews execute(String uri, String materialId) throws WxErrorException, IOException {
    HttpRequest request =HttpRequest.post(uri);
    request.query("media_id", materialId);
    HttpResponse response =request.send();
    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    } else {
      return WxMpGsonBuilder.create().fromJson(responseContent, WxMpMaterialNews.class);
    }
  }


}
