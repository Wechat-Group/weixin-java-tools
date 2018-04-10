package com.github.binarywang.wxpay.bean.notify;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付异步通知代金券详细
 */
@Data
@NoArgsConstructor
public class WxPayOrderNotifyCoupon implements Serializable {
  private static final long serialVersionUID = -4165343733538156097L;

  private String couponId;
  private String couponType;
  private Integer couponFee;

  public Map<String, String> toMap(int index) {
    Map<String, String> map = new HashMap<>();
    map.put("coupon_id_" + index, this.getCouponId());
    map.put("coupon_type_" + index, this.getCouponType());
    map.put("coupon_fee_" + index, this.getCouponFee() + "");
    return map;
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }
}
