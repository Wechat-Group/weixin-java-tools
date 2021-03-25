package me.chanjar.weixin.open.bean.minishop.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("小商店优惠券消耗信息")
public class WxMinishopCouponStockInfo implements Serializable {
  @ApiModelProperty(value = "优惠券发放量")
  private Integer issuedNum;

  @ApiModelProperty(value = "优惠券领用量")
  private Integer receiveNum;

  @ApiModelProperty(value = "优惠券已用量")
  private Integer usedNum;
}
