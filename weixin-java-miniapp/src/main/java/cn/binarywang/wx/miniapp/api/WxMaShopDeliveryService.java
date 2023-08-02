package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopDeliveryRecieveRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopDeliverySendRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopDeliveryGetCompanyListResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序交易组件-物流发货服务
 *
 * @author boris
 * @author liming1019
 */
public interface WxMaShopDeliveryService {
  /**
   * 获取快递公司列表
   *
   * @return WxMaShopDeliveryGetCompanyListResponse
   * @throws WxErrorException
   */
  WxMaShopDeliveryGetCompanyListResponse getCompanyList() throws WxErrorException;

  /**
   * 订单发货
   *
   * @param request
   * @return WxMaShopBaseResponse
   * @throws WxErrorException
   */
  WxMaShopBaseResponse send(WxMaShopDeliverySendRequest request) throws WxErrorException;

  /**
   * 订单确认收货
   *
   * @param request
   * @return WxMaShopBaseResponse
   * @throws WxErrorException
   */
  WxMaShopBaseResponse receive(WxMaShopDeliveryRecieveRequest request) throws WxErrorException;

}
