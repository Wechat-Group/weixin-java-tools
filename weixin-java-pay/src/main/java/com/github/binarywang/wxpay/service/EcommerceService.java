package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.ecommerce.*;
import com.github.binarywang.wxpay.bean.ecommerce.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 *  电商收付通相关服务类.
 *  接口规则：https://wechatpay-api.gitbook.io/wechatpay-api-v3
 * </pre>
 *
 * @author cloudX
 * @date 2020/08/17
 */
public interface EcommerceService {
  /**
   * <pre>
   * 二级商户进件API
   * 接口地址: https://api.mch.weixin.qq.com/v3/ecommerce/applyments/
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_1.shtml
   *
   * </pre>
   *
   * @param request 请求对象
   * @return . applyments result
   * @throws WxPayException the wx pay exception
   */
  ApplymentsResult createApply(ApplymentsRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询申请状态API
   * 请求URL: https://api.mch.weixin.qq.com/v3/ecommerce/applyments/{applyment_id}
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_2.shtml
   * </pre>
   *
   * @param applymentId 申请单ID
   * @return . applyments status result
   * @throws WxPayException the wx pay exception
   */
  ApplymentsStatusResult queryApplyStatusByApplymentId(String applymentId) throws WxPayException;

  /**
   * <pre>
   * 查询申请状态API
   * 请求URL: https://api.mch.weixin.qq.com/v3/ecommerce/applyments/out-request-no/{out_request_no}
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter3_2.shtml
   * </pre>
   *
   * @param outRequestNo 业务申请编号
   * @return . applyments status result
   * @throws WxPayException the wx pay exception
   */
  ApplymentsStatusResult queryApplyStatusByOutRequestNo(String outRequestNo) throws WxPayException;

  /**
   * <pre>
   * 合单支付API(APP支付、JSAPI支付、H5支付、NATIVE支付).
   * 请求URL：https://api.mch.weixin.qq.com/v3/combine-transactions/jsapi
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/e-combine.shtml
   * </pre>
   *
   * @param tradeType 支付方式
   * @param request 请求对象
   * @return 微信合单支付返回
   */
  TransactionsResult combine(TradeTypeEnum tradeType, CombineTransactionsRequest request) throws WxPayException;

  /**
   * <pre>
   * 合单支付API(APP支付、JSAPI支付、H5支付、NATIVE支付).
   * 请求URL：https://api.mch.weixin.qq.com/v3/combine-transactions/jsapi
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/e-combine.shtml
   * </pre>
   *
   * @param tradeType 支付方式
   * @param request 请求对象
   * @return 调起支付需要的参数
   */
  <T> T combineTransactions(TradeTypeEnum tradeType, CombineTransactionsRequest request) throws WxPayException;

  /**
   * <pre>
   * 合单支付通知回调数据处理
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/e-combine.shtml
   * </pre>
   *
   * @param notifyData 通知数据
   * @param header 通知头部数据，不传则表示不校验头
   * @return 解密后通知数据
   */
  CombineTransactionsNotifyResult parseCombineNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   *  <pre>
   *  服务商模式普通支付API(APP支付、JSAPI支付、H5支付、NATIVE支付).
   *  请求URL：https://api.mch.weixin.qq.com/v3/pay/partner/transactions/jsapi
   *  文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/transactions_sl.shtml
   *  </pre>
   * @param tradeType 支付方式
   * @param request 请求对象
   * @return 调起支付需要的参数
   */
  TransactionsResult partner(TradeTypeEnum tradeType, PartnerTransactionsRequest request) throws WxPayException;

  /**
   *  <pre>
   *  服务商模式普通支付API(APP支付、JSAPI支付、H5支付、NATIVE支付).
   *  请求URL：https://api.mch.weixin.qq.com/v3/pay/partner/transactions/jsapi
   *  文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/transactions_sl.shtml
   *  </pre>
   * @param tradeType 支付方式
   * @param request 请求对象
   * @return 调起支付需要的参数
   */
  <T> T partnerTransactions(TradeTypeEnum tradeType, PartnerTransactionsRequest request) throws WxPayException;

  /**
   * <pre>
   * 普通支付通知回调数据处理
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/pages/e_transactions.shtml
   * </pre>
   *
   * @param notifyData 通知数据
   * @param header 通知头部数据，不传则表示不校验头
   * @return 解密后通知数据
   */
  PartnerTransactionsNotifyResult parsePartnerNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;
}
