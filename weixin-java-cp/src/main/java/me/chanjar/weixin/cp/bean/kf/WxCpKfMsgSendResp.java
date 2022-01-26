package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * @author leiin
 * @date 2022/1/26 7:41 下午
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WxCpKfMsgSendResp extends WxCpBaseResp {
  @SerializedName("msgid")
  private String msgId;

  public static WxCpKfMsgSendResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfMsgSendResp.class);
  }
}
