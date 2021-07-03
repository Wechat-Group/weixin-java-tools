package cn.binarywang.wx.miniapp.bean.invoice.reimburse;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * <pre>
 * 更新发票状态参数对象
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021-03-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInvoiceStatusRequest implements Serializable {

  private static final long serialVersionUID = -4122242332481909977L;


  /**
   * 发票卡券的card_id
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("card_id")
  private String cardId;


  /**
   * 发票卡券的加密code,和card_id共同构成一张发票卡券的唯一标识
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("encrypt_code")
  private String encryptCode;


  /**
   * 发票报销状态
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("reimburse_status")
  private String reimburseStatus;


  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
