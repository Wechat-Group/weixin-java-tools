package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 获取邀请链接.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpSpaceShare extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("space_share_url")
  private String spaceShareUrl;

  public static WxCpSpaceShare fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpSpaceShare.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
