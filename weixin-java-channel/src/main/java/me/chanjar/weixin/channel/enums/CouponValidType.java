package me.chanjar.weixin.channel.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 视频号小店 优惠券 推广类型
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CouponValidType {
  /** 指定时间范围生效 */
  COUPON_VALID_TYPE_TIME(1, "指定时间范围生效"),
  /** 生效天数 */
  COUPON_VALID_TYPE_DAY(2, "生效天数"),

  ;

  private final int key;
  private final String val;

  CouponValidType(int key, String val) {
    this.key = key;
    this.val = val;
  }

  public int getKey() {
    return key;
  }

  public String getVal() {
    return val;
  }
}
