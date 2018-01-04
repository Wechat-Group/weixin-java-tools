package com.github.binarywang.wxpay.constant;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <pre>
 * 微信支付常量类
 * Created by Binary Wang on 2017-8-24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxPayConstants {

  /**
   * 拉取订单评价数据接口的参数中日期格式
   */
  public static final Format QUERY_COMMENT_DATE_FORMAT =  FastDateFormat.getInstance("yyyyMMddHHmmss");

  /**
   * 校验用户姓名选项，企业付款时使用
   */
  public static class CheckNameOption {
    /**
     * 不校验真实姓名
     */
    public static final String NO_CHECK = "NO_CHECK";

    /**
     * 强校验真实姓名
     */
    public static final String FORCE_CHECK = "FORCE_CHECK";
  }

  /**
   * 压缩账单的类型
   */
  public static class TarType {
    /**
     * 固定值：GZIP，返回格式为.gzip的压缩包账单
     */
    public static final String GZIP = "GZIP";
  }

  /**
   * 账单类型
   */
  public static class BillType {
    /**
     * 查询红包时使用：通过商户订单号获取红包信息
     */
    public static final String MCHT = "MCHT";

    //以下为下载对账单时的账单类型
    /**
     * 返回当日所有订单信息，默认值
     */
    public static final String ALL = "ALL";
    /**
     * 返回当日成功支付的订单
     */
    public static final String SUCCESS = "SUCCESS";
    /**
     * 返回当日退款订单
     */
    public static final String REFUND = "REFUND";
    /**
     * 返回当日充值退款订单（相比其他对账单多一栏“返还手续费”）
     */
    public static final String RECHARGE_REFUND = "RECHARGE_REFUND";
  }

  /**
   * 交易类型
   */
  public static class TradeType {
    /**
     * 原生扫码支付
     */
    public static final String NATIVE = "NATIVE";

    /**
     * App支付
     */
    public static final String APP = "APP";

    /**
     * 公众号支付
     */
    public static final String JSAPI = "JSAPI";

    /**
     * H5支付
     */
    public static final String MWEB = "MWEB";

    /**
     * 刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
     */
    public static final String MICROPAY = "MICROPAY";
  }

  /**
   * 签名类型
   */
  public static class SignType {
    public static final String HMAC_SHA256 = "HMAC-SHA256";
    public static final String MD5 = "MD5";
    public static final List<String> ALL_SIGN_TYPES = Lists.newArrayList(HMAC_SHA256, MD5);
  }

  /**
   * 限定支付方式
   */
  public static class LimitPay {
    /**
     * no_credit--指定不能使用信用卡支付
     */
    public static final String NO_CREDIT = "no_credit";
  }

  /**
   * 业务结果代码
   */
  public static class ResultCode {
    /**
     * 成功
     */
    public static final String SUCCESS = "SUCCESS";

    /**
     * 失败
     */
    public static final String FAIL = "FAIL";
  }

  /**
   * 退款资金来源
   */
  public static class RefundAccountSource {
    /**
     * 可用余额退款/基本账户
     */
    public static final String RECHARGE_FUNDS = "REFUND_SOURCE_RECHARGE_FUNDS";

    /**
     * 未结算资金退款
     */
    public static final String UNSETTLED_FUNDS = "REFUND_SOURCE_UNSETTLED_FUNDS";

  }

  /**
   * 退款渠道
   */
  public static class RefundChannel {
    /**
     * 原路退款
     */
    public static final String ORIGINAL = "ORIGINAL";

    /**
     * 退回到余额
     */
    public static final String BALANCE = "BALANCE";

    /**
     * 原账户异常退到其他余额账户
     */
    public static final String OTHER_BALANCE = "OTHER_BALANCE";

    /**
     * 原银行卡异常退到其他银行卡
     */
    public static final String OTHER_BANKCARD = "OTHER_BANKCARD";
  }

  /**
   * 交易状态
   */
  public static class WxpayTradeStatus {
    /**
     * 支付成功
     */
    public static final String SUCCESS = "SUCCESS";

    /**
     * 支付失败(其他原因，如银行返回失败)
     */
    public static final String PAY_ERROR = "PAYERROR";

    /**
     * 用户支付中
     */
    public static final String USER_PAYING = "USERPAYING";

    /**
     * 已关闭
     */
    public static final String CLOSED = "CLOSED";

    /**
     * 未支付
     */
    public static final String NOTPAY = "NOTPAY";

    /**
     * 转入退款
     */
    public static final String REFUND = "REFUND";

    /**
     * 已撤销（刷卡支付）
     */
    public static final String REVOKED = "REVOKED";
  }

  /**
   * 退款状态
   */
  public static class RefundStatus {
    /**
     * 退款成功
     */
    public static final String SUCCESS = "SUCCESS";

    /**
     * 退款关闭
     */
    public static final String REFUND_CLOSE = "REFUNDCLOSE";

    /**
     * 退款处理中
     */
    public static final String PROCESSING = "PROCESSING";

    /**
     * 退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。
     */
    public static final String CHANGE = "CHANGE";
  }

  /**
   * 关闭订单结果错误代码
   */
  public static class OrderCloseResultErrorCode {
    /**
     * 订单已支付
     */
    public static final String ORDER_PAID = "ORDERPAID";

    /**
     * 系统错误
     */
    public static final String SYSTEM_ERROR = "SYSTEMERROR";

    /**
     * 订单不存在
     */
    public static final String ORDER_NOT_EXIST = "ORDERNOTEXIST";

    /**
     * 订单已关闭
     */
    public static final String ORDER_CLOSED = "ORDERCLOSED";

    /**
     * 签名错误
     */
    public static final String SIGN_ERROR = "SIGNERROR";

    /**
     * 未使用POST传递参数
     */
    public static final String REQUIRE_POST_METHOD = "REQUIRE_POST_METHOD";

    /**
     * XML格式错误
     */
    public static final String XML_FORMAT_ERROR = "XML_FORMAT_ERROR";

    /**
     * 订单状态错误
     */
    public static final String TRADE_STATE_ERROR = "TRADE_STATE_ERROR";
  }
}
