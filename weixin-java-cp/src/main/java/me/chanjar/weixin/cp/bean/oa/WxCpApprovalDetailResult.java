package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 审批申请详情响应结果
 *
 * @author element
 */
@Data
public class WxCpApprovalDetailResult implements Serializable {
  private static final long serialVersionUID = 3909779949756252918L;

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("info")
  private WxCpApprovalDetail info;

}
