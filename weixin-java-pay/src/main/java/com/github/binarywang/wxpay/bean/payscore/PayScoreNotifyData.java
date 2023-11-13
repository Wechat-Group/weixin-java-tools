package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信支付分确认订单跟支付回调对象
 *
 * @author doger.wang
 * created on  2020/5/14 12:18
 */
@NoArgsConstructor
@Data
public class PayScoreNotifyData implements Serializable {
  private static final long serialVersionUID = -8538014389773390989L;

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
   * <p>1、授权成功通知的类型为PAYSCORE.USER_OPEN_SERVICE</p>
   * <p>2、解除授权成功通知的类型为PAYSCORE.USER_CLOSE_SERVICE</p>
   * <p>3、用户确认成功通知的类型为PAYSCORE.USER_CONFIRM</p>
   * <p>4、支付成功通知的类型为PAYSCORE.USER_PAID</p>
   * <p>5、取消签约成功通知类型为PAYSCORE.USER_CANCEL_SIGN_PLAN</p>
   * <p>6、签约计划成功通知类型为PAYSCORE.USER_SIGN_PLAN</p>
   */
  @SerializedName("event_type")
  private String eventType;

  /**
   * 通知数据类型
   */
  @SerializedName("resource_type")
  private String resourceType;


  /**
   * 通知数据
   */
  @SerializedName("resource")
  private Resource resource;

  /**
   * 回调摘要
   * summary
   */
  @SerializedName("summary")
  private String summary;

  @Data
  public static class Resource implements Serializable {
    private static final long serialVersionUID = 8530711804335261449L;
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
    @SerializedName("nonce")
    private String nonce;

    /**
     * 随机串
     */
    @SerializedName("associated_data")
    private String associatedData;

    /**
     * 原始回调类型，支付分的原始回调类型为payscore
     */
    @SerializedName("original_type")
    private String originalType;
  }
}
