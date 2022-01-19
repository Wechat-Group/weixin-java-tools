package cn.binarywang.wx.miniapp.bean.delivery;

import cn.binarywang.wx.miniapp.bean.delivery.base.WxMaDeliveryBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 微信小程序即时配送 下配送单接口 响应参数.
 *
 * @author Luo
 * @version 1.0
 * @date 2021-10-14 10:49
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AddOrderResponse extends WxMaDeliveryBaseResponse implements Serializable {

    private static final long serialVersionUID = 3773007367000633663L;

    /**
     * 实际运费(单位：元)，运费减去优惠券费用.
     */
    @SerializedName("fee")
    private BigDecimal fee;

    /**
     * 运费(单位：元).
     */
    @SerializedName("deliverfee")
    private BigDecimal deliverFee;

    /**
     * 优惠券费用(单位：元).
     */
    @SerializedName("couponfee")
    private BigDecimal couponFee;

    /**
     * 小费(单位：元).
     */
    @SerializedName("tips")
    private BigDecimal tips;

    /**
     * 保价费(单位：元).
     */
    @SerializedName("insurancfee")
    private BigDecimal insurancFee;

    /**
     * 配送距离(整数单位：米).
     */
    @SerializedName("distance")
    private BigDecimal distance;

    /**
     * 配送单号.
     */
    @SerializedName("waybill_id")
    private String waybillId;

    /**
     * 配送状态.
     */
    @SerializedName("order_status")
    private Integer orderStatus;

    /**
     * 收货码.
     */
    @SerializedName("finish_code")
    private Integer finishCode;

    /**
     * 取货码.
     */
    @SerializedName("pickup_code")
    private Integer pickupCode;

    /**
     * 预计骑手接单时间，单位秒，比如5分钟，就填300, 无法预计填0.
     */
    @SerializedName("dispatch_duration")
    private BigDecimal dispatchDuration;

}
