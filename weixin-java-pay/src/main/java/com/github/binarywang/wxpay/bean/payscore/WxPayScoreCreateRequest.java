package com.github.binarywang.wxpay.bean.payscore;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author doger.wang
 * @date 2020/5/12 16:36
 */
@NoArgsConstructor
@Data
public class WxPayScoreCreateRequest implements Serializable {


  private static final long serialVersionUID = 364764508076146082L;
  /**
     * out_order_no : 1234323JKHDFE1243252
     * appid : wxd678efh567hg6787
     * service_id : 500001
     * service_introduction : 某某酒店
     * post_payments : [{"name":"就餐费用服务费","amount":4000,"description":"就餐人均100元服务费：100/小时","count":1}]
     * post_discounts : [{"name":"满20减1元","description":"不与其他优惠叠加"}]
     * time_range : {"start_time":"20091225091010","end_time":"20091225121010"}
     * location : {"start_location":"嗨客时尚主题展餐厅","end_location":"嗨客时尚主题展餐厅"}
     * risk_fund : {"name":"ESTIMATE_ORDER_COST","amount":10000,"description":"就餐的预估费用"}
     * attach : Easdfowealsdkjfnlaksjdlfkwqoi&wl3l2sald
     * notify_url : https://api.test.com
     * openid : oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * need_user_confirm : true
     */

    private String out_order_no;
    private String appid;
    private String service_id;
    private String service_introduction;
    private TimeRange time_range;
    private Location location;
    private RiskFund risk_fund;
    private String attach;
    private String notify_url;
    private String openid;
    private boolean need_user_confirm;
    private List<PostPayments> post_payments;
    private List<PostDiscounts> post_discounts;

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
    public static class RiskFund {
        /**
         * name : ESTIMATE_ORDER_COST
         * amount : 10000
         * description : 就餐的预估费用
         */

        private String name;
        private int amount;
        private String description;
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
}
