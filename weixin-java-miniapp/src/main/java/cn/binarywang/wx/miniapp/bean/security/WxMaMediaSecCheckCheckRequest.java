package cn.binarywang.wx.miniapp.bean.security;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dingxw
 * @date 2021/11/18 20:27
 */
@Data
@Builder
public class WxMaMediaSecCheckCheckRequest implements Serializable {

  private static final long serialVersionUID = -3947838615379224577L;

  @SerializedName("media_url")
  private String mediaUrl;

  @SerializedName("media_type")
  private Integer mediaType;

  @SerializedName("version")
  private Integer version;

  @SerializedName("openid")
  private String openid;

  @SerializedName("scene")
  private Integer scene;

}
