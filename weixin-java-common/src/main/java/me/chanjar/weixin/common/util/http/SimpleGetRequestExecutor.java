package me.chanjar.weixin.common.util.http;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;

import java.io.IOException;


/**
 * 简单的GET请求执行器，请求的参数是String, 返回的结果也是String
 *
 * @author Daniel Qian
 */
public class SimpleGetRequestExecutor implements RequestExecutor<String, String> {

  @Override
  public String execute(String uri, String queryParam) throws WxErrorException, IOException {
    if (queryParam != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
    }

    HttpRequest httpRequest = HttpRequest.post(uri);
    HttpResponse response = httpRequest.send();
    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return responseContent;
  }

}
