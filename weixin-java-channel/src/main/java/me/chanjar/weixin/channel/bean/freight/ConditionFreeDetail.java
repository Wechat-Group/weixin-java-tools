package me.chanjar.weixin.channel.bean.freight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 计费规则
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConditionFreeDetail extends AddressInfoList {

  private static final long serialVersionUID = 9204578767029379142L;
  /** 最低件数 */
  @JsonProperty("min_piece")
  private Integer minPiece;

  /** 最低重量 */
  @JsonProperty("min_weight")
  private Double minWeight;

  /** 最低金额 */
  @JsonProperty("min_amount")
  private Integer minAmount;

  /** 计费方式对应的选项是否已设置 */
  @JsonProperty("valuation_flag")
  private Integer valuationFlag;

  /** 金额是否设置 */
  @JsonProperty("amount_flag")
  private Integer amountFlag;
}
