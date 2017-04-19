package me.chanjar.weixin.mp.util.http;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * 获得QrCode图片 请求执行器
 * @author chanjarster
 *
 */
public class QrCodeRequestExecutor implements RequestExecutor<File, WxMpQrCodeTicket> {

  @Override
  public File execute(String uri, WxMpQrCodeTicket ticket) throws WxErrorException, IOException {
    if (ticket != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?")
          ? "ticket=" + URLEncoder.encode(ticket.getTicket(), "UTF-8")
          : "&ticket=" + URLEncoder.encode(ticket.getTicket(), "UTF-8");
    }


    HttpRequest request =HttpRequest.get(uri);
    HttpResponse response =request.send();
    String content =response.header("Content-Type");
    if (content != null && content.length() > 0) {
      // 出错
      if ("text/plain".equals(content)) {
        String responseContent = response.bodyText();
        throw new WxErrorException(WxError.fromJson(responseContent));
      }
    }

    InputStream inputStream =new ByteArrayInputStream(response.bodyBytes());
    return FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), "jpg");
  }


}
