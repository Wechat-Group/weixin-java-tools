package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

/**
 * 交易账单结果
 *
 * @author: f00lish
 * @date: 2020/09/28
 */
@Data
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TradeBillResult implements Serializable {
  private static final long serialVersionUID = 6928601792206656068L;

  /**
   * <pre>
   * 字段名：哈希类型
   * 变量名：hash_type
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  原始账单（gzip需要解压缩）的摘要值，用于校验文件的完整性。
   *  示例值：SHA1
   * </pre>
   */
  @SerializedName(value = "hash_type")
  private String hashType;

  /**
   * <pre>
   * 字段名：哈希值
   * 变量名：hash_value
   * 是否必填：是
   * 类型：string（1024）
   * 描述：
   *  原始账单（gzip需要解压缩）的摘要值，用于校验文件的完整性。
   *  示例值：79bb0f45fc4c42234a918000b2668d689e2bde04
   * </pre>
   */
  @SerializedName(value = "hash_value")
  private String hashValue;

  /**
   * <pre>
   * 字段名：账单下载地址
   * 变量名：download_url
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  供下一步请求账单文件的下载地址，该地址30s内有效。
   *  示例值：https://api.mch.weixin.qq.com/v3/billdownload/file?token=xxx
   * </pre>
   */
  @SerializedName(value = "download_url")
  private String downloadUrl;

}
