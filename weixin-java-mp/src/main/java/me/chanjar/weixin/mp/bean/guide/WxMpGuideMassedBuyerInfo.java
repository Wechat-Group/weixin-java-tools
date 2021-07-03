package me.chanjar.weixin.mp.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 下方客户状态信息
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/13/013
 */

@Data
public class WxMpGuideMassedBuyerInfo implements ToJson, Serializable {
  private static final long serialVersionUID = -7433816414896345471L;

  /**
   * 客户openid
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 消息发送状态(1.未发送 2.发送成功 3.未关注公众号 4.没有quota(没有发送机会) 5.系统错误)
   */
  @SerializedName("send_status")
  private int sendStatus;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideMassedBuyerInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideMassedBuyerInfo.class);
  }
}
