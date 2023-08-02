package cn.binarywang.wx.miniapp.bean.shop.request.shipping;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xzh
 * @Description
 * @createTime 2023/07/10 10:39
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingListBean implements Serializable {
  private static final long serialVersionUID = -6554762808990702774L;

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
   * 必填
   * 商品信息，例如：微信红包抱枕*1个，限120个字以内
   */
  @SerializedName("item_desc")
  private String itemDesc;
  /**
   * 联系方式，当发货的物流公司为顺丰时，联系方式为必填，收件人或寄件人联系方式二选一
   */
  @SerializedName("contact")
  private ContactBean contact;
}
