package me.chanjar.weixin.common.util.http;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;

import java.io.IOException;


/**
 * 简单的POST请求执行器，请求的参数是String, 返回的结果也是String
 *
 * @author Daniel Qian
 */
public class SimplePostRequestExecutor implements RequestExecutor<String, String> {

  @Override
  public String execute(String uri, String postEntity) throws WxErrorException, IOException {
    HttpRequest httpRequest = HttpRequest.post(uri);
    HttpResponse response = httpRequest.send();
    String responseContent = response.bodyText();
    if (responseContent.isEmpty()) {
      throw new WxErrorException(
        WxError.newBuilder().setErrorCode(9999).setErrorMsg("无响应内容")
          .build());
    }

    if (responseContent.startsWith("<xml>")) {
      //xml格式输出直接返回
      return responseContent;
    }

    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return responseContent;
  }

}
