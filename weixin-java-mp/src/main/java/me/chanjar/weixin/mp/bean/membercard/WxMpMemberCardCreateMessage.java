package me.chanjar.weixin.mp.bean.membercard;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.bean.card.MemberCardCreateRequest;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

@Data
public final class WxMpMemberCardCreateMessage implements Serializable {

  @SerializedName("card")
  private MemberCardCreateRequest cardCreateRequest;

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

  public static WxMpMemberCardCreateMessage fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpMemberCardCreateMessage.class);
  }
}
