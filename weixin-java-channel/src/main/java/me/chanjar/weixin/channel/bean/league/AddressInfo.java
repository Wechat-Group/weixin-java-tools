package me.chanjar.weixin.channel.bean.league;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 地址信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class AddressInfo implements Serializable {

  private static final long serialVersionUID = -5719456688033731919L;
  /** 邮编 */
  @JsonProperty("postal_code")
  private String postalCode;

  /** 国标收货地址第一级地址 */
  @JsonProperty("province_name")
  private String provinceName;

  /** 国标收货地址第二级地址 */
  @JsonProperty("city_name")
  private String cityName;

  /** 国标收货地址第三级地址 */
  @JsonProperty("county_name")
  private String countyName;
}
