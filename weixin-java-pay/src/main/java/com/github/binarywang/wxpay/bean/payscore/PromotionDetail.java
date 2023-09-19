package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 优惠详情
 **/
@Data
@NoArgsConstructor
public class PromotionDetail implements Serializable {

  private static final long serialVersionUID = -4405156288317582934L;

  @SerializedName("coupon_id")
  private String couponId;
  @SerializedName("name")
  private String name;
  @SerializedName("scope")
  private String scope;
  @SerializedName("type")
  private String type;
  @SerializedName("amount")
  private Integer amount;
  @SerializedName("stock_id")
  private String stockId;
  @SerializedName("wechatpay_contribute")
  private Integer wechatpayContribute;
  @SerializedName("merchant_contribute")
  private Integer merchantContribute;
  @SerializedName("other_contribute")
  private Integer otherContribute;
  @SerializedName("currency")
  private String currency;
  @SerializedName("goods_detail")
  private List<GoodsDetail> goodsDetail;
}
