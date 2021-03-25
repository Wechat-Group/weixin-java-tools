package me.chanjar.weixin.open.bean.minishop.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("小商店优惠券信息")
public class WxMinishopCoupon implements Serializable {
  @ApiModelProperty(value = "优惠券类型: 1 商品条件折券, discount_condition.product_ids, discount_condition.product_cnt, discount_info.discount_num 必填" +
    "2 商品满减券, discount_condition.product_ids, discount_condition.product_price, discount_info.discount_fee 必填" +
    "3 商品统一折扣券, discount_condition.product_ids, discount_info.discount_num必填" +
    "4 商品直减券, 如果小于可用的商品中的最小价格会提醒(没有商品时超过50w提醒）, discount_condition.product_ids, discount_fee 必填" +
    "101 店铺条件折扣券, discount_condition.product_cnt, discount_info.discount_num必填" +
    "102 店铺满减券, discount_condition.product_price, discount_info.discount_fee 必填" +
    "103 店铺统一折扣券, discount_info.discount_num 必填" +
    "104 店铺直减券, 如果小于可用的商品中的最小价格会提醒(没有商品时超过50w提醒）, discount_fee 必填", required = true)
  private Integer type;

  @ApiModelProperty(value = "优惠券名称", required = true)
  private String name;

  @ApiModelProperty(value = "商品折扣券信息")
  private WxMinishopCouponDiscountInfo discountInfo;

  @ApiModelProperty(value = "优惠券额外信息")
  private WxMinishopCouponExtInfo extInfo;

  @ApiModelProperty(value = "推广渠道信息",required = true)
  private WxMinishopCouponPromoteInfo promoteInfo;

  @ApiModelProperty(value = "优惠券领取信息", required = true)
  private WxMinishopCouponReceiveInfo receiveInfo;

  @ApiModelProperty(value = "优惠券有效期信息", required = true)
  private WxMinishopCouponValidInfo validInfo;
}
