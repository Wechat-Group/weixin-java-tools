package cn.binarywang.wx.miniapp.bean.delivery;

import cn.binarywang.wx.miniapp.bean.delivery.base.WxMaDeliveryBaseRequest;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 微信小程序即时配送 取消配送单接口 请求参数.
 *
 * @author Luo
 * @version 1.0
 * @date 2021-10-14 10:49
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class CancelOrderRequest extends WxMaDeliveryBaseRequest implements Serializable {

    private static final long serialVersionUID = 3773007367000633663L;

    /**
     * 配送单id.
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("waybill_id")
    private String waybillId;

    /**
     * 取消原因Id.
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("cancel_reason_id")
    private Integer cancelReasonId;

    /**
     * 取消原因.
     * <pre>
     * 是否必填：否
     * </pre>
     */
    @SerializedName("cancel_reason")
    private String cancelReason;

}
