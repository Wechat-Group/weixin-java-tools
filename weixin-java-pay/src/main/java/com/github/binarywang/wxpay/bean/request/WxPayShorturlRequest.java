package com.github.binarywang.wxpay.bean.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 * 转换短链接请求对象类
 * Created by Binary Wang on 2017-3-27.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
@XStreamAlias("xml")
public class WxPayShorturlRequest extends WxPayBaseRequest {
  /**
   * <pre>
   * URL链接
   * long_url
   * 是
   * String(512)
   * weixin：//wxpay/bizpayurl?sign=XXXXX&appid=XXXXX&mch_id=XXXXX&product_id=XXXXXX&time_stamp=XXXXXX&nonce_str=XXXXX
   * 需要转换的URL，签名用原串，传输需URLencode
   * </pre>
   */
  @XStreamAlias("long_url")
  private String longUrl;

  public String getLongUrl() {
    return this.longUrl;
  }

  public void setLongUrl(String longUrl) {
    this.longUrl = longUrl;
  }

  public WxPayShorturlRequest() {
  }

  public WxPayShorturlRequest(String longUrl) {
    this.longUrl = longUrl;
  }

  @Override
  protected void checkConstraints() {
    //do nothing
  }
}
