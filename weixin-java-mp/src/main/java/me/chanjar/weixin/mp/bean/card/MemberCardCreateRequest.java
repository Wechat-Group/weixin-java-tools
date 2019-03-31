package me.chanjar.weixin.mp.bean.card;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

@Data
public class MemberCardCreateRequest implements Serializable {
  @SerializedName("card_type")
  private String cardType = "MEMBER_CARD";

  @SerializedName("member_card")
  private MemberCard memberCard;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
