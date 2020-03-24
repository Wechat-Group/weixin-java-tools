package me.chanjar.weixin.mp.bean.ocr;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author Theo Nie
 */
@Data
public class WxMpOcrCommResult implements Serializable {
  private static final long serialVersionUID = 455833771627756440L;

  @SerializedName("img_size")
  private WxMpOcrImgSize imgSize;
  @SerializedName("items")
  private List<Items> items;

  public static WxMpOcrCommResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpOcrCommResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  @Data
  public static class Items implements Serializable {

    private static final long serialVersionUID = 3066181677009102791L;
    @SerializedName("text")
    private String text;
    @SerializedName("pos")
    private WxMpOcrPos pos;

    @Override
    public String toString() {
      return WxMpGsonBuilder.create().toJson(this);
    }
  }
}
