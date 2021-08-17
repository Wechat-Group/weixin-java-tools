package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

import java.util.Map;

/**
 * @author chenliang
 * @date 2021-08-02 5:18 下午
 * <pre>
 *   支付中签约入参
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxPayEntrustRequest extends BaseWxPayRequest {

  /**
   * <pre>
   * 签约商户号
   * contract_mchid
   * 是
   * String(32)
   * 1200009811
   * 签约商户号，必须与mch_id一致
   * </pre>
   */
  @Required
  @XStreamAlias("contract_mchid")
  private String contractMchId;

  /**
   * <pre>
   * 签约APPID
   * contract_appid
   * 是
   * String(32)
   * wxcbda96de0b165486
   * 签约公众号，必须与APPID一致
   * </pre>
   */
  @Required
  @XStreamAlias("contract_appid")
  private String contractAppId;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * 是
   * String(32)
   * 123456
   * 商户系统内部的订单号，32字符内，可包含字母
   * </pre>
   */
  @Required
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 设备号
   * device_info
   * 否
   * String(32)
   * 013467007045764
   * 终端设备号，若为PC网页或公众号内则传WEB
   * </pre>
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * <pre>
   * 商品描述
   * body
   * 是
   * String(128)
   * ipad mini 16G 白色
   * 商品支付单简要描述
   * </pre>
   */
  @Required
  @XStreamAlias("body")
  private String body;

  /**
   * <pre>
   * 商品详情
   * detail
   * 否
   * String(8192)
   * ipad mini 16G 白色
   * 商品名称明细列表
   * </pre>
   */
  @XStreamAlias("detail")
  private String detail;

  /**
   * <pre>
   * 附加数据
   * attach
   * 否
   * String(127)
   * online/dev/dev1
   * 商家数据包
   * </pre>
   */
  @XStreamAlias("attach")
  private String attach;

  /**
   * <pre>
   * 回调通知url
   * notify_url
   * 是
   * String(256)
   * https://weixin.qq.com
   * 回调通知地址
   * </pre>
   */
  @Required
  @XStreamAlias("notify_url")
  private String notifyUrl;

  /**
   * <pre>
   * 总金额
   * total_fee
   * 是
   * int
   * 888
   * 订单总金额，单位分
   * </pre>
   */
  @Required
  @XStreamAlias("total_fee")
  private Integer totalFee;

  /**
   * <pre>
   * 终端ip
   * spbill_create_ip
   * 是
   * String(16)
   * 127.0.0.1
   * 用户的客户端IP
   * </pre>
   */
  @Required
  @XStreamAlias("spbill_create_ip")
  private String spbillCreateIp;

  /**
   * <pre>
   * 交易起始时间
   * time_start
   * 否
   * String(14)
   * 20201025171529
   * 订单生成时间，格式yyyyMMddHHmmss
   * </pre>
   */
  @XStreamAlias("time_start")
  private String timeStart;

  /**
   * <pre>
   * 交易结束时间
   * time_expire
   * 否
   * String(14)
   * 20201025171529
   * 订单失效时间，格式yyyyMMddHHmmss
   * </pre>
   */
  @XStreamAlias("time_expire")
  private String timeExpire;

  /**
   * <pre>
   * 商品标记
   * goods_tag
   * 否
   * String(32)
   * wxg
   * 商品标记，代金券或立减优惠功能参数
   * </pre>
   */
  @XStreamAlias("goods_tag")
  private String goodsTag;

  /**
   * <pre>
   * 交易类型
   * trade_type
   * 是
   * String(16)
   * JSAPI
   * JSAPI,MWEB
   * </pre>
   */
  @Required
  @XStreamAlias("trade_type")
  private String tradeType;

  /**
   * <pre>
   * 商品ID
   * product_id
   * 否
   * String(32)
   * 12234355463434643
   * 二维码支付必传，二维码中包含商品ID
   * </pre>
   */
  @XStreamAlias("product_id")
  private String productId;

  /**
   * <pre>
   * 指定支付方式
   * limit_pay
   * 否
   * String(32)
   * no_credit
   * no_credit--指定不能使用信用卡支付
   * </pre>
   */
  @XStreamAlias("limit_pay")
  private String limitPay;

  /**
   * <pre>
   * 用户表示
   * openid
   * 否
   * String(128)
   * oUpF4sdsidj3Jds89
   * tradetype=JSAPI 则必传
   * </pre>
   */
  @XStreamAlias("openid")
  private String openId;

  /**
   * <pre>
   * 协议模板ID
   * plan_id
   * 是
   * String(28)
   * 12535
   * 协议模板ID，分为首次签约，支付中签约，重新签约
   * </pre>
   */
  @Required
  @XStreamAlias("plan_id")
  private String planId;

  /**
   * <pre>
   * 签约协议号
   * contract_code
   * 是
   * String(32)
   * 100000
   * 商户侧签约协议号，由商户生成，只能是数字，大小写字母组成
   * </pre>
   */
  @Required
  @XStreamAlias("contract_code")
  private String contractCode;

  /**
   * <pre>
   * 请求序列号
   * request_serial
   * 是
   * int(64)
   * 1000
   * 商户请求签约时的序列号，要求唯一性，禁止使用0开头的，用户排序，纯数字
   * </pre>
   */
  @Required
  @XStreamAlias("request_serial")
  private Long requestSerial;

  /**
   * <pre>
   * 用户账户展示名称
   * contract_display_account
   * 是
   * string(32)
   * 微信代扣
   * 签约用户的名称，用户页面展示，不支持符号表情
   * </pre>
   */
  @Required
  @XStreamAlias("contract_display_account")
  private String contractDisplayAccount;

  /**
   * <pre>
   * 签约信息通知URL
   * contract_notify_url
   * 是
   * string(32)
   * https://yoursite.com
   * 签约信息回调通知URL
   * </pre>
   */
  @Required
  @XStreamAlias("contract_notify_url")
  private String contractNotifyUrl;

  /**
   * <pre>
   * 商户测的用户标识
   * contract_outerid
   * 否
   * string(32)
   * 陈*(12000002)
   * 用于多账户签约，值与contract_display_account相同即可，同一模板下唯一
   * </pre>
   */
  @XStreamAlias("contract_outerid")
  private String contractOuterId;

  @Override
  protected void checkConstraints() throws WxPayException {

  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("contract_mchid", contractMchId);
    map.put("contract_appid", contractAppId);
    map.put("out_trade_no", outTradeNo);
    map.put("device_info", deviceInfo);
    map.put("body", body);
    map.put("detail", detail);
    map.put("attach", attach);
    map.put("notify_url", notifyUrl);
    map.put("total_fee", totalFee.toString());
    map.put("spbill_create_ip", spbillCreateIp);
    map.put("time_start", timeStart);
    map.put("time_expire", timeExpire);
    map.put("goods_tag", goodsTag);
    map.put("trade_type", tradeType);
    map.put("product_id", productId);
    map.put("limit_pay", limitPay);
    map.put("openid", openId);
    map.put("plan_id", planId);
    map.put("contract_code", contractCode);
    map.put("request_serial", requestSerial.toString());
    map.put("contract_display_account", contractDisplayAccount);
    map.put("contract_notify_url", contractNotifyUrl);
    map.put("contract_outerid", contractOuterId);
  }
}
