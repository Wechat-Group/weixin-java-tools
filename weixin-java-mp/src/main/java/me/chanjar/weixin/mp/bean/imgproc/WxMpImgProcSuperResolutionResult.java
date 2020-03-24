package me.chanjar.weixin.mp.bean.imgproc;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 图片高清化返回结果
 * @author Theo Nie
 */
@Data
public class WxMpImgProcSuperResolutionResult implements Serializable {

  private static final long serialVersionUID = 8007440280170407021L;
  @SerializedName("media_id")
  private String mediaId;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public static WxMpImgProcSuperResolutionResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpImgProcSuperResolutionResult.class);
  }
}
