package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants.RefundAccountSource;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * <pre>
 * 微信支付-申请退款请求参数
 * 注释中各行每个字段描述对应如下：
 * <li>字段名
 * <li>变量名
 * <li>是否必填
 * <li>类型
 * <li>示例值
 * <li>描述
 * Created by Binary Wang on 2016-10-08.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxPayRefundRequest extends WxPayBaseRequest {
  private static final String[] REFUND_ACCOUNT = new String[]{
    RefundAccountSource.RECHARGE_FUNDS,
    RefundAccountSource.UNSETTLED_FUNDS
  };
  /**
   * <pre>
   * 设备号
   * device_info
   * 否
   * String(32)
   * 13467007045764
   * 终端设备号
   * </pre>
   */
  @XStreamAlias("device_info")
  private String deviceInfo;
  /**
   * <pre>
   * 微信订单号
   * transaction_id
   * 跟out_trade_no二选一
   * String(28)
   * 1217752501201400000000000000
   * 微信生成的订单号，在支付通知中有返回
   * </pre>
   */
  @XStreamAlias("transaction_id")
  private String transactionId;
  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * 跟transaction_id二选一
   * String(32)
   * 1217752501201400000000000000
   * 商户侧传给微信的订单号
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;
  /**
   * <pre>
   * 商户退款单号
   * out_refund_no
   * 是
   * String(32)
   * 1217752501201400000000000000
   * 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
   * </pre>
   */
  @Required
  @XStreamAlias("out_refund_no")
  private String outRefundNo;
  /**
   * <pre>
   * 订单金额
   * total_fee
   * 是
   * Int
   * 100
   * 订单总金额，单位为分，只能为整数，详见支付金额
   * </pre>
   */
  @Required
  @XStreamAlias("total_fee")
  private Integer totalFee;
  /**
   * <pre>
   * 退款金额
   * refund_fee
   * 是
   * Int
   * 100
   * 退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
   * </pre>
   */
  @Required
  @XStreamAlias("refund_fee")
  private Integer refundFee;
  /**
   * <pre>
   * 货币种类
   * refund_fee_type
   * 否
   * String(8)
   * CNY
   * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
   * </pre>
   */
  @XStreamAlias("refund_fee_type")
  private String refundFeeType;
  /**
   * <pre>
   * 操作员
   * op_user_id
   * 是
   * String(32)
   * 1900000109
   * 操作员帐号, 默认为商户号
   * </pre>
   */
  //@Required
  @XStreamAlias("op_user_id")
  private String opUserId;
  /**
   * <pre>
   * 退款资金来源
   * refund_account
   * 否
   * String(30)
   * REFUND_SOURCE_RECHARGE_FUNDS
   * 仅针对老资金流商户使用，
   * <li>REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款），
   * <li>REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
   * </pre>
   */
  @XStreamAlias("refund_account")
  private String refundAccount;
  /**
   * <pre>
   * 退款原因
   * refund_account
   * 否
   * String(80)
   * 商品已售完
   * 若商户传入，会在下发给用户的退款消息中体现退款原因
   * </pre>
   */
  @XStreamAlias("refund_desc")
  private String refundDesc;

  @Override
  public void checkAndSign(WxPayConfig config, boolean isIgnoreSignType) throws WxPayException {
    if (StringUtils.isBlank(this.getOpUserId())) {
      this.setOpUserId(config.getMchId());
    }

    super.checkAndSign(config, isIgnoreSignType);
  }

  @Override
  protected void checkConstraints() throws WxPayException {
    if (StringUtils.isNotBlank(this.getRefundAccount())) {
      if (!ArrayUtils.contains(REFUND_ACCOUNT, this.getRefundAccount())) {
        throw new WxPayException(String.format("refund_account目前必须为%s其中之一,实际值：%s",
          Arrays.toString(REFUND_ACCOUNT), this.getRefundAccount()));
      }
    }

    if (StringUtils.isBlank(this.getOutTradeNo()) && StringUtils.isBlank(this.getTransactionId())) {
      throw new WxPayException("transaction_id 和 out_trade_no 不能同时为空，必须提供一个");
    }
  }

}
