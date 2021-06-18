package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/** 文字素材信息
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/12/012
 */

@Data
public class WxMpGuideWordMaterialInfo implements ToJson, Serializable {
  private static final long serialVersionUID = -1370377663251409658L;

  /**
   * 文字内容
   */
  @SerializedName("word")
  private String word;

  /**
   * 创建时间戳
   */
  @SerializedName("create_time")
  private Long createTime;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideWordMaterialInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideWordMaterialInfo.class);
  }
}
