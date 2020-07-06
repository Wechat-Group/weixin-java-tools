package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.profitsharing.*;
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
  public ProfitSharingResult profitSharing(ProfitSharingRequest request) throws WxPayException {
    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/secapi/pay/profitsharing";

    String responseContent = this.payService.post(url, request.toXML(), true);
    ProfitSharingResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingResult multiProfitSharing(ProfitSharingRequest request) throws WxPayException {
    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/secapi/pay/multiprofitsharing";

    String responseContent = this.payService.post(url, request.toXML(), true);
    ProfitSharingResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingResult profitSharingFinish(ProfitSharingFinishRequest request) throws WxPayException {
    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/secapi/pay/profitsharingfinish";

    String responseContent = this.payService.post(url, request.toXML(), true);
    ProfitSharingResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingReceiverResult addReceiver(ProfitSharingReceiverRequest request) throws WxPayException {
    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/pay/profitsharingaddreceiver";

    String responseContent = this.payService.post(url, request.toXML(), true);
    ProfitSharingReceiverResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingReceiverResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingReceiverResult removeReceiver(ProfitSharingReceiverRequest request) throws WxPayException {
    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/pay/profitsharingremovereceiver";

    String responseContent = this.payService.post(url, request.toXML(), true);
    ProfitSharingReceiverResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingReceiverResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingQueryResult profitSharingQuery(ProfitSharingQueryRequest request) throws WxPayException {
    request.setAppid(null);

    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/pay/profitsharingquery";

    String responseContent = this.payService.post(url, request.toXML(), true);
    ProfitSharingQueryResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingQueryResult.class);
    result.formatReceivers();
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingReturnResult profitSharingReturn(ProfitSharingReturnRequest returnRequest) throws WxPayException {
    returnRequest.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/secapi/pay/profitsharingreturn";

    String responseContent = this.payService.post(url, returnRequest.toXML(), true);
    ProfitSharingReturnResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingReturnResult.class);
    result.checkResult(this.payService, returnRequest.getSignType(), true);
    return result;
  }

  @Override
  public ProfitSharingReturnResult profitSharingReturnQuery(ProfitSharingReturnQueryRequest queryRequest) throws WxPayException {
    queryRequest.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/pay/profitsharingreturnquery";

    String responseContent = this.payService.post(url, queryRequest.toXML(), true);
    ProfitSharingReturnResult result = BaseWxPayResult.fromXML(responseContent, ProfitSharingReturnResult.class);
    result.checkResult(this.payService, queryRequest.getSignType(), true);
    return result;
  }
}
