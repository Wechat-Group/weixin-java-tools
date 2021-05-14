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
 * 分组顾问信息.
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/8/008
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WxMpGuideGroupInfo implements ToJson, Serializable {
  private static final long serialVersionUID = -4927568853154487513L;

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
   * 顾问昵称
   */
  @SerializedName("guide_nickname")
  private String nickName;

  /**
   * 顾问头像
   */
  @SerializedName("guide_headimgurl")
  private String headImgUrl;

  /**
   * 创建时间戳
   */
  @SerializedName("create_time")
  private Long createTime;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideGroupInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideGroupInfo.class);
  }
}
