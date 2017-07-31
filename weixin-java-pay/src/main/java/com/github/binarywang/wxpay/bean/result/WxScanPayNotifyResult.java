package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

public class WxScanPayNotifyResult extends WxPayBaseResult implements Serializable {
  private static final long serialVersionUID = 3381324564266118986L;

  /**
   * 预支付ID
   */
  @XStreamAlias("prepay_id")
  private String prepayId;

  public String getPrepayId() {
    return prepayId;
  }

  public void setPrepayId(String prepayId) {
    this.prepayId = prepayId;
  }

}
