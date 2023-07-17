package cn.binarywang.wx.miniapp.bean.shop.request.shipping;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xzh
 * created on  2023/5/17 17:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaOrderShippingInfoGetListRequest implements Serializable {

  private static final long serialVersionUID = -3682480001426075609L;

  /**
   * 支付时间所属范围
   */
  @SerializedName("pay_time_range")
  private PayTimeRange payTimeRange;

  /**
   * 订单状态枚举：(1) 待发货；(2) 已发货；(3) 确认收货；(4) 交易完成；(5) 已退款。
   */
  @SerializedName("order_state")
  private Integer orderState;
  /**
   * 支付者openid
   */
  @SerializedName("openid")
  private String openId;
  /**
   * 翻页时使用，获取第一页时不用传入，如果查询结果中 has_more 字段为 true，则传入该次查询结果中返回的 last_index 字段可获取下一页。
   */
  @SerializedName("last_index")
  private String lastIndex;
  /**
   * 翻页时使用，返回列表的长度，默认为100。
   */
  @SerializedName("page_size")
  private Long pageSize;

  @Data
  public static class PayTimeRange implements Serializable {

    private static final long serialVersionUID = -1477231289550635468L;

    /**
     * 起始时间，时间戳形式，不填则视为从0开始
     */
    @SerializedName("begin_time")
    private Long beginTime;
    /**
     * 结束时间（含），时间戳形式，不填则视为32位无符号整型的最大值
     */
    @SerializedName("end_time")
    private Long endTime;
  }

}
