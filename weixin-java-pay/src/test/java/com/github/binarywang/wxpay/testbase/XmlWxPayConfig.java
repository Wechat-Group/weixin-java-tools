package com.github.binarywang.wxpay.testbase;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

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
  public boolean useSandboxForWxPay() {
    //沙箱环境不成熟，有问题无法使用，暂时屏蔽掉
    // return true;
    return false;
  }
}
