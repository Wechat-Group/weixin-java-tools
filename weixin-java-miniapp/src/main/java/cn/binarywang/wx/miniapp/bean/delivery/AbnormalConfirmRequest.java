package cn.binarywang.wx.miniapp.bean.delivery;

import cn.binarywang.wx.miniapp.bean.delivery.base.WxMaDeliveryBaseRequest;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 微信小程序即时配送 异常件退回商家商家确认收货接口 请求参数.
 *
 * @author Luo
 * @version 1.0
 * @date 2021-10-14 10:49
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AbnormalConfirmRequest extends WxMaDeliveryBaseRequest implements Serializable {

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
     * 备注.
     * <pre>
     * 是否必填：否
     * </pre>
     */
    @SerializedName("remark")
    private String remark;

}
