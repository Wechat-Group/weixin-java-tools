package me.chanjar.weixin.cp.bean.corpgroup;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * @Project: WxJava
 * @Package: me.chanjar.weixin.cp.bean.corpgroup
 * @Description: 获取下级/下游企业的access_token返回类
 * @Author: libo
 * @Email: 422423229@qq.com
 * @Date: 27/2/2023 9:07 PM
 */
@Data
public class WxCpCorpGroupCorpToken implements Serializable {
  private static final long serialVersionUID = -8139617060677460515L;
  @SerializedName("access_token")
  private String accessToken;
  @SerializedName("expires_in")
  private int expiresIn;

  public static WxCpCorpGroupCorpToken fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpCorpGroupCorpToken.class);
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
