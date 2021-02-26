package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

/**
 * 获取下载结果对象
 *
 * @author thinsstar
 */
@NoArgsConstructor
@Data
public class FavorStocksFlowGetResult {

  public static FavorStocksFlowGetResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, FavorStocksFlowGetResult.class);
  }

  /**
   * 下载链接
   * <p>
   * 流水文件下载链接，30s内有效。
   * 示例值：download://example.csv
   */
  @SerializedName("url")
  private String url;

  /**
   * 安全校验码
   * <p>
   * 文件内容的哈希值，防止篡改。
   * 示例值：8ae0eb442c408d2e90d669d6f4ad6b7e6e049d6f
   */
  @SerializedName("hash_value")
  private String hashValue;

  /**
   * 哈希算法类型
   * <p>
   * 哈希算法类型，目前只支持SHA1。
   * 示例值：SHA1
   */
  @SerializedName("hash_type")
  private String hashType;
}
