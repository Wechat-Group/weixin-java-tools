package cn.binarywang.wx.miniapp.bean.express.request;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 获取运单请求对象
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaExpressGetOrderRequest implements Serializable {
  private static final long serialVersionUID = 8239315305577639778L;

  /**
   * 订单ID
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("order_id")
  private String orderId;

  /**
   * 用户openid
   * <pre>
   * 是否必填： 否
   * 描述： 当add_source=2时无需填写（不发送物流服务通知）
   * </pre>
   */
  private String openid;

  /**
   * 快递公司ID
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("delivery_id")
  private String deliveryId;

  /**
   * 运单ID
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("waybill_id")
  private String waybillId;


  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
