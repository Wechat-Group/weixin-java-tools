package com.github.binarywang.wxpay.bean.coupon;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

/**
 * <pre>
 * 查询代金券信息响应结果类
 * Created by Binary Wang on 2017-7-15.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxPayCouponInfoQueryResult extends BaseWxPayResult {
  /**
   * <pre>
   * 字段名：设备号.
   * 变量名：device_info
   * 是否必填：否
   * 示例值：123456sb
   * 类型：String(32)
   * 说明：微信支付分配的终端设备号，
   * </pre>
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * <pre>
   * 字段名：批次ID.
   * 变量名：coupon_stock_id
   * 是否必填：是
   * 示例值：1567
   * 类型：String
   * 说明：代金券批次Id
   * </pre>
   */
  @XStreamAlias("coupon_stock_id")
  private String couponStockId;

  /**
   * <pre>
   * 字段名：代金券id.
   * 变量名：coupon_id
   * 是否必填：是
   * 示例值：4242
   * 类型：String
   * 说明：代金券id
   * </pre>
   */
  @XStreamAlias("coupon_id")
  private String couponId;

  /**
   * <pre>
   * 字段名：代金券面额.
   * 变量名：coupon_value
   * 是否必填：是
   * 示例值：4
   * 类型：Unsinged int
   * 说明：代金券面值,单位是分
   * </pre>
   */
  @XStreamAlias("coupon_value")
  private Integer couponValue;

  /**
   * <pre>
   * 字段名：代金券使用门槛.
   * 变量名：coupon_minimum 微信文档有误
   * 是否必填：是
   * 示例值：10
   * 类型：Unsinged int
   * 说明：代金券使用最低限额,单位是分
   * </pre>
   */
  @XStreamAlias("coupon_minimum")
  private Integer couponMinimum;

  /**
   * <pre>
   * 字段名：代金券名称.
   * 变量名：coupon_name
   * 是否必填：是
   * 示例值：测试代金券
   * 类型：String
   * 说明：代金券名称
   * </pre>
   */
  @XStreamAlias("coupon_name")
  private String couponName;

  /**
   * <pre>
   * 字段名：代金券状态.
   * 变量名：coupon_state
   * 是否必填：是
   * 示例值：SENDED
   * 类型：String
   * 说明：代金券状态：SENDED-可用，USED-已实扣，EXPIRED-已过期
   * </pre>
   */
  @XStreamAlias("coupon_state")
  private String couponState;

  /**
   * <pre>
   * 字段名：代金券描述.
   * 变量名：coupon_desc
   * 是否必填：是
   * 示例值：微信支付-代金券
   * 类型：String
   * 说明：代金券描述
   * </pre>
   */
  @XStreamAlias("coupon_desc")
  private String couponDesc;

  /**
   * <pre>
   * 字段名：实际优惠金额.
   * 变量名：coupon_use_value
   * 是否必填：是
   * 示例值：0
   * 类型：Unsinged int
   * 说明：代金券实际使用金额
   * </pre>
   */
  @XStreamAlias("coupon_use_value")
  private Integer couponUseValue;

  /**
   * <pre>
   * 字段名：优惠剩余可用额.
   * 变量名：coupon_remain_value
   * 是否必填：是
   * 示例值：4
   * 类型：Unsinged int
   * 说明：代金券剩余金额：部分使用情况下，可能会存在券剩余金额
   * </pre>
   */
  @XStreamAlias("coupon_remain_value")
  private Integer couponRemainValue;

  /**
   * <pre>
   * 字段名：生效开始时间.
   * 变量名：begin_time
   * 是否必填：是
   * 示例值：1943787483
   * 类型：String
   * 说明：格式为时间戳
   * </pre>
   */
  @XStreamAlias("begin_time")
  private String beginTime;

  /**
   * <pre>
   * 字段名：生效结束时间.
   * 变量名：end_time
   * 是否必填：是
   * 示例值：1943787484
   * 类型：String
   * 说明：格式为时间戳
   * </pre>
   */
  @XStreamAlias("end_time")
  private String endTime;

  /**
   * <pre>
   * 字段名：发放时间.
   * 变量名：send_time
   * 是否必填：是
   * 示例值：1943787420
   * 类型：String
   * 说明：格式为时间戳
   * </pre>
   */
  @XStreamAlias("send_time")
  private String sendTime;

  /**
   * <pre>
   * 字段名：消耗方商户id.
   * 变量名：consumer_mch_id
   * 是否必填：否
   * 示例值：10000098
   * 类型：String
   * 说明：代金券使用后，消耗方商户id
   * </pre>
   */
  @XStreamAlias("consumer_mch_id")
  private String consumerMchId;

  /**
   * <pre>
   * 字段名：发放来源.
   * 变量名：send_source
   * 是否必填：是
   * 示例值：FULL_SEND
   * 类型：String
   * 说明：代金券发放来源：FULL_SEND-满送 NORMAL-普通发放场景
   * </pre>
   */
  @XStreamAlias("send_source")
  private String sendSource;

  /**
   * <pre>
   * 字段名：是否允许部分使用.
   * 变量名：is_partial_use
   * 是否必填：否
   * 示例值：1
   * 类型：String
   * 说明：该代金券是否允许部分使用标识：1-表示支持部分使用
   * </pre>
   */
  @XStreamAlias("is_partial_use")
  private String isPartialUse;

  @Override
  protected void loadXml(Document d) {
    deviceInfo = readXmlString(d, "device_info");
    couponStockId = readXmlString(d, "coupon_stock_id");
    couponId = readXmlString(d, "coupon_id");
    couponValue = readXmlInteger(d, "coupon_value");
    couponMinimum = readXmlInteger(d, "coupon_minimum");
    couponName = readXmlString(d, "coupon_name");
    couponState = readXmlString(d, "coupon_state");
    couponDesc = readXmlString(d, "coupon_desc");
    couponUseValue = readXmlInteger(d, "coupon_use_value");
    couponRemainValue = readXmlInteger(d, "coupon_remain_value");
    beginTime = readXmlString(d, "begin_time");
    endTime = readXmlString(d, "end_time");
    sendTime = readXmlString(d, "send_time");
    consumerMchId = readXmlString(d, "consumer_mch_id");
    sendSource = readXmlString(d, "send_source");
    isPartialUse = readXmlString(d, "is_partial_use");
  }
}
