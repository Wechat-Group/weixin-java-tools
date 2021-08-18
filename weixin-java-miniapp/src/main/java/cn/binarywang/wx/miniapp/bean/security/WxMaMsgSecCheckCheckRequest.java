package cn.binarywang.wx.miniapp.bean.security;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liming1019
 */
@Data
@Builder
public class WxMaMsgSecCheckCheckRequest implements Serializable {
  private static final long serialVersionUID = 3233176903681625506L;

  @SerializedName("version")
  private String version;

  @SerializedName("openid")
  private String openid;

  @SerializedName("scene")
  private Integer scene;

  @SerializedName("content")
  private String content;

  @SerializedName("nickname")
  private String nickname;

  @SerializedName("title")
  private String title;

  @SerializedName("signature")
  private String signature;
}
