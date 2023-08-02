package me.chanjar.weixin.open.bean.minishop.coupon;

import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 小商店商品折扣券信息
 */
@Data
public class WxMinishopCouponDiscountInfo implements Serializable {
  private static final long serialVersionUID = -2290048692838721473L;

  /**
   * 小商店商品折扣详情
   */
  private WxMinishopCouponDiscountCondition discountCondition;

  /**
   * 满减金额
   */
  private Integer discountFee;

  /**
   * 打折商品数量，满减券需填写
   */
  private Integer discountNum;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("discount_condition", discountCondition.toJsonObject());
    jsonObject.addProperty("discount_fee", discountFee);
    jsonObject.addProperty("discount_num", discountNum);
    return jsonObject;
  }
}
