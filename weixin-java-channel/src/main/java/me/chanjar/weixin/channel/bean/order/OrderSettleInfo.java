package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 结算信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class OrderSettleInfo implements Serializable {

  private static final long serialVersionUID = 2140632631448343656L;
  /** 预计技术服务费（单位为分） */
  @JsonProperty("predict_commission_fee")
  private Integer predictCommissionFee;

  /** 实际技术服务费（单位为分）（未结算时本字段为空） */
  @JsonProperty("commission_fee")
  private Integer commissionFee;

}
