package me.chanjar.weixin.open.bean.shoppingOrders;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenShoppingOrdersConfirmResult extends WxOpenResult {
  private static final long serialVersionUID = 8534868743462740891L;
  /**
   * 最近一次审核的结果
   */
  @SerializedName("last_result")
  private String lastResult;
}
