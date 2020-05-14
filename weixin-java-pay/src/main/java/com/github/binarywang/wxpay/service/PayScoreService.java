package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.entpay.*;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreCreateRequest;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreCreateResult;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 *  支付分相关服务类.
 *  Created by DogerWang on 2020/05/12.
 * </pre>
 *
 *
 */
public interface PayScoreService {



  /**
   * <pre>
   * 支付分创建订单API.
   * 微信支付分是对个人的身份特质、支付行为、使用历史等情况的综合计算分值，旨在为用户提供更简单便捷的生活方式。
   * 微信用户可以在具体应用场景中，开通微信支付分。开通后，用户可以在【微信—>钱包—>支付分】中查看分数和使用记录。（即需在应用场景中使用过一次，钱包才会出现支付分入口）
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter1_1.shtml
   * 接口链接：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_1.shtml
   * </pre>
   *
   * @param request 请求对象
   * @return the ent pay result
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreCreateResult createServiceOrder(WxPayScoreCreateRequest request) throws WxPayException;

















}
