package me.chanjar.weixin.mp.bean.invoice.merchant;

import lombok.Data;

import java.io.Serializable;

/**
 * 拒绝开票请求参数
 */
@Data
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
