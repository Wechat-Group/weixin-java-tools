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
 * @createTime 2023/07/10 10:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactBean implements Serializable {
  private static final long serialVersionUID = 3388264169113920140L;

  /**
   * 寄件人联系方式，寄件人联系方式，采用掩码传输，最后4位数字不能打掩码 示例值: `189****1234, 021-****1234, ****1234, 0**2-***1234, 0**2-******23-10, ****123-8008` 值限制: 0 ≤ value ≤ 1024
   */
  @SerializedName("consignor_contact")
  private String consignorContact;
  /**
   * 收件人联系方式，收件人联系方式为，采用掩码传输，最后4位数字不能打掩码 示例值: `189****1234, 021-****1234, ****1234, 0**2-***1234, 0**2-******23-10, ****123-8008` 值限制: 0 ≤ value ≤ 1024
   */
  @SerializedName("receiver_contact")
  private String receiverContact;
}
