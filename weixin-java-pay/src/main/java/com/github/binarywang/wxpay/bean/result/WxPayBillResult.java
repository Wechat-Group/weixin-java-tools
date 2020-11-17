package com.github.binarywang.wxpay.bean.result;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信对账单结果类.
 *
 * @author DDLeEHi
 */
@Data
@NoArgsConstructor
public class WxPayBillResult implements Serializable {
  private static final String TOTAL_DEAL_COUNT = "总交易单数";
  private static final long serialVersionUID = -7687458652694204070L;

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }

  /**
   * 对账明细列表.
   */
  private List<WxPayBillInfo> billInfoList;
  /**
   * 总交易单数.
   */
  private String totalRecord;
  /**
   * 应结订单总金额.
   */
  private String totalFee;
  /**
   * 退款总金额.
   */
  private String totalRefundFee;
  /**
   * 充值券退款总金额.
   */
  private String totalCouponFee;
  /**
   * 手续费总金额.
   */
  private String totalPoundageFee;
  /**
   * 订单总金额.
   */
  private String totalAmount;
  /**
   * 申请退款总金额.
   */
  private String totalAppliedRefundFee;

  /**
   * 根据账单类型，从原始对账单字符串里构造出WxPayBillResult对象
   *
   * @param responseContent 原始对账单字符串
   * @param billType        账单类型
   * @return WxPayBillResult对象
   */
  public static WxPayBillResult fromRawBillResultString(String responseContent, String billType) {
    switch (billType) {
      case "ALL": {
        return fromRawBillResultString(responseContent);
      }
      case "SUCCESS": {
        return fromRawBillResultStringToSuccess(responseContent);
      }
      case "REFUND": {
        return fromRawBillResultStringToRefund(responseContent);
      }
      case "RECHARGE_REFUND": {
        return fromRawBillResultStringToRechargeRefund(responseContent);
      }
      default: {
        return null;
      }
    }
  }

  /**
   * 从原始对账单字符串里构造出WxPayBillResult对象，用于构建当日所有订单信息
   */
  private static WxPayBillResult fromRawBillResultString(String responseContent) {
    String listStr = "";
    String objStr = "";
    if (responseContent.contains(TOTAL_DEAL_COUNT)) {
      listStr = responseContent.substring(0, responseContent.indexOf(TOTAL_DEAL_COUNT));
      objStr = responseContent.substring(responseContent.indexOf(TOTAL_DEAL_COUNT));
    }

    List<WxPayBillInfo> results = new ArrayList<>();
    // 去空格
    String newStr = listStr.replaceAll(",", " ");
    // 数据分组
    String[] tempStr = newStr.split("`");
    // 分组标题
    String[] t = tempStr[0].split(" ");
    // 计算循环次数
    int j = tempStr.length / t.length;
    // 纪录数组下标
    int k = 1;
    // 交易时间,公众账号ID,商户号,特约商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,
    // 应结订单金额,代金券金额,微信退款单号,商户退款单号,退款金额,充值券退款金额,退款类型,退款状态,商品名称,商户数据包,手续费,费率,
    // 订单金额,申请退款金额,费率备注  （开通免充值券后的结算对账单专有的三个字段）
    for (int i = 0; i < j; i++) {
      WxPayBillInfo result = new WxPayBillInfo();
      result.setTradeTime(tempStr[k].trim());
      result.setAppId(tempStr[k + 1].trim());
      result.setMchId(tempStr[k + 2].trim());
      result.setSubMchId(tempStr[k + 3].trim());
      result.setDeviceInfo(tempStr[k + 4].trim());
      result.setTransactionId(tempStr[k + 5].trim());
      result.setOutTradeNo(tempStr[k + 6].trim());
      result.setOpenId(tempStr[k + 7].trim());
      result.setTradeType(tempStr[k + 8].trim());
      result.setTradeState(tempStr[k + 9].trim());
      result.setBankType(tempStr[k + 10].trim());
      result.setFeeType(tempStr[k + 11].trim());
      result.setTotalFee(tempStr[k + 12].trim());
      result.setCouponFee(tempStr[k + 13].trim());
      result.setRefundId(tempStr[k + 14].trim());
      result.setOutRefundNo(tempStr[k + 15].trim());
      result.setSettlementRefundFee(tempStr[k + 16].trim());
      result.setCouponRefundFee(tempStr[k + 17].trim());
      result.setRefundChannel(tempStr[k + 18].trim());
      result.setRefundState(tempStr[k + 19].trim());
      result.setBody(tempStr[k + 20].trim());
      result.setAttach(tempStr[k + 21].trim());
      result.setPoundage(tempStr[k + 22].trim());
      result.setPoundageRate(tempStr[k + 23].trim());

      if (t.length > 24) {
        // 开通免充值券后的结算对账单
        result.setTotalAmount(tempStr[k + 24].trim());
        result.setAppliedRefundAmount(tempStr[k + 25].trim());
        result.setFeeRemark(tempStr[k + 26].trim());
      }

      results.add(result);
      k += t.length;
    }

    WxPayBillResult billResult = new WxPayBillResult();
    billResult.setBillInfoList(results);

    /*
     * 总交易单数,应结订单总金额,退款总金额,充值券退款总金额,手续费总金额,订单总金额,申请退款总金额 `48,`5.76,`1.42,`0.00,`0.01000,`5.76,`1.42
     * 参考以上格式进行取值
     */
    String[] totalTempStr = objStr.replaceAll(",", " ").split("`");
    billResult.setTotalRecord(totalTempStr[1].trim());
    billResult.setTotalFee(totalTempStr[2].trim());
    billResult.setTotalRefundFee(totalTempStr[3].trim());
    billResult.setTotalCouponFee(totalTempStr[4].trim());
    billResult.setTotalPoundageFee(totalTempStr[5].trim());
    billResult.setTotalAmount(get(totalTempStr, 6));
    billResult.setTotalAppliedRefundFee(get(totalTempStr, 7));

    return billResult;
  }

  /**
   * 从原始对账单字符串里构造出WxPayBillResult对象，用于构建当日成功支付的订单
   */
  private static WxPayBillResult fromRawBillResultStringToSuccess(String responseContent) {
    String listStr = "";
    String objStr = "";
    if (responseContent.contains(TOTAL_DEAL_COUNT)) {
      listStr = responseContent.substring(0, responseContent.indexOf(TOTAL_DEAL_COUNT));
      objStr = responseContent.substring(responseContent.indexOf(TOTAL_DEAL_COUNT));
    }

    List<WxPayBillInfo> results = new ArrayList<>();
    // 去空格
    String newStr = listStr.replaceAll(",", " ");
    // 数据分组
    String[] tempStr = newStr.split("`");
    // 分组标题
    String[] t = tempStr[0].split(" ");
    // 计算循环次数
    int j = tempStr.length / t.length;
    // 纪录数组下标
    int k = 1;
    // 交易时间,公众账号ID,商户号,特约商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,
    // 应结订单金额,代金券金额,商品名称,商户数据包,手续费,费率,订单金额,费率备注
    for (int i = 0; i < j; i++) {
      WxPayBillInfo result = new WxPayBillInfo();
      result.setTradeTime(tempStr[k].trim());
      result.setAppId(tempStr[k + 1].trim());
      result.setMchId(tempStr[k + 2].trim());
      result.setSubMchId(tempStr[k + 3].trim());
      result.setDeviceInfo(tempStr[k + 4].trim());
      result.setTransactionId(tempStr[k + 5].trim());
      result.setOutTradeNo(tempStr[k + 6].trim());
      result.setOpenId(tempStr[k + 7].trim());
      result.setTradeType(tempStr[k + 8].trim());
      result.setTradeState(tempStr[k + 9].trim());
      result.setBankType(tempStr[k + 10].trim());
      result.setFeeType(tempStr[k + 11].trim());
      result.setTotalFee(tempStr[k + 12].trim());
      result.setCouponFee(tempStr[k + 13].trim());
      result.setBody(tempStr[k + 14].trim());
      result.setAttach(tempStr[k + 15].trim());
      result.setPoundage(tempStr[k + 16].trim());
      result.setPoundageRate(tempStr[k + 17].trim());
      result.setTotalAmount(tempStr[k + 18].trim());
      result.setFeeRemark(tempStr[k + 19].trim());
      results.add(result);
      k += t.length;
    }

    WxPayBillResult billResult = new WxPayBillResult();
    billResult.setBillInfoList(results);

    /*
     * 总交易单数,应结订单总金额,退款总金额,充值券退款总金额,手续费总金额,订单总金额,申请退款总金额 `31,`3.05,`0.00,`0.00,`0.02000,`3.05,`0.00
     * 参考以上格式进行取值
     */
    String[] totalTempStr = objStr.replaceAll(",", " ").split("`");
    billResult.setTotalRecord(totalTempStr[1].trim());
    billResult.setTotalFee(totalTempStr[2].trim());
    billResult.setTotalRefundFee(totalTempStr[3].trim());
    billResult.setTotalCouponFee(totalTempStr[4].trim());
    billResult.setTotalPoundageFee(totalTempStr[5].trim());
    billResult.setTotalAmount(get(totalTempStr, 6));
    billResult.setTotalAppliedRefundFee(get(totalTempStr, 7));
    return billResult;
  }

  /**
   * 从原始对账单字符串里构造出WxPayBillResult对象，用于构建当日退款的订单
   */
  private static WxPayBillResult fromRawBillResultStringToRefund(String responseContent) {
    String listStr = "";
    String objStr = "";
    if (responseContent.contains(TOTAL_DEAL_COUNT)) {
      listStr = responseContent.substring(0, responseContent.indexOf(TOTAL_DEAL_COUNT));
      objStr = responseContent.substring(responseContent.indexOf(TOTAL_DEAL_COUNT));
    }

    List<WxPayBillInfo> results = new ArrayList<>();
    // 去空格
    String newStr = listStr.replaceAll(",", " ");
    // 数据分组
    String[] tempStr = newStr.split("`");
    // 分组标题
    String[] t = tempStr[0].split(" ");
    // 计算循环次数
    int j = tempStr.length / t.length;
    // 纪录数组下标
    int k = 1;
    // 交易时间,公众账号ID,商户号,子商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,总金额,代金券或立减优惠金额,
    // 退款申请时间,退款成功时间,微信退款单号,商户退款单号,退款金额,代金券或立减优惠退款金额,退款类型,退款状态,商品名称,商户数据包,手续费,费率
    for (int i = 0; i < j; i++) {
      WxPayBillInfo result = new WxPayBillInfo();
      result.setTradeTime(tempStr[k].trim());
      result.setAppId(tempStr[k + 1].trim());
      result.setMchId(tempStr[k + 2].trim());
      result.setSubMchId(tempStr[k + 3].trim());
      result.setDeviceInfo(tempStr[k + 4].trim());
      result.setTransactionId(tempStr[k + 5].trim());
      result.setOutTradeNo(tempStr[k + 6].trim());
      result.setOpenId(tempStr[k + 7].trim());
      result.setTradeType(tempStr[k + 8].trim());
      result.setTradeState(tempStr[k + 9].trim());
      result.setBankType(tempStr[k + 10].trim());
      result.setFeeType(tempStr[k + 11].trim());
      result.setTotalFee(tempStr[k + 12].trim());
      result.setCouponFee(tempStr[k + 13].trim());
      result.setRefundTime(tempStr[k + 14].trim());
      result.setRefundSuccessTime(tempStr[k + 15].trim());
      result.setRefundId(tempStr[k + 16].trim());
      result.setOutRefundNo(tempStr[k + 17].trim());
      result.setSettlementRefundFee(tempStr[k + 18].trim());
      result.setCouponRefundFee(tempStr[k + 19].trim());
      result.setRefundChannel(tempStr[k + 20].trim());
      result.setRefundState(tempStr[k + 21].trim());
      result.setBody(tempStr[k + 22].trim());
      result.setAttach(tempStr[k + 23].trim());
      result.setPoundage(tempStr[k + 24].trim());
      result.setPoundageRate(tempStr[k + 25].trim());
      results.add(result);
      k += t.length;
    }

    WxPayBillResult billResult = new WxPayBillResult();
    billResult.setBillInfoList(results);

    /*
     * 总交易单数,应结订单总金额,退款总金额,充值券退款总金额,手续费总金额,订单总金额,申请退款总金额 `2,`0.02,`0.0,`0.0,`0
     * 参考以上格式进行取值
     */
    String[] totalTempStr = objStr.replaceAll(",", " ").split("`");
    billResult.setTotalRecord(totalTempStr[1].trim());
    billResult.setTotalFee(totalTempStr[2].trim());
    billResult.setTotalRefundFee(totalTempStr[3].trim());
    billResult.setTotalCouponFee(totalTempStr[4].trim());
    billResult.setTotalPoundageFee(totalTempStr[5].trim());
    billResult.setTotalAmount(get(totalTempStr, 6));
    billResult.setTotalAppliedRefundFee(get(totalTempStr, 7));

    return billResult;
  }

  /**
   * 从原始对账单字符串里构造出WxPayBillResult对象，用于构建当日充值退款订单
   */
  private static WxPayBillResult fromRawBillResultStringToRechargeRefund(String responseContent) {
    String listStr = "";
    String objStr = "";
    if (responseContent.contains(TOTAL_DEAL_COUNT)) {
      listStr = responseContent.substring(0, responseContent.indexOf(TOTAL_DEAL_COUNT));
      objStr = responseContent.substring(responseContent.indexOf(TOTAL_DEAL_COUNT));
    }

    List<WxPayBillInfo> results = new ArrayList<>();
    // 去空格
    String newStr = listStr.replaceAll(",", " ");
    // 数据分组
    String[] tempStr = newStr.split("`");
    // 分组标题
    String[] t = tempStr[0].split(" ");
    // 计算循环次数
    int j = tempStr.length / t.length;
    // 纪录数组下标
    int k = 1;
    // 交易时间,公众账号ID,商户号,子商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,应结订单金额,代金券金额,
    // 退款申请时间,退款成功时间,微信退款单号,商户退款单号,退款金额,充值券退款金额,退款类型,退款状态,商品名称,商户数据包,返还手续费,费率,订单金额,申请退款金额
    for (int i = 0; i < j; i++) {
      WxPayBillInfo result = new WxPayBillInfo();
      result.setTradeTime(tempStr[k].trim());
      result.setAppId(tempStr[k + 1].trim());
      result.setMchId(tempStr[k + 2].trim());
      result.setSubMchId(tempStr[k + 3].trim());
      result.setDeviceInfo(tempStr[k + 4].trim());
      result.setTransactionId(tempStr[k + 5].trim());
      result.setOutTradeNo(tempStr[k + 6].trim());
      result.setOpenId(tempStr[k + 7].trim());
      result.setTradeType(tempStr[k + 8].trim());
      result.setTradeState(tempStr[k + 9].trim());
      result.setBankType(tempStr[k + 10].trim());
      result.setFeeType(tempStr[k + 11].trim());
      result.setTotalFee(tempStr[k + 12].trim());
      result.setCouponFee(tempStr[k + 13].trim());
      result.setRefundTime(tempStr[k + 14].trim());
      result.setRefundSuccessTime(tempStr[k + 15].trim());
      result.setRefundId(tempStr[k + 16].trim());
      result.setOutRefundNo(tempStr[k + 17].trim());
      result.setSettlementRefundFee(tempStr[k + 18].trim());
      result.setCouponRefundFee(tempStr[k + 19].trim());
      result.setRefundChannel(tempStr[k + 20].trim());
      result.setRefundState(tempStr[k + 21].trim());
      result.setBody(tempStr[k + 22].trim());
      result.setAttach(tempStr[k + 23].trim());
      result.setPoundage(tempStr[k + 24].trim());
      result.setPoundageRate(tempStr[k + 25].trim());
      result.setTotalAmount(get(tempStr, k + 26, t.length));
      result.setAppliedRefundAmount(get(tempStr, k + 27, t.length));
      results.add(result);
      k += t.length;
    }

    WxPayBillResult billResult = new WxPayBillResult();
    billResult.setBillInfoList(results);

    /*
     * 总交易单数,应结订单总金额,退款总金额,充值券退款总金额,手续费总金额,订单总金额,申请退款总金额 `2,`0.02,`0.0,`0.0,`0
     * 参考以上格式进行取值
     */
    String[] totalTempStr = objStr.replaceAll(",", " ").split("`");
    billResult.setTotalRecord(totalTempStr[1].trim());
    billResult.setTotalFee(totalTempStr[2].trim());
    billResult.setTotalRefundFee(totalTempStr[3].trim());
    billResult.setTotalCouponFee(totalTempStr[4].trim());
    billResult.setTotalPoundageFee(totalTempStr[5].trim());
    billResult.setTotalAmount(get(totalTempStr, 6));
    billResult.setTotalAppliedRefundFee(get(totalTempStr, 7));

    return billResult;
  }

  private static String get(String[] array, int idx) {
    return get(array, idx, array.length);
  }

  private static String get(String[] array, int idx, int length) {
    if (length > idx) {
      return array[idx].trim();
    }
    return null;
  }
}
