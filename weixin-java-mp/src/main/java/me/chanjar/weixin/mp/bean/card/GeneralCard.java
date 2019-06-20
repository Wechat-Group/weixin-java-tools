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
public final class GeneralCard extends Card implements Serializable {
  private static final long serialVersionUID = -1577656733441132585L;

  /**
   * 兑换券专用，填写兑换内容的名称。
   */
  @SerializedName("default_detail")
  private String defaultDetail;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public static GeneralCard fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, GeneralCard.class);
  }
}
