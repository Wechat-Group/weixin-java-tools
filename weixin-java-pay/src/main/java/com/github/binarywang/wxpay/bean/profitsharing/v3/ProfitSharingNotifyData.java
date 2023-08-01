package com.github.binarywang.wxpay.bean.profitsharing.v3;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * 微信V3接口
 * 通用通知实体
 *
 * @author yuanbo
 * @since 2022-04-26-21:33 PM
 */
@Data
@NoArgsConstructor
public class ProfitSharingNotifyData implements Serializable{
  private static final long serialVersionUID = 4242383310854692441L;

  /**
   * <pre>
   * 字段名：通知ID
   * 是否必填：是
   * 描述：通知的唯一ID
   * </pre>
   */
  @SerializedName("id")
  private String id;

  /**
   * <pre>
   * 字段名：通知创建时间
   * 是否必填：是
   * 描述：通知创建的时间，Rfc3339标准
   * </pre>
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * <pre>
   * 字段名：通知数据类型
   * 是否必填：是
   * 描述：通知的资源数据类型
   * </pre>
   */
  @SerializedName("resource_type")
  private String resourceType;

  /**
   * <pre>
   * 字段名：通知类型
   * 是否必填：是
   * 描述：通知的类型
   * </pre>
   */
  @SerializedName("event_type")
  private String eventType;

  /**
   * <pre>
   * 字段名：通知数据
   * 是否必填：是
   * 描述：通知资源数据
   * </pre>
   */
  @SerializedName("resource")
  private Resource resource;

  /**
   * <pre>
   * 字段名：通知简要说明
   * 是否必填：是
   * 描述：通知简要说明
   * </pre>
   */
  @SerializedName("summary")
  private String summary;

  @Data
  @NoArgsConstructor
  public static class Resource implements Serializable {

    private static final long serialVersionUID = 8530711804335261449L;


    /**
     * <pre>
     * 字段名：加密算法类型
     * 是否必填：是
     * 描述：对分账结果数据进行加密的加密算法，目前只支持AEAD_AES_256_GCM
     * </pre>
     */
    @SerializedName("algorithm")
    private String algorithm;


    /**
     * <pre>
     * 字段名：加密前的对象类型
     * 是否必填：是
     * 描述：加密前的对象类型，分账动账通知的类型为profitsharing
     * </pre>
     */
    @SerializedName("original_type")
    private String originalType;

    /**
     * <pre>
     * 字段名：数据密文
     * 是否必填：是
     * 描述：Base64编码后的分账结果数据密文
     * </pre>
     */
    @SerializedName("ciphertext")
    private String cipherText;

    /**
     * <pre>
     * 字段名：随机串
     * 是否必填：是
     * 描述：加密使用的随机串
     * </pre>
     */
    @SerializedName("nonce")
    private String nonce;

    /**
     * <pre>
     * 字段名：附加数据
     * 是否必填：否
     * 描述：附加数据
     * </pre>
     */
    @SerializedName("associated_data")
    private String associatedData;
  }
}
