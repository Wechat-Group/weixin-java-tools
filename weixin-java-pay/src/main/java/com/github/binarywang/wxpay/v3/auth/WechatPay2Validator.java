package com.github.binarywang.wxpay.v3.auth;


import java.io.IOException;

import com.github.binarywang.wxpay.v3.Validator;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WechatPay2Validator implements Validator {

  private static final Logger log = LoggerFactory.getLogger(WechatPay2Validator.class);

  private Verifier verifier;

  public WechatPay2Validator(Verifier verifier) {
    this.verifier = verifier;
  }

  @Override
  public final boolean validate(CloseableHttpResponse response) throws IOException {
    Header serialNo = response.getFirstHeader("Wechatpay-Serial");
    Header sign = response.getFirstHeader("Wechatpay-Signature");
    Header timestamp = response.getFirstHeader("Wechatpay-TimeStamp");
    Header nonce = response.getFirstHeader("Wechatpay-Nonce");

    // todo: check timestamp
    if (timestamp == null || nonce == null || serialNo == null || sign == null) {
      return false;
    }

    String message = buildMessage(response);
    return verifier.verify(serialNo.getValue(), message.getBytes("utf-8"), sign.getValue());
  }

  protected final String buildMessage(CloseableHttpResponse response) throws IOException {
    String timestamp = response.getFirstHeader("Wechatpay-TimeStamp").getValue();
    String nonce = response.getFirstHeader("Wechatpay-Nonce").getValue();

    String body = getResponseBody(response);
    return timestamp + "\n"
          + nonce + "\n"
          + body + "\n";
  }

  protected final String getResponseBody(CloseableHttpResponse response) throws IOException {
    HttpEntity entity = response.getEntity();

    return (entity != null && entity.isRepeatable()) ? EntityUtils.toString(entity) : "";
  }
}
