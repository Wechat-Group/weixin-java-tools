package cn.binarywang.wx.miniapp.bean.code;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 查询小程序版本信息
 *
 * @author <a href="https://github.com/leonxi">LeonXi</a>
 * @since 2022-04-13 16:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMaCodeVersionInfo implements Serializable {

  private static final long serialVersionUID = 6929700728659511688L;

  /**
   * 体验版信息
   */
  @SerializedName("exp_info")
  private ExpInfo expInfo;

  /**
   * 线上版信息
   */
  @SerializedName("release_info")
  private ReleaseInfo releaseInfo;

  public static WxMaCodeVersionInfo fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaCodeVersionInfo.class);
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ExpInfo implements Serializable {

    private static final long serialVersionUID = 6315578419554592943L;

    /**
     * 提交体验版的时间
     */
    @SerializedName("exp_time")
    private Long expTime;

    /**
     * 体验版版本信息
     */
    @SerializedName("exp_version")
    private String expVersion;

    /**
     * 体验版版本描述
     */
    @SerializedName("exp_desc")
    private String expDesc;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ReleaseInfo implements Serializable {

    private static final long serialVersionUID = 2098307354673939939L;

    /**
     * 发布线上版的时间
     */
    @SerializedName("release_time")
    private Long releaseTime;

    /**
     * 线上版版本信息
     */
    @SerializedName("release_version")
    private String releaseVersion;

    /**
     * 线上版本描述
     */
    @SerializedName("release_desc")
    private String releaseDesc;
  }
}
