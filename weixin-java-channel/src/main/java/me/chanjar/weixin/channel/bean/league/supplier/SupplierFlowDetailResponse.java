package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 团长流水明细响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SupplierFlowDetailResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -3962482396458765234L;
  /** 流水信息 */
  @JsonProperty("funds_flow")
  private FundsFlowInfo fundsFlow;
}
