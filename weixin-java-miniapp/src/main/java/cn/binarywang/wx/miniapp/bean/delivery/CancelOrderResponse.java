package cn.binarywang.wx.miniapp.bean.delivery;

import cn.binarywang.wx.miniapp.bean.delivery.base.WxMaDeliveryBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 微信小程序即时配送 取消配送单接口 响应参数.
 *
 * @author Luo
 * @version 1.0
 * @date 2021-10-14 10:49
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class CancelOrderResponse extends WxMaDeliveryBaseResponse implements Serializable {

    private static final long serialVersionUID = 3773007367000633663L;

    /**
     * 扣除的违约金(单位：元)，精确到分.
     */
    @SerializedName("deduct_fee")
    private BigDecimal deductFee;

    /**
     * 说明.
     */
    @SerializedName("desc")
    private String desc;

}
