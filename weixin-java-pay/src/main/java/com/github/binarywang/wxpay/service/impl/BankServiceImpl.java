package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.bank.BankAccountResult;
import com.github.binarywang.wxpay.bean.bank.BankingResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.BankService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;

/**
 * 微信支付-银行组件
 *
 * @author zhongjun
 **/
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
  private final WxPayService payService;
  private static final Gson GSON = new GsonBuilder().create();

  @Override
  public BankAccountResult searchBanksByBankAccount(String accountNumber) throws WxPayException {
    String url = String.format("%s/v3/capital/capitallhh/banks/search-banks-by-bank-account?account_number=%s", this.payService.getPayBaseUrl(), accountNumber);
    String response = payService.getV3WithWechatPaySerial(url);
    return GSON.fromJson(response, BankAccountResult.class);
  }

  @Override
  public BankingResult personalBanking(Integer offset, Integer limit) throws WxPayException {
    offset = offset == null ? 0 : offset;
    limit = limit == null ? 200 : limit;
    String url = String.format("%s/v3/capital/capitallhh/banks/personal-banking?offset=%s&limit=%s", this.payService.getPayBaseUrl(), offset, limit);
    String response = payService.getV3(url);
    return GSON.fromJson(response, BankingResult.class);
  }

  @Override
  public BankingResult corporateBanking(Integer offset, Integer limit) throws WxPayException {
    offset = offset == null ? 0 : offset;
    limit = limit == null ? 200 : limit;
    String url = String.format("%s/v3/capital/capitallhh/banks/corporate-banking?offset=%s&limit=%s", this.payService.getPayBaseUrl(), offset, limit);
    String response = payService.getV3(url);
    return GSON.fromJson(response, BankingResult.class);
  }
}
