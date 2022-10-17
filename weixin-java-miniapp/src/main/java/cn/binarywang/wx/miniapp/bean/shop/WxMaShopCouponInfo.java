package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leiin
 * created on  2022/7/1 2:57 下午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopCouponInfo implements Serializable {

  private static final long serialVersionUID = 5807154725645642700L;

  /**
   * 是否必填：是
   * 说明：商家侧优惠券ID
   */
  @SerializedName("out_coupon_id")
  private String outCouponId;
  /**
   * 是否必填：是
   * 说明：优惠券类型
   */
  @SerializedName("type")
  private Integer type;
  /**
   * 是否必填：是
   * 说明：优惠券推广类型
   */
  @SerializedName("promote_type")
  private Integer promoteType;

  @SerializedName("coupon_info")
  private CouponInfo couponInfo;

  // 返回参数
  /**
   * 优惠券状态
   */
  @SerializedName("status")
  private Integer status;
  /**
   * 创建时间
   */
  @SerializedName("create_time")
  private Long createTime;
  /**
   * 更新时间
   */
  @SerializedName("update_time")
  private Long updateTime;

  @SerializedName("coupon_stock")
  private CouponStock couponStock;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CouponInfo implements Serializable {

    private static final long serialVersionUID = -7913225774910831745L;

    /**
     * 是否必填：是
     * 说明：优惠券名
     */
    private String name;

    @SerializedName("promote_info")
    private PromoteInfo promoteInfo;

    @SerializedName("discount_info")
    private DiscountInfo discountInfo;

    @SerializedName("receive_info")
    private ReceiveInfo receiveInfo;

    @SerializedName("valid_info")
    private ValidInfo validInfo;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class PromoteInfo {
    @SerializedName("promote_type")
    private Integer promoteType;
    private PromoteFinder finder;

    @Data
    public static class PromoteFinder {
      private String nickname;
    }
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class DiscountInfo {

    /**
     * 是否必填：	否
     * 说明：折扣数,比如5.1折,则填5100,折扣券必需
     */
    @SerializedName("discount_num")
    private Integer discountNum;
    /**
     * 是否必填：	否
     * 说明：减金额,单位为分，直减券、满减券必需
     */
    @SerializedName("discount_fee")
    private Long discountFee;

    @SerializedName("discount_condition")
    private DiscountCondition discountCondition;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiscountCondition {

      /**
       * 是否必填：	否
       * 说明：优惠条件所需的商品数
       */
      @SerializedName("product_cnt")
      private Integer productCnt;
      /**
       * 是否必填：	否
       * 说明：优惠条件所需满足的金额
       */
      @SerializedName("product_price")
      private Long productPrice;
      /**
       * 是否必填： 否
       * 说明：指定商品商家侧ID，商品券必需，最多128个
       */
      @SerializedName("out_product_ids")
      private List<String> outProductIds;

      @SerializedName("tradein_info")
      private TradeinInfo tradeinInfo;

      @SerializedName("buyget_info")
      private BuygetInfo buyget_info;

      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      public static class TradeinInfo {

        /**
         * 是否必填：否
         * 说明：换购商品商家侧ID，换购券必需
         */
        @SerializedName("out_product_id")
        private String outProductId;
        /**
         * 是否必填：否
         * 说明：需要支付的金额，单位分，换购券必需
         */
        @SerializedName("price")
        private Long price;
      }

      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      public static class BuygetInfo {
        /**
         * 是否必填：否
         * 说明：购买商品商家侧ID，买赠券必需
         */
        @SerializedName("buy_out_product_id")
        private String buyOutProductId;
        /**
         * 是否必填：否
         * 说明：购买商品数，买赠券必需
         */
        @SerializedName("buy_product_cnt")
        private Integer buyProductCnt;
        /**
         * 是否必填：否
         * 说明：赠送商品商家侧ID，买赠券必需
         */
        @SerializedName("get_out_product_id")
        private String getOutProductId;
        /**
         * 是否必填：否
         * 说明：赠送商品数，买赠券必需
         */
        @SerializedName("get_product_cnt")
        private Integer getProductCnt;
      }
    }
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ReceiveInfo {

    /**
     * 是否必填：是
     * 说明：领取开始时间 （秒级时间戳）
     */
    @SerializedName("start_time")
    private Long startTime;
    /**
     * 是否必填：是
     * 说明：领取结束时间 （秒级时间戳）
     */
    @SerializedName("end_time")
    private Long endTime;
    /**
     * 是否必填：是
     * 说明：个人限领张数，只做展示，领券回调时接入方判断有无超领。
     */
    @SerializedName("limit_num_one_person")
    private Integer limitNumOnePerson;
    /**
     * 是否必填：是
     * 说明：总发放量，即初始库存数，只做展示，领券回调时接入方判断有无超领。
     */
    @SerializedName("total_num")
    private Integer totalNum;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ValidInfo {

    /**
     * 是否必填：是
     * 说明：有效期类型,1:商品指定时间区间,2:生效天数,3:生效秒数
     */
    @SerializedName("valid_type")
    private Integer validType;
    /**
     * 是否必填：否
     * 说明：生效天数，有效期类型为2时必需
     */
    @SerializedName("valid_day_num")
    private Integer validDayNum;
    /**
     * 是否必填：否
     * 说明：生效秒数，有效期类型为3时必需
     */
    @SerializedName("valid_second")
    private Long validSecond;
    /**
     * 是否必填：否
     * 说明：生效开始时间，有效期类型为1时必需
     */
    @SerializedName("start_time")
    private Long startTime;
    /**
     * 是否必填：否
     * 说明：生效结束时间，有效期类型为1时必需
     */
    @SerializedName("end_time")
    private Long endTime;
  }

  @Data
  public static class CouponStock {

    /**
     * 商家侧优惠券ID
     */
    @SerializedName("out_coupon_id")
    private String outCouponId;
    /**
     * 创建时间
     */
    @SerializedName("create_time")
    private Long createTime;
    /**
     * 更新时间
     */
    @SerializedName("update_time")
    private Long updateTime;

    @SerializedName("stock_info")
    private StockInfo stockInfo;

    @Data
    public static class StockInfo {
      /**
       * 优惠券库存剩余量
       */
      @SerializedName("issued_num")
      private Integer issuedNum;
      /**
       * 优惠卷发放量
       */
      @SerializedName("receive_num")
      private Integer receiveNum;
    }
  }
}
