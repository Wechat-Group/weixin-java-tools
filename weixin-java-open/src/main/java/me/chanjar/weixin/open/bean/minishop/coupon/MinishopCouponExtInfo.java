package me.chanjar.weixin.open.bean.minishop.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("小商店优惠券的扩展信息")
public class MinishopCouponExtInfo implements Serializable {
  @ApiModelProperty("备注信息")
  private String notes;

  @ApiModelProperty(value = "优惠券有效时间", required = true)
  private String validTime;
}
