package com.github.binarywang.wxpay.bean.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

/**
 * <pre>
 *  提交刷卡支付请求对象类
 * Created by Binary Wang on 2017-3-23.
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
public class WxPayMicropayRequest extends BaseWxPayRequest {
  /**
   * <pre>
   * 商品描述
   * body
   * 是
   * String(128)
   * image形象店-深圳腾大- QQ公仔
   * 商品简单描述，该字段须严格按照规范传递，具体请见参数规定
   **/
  @Required
  @XStreamAlias("body")
  private String body;

  /**
   * <pre>
   * 商品详情
   * detail
   * 否
   * String(6000)
   *
   * 单品优惠功能字段，需要接入请见详细说明
   **/
  @XStreamAlias("detail")
  private String detail;

  /**
   * <pre>
   * 附加数据
   * attach
   * 否
   * String(127)
   * 说明
   * 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
   **/
  @XStreamAlias("attach")
  private String attach;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * 是
   * String(32)
   * 1217752501201407033233368018
   * 商户系统内部的订单号,32个字符内、可包含字母,其他说明见商户订单号
   **/
  @Required
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 订单金额
   * total_fee
   * 是
   * Int
   * 888
   * 订单总金额，单位为分，只能为整数，详见支付金额
   **/
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
   * 符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
   **/
  @XStreamAlias("fee_type")
  private String feeType;

  /**
   * <pre>
   * 终端IP
   * spbill_create_ip
   * 是
   * String(16)
   * 8.8.8.8
   * 调用微信支付API的机器IP
   **/
  @Required
  @XStreamAlias("spbill_create_ip")
  private String spbillCreateIp;

  /**
   * <pre>
   * 商品标记
   * goods_tag
   * 否
   * String(32)
   * 1234
   * 商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
   **/
  @XStreamAlias("goods_tag")
  private String goodsTag;

  /**
   * <pre>
   * 指定支付方式
   * limit_pay
   * 否
   * String(32)
   * no_credit
   * no_credit--指定不能使用信用卡支付
   **/
  @XStreamAlias("limit_pay")
  private String limitPay;

  /**
   * <pre>
   * 授权码
   * auth_code
   * 是
   * String(128)
   * 120061098828009406
   * 扫码支付授权码，设备读取用户微信中的条码或者二维码信息注：用户刷卡条形码规则：18位纯数字，以10、11、12、13、14、15开头）
   **/
  @Required
  @XStreamAlias("auth_code")
  private String authCode;

  @Override
  protected void checkConstraints() {
    //do nothing
  }

}
