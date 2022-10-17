package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 获取分享链接返回信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpFileShare extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("share_url")
  private String shareUrl;

  /**
   * From json wx cp file share.
   *
   * @param json the json
   * @return the wx cp file share
   */
  public static WxCpFileShare fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFileShare.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
