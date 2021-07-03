package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopOrderInfo;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopOrderPayRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAddOrderResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopGetOrderResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序交易组件-订单服务
 *
 * @author boris
 */
public interface WxMaShopOrderService {
  Boolean checkScene(Integer scene) throws WxErrorException;

  WxMaShopAddOrderResponse addOrder(WxMaShopOrderInfo orderInfo) throws WxErrorException;

  WxMaShopBaseResponse orderPay(WxMaShopOrderPayRequest request) throws WxErrorException;

  WxMaShopGetOrderResponse getOrder(Integer orderId, String outOrderId, String openid)
    throws WxErrorException;
}
