package com.github.binarywang.wxpay.testbase;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class XmlWxPayConfig extends WxPayConfig {
  private String openid;

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  @Override
  public boolean useSandbox() {
    //沙箱环境不成熟，有问题无法使用，暂时屏蔽掉
//     return true;
    return false;
  }
}
