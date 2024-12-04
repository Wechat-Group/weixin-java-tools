package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 核销事件回调内容
 *
 * @author thinsstar
 */
@NoArgsConstructor
@Data
public class FavorCouponsUseResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 创建批次的商户号
   * <p>
   * 批次创建方商户号
   * 示例值：9800064
   */
  @SerializedName("stock_creator_mchid")
  private String stockCreatorMchid;

  /**
   * 批次号
   * <p>
   * 微信为每个代金券批次分配的唯一id。
   * 示例值：9865888
   */
  @SerializedName("stock_id")
  private String stockId;

  /**
   * 代金券id
   * <p>
   * 微信为代金券唯一分配的id。
   * 示例值：98674556
   */
  @SerializedName("coupon_id")
  private String couponId;

  /**
   * 单品优惠特定信息
   * <p>
   * 单品优惠特定信息。
   */
  @SerializedName("singleitem_discount_off")
  private SingleitemDiscountOff singleitemDiscountOff;

  /**
   * 减至优惠特定信息
   * <p>
   * 减至优惠限定字段，仅减至优惠场景有返回。
   */
  @SerializedName("discount_to")
  private DiscountTo discountTo;

  /**
   * 代金券名称
   * <p>
   * 代金券名称
   * 示例值：微信支付代金券
   */
  @SerializedName("coupon_name")
  private String couponName;

  /**
   * 代金券状态
   * <p>
   * 代金券状态：
   * SENDED：可用
   * USED：已实扣
   * EXPIRED：已过期
   * 示例值：EXPIRED
   */
  @SerializedName("status")
  private String status;

  /**
   * 使用说明
   * <p>
   * 代金券描述说明字段。
   * 示例值：微信支付营销
   */
  @SerializedName("description")
  private String description;

  /**
   * 领券时间
   * <p>
   * 领券时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
   * 示例值: 2015-05-20T13:29:35.120+08:00
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 券类型
   * <p>
   * 券类型：
   * NORMAL：满减券
   * CUT_TO：减至券
   * 示例值：CUT_TO
   */
  @SerializedName("coupon_type")
  private String couponType;

  /**
   * 是否无资金流
   * <p>
   * 枚举值：
   * true：是
   * false：否
   * 示例值：true
   */
  @SerializedName("no_cash")
  private Boolean noCash;

  /**
   * 可用开始时间
   * <p>
   * 可用开始时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
   * 示例值: 2015-05-20T13:29:35.120+08:00
   */
  @SerializedName("available_begin_time")
  private String availableBeginTime;

  /**
   * 可用结束时间
   * <p>
   * 可用结束时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
   * 示例值: 2015-05-20T13:29:35.120+08:00
   */
  @SerializedName("available_end_time")
  private String availableEndTime;

  /**
   * 是否单品优惠
   * <p>
   * 枚举值：
   * true：是
   * false：否
   * 示例值：true
   */
  @SerializedName("singleitem")
  private Boolean singleitem;

  /**
   * 普通满减券信息
   * <p>
   * 普通满减券面额、门槛信息。
   */
  @SerializedName("normal_coupon_information")
  private NormalCouponInformation normalCouponInformation;

  /**
   * 实扣代金券信息
   * <p>
   * 普通满减券面额、门槛信息。
   */
  @SerializedName("consume_information")
  private ConsumeInformation consumeInformation;

  @Data
  @NoArgsConstructor
  public static class SingleitemDiscountOff implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 单品最高优惠价格
     * <p>
     * 单品最高优惠价格，单位：分。
     * 示例值：100
     */
    @SerializedName(value = "single_price_max")
    private Long singlePriceMax;
  }

  @Data
  @NoArgsConstructor
  public static class DiscountTo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 减至后优惠单价
     * <p>
     * 减至后优惠单价，单位：分。
     * 示例值：100
     */
    @SerializedName(value = "cut_to_price")
    private Long cutToPrice;

    /**
     * 最高价格
     * <p>
     * 可享受优惠的最高价格，单位：分。
     * 示例值：20
     */
    @SerializedName(value = "max_price")
    private Long maxPrice;
  }

  @Data
  @NoArgsConstructor
  public static class NormalCouponInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 面额
     * <p>
     * 面额，单位：分。
     * 示例值：100
     */
    @SerializedName(value = "coupon_amount")
    private Long couponAmount;

    /**
     * 门槛
     * <p>
     * 使用券金额门槛，单位：分。
     * 示例值：100
     */
    @SerializedName(value = "transaction_minimum")
    private Long transactionMinimum;
  }

  @Data
  @NoArgsConstructor
  public static class ConsumeInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 核销时间
     * <p>
     * 代金券核销时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
     * 示例值：2015-05-20T13:29:35.120+08:00
     */
    @SerializedName(value = "consume_time")
    private String consumeTime;

    /**
     * 核销商户号
     * <p>
     * 核销代金券的商户号。
     * 示例值：9856081
     */
    @SerializedName(value = "consume_mchid")
    private String consumeMchid;

    /**
     * 核销订单号
     * <p>
     * 核销订单号
     * 示例值：2345234523
     */
    @SerializedName(value = "transaction_id")
    private String transactionId;

    /**
     * 单品信息
     * <p>
     * 商户下单接口传的单品信息。
     */
    @SerializedName("goods_detail")
    private List<GoodsDetail> goodsDetail;
  }

  @Data
  @NoArgsConstructor
  public static class GoodsDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 单品编码
     * <p>
     * 单品券创建时录入的单品编码。
     * 示例值：a_goods1
     */
    @SerializedName(value = "goods_id")
    private String goodsId;

    /**
     * 单品数量
     * <p>
     * 单品数据
     * 示例值：7
     */
    @SerializedName(value = "quantity")
    private Integer quantity;

    /**
     * 单品单价
     * <p>
     * 单品单价
     * 示例值：1
     */
    @SerializedName(value = "price")
    private Integer price;

    /**
     * 优惠金额
     * <p>
     * 优惠金额
     * 示例值：4
     */
    @SerializedName(value = "discount_amount")
    private Integer discountAmount;
  }
}
