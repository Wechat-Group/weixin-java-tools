package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.AddressInfo;

/**
 * 地址信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderAddressInfo extends AddressInfo {

  private static final long serialVersionUID = 3062707865189774795L;
  /** 虚拟发货订单联系方式(deliver_method=1时返回) */
  @JsonProperty("virtual_order_tel_number")
  private String virtualOrderTelNumber;
}
