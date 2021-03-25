package me.chanjar.weixin.open.bean.minishop.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("小商店优惠券有效信息设置")
public class WxMinishopCouponValidInfo implements Serializable {
  @ApiModelProperty(value = "优惠券有效期结束时间，若填了start必填")
  private Integer endTime;

  @ApiModelProperty(value = "优惠券有效期开始时间，和valid_day_num二选一")
  private Integer startTime;

  @ApiModelProperty(value = "优惠券有效期天数，和start_time二选一", required = true)
  private Integer validDayNum;

  @ApiModelProperty(value = "优惠券有效期类型", required = true)
  private Integer validType;
}
