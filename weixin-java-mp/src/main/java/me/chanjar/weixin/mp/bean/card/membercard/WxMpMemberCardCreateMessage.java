package me.chanjar.weixin.mp.bean.card.membercard;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

@Data
public final class WxMpMemberCardCreateMessage implements Serializable {

  @SerializedName("card")
  private MemberCardCreateRequest cardCreateRequest;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public static WxMpMemberCardCreateMessage fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpMemberCardCreateMessage.class);
  }
}
