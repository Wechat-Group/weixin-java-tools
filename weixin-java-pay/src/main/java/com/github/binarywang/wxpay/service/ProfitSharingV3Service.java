package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader;
import com.github.binarywang.wxpay.bean.profitsharingV3.*;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 微信支付V3-资金应用-分账
 *
 * @author pg 2021-6-23
 * @date 2021-6-23
 */
public interface ProfitSharingV3Service {
  /**
   * <pre>
   * 请求分账API
   *
   * 微信订单支付成功后，商户发起分账请求，将结算后的资金分到分账接收方
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_1.shtml
   * 接口链接: https://api.mch.weixin.qq.com/v3/profitsharing/orders
   *
   * 注意：
   * 对同一笔订单最多能发起20次分账请求，每次请求最多分给50个接收方
   * 此接口采用异步处理模式，即在接收到商户请求后，优先受理请求再异步处理，最终的分账结果可以通过查询分账接口获取
   * </pre>
   *
   * @param request {@link ProfitSharingRequest} 针对某一笔支付订单的分账方法
   * @return {@link ProfitSharingResult} 微信返回的分账结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_1.shtml">微信文档</a>
   */
  ProfitSharingResult profitSharing(ProfitSharingRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询分账结果API
   *
   * 发起分账请求后，可调用此接口查询分账结果
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_2.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/profitsharing/orders/{out_order_no}
   *
   * 注意：
   * • 发起解冻剩余资金请求后，可调用此接口查询解冻剩余资金的结果
   * </pre>
   *
   * @param outOrderNo    商户系统内部的分账单号，在商户系统内部唯一，同一分账单号多次请求等同一次。只能是数字、大小写字母_-|*@ 。
   * @param transactionId 微信支付订单号
   * @return {@link ProfitSharingResult} 微信返回的分账结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_2.shtml">微信文档</a>
   */
  ProfitSharingResult getProfitSharingResult(String outOrderNo, String transactionId) throws WxPayException;

  /**
   * <pre>
   * 请求分账回退API
   *
   * 如果订单已经分账，在退款时，可以先调此接口，将已分账的资金从分账接收方的账户回退给分账方，再发起退款
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_3.shtml
   * 接口链接: https://api.mch.weixin.qq.com/v3/profitsharing/return-orders
   *
   * 注意：
   * • 分账回退以原分账单为依据，支持多次回退，申请回退总金额不能超过原分账单分给该接收方的金额
   * • 此接口采用同步处理模式，即在接收到商户请求后，会实时返回处理结果
   * • 对同一笔分账单最多能发起20次分账回退请求
   * • 退款和分账回退没有耦合，分账回退可以先于退款请求，也可以后于退款请求
   * • 此功能需要接收方在商户平台-交易中心-分账-分账接收设置下，开启同意分账回退后，才能使用
   * </pre>
   *
   * @param request {@link ProfitSharingReturnRequest} 针对某一笔支付订单的分账方法
   * @return {@link ProfitSharingReturnResult} 微信返回的分账回退结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_3.shtml">微信文档</a>
   */
  ProfitSharingReturnResult profitSharingReturn(ProfitSharingReturnRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询分账回退结果API
   *
   * 商户需要核实回退结果，可调用此接口查询回退结果
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_4.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/profitsharing/return-orders/{out_return_no}
   *
   * 注意：
   * • 如果分账回退接口返回状态为处理中，可调用此接口查询回退结果
   * </pre>
   *
   * @param outOrderNo  原发起分账请求时使用的商户系统内部的分账单号
   * @param outReturnNo 调用回退接口提供的商户系统内部的回退单号
   * @return {@link ProfitSharingReturnResult} 微信返回的分账回退结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_4.shtml">微信文档</a>
   */
  ProfitSharingReturnResult getProfitSharingReturnResult(String outOrderNo, String outReturnNo) throws WxPayException;

  /**
   * <pre>
   * 解冻剩余资金API
   *
   * 不需要进行分账的订单，可直接调用本接口将订单的金额全部解冻给特约商户
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_5.shtml
   * 接口链接: https://api.mch.weixin.qq.com/v3/profitsharing/orders/unfreeze
   *
   * 注意：
   * • 调用分账接口后，需要解冻剩余资金时，调用本接口将剩余的分账金额全部解冻给特约商户
   * • 此接口采用异步处理模式，即在接收到商户请求后，优先受理请求再异步处理，最终的分账结果可以通过查询分账接口获取
   * </pre>
   *
   * @param request 解冻剩余资金请求实体 {@link ProfitSharingUnfreezeRequest}
   * @return {@link ProfitSharingReturnResult} 微信返回的解冻剩余资金结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_5.shtml">微信文档</a>
   */
  ProfitSharingUnfreezeResult profitSharingUnfreeze(ProfitSharingUnfreezeRequest request) throws WxPayException;

  /**
   * <pre>
   * 查询剩余待分金额API
   *
   * 可调用此接口查询订单剩余待分金额
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_6.shtml
   * 接口链接: https://api.mch.weixin.qq.com/v3/profitsharing/transactions/{transaction_id}/amounts
   * </pre>
   *
   * @param transactionId 微信订单号，微信支付订单号
   * @return {@link ProfitSharingUnsplitResult} 微信返回的订单剩余待分金额结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_6.shtml">微信文档</a>
   */
  ProfitSharingUnsplitResult getProfitSharingUnsplitAmount(String transactionId) throws WxPayException;

  /**
   * <pre>
   * 添加分账接收方API
   *
   * 商户发起添加分账接收方请求，建立分账接收方列表。后续可通过发起分账请求，将分账方商户结算后的资金，分到该分账接收方
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_8.shtml
   * 接口链接: https://api.mch.weixin.qq.com/v3/profitsharing/receivers/add
   * </pre>
   *
   * @param receiver 分账接收方实体 {@link ProfitSharingReceiver}
   * @return {@link ProfitSharingReceiver} 微信返回的分账接收方结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_8.shtml">微信文档</a>
   */
  ProfitSharingReceiver addProfitSharingReceiver(ProfitSharingReceiver receiver) throws WxPayException;

  /**
   * <pre>
   * 删除分账接收方API
   *
   * 商户发起删除分账接收方请求。删除后，不支持将分账方商户结算后的资金，分到该分账接收方
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_9.shtml
   * 接口链接: https://api.mch.weixin.qq.com/v3/profitsharing/receivers/delete
   * </pre>
   *
   * @param receiver 分账接收方实体 {@link ProfitSharingReceiver}
   * @return {@link ProfitSharingReceiver} 微信返回的删除的分账接收方结果
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_9.shtml">微信文档</a>
   */
  ProfitSharingReceiver deleteProfitSharingReceiver(ProfitSharingReceiver receiver) throws WxPayException;


  /**
   * <pre>
   * 分账动账通知
   *
   * 分账或分账回退成功后，微信会把相关变动结果发送给分账接收方（只支持商户）。
   * 对后台通知交互时，如果微信收到应答不是成功或超时，微信认为通知失败，微信会通过一定的策略定期重新发起通知，尽可能提高通知的成功率，但微信不保证通知最终能成功。
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_10.shtml
   * </pre>
   *
   * @param notifyData 分账通知实体
   * @param header     分账通知头 {@link SignatureHeader}
   * @return {@link ProfitSharingNotifyData} 资源对象
   * @throws WxPayException the wx pay exception
   * @see <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter8_1_10.shtml">微信文档</a>
   */
  ProfitSharingNotifyData getProfitSharingNotifyData(String notifyData, SignatureHeader header) throws WxPayException;

}
