package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShopRegisterService;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopRegisterApplySceneRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopRegisterFinishAccessInfoRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopRegisterCheckResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Register.*;

/**
 * @author liming1019
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaShopRegisterServiceImpl implements WxMaShopRegisterService {
  private static final String ERR_CODE = "errcode";
  private final WxMaService wxMaService;

  @Override
  public WxMaShopBaseResponse registerApply() throws WxErrorException {
    String responseContent = this.wxMaService.post(REGISTER_APPLY, new JsonObject());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMaShopRegisterCheckResponse registerCheck() throws WxErrorException {
    String responseContent = this.wxMaService.post(REGISTER_CHECK, new JsonObject());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopRegisterCheckResponse.class);
  }

  @Override
  public WxMaShopBaseResponse registerFinishAccessInfo(WxMaShopRegisterFinishAccessInfoRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(REGISTER_FINISH_ACCESS_INFO, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMaShopBaseResponse registerApplyScene(WxMaShopRegisterApplySceneRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(REGISTER_APPLY_SCENE, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }
}
