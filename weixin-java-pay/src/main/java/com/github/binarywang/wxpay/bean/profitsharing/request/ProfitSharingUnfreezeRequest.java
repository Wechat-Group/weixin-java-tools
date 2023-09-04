package com.github.binarywang.wxpay.bean.profitsharing.request;

import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

import java.util.Map;

/**
 * 解冻剩余资金API请求实体
 * @author Wang GuangXin 2019/10/23 14:02
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class ProfitSharingUnfreezeRequest extends BaseWxPayRequest {

  private static final long serialVersionUID = -4265779954583596627L;

  /**
   * <pre>
   * 字段名：微信订单号.
   * 变量名：transaction_id
   * 是否必填：是
   * String(32)
   * 示例值：4208450740201411110007820472
   * 描述：微信支付订单号
   * </pre>
   */
  @XStreamAlias("transaction_id")
  @Required
  private String transactionId;

  /**
   * <pre>
   * 字段名：商户分账单号.
   * 变量名：out_order_no
   * 是否必填：是
   * String(64)
   * 示例值：P20150806125346
   * 描述：商户系统内部的分账单号，在商户系统内部唯一（单次分账、多次分账、完结分账应使用不同的商户分账单号），同一分账单号多次请求等同一次。只能是数字、大小写字母_-|*@
   * </pre>
   */
  @XStreamAlias("out_order_no")
  @Required
  private String outOrderNo;

  /**
   * <pre>
   * 字段名：分账完结描述.
   * 变量名：out_order_no
   * 是否必填：是
   * String(80)
   * 示例值：分账已完成
   * 描述：分账完结的原因描述
   * </pre>
   */
  @XStreamAlias("description")
  @Required
  private String description;

  @Override
  protected void checkConstraints() {
    this.setSignType(WxPayConstants.SignType.HMAC_SHA256);
  }

  @Override
  protected boolean ignoreSubAppId() {
    return true;
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("transaction_id", transactionId);
    map.put("out_order_no", outOrderNo);
    map.put("description", description);
  }
}
