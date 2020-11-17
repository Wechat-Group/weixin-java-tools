package com.github.binarywang.wxpay.bean.notify;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

/**
 * 支付异步通知代金券详细.
 *
 * @author aimilin
 */
@Data
@NoArgsConstructor
public class WxPayOrderNotifyCoupon implements Serializable {
  private static final long serialVersionUID = -4165343733538156097L;

  private String couponId;
  private String couponType;
  private Integer couponFee;

  /**
   * To map map.
   *
   * @param index the index
   * @return the map
   */
  public Map<String, String> toMap(int index) {
    Map<String, String> map = new HashMap<>();
    map.put("coupon_id_" + index, this.getCouponId());
    map.put("coupon_type_" + index, this.getCouponType());
    map.put("coupon_fee_" + index, this.getCouponFee() + "");
    return map;
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }
}
