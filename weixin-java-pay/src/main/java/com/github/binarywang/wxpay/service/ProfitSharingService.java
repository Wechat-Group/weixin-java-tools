package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.profitsharing.*;
import com.github.binarywang.wxpay.exception.WxPayException;

import com.github.binarywang.wxpay.bean.profitsharing.ProfitSharingResult;
import com.github.binarywang.wxpay.bean.profitsharing.ProfitSharingRequest;

/**
 * 注意：微信最高分账比例为30%
 * 可多次分账到同一个人，但是依然不能超过30%
 *
 * @author Wang GuangXin 2019/10/22 10:05
 * @version 1.0
 */
public interface ProfitSharingService {
  /**
   * <pre>
   * 单次分账请求按照传入的分账接收方账号和资金进行分账，同时会将订单剩余的待分账金额解冻给特约商户。故操作成功后，订单不能再进行分账，也不能进行分账完结。
   * 接口频率：30QPS
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_1&index=1
   * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/profitsharing
   * </pre>
   *
   * @param profitsharingRequest
   * @return
   * @throws WxPayException the wx pay exception
   */
  ProfitSharingResult profitsharing(ProfitSharingRequest profitsharingRequest) throws WxPayException;

  /**
   * <pre>
   * 微信订单支付成功后，服务商代子商户发起分账请求，将结算后的钱分到分账接收方。多次分账请求仅会按照传入的分账接收方进行分账，不会对剩余的金额进行任何操作。故操作成功后，在待分账金额不等于零时，订单依旧能够再次进行分账。
   * 多次分账，可以将本商户作为分账接收方直接传入，实现释放资金给本商户的功能
   * 对同一笔订单最多能发起20次多次分账请求
   * 接口频率：30QPS
   * </pre>
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_6&index=2
   * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/multiprofitsharing
   *
   * @param profitsharingRequest
   * @return
   * @throws WxPayException the wx pay exception
   */
  ProfitSharingResult multiprofitsharing(ProfitSharingRequest profitsharingRequest) throws WxPayException;

  /**
   * <pre>
   * 1、不需要进行分账的订单，可直接调用本接口将订单的金额全部解冻给特约商户
   * 2、调用多次分账接口后，需要解冻剩余资金时，调用本接口将剩余的分账金额全部解冻给特约商户
   * 3、已调用请求单次分账后，剩余待分账金额为零，不需要再调用此接口。
   * 接口频率：30QPS
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_5&index=6
   * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/profitsharingfinish
   * </pre>
   *
   * @param profitSharingFinishRequest
   * @return
   * @throws WxPayException the wx pay exception
   */
  ProfitSharingResult profitsharingfinish(ProfitSharingFinishRequest profitSharingFinishRequest) throws WxPayException;

  /**
   * <pre>
   * 服务商代子商户发起添加分账接收方请求，后续可通过发起分账请求将结算后的钱分到该分账接收方。
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_3&index=4
   * 接口链接：https://api.mch.weixin.qq.com/pay/profitsharingaddreceiver
   * </pre>
   *
   * @param profitSharingReceiverRequest
   * @return
   * @throws WxPayException
   */
  ProfitSharingReceiverResult addReceiver(ProfitSharingReceiverRequest profitSharingReceiverRequest) throws WxPayException;

  /**
   * <pre>
   * 服务商代子商户发起删除分账接收方请求，删除后不支持将结算后的钱分到该分账接收方。
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_4&index=5
   * 接口链接：https://api.mch.weixin.qq.com/pay/profitsharingremovereceiver
   * </pre>
   *
   * @param profitSharingReceiverRequest
   * @return
   * @throws WxPayException
   */
  ProfitSharingReceiverResult removeReceiver(ProfitSharingReceiverRequest profitSharingReceiverRequest) throws WxPayException;

  /**
   * TODO:微信返回签名失败
   * <pre>
   * 发起分账请求后，可调用此接口查询分账结果；发起分账完结请求后，可调用此接口查询分账完结的执行结果。
   * 接口频率：80QPS
   * </pre>
   *
   * @param profitSharingReceiverRequest
   * @return
   * @throws WxPayException
   */
  ProfitSharingQueryResult profitsharingQuery(ProfitSharingQueryRequest profitSharingReceiverRequest) throws WxPayException;

  /**
   * TODO:这个接口用真实的数据返回【参数不正确】，我对比官方文档除了缺少sub_mch_id，和sub_appid之外其他相同，当我随便填了一个商户id的时候，提示【回退方没有开通分账回退功能】
   * <pre>
   * 仅对订单进行退款时，如果订单已经分账，可以先调用此接口将指定的金额从分账接收方（仅限商户类型的分账接收方）回退给特约商户，然后再退款。
   * 回退以原分账请求为依据，可以对分给分账接收方的金额进行多次回退，只要满足累计回退不超过该请求中分给接收方的金额。
   * 此接口采用同步处理模式，即在接收到商户请求后，会实时返回处理结果。
   * 此功能需要接收方在商户平台-交易中心-分账-分账接收设置下，开启同意分账回退后，才能使用。
   * 接口频率：30QPS
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_7&index=7
   * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/profitsharingreturn
   * </pre>
   *
   * @param profitSharingReturnRequest
   * @return
   * @throws WxPayException
   */
  ProfitSharingReturnResult profitsharingReturn(ProfitSharingReturnRequest profitSharingReturnRequest) throws WxPayException;

  /**
   * TODO:因profitsharingReturn接口无法使用，没有办法对这里进行真实的测试，模拟数据这里返回【记录不存在】
   * <pre>
   * 商户需要核实回退结果，可调用此接口查询回退结果。
   * 如果分账回退接口返回状态为处理中，可调用此接口查询回退结果。
   * 接口频率：30QPS
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=25_8&index=8
   * 接口链接：https://api.mch.weixin.qq.com/pay/profitsharingreturnquery
   * </pre>
   *
   * @param profitSharingReturnQueryRequest
   * @return
   * @throws WxPayException
   */
  ProfitSharingReturnResult profitsharingReturnQuery(ProfitSharingReturnQueryRequest profitSharingReturnQueryRequest) throws WxPayException;

}
