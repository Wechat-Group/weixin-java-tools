package com.github.binarywang.wxpay.bean.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 * 授权码查询openid接口请求对象类
 * Created by Binary Wang on 2017-3-27.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
@XStreamAlias("xml")
public class WxPayAuthcode2OpenidRequest extends WxPayBaseRequest {

  /**
   * <pre>
   *     授权码
   *     auth_code
   *     是
   *     String(128)
   *     扫码支付授权码，设备读取用户微信中的条码或者二维码信息
   * </pre>
   */
  @XStreamAlias("auth_code")
  private String authCode;

  public WxPayAuthcode2OpenidRequest() {
  }

  public WxPayAuthcode2OpenidRequest(String authCode) {
    this.authCode = authCode;
  }

  public String getAuthCode() {
    return this.authCode;
  }

  public void setAuthCode(String authCode) {
    this.authCode = authCode;
  }

  @Override
  protected void checkConstraints() {
    // nothing to do
  }
}
