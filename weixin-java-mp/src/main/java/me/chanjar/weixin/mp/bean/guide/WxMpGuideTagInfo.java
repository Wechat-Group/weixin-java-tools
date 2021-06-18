package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 标签信息
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/11/011
 */

@Data
public class WxMpGuideTagInfo implements ToJson, Serializable {
  private static final long serialVersionUID = 2086445319422158695L;

  /**
   * 标签类型名称
   */
  @SerializedName("tag_name")
  private String tagName;

  /**
   * 标签值
   */
  @SerializedName("tag_values")
  private List<String> values;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideTagInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideTagInfo.class);
  }
}
