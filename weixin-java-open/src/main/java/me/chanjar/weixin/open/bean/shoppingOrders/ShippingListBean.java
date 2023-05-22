package me.chanjar.weixin.open.bean.shoppingOrders;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingListBean implements Serializable {

  private static final long serialVersionUID = -6884739637300493109L;
  /**
   * 物流单号，物流快递发货时必填，示例值: 323244567777 字符字节限制: [1, 128]
   */
  @SerializedName("tracking_no")
  private String trackingNo;
  /**
   * 物流公司编码，快递公司ID，参见「查询物流公司编码列表」，物流快递发货时必填， 示例值: DHL 字符字节限制: [1, 128]
   */
  @SerializedName("express_company")
  private String expressCompany;
  /**
   * 物流关联的商品列表，当统一发货（单个物流单）时，该项不填；当分拆发货（多个物流单）时，需填入各物流单关联的商品列表 多重性: [0, 50]
   */
  @SerializedName("item_list")
  private List<ShippingItemListBean> itemList;
  /**
   * 联系方式，当发货的物流公司为顺丰时，联系方式为必填，收件人或寄件人联系方式二选一
   */
  @SerializedName("contact")
  private ContactBean contact;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ShippingItemListBean implements Serializable {

    private static final long serialVersionUID = -1433227869321841858L;
    /**
     * 商户侧商品ID，商户系统内部商品编码，分拆发货模式下为必填，用于标识每笔物流单号内包含的商品，需与「上传购物详情」中传入的商品ID匹配
     * 示例值: 1246464644 字符字节限制: [1, 64]
     */
    @SerializedName("merchant_item_id")
    private String merchantItemId;
  }
}
