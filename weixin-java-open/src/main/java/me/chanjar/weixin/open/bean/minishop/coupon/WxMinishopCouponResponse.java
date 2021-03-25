package me.chanjar.weixin.open.bean.minishop.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("小商店优惠券请求回复数据信息")
public class WxMinishopCouponResponse implements Serializable {
  @ApiModelProperty("错误码")
  private Integer errcode;

  @ApiModelProperty("错误信息")
  private String errmsg;

  @ApiModelProperty("优惠券信息")
  private List<WxMinishopCouponStock> coupons;


}
