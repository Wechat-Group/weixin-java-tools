package me.chanjar.weixin.cp.bean.order;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 应用版本付费订单详情
 *
 * @author leiguoqing
 * @date 2022年4月24日
 */
@Getter
@Setter
public class WxCpTpOrderDetails extends WxCpBaseResp {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -5028321625140879571L;

  /**
   * 订单号
   */
  @SerializedName("orderid")
  private String orderId;

  /**
   * 订单状态。0-未支付，1-已支付，2-已关闭， 3-未支付且已过期， 4-申请退款中， 5-申请退款成功， 6-退款被拒绝
   */
  @SerializedName("order_status")
  private Integer orderStatus;

  /**
   * 订单类型。0-普通订单，1-扩容订单，2-续期，3-版本变更
   */
  @SerializedName("order_type")
  private Integer orderType;

  /**
   * 客户企业的corpid
   */
  @SerializedName("paid_corpid")
  private String paidCorpId;

  /**
   * 下单操作人员userid。如果是服务商代下单，没有该字段。
   */
  @SerializedName("operator_id")
  private String operatorId;


  /**
   * 应用id
   */
  @SerializedName("suiteid")
  private String suiteId;


  /**
   * 应用id。（仅旧套件有该字段）
   */
  @SerializedName("appid")
  private String appId;


  /**
   * 购买版本ID
   */
  @SerializedName("edition_id")
  private String editionId;


  /**
   * 购买版本名字
   */
  @SerializedName("edition_name")
  private String editionName;


  /**
   * 实付款金额，单位分
   */
  @SerializedName("price")
  private Long price;


  /**
   * 购买的人数
   */
  @SerializedName("user_count")
  private Integer userCount;


  /**
   * 购买的时间，单位天
   */
  @SerializedName("order_period")
  private Integer orderPeriod;

  /**
   * 下单时间，秒级时间戳
   */
  @SerializedName("order_time")
  private Long orderTime;

  /**
   * 付款时间，秒级时间戳
   */
  @SerializedName("paid_time")
  private Long paidTime;


  /**
   * 购买生效期的开始时间，秒级时间戳
   */
  @SerializedName("begin_time")
  private Long beginTime;


  /**
   * 购买生效期的结束时间，秒级时间戳
   */
  @SerializedName("end_time")
  private Long endTime;

  /**
   * 下单来源。0-客户下单；1-服务商代下单；2-代理商代下单
   */
  @SerializedName("order_from")
  private Integer orderFrom;


  /**
   * 下单方corpid
   */
  @SerializedName("operator_corpid")
  private String operatorCorpId;

  /**
   * 服务商分成金额，单位分
   */
  @SerializedName("service_share_amount")
  private Long serviceShareAmount;


  /**
   * 平台分成金额，单位分
   */
  @SerializedName("platform_share_amount")
  private Long platformShareAmount;


  /**
   * 代理商分成金额，单位分
   */
  @SerializedName("dealer_share_amount")
  private Long dealerShareAmount;


  /**
   * 渠道商信息（仅当有渠道商报备后才会有此字段）
   */
  @SerializedName("dealer_corp_info")
  private DealerCorpInfo dealerCorpInfo;


  /**
   * 渠道商信息（仅当有渠道商报备后才会有此字段）
   */
  @Getter
  @Setter
  public static class DealerCorpInfo {
    /**
     * 代理商corpid
     */
    @SerializedName("corpid")
    private String corpId;


    /**
     * 代理商名
     */
    @SerializedName("corp_name")
    private String corpName;
  }

  /**
   * From json wx cp tp order details.
   *
   * @param json the json
   * @return the wx cp tp order details
   */
  public static WxCpTpOrderDetails fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpOrderDetails.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
