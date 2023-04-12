package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 团长余额响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SupplierBalanceResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 5584817726976222436L;
  /** 可提现余额 */
  @JsonProperty("available_amount")
  private Integer availableAmount;

  /** 待结算余额 */
  @JsonProperty("pending_amount")
  private Integer pendingAmount;
}
