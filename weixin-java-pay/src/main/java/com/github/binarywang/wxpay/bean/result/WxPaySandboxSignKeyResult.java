package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 *  Created by BinaryWang on 2017/6/18.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@XStreamAlias("xml")
public class WxPaySandboxSignKeyResult extends WxPayBaseResult {

  /**
   * <pre>
   * 沙箱密钥
   * sandbox_signkey
   * 否
   * 013467007045764
   * String(32)
   * 返回的沙箱密钥
   * </pre>
   */
  @XStreamAlias("sandbox_signkey")
  private String sandboxSignKey;

  public String getSandboxSignKey() {
    return sandboxSignKey;
  }

  public void setSandboxSignKey(String sandboxSignKey) {
    this.sandboxSignKey = sandboxSignKey;
  }
}
