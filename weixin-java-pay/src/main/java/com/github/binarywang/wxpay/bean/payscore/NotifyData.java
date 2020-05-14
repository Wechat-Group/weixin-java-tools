package com.github.binarywang.wxpay.bean.payscore;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信支付分确认订单跟支付回调对象
 * @author doger.wang
 * @date 2020/5/14 12:18
 */
@NoArgsConstructor
@Data
public class NotifyData {


  /**
   * id : EV-2018022511223320873
   * create_time : 20180225112233
   * resource_type : encrypt-resource
   * event_type : PAYSCORE.USER_CONFIRM
   * resource : {"algorithm":"AEAD_AES_256_GCM","ciphertext":"...","nonce":"...","associated_data":""}
   */

  private String id;
  private String create_time;
  private String resource_type;
  private String event_type;
  private Resource resource;

  @NoArgsConstructor
  @Data
  public static class Resource {
    /**
     * algorithm : AEAD_AES_256_GCM
     * ciphertext : ...
     * nonce : ...
     * associated_data :
     */

    private String algorithm;
    private String ciphertext;
    private String nonce;
    private String associated_data;
  }
}
