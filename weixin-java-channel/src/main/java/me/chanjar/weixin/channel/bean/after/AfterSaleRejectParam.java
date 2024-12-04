package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 售后单拒绝信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@JsonInclude(Include.NON_NULL)
public class AfterSaleRejectParam extends AfterSaleIdParam {

  private static final long serialVersionUID = -7507483859864253314L;
  /**
   * 拒绝原因
   */
  @JsonProperty("reject_reason")
  private String rejectReason;

  /**
   * 拒绝原因枚举值
   */
  @JsonProperty("reject_reason_type")
  private Integer rejectReasonType;

  public AfterSaleRejectParam() {
  }

  public AfterSaleRejectParam(String afterSaleOrderId, String rejectReason) {
    super(afterSaleOrderId);
    this.rejectReason = rejectReason;
  }

  public AfterSaleRejectParam(String afterSaleOrderId, String rejectReason, Integer rejectReasonType) {
    super(afterSaleOrderId);
    this.rejectReason = rejectReason;
    this.rejectReasonType = rejectReasonType;
  }
}
