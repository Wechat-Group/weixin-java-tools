package com.github.binarywang.wxpay.testbase;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * The type Xml wx pay config.
 */
@XStreamAlias("xml")
public class XmlWxPayConfig extends WxPayConfig {
  private String openid;

  /**
   * Gets openid.
   *
   * @return the openid
   */
  public String getOpenid() {
    return openid;
  }

  /**
   * Sets openid.
   *
   * @param openid the openid
   */
  public void setOpenid(String openid) {
    this.openid = openid;
  }

  @Override
  public boolean isUseSandboxEnv() {
    //沙箱环境不成熟，有问题无法使用，暂时屏蔽掉
    //return true;
    return false;
  }
}
