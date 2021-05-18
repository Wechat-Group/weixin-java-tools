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
 * 客户信息
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/10/010
 */

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WxMpGuideBuyerInfo implements ToJson, Serializable {
  private static final long serialVersionUID = -8076715937378141119L;

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

  public static WxMpGuideBuyerInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideBuyerInfo.class);
  }
}
