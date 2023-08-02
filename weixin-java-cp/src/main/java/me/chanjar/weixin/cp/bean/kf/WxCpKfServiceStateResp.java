package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * The type Wx cp kf service state resp.
 *
 * @author leiin  created on  2022/1/26 5:00 下午
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WxCpKfServiceStateResp extends WxCpBaseResp {

  private static final long serialVersionUID = 8077134413448067090L;
  @SerializedName("service_state")
  private Integer serviceState;
  @SerializedName("servicer_userid")
  private String servicerUserId;

  /**
   * From json wx cp kf service state resp.
   *
   * @param json the json
   * @return the wx cp kf service state resp
   */
  public static WxCpKfServiceStateResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfServiceStateResp.class);
  }
}
