package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 图片素材信息
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/12/012
 */
@Data
public class WxMpGuideImgMaterialInfo implements ToJson, Serializable {
  private static final long serialVersionUID = 9165977127399850455L;

  /**
   * 图片链接
   */
  @SerializedName("picurl")
  private String picUrl;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideImgMaterialInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideImgMaterialInfo.class);
  }
}
