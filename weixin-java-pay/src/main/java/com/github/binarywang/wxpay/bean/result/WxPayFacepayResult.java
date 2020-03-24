package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

/**
 * <pre>
 * 提交书刷脸支付接口响应结果对象类
 * Created by Jmdhappy on 2019-09-05.
 * </pre>
 *
 * @author <a href="https://github.com/jmdhappy/xxpay-master">XxPay</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxPayFacepayResult extends BaseWxPayResult {

  private static final long serialVersionUID = -4116580976046716911L;

  /**
   * <pre>
   * 设备号.
   * device_info
   * 否
   * String(32)
   * 013467007045764
   * 调用接口提交的终端设备号
   * </pre>
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * <pre>
   * 用户标识.
   * openid
   * 是
   * String(128)
   * Y
   * 用户在商户appid 下的唯一标识
   * </pre>
   **/
  @XStreamAlias("openid")
  private String openid;

  /**
   * <pre>
   * 是否关注公众账号.
   * is_subscribe
   * 是
   * String(1)
   * Y
   * 用户是否关注公众账号，仅在公众账号类型支付有效，取值范围：Y或N;Y-关注;N-未关注
   * </pre>
   **/
  @XStreamAlias("is_subscribe")
  private String isSubscribe;

  /**
   * <pre>
   * 用户子标识.
   * sub_openid
   * 否
   * String(128)
   * Y
   * 子商户appid下用户唯一标识，如需返回则请求时需要传sub_appid
   * </pre>
   **/
  @XStreamAlias("sub_openid")
  private String subOpenid;

  /**
   * <pre>
   * 是否关注子公众账号.
   * sub_is_subscribe
   * 是
   * String(1)
   * Y
   * 用户是否关注子公众账号，仅在公众账号类型支付有效，取值范围：Y或N;Y-关注;N-未关注
   * </pre>
   **/
  @XStreamAlias("sub_is_subscribe")
  private String subsSubscribe;

  /**
   * <pre>
   * 交易类型.
   * trade_type
   * 是
   * String(16)
   * FACEPAY
   * 支付类型为 FACEPAY(即刷脸支付)
   * </pre>
   **/
  @XStreamAlias("trade_type")
  private String tradeType;

  /**
   * <pre>
   * 付款银行.
   * bank_type
   * 是
   * String(32)
   * CMC
   * 银行类型，采用字符串类型的银行标识，值列表详见银行类型
   * </pre>
   **/
  @XStreamAlias("bank_type")
  private String bankType;

  /**
   * <pre>
   * 货币类型.
   * fee_type
   * 否
   * String(16)
   * CNY
   * 符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
   * </pre>
   **/
  @XStreamAlias("fee_type")
  private String feeType;

  /**
   * <pre>
   * 订单金额.
   * total_fee
   * 是
   * Int
   * 888
   * 订单总金额，单位为分，只能为整数，详见支付金额
   * </pre>
   **/
  @XStreamAlias("total_fee")
  private Integer totalFee;

  /**
   * <pre>
   * 现金支付货币类型.
   * cash_fee_type
   * 否
   * String(16)
   * CNY
   * 符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
   * </pre>
   **/
  @XStreamAlias("cash_fee_type")
  private String cashFeeType;

  /**
   * <pre>
   * 现金支付金额.
   * cash_fee
   * 是
   * Int
   * 100
   * 订单现金支付金额，详见支付金额
   * </pre>
   **/
  @XStreamAlias("cash_fee")
  private Integer cashFee;

  /**
   * <pre>
   * 微信支付订单号.
   * transaction_id
   * 是
   * String(32)
   * 1217752501201407033233368018
   * 微信支付订单号
   * </pre>
   **/
  @XStreamAlias("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 商户订单号.
   * out_trade_no
   * 是
   * String(32)
   * 1217752501201407033233368018
   * 商户系统的订单号，与请求一致。
   * </pre>
   **/
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 商品详情.
   * detail
   * 否
   * String(8192)
   * 与提交数据一致
   * 实际提交的返回
   * </pre>
   **/
  @XStreamAlias("detail")
  private String detail;

  /**
   * <pre>
   * 商家数据包.
   * attach
   * 否
   * String(128)
   * 123456
   * 商家数据包，原样返回
   * </pre>
   **/
  @XStreamAlias("attach")
  private String attach;

  /**
   * <pre>
   * 营销详情.
   * promotion_detail
   * 否
   * String(6000)
   * 示例见下文
   * 新增返回，单品优惠功能字段，需要接入请见详细说明
   * </pre>
   **/
  @XStreamAlias("promotion_detail")
  private String promotionDetail;

  /**
   * <pre>
   * 支付完成时间.
   * time_end
   * 是
   * String(14)
   * 20141030133525
   * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。详见时间规则
   * </pre>
   **/
  @XStreamAlias("time_end")
  private String timeEnd;

  /**
   * 从XML结构中加载额外的熟悉
   *
   * @param d Document
   */
  @Override
  protected void loadXML(Document d) {
    deviceInfo = readXMLString(d, "device_info");
    openid = readXMLString(d, "openid");
    isSubscribe = readXMLString(d, "is_subscribe");
    subOpenid = readXMLString(d, "sub_openid");
    subsSubscribe = readXMLString(d, "sub_is_subscribe");
    tradeType = readXMLString(d, "trade_type");
    bankType = readXMLString(d, "bank_type");
    feeType = readXMLString(d, "fee_type");
    totalFee = readXMLInteger(d, "total_fee");
    cashFeeType = readXMLString(d, "cash_fee_type");
    cashFee = readXMLInteger(d, "cash_fee");
    transactionId = readXMLString(d, "transaction_id");
    outTradeNo = readXMLString(d, "out_trade_no");
    detail = readXMLString(d, "detail");
    attach = readXMLString(d, "attach");
    promotionDetail = readXMLString(d, "promotion_detail");
    timeEnd = readXMLString(d, "time_end");
  }

}
