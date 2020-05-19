package com.github.binarywang.wxpay.bean.payscore;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author doger.wang
 * @date 2020/5/12 17:05
 */
@NoArgsConstructor
@Data
public class WxPayScoreResult implements Serializable {


  private static final long serialVersionUID = 8809250065540275770L;
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

    private String appid;
    private String mchid;
    private String out_order_no;
    private String service_id;
    private String service_introduction;
    private String state;
    private String state_description;
    private RiskFund risk_fund;
    private TimeRange time_range;
    private Location location;
    private String attach;
    private String notify_url;
    private String order_id;
    @JSONField(name = "package")
    private String packageX;
    private List<PostPayments> post_payments;
    private List<PostDiscounts> post_discounts;
  private boolean need_collection;
  private Collection collection;
  //用于跳转的sign注意区分需确认模式和无需确认模式的数据差别。创单接口会返回，查询请自行组装
  private Map<String, String> payScoreSignInfo;

    @NoArgsConstructor
    @Data
    public static class RiskFund {
        /**
         * name :  ESTIMATE_ORDER_COST
         * amount : 10000
         * description : 就餐的预估费用
         */

        private String name;
        private int amount;
        private String description;
    }

    @NoArgsConstructor
    @Data
    public static class TimeRange {
        /**
         * start_time : 20091225091010
         * end_time : 20091225121010
         */

        private String start_time;
        private String end_time;
    }

    @NoArgsConstructor
    @Data
    public static class Location {
        /**
         * start_location : 嗨客时尚主题展餐厅
         * end_location : 嗨客时尚主题展餐厅
         */

        private String start_location;
        private String end_location;
    }

    @NoArgsConstructor
    @Data
    public static class PostPayments {
        /**
         * name : 就餐费用服务费
         * amount : 4000
         * description : 就餐人均100元服务费：100/小时
         * count : 1
         */

        private String name;
        private int amount;
        private String description;
        private int count;
    }

    @NoArgsConstructor
    @Data
    public static class PostDiscounts {
        /**
         * name : 满20减1元
         * description : 不与其他优惠叠加
         */

        private String name;
        private String description;
    }

  @NoArgsConstructor
  @Data
  public static class Collection {
    /**
     * state : USER_PAID
     * total_amount : 3900
     * paying_amount : 3000
     * paid_amount : 900
     * details : [{"seq":1,"amount":900,"paid_type":"NEWTON","paid_time":"20091225091210","transaction_id":"15646546545165651651"}]
     */

    private String state;
    private int total_amount;
    private int paying_amount;
    private int paid_amount;
    private List<Details> details;

    @NoArgsConstructor
    @Data
    public static class Details {
      /**
       * seq : 1
       * amount : 900
       * paid_type : NEWTON
       * paid_time : 20091225091210
       * transaction_id : 15646546545165651651
       */

      private int seq;
      private int amount;
      private String paid_type;
      private String paid_time;
      private String transaction_id;
    }
  }
}
