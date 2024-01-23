package me.chanjar.weixin.cp.bean.oa.doc;

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
public class WxCpDocShare extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5128321625142879581L;

  /**
   * 文档分享链接
   */
  @SerializedName("share_url")
  private String shareUrl;

  /**
   * From json wx cp space share.
   *
   * @param json the json
   * @return the wx cp space share
   */
  public static WxCpDocShare fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDocShare.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
