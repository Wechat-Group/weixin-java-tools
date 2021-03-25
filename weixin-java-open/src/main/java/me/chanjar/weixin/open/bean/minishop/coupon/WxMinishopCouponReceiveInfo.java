package me.chanjar.weixin.open.bean.minishop.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("小商店优惠券领取信息")
public class WxMinishopCouponReceiveInfo implements Serializable {
  @ApiModelProperty(value = "优惠券领用结束时间", required = true)
  private String endTime;

  @ApiModelProperty(value = "是否限制一人使用", required = true)
  private Integer limitNumOnePerson;

  @ApiModelProperty(value = "优惠券领用开始时间",required = true)
  private String startTime;

  @ApiModelProperty(value = "优惠券领用总数", required = true)
  private Integer totalNum;
}
