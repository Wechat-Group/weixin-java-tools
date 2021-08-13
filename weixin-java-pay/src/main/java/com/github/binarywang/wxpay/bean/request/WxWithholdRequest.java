package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

import java.util.Map;

/**
 * @author chenliang
 * @date 2021-08-02 5:26 下午
 *
 * <pre>
 *   发起微信委托代扣参数
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxWithholdRequest extends BaseWxPayRequest {

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
   * 货币类型
   * fee_type
   * 否
   * String(16)
   * CNY
   * 默认人民币：CNY
   * </pre>
   */
  @XStreamAlias("fee_type")
  private String feeType;

  /**
   * <pre>
   * 终端ip
   * spbill_create_ip
   * 否
   * String(16)
   * 127.0.0.1
   * 用户的客户端IP
   * </pre>
   */
  @XStreamAlias("spbill_create_ip")
  private String spbillCreateIp;

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
   * 委托代扣协议ID
   * contract_id
   * 是
   * String(32)
   * Wx234324808503234483920
   * 签约成功后微信返回的委托代扣协议ID
   * </pre>
   */
  @Required
  @XStreamAlias("contract_id")
  private String contractId;

  @Override
  protected void checkConstraints() throws WxPayException {

  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("body", body);
    map.put("detail", detail);
    map.put("attach", attach);
    map.put("out_trade_no", outTradeNo);
    map.put("total_fee", totalFee.toString());
    map.put("fee_type", feeType);
    map.put("spbill_create_ip", spbillCreateIp);
    map.put("goods_tag", goodsTag);
    map.put("notify_url", notifyUrl);
    map.put("trade_type", tradeType);
    map.put("contract_id", contractId);
  }
}
