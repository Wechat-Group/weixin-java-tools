package cn.binarywang.wx.miniapp.bean.delivery;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 微信小程序即时配送 模拟配送公司更新配送单状态 请求参数.
 *
 * @author Luo
 * @version 1.0
 * @date 2021-10-14 10:49
 */
@Data
@Accessors(chain = true)
public class MockUpdateOrderRequest implements Serializable {

    private static final long serialVersionUID = 3773007367000633663L;

    /**
     * 商家id, 必须是 "test_shop_id".
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("shopid")
    private String shopId = "test_shop_id";

    /**
     * 唯一标识订单的 ID，由商户生成.
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("shop_order_id")
    private String shopOrderId;

    /**
     * 状态变更时间点，Unix秒级时间戳.
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("action_time")
    private Long actionTime;

    /**
     * 配送状态，枚举值.
     * <pre>
     * 是否必填：是
     * </pre>
     */
    @SerializedName("order_status")
    private Integer orderStatus;

}
