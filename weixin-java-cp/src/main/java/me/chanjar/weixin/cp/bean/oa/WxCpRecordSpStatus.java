package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审批记录(节点)分支审批状态
 * <p>
 * 1-审批中；2-已同意；3-已驳回；4-已转审；11-已退回
 *
 * @author element
 */
@AllArgsConstructor
@Getter
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
  TURNED(4),
  /**
   * 已退回
   */
  @SerializedName("11")
  WITHDRAWN(11),
  /**
   * 12-已加签
   */
  @SerializedName("12")
  SIGNED(12),
  /**
   * 13-已同意并加签
   */
  @SerializedName("13")
  PASSEDANDSIGNED(13);

  private final Integer status;

}
