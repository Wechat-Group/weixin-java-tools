package me.chanjar.weixin.mp.util.http;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;

import java.io.File;
import java.io.IOException;

/**
 * @author miller
 */
public class MediaImgUploadRequestExecutor implements RequestExecutor<WxMediaImgUploadResult, File> {
  @Override
  public WxMediaImgUploadResult execute(String uri, File data) throws WxErrorException, IOException {
    if (data == null) {
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("文件对象为空").build());
    }

    HttpRequest request =HttpRequest.post(uri);
    request.form("media", data);
    HttpResponse response =request.send();
    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }

    return WxMediaImgUploadResult.fromJson(responseContent);
  }

}
