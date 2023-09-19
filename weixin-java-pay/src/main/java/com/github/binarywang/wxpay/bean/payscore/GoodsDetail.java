package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 优惠商品信息
 **/
@Data
@NoArgsConstructor
public class GoodsDetail implements Serializable {

  private static final long serialVersionUID = 7139782546598279686L;
  @SerializedName("goods_id")
  private String goodsId;
  @SerializedName("quantity")
  private Integer  quantity;
  @SerializedName("unit_price")
  private Integer unitPrice;
  @SerializedName("discount_amount")
  private Integer  discountAmount;
  @SerializedName("goods_remark")
  private String goodsRemark;
}
