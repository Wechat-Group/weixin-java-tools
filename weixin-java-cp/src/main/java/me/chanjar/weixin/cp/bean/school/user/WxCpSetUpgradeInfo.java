package me.chanjar.weixin.cp.bean.school.user;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 修改自动升年级的配置 返回结果.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpSetUpgradeInfo extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("next_upgrade_time")
  private Long nextUpgradeTime;

  /**
   * From json wx cp set upgrade info.
   *
   * @param json the json
   * @return the wx cp set upgrade info
   */
  public static WxCpSetUpgradeInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpSetUpgradeInfo.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
