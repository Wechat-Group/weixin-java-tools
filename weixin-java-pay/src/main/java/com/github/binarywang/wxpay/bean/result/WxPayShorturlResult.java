package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 * 转换短链接结果对象类
 * Created by Binary Wang on 2017-3-27.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
@XStreamAlias("xml")
public class WxPayShorturlResult extends WxPayBaseResult {
  /**
   * <pre>
   * URL链接
   * short_url
   * 是
   * String(64)
   * weixin：//wxpay/s/XXXXXX
   * 转换后的URL
   * </pre>
   */
  @XStreamAlias("short_url")
  private String shortUrl;

  public String getShortUrl() {
    return this.shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }
}
