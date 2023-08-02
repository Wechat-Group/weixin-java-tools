package me.chanjar.weixin.mp.bean.invoice.merchant;

import lombok.Data;

import java.io.Serializable;

/**
 * 获取授权链接返回结果DTO
 *
 * @author Mario Luo
 */
@Data
public class InvoiceAuthPageResult implements Serializable {
  private static final long serialVersionUID = 2922797121045894425L;

  /**
   * 授权页地址
   */
  private String authUrl;

  /**
   * 当发起端为小程序时, 返回
   */
  private String appid;
}
