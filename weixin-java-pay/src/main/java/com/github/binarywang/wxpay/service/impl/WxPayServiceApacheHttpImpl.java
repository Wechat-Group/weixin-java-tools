package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.exception.WxPayException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;

/**
 * <pre>
 * 微信支付请求实现类，apache httpclient实现
 * Created by Binary Wang on 2016/7/28.
 * </pre>
 *
 * @author binarywang (https://github.com/binarywang)
 */
public class WxPayServiceApacheHttpImpl extends WxPayServiceAbstractImpl {

  @Override
  protected String post(String url, String requestStr, boolean useKey) throws WxPayException {
    try {
      HttpClientBuilder httpClientBuilder = HttpClients.custom();
      if (useKey) {
        SSLContext sslContext = this.getConfig().getSslContext();
        if (null == sslContext) {
          sslContext = this.getConfig().initSSLContext();
        }

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
          new String[]{"TLSv1"}, null, new DefaultHostnameVerifier());
        httpClientBuilder.setSSLSocketFactory(sslsf);
      }

      HttpPost httpPost = new HttpPost(url);
      httpPost.setConfig(RequestConfig.custom()
        .setConnectionRequestTimeout(this.getConfig().getHttpConnectionTimeout())
        .setConnectTimeout(this.getConfig().getHttpConnectionTimeout())
        .setSocketTimeout(this.getConfig().getHttpTimeout())
        .build());

      try (CloseableHttpClient httpclient = httpClientBuilder.build()) {
        httpPost.setEntity(new StringEntity(new String(requestStr.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1)));
        try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
          String result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
          this.log.info("\n【请求地址】：{}\n【请求数据】：{}\n【响应数据】：{}", url, requestStr, result);
          return result;
        }
      } finally {
        httpPost.releaseConnection();
      }
    } catch (Exception e) {
      this.log.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, requestStr, e.getMessage());
      throw new WxPayException(e.getMessage(), e);
    }
  }

}
