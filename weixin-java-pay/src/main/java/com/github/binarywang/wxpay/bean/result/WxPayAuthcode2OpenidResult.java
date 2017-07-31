package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 *  授权码查询openid接口请求结果类
 * Created by Binary Wang on 2017-3-27.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
@XStreamAlias("xml")
public class WxPayAuthcode2OpenidResult extends WxPayBaseResult {
  /**
   * <pre>
   *   用户标识
   *   openid
   *   是
   *   String(128)
   *   用户在商户appid下的唯一标识
   * </pre>
   */
  @XStreamAlias("openid")
  private String openid;

  public WxPayAuthcode2OpenidResult() {
  }

  public WxPayAuthcode2OpenidResult(String openid) {
    this.openid = openid;
  }

  public String getOpenid() {
    return this.openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }
}
