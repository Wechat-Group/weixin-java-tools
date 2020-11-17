package com.github.binarywang.wxpay.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 营销详情	.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-06-07
 */
@Data
public class WxPayRefundPromotionDetail implements Serializable {
  private static final long serialVersionUID = 2197712244944584263L;

  /**
   * 字段名：券ID
   * 变量名：promotion_id
   * 是否必填：是
   * 类型：String(32)
   * 示例例：109519
   * 描述：券或者立减优惠id
   */
  @SerializedName("promotion_id")
  private String promotionId;
  /**
   * 字段名：优惠范围
   * 变量名：scope
   * 是否必填：是
   * 类型：String(32)
   * 示例例：SINGLE
   * 描述：GLOBAL- 全场代金券，SINGLE- 单品优惠
   */
  @SerializedName("scope")
  private String scope;
  /**
   * 字段名：优惠类型
   * 变量名：type
   * 是否必填：是
   * 类型：String(32)
   * 示例例：DISCOUNT
   * 描述：COUPON- 代金券，需要走结算资金的充值型代金券,（境外商户券币种与支付币种一致），DISCOUNT- 优惠券，不走结算资金的免充值型优惠券，（境外商户券币种与标价币种一致
   */
  @SerializedName("type")
  private String type;
  /**
   * 字段名：代金券退款金额
   * 变量名：refund_amount
   * 是否必填：是
   * 类型：Int
   * 示例例：100
   * 描述：代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
   */
  @SerializedName("refund_amount")
  private Integer refundAmount;
  /**
   * 字段名：商品列表
   * 变量名：goods_detail
   * 是否必填：否
   * 类型：String
   * 示例例：见下文
   * 描述：商品信息，使用Json格式
   */
  @SerializedName("goods_detail")
  private List<GoodDetail> goodsDetails;

  @Data
  public static class GoodDetail {
    /**
     * 字段名：商品编码
     * 变量名：goods_id
     * 是否必填：是
     * 类型：String(32)
     * 示例值：商品编码
     * 描述：由半角的大小写字母、数字、中划线、下划线中的一种或几种组成
     */
    @SerializedName("goods_id")
    private String goodsId;

    /**
     * 字段名：优惠退款金额
     * 变量名：refund_amount
     * 是否必填：是
     * 类型：int
     * 示例值：528800
     * 描述：优惠退款金额
     */
    @SerializedName("refund_amount")
    private Integer refundAmount;

    /**
     * 字段名：商品退货数量
     * 变量名：refund_quantity
     * 是否必填：是
     * 类型：int
     * 示例值：1
     * 描述：单品的退货数量
     */
    @SerializedName("refund_quantity")
    private Integer refundQuantity;

    /**
     * 字段名：商品单价
     * 变量名：price
     * 是否必填：是
     * 类型：int
     * 示例值：528800
     * 描述：单位为：分。如果商户有优惠，需传输商户优惠后的单价(例如：用户对一笔100元的订单使用了商场发的优惠券100-50，则活动商品的单价应为原单价-50)
     */
    @SerializedName("price")
    private Integer price;

  }
}
