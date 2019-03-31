package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

@Data
public class MemberCardUpdateRequest implements Serializable {
  @SerializedName("card_id")
  private String cardId;

  @SerializedName("member_card")
  private MemberCardUpdate memberCardUpdate;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
