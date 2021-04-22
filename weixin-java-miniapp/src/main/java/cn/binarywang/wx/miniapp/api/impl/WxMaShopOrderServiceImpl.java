package cn.binarywang.wx.miniapp.api.impl;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Order.ORDER_ADD;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Order.ORDER_CHECK_SCENE;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Order.ORDER_GET;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Order.ORDER_PAY;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShopOrderService;
import cn.binarywang.wx.miniapp.bean.shop.WxMaShopOrderInfo;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopOrderPayRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAddOrderResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopGetOrderResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;

/**
 * @author boris
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaShopOrderServiceImpl implements WxMaShopOrderService {
  private static final String ERR_CODE = "errcode";
  private static final String MATCH_KEY = "is_matched";
  private final WxMaService wxMaService;

  @Override
  public Boolean checkScene(Integer scene) throws WxErrorException {
    String responseContent = this.wxMaService
      .post(ORDER_CHECK_SCENE, GsonHelper.buildJsonObject("scene", scene));
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return jsonObject.get(MATCH_KEY).getAsBoolean();
  }

  @Override
  public WxMaShopAddOrderResponse addOrder(WxMaShopOrderInfo orderInfo) throws WxErrorException {
    String responseContent = this.wxMaService.post(ORDER_ADD, orderInfo);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopAddOrderResponse.class);
  }

  @Override
  public WxMaShopBaseResponse orderPay(WxMaShopOrderPayRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(ORDER_PAY, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMaShopGetOrderResponse getOrder(Integer orderId, String outOrderId, String openid)
    throws WxErrorException {
    String responseContent = this.wxMaService.post(ORDER_GET,
      GsonHelper.buildJsonObject("order_id", orderId, "out_order_id", outOrderId,
        "openid", openid));
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopGetOrderResponse.class);
  }
}
