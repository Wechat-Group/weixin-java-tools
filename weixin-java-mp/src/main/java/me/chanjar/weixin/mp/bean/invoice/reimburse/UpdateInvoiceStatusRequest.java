package me.chanjar.weixin.mp.bean.invoice.reimburse;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

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

  /**
   * 无参构造方法
   */
  @Tolerate
  UpdateInvoiceStatusRequest() {}

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
