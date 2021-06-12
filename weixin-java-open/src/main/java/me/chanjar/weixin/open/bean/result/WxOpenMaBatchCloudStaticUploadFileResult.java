package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaBatchCloudStaticUploadFileResult extends WxOpenResult {

  private static final long serialVersionUID = 7021875040823865571L;

  /**
   * 上传链接
   */
  @SerializedName("signed_url")
  private String signedUrl;

  /**
   * x-cos-security-token的值
   */
  @SerializedName("token")
  private String token;
}
