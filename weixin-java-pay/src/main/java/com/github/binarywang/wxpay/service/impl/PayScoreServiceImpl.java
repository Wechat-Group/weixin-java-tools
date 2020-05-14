package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.payscore.WxPayScoreCreateRequest;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreCreateResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.PayScoreService;
import com.github.binarywang.wxpay.service.WxPayService;

/**
 * @author doger.wang
 * @date 2020/5/14 9:43
 */

public class PayScoreServiceImpl implements PayScoreService {
  private WxPayService payService;

  public PayScoreServiceImpl(WxPayService payService) {
    this.payService = payService;
  }


  @Override
  public WxPayScoreCreateResult createServiceOrder(WxPayScoreCreateRequest request) throws WxPayException {



    return null;
  }
}
