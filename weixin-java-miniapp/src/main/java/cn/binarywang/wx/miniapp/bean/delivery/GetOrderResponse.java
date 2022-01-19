package cn.binarywang.wx.miniapp.bean.delivery;

import cn.binarywang.wx.miniapp.bean.delivery.base.WxMaDeliveryBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 微信小程序即时配送 拉取配送单信息 响应参数.
 *
 * @author Luo
 * @version 1.0
 * @date 2021-10-14 10:49
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class GetOrderResponse extends WxMaDeliveryBaseResponse implements Serializable {

    private static final long serialVersionUID = 3773007367000633663L;

    /**
     * 配送状态.
     */
    @SerializedName("order_status")
    private Integer orderStatus;

    /**
     * 配送单号.
     */
    @SerializedName("waybill_id")
    private String waybillId;

    /**
     * 骑手姓名.
     */
    @SerializedName("rider_name")
    private String riderName;

    /**
     * 骑手电话.
     */
    @SerializedName("rider_phone")
    private String riderPhone;

    /**
     * 骑手位置经度, 配送中时返回.
     */
    @SerializedName("rider_lng")
    private BigDecimal riderLng;

    /**
     * 骑手位置纬度, 配送中时返回.
     */
    @SerializedName("rider_lat")
    private BigDecimal riderLat;

    /**
     * 预计还剩多久送达时间, 配送中时返回，单位秒， 已取货配送中需返回，比如5分钟后送达，填300.
     */
    @SerializedName("reach_time")
    private BigDecimal reachTime;

}
