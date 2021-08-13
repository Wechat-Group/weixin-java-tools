package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShopDeliveryService;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopDeliveryRecieveRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopDeliverySendRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopDeliveryGetCompanyListResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Delivery.*;
import static cn.binarywang.wx.miniapp.constant.WxMaConstants.ERRCODE;

/**
 * @author boris
 * @author liming1019
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaShopDeliveryServiceImpl implements WxMaShopDeliveryService {
  private final WxMaService wxMaService;

  /**
   * 获取快递公司列表
   *
   * @return WxMaShopDeliveryGetCompanyListResponse
   * @throws WxErrorException
   */
  @Override
  public WxMaShopDeliveryGetCompanyListResponse getCompanyList() throws WxErrorException {
    String responseContent = this.wxMaService.post(GET_COMPANY_LIST, new JsonObject());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERRCODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopDeliveryGetCompanyListResponse.class);
  }

  /**
   * 订单发货
   *
   * @param request
   * @return WxMaShopBaseResponse
   * @throws WxErrorException
   */
  @Override
  public WxMaShopBaseResponse send(WxMaShopDeliverySendRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(DELIVERY_SEND, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERRCODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  /**
   * 订单确认收货
   *
   * @param request
   * @return WxMaShopBaseResponse
   * @throws WxErrorException
   */
  @Override
  public WxMaShopBaseResponse receive(WxMaShopDeliveryRecieveRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(DELIVERY_RECEIVE, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERRCODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }
}
