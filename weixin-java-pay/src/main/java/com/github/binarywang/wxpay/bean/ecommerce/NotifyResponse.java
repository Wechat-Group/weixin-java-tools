package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通知数据
 */
@Data
@NoArgsConstructor
public class NotifyResponse implements Serializable {
  private static final long serialVersionUID = 341873114458149365L;
  @SerializedName(value = "id")
  private String id;

  @SerializedName(value = "create_time")
  private String createTime;

  @SerializedName(value = "event_type")
  private String eventType;

  @SerializedName(value = "resource_type")
  private String resourceType;

  @SerializedName(value = "resource")
  private Resource resource;

  @SerializedName(value = "summary")
  private String summary;

  @Data
  @NoArgsConstructor
  public static class Resource implements Serializable {

    @SerializedName(value = "algorithm")
    private String algorithm;

    @SerializedName(value = "ciphertext")
    private String ciphertext;

    @SerializedName(value = "associated_data")
    private String associatedData;

    @SerializedName(value = "nonce")
    private String nonce;
  }

}
