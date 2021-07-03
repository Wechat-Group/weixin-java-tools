package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 顾问敏感词
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/8/008
 */

@Data
public class WxMpGuideSensitiveWords implements ToJson, Serializable {
  private static final long serialVersionUID = 1546603590395563048L;

  /**
   * 敏感词数组
   */
  @SerializedName("values")
  private String[] values;
  /**
   * 修改时间
   */
  @SerializedName("updatetime")
  private Long updateTime;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideSensitiveWords fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideSensitiveWords.class);
  }
}
