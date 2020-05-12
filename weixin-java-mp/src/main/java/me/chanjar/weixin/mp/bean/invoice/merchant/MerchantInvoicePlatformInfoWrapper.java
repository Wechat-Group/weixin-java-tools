package me.chanjar.weixin.mp.bean.invoice.merchant;

import lombok.Data;

import java.io.Serializable;

/**
 * 设置商户联系信息和发票过时时间参数
 */
@Data
public class MerchantInvoicePlatformInfoWrapper implements Serializable {

  private MerchantInvoicePlatformInfo paymchInfo;

}
