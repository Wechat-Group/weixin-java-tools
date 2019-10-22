package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.entpay.EntPayResult;
import com.github.binarywang.wxpay.bean.profitsharing.ProfitSharingResult;
import com.github.binarywang.wxpay.bean.profitsharing.ProfitsharingRequest;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.ProfitSharingService;
import com.github.binarywang.wxpay.service.WxPayService;

/**
 * @author Wang GuangXin 2019/10/22 10:13
 * @version 1.0
 */
public class ProfitSharingServiceImpl implements ProfitSharingService {
  private WxPayService payService;
  public ProfitSharingServiceImpl(WxPayService payService) {
    this.payService = payService;
  }

  @Override
  public ProfitSharingResult profitsharing(ProfitsharingRequest request) throws WxPayException {
    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/secapi/pay/profitsharing";

    String responseContent = this.payService.post(url, request.toXML(), true);
    ProfitSharingResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }
}
