package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader;
import com.github.binarywang.wxpay.bean.payscore.*;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * <pre>
 *  服务商支付分相关服务类.
 *   微信支付分是对个人的身份特质、支付行为、使用历史等情况的综合计算分值，旨在为用户提供更简单便捷的生活方式。
 *   微信用户可以在具体应用场景中，开通微信支付分。开通后，用户可以在【微信—>钱包—>支付分】中查看分数和使用记录。
 *   （即需在应用场景中使用过一次，钱包才会出现支付分入口）
 *
 * @author hallkk
 * created on  2022/05/18
 */
public interface PartnerPayScoreService {


  /**
   * <pre>
   * 支付分商户预授权API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore_partner/chapter5_1.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/partner/permissions
   * </pre>
   *
   * @param request 请求对象
   * @return WxPartnerPayScoreResult wx  partner payscore result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult permissions(WxPartnerPayScoreRequest request) throws WxPayException;


  /**
   * <pre>
   * 支付分查询与用户授权记录（授权协议号）API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore_partner/chapter5_2.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/partner/permissions/authorization-code/{authorization_code}
   * </pre>
   *
   * @param serviceId
   * @param subMchid
   * @param authorizationCode
   * @return WxPayScoreResult wx partner payscore result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult permissionsQueryByAuthorizationCode(String serviceId, String subMchid,
                                                              String authorizationCode) throws WxPayException;


  /**
   * <pre>
   * 解除用户授权关系（授权协议号）API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore_partner/chapter5_4.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/partner/permissions/authorization-code/{authorization_code}/terminate
   * </pre>
   *
   * @param serviceId
   * @param subMchid
   * @param authorizationCode
   * @param reason
   * @return WxPartnerPayScoreResult wx partner payscore result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult permissionsTerminateByAuthorizationCode(String serviceId, String subMchid,
                                                                  String authorizationCode, String reason) throws WxPayException;


  /**
   * <pre>
   * 支付分查询与用户授权记录（openid）API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore_partner/chapter5_3.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/partner/permissions/search
   * </pre>
   *
   * @param serviceId
   * @param subMchid
   * @param subAppid
   * @param openId
   * @param subOpenid
   * @return WxPayScoreResult wx partner payscore result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult permissionsQueryByOpenId(String serviceId, String appId, String subMchid, String subAppid,
                                                   String openId, String subOpenid) throws WxPayException;


  /**
   * <pre>
   * 解除用户授权关系（openid）API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore_partner/chapter5_5.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/partner/permissions/openid/{openid}/terminate
   * </pre>
   *
   * @param serviceId
   * @param subMchid
   * @param subAppid
   * @param openId
   * @param subOpenid
   * @param reason
   * @return WxPayScoreResult wx partner payscore result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult permissionsTerminateByOpenId(String serviceId, String appId, String subMchid, String subAppid,
                                                       String openId, String subOpenid, String reason) throws WxPayException;


  /**
   * <pre>
   * 支付分创建订单API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore_partner/chapter3_1.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/partner/serviceorder
   * </pre>
   *
   * @param request 请求对象
   * @return WxPayScoreResult wx partner payscore result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult createServiceOrder(WxPartnerPayScoreRequest request) throws WxPayException;

  /**
   * <pre>
   * 支付分查询订单API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore_partner/chapter3_2.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/partner/serviceorder
   * </pre>
   *
   * @param serviceId
   * @param subMchid
   * @param outOrderNo the out order no
   * @param queryId    the query id
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult queryServiceOrder(String serviceId, String subMchid,
                                            String outOrderNo, String queryId) throws WxPayException;

  /**
   * <pre>
   * 支付分取消订单API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore_partner/chapter3_3.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/partner/serviceorder/{out_order_no}/cancel
   * </pre>
   * <p>
   *
   * @param serviceId
   * @param subMchid
   * @param outOrderNo the out order no
   * @param reason     the reason
   * @return com.github.binarywang.wxpay.bean.payscore.WxPayScoreResult wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult cancelServiceOrder(String serviceId, String appId, String subMchid,
                                             String outOrderNo, String reason) throws WxPayException;

  /**
   * <pre>
   * 支付分修改订单金额API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore_partner/chapter3_4.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/payscore/partner/serviceorder/{out_order_no}/modify
   * </pre>
   * <p>
   *
   * @param request the request
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult modifyServiceOrder(WxPartnerPayScoreRequest request) throws WxPayException;

  /**
   * <pre>
   * 支付分完结订单API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore_partner/chapter3_5.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/partner/serviceorder/{out_order_no}/complete
   * </pre>
   *
   * @param request the request
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  void completeServiceOrder(WxPartnerPayScoreRequest request) throws WxPayException;

  /**
   * <pre>
   * 商户发起催收扣款API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore_partner/chapter3_6.shtml
   * 请求URL：https://api.mch.weixin.qq.com/v3/payscore/partner/serviceorder/{out_order_no}/pay
   *
   * </pre>
   *
   * @param serviceId
   * @param subMchid
   * @param outOrderNo the out order no
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult payServiceOrder(String serviceId, String appId, String subMchid, String outOrderNo) throws WxPayException;

  /**
   * <pre>
   * 支付分订单收款API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore_partner/chapter3_7.shtml
   * 请求URL： https://api.mch.weixin.qq.com/v3/payscore/partner/serviceorder/{out_order_no}/sync
   * </pre>
   *
   * @param request the request
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult syncServiceOrder(WxPartnerPayScoreRequest request) throws WxPayException;

  /**
   * <pre>
   * 收付通子商户申请绑定支付分服务API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore_partner/chapter9_1.shtml
   * 请求URL： https://api.mch.weixin.qq.com/v3/payscore/partner/service-account-applications
   * </pre>
   *
   * @param request the request
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult applyServiceAccount(WxPartnerPayScoreRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询收付通子商户服务绑定结果API.
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore_partner/chapter9_2.shtml
   * 请求URL： https://api.mch.weixin.qq.com/v3/payscore/partner/service-account-applications/{out_apply_no}
   * </pre>
   *
   * @param outApplyNo 商户申请绑定单号
   * @return the wx pay score result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerPayScoreResult queryServiceAccountState(String outApplyNo) throws WxPayException;

  /**
   * <pre>
   * 授权/解除授权服务回调数据处理
   * 文档地址: https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter6_2_23.shtml
   * </pre>
   *
   * @param notifyData 通知数据
   * @param header     通知头部数据，不传则表示不校验头
   * @return 解密后通知数据 return user authorization status notify result
   * @throws WxPayException the wx pay exception
   */
  WxPartnerUserAuthorizationStatusNotifyResult parseUserAuthorizationStatusNotifyResult(String notifyData, SignatureHeader header) throws WxPayException;

  /**
   * <pre>
   * 支付分回调内容解析方法
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3_partner/Offline/apis/chapter6_2_9.shtml
   * </pre>
   *
   * @param data the data
   * @return the wx pay score result
   */
  PayScoreNotifyData parseNotifyData(String data, SignatureHeader header) throws WxPayException;

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
  WxPartnerPayScoreResult decryptNotifyDataResource(PayScoreNotifyData data) throws WxPayException;

}
