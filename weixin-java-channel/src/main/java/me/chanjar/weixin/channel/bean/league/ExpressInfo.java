package me.chanjar.weixin.channel.bean.league;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 物流信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class ExpressInfo implements Serializable {

  private static final long serialVersionUID = -4604691645808459334L;
  /** 发货时间期限 */
  @JsonProperty("send_time")
  private String sendTime;

  /** 发货地址 */
  @JsonProperty("address_info")
  private AddressInfo addressInfo;

  /** 计费方式：FREE：包邮CONDITION_FREE：条件包邮NO_FREE：不包邮 */
  @JsonProperty("shipping_method")
  private String shippingMethod;

}
