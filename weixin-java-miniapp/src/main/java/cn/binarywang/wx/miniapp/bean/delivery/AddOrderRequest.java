package cn.binarywang.wx.miniapp.bean.delivery;

import cn.binarywang.wx.miniapp.bean.delivery.base.WxMaDeliveryBaseRequest;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 微信小程序即时配送 下配送单接口 请求参数.
 *
 * @author Luo
 * @version 1.0
 * @date 2021-10-14 10:49
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AddOrderRequest extends WxMaDeliveryBaseRequest implements Serializable {

    private static final long serialVersionUID = 3773007367000633663L;

    /**
     * 子商户id，区分小程序内部多个子商户.
     * <pre>
     * 是否必填：否
     * </pre>
     */
    @SerializedName("sub_biz_id")
    private String subBizId;

    /**
     * 发件人信息，顺丰同城急送必须填写，美团配送、达达、闪送，若传了shop_no的值可不填该字段.
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("sender")
    private Sender sender;

    /**
     * 收件人信息.
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("receiver")
    private Receiver receiver;

    /**
     * 货物信息.
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("cargo")
    private Cargo cargo;

    /**
     * 订单信息.
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("order_info")
    private OrderInfo orderInfo;

    /**
     * 商品信息，会展示到物流通知消息中.
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("shop")
    private Shop shop;

    /**
     * 发件人信息.
     */
    @Data
    @Accessors(chain = true)
    public static class Sender implements Serializable {

        private static final long serialVersionUID = -8101805250220380047L;

        /**
         * 姓名，最长不超过256个字符.
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("name")
        private String name;

        /**
         * 城市名称，如广州市.
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("city")
        private String city;

        /**
         * 地址(街道、小区、大厦等，用于定位).
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("address")
        private String address;

        /**
         * 地址(街道、小区、大厦等，用于定位).
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("address_detail")
        private String addressDetail;

        /**
         * 坐标类型，默认 0：火星坐标（高德，腾讯地图均采用火星坐标） 1：百度坐标.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("coordinate_type")
        private int coordinateType;

        /**
         * 经度（火星坐标或百度坐标，和 coordinate_type 字段配合使用，确到小数点后6位.
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("lng")
        private BigDecimal lng;

        /**
         * 纬度（火星坐标或百度坐标，和 coordinate_type 字段配合使用，精确到小数点后6位）.
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("lat")
        private BigDecimal lat;

        /**
         * 电话/手机号，最长不超过64个字符.
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("phone")
        private String phone;

    }

    /**
     * 收件人信息.
     */
    @Data
    @Accessors(chain = true)
    public static class Receiver implements Serializable {

        private static final long serialVersionUID = -8101805250220380047L;

        /**
         * 姓名，最长不超过256个字符.
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("name")
        private String name;

        /**
         * 城市名称，如广州市.
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("city")
        private String city;

        /**
         * 地址(街道、小区、大厦等，用于定位).
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("address")
        private String address;

        /**
         * 地址(街道、小区、大厦等，用于定位).
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("address_detail")
        private String addressDetail;

        /**
         * 坐标类型，默认 0：火星坐标（高德，腾讯地图均采用火星坐标） 1：百度坐标.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("coordinate_type")
        private int coordinateType;

        /**
         * 经度（火星坐标或百度坐标，和 coordinate_type 字段配合使用，确到小数点后6位.
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("lng")
        private BigDecimal lng;

        /**
         * 纬度（火星坐标或百度坐标，和 coordinate_type 字段配合使用，精确到小数点后6位）.
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("lat")
        private BigDecimal lat;

        /**
         * 电话/手机号，最长不超过64个字符.
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("phone")
        private String phone;

    }

    /**
     * 商品信息.
     */
    @Data
    @Accessors(chain = true)
    public static class Shop implements Serializable {

        private static final long serialVersionUID = -8958461649711388689L;

        /**
         * 商品数量.
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("goods_count")
        private Integer goodsCount;

        /**
         * 商品名称.
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("goods_name")
        private String goodsName;

        /**
         * 商品缩略图 url.
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("img_url")
        private String imgUrl;

        /**
         * 商家小程序的路径，建议为订单页面.
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("wxa_path")
        private String wxaPath;

        /**
         * 若结算方式为：第三方向配送公司统一结算，商户后续和第三方结算，则该参数必填.
         * 在该结算模式下，第三方用自己的开发小程序替授权商户发起下单，并将授权小程序的appid给平台，后续配送通知中可回流授权商户小程序.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("wxa_appid")
        private String wxaAppid;

    }

    /**
     * 订单信息.
     */
    @Data
    @Accessors(chain = true)
    public static class OrderInfo implements Serializable {

        private static final long serialVersionUID = 5277759430030747900L;

        /**
         * 配送服务代码 不同配送公司自定义, 顺丰和达达不填.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("delivery_service_code")
        private String deliveryServiceCode;

        /**
         * 订单类型, 0: 即时单 1 预约单，如预约单，需要设置expected_delivery_time或expected_finish_time或expected_pick_time.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("order_type")
        private Integer orderType;

        /**
         * 期望派单时间(达达支持，表示达达系统调度时间, 到那个时间才会有状态更新的回调通知)，unix-timestamp, 比如1586342180.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("expected_delivery_time")
        private Long expectedDeliveryTime;

        /**
         * 期望送达时间(美团、顺丰同城急送支持），unix-timestamp, 比如1586342180.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("expected_finish_time")
        private Long expectedFinishTime;

        /**
         * 期望取件时间（闪送、顺丰同城急送支持，闪送需要设置两个小时后的时间，顺丰同城急送只需传expected_finish_time或expected_pick_time其中之一即可，同时都传则以expected_finish_time为准），unix-timestamp, 比如1586342180.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("expected_pick_time")
        private Long expectedPickTime;

        /**
         * 备注，最长不超过200个字符.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("note")
        private String note;

        /**
         * 门店订单流水号，建议提供，方便骑手门店取货，最长不超过32个字符.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("poi_seq")
        private String poiSeq;

        /**
         * 用户下单付款时间, 顺丰必填, 比如1555220757.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("order_time")
        private Long orderTime;

        /**
         * 是否保价，0：非保价，1：保价.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("is_insured")
        private Integer isInsured;

        /**
         * 保价金额，单位为元，精确到分.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("declared_value")
        private BigDecimal declaredValue;

        /**
         * 小费，单位为元, 下单一般不加小费.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("tips")
        private Integer tips;

        /**
         * 是否选择直拿直送（0：不需要；1：需要。选择直拿直送后，同一时间骑手只能配送此订单至完成，配送费用也相应高一些，闪送必须选1，达达可选0或1，其余配送公司不支持直拿直送）.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("is_direct_delivery")
        private Integer isDirectDelivery;

        /**
         * 骑手应付金额，单位为元，精确到分.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("cash_on_delivery")
        private BigDecimal cashOnDelivery;

        /**
         * 骑手应收金额，单位为元，精确到分.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("cash_on_pickup")
        private BigDecimal cashOnPickup;

        /**
         * 物流流向，1：从门店取件送至用户；2：从用户取件送至门店.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("rider_pick_method")
        private Integer riderPickMethod;

        /**
         * 收货码（0：不需要；1：需要。收货码的作用是：骑手必须输入收货码才能完成订单妥投）.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("is_finish_code_needed")
        private Integer isFinishCodeNeeded;

        /**
         * 取货码（0：不需要；1：需要。取货码的作用是：骑手必须输入取货码才能从商家取货）.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("is_pickup_code_needed")
        private Integer isPickupCodeNeeded;

    }

    /**
     * 货物信息.
     */
    @NoArgsConstructor
    @Data
    @Accessors(chain = true)
    public static class Cargo implements Serializable {

        private static final long serialVersionUID = -8339389045820636620L;

        /**
         * 货物价格，单位为元，精确到小数点后两位（如果小数点后位数多于两位，则四舍五入保留两位小数），范围为(0-5000].
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("goods_value")
        private BigDecimal goodsValue;

        /**
         * 货物高度，单位为cm，精确到小数点后两位（如果小数点后位数多于两位，则四舍五入保留两位小数），范围为(0-45].
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("goods_height")
        private BigDecimal goodsHeight;

        /**
         * 货物长度，单位为cm，精确到小数点后两位（如果小数点后位数多于两位，则四舍五入保留两位小数），范围为(0-65].
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("goods_length")
        private BigDecimal goodsLength;

        /**
         * 货物宽度，单位为cm，精确到小数点后两位（如果小数点后位数多于两位，则四舍五入保留两位小数），范围为(0-50].
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("goods_width")
        private BigDecimal goodsWidth;

        /**
         * 货物重量，单位为kg，精确到小数点后两位（如果小数点后位数多于两位，则四舍五入保留两位小数），范围为(0-50].
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("goods_weight")
        private BigDecimal goodsWeight;

        /**
         * 货物详情，最长不超过10240个字符.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("goods_detail")
        private GoodsDetail goodsDetail;

        /**
         * 货物取货信息，用于骑手到店取货，最长不超过100个字符.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("goods_pickup_info")
        private String goodsPickupInfo;

        /**
         * 货物交付信息，最长不超过100个字符.
         * <pre>
         * 是否必填：否
         * </pre>
         */
        @SerializedName("goods_delivery_info")
        private String goodsDeliveryInfo;

        /**
         * 品类一级类目, 详见品类表 https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/union/access-guidelines/promoter/api/product/category.html.
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("cargo_first_class")
        private String cargoFirstClass;

        /**
         * 品类二级类目.
         * <pre>
         * 是否必填：是
         * </pre>
         */
        @SerializedName("cargo_second_class")
        private String cargoSecondClass;

        /**
         * 货物详情.
         */
        @Data
        @Accessors(chain = true)
        public static class GoodsDetail implements Serializable {

            private static final long serialVersionUID = -8339389045820636620L;

            /**
             * 货物列表.
             * <pre>
             * 是否必填：是
             * </pre>
             */
            @SerializedName("goods")
            private List<Goods> goods;

            /**
             * 货物详情.
             */
            @NoArgsConstructor
            @Data
            @Accessors(chain = true)
            public static class Goods implements Serializable {

                private static final long serialVersionUID = -8339389045820636620L;

                /**
                 * 货物数量.
                 * <pre>
                 * 是否必填：是
                 * </pre>
                 */
                @SerializedName("good_count")
                private Integer goodCount;

                /**
                 * 货品名称.
                 * <pre>
                 * 是否必填：是
                 * </pre>
                 */
                @SerializedName("good_name")
                private String goodName;

                /**
                 * 货品单价，精确到小数点后两位（如果小数点后位数多于两位，则四舍五入保留两位小数）.
                 * <pre>
                 * 是否必填：否
                 * </pre>
                 */
                @SerializedName("good_price")
                private BigDecimal goodPrice;

                /**
                 * 货品单位，最长不超过20个字符.
                 * <pre>
                 * 是否必填：否
                 * </pre>
                 */
                @SerializedName("good_unit")
                private String goodUnit;

            }

        }

    }

}
