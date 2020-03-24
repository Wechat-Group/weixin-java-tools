package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaSearchStatusResult extends WxOpenResult {
  private static final long serialVersionUID = -1843419921284224813L;

  @SerializedName("status")
  private Integer status;

  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }
}
