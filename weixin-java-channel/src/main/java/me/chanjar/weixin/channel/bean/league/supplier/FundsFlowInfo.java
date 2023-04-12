package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 团长资金流水详情
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class FundsFlowInfo implements Serializable {

  private static final long serialVersionUID = 102705878551490327L;
  /** 流水id */
  @JsonProperty("flow_id")
  private String flowId;

  /** 资金类型, 1提现 2分账 */
  @JsonProperty("funds_type")
  private Integer fundsType;

  /** 流水金额 单位：分 */
  @JsonProperty("amount")
  private Integer amount;

  /** 余额 单位：分 */
  @JsonProperty("balance")
  private Integer balance;

  /** 记账时间 */
  @JsonProperty("bookkeeping_time")
  private String bookkeepingTime;

  /** 备注 */
  @JsonProperty("remark")
  private String remark;

  /** 关联订单号 */
  @JsonProperty("order_id")
  private String orderId;

  /** 关联提现单号 */
  @JsonProperty("withdraw_id")
  private String withdrawId;

}
