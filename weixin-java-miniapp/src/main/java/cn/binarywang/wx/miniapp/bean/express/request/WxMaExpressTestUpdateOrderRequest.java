package cn.binarywang.wx.miniapp.bean.express.request;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <pre>
 * 模拟快递公司更新订单状态请求对象
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
@Builder
public class WxMaExpressTestUpdateOrderRequest implements Serializable {

  private static final long serialVersionUID = -3701602332580704140L;

  /**
   * 商户id
   * <pre>
   * 是否必填： 是
   * 描述： 需填test_biz_id,默认值已设置
   * </pre>
   */
  @SerializedName("biz_id")
  @Builder.Default
  private final String bizId = "test_biz_id";

  /**
   * 快递公司id
   * <pre>
   * 是否必填： 是
   * 描述： 需填TEST,默认值已设置
   * </pre>
   */
  @SerializedName("delivery_id")
  @Builder.Default
  private final String deliveryId = "TEST";

  /**
   * 订单号
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("order_id")
  private String orderId;

  /**
   * 运单号
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("waybill_id")
  private String waybillId;

  /**
   * 轨迹变化 Unix 时间戳
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("action_time")
  private Long actionTime;

  /**
   * 轨迹变化类型
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("action_type")
  private Integer actionType;

  /**
   * 轨迹变化具体信息说明,使用UTF-8编码
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("action_msg")
  private String actionMsg;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

}
