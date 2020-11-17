package com.github.binarywang.wxpay.bean.result;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 记账时间:2018-02-01 04:21:23 微信支付业务单号:50000305742018020103387128253 资金流水单号:1900009231201802015884652186 业务名称:退款
 * 业务类型:退款 收支类型:支出 收支金额（元）:0.02 账户结余（元）:0.17 资金变更提交申请人:system 备注:缺货 业务凭证号:REF4200000068201801293084726067
 *
 * @author cwivan
 */
@Data
@NoArgsConstructor
public class WxPayFundFlowBaseResult implements Serializable {
  private static final long serialVersionUID = 4474557532904682718L;

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }

  /**
   * 记账时间.
   */
  private String BillingTime;
  /**
   * 微信支付业务单号.
   */
  private String bizTransactionId;
  /**
   * 资金流水单号.
   */
  private String fundFlowId;
  /**
   * 业务名称
   */
  private String bizName;
  /**
   * 业务类型.
   */
  private String bizType;
  /**
   * 收支类型.
   */
  private String financialType;
  /**
   * 收支金额（元）.
   */
  private String financialFee;
  /**
   * 账户结余（元）.
   */
  private String AccountBalance;
  /**
   * 资金变更提交申请人.
   */
  private String fundApplicant;
  /**
   * 备注.
   */
  private String memo;
  /**
   * 业务凭证号.
   */
  private String bizVoucherId;

}
