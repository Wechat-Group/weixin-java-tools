package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取快捷回复，关注顾问自动回复返回类
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/8/008
 */
@Data
public class WxMpGuideConfig implements Serializable {
  private static final long serialVersionUID = -343579331927473027L;

  /**
   * 快捷回复列表
   */
  @SerializedName("guide_fast_reply_list")
  private List<WxMpGuideFastReply> guideFastReplyList;

  /**
   * 第一条关注顾问自动回复（欢迎语）
   */
  @SerializedName("guide_auto_reply")
  private WxMpGuideAutoReply guideAutoReply;

  /**
   * 第二条关注顾问自动回复（欢迎语）
   */
  @SerializedName("guide_auto_reply_plus")
  private WxMpGuideAutoReply guideAutoReplyPlus;

  public static WxMpGuideConfig fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideConfig.class);
  }
}
