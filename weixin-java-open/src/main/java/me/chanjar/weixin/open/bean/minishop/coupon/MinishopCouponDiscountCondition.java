package me.chanjar.weixin.open.bean.minishop.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("小商店商品折扣券详细信息")
public class MinishopCouponDiscountCondition {
  @ApiModelProperty(value = "商品折扣券打折金额", required = false)
  private Integer productCnt;

  @ApiModelProperty(value = "商品id，商品折扣券需填写", required = false)
  private List<Integer> productIds;


  @ApiModelProperty(value = "商品价格，满减券需填写", required = false)
  private Integer productPrice;
}
