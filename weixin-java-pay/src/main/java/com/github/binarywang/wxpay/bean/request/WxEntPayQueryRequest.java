package com.github.binarywang.wxpay.bean.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import me.chanjar.weixin.common.annotation.Required;
import me.chanjar.weixin.common.util.ToStringUtils;

/**
 * <pre>
 * 企业付款请求对象
 * 注释中各行每个字段描述对应如下：
 * <li>字段名
 * <li>变量名
 * <li>是否必填
 * <li>类型
 * <li>示例值
 * <li>描述
 * </pre>
 * Created by Binary Wang on 2016/10/19.
 *
 * @author binarywang (https://github.com/binarywang)
 */
@XStreamAlias("xml")
public class WxEntPayQueryRequest extends WxPayBaseRequest {
  /**
   * <pre>
   * 商户订单号
   * partner_trade_no
   * 是
   * 10000098201411111234567890
   * String
   * 商户订单号
   * </pre>
   */
  @Required
  @XStreamAlias("partner_trade_no")
  private String partnerTradeNo;

  public String getPartnerTradeNo() {
    return this.partnerTradeNo;
  }

  public void setPartnerTradeNo(String partnerTradeNo) {
    this.partnerTradeNo = partnerTradeNo;
  }

  @Override
  protected void checkConstraints() {
    //do nothing
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

}
