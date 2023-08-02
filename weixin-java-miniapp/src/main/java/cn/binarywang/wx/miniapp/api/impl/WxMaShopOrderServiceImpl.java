package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShopOrderService;
import cn.binarywang.wx.miniapp.bean.shop.WxMaShopOrderInfo;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopOrderPayRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.*;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.Format;
import java.util.Date;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Order.*;
import static me.chanjar.weixin.common.api.WxConsts.ERR_CODE;

/**
 * @author boris
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaShopOrderServiceImpl implements WxMaShopOrderService {

  private final Format dateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

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
    return this.post(ORDER_ADD,orderInfo, WxMaShopAddOrderResponse.class);
  }

  @Override
  public WxMaShopBaseResponse orderPay(WxMaShopOrderPayRequest request) throws WxErrorException {
    return this.post(ORDER_PAY,request, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMaShopGetOrderResponse getOrder(Long orderId, String outOrderId, String openid) throws WxErrorException {
    return this.post(ORDER_GET, GsonHelper.buildJsonObject("order_id", orderId, "out_order_id", outOrderId,
      "openid", openid), WxMaShopGetOrderResponse.class);
  }

  @Override
  public WxMaShopGetOrderListResponse getOrderList(Integer page, Integer pageSize, Boolean desc, Date startCreateTime, Date endCreateTime) throws WxErrorException {
    JsonObject object = new JsonObject();
    object.addProperty("page", page == null ? 1 : page);
    object.addProperty("page_size", pageSize == null ? 10 : pageSize);
    object.addProperty("desc", desc ? 1 : 2);
    if (startCreateTime != null) {
      object.addProperty("start_create_time", this.dateFormat.format(startCreateTime));
    }
    if (endCreateTime != null) {
      object.addProperty("end_create_time", this.dateFormat.format(endCreateTime));
    }
    return this.post(ORDER_GET_LIST, object, WxMaShopGetOrderListResponse.class);
  }

  @Override
  public WxMaShopGetPaymentParamsResponse getPaymentParams(String orderId, String outOrderId, String openid) throws WxErrorException {
    return this.post(ORDER_GET_PAYMENT_PARAMS,
      GsonHelper.buildJsonObject("order_id", orderId, "out_order_id", outOrderId,
        "openid", openid), WxMaShopGetPaymentParamsResponse.class);
  }


  private <T> T post(String url, Object params, Class<T> classOfT) throws WxErrorException {
    String responseContent = this.wxMaService.post(url, params);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, classOfT);
  }
}
