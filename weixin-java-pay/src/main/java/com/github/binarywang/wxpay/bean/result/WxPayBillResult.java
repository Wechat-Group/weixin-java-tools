package com.github.binarywang.wxpay.bean.result;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class WxPayBillResult implements Serializable {
  private static final long serialVersionUID = -7687458652694204070L;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  /**
   * 对账返回对象
   */
  private List<WxPayBillBaseResult> wxPayBillBaseResultLst;
  /**
   * 总交易单数
   */
  private String totalRecord;
  /**
   * 总交易额
   */
  private String totalFee;
  /**
   * 总退款金额
   */
  private String totalRefundFee;
  /**
   * 总代金券或立减优惠退款金额
   */
  private String totalCouponFee;
  /**
   * 手续费总金额
   */
  private String totalPoundageFee;

}
