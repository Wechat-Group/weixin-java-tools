package com.github.binarywang.wxpay.bean.notify;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WxScanPayNotifyResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = 3381324564266118986L;

  /**
   * 预支付ID
   */
  @XStreamAlias("prepay_id")
  private String prepayId;

}
