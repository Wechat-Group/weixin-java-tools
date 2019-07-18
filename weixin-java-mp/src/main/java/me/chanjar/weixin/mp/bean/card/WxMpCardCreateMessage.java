package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

@Data
public final class WxMpCardCreateMessage implements Serializable {

  @SerializedName("card")
  private CardCreateRequest cardCreateRequest;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public static WxMpCardCreateMessage fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpCardCreateMessage.class);
  }
}
