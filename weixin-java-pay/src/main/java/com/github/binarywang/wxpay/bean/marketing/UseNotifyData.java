package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 核销事件回调通知对象
 *
 * @author thinsstar
 */
@NoArgsConstructor
@Data
public class UseNotifyData implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 通知ID
   */
  @SerializedName("id")
  private String id;

  /**
   * 通知创建时间
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 通知类型
   */
  @SerializedName("event_type")
  private String eventType;

  /**
   * 通知数据类型
   */
  @SerializedName("resource_type")
  private String resourceType;

  /**
   * 回调摘要
   * summary
   */
  @SerializedName("summary")
  private String summary;

  /**
   * 通知数据
   */
  @SerializedName("resource")
  private Resource resource;

  @Data
  public static class Resource implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 加密算法类型
     */
    @SerializedName("algorithm")
    private String algorithm;

    /**
     * 数据密文
     */
    @SerializedName("ciphertext")
    private String cipherText;

    /**
     * 附加数据
     */
    @SerializedName("associated_data")
    private String associatedData;

    /**
     * 随机串
     */
    @SerializedName("nonce")
    private String nonce;

    /**
     * 原始回调类型
     */
    @SerializedName("original_type")
    private String originalType;
  }
}
