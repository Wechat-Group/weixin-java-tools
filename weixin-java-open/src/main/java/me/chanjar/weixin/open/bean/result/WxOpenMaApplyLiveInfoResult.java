package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 清心
 * created at 2022-10-04 16:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxOpenMaApplyLiveInfoResult extends WxOpenResult{

  @SerializedName("action")
  private String action;

}
