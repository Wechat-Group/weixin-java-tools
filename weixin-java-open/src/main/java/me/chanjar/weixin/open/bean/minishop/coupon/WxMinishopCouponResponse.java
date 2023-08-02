package me.chanjar.weixin.open.bean.minishop.coupon;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 小商店优惠券请求回复数据信息
 */
@Data
public class WxMinishopCouponResponse implements Serializable {
  private static final long serialVersionUID = 1579611003616556089L;

  /**
   * 错误码
   */
  private Integer errcode;

  /**
   * 错误信息
   */
  private String errmsg;

  /**
   * 优惠券信息
   */
  private List<WxMinishopCouponStock> coupons;


}
