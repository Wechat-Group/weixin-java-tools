package me.chanjar.weixin.qidian.bean.card.membercard;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

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
