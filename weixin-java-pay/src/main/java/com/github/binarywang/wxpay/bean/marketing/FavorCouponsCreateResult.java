package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 发放代金券返回结果对象
 *
 * @author thinsstar
 */
@NoArgsConstructor
@Data
public class FavorCouponsCreateResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 代金券id
   * <p>
   * 发放给用户的代金券id。
   * 示例值：9867041
   */
  @SerializedName("coupon_id")
  private String couponId;
}
