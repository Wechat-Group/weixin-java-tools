package me.chanjar.weixin.cp.bean.corpgroup;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 获取下级/下游企业小程序session返回类
 *
 * @author libo
 */
@Data
public class WxCpMaTransferSession implements Serializable {

  private static final long serialVersionUID = 4189407986285166516L;
  @SerializedName("userid")
  private String userId;
  @SerializedName("session_key")
  private String sessionKey;


  /**
   * From json WxCpMaTransferSession.
   *
   * @param json the json
   * @return the WxCpMaTransferSession
   */
  public static WxCpMaTransferSession fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpMaTransferSession.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
