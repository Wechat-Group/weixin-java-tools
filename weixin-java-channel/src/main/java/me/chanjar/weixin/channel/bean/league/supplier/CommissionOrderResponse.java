package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 佣金订单响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommissionOrderResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 7004553990771819977L;
  /** 佣金单 */
  @JsonProperty("commssion_order")
  private CommissionOrder commissionOrder;


  /** 佣金单 */
  @Data
  @NoArgsConstructor
  public static class CommissionOrder implements Serializable {

    private static final long serialVersionUID = 735931726521944716L;
    /** 订单号 */
    @JsonProperty("order_id")
    private String orderId;

    /** 商品skuid */
    @JsonProperty("sku_id")
    private String skuId;

    /** 秒级时间戳 */
    @JsonProperty("create_time")
    private Long createTime;

    /** 秒级时间戳 */
    @JsonProperty("update_time")
    private Long updateTime;

    /** 佣金单状态，见{@link me.chanjar.weixin.channel.enums.CommissionOrderStatus} */
    @JsonProperty("status")
    private Integer status;

    /** 订单详情 */
    @JsonProperty("order_detail")
    private OrderDetail orderDetail;
  }

  /** 订单详情 */
  @Data
  @NoArgsConstructor
  public static class OrderDetail implements Serializable {

    private static final long serialVersionUID = 8349635368396073000L;
    /** 小店商家信息 */
    @JsonProperty("shop_info")
    private BizInfo shopInfo;

    /** 佣金单商品信息 */
    @JsonProperty("product_info")
    private ProductInfo productInfo;

    /** 订单信息 */
    @JsonProperty("order_info")
    private OrderInfo orderInfo;

    /** 分佣信息 */
    @JsonProperty("commission_info")
    private CommissionInfo commissionInfo;

  }

  /** 小店商家信息 */
  @Data
  @NoArgsConstructor
  public static class BizInfo implements Serializable {

    private static final long serialVersionUID = -8229584987720782974L;
    /** 所属小店appid */
    @JsonProperty("appid")
    private String appid;

  }

  /** 佣金单商品信息 */
  @Data
  @NoArgsConstructor
  public static class ProductInfo implements Serializable {

    private static final long serialVersionUID = -2790410903073956864L;
    /** 商品id */
    @JsonProperty("product_id")
    private String productId;

    /** sku小图 */
    @JsonProperty("thumb_img")
    private String thumbImg;

    /** 可分佣金额 */
    @JsonProperty("actual_payment")
    private Integer actualPayment;

    /** 商品标题 */
    @JsonProperty("title")
    private String title;

  }

  /** 订单信息 */
  @Data
  @NoArgsConstructor
  public static class OrderInfo implements Serializable {

    private static final long serialVersionUID = 7610425518539999170L;
    /** 订单状态，枚举值见OrderStatus */
    @JsonProperty("order_status")
    private Integer status;

  }

  /** 分佣信息 */
  @Data
  @NoArgsConstructor
  public static class CommissionInfo implements Serializable {

    private static final long serialVersionUID = -2114290318872427720L;
    /** 带货达人信息 */
    @JsonProperty("finder_info")
    private FinderInfo finderInfo;

    /** 服务费率[0, 1000000] */
    @JsonProperty("service_ratio")
    private Integer serviceRatio;

    /** 服务费金额 */
    @JsonProperty("service_amount")
    private Integer serviceAmount;

    /** 服务费结算时间 */
    @JsonProperty("profit_sharding_suc_time")
    private Long profitShardingSucTime;

  }

  /** 带货达人信息 */
  @Data
  @NoArgsConstructor
  public static class FinderInfo implements Serializable {

    private static final long serialVersionUID = 7383486670949864257L;
    /** 达人昵称 */
    @JsonProperty("nickname")
    private String nickname;

    /** 佣金率[0, 1000000] */
    @JsonProperty("ratio")
    private Integer ratio;
    /** 佣金 */
    @JsonProperty("amount")
    private Integer amount;

    /** 视频号openfinderid */
    @JsonProperty("openfinderid")
    private String openfinderid;
  }
}
