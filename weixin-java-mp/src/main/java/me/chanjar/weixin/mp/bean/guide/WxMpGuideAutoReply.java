package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 关注顾问自动回复（欢迎语）
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/8/008
 */
@Data
public class WxMpGuideAutoReply implements ToJson, Serializable {

  private static final long serialVersionUID = -3584275317132197695L;

  /**
   * 新客户关注自动回复内容
   */
  @SerializedName("content")
  private String content;

  /**
   * 新客户关注自动回复内容类型
   * 1表示文字，2表示图片，3表示小程序卡片
   */
  @SerializedName("msgtype")
  private Integer msgType;

  /**
   * 修改时间
   */
  @SerializedName("updatetime")
  private Long updateTime;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideAutoReply fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideAutoReply.class);
  }
}
