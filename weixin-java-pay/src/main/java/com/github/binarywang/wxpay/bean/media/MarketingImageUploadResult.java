package com.github.binarywang.wxpay.bean.media;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

/**
 * 媒体文件上传返回结果对象
 *
 * @author thinsstar
 */
@NoArgsConstructor
@Data
public class MarketingImageUploadResult {

  public static MarketingImageUploadResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, MarketingImageUploadResult.class);
  }

  /**
   * 媒体文件URL地址
   * <p>
   * 微信返回的媒体文件标识url。有效期为永久
   * 示例值：https://qpic.cn/xxx
   */
  @SerializedName("media_url")
  private String mediaUrl;
}
