package me.chanjar.weixin.channel.bean.after;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 售后单同意信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@JsonInclude(Include.NON_NULL)
public class AfterSaleAcceptParam extends AfterSaleIdParam {

  private static final long serialVersionUID = -4352801757159074950L;
  /** 同意退货时传入地址id */
  @JsonProperty("address_id")
  private String addressId;

  public AfterSaleAcceptParam() {
  }

  public AfterSaleAcceptParam(String afterSaleOrderId, String addressId) {
    super(afterSaleOrderId);
    this.addressId = addressId;
  }
}
