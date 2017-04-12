package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.request.*;
import com.github.binarywang.wxpay.bean.result.*;
import com.github.binarywang.wxpay.config.WxPayConfig;
import me.chanjar.weixin.common.exception.WxErrorException;

import java.io.File;
import java.util.Map;

/**
 * <pre>
 * 微信支付相关接口
 * Created by Binary Wang on 2016/7/28.
 * </pre>
 *
 * @author binarywang (https://github.com/binarywang)
 */
public interface WxPayService {

  /**
   * <pre>
   * 查询订单(详见https://com.github.binarywang.wechat.pay.bean.pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2)
   * 该接口提供所有微信支付订单的查询，商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。
   * 需要调用查询接口的情况：
   * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
   * ◆ 调用支付接口后，返回系统错误或未知交易状态情况；
   * ◆ 调用被扫支付API，返回USERPAYING的状态；
   * ◆ 调用关单或撤销接口API之前，需确认支付状态；
   * 接口地址：https://api.mch.weixin.qq.com/pay/orderquery
   * </pre>
   *
   * @param transactionId 微信订单号
   * @param outTradeNo    商户系统内部的订单号，当没提供transactionId时需要传这个。
   */
  WxPayOrderQueryResult queryOrder(String transactionId, String outTradeNo) throws WxErrorException;

  /**
   * <pre>
   * 关闭订单
   * 应用场景
   * 以下情况需要调用关单接口：
   * 1. 商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；
   * 2. 系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
   * 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
   * 接口地址：https://api.mch.weixin.qq.com/pay/closeorder
   * 是否需要证书：   不需要。
   * </pre>
   *
   * @param outTradeNo 商户系统内部的订单号
   */
  WxPayOrderCloseResult closeOrder(String outTradeNo) throws WxErrorException;

