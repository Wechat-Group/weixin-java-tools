package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.bank.BankAccountResult;
import com.github.binarywang.wxpay.bean.bank.BankingResult;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 * 微信支付-银行组件
 * </pre>
 *
 * @author zhongjun
 **/
public interface BankService {
  /**
   * <pre>
   *
   * 获取对私银行卡号开户银行
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/capital/capitallhh/banks/search-banks-by-bank-account">https://api.mch.weixin.qq.com/v3/capital/capitallhh/banks/search-banks-by-bank-account</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_1.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_1.shtml</a>
   * </pre>
   *
   * @param accountNumber 银行卡号	该字段需进行加密处理，加密方法详见敏感信息加密说明。(提醒：必须在HTTP头中上送Wechatpay-Serial)
   * @return BankAccountResult 对私银行卡号开户银行信息
   * @throws WxPayException .
   */
  BankAccountResult searchBanksByBankAccount(String accountNumber) throws WxPayException;

  /**
   * <pre>
   *
   * 查询支持个人业务的银行列表
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/capital/capitallhh/banks/personal-banking">https://api.mch.weixin.qq.com/v3/capital/capitallhh/banks/personal-banking</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_2.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_2.shtml</a>
   * </pre>
   *
   * @param offset 本次查询偏移量
   * @param limit  本次请求最大查询条数,最大值为200
   * @return PersonalBankingResult 支持个人业务的银行列表信息
   * @throws WxPayException .
   */
  BankingResult personalBanking(Integer offset, Integer limit) throws WxPayException;

  /**
   * <pre>
   *
   * 支持对公业务的银行列表
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/capital/capitallhh/banks/corporate-banking">https://api.mch.weixin.qq.com/v3/capital/capitallhh/banks/corporate-banking</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_3.shtml">https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter11_2_3.shtml</a>
   * </pre>
   *
   * @param offset 本次查询偏移量
   * @param limit  本次请求最大查询条数,最大值为200
   * @return BankingResult 支持对公业务的银行列表信息
   * @throws WxPayException .
   */
  BankingResult corporateBanking(Integer offset, Integer limit) throws WxPayException;
}
