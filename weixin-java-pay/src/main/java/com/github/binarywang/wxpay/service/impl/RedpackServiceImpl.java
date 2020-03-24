package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.request.WxPayRedpackQueryRequest;
import com.github.binarywang.wxpay.bean.request.WxPaySendMiniProgramRedpackRequest;
import com.github.binarywang.wxpay.bean.request.WxPaySendRedpackRequest;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.bean.result.WxPayRedpackQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendMiniProgramRedpackResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.RedpackService;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.RequiredArgsConstructor;

/**
 * 老板加点注释吧.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-12-26
 */
@RequiredArgsConstructor
public class RedpackServiceImpl implements RedpackService {
  private final WxPayService payService;

  @Override
  public WxPaySendMiniProgramRedpackResult sendMiniProgramRedpack(WxPaySendMiniProgramRedpackRequest request)
    throws WxPayException {
    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/mmpaymkttransfers/sendminiprogramhb";
    String responseContent = this.payService.post(url, request.toXML(), true);

    WxPaySendMiniProgramRedpackResult result = BaseWxPayResult.fromXML(responseContent, WxPaySendMiniProgramRedpackResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public WxPaySendRedpackResult sendRedpack(WxPaySendRedpackRequest request) throws WxPayException {
    request.checkAndSign(this.payService.getConfig());

    String url = this.payService.getPayBaseUrl() + "/mmpaymkttransfers/sendredpack";
    if (request.getAmtType() != null) {
      //裂变红包
      url = this.payService.getPayBaseUrl() + "/mmpaymkttransfers/sendgroupredpack";
    }

    String responseContent = this.payService.post(url, request.toXML(), true);
    final WxPaySendRedpackResult result = BaseWxPayResult.fromXML(responseContent, WxPaySendRedpackResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public WxPayRedpackQueryResult queryRedpack(String mchBillNo) throws WxPayException {
    WxPayRedpackQueryRequest request = new WxPayRedpackQueryRequest();
    request.setMchBillNo(mchBillNo);
    return this.queryRedpack(request);
  }

  @Override
  public WxPayRedpackQueryResult queryRedpack(WxPayRedpackQueryRequest request) throws WxPayException {
    request.setBillType(WxPayConstants.BillType.MCHT);
    request.checkAndSign(this.payService.getConfig());

    String url = this.payService.getPayBaseUrl() + "/mmpaymkttransfers/gethbinfo";
    String responseContent = this.payService.post(url, request.toXML(), true);
    WxPayRedpackQueryResult result = BaseWxPayResult.fromXML(responseContent, WxPayRedpackQueryResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }
}
