package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 离线自动回复
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/8/008
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WxMpGuideOffLineReply implements ToJson, Serializable {
  private static final long serialVersionUID = 1337376246361830706L;

  /**
   * 离线自动回复内容
   */
  @SerializedName("content")
  private String content;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideOffLineReply fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideOffLineReply.class);
  }
}
