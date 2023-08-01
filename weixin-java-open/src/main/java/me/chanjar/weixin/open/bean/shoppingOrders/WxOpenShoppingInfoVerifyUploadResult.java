package me.chanjar.weixin.open.bean.shoppingOrders;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenShoppingInfoVerifyUploadResult extends WxOpenResult {

  private static final long serialVersionUID = -3223834939130803964L;
  /**
   * 验证结果
   */
  @SerializedName("verify_result")
  private String verifyResult;
}
