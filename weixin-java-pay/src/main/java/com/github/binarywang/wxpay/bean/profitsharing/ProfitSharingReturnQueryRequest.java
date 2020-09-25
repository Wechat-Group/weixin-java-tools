package com.github.binarywang.wxpay.bean.profitsharing;

import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author Wang GuangXin 2019/10/23 15:32
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class ProfitSharingReturnQueryRequest extends BaseWxPayRequest {
  private static final long serialVersionUID = -8838464614726086009L;
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
   * 描述：调用回退接口提供的商户系统内部的回退单号
   * </pre>
   */
  @Required
  @XStreamAlias("out_return_no")
  private String outReturnNo;

  @Override
  protected void checkConstraints() throws WxPayException {
    if (StringUtils.isBlank(orderId) && StringUtils.isBlank(outOrderNo)) {
      throw new WxPayException("order_id 和 outOrderNo 必须有一个存在");
    }
    this.setSignType(WxPayConstants.SignType.HMAC_SHA256);
  }

  @Override
  protected boolean ignoreSubAppId() {
    return super.ignoreSubAppId();
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("order_id", orderId);
    map.put("out_order_no", outOrderNo);
    map.put("out_return_no", outReturnNo);
  }
}
