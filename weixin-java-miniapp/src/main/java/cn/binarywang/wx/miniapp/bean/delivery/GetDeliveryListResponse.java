package cn.binarywang.wx.miniapp.bean.delivery;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 获取运力id列表get_delivery_list 响应参数
 * </pre>
 *
 * @author zhongjun
 * @since 2024-03-14
 */
@Data
@Accessors(chain = true)
public class GetDeliveryListResponse extends WxMaBaseResponse implements Serializable {

  private static final long serialVersionUID = 7113254030347413645L;

  /**
   * 运力公司个数
   */
  @SerializedName("count")
  private Integer count;

  /**
   * 运力公司列表
   */
  @SerializedName("delivery_list")
  private List<DeliveryList> deliveryList;

  @Data
  @Accessors(chain = true)
  public static class DeliveryList implements Serializable {

    private static final long serialVersionUID = 2543667583406735085L;

    /**
     * 运力公司 id
     */
    @SerializedName("delivery_id")
    private String deliveryId;
    /**
     * 运力公司名称
     */
    @SerializedName("delivery_name")
    private String deliveryName;

  }


  public static GetDeliveryListResponse fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, GetDeliveryListResponse.class);
  }
}
