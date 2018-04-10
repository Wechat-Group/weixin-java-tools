package com.github.binarywang.wxpay.bean.result;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;

import java.util.List;

/**
 * <pre>
 *  查询订单 返回结果对象
 * Created by Binary Wang on 2016-10-24.
 * 注释中各行每个字段描述对应如下：
 * <li>字段名
 * <li>变量名
 * <li>是否必填
 * <li>类型
 * <li>示例值
 * <li>描述
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxPayOrderQueryResult extends BaseWxPayResult {

  /**
   * <pre>
   * 字段名：营销详情.
   * 变量名：promotion_detail
   * 是否必填：否，单品优惠才有
   * 类型：String(6000)
   * 示例值：[{"promotion_detail":[{"promotion_id":"109519","name":"单品惠-6","scope":"SINGLE","type":"DISCOUNT","amount":5,"activity_id":"931386","wxpay_contribute":0,"merchant_contribute":0,"other_contribute":5,"goods_detail":[{"goods_id":"a_goods1","goods_remark":"商品备注","quantity":7,"price":1,"discount_amount":4},{"goods_id":"a_goods2","goods_remark":"商品备注","quantity":1,"price":2,"discount_amount":1}]}]}
   * 描述：单品优惠专用参数，详见https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_201&index=3
   * </pre>
   */
  @XStreamAlias("promotion_detail")
  private String promotionDetail;

  /**
   * <pre>设备号
   * device_info
   * 否
   * String(32)
   * 013467007045764
   * 微信支付分配的终端设备号，
   * </pre>
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * <pre>用户标识
   * openid
   * 是
   * String(128)
   * oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
   * 用户在商户appid下的唯一标识
   * </pre>
   */
  @XStreamAlias("openid")
  private String openid;

  /**
   * <pre>是否关注公众账号
   * is_subscribe
   * 否
   * String(1)
   * Y
   * 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
   * </pre>
   */
  @XStreamAlias("is_subscribe")
  private String isSubscribe;

  /**
   * <pre>交易类型
   * trade_type
   * 是
   * String(16)
   * JSAPI
   * 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY，详细说明见参数规定
   * </pre>
   */
  @XStreamAlias("trade_type")
  private String tradeType;

  /**
   * <pre>交易状态
   * trade_state
   * 是
   * String(32)
   * SUCCESS
   * SUCCESS—支付成功,REFUND—转入退款,NOTPAY—未支付,CLOSED—已关闭,REVOKED—已撤销（刷卡支付）,USERPAYING--用户支付中,PAYERROR--支付失败(其他原因，如银行返回失败)
   * </pre>
   */
  @XStreamAlias("trade_state")
  private String tradeState;

  /**
   * <pre>付款银行
   * bank_type
   * 是
   * String(16)
   * CMC
   * 银行类型，采用字符串类型的银行标识
   * </pre>
   */
  @XStreamAlias("bank_type")
  private String bankType;

  /**
   * <pre>订单金额
   * total_fee
   * 是
   * Int
   * 100
   * 订单总金额，单位为分
   * </pre>
   */
  @XStreamAlias("total_fee")
  private Integer totalFee;

  /**
   * <pre>应结订单金额
   * settlement_total_fee
   * 否
   * Int
   * 100
   * 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
   * </pre>
   */
  @XStreamAlias("settlement_total_fee")
  private Integer settlementTotalFee;

  /**
   * <pre>货币种类
   * fee_type
   * 否
   * String(8)
   * CNY
   * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
   * </pre>
   */
  @XStreamAlias("fee_type")
  private String feeType;

  /**
   * <pre>现金支付金额
   * cash_fee
   * 是
   * Int
   * 100
   * 现金支付金额订单现金支付金额，详见支付金额
   * </pre>
   */
  @XStreamAlias("cash_fee")
  private Integer cashFee;

  /**
   * <pre>现金支付货币类型
   * cash_fee_type
   * 否
   * String(16)
   * CNY
   * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
   * </pre>
   */
  @XStreamAlias("cash_fee_type")
  private String cashFeeType;

  /**
   * <pre>代金券金额
   * coupon_fee
   * 否
   * Int
   * 100
   * “代金券”金额<=订单金额，订单金额-“代金券”金额=现金支付金额，详见支付金额
   * </pre>
   */
  @XStreamAlias("coupon_fee")
  private Integer couponFee;

  /**
   * <pre>代金券使用数量
   * coupon_count
   * 否
   * Int
   * 1
   * 代金券使用数量
   * </pre>
   */
  @XStreamAlias("coupon_count")
  private Integer couponCount;

  private List<Coupon> coupons;
  /**
   * <pre>微信支付订单号
   * transaction_id
   * 是
   * String(32)
   * 1009660380201506130728806387
   * 微信支付订单号
   * </pre>
   */
  @XStreamAlias("transaction_id")
  private String transactionId;
  /**
   * <pre>商户订单号
   * out_trade_no
   * 是
   * String(32)
   * 20150806125346
   * 商户系统的订单号，与请求一致。
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;
  /**
   * <pre>附加数据
   * attach
   * 否
   * String(128)
   * 深圳分店
   * 附加数据，原样返回
   * </pre>
   */
  @XStreamAlias("attach")
  private String attach;
  /**
   * <pre>支付完成时间
   * time_end
   * 是
   * String(14)
   * 20141030133525
   * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
   * </pre>
   */
  @XStreamAlias("time_end")
  private String timeEnd;
  /**
   * <pre>交易状态描述
   * trade_state_desc
   * 是
   * String(256)
   * 支付失败，请重新下单支付
   * 对当前查询订单状态的描述和下一步操作的指引
   * </pre>
   */
  @XStreamAlias("trade_state_desc")
  private String tradeStateDesc;

  /**
   * 通过xml组装coupons属性内容
   */
  public void composeCoupons() {
    if (this.couponCount != null && this.couponCount > 0) {
      this.coupons = Lists.newArrayList();
      for (int i = 0; i < this.couponCount; i++) {
        this.coupons.add(new Coupon(this.getXmlValue("xml/coupon_type_" + i),
          this.getXmlValue("xml/coupon_id_" + i),
          this.getXmlValueAsInt("xml/coupon_fee_" + i)));
      }
    }
  }

  @Data
  @Builder(builderMethodName = "newBuilder")
  @AllArgsConstructor
  public static class Coupon {
    /**
     * <pre>代金券类型
     * coupon_type_$n
     * 否
     * String
     * CASH
     * <li>CASH--充值代金券
     * <li>NO_CASH---非充值代金券
     * 	订单使用代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_$0
     * </pre>
     */
    private String couponType;

    /**
     * <pre>代金券ID
     * coupon_id_$n
     * 否
     * String(20)
     * 10000
     * 代金券ID, $n为下标，从0开始编号
     * </pre>
     */
    private String couponId;

    /**
     * <pre>单个代金券支付金额
     * coupon_fee_$n
     * 否
     * Int
     * 100
     * 单个代金券支付金额, $n为下标，从0开始编号
     * </pre>
     */
    private Integer couponFee;

  }
}
