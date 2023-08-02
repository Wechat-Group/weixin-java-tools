package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopOrderInfo;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopOrderPayRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.*;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.Date;

/**
 * 小程序交易组件-订单服务
 *
 * @author boris
 */
public interface WxMaShopOrderService {
  Boolean checkScene(Integer scene) throws WxErrorException;

  WxMaShopAddOrderResponse addOrder(WxMaShopOrderInfo orderInfo) throws WxErrorException;

  WxMaShopBaseResponse orderPay(WxMaShopOrderPayRequest request) throws WxErrorException;

  WxMaShopGetOrderResponse getOrder(Long orderId, String outOrderId, String openid)
    throws WxErrorException;


  /**
   * <pre>
   *
   * 获取订单列表
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：<a href="https://api.weixin.qq.com/shop/order/get_list">请求地址</a>
   *
   * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/ministore/minishopopencomponent2/API/order/get_order_list.html">文档地址</a>
   * </pre>
   *
   * @param page  第x页，大于等于1
   * @param pageSize  每页订单数，上限100
   * @param desc  是否时间倒叙
   * @param startCreateTime  起始创建时间
   * @param endCreateTime  最终创建时间
   * @return 订单列表信息
   * @throws WxErrorException .
   */
  WxMaShopGetOrderListResponse getOrderList(Integer page, Integer pageSize, Boolean desc, Date startCreateTime, Date endCreateTime)
    throws WxErrorException;

  /**
   * <pre>
   *
   * 生成支付参数
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：<a href="https://api.weixin.qq.com/shop/order/getpaymentparams">请求地址</a>
   *
   * 文档地址：<a href="https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/business-capabilities/ministore/minishopopencomponent2/API/order/getpaymentparams.html">文档地址</a>
   * </pre>
   *
   * @param orderId 微信侧订单id
   * @param outOrderId 商家自定义订单ID
   * @param openid  用户openid
   * @return 支付参数
   * @throws WxErrorException .
   */
  WxMaShopGetPaymentParamsResponse getPaymentParams(String orderId, String outOrderId, String openid) throws WxErrorException;
}
