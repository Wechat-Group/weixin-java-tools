package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * .
 * @author leeis
 * @Date 2018/12/29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GeneralCardCreateRequest extends CardCreateRequest implements Serializable {
  private static final long serialVersionUID = 1771355872211267723L;

  @SerializedName("card_type")
  private String cardType = "GENERAL_COUPON";

  @SerializedName("general_coupon")
  private GeneralCard generalCoupon;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
