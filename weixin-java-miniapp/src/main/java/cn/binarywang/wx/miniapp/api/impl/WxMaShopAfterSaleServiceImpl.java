package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShopAfterSaleService;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAfterSaleAddRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAfterSaleGetRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAfterSaleUpdateRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAfterSaleGetResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Aftersale.*;
import static cn.binarywang.wx.miniapp.constant.WxMaConstants.ERRCODE;

/**
 * @author boris
 * @author liming1019
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaShopAfterSaleServiceImpl implements WxMaShopAfterSaleService {
  private final WxMaService wxMaService;


  /**
   * 创建售后
   *
   * @param request
   * @return WxMaShopBaseResponse
   * @throws WxErrorException
   */
  @Override
  public WxMaShopBaseResponse add(WxMaShopAfterSaleAddRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(AFTERSALE_ADD, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERRCODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  /**
   * 获取订单下售后单
   *
   * @param request
   * @return WxMaShopAfterSaleGetResponse
   * @throws WxErrorException
   */
  @Override
  public WxMaShopAfterSaleGetResponse get(WxMaShopAfterSaleGetRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(AFTERSALE_GET, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERRCODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopAfterSaleGetResponse.class);
  }

  /**
   * 更新售后
   *
   * @param request
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxMaShopBaseResponse update(WxMaShopAfterSaleUpdateRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(AFTERSALE_UPDATE, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERRCODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }
}
