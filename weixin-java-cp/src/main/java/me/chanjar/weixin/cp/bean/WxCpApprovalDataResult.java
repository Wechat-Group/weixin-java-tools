package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Element
 * @Package me.chanjar.weixin.cp.bean
 * @date 2019-04-06 14:36
 * @Description: 企业微信 OA 审批数据
 */
@Data
public class WxCpApprovalDataResult implements Serializable {
  private static final long serialVersionUID = -1046940445840716590L;

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  private Integer count;

  private Integer total;

  @SerializedName("next_spnum")
  private Long nextSpnum;

  private WxCpApprovalData[] data;


  @Data
  public static class WxCpApprovalData implements Serializable{

    private static final long serialVersionUID = -3051785319608491640L;

    private String spname;

    @SerializedName("apply_name")
    private String applyName;

    @SerializedName("apply_org")
    private String applyOrg;

    @SerializedName("approval_name")
    private String[] approvalName;

    @SerializedName("notify_name")
    private String[] notifyName;

    @SerializedName("sp_status")
    private Integer spStatus;

    @SerializedName("sp_num")
    private Long spNum;

    @SerializedName("apply_time")
    private Long applyTime;

    @SerializedName("apply_user_id")
    private String applyUserId;

    @SerializedName("comm")
    private Map<String,String> comm;
  }
}
