package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.brandmerchanttransfer.request.*;
import com.github.binarywang.wxpay.bean.brandmerchanttransfer.result.BrandBatchesQueryResult;
import com.github.binarywang.wxpay.bean.brandmerchanttransfer.result.BrandDetailsQueryResult;
import com.github.binarywang.wxpay.bean.brandmerchanttransfer.result.BrandTransferBatchesResult;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 品牌商户发放红包商家转账到零钱（直联商户）
 *
 * @author moran
 */
public interface BrandMerchantTransferService {

  /**
   * 品牌商户发放红包API
   * <p>
   * 适用对象：直连商户
   * 文档详见:
   * 请求URL：https://api.mch.weixin.qq.com/v3/fund-app/brand-redpacket/brand-merchant-batches
   * 请求方式：POST
   * 接口限频： 单个商户 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   * 是否需要证书：是
   *
   * @param request the request
   * @return transfer create result
   * @throws WxPayException the wx pay exception
   */
  BrandTransferBatchesResult createBrandTransfer(BrandTransferBatchesRequest request) throws WxPayException;

  /**
   * 品牌红包微信批次单号查询批次单API
   * <p>
   * 适用对象：直连商户
   * 文档详见:
   * 请求URL：https://api.mch.weixin.qq.com/v3/fund-app/brand-redpacket/brand-merchant-batches/{batch_no}
   * 请求方式：GET
   * 接口限频： 单个商户 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param request the request
   * @return batches query result
   * @throws WxPayException the wx pay exception
   */
  BrandBatchesQueryResult queryBrandWxBatches(BrandWxBatchesQueryRequest request) throws WxPayException;

  /**
   * 品牌红包微信支付明细单号查询明细单API
   * <p>
   * 适用对象：直连商户
   * 文档详见:
   * 请求URL：https://api.mch.weixin.qq.com/v3/fund-app/brand-redpacket/brand-merchant-batches/{batch_no}/details/{detail_no}
   * 请求方式：GET
   * 接口限频： 单个商户 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param request the request
   * @return details query result
   * @throws WxPayException the wx pay exception
   */
  BrandDetailsQueryResult queryBrandWxDetails(BrandWxDetailsQueryRequest request) throws WxPayException;

  /**
   * 品牌红包商家批次单号查询批次单API
   * <p>
   * 适用对象：直连商户
   * 文档详见:
   * 请求URL：https://api.mch.weixin.qq.com/v3/fund-app/brand-redpacket/brand-merchant-out-batches/{out_batch_no}
   * 请求方式：GET
   * 接口限频： 单个商户 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param request the request
   * @return batches query result
   * @throws WxPayException the wx pay exception
   */
  BrandBatchesQueryResult queryBrandMerchantBatches(BrandMerchantBatchesQueryRequest request) throws WxPayException;

  /**
   * 品牌红包商家明细单号查询明细单API
   * <p>
   * 适用对象：直连商户
   * 文档详见:
   * 请求URL：https://api.mch.weixin.qq.com/v3/fund-app/brand-redpacket/brand-merchant-out-batches/{out_batch_no}/out-details/{out_detail_no}
   * 请求方式：GET
   * 接口限频： 单个商户 50QPS，如果超过频率限制，会报错FREQUENCY_LIMITED，请降低频率请求。
   *
   * @param request the request
   * @return details query result
   * @throws WxPayException the wx pay exception
   */
  BrandDetailsQueryResult queryBrandMerchantDetails(BrandMerchantDetailsQueryRequest request) throws WxPayException;


}
