package me.chanjar.weixin.mp.bean.ocr;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * @author Theo Nie
 */
@Data
public class WxMpOcrImgSize implements Serializable {
  private static final long serialVersionUID = 5234409123551074168L;

  @SerializedName("w")
  private int w;
  @SerializedName("h")
  private int h;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
