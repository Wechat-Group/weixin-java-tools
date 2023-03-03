package me.chanjar.weixin.cp.bean.corpgroup;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * @Project: WxJava
 * @Package: me.chanjar.weixin.cp.bean.corpgroup
 * @Description: 应用类
 * @Author: libo
 * @Email: 422423229@qq.com
 * @Date: 27/2/2023 9:50 PM
 */
@NoArgsConstructor
@Data
public class WxCpCorpGroupCorp implements Serializable {

  private static final long serialVersionUID = 6842919838272832415L;
  @SerializedName("corpid")
  private String corpid;
  @SerializedName("corp_name")
  private String corpName;
  @SerializedName("agentid")
  private Integer agentid;

  public static WxCpCorpGroupCorp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpCorpGroupCorp.class);
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
