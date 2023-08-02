package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenRegisterPersonalWeappResult extends WxOpenResult {
  private String taskid;
  @SerializedName("authorize_url")
  private String authorizeUrl;
  @SerializedName("status")
  private Integer status;
}
