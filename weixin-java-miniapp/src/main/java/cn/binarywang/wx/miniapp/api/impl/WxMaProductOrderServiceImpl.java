package cn.binarywang.wx.miniapp.api.impl;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Order.AFTER_SALE_ACCEPT_APPLY;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Order.AFTER_SALE_REJECT_APPLY;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Order.BATCH_GET_AFTER_SALE_ORDER;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Order.GET_AFTER_SALE_ORDER;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Order.PRODUCT_DELIVERY_SEND;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Order.PRODUCT_ORDER_CHANGE_MERCHANT_NOTES_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Order.PRODUCT_ORDER_DETAIL_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Product.Order.PRODUCT_ORDER_GET_LIST;

import cn.binarywang.wx.miniapp.api.WxMaProductOrderService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.product.WxMiniBatchGetAfterSaleOrderResponse;
import cn.binarywang.wx.miniapp.bean.product.WxMiniGetAfterSaleOrderResponse;
import cn.binarywang.wx.miniapp.bean.product.WxMiniOrderDeliveryRequest;
import cn.binarywang.wx.miniapp.bean.product.WxMinishopOrderDetailResponse;
import cn.binarywang.wx.miniapp.bean.product.WxMinishopOrderListResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

/**
 * 小程序交易组件-标准版-订单服务
 *
 * @author boris 详情请见 : https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/ministore/minishopopencomponent/API/order/get_order_list.html
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaProductOrderServiceImpl implements WxMaProductOrderService {

  private final WxMaService wxMaService;


  @Override
  public WxMinishopOrderListResponse getOrderList(
    String startCreateTime, String endCreateTime, String startUpdateTime,
    String endUpdateTime, Integer status, Integer page, Integer pageSize, Integer source)
    throws WxErrorException {
    String responseContent = this.wxMaService
      .post(PRODUCT_ORDER_GET_LIST, GsonHelper.buildJsonObject(
        "start_create_time", startCreateTime, "end_create_time", endCreateTime,
        "start_update_time", startUpdateTime, "end_update_time", endUpdateTime,
        "status", status, "page", page, "page_size", pageSize, "source", source));

    WxMinishopOrderListResponse response = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMinishopOrderListResponse.class);

    if (response.getErrCode() != 0) {
      throw new WxErrorException(new WxError(response.getErrCode(), response.getErrMsg()));
    }

    return response;

  }

  @Override
  public WxMinishopOrderDetailResponse getOrderDetail(
    Long orderId) throws WxErrorException {
    String responseContent = this.wxMaService
      .post(PRODUCT_ORDER_DETAIL_URL, GsonHelper.buildJsonObject(
        "order_id", orderId));

    WxMinishopOrderDetailResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMinishopOrderDetailResponse.class);

    if (getDetailResponse.getErrCode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrCode(), getDetailResponse.getErrMsg()));
    }

    return getDetailResponse;
  }

  @Override
  public void changeMerchantNotes(Long orderId, String merchantNotes) throws WxErrorException {
    String responseContent = this.wxMaService
      .post(PRODUCT_ORDER_CHANGE_MERCHANT_NOTES_URL, GsonHelper.buildJsonObject(
        "order_id", orderId,"merchant_notes",merchantNotes));

    WxMaShopBaseResponse changeResult = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaShopBaseResponse.class);

    if (changeResult.getErrCode() != 0) {
      throw new WxErrorException(
        new WxError(changeResult.getErrCode(), changeResult.getErrMsg()));
    }
  }

  @Override
  public WxMaShopBaseResponse deliverySend(WxMiniOrderDeliveryRequest request)
    throws WxErrorException {
    String response = this.wxMaService.post(PRODUCT_DELIVERY_SEND, request);
    WxMaShopBaseResponse baseResponse = WxMaGsonBuilder.create()
      .fromJson(response, WxMaShopBaseResponse.class);
    if (baseResponse.getErrCode() != 0) {
      throw new WxErrorException(
        new WxError(baseResponse.getErrCode(), baseResponse.getErrMsg()));
    }
    return baseResponse;
  }

  @Override
  public WxMiniGetAfterSaleOrderResponse getAfterSaleOrder(Long afterSaleOrderId)
    throws WxErrorException {
    String response = this.wxMaService.post(GET_AFTER_SALE_ORDER,
      GsonHelper.buildJsonObject("after_sale_order_id", afterSaleOrderId));

    WxMiniGetAfterSaleOrderResponse orderResponse = WxMaGsonBuilder.create()
      .fromJson(response, WxMiniGetAfterSaleOrderResponse.class);
    if (orderResponse.getErrCode() != 0) {
      throw new WxErrorException(
        new WxError(orderResponse.getErrCode(), orderResponse.getErrMsg()));
    }
    return orderResponse;
  }

  @Override
  public WxMiniBatchGetAfterSaleOrderResponse batchGetAfterSaleOrder(
    List<Long> afterSaleOrderIdList)
    throws WxErrorException {
    String response = this.wxMaService.post(BATCH_GET_AFTER_SALE_ORDER,
      GsonHelper.buildJsonObject("after_sale_order_id_list", afterSaleOrderIdList));

    WxMiniBatchGetAfterSaleOrderResponse orderResponse = WxMaGsonBuilder.create()
      .fromJson(response, WxMiniBatchGetAfterSaleOrderResponse.class);
    if (orderResponse.getAfterSaleOrderList() == null) {
      throw new WxErrorException(
        new WxError(orderResponse.getErrCode(), "售后查询不存在"));
    }
    return orderResponse;
  }

  @Override
  public WxMaShopBaseResponse afterSaleAccept(Long orderId, Long addressId)
    throws WxErrorException {
    String response = this.wxMaService.post(AFTER_SALE_ACCEPT_APPLY,
      GsonHelper.buildJsonObject("order_id", orderId, "address_id", addressId));
    WxMaShopBaseResponse baseResponse = WxGsonBuilder.create()
      .fromJson(response, WxMaShopBaseResponse.class);
    if (baseResponse.getErrCode() != 0) {
      throw new WxErrorException(
        new WxError(baseResponse.getErrCode(), baseResponse.getErrMsg()));
    }
    return baseResponse;
  }

  @Override
  public WxMaShopBaseResponse afterSaleReject(Long afterSaleOrderId, String rejectReason)
    throws WxErrorException {
    String response = this.wxMaService.post(AFTER_SALE_REJECT_APPLY,
      GsonHelper.buildJsonObject("order_id", afterSaleOrderId, "reject_reason", rejectReason));
    WxMaShopBaseResponse baseResponse = WxGsonBuilder.create()
      .fromJson(response, WxMaShopBaseResponse.class);
    if (baseResponse.getErrCode() != 0) {
      throw new WxErrorException(
        new WxError(baseResponse.getErrCode(), baseResponse.getErrMsg()));
    }
    return baseResponse;
  }
}
