package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 离线自动回复与敏感词
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/8/008
 */
@Data
public class WxMpGuideAcctConfig implements Serializable {
  private static final long serialVersionUID = -5941249630655543648L;

  /**
   * 敏感词
   */
  @SerializedName("black_keyword")
  private WxMpGuideSensitiveWords guideSensitiveWords;

  /**
   * 离线自动回复内容
   */
  @SerializedName("guide_auto_reply")
  private WxMpGuideOffLineReply guideOffLineReply;

  public static WxMpGuideAcctConfig fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideAcctConfig.class);
  }
}
