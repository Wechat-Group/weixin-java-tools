package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 *  微信基础支付v3相关服务类.
 * </pre>
 *
 * @author thinsstar
 */
public interface BasePayV3Service {

  /**
   * 调用统一下单接口，并组装生成支付所需参数对象.
   *
   * @param <T>     请使用{@link com.github.binarywang.wxpay.bean.order}包下的类
   * @param request 统一下单请求参数
   * @return 返回 {@link com.github.binarywang.wxpay.bean.order}包下的类对象
   * @throws WxPayException the wx pay exception
   */
  <T> T createOrder(WxPayUnifiedOrderV3Request request) throws WxPayException;
}
