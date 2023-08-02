package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopPayCreateOrderRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopPayOrderRefundRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopPayCreateOrderResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopPayGetOrderResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序支付管理订单相关接口
 *
 * @author liming1019
 */
public interface WxMaShopPayService {

  /**
   * 创建订单
   * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/ministore/wxafunds/API/order/create_order.html">文档地址</a>
   *
   * @param request 创建订单参数
   * @return 创建订单结果
   * @throws WxErrorException .
   */
  WxMaShopPayCreateOrderResponse createOrder(WxMaShopPayCreateOrderRequest request) throws WxErrorException;

  /**
   * 查询订单详情
   * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/ministore/wxafunds/API/order/get_order_detail.html">文档地址</a>
   *
   * @param trade_no
   * @return
   * @throws WxErrorException
   */
  WxMaShopPayGetOrderResponse getOrder(String trade_no) throws WxErrorException;

  /**
   * 订单退款
   * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/ministore/wxafunds/API/order/refunds_order.html">文档地址</a>
   *
   * @param request
   * @return
   * @throws WxErrorException
   */
  WxMaShopBaseResponse refundOrder(WxMaShopPayOrderRefundRequest request) throws WxErrorException;
}
