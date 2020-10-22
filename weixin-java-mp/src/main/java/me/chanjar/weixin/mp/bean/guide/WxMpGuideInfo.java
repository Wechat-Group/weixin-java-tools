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
 * 对话能力-顾问信息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-10-06
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WxMpGuideInfo implements ToJson, Serializable {
  private static final long serialVersionUID = -8159470115679031290L;

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
   * 顾问状态（1:确认中；2已确认；3已拒绝；4已过期）
   */
  @SerializedName("status")
  private Integer status;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideInfo.class);
  }
}
