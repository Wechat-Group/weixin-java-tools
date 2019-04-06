package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

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



}
