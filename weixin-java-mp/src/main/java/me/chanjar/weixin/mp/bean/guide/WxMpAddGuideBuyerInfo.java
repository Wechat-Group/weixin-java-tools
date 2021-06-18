package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 客户信息dto
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/11/011
 */

@Data
@Builder
public class WxMpAddGuideBuyerInfo implements ToJson, Serializable {
  private static final long serialVersionUID = -1703303970552268691L;

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

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpAddGuideBuyerInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpAddGuideBuyerInfo.class);
  }
}
