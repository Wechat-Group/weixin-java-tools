package com.github.binarywang.wxpay.bean.profitsharing;

import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Wang GuangXin 2019/10/23 14:27
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class ProfitSharingReturnRequest extends BaseWxPayRequest {
  private static final long serialVersionUID = 5926280401474809744L;
  /**
   * <pre>
   * 字段名：微信分账单号.
   * 变量名：order_id
   * 是否必填：二选一
   * string(64)
   * 示例值：3008450740201411110007820472
   * 描述：原发起分账请求时，微信返回的微信分账单号，与商户分账单号一一对应。
   * 微信分账单号与商户分账单号二选一填写
   * </pre>
   */
  @XStreamAlias("order_id")
  private String orderId;

  /**
   * <pre>
   * 字段名：商户分账单号.
   * 变量名：out_order_no
   * 是否必填：二选一
   * Sstring(64)
   * 示例值：P20180806125346
   * 描述：原发起分账请求时使用的商户后台系统的分账单号。
   * 微信分账单号与商户分账单号二选一填写
   * </pre>
   */
  @XStreamAlias("out_order_no")
  private String outOrderNo;

  /**
   * <pre>
   * 字段名：商户回退单号.
   * 变量名：out_return_no
   * 是否必填：是
   * string(64)
   * 示例值：R20190516001
   * 描述：此回退单号是商户在自己后台生成的一个新的回退单号，在商户后台唯一
   * 只能是数字、大小写字母_-|*@ ，同一回退单号多次请求等同一次。
   * </pre>
   */
  @Required
  @XStreamAlias("out_return_no")
  private String outReturnNo;

  /**
   * <pre>
   * 字段名：回退方类型.
   * 变量名：return_account_type
   * 是否必填：是
   * String(32)
   * 示例值：MERCHANT_ID
   * 描述：枚举值：
   * MERCHANT_ID：商户ID
   * 暂时只支持从商户接收方回退分账金额
   * </pre>
   */
  @Required
  @XStreamAlias("return_account_type")
  private String returnAccountType;

  /**
   * <pre>
   * 字段名：回退方账号.
   * 变量名：return_account
   * 是否必填：是
   * String(64)
   * 示例值：86693852
   * 描述：回退方类型是MERCHANT_ID时，填写商户ID
   * 只能对原分账请求中成功分给商户接收方进行回退
   * </pre>
   */
  @Required
  @XStreamAlias("return_account")
  private String returnAccount;

  /**
   * <pre>
   * 字段名：回退金额.
   * 变量名：return_amount
   * 是否必填：是
   * int
   * 示例值：888
   * 描述：需要从分账接收方回退的金额，单位为分，只能为整数，不能超过原始分账单分出给该接收方的金额
   * </pre>
   */
  @Required
  @XStreamAlias("return_amount")
  private Integer returnAmount;

  /**
   * <pre>
   * 字段名：回退描述.
   * 变量名：description
   * 是否必填：是
   * String(80)
   * 示例值：用户退款
   * 描述：分账回退的原因描述
   * </pre>
   */
  @Required
  @XStreamAlias("description")
  private String description;


  @Override
  protected void checkConstraints() throws WxPayException {
    if (StringUtils.isBlank(orderId) && StringUtils.isBlank(outOrderNo)) {
      throw new WxPayException("order_id 和 outOrderNo 必须有一个存在");
    }
    this.setSignType(WxPayConstants.SignType.HMAC_SHA256);
  }
}
