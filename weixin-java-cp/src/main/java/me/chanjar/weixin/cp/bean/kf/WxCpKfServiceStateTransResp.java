package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * The type Wx cp kf service state trans resp.
 *
 * @author leiin  created on  2022/1/26 5:03 下午
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WxCpKfServiceStateTransResp extends WxCpBaseResp {

  private static final long serialVersionUID = -7874378445629022791L;

  @SerializedName("msg_code")
  private String msgCode;

  /**
   * From json wx cp kf service state trans resp.
   *
   * @param json the json
   * @return the wx cp kf service state trans resp
   */
  public static WxCpKfServiceStateTransResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfServiceStateTransResp.class);
  }
}
