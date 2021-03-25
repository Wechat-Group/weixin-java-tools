package me.chanjar.weixin.open.bean.minishop.coupon;

import com.google.gson.JsonObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("小商店优惠券有效信息设置")
public class WxMinishopCouponValidInfo implements Serializable {
  @ApiModelProperty(value = "优惠券有效期结束时间，若填了start必填")
  private Long endTime;

  @ApiModelProperty(value = "优惠券有效期开始时间，和valid_day_num二选一")
  private Long startTime;

  @ApiModelProperty(value = "优惠券有效期天数，和start_time二选一", required = true)
  private Integer validDayNum;

  @ApiModelProperty(value = "优惠券有效期类型, 0: 指定时间范围生效; 1: 生效天数", required = true)
  private Integer validType;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("start_time", startTime);
    jsonObject.addProperty("end_time", endTime);
    jsonObject.addProperty("valid_day_num", validDayNum);
    jsonObject.addProperty("valid_type", validType);

    return jsonObject;
  }
}
