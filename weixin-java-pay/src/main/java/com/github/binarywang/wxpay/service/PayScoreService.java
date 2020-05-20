package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.payscore.PayScoreNotifyData;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreRequest;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult;
import com.github.binarywang.wxpay.exception.WxPayException;

import java.net.URISyntaxException;

/**
 * <pre>
 *  支付分相关服务类.
 *   微信支付分是对个人的身份特质、支付行为、使用历史等情况的综合计算分值，旨在为用户提供更简单便捷的生活方式。
 *   微信用户可以在具体应用场景中，开通微信支付分。开通后，用户可以在【微信—>钱包—>支付分】中查看分数和使用记录。
 *   （即需在应用场景中使用过一次，钱包才会出现支付分入口）
 *
 *  Created by doger.wang on 2020/05/12.
 * </pre>
 *
 * @author doger.wang
 */
public interface PayScoreService {
  /**
   * <pre>
   * 支付分创建订单API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_1.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/serviceorder
   * </pre>
   *
   * @param request 请求对象
   * @return WxPayScoreResult wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult createServiceOrder(WxPayScoreRequest request) throws WxPayException;

  /**
   * <pre>
   * 支付分查询订单API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_2.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/serviceorder
   * </pre>
   *
   * @param outOrderNo the out order no
   * @param queryId    the query id
   * @return the wx pay score result
   * @throws WxPayException     the wx pay exception
   * @throws URISyntaxException the uri syntax exception
   */
  WxPayScoreResult queryServiceOrder(String outOrderNo, String queryId) throws WxPayException, URISyntaxException;

  /**
   * <pre>
   * 支付分取消订单API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_3.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/serviceorder/{out_order_no}/cancel
   * </pre>
   *
   * @param outOrderNo the out order no
   * @param reason     the reason
   * @return com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult cancelServiceOrder(String outOrderNo, String reason) throws WxPayException;

  /**
   * <pre>
   * 支付分修改订单金额API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_4.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/serviceorder/{out_order_no}/modify
   * </pre>
   *
   * @param request the request
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult modifyServiceOrder(WxPayScoreRequest request) throws WxPayException;

  /**
   * <pre>
   * 支付分完结订单API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_5.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/serviceorder/{out_order_no}/complete
   * </pre>
   *
   * @param request the request
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult completeServiceOrder(WxPayScoreRequest request) throws WxPayException;

  /**
   * <pre>
   * 支付分订单收款API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_6.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/serviceorder/{out_order_no}/pay
   *
   * </pre>
   *
   * @param outOrderNo the out order no
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult payServiceOrder(String outOrderNo) throws WxPayException;

  /**
   * <pre>
   * 支付分订单收款API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_7.shtml
   * 请求URL： https://api.mch.weixin.qq.com/v3/payscore/serviceorder/{out_order_no}/sync
   * </pre>
   *
   * @param request the request
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult syncServiceOrder(WxPayScoreRequest request) throws WxPayException;

  /**
   * <pre>
   * 支付分回调内容解析方法
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_2.shtml
   * </pre>
   *
   * @param data the data
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  PayScoreNotifyData parseNotifyData(String data);


  /**
   * <pre>
   * 支付分回调NotifyData解密resource
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_2.shtml
   * </pre>
   *
   * @param data the data
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult decryptNotifyDataResource(PayScoreNotifyData data) throws WxPayException;

}
