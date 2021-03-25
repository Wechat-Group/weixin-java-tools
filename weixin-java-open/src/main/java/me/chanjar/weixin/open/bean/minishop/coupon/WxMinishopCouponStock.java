package me.chanjar.weixin.open.bean.minishop.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("小商店优惠券返回信息")
@Data
public class WxMinishopCouponStock implements Serializable {
  @ApiModelProperty("优惠券ID")
  private Integer couponId;

  @ApiModelProperty("优惠券类型")
  private Integer type;

  @ApiModelProperty("优惠券状态")
  private Integer status;

  @ApiModelProperty("优惠券创建时间")
  private String createTime;

  @ApiModelProperty("优惠券更新时间")
  private String updateTime;

  @ApiModelProperty("优惠券详情信息")
  private WxMinishopCoupon couponInfo;

  @ApiModelProperty("优惠券使用信息")
  private WxMinishopCouponStockInfo stockInfo;
}
