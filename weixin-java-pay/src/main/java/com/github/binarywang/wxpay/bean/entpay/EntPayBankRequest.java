package com.github.binarywang.wxpay.bean.entpay;

import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.annotation.Required;

/**
 * <pre>
 *  企业付款到银行卡的请求对象类
 *  Created by BinaryWang on 2017/12/20.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@XStreamAlias("xml")
public class EntPayBankRequest extends BaseWxPayRequest {

  /**
   * <pre>
   * 商户企业付款单号.
   * 变量名：partner_trade_no
   * 是否必填：是
   * 示例值：1212121221227
   * 类型：string(32)
   * 描述：商户订单号，需保持唯一（只允许数字[0~9]或字母[A~Z]和[a~z]，最短8位，最长32位）
   */
  @Required
  @XStreamAlias("partner_trade_no")
  private String partnerTradeNo;

  /**
   * <pre>
   * 收款方银行卡号.
   * 传值时请传原始值
   * 变量名：enc_bank_no
   * 是否必填：是
   * 示例值：8609cb22e1774a50a930e414cc71eca06121bcd266335cda230d24a7886a8d9f
   * 类型：string(64)
   * 描述：收款方银行卡号（采用标准RSA算法，公钥由微信侧提供）,详见获取RSA加密公钥API
   *
   */
  @Required
  @XStreamAlias("enc_bank_no")
  private String encBankNo;

  /**
   * <pre>
   * 收款方用户名.
   * 传值时请传原始值
   * 变量名：enc_true_name
   * 是否必填：是
   * 示例值：ca775af5f841bdf424b2e6eb86a6e21e
   * 类型：string(64)
   * 描述：收款方用户名（采用标准RSA算法，公钥由微信侧提供）详见获取RSA加密公钥API
   */
  @Required
  @XStreamAlias("enc_true_name")
  private String encTrueName;

  /**
   * <pre>
   * 收款方开户行.
   * 变量名：bank_code
   * 是否必填：是
   * 示例值：1001
   * 类型：string(64)
   * 描述：银行卡所在开户行编号,详见银行编号列表
   */
  @Required
  @XStreamAlias("bank_code")
  private String bankCode;

  /**
   * <pre>
   * 付款金额.
   * 变量名：amount
   * 是否必填：是
   * 示例值：100000
   * 类型：int
   * 描述：付款金额：RMB分（支付总额，不含手续费） 注：大于0的整数
   */
  @Required
  @XStreamAlias("amount")
  private Integer amount;

  /**
   * <pre>
   * 付款说明.
   * 变量名：desc
   * 是否必填：否
   * 示例值：理财
   * 类型：string
   * 描述：企业付款到银行卡付款说明,即订单备注（UTF8编码，允许100个字符以内）
   */
  @Required
  @XStreamAlias("desc")
  private String description;

  @Override
  protected void checkConstraints() throws WxPayException {

  }

  @Override
  protected boolean ignoreAppid() {
    return true;
  }
}
