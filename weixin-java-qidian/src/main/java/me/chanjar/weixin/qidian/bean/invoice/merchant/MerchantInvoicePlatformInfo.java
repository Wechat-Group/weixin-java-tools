package me.chanjar.weixin.qidian.bean.invoice.merchant;

import java.io.Serializable;

/**
 * 商户的开票平台信息
 */
public class MerchantInvoicePlatformInfo implements Serializable {

  /**
   * 微信支付商户号
   */
  private String mchid;

  /**
   * 为该商户提供开票服务的开票平台 id ，由开票平台提供给商户
   */
  private String sPappid;
}
