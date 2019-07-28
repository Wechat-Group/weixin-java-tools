package com.github.binarywang.wxpay.bean.result;

import com.github.binarywang.wxpay.constant.WxPayConstants;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author m8cool
 */
public class WxPayBillResultTest {

  public static final String PAY_BILL_RESULT_ALL_CONTENT = "交易时间,公众账号ID,商户号,特约商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,应结订单金额,代金券金额,微信退款单号,商户退款单号,退款金额,充值券退款金额,退款类型,退款状态,商品名称,商户数据包,手续费,费率,订单金额,申请退款金额,费率备注\n" +
    "`2019-07-25 08:35:41,`WWWW,`XXXXXXXX,`0,`,`XXXXXXXXXXXXX,`XXXXXXXXXX,`XXXXXXXXXXX,`JSAPI,`SUCCESS,`PSBC_DEBIT,`CNY,`6.00,`0.00,`0,`0,`0.00,`0.00,`,`,`XXXXXX,`XXXXXXX,`0.04000,`0.60%,`6.00,`0.00,`\n" +
    "总交易单数,应结订单总金额,退款总金额,充值券退款总金额,手续费总金额,订单总金额,申请退款总金额\n" +
    "`48,`5.76,`1.42,`0.00,`0.01000,`5.76,`1.42\n" ;
  public static final String PAY_BILL_RESULT_SUCCESS_CONTENT = "交易时间,公众账号ID,商户号,特约商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,应结订单金额,代金券金额,商品名称,商户数据包,手续费,费率,订单金额,费率备注\n" +
    "`2019-07-23 18:46:41,`XXXX,`XXX,`XXX,`,`XXX,`XXX,`XXX,`JSAPI,`SUCCESS,`CFT,`CNY,`0.01,`0.00,`XXX,`XXXX,`0.00000,`0.60%,`0.01,`\n" +
    "总交易单数,应结订单总金额,退款总金额,充值券退款总金额,手续费总金额,订单总金额,申请退款总金额\n" +
    "`31,`3.05,`0.00,`0.00,`0.02000,`3.05,`0.00" ;
  public static final String PAY_BILL_RESULT_REFUND_CONTENT = "交易时间,公众账号ID,商户号,特约商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,应结订单金额,代金券金额,退款申请时间,退款成功时间,微信退款单号,商户退款单号,退款金额,充值券退款金额,退款类型,退款状态,商品名称,商户数据包,手续费,费率,订单金额,申请退款金额,费率备注\n" +
    "`2019-07-23 20:53:36,`xxx,`xxx,`xxx,`,`xxx,`xxxx,`xxxxx,`JSAPI,`REFUND,`CFT,`CNY,`0.00,`0.00,`2019-07-23 20:53:36,`2019-07-23 20:53:40,`xxxx,`xxx,`0.01,`0.00,`ORIGINAL,`SUCCESS,`xxxx,`xxxx,`0.00000,`0.60%,`0.00,`0.01,`\n" +
    "总交易单数,应结订单总金额,退款总金额,充值券退款总金额,手续费总金额,订单总金额,申请退款总金额\n" +
    "`4,`0.00,`2.02,`0.00,`-0.02000,`0.00,`2.02";

  /**
   * 测试微信返回类型为ALL时，解析结果是否正确
   */
  @Test
  public void testFromRawBillResultStringAll(){
    WxPayBillResult result = WxPayBillResult.fromRawBillResultString(PAY_BILL_RESULT_ALL_CONTENT, WxPayConstants.BillType.ALL);

    Assert.assertEquals("48",result.getTotalRecord());
    Assert.assertEquals("5.76",result.getTotalFee());
    Assert.assertEquals("1.42",result.getTotalRefundFee());
    Assert.assertEquals("0.00",result.getTotalCouponFee());
    Assert.assertEquals("0.01000",result.getTotalPoundageFee());
    Assert.assertEquals("5.76",result.getTotalAmount());
    Assert.assertEquals("1.42",result.getTotalAppliedRefundFee());

    Assert.assertEquals("6.00",result.getBillInfoList().get(0).getTotalAmount());
    Assert.assertEquals("0.00",result.getBillInfoList().get(0).getAppliedRefundAmount());
    Assert.assertEquals("",result.getBillInfoList().get(0).getFeeRemark());


  }

  /**
   * 测试微信返回类型为SUCCESS时，解析结果是否正确
   */
  @Test
  public void testFromRawBillResultStringSuccess(){
    WxPayBillResult result = WxPayBillResult.fromRawBillResultString(PAY_BILL_RESULT_SUCCESS_CONTENT, WxPayConstants.BillType.SUCCESS);

    Assert.assertEquals("31",result.getTotalRecord());
    Assert.assertEquals("3.05",result.getTotalFee());
    Assert.assertEquals("0.00",result.getTotalRefundFee());
    Assert.assertEquals("0.00",result.getTotalCouponFee());
    Assert.assertEquals("0.02000",result.getTotalPoundageFee());
    Assert.assertEquals("3.05",result.getTotalAmount());
    Assert.assertEquals("0.00",result.getTotalAppliedRefundFee());

    Assert.assertEquals("0.01",result.getBillInfoList().get(0).getTotalAmount());
    Assert.assertEquals("",result.getBillInfoList().get(0).getFeeRemark());

  }
}
