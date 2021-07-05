package me.chanjar.weixin.open.bean.minishop.coupon;

import lombok.Data;

import java.io.Serializable;

/**
 * 小商店优惠券返回信息
 */
@Data
public class WxMinishopCouponStock implements Serializable {
  private static final long serialVersionUID = -2022165905204478132L;

  /**
   * 优惠券ID
   */
  private Integer couponId;

  /**
   * 优惠券类型
   */
  private Integer type;

  /**
   * 优惠券状态
   */
  private Integer status;

  /**
   * 优惠券创建时间
   */
  private String createTime;

  /**
   * 优惠券更新时间
   */
  private String updateTime;

  /**
   * 优惠券详情信息
   */
  private WxMinishopCoupon couponInfo;

  /**
   * 优惠券使用信息
   */
  private WxMinishopCouponStockInfo stockInfo;
}
