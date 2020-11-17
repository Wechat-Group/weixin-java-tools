package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaShowItemResult extends WxOpenResult {

  private static final long serialVersionUID = -5707576958339934210L;

  @SerializedName("is_open")
  private Integer isOpen;

  @SerializedName("can_open")
  private Integer canOpen;

  @SerializedName("appid")
  private String appid;

  @SerializedName("nickname")
  private String nickname;

  @SerializedName("headimg")
  private String headimg;

  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }

}
