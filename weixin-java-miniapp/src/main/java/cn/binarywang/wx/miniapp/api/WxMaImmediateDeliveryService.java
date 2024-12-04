package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import cn.binarywang.wx.miniapp.bean.delivery.*;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小程序即时配送服务.
 * <pre>
 *     文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/immediate-delivery/overview.html
 * </pre>
 *
 * @author Luo
 * @version 1.0
 * created on  2021-10-13 16:40
 */
public interface WxMaImmediateDeliveryService {

  /**
   * 拉取已绑定账号.
   * <pre>
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.getBindAccount.html
   * </pre>
   *
   * @return 响应
   * @throws WxErrorException 异常
   */
  BindAccountResponse getBindAccount() throws WxErrorException;

  /**
   * 下配送单接口.
   * <pre>
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.addOrder.html
   * </pre>
   *
   * @param request request
   * @return 响应
   * @throws WxErrorException 异常
   */
  AddOrderResponse addOrder(AddOrderRequest request) throws WxErrorException;

  /**
   * 拉取配送单信息.
   * <pre>
   * 商家可使用本接口查询某一配送单的配送状态，便于商家掌握配送情况。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.getOrder.html
   * </pre>
   *
   * @param request request
   * @return 响应
   * @throws WxErrorException 异常
   */
  GetOrderResponse getOrder(GetOrderRequest request) throws WxErrorException;

  /**
   * 取消配送单接口.
   * <pre>
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.cancelOrder.html
   * </pre>
   *
   * @param request request
   * @return 响应
   * @throws WxErrorException 异常
   */
  CancelOrderResponse cancelOrder(CancelOrderRequest request) throws WxErrorException;

  /**
   * 异常件退回商家商家确认收货接口.
   * <pre>
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.abnormalConfirm.html
   * </pre>
   *
   * @param request request
   * @return 响应
   * @throws WxErrorException 异常
   */
  AbnormalConfirmResponse abnormalConfirm(AbnormalConfirmRequest request) throws WxErrorException;

  /**
   * 模拟配送公司更新配送单状态, 该接口只用于沙盒环境，即订单并没有真实流转到运力方.
   * <pre>
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.mockUpdateOrder.html
   * </pre>
   *
   * @param request request
   * @return 响应
   * @throws WxErrorException 异常
   */
  MockUpdateOrderResponse mockUpdateOrder(MockUpdateOrderRequest request) throws WxErrorException;


  /**
   * 商户使用此接口向微信提供某交易单号对应的运单号。微信后台会跟踪运单的状态变化
   * <pre>
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/express/express_search.html
   * </pre>
   *
   * @param request request
   * @return 响应
   * @throws WxErrorException 异常
   */
  TraceWaybillResponse traceWaybill(TraceWaybillRequest request) throws WxErrorException;


  /**
   * 商户在调用完trace_waybill接口后，可以使用本接口查询到对应运单的详情信息
   * <pre>
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/express/express_search.html
   * </pre>
   *
   * @param request request
   * @return 响应
   * @throws WxErrorException 异常
   */
  QueryWaybillTraceResponse queryWaybillTrace(QueryWaybillTraceRequest request)
    throws WxErrorException;


  /**
   * 传运单接口 follow_waybill 订阅微信后台会跟踪运单的状态变化
   * <pre>
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/express/express_open_msg.html
   * </pre>
   *
   * @param request request
   * @return 响应
   * @throws WxErrorException 异常
   */
  FollowWaybillResponse followWaybill(FollowWaybillRequest request)
    throws WxErrorException;


  /**
   * 查运单接口 query_follow_trace
   *
   * <pre>
   * 商户在调用完trace_waybill接口后，可以使用本接口查询到对应运单的详情信息
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/express/express_open_msg.html
   * </pre>
   *
   * @param request request
   * @return 响应
   * @throws WxErrorException 异常
   */
  QueryFollowTraceResponse queryFollowTrace(QueryFollowTraceRequest request)
    throws WxErrorException ;

  /**
   * 获取运力id列表get_delivery_list
   *
   * <pre>
   * 商户使用此接口获取所有运力id的列表
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/express/business/express_search.html
   * </pre>
   *
   * @return 响应
   * @throws WxErrorException 异常
   */
  GetDeliveryListResponse getDeliveryList() throws WxErrorException;

  /**
   * 更新物流物品信息接口 update_waybill_goods
   *
   * <pre>
   * 更新物品信息
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/express/business/express_search.html
   * </pre>
   *
   * @return 响应
   * @throws WxErrorException 异常
   */
  WxMaBaseResponse updateWaybillGoods(UpdateWaybillGoodsRequest request) throws WxErrorException;

}
