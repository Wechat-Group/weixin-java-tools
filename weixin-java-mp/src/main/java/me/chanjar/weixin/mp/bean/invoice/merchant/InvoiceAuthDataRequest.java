package me.chanjar.weixin.mp.bean.invoice.merchant;

import lombok.Data;

import java.io.Serializable;

/**
 * 获取电子开票用户授权数据
 */
@Data
public class InvoiceAuthDataRequest implements Serializable {

  /**
   * 开票平台在微信的标识号，商户需要找开票平台提供
   */
  private String sPappid;

  /**
   * 订单id，在商户内单笔开票请求的唯一识别号
   */
  private String orderId;

}
