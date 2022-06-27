package me.chanjar.weixin.cp.bean.license;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * @author Totoro
 * @date 2022/6/27 11:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpTpLicenseSimpleOrder implements Serializable {

  private static final long serialVersionUID = -4094302825442292644L;

  @SerializedName("order_id")
  private String orderId;
  @SerializedName("order_type")
  private Integer orderType;

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
