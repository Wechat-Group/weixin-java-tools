package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 审批流程信息
 *
 * @author element
 */
@Data
public class WxCpApprovalRecord implements Serializable {

  private static final long serialVersionUID = -327230786004105887L;

  @SerializedName("sp_status")
  private WxCpRecordSpStatus status;

  @SerializedName("approverattr")
  private WxCpApproverAttr approverAttr;

  private List<WxCpApprovalRecordDetail> details;

}
