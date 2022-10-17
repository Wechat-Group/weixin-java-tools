package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * The type Wx cp kf msg send resp.
 *
 * @author leiin  created on  2022/1/26 7:41 下午
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WxCpKfMsgSendResp extends WxCpBaseResp {
  @SerializedName("msgid")
  private String msgId;

  /**
   * From json wx cp kf msg send resp.
   *
   * @param json the json
   * @return the wx cp kf msg send resp
   */
  public static WxCpKfMsgSendResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfMsgSendResp.class);
  }
}
