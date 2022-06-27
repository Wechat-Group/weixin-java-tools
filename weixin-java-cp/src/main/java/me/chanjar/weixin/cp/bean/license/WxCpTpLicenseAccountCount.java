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
 * @date 2022/6/27 11:54
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpTpLicenseAccountCount implements Serializable {
  private static final long serialVersionUID = 8521389670723004989L;

  @SerializedName("base_count")
  private Integer baseCount;
  @SerializedName("external_contact_count")
  private Integer externalContactCount;

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
