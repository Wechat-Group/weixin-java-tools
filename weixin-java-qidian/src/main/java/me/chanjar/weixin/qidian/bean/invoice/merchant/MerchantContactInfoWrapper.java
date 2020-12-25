package me.chanjar.weixin.qidian.bean.invoice.merchant;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 设置商户联系信息和发票过时时间参数
 *
 * @author Mario Luo
 */
@Data
@AllArgsConstructor
public class MerchantContactInfoWrapper implements Serializable {
  private static final long serialVersionUID = -5377979396495452212L;

  private MerchantContactInfo contact;
}
