package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.exception.WxPayException;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.net.SSLSocketHttpConnectionProvider;
import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;

/**
 * 微信支付请求实现类，jodd-http实现
 * Created by Binary Wang on 2016/7/28.
 *
 * @author binarywang (https://github.com/binarywang)
 */
public class WxPayServiceJoddHttpImpl extends WxPayServiceAbstractImpl {

  @Override
  protected String post(String url, String requestStr, boolean useKey) throws WxPayException {
    try {
      HttpRequest request = HttpRequest
        .post(url)
        .timeout(this.config.getHttpTimeout())
        .connectionTimeout(this.config.getHttpConnectionTimeout())
        .bodyText(requestStr);

      if (useKey) {
        SSLContext sslContext = this.getConfig().getSslContext();
        if (null == sslContext) {
          sslContext = this.getConfig().initSSLContext();
        }
        request.withConnectionProvider(new SSLSocketHttpConnectionProvider(sslContext));
      }

      String responseString = this.getResponseString(request.send());

      this.log.info("\n【请求地址】：{}\n【请求数据】：{}\n【响应数据】：{}", url, requestStr, responseString);
      return responseString;
    } catch (Exception e) {
      this.log.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, requestStr, e.getMessage());
      throw new WxPayException(e.getMessage(), e);
    }
  }

  private String getResponseString(HttpResponse response) throws WxPayException {
    try {
      this.log.debug("【微信服务器响应头信息】：\n{}", response.toString(false));
    } catch (NullPointerException e) {
      throw new WxPayException("response.toString() 居然抛出空指针异常了", e);
    }

    String responseString = response.bodyText();

    if (StringUtils.isBlank(responseString)) {
      throw new WxPayException("响应信息为空");
    }

    if (StringUtils.isBlank(response.charset())) {
      responseString = new String(responseString.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    return responseString;
  }


}
