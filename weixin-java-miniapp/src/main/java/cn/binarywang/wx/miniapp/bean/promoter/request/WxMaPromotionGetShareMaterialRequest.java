package cn.binarywang.wx.miniapp.bean.promoter.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhuangzibin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaPromotionGetShareMaterialRequest implements Serializable {

  private static final long serialVersionUID = -7420667215630983582L;

  /*
    {
      "path": "xxxxx",
      "openid": "xxxxx",
      "extra_info": "xxxxx",
      "title": "xxxxx",
      "share_type": 0,
      "env_version": "trial"
    }
   */

  /**
   * 小程序页面path
   * 必填
   */
  @SerializedName("path")
  private String path;

  /**
   * 推广员的openid或unionid
   * 必填
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 自定义参数，长度不能超过80个字符
   * 非必填
   */
  @SerializedName("extra_info")
  private String extraInfo;

  /**
   * 页面名称，长度不能超过20个字符，默认为“推广活动页”
   * 非必填
   */
  @SerializedName("title")
  private String title;

  /**
   * 0：三种分享素材全返回 1、短链 2、带参path 3：小程序码（默认全部返回）
   * 非必填
   */
  @SerializedName("share_type")
  private Long shareType;

  /**
   * 默认正式版，develop: 开发版 ， trial: 体验版，仅短链支持跳转开发版/体验版
   * 非必填
   */
  @SerializedName("env_version")
  private String envVersion;
}
