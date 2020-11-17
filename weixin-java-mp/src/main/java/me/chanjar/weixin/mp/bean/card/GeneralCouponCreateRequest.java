package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * .
 * @author leeis
 * @date 2018/12/29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GeneralCouponCreateRequest extends AbstractCardCreateRequest implements Serializable {
  private static final long serialVersionUID = 1771355872211267723L;

  @SerializedName("card_type")
  private String cardType = "GENERAL_COUPON";

  @SerializedName("general_coupon")
  private GeneralCoupon generalCoupon;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
