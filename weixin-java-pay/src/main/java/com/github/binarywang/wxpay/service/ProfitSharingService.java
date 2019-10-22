package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.profitsharing.ProfitSharingResult;
import com.github.binarywang.wxpay.bean.profitsharing.ProfitsharingRequest;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * @author Wang GuangXin 2019/10/22 10:05
 * @version 1.0
 */
public interface ProfitSharingService {
  /**
   * 单次分账请求按照传入的分账接收方账号和资金进行分账，同时会将订单剩余的待分账金额解冻给特约商户。故操作成功后，订单不能再进行分账，也不能进行分账完结。
   * <p>
   * 接口频率：30QPS
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_1&index=1
   * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/profitsharing
   *
   * @param profitsharingRequest
   * @return
   * @throws WxPayException the wx pay exception
   */
  ProfitSharingResult profitsharing(ProfitsharingRequest profitsharingRequest) throws WxPayException;

  ;
}
