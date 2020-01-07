package me.chanjar.weixin.cp.bean.oa;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author element
 */
@Data
public class WxCpApprovalInfo implements Serializable {

  private static final long serialVersionUID = 7387181805254287167L;

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("sp_no_list")
  private List<String> spNoList;

  @SerializedName("next_cursor")
  private Integer nextCursor;

}
