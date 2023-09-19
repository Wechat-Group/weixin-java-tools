package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author doger.wang
 * created on  2020/5/12 17:05
 */
@NoArgsConstructor
@Data
public class WxPayScoreResult implements Serializable {
  private static final long serialVersionUID = 8809250065540275770L;

  public static WxPayScoreResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxPayScoreResult.class);
  }

  /**
   * appid : wxd678efh567hg6787
   * mchid : 1230000109
   * out_order_no : 1234323JKHDFE1243252
   * service_id : 500001
   * service_introduction : 某某酒店
   * state : CREATED
   * state_description : MCH_COMPLETE
   * post_payments : [{"name":"就餐费用服务费","amount":4000,"description":"就餐人均100元服务费：100/小时","count":1}]
   * post_discounts : [{"name":"满20减1元","description":"不与其他优惠叠加"}]
   * risk_fund : {"name":" ESTIMATE_ORDER_COST","amount":10000,"description":"就餐的预估费用"}
   * time_range : {"start_time":"20091225091010","end_time":"20091225121010"}
   * location : {"start_location":"嗨客时尚主题展餐厅","end_location":"嗨客时尚主题展餐厅"}
   * attach : Easdfowealsdkjfnlaksjdlfkwqoi&wl3l2sald
   * notify_url : https://api.test.com
   * order_id : 15646546545165651651
   * package :  DJIOSQPYWDxsjdldeuwhdodwxasd_dDiodnwjh9we
   */
  @SerializedName("appid")
  private String appid;
  @SerializedName("mchid")
  private String mchid;
  @SerializedName("out_order_no")
  private String outOrderNo;
  @SerializedName("service_id")
  private String serviceId;
  @SerializedName("service_introduction")
  private String serviceIntroduction;
  @SerializedName("state")
  private String state;
  @SerializedName("state_description")
  private String stateDescription;
  @SerializedName("risk_fund")
  private RiskFund riskFund;
  @SerializedName("time_range")
  private TimeRange timeRange;
  @SerializedName("location")
  private Location location;
  @SerializedName("attach")
  private String attach;
  @SerializedName("notify_url")
  private String notifyUrl;
  @SerializedName("order_id")
  private String orderId;
  @SerializedName("package")
  private String packageX;
  @SerializedName("post_payments")
  private List<PostPayment> postPayments;
  @SerializedName("post_discounts")
  private List<PostDiscount> postDiscounts;
  @SerializedName("need_collection")
  private boolean needCollection;
  /**
   * 收款信息
   */
  @SerializedName("collection")
  private Collection collection;
  /**
   * 用于跳转的sign注意区分需确认模式和无需确认模式的数据差别。创单接口会返回，查询请自行组装
   */
  @SerializedName("payScoreSignInfo")
  private Map<String, String> payScoreSignInfo;

  @SerializedName("openid")
  private String openid;

  @SerializedName("apply_permissions_token")
  private String applyPermissionsToken;

  @SerializedName("authorization_code")
  private String authorizationCode;

  @SerializedName("authorization_state")
  private String authorizationState;

  @SerializedName("cancel_authorization_time")
  private String cancelAuthorizationTime;

  @SerializedName("authorization_success_time")
  private String authorizationSuccessTime;

  /**
   * 用户分层
   */
  @SerializedName("user_risk_level")
  private Integer userRiskLevel;

  /**
   * 分层版本
   */
  @SerializedName("risk_level_version")
  private Integer riskLevelVersion;

  /**
   * 总金额
   */
  @SerializedName("total_amount")
  private Integer totalAmount;

  /**
   * 渠道商商户号
   */
  @SerializedName("channel_id")
  private String channelId;

  /**
   * 收款信息
   */
  @Data
  @NoArgsConstructor
  public static class Collection implements Serializable {
    private static final long serialVersionUID = 2279516555276133086L;
    /**
     * state : USER_PAID
     * total_amount : 3900
     * paying_amount : 3000
     * paid_amount : 900
     * details : [{"seq":1,"amount":900,"paid_type":"NEWTON","paid_time":"20091225091210","transaction_id":"15646546545165651651"}]
     */
    @SerializedName("state")
    private String state;
    @SerializedName("total_amount")
    private int totalAmount;
    @SerializedName("paying_amount")
    private int payingAmount;
    @SerializedName("paid_amount")
    private int paidAmount;
    @SerializedName("details")
    private List<Detail> details;
  }

}
