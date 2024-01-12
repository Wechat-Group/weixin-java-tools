package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.bank.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.BankService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;

import java.net.URLEncoder;

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
    try {
      String encryptAccountNumber = RsaCryptoUtil.encryptOAEP(accountNumber, this.payService.getConfig().getVerifier().getValidCertificate());
      accountNumber = URLEncoder.encode(encryptAccountNumber, "UTF-8");
    } catch (Exception e) {
      throw new RuntimeException("银行卡号加密异常!", e);
    }
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

  @Override
  public ProvincesResult areasProvinces() throws WxPayException {
    String url = String.format("%s/v3/capital/capitallhh/areas/provinces", this.payService.getPayBaseUrl());
    String response = payService.getV3WithWechatPaySerial(url);
    return GSON.fromJson(response, ProvincesResult.class);
  }

  @Override
  public CitiesResult areasCities(Integer provinceCode) throws WxPayException {
    String url = String.format("%s/v3/capital/capitallhh/areas/provinces/%s/cities", this.payService.getPayBaseUrl(), provinceCode);
    String response = payService.getV3WithWechatPaySerial(url);
    return GSON.fromJson(response, CitiesResult.class);
  }

  @Override
  public BankBranchesResult bankBranches(String bankAliasCode, Integer cityCode, Integer offset, Integer limit) throws WxPayException {
    offset = offset == null ? 0 : offset;
    limit = limit == null ? 200 : limit;
    String url = String.format("%s/v3/capital/capitallhh/banks/%s/branches?city_code=%s&offset=%s&limit=%s", this.payService.getPayBaseUrl(), bankAliasCode, cityCode, offset, limit);
    String response = payService.getV3(url);
    return GSON.fromJson(response, BankBranchesResult.class);
  }
}
