package me.chanjar.weixin.open.bean.minishop.coupon;

import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 小商店优惠券推广渠道
 */
@Data
public class WxMinishopCouponPromoteInfo implements Serializable {
  private static final long serialVersionUID = 1928131284657756435L;

  /**
   * 用户自定义推广渠道
   */
  private String customizeChannel;

  /**
   * 推广类型, 1:店铺内推广,2:自定义推广渠道
   */
  private Integer promotionType;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("customize_channel", customizeChannel);
    jsonObject.addProperty("promote_type", promotionType);
    return jsonObject;
  }
}
