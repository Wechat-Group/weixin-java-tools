package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 客户顾问关系
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/11/011
 */

@Data
public class WxMpGuideBuyerRelation implements ToJson, Serializable {
  private static final long serialVersionUID = 1531261524650705552L;

  /**
   * 顾问的微信帐号
   */
  @SerializedName("guide_account")
  private String guideAccount;

  /**
   * 顾问的openid或者unionid
   */
  @SerializedName("guide_openid")
  private String guideOpenid;

  /**
   * 客户的openId
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 客户的名称
   */
  @SerializedName("buyer_nickname")
  private String nickname;

  /**
   * 创建时间戳
   */
  @SerializedName("create_time")
  private Long createTime;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideBuyerRelation fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideBuyerRelation.class);
  }
}
