package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShopPayService;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopPayCreateOrderRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopPayCreateOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Pay.CREATE_ORDER;

/**
 * 小程序支付管理订单相关接口
 *
 * @author liming1019
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaShopPayServiceImpl implements WxMaShopPayService {
  private final WxMaService wxMaService;

  @Override
  public WxMaShopPayCreateOrderResponse createOrder(WxMaShopPayCreateOrderRequest request) throws WxErrorException {
    String response = this.wxMaService.post(CREATE_ORDER, request);
    return WxGsonBuilder.create().fromJson(response, WxMaShopPayCreateOrderResponse.class);
  }
}
