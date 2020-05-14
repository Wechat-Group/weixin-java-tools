package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.payscore.NotifyData;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreRequest;
import com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult;
import com.github.binarywang.wxpay.exception.WxPayException;

import java.net.URISyntaxException;

/**
 * <pre>
 *  支付分相关服务类.
 *   微信支付分是对个人的身份特质、支付行为、使用历史等情况的综合计算分值，旨在为用户提供更简单便捷的生活方式。
 *   微信用户可以在具体应用场景中，开通微信支付分。开通后，用户可以在【微信—>钱包—>支付分】中查看分数和使用记录。（即需在应用场景中使用过一次，钱包才会出现支付分入口）
 *
 *  Created by doger.wang on 2020/05/12.
 * </pre>
 *
 *
 */
public interface PayScoreService {



  /**
   * <pre>
   * 支付分创建订单API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter1_1.shtml
   * 接口链接：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_1.shtml
   * </pre>
   *
   * @param request 请求对象
   * @return WxPayScoreResult
   * @throws WxPayException the wx pay exception
   */
  WxPayScoreResult createServiceOrder(WxPayScoreRequest request) throws WxPayException;



  /**
   * <pre>
   * 支付分查询订单API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_2.shtml
   * 接口链接：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_2.shtml
   * </pre>
   *
   * @Author doger.wang
   * @Description
   * @Date  2020/5/14 15:40
   * @Param out_order_no, query_id选填一个
   * @return com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult
   **/
  WxPayScoreResult queryServiceOrder( String out_order_no,String query_id ) throws WxPayException, URISyntaxException;


  /**
   * <pre>
   * 支付分取消订单API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_3.shtml
   * 接口链接：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_3.shtml
   * </pre>
   *
   * @Author doger.wang
   * @Description
   * @Date  2020/5/14 15:40
   * @Param out_order_no reason
   * @return com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult
   **/
  WxPayScoreResult cancelServiceOrder(String out_order_no,  String reason) throws WxPayException;

  /**
   * <pre>
   * 支付分修改订单金额API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_4.shtml
   * 接口链接：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_4.shtml
   * </pre>
   *
   * @Author doger.wang
   * @Description
   * @Date  2020/5/14 15:40
   * @Param WxPayScoreRequest
   * @return com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult
   **/
  WxPayScoreResult modifyServiceOrder(WxPayScoreRequest request) throws WxPayException;


  /**
   * <pre>
   * 支付分完结订单API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_5.shtml
   * 接口链接：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_5.shtml
   * </pre>
   *
   * @Author doger.wang
   * @Description
   * @Date  2020/5/14 15:40
   * @Param WxPayScoreRequest
   * @return com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult
   **/
  WxPayScoreResult completeServiceOrder(WxPayScoreRequest request) throws WxPayException;


  /**
   * <pre>
   * 支付分订单收款API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_6.shtml
   * 接口链接：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_6.shtml
   * </pre>
   *
   * @Author doger.wang
   * @Description
   * @Date  2020/5/14 15:40
   * @Param out_order_no
   * @return com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult
   **/
  WxPayScoreResult payServiceOrder(String out_order_no) throws WxPayException;


  /**
   * <pre>
   * 支付分订单收款API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_7.shtml
   * 接口链接：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_7.shtml
   * </pre>
   *
   * @Author doger.wang
   * @Description
   * @Date  2020/5/14 15:40
   * @Param WxPayScoreRequest
   * @return com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult
   **/
  WxPayScoreResult syncServiceOrder(WxPayScoreRequest request) throws WxPayException;


  /**
   * <pre>
   * 支付分回调内容解密方法
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_2.shtml
   * 接口链接：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_2.shtml
   * </pre>
   *
   * @param NotifyData 请求对象
   * @return WxPayScoreResult
   */
  WxPayScoreResult decryptNotifyData(NotifyData data) throws WxPayException;


















}
