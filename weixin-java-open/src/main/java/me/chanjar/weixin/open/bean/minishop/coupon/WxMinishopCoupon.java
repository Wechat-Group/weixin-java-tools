package me.chanjar.weixin.open.bean.minishop.coupon;

import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class WxMinishopCoupon implements Serializable {
  private static final long serialVersionUID = -6195756716727405785L;

  /**
   * 新增完成之后可以看到这个couponId
   */
  private Integer couponId;

  /**
   * 优惠券状态
   */
  private Integer status;

  /**
   * 优惠券类型:
   * 1 商品条件折券, discount_condition.product_ids, discount_condition.product_cnt, discount_info.discount_num 必填；
   * 2 商品满减券, discount_condition.product_ids, discount_condition.product_price, discount_info.discount_fee 必填；
   * 3 商品统一折扣券, discount_condition.product_ids, discount_info.discount_num必填；
   * 4 商品直减券, 如果小于可用的商品中的最小价格会提醒(没有商品时超过50w提醒）, discount_condition.product_ids, discount_fee 必填；
   * 101 店铺条件折扣券, discount_condition.product_cnt, discount_info.discount_num必填；
   * 102 店铺满减券, discount_condition.product_price, discount_info.discount_fee 必填；
   * 103 店铺统一折扣券, discount_info.discount_num 必填；
   * 104 店铺直减券, 如果小于可用的商品中的最小价格会提醒(没有商品时超过50w提醒）, discount_fee 必填
   */
  private Integer type;

  /**
   * 优惠券名称
   */
  private String name;

  /**
   * 商品折扣券信息
   */
  private WxMinishopCouponDiscountInfo discountInfo;

  /**
   * 优惠券额外信息
   */
  private WxMinishopCouponExtInfo extInfo;

  /**
   * 推广渠道信息
   */
  private WxMinishopCouponPromoteInfo promoteInfo;

  /**
   * 优惠券领取信息
   */
  private WxMinishopCouponReceiveInfo receiveInfo;

  /**
   * 优惠券有效期信息
   */
  private WxMinishopCouponValidInfo validInfo;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    if (couponId != null) {
      jsonObject.addProperty("coupon_id", couponId);
    }

    if (status != null) {
      jsonObject.addProperty("status", status);
    }
    jsonObject.addProperty("type", type);
    jsonObject.addProperty("name", name);
    if (discountInfo != null) {
      jsonObject.add("discount_info", discountInfo.toJsonObject());
    }

    if (extInfo != null) {
      jsonObject.add("ext_info", extInfo.toJsonObject());
    }

    if (promoteInfo != null) {
      jsonObject.add("promote_info", promoteInfo.toJsonObject());
    }

    if (receiveInfo != null) {
      jsonObject.add("receive_info", receiveInfo.toJsonObject());
    }

    if (validInfo != null) {
      jsonObject.add("valid_info", validInfo.toJsonObject());
    }

    return jsonObject;
  }
}
