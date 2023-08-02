package me.chanjar.weixin.open.bean.minishop.coupon;

import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 小商店优惠券领取信息
 */
@Data
public class WxMinishopCouponReceiveInfo implements Serializable {
  private static final long serialVersionUID = -3168216738144299136L;

  /**
   * 优惠券领用结束时间
   */
  private Long endTime;

  /**
   * 是否限制一人使用
   */
  private Integer limitNumOnePerson;

  /**
   * 优惠券领用开始时间
   */
  private Long startTime;

  /**
   * 优惠券领用总数
   */
  private Integer totalNum;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("start_time", startTime);
    jsonObject.addProperty("end_time", endTime);
    jsonObject.addProperty("limit_num_one_person", limitNumOnePerson);
    jsonObject.addProperty("total_num", totalNum);
    return jsonObject;
  }
}
