package me.chanjar.weixin.mp.bean.invoice.reimburse;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * <pre>
 * 查询报销发票信息参数对象
 * </pre>
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021-03-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceInfoRequest implements Serializable {

  private static final long serialVersionUID = 7854633127026139444L;


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



  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