  /**
   * 统一下单(详见http://com.github.binarywang.wechat.pay.bean.pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1)
   * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
   * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
   *
   * @param request 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
   */
  WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest request) throws WxErrorException;

  /**
   * 该接口调用“统一下单”接口，并拼装发起支付请求需要的参数
   * 详见http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115&token=&lang=zh_CN
   *
   * @param request 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
   */
  Map<String, String> getPayInfo(WxPayUnifiedOrderRequest request) throws WxErrorException;

  /**
   * 获取配置
   */
  WxPayConfig getConfig();

  /**
   * 设置配置对象
   */
  void setConfig(WxPayConfig config);

  /**
   * <pre>
   * 微信支付-申请退款
   * 详见 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
   * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/refund
   * </pre>
   *
   * @param request 请求对象
   * @return 退款操作结果
   */
  WxPayRefundResult refund(WxPayRefundRequest request) throws WxErrorException;

  /**
   * <pre>
   * 微信支付-查询退款
   * 应用场景：
   *  提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，
   *  银行卡支付的退款3个工作日后重新查询退款状态。
   * 详见 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5
   * 接口链接：https://api.mch.weixin.qq.com/pay/refundquery
   * </pre>
   * 以下四个参数四选一
   *
   * @param transactionId 微信订单号
   * @param outTradeNo    商户订单号
   * @param outRefundNo   商户退款单号
   * @param refundId      微信退款单号
   * @return 退款信息
   */
  WxPayRefundQueryResult refundQuery(String transactionId, String outTradeNo, String outRefundNo, String refundId)
    throws WxErrorException;

  /**
   * 读取支付结果通知
   * 详见http://com.github.binarywang.wechat.pay.bean.pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
   */
  WxPayOrderNotifyResult getOrderNotifyResult(String xmlData) throws WxErrorException;

  /**
   * 微信公众号支付签名算法(详见:https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=4_3)
   *
   * @param xmlbean Bean需要标记有XML注解，默认使用配置中的PartnerKey进行签名
   * @return 签名字符串
   * @see #createSign(Map, String)
   * @since 2.5.0
   */
  String createSign(Object xmlbean);

  /**
   * 微信公众号支付签名算法(详见:https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=4_3)
   *
   * @param xmlbean Bean需要标记有XML注解
   * @param signKey 签名Key
   * @return 签名字符串
   * @see #createSign(Map, String)
   */
  String createSign(Object xmlbean, String signKey);

  /**
   * 微信公众号支付签名算法(详见:https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=4_3)
   *
   * @param prams 参数信息，默认使用配置中的PartnerKey进行签名
   * @return 签名字符串
   * @see #createSign(Map, String)
   */
  String createSign(Map<String, String> prams);


  /**
   * 微信公众号支付签名算法(详见:https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=4_3)
   *
   * @param prams   参数信息
   * @param signKey 签名Key
   * @return 签名字符串
   */
  String createSign(Map<String, String> prams, String signKey);


  /**
   * 校验签名是否正确，默认使用配置中的PartnerKey进行签名
   *
   * @param xmlbean Bean需要标记有XML注解
   * @return true - 签名校验成功，false - 签名校验失败
   * @see #checkSign(Map, String)
   */
  boolean checkSign(Object xmlbean);

  /**
   * 校验签名是否正确
   *
   * @param xmlbean Bean需要标记有XML注解
   * @param signKey 校验的签名Key
   * @return true - 签名校验成功，false - 签名校验失败
   * @see #checkSign(Map, String)
   */
  boolean checkSign(Object xmlbean, String signKey);

  /**
   * 校验签名是否正确，默认使用配置中的PartnerKey进行签名
   *
   * @param prams 需要校验的参数Map
   * @return true - 签名校验成功，false - 签名校验失败
   * @see #checkSign(Map, String)
   */
  boolean checkSign(Map<String, String> prams);

  /**
   * 校验签名是否正确
   *
   * @param params  需要校验的参数Map
   * @param signKey 校验的签名Key
   * @return true - 签名校验成功，false - 签名校验失败
   * @see #checkSign(Map, String)
   */
  boolean checkSign(Map<String, String> params, String signKey);

  /**
   * 发送微信红包给个人用户
   * <pre>
   * 文档详见:
   * 发送普通红包 https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=13_4&index=3
   *  接口地址：https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack
   * 发送裂变红包 https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=13_5&index=4
   *  接口地址：https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack
   * </pre>
   *
   * @param request 请求对象
   */
  WxPaySendRedpackResult sendRedpack(WxPaySendRedpackRequest request) throws WxErrorException;

  /**
   * <pre>
   *   查询红包记录
   *   用于商户对已发放的红包进行查询红包的具体信息，可支持普通红包和裂变包。
   *   请求Url	https://api.mch.weixin.qq.com/mmpaymkttransfers/gethbinfo
   *   是否需要证书	是（证书及使用说明详见商户证书）
   *   请求方式	POST
   * </pre>
   *
   * @param mchBillNo 商户发放红包的商户订单号，比如10000098201411111234567890
   */
  WxPayRedpackQueryResult queryRedpack(String mchBillNo) throws WxErrorException;

  /**
   * <pre>
   * 企业付款业务是基于微信支付商户平台的资金管理能力，为了协助商户方便地实现企业向个人付款，针对部分有开发能力的商户，提供通过API完成企业付款的功能。
   * 比如目前的保险行业向客户退保、给付、理赔。
   * 企业付款将使用商户的可用余额，需确保可用余额充足。查看可用余额、充值、提现请登录商户平台“资金管理”https://pay.weixin.qq.com/进行操作。
   * 注意：与商户微信支付收款资金并非同一账户，需要单独充值。
   * 文档详见:https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_2
   * 接口链接：https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers
   * </pre>
   *
   * @param request 请求对象
   */
  WxEntPayResult entPay(WxEntPayRequest request) throws WxErrorException;

  /**
   * <pre>
   * 查询企业付款API
   * 用于商户的企业付款操作进行结果查询，返回付款操作详细结果。
   * 文档详见:https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_3
   * 接口链接：https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo
   * </pre>
   *
   * @param partnerTradeNo 商户订单号
   */
  WxEntPayQueryResult queryEntPay(String partnerTradeNo) throws WxErrorException;

  /**
   * <pre>
   * 扫码支付模式一生成二维码的方法
   * 二维码中的内容为链接，形式为：
   * weixin://wxpay/bizpayurl?sign=XXXXX&appid=XXXXX&mch_id=XXXXX&product_id=XXXXXX&time_stamp=XXXXXX&nonce_str=XXXXX
   * 其中XXXXX为商户需要填写的内容，商户将该链接生成二维码，如需要打印发布二维码，需要采用此格式。商户可调用第三方库生成二维码图片。
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_4
   * </pre>
   *
   * @param productId  产品Id
   * @param sideLength 要生成的二维码的边长，如果为空，则取默认值400
   * @param logoFile   商户logo图片的文件对象，可以为空
   * @return 生成的二维码的字节数组
   */
  byte[] createScanPayQrcodeMode1(String productId, File logoFile, Integer sideLength);

  /**
   * <pre>
   * 扫码支付模式二生成二维码的方法
   * 对应链接格式：weixin：//wxpay/bizpayurl?sr=XXXXX。请商户调用第三方库将code_url生成二维码图片。
   * 该模式链接较短，生成的二维码打印到结账小票上的识别率较高。
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_5
   * </pre>
   *
   * @param codeUrl    微信返回的交易会话的二维码链接
   * @param logoFile   商户logo图片的文件对象，可以为空
   * @param sideLength 要生成的二维码的边长，如果为空，则取默认值400
   * @return 生成的二维码的字节数组
   */
  byte[] createScanPayQrcodeMode2(String codeUrl, File logoFile, Integer sideLength);

  /**
   * <pre>
   * 交易保障
   * 应用场景：
   *  商户在调用微信支付提供的相关接口时，会得到微信支付返回的相关信息以及获得整个接口的响应时间。
   *  为提高整体的服务水平，协助商户一起提高服务质量，微信支付提供了相关接口调用耗时和返回信息的主动上报接口，
   *  微信支付可以根据商户侧上报的数据进一步优化网络部署，完善服务监控，和商户更好的协作为用户提供更好的业务体验。
   * 接口地址： https://api.mch.weixin.qq.com/payitil/report
   * 是否需要证书：不需要
   * </pre>
   */
  void report(WxPayReportRequest request) throws WxErrorException;

  /**
   * <pre>
   * 下载对账单
   * 商户可以通过该接口下载历史交易清单。比如掉单、系统错误等导致商户侧和微信侧数据不一致，通过对账单核对后可校正支付状态。
   * 注意：
   * 1、微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账单中，跟原支付单订单号一致，bill_type为REVOKED；
   * 2、微信在次日9点启动生成前一天的对账单，建议商户10点后再获取；
   * 3、对账单中涉及金额的字段单位为“元”。
   * 4、对账单接口只能下载三个月以内的账单。
   * 接口链接：https://api.mch.weixin.qq.com/pay/downloadbill
   * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6">下载对账单</a>
   * </pre>
   *
   * @param billDate   对账单日期 bill_date	下载对账单的日期，格式：20140603
   * @param billType   账单类型	bill_type	ALL，返回当日所有订单信息，默认值，SUCCESS，返回当日成功支付的订单，REFUND，返回当日退款订单
   * @param tarType    压缩账单	tar_type	非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
   * @param deviceInfo 设备号	device_info	非必传参数，终端设备号
   * @return 保存到本地的临时文件
   */
  File downloadBill(String billDate, String billType, String tarType, String deviceInfo) throws WxErrorException;
}
