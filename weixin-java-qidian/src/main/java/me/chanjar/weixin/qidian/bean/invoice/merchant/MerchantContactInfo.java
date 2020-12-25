package me.chanjar.weixin.qidian.bean.invoice.merchant;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 商户联系信息
 *
 * @author Mario Luo
 */
@Data
public class MerchantContactInfo implements Serializable {
  private static final long serialVersionUID = -2008465944249686100L;

  /**
   * 联系电话
   */
  private String phone;

  /**
   * 开票超时时间
   */
  @SerializedName("time_out")
  private Integer timeout;

}
