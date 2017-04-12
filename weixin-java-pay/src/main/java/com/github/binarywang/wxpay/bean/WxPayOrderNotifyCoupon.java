package com.github.binarywang.wxpay.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付异步通知代金券详细
 */
public class WxPayOrderNotifyCoupon implements Serializable {
  /**
   * @fields serialVersionUID
   */
  private static final long serialVersionUID = -4165343733538156097L;

  @XStreamAlias("coupon_id")
  private String couponId;
  @XStreamAlias("coupon_type")
  private String couponType;
  @XStreamAlias("coupon_fee")
  private Integer couponFee;

  public String getCouponId() {
    return couponId;
  }

  public void setCouponId(String couponId) {
    this.couponId = couponId;
  }

  public String getCouponType() {
    return couponType;
  }

  public void setCouponType(String couponType) {
    this.couponType = couponType;
  }

  public Integer getCouponFee() {
    return couponFee;
  }

  public void setCouponFee(Integer couponFee) {
    this.couponFee = couponFee;
  }

  public Map<String, String> toMap(int index) {
    Map<String, String> map = new HashMap<>();
    map.put("coupon_id_" + index, this.getCouponId());
    map.put("coupon_type_" + index, this.getCouponType());
    map.put("coupon_fee_" + index, this.getCouponFee() + "");
    return map;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }
}
