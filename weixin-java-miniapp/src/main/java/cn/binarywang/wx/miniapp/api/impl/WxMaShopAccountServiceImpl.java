package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShopAccountService;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAccountUpdateInfoRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAccountGetBrandListResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAccountGetCategoryListResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAccountGetInfoResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Account.*;

/**
 * @author liming1019
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaShopAccountServiceImpl implements WxMaShopAccountService {
  private static final String ERR_CODE = "errcode";
  private final WxMaService wxMaService;

  @Override
  public WxMaShopAccountGetCategoryListResponse getCategoryList() throws WxErrorException {
    String responseContent = this.wxMaService.post(GET_CATEGORY_LIST, new JsonObject());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopAccountGetCategoryListResponse.class);
  }

  @Override
  public WxMaShopAccountGetBrandListResponse getBrandList() throws WxErrorException {
    String responseContent = this.wxMaService.post(GET_BRAND_LIST, new JsonObject());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopAccountGetBrandListResponse.class);
  }

  @Override
  public WxMaShopBaseResponse updateInfo(WxMaShopAccountUpdateInfoRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(UPDATE_INFO, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMaShopAccountGetInfoResponse getInfo() throws WxErrorException {
    String responseContent = this.wxMaService.post(GET_INFO, new JsonObject());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopAccountGetInfoResponse.class);
  }
}
