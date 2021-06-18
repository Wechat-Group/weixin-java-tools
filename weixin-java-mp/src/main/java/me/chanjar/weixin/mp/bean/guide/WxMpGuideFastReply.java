package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 顾问快捷回复
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/8/008
 */
@Data
public class WxMpGuideFastReply implements ToJson, Serializable {
  private static final long serialVersionUID = -3316181204068248972L;

  /**
   * 快捷回复内容
   */
  @SerializedName("content")
  private String content;

  /**
   * 修改时间
   */
  @SerializedName("updatetime")
  private Long updateTime;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideFastReply fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideFastReply.class);
  }
}
