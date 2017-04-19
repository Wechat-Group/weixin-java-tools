package me.chanjar.weixin.common.util.http;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;

import java.io.File;
import java.io.IOException;


/**
 * 上传媒体文件请求执行器，请求的参数是File, 返回的结果是String
 *
 * @author Daniel Qian
 */
public class MediaUploadRequestExecutor implements RequestExecutor<WxMediaUploadResult, File> {

  @Override
  public WxMediaUploadResult execute(String uri, File file) throws WxErrorException, IOException {
    HttpRequest request = HttpRequest.post(uri);
    request.form("media", file);
    HttpResponse response = request.send();
    String responseContent =response.bodyText();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return WxMediaUploadResult.fromJson(responseContent);
  }

}
