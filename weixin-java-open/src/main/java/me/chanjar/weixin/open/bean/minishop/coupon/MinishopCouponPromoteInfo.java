package me.chanjar.weixin.open.bean.minishop.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("小商店优惠券推广渠道")
public class MinishopCouponPromoteInfo implements Serializable {
  @ApiModelProperty(value = "用户自定义推广渠道", required = true)
  private String customizeChannel;

  @ApiModelProperty(value = "推广类型", required = true)
  private String promotionType;
}
