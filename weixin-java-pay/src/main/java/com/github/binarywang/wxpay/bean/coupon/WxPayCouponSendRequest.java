package com.github.binarywang.wxpay.bean.coupon;

import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

/**
 * <pre>
 * 发送代金券请求对象类
 * Created by Binary Wang on 2017-7-15.
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
public class WxPayCouponSendRequest extends BaseWxPayRequest {
  /**
   * <pre>
   * 字段名：代金券批次id
   * 变量名：coupon_stock_id
   * 是否必填：是
   * 示例值：1757
   * 类型：String
   * 说明：代金券批次id
   * </pre>
   */
  @Required
  @XStreamAlias("coupon_stock_id")
  private String couponStockId;

  /**
   * <pre>
   * 字段名：openid记录数
   * 变量名：openid_count
   * 是否必填：是
   * 示例值：1
   * 类型：int
   * 说明：openid记录数（目前支持num=1）
   * </pre>
   */
  @Required
  @XStreamAlias("openid_count")
  private Integer openidCount;

  /**
   * <pre>
   * 字段名：商户单据号
   * 变量名：partner_trade_no
   * 是否必填：是
   * 示例值：1000009820141203515766
   * 类型：String
   * 说明：商户此次发放凭据号（格式：商户id+日期+流水号），商户侧需保持唯一性
   * </pre>
   */
  @Required
  @XStreamAlias("partner_trade_no")
  private String partnerTradeNo;

  /**
   * <pre>
   * 字段名：用户openid
   * 变量名：openid
   * 是否必填：是
   * 示例值：onqOjjrXT-776SpHnfexGm1_P7iE
   * 类型：String
   * 说明：Openid信息，用户在appid下的openid。
   * </pre>
   */
  @Required
  @XStreamAlias("openid")
  private String openid;

  /**
   * <pre>
   * 字段名：操作员
   * 变量名：op_user_id
   * 是否必填：否
   * 示例值：10000098
   * 类型：String(32)
   * 说明：操作员帐号, 默认为商户号,可在商户平台配置操作员对应的api权限
   * </pre>
   */
  @XStreamAlias("op_user_id")
  private String opUserId;

  /**
   * <pre>
   * 字段名：设备号
   * 变量名：device_info
   * 是否必填：否
   * 示例值：
   * 类型：String(32)
   * 说明：微信支付分配的终端设备号
   * </pre>
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * <pre>
   * 字段名：协议版本
   * 变量名：version
   * 是否必填：否
   * 示例值：1.0
   * 类型：String(32)
   * 说明：默认1.0
   * </pre>
   */
  @XStreamAlias("version")
  private String version;

  /**
   * <pre>
   * 字段名：协议类型
   * 变量名：type
   * 是否必填：否
   * 示例值：XML
   * 类型：String(32)
   * 说明：XML【目前仅支持默认XML】
   * </pre>
   */
  @XStreamAlias("type")
  private String type;

  @Override
  protected void checkConstraints() {
    //do nothing
  }
}
