package me.chanjar.weixin.mp.bean.invoice.merchant;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 商户的开票平台信息
 *
 * @author Mario Luo
 */
@Data
public class MerchantInvoicePlatformInfo implements Serializable {
  private static final long serialVersionUID = -2388214622725430530L;

  /**
   * 微信支付商户号
   */
  private String mchid;

  /**
   * 为该商户提供开票服务的开票平台 id ，由开票平台提供给商户
   */
  @SerializedName("s_pappid")
  private String sPappid;
}
