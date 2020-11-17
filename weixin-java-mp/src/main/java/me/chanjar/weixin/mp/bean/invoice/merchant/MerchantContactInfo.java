package me.chanjar.weixin.mp.bean.invoice.merchant;

import lombok.Data;

import java.io.Serializable;

/**
 * 商户联系信息
 */
@Data
public class MerchantContactInfo implements Serializable {
  /**
   * 联系电话
   */
  private String phone;

  /**
   * 开票超时时间
   */
  private Integer timeout;

}
