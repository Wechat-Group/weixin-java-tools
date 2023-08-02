package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenRegisterBetaWeappResult extends WxOpenResult {
  @SerializedName("authorize_url")
  private String authorizeUrl;
  @SerializedName("unique_id")
  protected String uniqueId;
}
