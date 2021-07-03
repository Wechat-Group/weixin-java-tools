package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * @date 2021/3/23
 * @description:
 */
@Data
public class WxMaShopOrderPayRequest implements Serializable {

  private static final long serialVersionUID = -954667936670521398L;

  /**
   * 订单ID
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("order_id")
  private Long orderId;
  /**
   * 商家自定义订单ID，与 order_id 二选一
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("out_order_id")
  private String outOrderId;
  /**
   * 用户的openid
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("openid")
  private String openid;
  /**
   * 类型，默认1:支付成功,2:支付失败,3:用户取消,4:超时未支付;5:商家取消
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("action_type")
  private Integer actionType;
  /**
   * 其他具体原因
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("action_remark")
  private String actionRemark;
  /**
   * 支付订单号，action_type=1时必填
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("transaction_id")
  private String transactionId;
  /**
   * 支付完成时间，action_type=1时必填
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("pay_time")
  private String payTime;
}

