package com.github.binarywang.wxpay.bean.notify;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 投诉通知.
 * 文档见：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter10_2_16.shtml
 *
 * @author <a href="https://gitee.com/jeequan/jeepay">jmdhappy</a>
 */
@Data
@NoArgsConstructor
public class ComplaintNotifyResult implements Serializable {
  private static final long serialVersionUID = -1L;
  /**
   * 源数据
   */
  private OriginNotifyResponse rawData;
  /**
   * 解密后的数据
   */
  private DecryptNotifyResult result;

  @Data
  @NoArgsConstructor
  public static class DecryptNotifyResult implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * <pre>
     * 字段名：投诉单号
     * 是否必填：是
     * 描述：
     *  投诉单对应的投诉单号
     * </pre>
     */
    @SerializedName(value = "complaint_id")
    private String complaintId;

    /**
     * <pre>
     * 字段名：动作类型
     * 是否必填：是
     * 描述：
     * 触发本次投诉通知回调的具体动作类型，枚举如下：
     * CREATE_COMPLAINT：用户提交投诉
     * CONTINUE_COMPLAINT：用户继续投诉
     * USER_RESPONSE：用户新留言
     * RESPONSE_BY_PLATFORM：平台新留言
     * SELLER_REFUND：收款方全额退款
     * MERCHANT_RESPONSE：商户新回复
     * MERCHANT_CONFIRM_COMPLETE：商户反馈处理完成
     * </pre>
     */
    @SerializedName(value = "action_type")
    private String actionType;

  }

}
