package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaOrderShippingService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaOrderShippingIsTradeManagedRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.shipping.WxMaOrderCombinedShippingInfoUploadRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.shipping.WxMaOrderShippingInfoUploadRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaOrderShippingBaseResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaOrderShippingIsTradeManagedResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.OrderShipping.*;


/**
 * @author xzh
 * created on  2023/5/17 17:44
 */
@Slf4j
@RequiredArgsConstructor
public class WxMaOrderShippingServiceImpl implements WxMaOrderShippingService {

  private final WxMaService wxMaService;

  /**
   * 查询小程序是否已开通发货信息管理服务
   *
   * @param appId 待查询小程序的 appid，非服务商调用时仅能查询本账号
   * @return WxMaOrderShippingBaseResponse
   * @throws WxErrorException
   */
  @Override
  public WxMaOrderShippingIsTradeManagedResponse isTradeManaged(String appId) throws WxErrorException {
    WxMaOrderShippingIsTradeManagedRequest request = WxMaOrderShippingIsTradeManagedRequest.builder().appId(appId).build();
    return request(IS_TRADE_MANAGED, request, WxMaOrderShippingIsTradeManagedResponse.class);
  }

  /**
   * 发货信息录入接口
   *
   * @param request 请求
   * @return WxMaOrderShippingBaseResponse
   * @throws WxErrorException
   */
  @Override
  public WxMaOrderShippingBaseResponse upload(WxMaOrderShippingInfoUploadRequest request) throws WxErrorException {
    return request(UPLOAD_SHIPPING_INFO, request, WxMaOrderShippingBaseResponse.class);
  }

  /**
   * 发货信息合单录入接口
   *
   * @param request 请求
   * @return WxMaOrderShippingBaseResponse
   * @throws WxErrorException
   */
  @Override
  public WxMaOrderShippingBaseResponse upload(WxMaOrderCombinedShippingInfoUploadRequest request) throws WxErrorException {
    return request(UPLOAD_COMBINED_SHIPPING_INFO, request, WxMaOrderShippingBaseResponse.class);
  }

  private <T> T request(String url, Object request, Class<T> resultT) throws WxErrorException {
    String responseContent = this.wxMaService.post(url, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, resultT);
  }
}
