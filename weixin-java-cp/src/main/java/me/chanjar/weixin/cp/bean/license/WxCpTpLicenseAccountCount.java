package me.chanjar.weixin.cp.bean.license;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * The type Wx cp tp license account count.
 *
 * @author Totoro  created on  2022/6/27 11:54
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

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
