package me.chanjar.weixin.qidian.bean.invoice.merchant;

import java.io.Serializable;

/**
 * 拒绝开票请求参数
 */
public class InvoiceRejectRequest implements Serializable {

  /**
   * 开票平台标示
   */
  private String sPappid;

  /**
   * 订单id
   */
  private String orderId;

  /**
   * 拒绝原因
   */
  private String reason;

  /**
   * 引导用户跳转url
   */
  private String url;

}
