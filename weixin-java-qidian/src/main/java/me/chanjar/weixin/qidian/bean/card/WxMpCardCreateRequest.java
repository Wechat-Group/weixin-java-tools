package me.chanjar.weixin.qidian.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * .
 *
 * @author IOMan
 */
@Data
public final class WxMpCardCreateRequest implements Serializable {
  private static final long serialVersionUID = 5951280855309617585L;

  @SerializedName("card")
  private AbstractCardCreateRequest cardCreateRequest;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public static WxMpCardCreateRequest fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpCardCreateRequest.class);
  }
}
