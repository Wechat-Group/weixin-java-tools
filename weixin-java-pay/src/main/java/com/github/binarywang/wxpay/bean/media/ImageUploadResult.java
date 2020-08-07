package com.github.binarywang.wxpay.bean.media;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

/**
 * 媒体文件上传返回结果对象
 * @author zhouyongshen
 */
@NoArgsConstructor
@Data
public class ImageUploadResult {

  public static ImageUploadResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, ImageUploadResult.class);
  }
  /**
   * 媒体文件标识 Id
   *
   * 微信返回的媒体文件标识Id。
   * 示例值：6uqyGjGrCf2GtyXP8bxrbuH9-aAoTjH-rKeSl3Lf4_So6kdkQu4w8BYVP3bzLtvR38lxt4PjtCDXsQpzqge_hQEovHzOhsLleGFQVRF-U_0
   *
   */
  @SerializedName("media_id")
  private String mediaId;
}
