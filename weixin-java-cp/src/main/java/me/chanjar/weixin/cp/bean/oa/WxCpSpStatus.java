package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;

/**
 * 审批单状态
 * (1-审批中；2-已通过；3-已驳回；4-已撤销；6-通过后撤销；7-已删除；10-已支付）
 *
 * @author element
 */
public enum WxCpSpStatus {

  /**
   * 审批中
   */
  @SerializedName("1")
  AUDITING(1),
  /**
   * 已通过
   */
  @SerializedName("2")
  PASSED(2),
  /**
   * 已驳回
   */
  @SerializedName("3")
  REJECTED(3),
  /**
   * 已撤销
   */
  @SerializedName("4")
  UNDONE(4),
  /**
   * 通过后撤销
   */
  @SerializedName("6")
  PASS_UNDONE(6),
  /**
   * 已删除
   */
  @SerializedName("7")
  DELETED(7),
  /**
   * 已支付
   */
  @SerializedName("10")
  ALREADY_PAY(10);

  private Integer status;

  private WxCpSpStatus(Integer status) {
    this.status = status;
  }
}
