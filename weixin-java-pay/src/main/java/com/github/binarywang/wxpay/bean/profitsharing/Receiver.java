package com.github.binarywang.wxpay.bean.profitsharing;

import java.io.Serializable;

/**
 * @author Wang GuangXin 2019/10/22 11:07
 * @version 1.0
 */
public class Receiver implements Serializable {
  private String type;
  private String account;
  private Integer amount;
  private String description;

  /**
   * @param type        MERCHANT_ID：商户ID
   *                    PERSONAL_WECHATID：个人微信号PERSONAL_OPENID：个人openid（由父商户APPID转换得到）PERSONAL_SUB_OPENID: 个人sub_openid（由子商户APPID转换得到）
   * @param account     类型是MERCHANT_ID时，是商户ID
   *                    类型是PERSONAL_WECHATID时，是个人微信号
   *                    类型是PERSONAL_OPENID时，是个人openid
   *                    类型是PERSONAL_SUB_OPENID时，是个人sub_openid
   * @param amount      分账金额，单位为分，只能为整数，不能超过原订单支付金额及最大分账比例金额
   * @param description 分账的原因描述，分账账单中需要体现
   */
  public Receiver(String type, String account, Integer amount, String description) {
    this.type = type;
    this.account = account;
    this.amount = amount;
    this.description = description;
  }

  public String getType() {
    return type;
  }

  public String getAccount() {
    return account;
  }

  public Integer getAmount() {
    return amount;
  }

  public String getDescription() {
    return description;
  }
}
