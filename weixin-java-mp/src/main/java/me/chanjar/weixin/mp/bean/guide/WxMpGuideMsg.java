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
 * 顾问聊天记录
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/7/007
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WxMpGuideMsg implements ToJson, Serializable {
  private static final long serialVersionUID = -5175162334221904778L;

  /**
   * 顾问的微信帐号
   */
  @SerializedName("guide_account")
  private String account;

  /**
   * 顾问的openid或者unionid
   */
  @SerializedName("guide_openid")
  private String openid;

  /**
   * 聊天记录生成时间
   */
  @SerializedName("create_time")
  private Long createTime;

  /**
   * 聊天内容
   */
  @SerializedName("content")
  private String content;

  /**
   * 聊天记录类型
   *
   * 1	文字类型
   * 3	图片类型
   * 49	小程序卡片类型
   */
  @SerializedName("content_type")
  private Integer contentType;

  /**
   * 消息指向
   *
   * 1	顾问发送消息给客户
   * 2	客户发送消息给顾问
   */
  @SerializedName("direction")
  private Integer direction;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideMsg fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideMsg.class);
  }
}
