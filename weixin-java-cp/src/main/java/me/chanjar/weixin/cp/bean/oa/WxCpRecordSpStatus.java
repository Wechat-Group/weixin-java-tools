package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;

/**
 * 审批记录(节点)分支审批状态
 *
 * 1-审批中；2-已同意；3-已驳回；4-已转审
 *
 * @author element
 */
public enum WxCpRecordSpStatus {

  /**
   * 审批中
   */
  @SerializedName("1")
  AUDITING(1),
  /**
   * 已同意
   */
  @SerializedName("2")
  PASSED(2),
  /**
   * 已驳回
   */
  @SerializedName("3")
  REJECTED(3),
  /**
   * 已转审
   */
  @SerializedName("4")
  TURNED(4);

  private Integer status;

  private WxCpRecordSpStatus(Integer status) {
    this.status = status;
  }

}
