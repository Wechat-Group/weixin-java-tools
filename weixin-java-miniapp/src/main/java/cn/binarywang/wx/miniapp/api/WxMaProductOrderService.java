package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.product.WxMiniBatchGetAfterSaleOrderResponse;
import cn.binarywang.wx.miniapp.bean.product.WxMiniGetAfterSaleOrderResponse;
import cn.binarywang.wx.miniapp.bean.product.WxMiniOrderDeliveryRequest;
import cn.binarywang.wx.miniapp.bean.product.WxMinishopOrderDetailResponse;
import cn.binarywang.wx.miniapp.bean.product.WxMinishopOrderListResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import java.util.List;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序交易组件-标准版-商品服务
 *
 * @author boris
 */
public interface WxMaProductOrderService {


  /**
   * 获取订单列表
   *
   * @param startCreateTime 否(未填更新时间范围时必填)
   * @param endCreateTime   否(未填更新时间范围时必填)
   * @param startUpdateTime 否(未填创建时间范围时必填)
   * @param endUpdateTime   否(未填创建时间范围时必填)
   * @param status          订单状态，枚举值见RequestOrderStatus
   * @param page            第几页（最小填1）
   * @param pageSize        每页数量(不超过10,000)
   * @param source          1:小商店,2:CPS带货
   * @return
   * @throws WxErrorException
   */
  WxMinishopOrderListResponse getOrderList(
    String startCreateTime,
    String endCreateTime,
    String startUpdateTime,
    String endUpdateTime,
    Integer status,
    Integer page,
    Integer pageSize,
    Integer source
  ) throws WxErrorException;


  /**
   * 获取订单详情
   *
   * @param orderId 订单ID，可从获取订单列表中获得
   * @return
   */
  WxMinishopOrderDetailResponse getOrderDetail(Long orderId) throws WxErrorException;


  /**
   * 修改订单备注
   * @param orderId  订单id
   * @param merchantNotes 备注内容
   */
  void changeMerchantNotes(Long orderId,String merchantNotes) throws WxErrorException;

  WxMaShopBaseResponse deliverySend(WxMiniOrderDeliveryRequest request)
    throws WxErrorException;

  WxMiniGetAfterSaleOrderResponse getAfterSaleOrder(Long afterSaleOrderId)
    throws WxErrorException;

  WxMiniBatchGetAfterSaleOrderResponse batchGetAfterSaleOrder(List<Long> afterSaleOrderIdList)
    throws WxErrorException;

  WxMaShopBaseResponse afterSaleAccept(Long orderId, Long addressId)
    throws WxErrorException;

  WxMaShopBaseResponse afterSaleReject(Long afterSaleOrderId, String rejectReason)
    throws WxErrorException;
}
