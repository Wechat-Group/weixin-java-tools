package me.chanjar.weixin.open.bean.minishop.coupon;

import lombok.Data;

import java.io.Serializable;

/**
 * 小商店优惠券消耗信息
 */
@Data
public class WxMinishopCouponStockInfo implements Serializable {
  private static final long serialVersionUID = 7690057714224606954L;

  /**
   * 优惠券发放量
   */
  private Integer issuedNum;

  /**
   * 优惠券领用量
   */
  private Integer receiveNum;

  /**
   * 优惠券已用量
   */
  private Integer usedNum;
}
