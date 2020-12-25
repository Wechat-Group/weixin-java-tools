package me.chanjar.weixin.qidian.bean.card;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

@Data
public class WxMpCardLandingPageCreateResult implements Serializable {
  private Integer errcode;
  private String errmsg;

  /**
   * 货架链接。
   */
  private String url;
  /**
   * 货架ID。货架的唯一标识
   */
  @SerializedName("page_id")
  private Integer pageId;

  public boolean isSuccess() {
    return 0 == errcode;
  }

  public static WxMpCardLandingPageCreateResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpCardLandingPageCreateResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}

