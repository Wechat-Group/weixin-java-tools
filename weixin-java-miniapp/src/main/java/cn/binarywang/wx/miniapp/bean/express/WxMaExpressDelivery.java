package cn.binarywang.wx.miniapp.bean.express;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.GsonParser;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 快递公司对象
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMaExpressDelivery implements Serializable {

  private static final long serialVersionUID = -8394544895730223810L;

  /**
   * 快递公司 ID
   */
  @SerializedName("delivery_id")
  private String deliveryId;

  /**
   * 快递公司名称
   */
  @SerializedName("delivery_name")
  private String deliveryName;

  /**
   * 是否支持散单, 1表示支持
   */
  @SerializedName("can_use_cash")
  private Integer canUseCash;

  /**
   * 是否支持查询面单余额, 1表示支持
   */
  @SerializedName("can_get_quota")
  private Integer canGetQuota;

  /**
   * 散单对应的bizid，当can_use_cash=1时有效
   */
  @SerializedName("cash_biz_id")
  private String cashBizId;

  /**
   * 支持的服务类型
   */
  @SerializedName("service_type")
  private List<ServiceType> serviceType;

  public static List<WxMaExpressDelivery> fromJson(String json) {
    JsonObject jsonObject = GsonParser.parse(json);
    return WxMaGsonBuilder.create().fromJson(jsonObject.get("data").toString(),
      new TypeToken<List<WxMaExpressDelivery>>() {
      }.getType());
  }

  @Data
  public static class ServiceType{

    /**
     * 服务类型ID
     */
    @SerializedName("service_type")
    private Integer serviceType;

    /**
     * 服务类型名称
     */
    @SerializedName("service_name")
    private String serviceName;
  }
}
