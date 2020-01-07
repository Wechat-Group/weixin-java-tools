package com.github.binarywang.wxpay.bean.result;

import com.github.binarywang.wxpay.constant.WxPayConstants;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * @author m8cool
 */
public class WxPayBillResultTest {
  private static final String PAY_BILL_RESULT_ALL_CONTENT = "交易时间,公众账号ID,商户号,特约商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,应结订单金额,代金券金额,微信退款单号,商户退款单号,退款金额,充值券退款金额,退款类型,退款状态,商品名称,商户数据包,手续费,费率,订单金额,申请退款金额,费率备注\n" +
    "`2019-07-25 08:35:41,`WWWW,`XXXXXXXX,`0,`,`XXXXXXXXXXXXX,`XXXXXXXXXX,`XXXXXXXXXXX,`JSAPI,`SUCCESS,`PSBC_DEBIT,`CNY,`6.00,`0.00,`0,`0,`0.00,`0.00,`,`,`XXXXXX,`XXXXXXX,`0.04000,`0.60%,`6.00,`0.00,`\n" +
    "总交易单数,应结订单总金额,退款总金额,充值券退款总金额,手续费总金额,订单总金额,申请退款总金额\n" +
    "`48,`5.76,`1.42,`0.00,`0.01000,`5.76,`1.42\n";
  private static final String PAY_BILL_RESULT_ALL_CONTENT_1 = "交易时间,公众账号ID,商户号,子商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,总金额,代金券或立减优惠金额,微信退款单号,商户退款单号,退款金额,代金券或立减优惠退款金额,退款类型,退款状态,商品名称,商户数据包,手续费,费率\n" +
    "`2014-11-10 16：33：45,`wx2421b1c4370ec43b,`10000100,`0,`1000,`1001690740201411100005734289,`1415640626,`085e9858e3ba5186aafcbaed1,`MICROPAY,`SUCCESS,`OTHERS,`CNY,`0.01,`0.0,`0,`0,`0,`0,`,`,`被扫支付测试,`订单额外描述,`0,`0.60%\n" +
    "`2014-11-10 16：46：14,`wx2421b1c4370ec43b,`10000100,`0,`1000,`1002780740201411100005729794,`1415635270,`085e9858e90ca40c0b5aee463,`MICROPAY,`SUCCESS,`OTHERS,`CNY,`0.01,`0.0,`0,`0,`0,`0,`,`,`被扫支付测试,`订单额外描述,`0,`0.60%\n" +
    "总交易单数,总交易额,总退款金额,总代金券或立减优惠退款金额,手续费总金额\n" +
    "`2,`0.02,`0.0,`0.0,`0";

  private static final String PAY_BILL_RESULT_SUCCESS_CONTENT = "交易时间,公众账号ID,商户号,特约商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,应结订单金额,代金券金额,商品名称,商户数据包,手续费,费率,订单金额,费率备注\n" +
    "`2019-07-23 18:46:41,`XXXX,`XXX,`XXX,`,`XXX,`XXX,`XXX,`JSAPI,`SUCCESS,`CFT,`CNY,`0.01,`0.00,`XXX,`XXXX,`0.00000,`0.60%,`0.01,`\n" +
    "总交易单数,应结订单总金额,退款总金额,充值券退款总金额,手续费总金额,订单总金额,申请退款总金额\n" +
    "`31,`3.05,`0.00,`0.00,`0.02000,`3.05,`0.00";
  private static final String PAY_BILL_RESULT_REFUND_CONTENT = "交易时间,公众账号ID,商户号,特约商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,应结订单金额,代金券金额,退款申请时间,退款成功时间,微信退款单号,商户退款单号,退款金额,充值券退款金额,退款类型,退款状态,商品名称,商户数据包,手续费,费率,订单金额,申请退款金额,费率备注\n" +
    "`2019-07-23 20:53:36,`xxx,`xxx,`xxx,`,`xxx,`xxxx,`xxxxx,`JSAPI,`REFUND,`CFT,`CNY,`0.00,`0.00,`2019-07-23 20:53:36,`2019-07-23 20:53:40,`xxxx,`xxx,`0.01,`0.00,`ORIGINAL,`SUCCESS,`xxxx,`xxxx,`0.00000,`0.60%,`0.00,`0.01,`\n" +
    "总交易单数,应结订单总金额,退款总金额,充值券退款总金额,手续费总金额,订单总金额,申请退款总金额\n" +
    "`4,`0.00,`2.02,`0.00,`-0.02000,`0.00,`2.02";


  /**
   * 测试微信返回类型为ALL时，解析结果是否正确
   */
  @Test
  public void testFromRawBillResultStringAll() {
    WxPayBillResult result = WxPayBillResult.fromRawBillResultString(PAY_BILL_RESULT_ALL_CONTENT, WxPayConstants.BillType.ALL);

    assertEquals(result.getTotalRecord(), "48");
    assertEquals(result.getTotalFee(), "5.76");
    assertEquals(result.getTotalRefundFee(), "1.42");
    assertEquals(result.getTotalCouponFee(), "0.00");
    assertEquals(result.getTotalPoundageFee(), "0.01000");
    assertEquals(result.getTotalAmount(), "5.76");
    assertEquals(result.getTotalAppliedRefundFee(), "1.42");
    assertEquals(result.getBillInfoList().get(0).getTotalAmount(), "6.00");
    assertEquals(result.getBillInfoList().get(0).getAppliedRefundAmount(), "0.00");
    assertEquals(result.getBillInfoList().get(0).getFeeRemark(), "");

    result = WxPayBillResult.fromRawBillResultString(PAY_BILL_RESULT_ALL_CONTENT_1, WxPayConstants.BillType.ALL);

    assertEquals(result.getTotalRecord(), "2");
    assertEquals(result.getTotalFee(), "0.02");
    assertEquals(result.getTotalRefundFee(), "0.0");
    assertEquals(result.getTotalCouponFee(), "0.0");
    assertEquals(result.getTotalPoundageFee(), "0");
    assertNull(result.getTotalAmount());
    assertNull(result.getTotalAppliedRefundFee());
    assertNull(result.getBillInfoList().get(0).getTotalAmount());
    assertNull(result.getBillInfoList().get(0).getAppliedRefundAmount());
    assertNull(result.getBillInfoList().get(0).getFeeRemark());

  }

  /**
   * 测试微信返回类型为SUCCESS时，解析结果是否正确
   */
  @Test
  public void testFromRawBillResultStringSuccess() {
    WxPayBillResult result = WxPayBillResult.fromRawBillResultString(PAY_BILL_RESULT_SUCCESS_CONTENT, WxPayConstants.BillType.SUCCESS);

    assertEquals(result.getTotalRecord(), "31");
    assertEquals(result.getTotalFee(), "3.05");
    assertEquals(result.getTotalRefundFee(), "0.00");
    assertEquals(result.getTotalCouponFee(), "0.00");
    assertEquals(result.getTotalPoundageFee(), "0.02000");
    assertEquals(result.getTotalAmount(), "3.05");
    assertEquals(result.getTotalAppliedRefundFee(), "0.00");
    assertEquals(result.getBillInfoList().get(0).getTotalAmount(), "0.01");
    assertEquals(result.getBillInfoList().get(0).getFeeRemark(), "");

  }
}
