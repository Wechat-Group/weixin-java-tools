package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 审批节点详情
 * @author element
 */
@Data
public class WxCpApprovalRecordDetail implements Serializable {

  private static final long serialVersionUID = -9142079764088495301L;

  /**
   * 分支审批人
   */
  @SerializedName("approver")
  private WxCpOperator approver;

  /**
   * 审批意见
   */
  @SerializedName("speech")
  private String speech;

  /**
   * 分支审批人审批状态
   */
  @SerializedName("sp_status")
  private WxCpRecordSpStatus spStatus;

  /**
   * 节点分支审批人审批操作时间戳，0表示未操作
   */
  @SerializedName("sptime")
  private Long spTime;

  /**
   * 节点分支审批人审批意见附件
   */
  @SerializedName("media_id")
  private List<String> mediaIds;

}
