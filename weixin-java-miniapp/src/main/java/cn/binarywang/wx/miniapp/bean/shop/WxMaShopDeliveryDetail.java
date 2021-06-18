package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * @date 2021/3/23
 * @description:
 */
@Data
public class WxMaShopDeliveryDetail implements Serializable {

  private static final long serialVersionUID = 9074573142867543744L;

  /**
   *
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("delivery_type")
  private Integer deliveryType;

  // 以下字段仅作为返回数据展示填充

  /**
   * 是否发货完成
   */
  @SerializedName("finish_all_delivery")
  private Integer finishAllDelivery;

  /**
   * 快递信息
   */
  @SerializedName("delivery_list")
  private List<WxMaShopDeliveryItem> deliveryList;
}

