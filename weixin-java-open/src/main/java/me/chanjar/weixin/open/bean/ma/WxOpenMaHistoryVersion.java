package me.chanjar.weixin.open.bean.ma;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;

/**
 * 微信开放平台小程序 可回退的小程序版本
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
@Data
public class WxOpenMaHistoryVersion implements Serializable {
  private static final long serialVersionUID = 98923601148793365L;

  @SerializedName("app_version")
  private Integer appVersion;

  @SerializedName("user_version")
  private String userVersion;

  @SerializedName("user_desc")
  private String userDesc;

  @SerializedName("commit_time")
  private Integer commitTime;

  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }
}
