package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 创建代金券批次返回结果对象
 *
 * @author thinsstar
 */
@NoArgsConstructor
@Data
public class FavorStocksCreateResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 批次号
   * <p>
   * 微信为每个代金券批次分配的唯一ID。
   * 示例值：98065001
   */
  @SerializedName("stock_id")
  private String stockId;
}
