package me.chanjar.weixin.common.bean.imgproc;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author Theo Nie
 */
@Data
public class WxImgProcAiCropResult implements Serializable {
  private static final long serialVersionUID = -6470673963772979463L;

  @SerializedName("img_size")
  private ImgSize imgSize;

  @SerializedName("results")
  private List<Results> results;

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxImgProcAiCropResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxImgProcAiCropResult.class);
  }

  @Data
  public static class ImgSize implements Serializable {
    private static final long serialVersionUID = -6470673963772979463L;

    @SerializedName("w")
    private int w;

    @SerializedName("h")
    private int h;

    @Override
    public String toString() {
      return WxGsonBuilder.create().toJson(this);
    }
  }

  @Data
  public static class Results implements Serializable {
    private static final long serialVersionUID = -6470673963772979463L;

    @SerializedName("crop_left")
    private int cropLeft;

    @SerializedName("crop_top")
    private int cropTop;

    @SerializedName("crop_right")
    private int cropRight;

    @SerializedName("crop_bottom")
    private int cropBottom;

    @Override
    public String toString() {
      return WxGsonBuilder.create().toJson(this);
    }
  }
}
