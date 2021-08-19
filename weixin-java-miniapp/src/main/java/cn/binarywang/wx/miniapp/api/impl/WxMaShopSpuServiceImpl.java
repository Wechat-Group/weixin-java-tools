package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShopSpuService;
import cn.binarywang.wx.miniapp.bean.shop.WxMaShopSpuInfo;
import cn.binarywang.wx.miniapp.bean.shop.WxMaShopSpuWithoutAuditInfo;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopSpuPageRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAddSpuResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopGetSpuListResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopGetSpuResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Spu.*;

/**
 * @author boris
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaShopSpuServiceImpl implements WxMaShopSpuService {

  private static final String ERR_CODE = "errcode";
  private final WxMaService wxMaService;

  @Override
  public WxMaShopAddSpuResponse addSpu(WxMaShopSpuInfo spuInfo) throws WxErrorException {
    String responseContent = this.wxMaService.post(SPU_ADD_URL, spuInfo);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopAddSpuResponse.class);
  }

  @Override
  public WxMaShopBaseResponse deleteSpu(Integer productId, String outProductId)
    throws WxErrorException {
    String responseContent = this.wxMaService
      .post(SPU_DEL_URL, GsonHelper.buildJsonObject("product_id", productId,
        "out_product_id", outProductId));
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMaShopGetSpuResponse getSpu(Integer productId, String outProductId, Integer needEditSpu)
    throws WxErrorException {
    String responseContent = this.wxMaService
      .post(SPU_GET_URL, GsonHelper.buildJsonObject("product_id", productId,
        "out_product_id", outProductId, "need_edit_spu", needEditSpu));
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopGetSpuResponse.class);
  }

  @Override
  public WxMaShopGetSpuListResponse getSpuList(WxMaShopSpuPageRequest request)
    throws WxErrorException {
    String responseContent = this.wxMaService.post(SPU_GET_LIST_URL, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopGetSpuListResponse.class);
  }

  @Override
  public WxMaShopAddSpuResponse updateSpu(WxMaShopSpuInfo spuInfo) throws WxErrorException {
    String responseContent = this.wxMaService.post(SPU_UPDATE_URL, spuInfo);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopAddSpuResponse.class);
  }

  @Override
  public WxMaShopAddSpuResponse updateSpuWithoutAudit(WxMaShopSpuWithoutAuditInfo spuInfo)
    throws WxErrorException {
    String responseContent = this.wxMaService.post(SPU_UPDATE_WITHOUT_URL, spuInfo);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopAddSpuResponse.class);
  }

  @Override
  public WxMaShopBaseResponse listingSpu(Integer productId, String outProductId)
    throws WxErrorException {
    String responseContent = this.wxMaService
      .post(SPU_LISTING_URL, GsonHelper.buildJsonObject("product_id", productId,
        "out_product_id", outProductId));
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMaShopBaseResponse delistingSpu(Integer productId, String outProductId)
    throws WxErrorException {
    String responseContent = this.wxMaService
      .post(SPU_DELISTING_URL, GsonHelper.buildJsonObject("product_id", productId,
        "out_product_id", outProductId));
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMaShopBaseResponse deleteAudit(Integer productId, String outProductId)
    throws WxErrorException {
    String responseContent = this.wxMaService
      .post(DEL_AUDIT_URL, GsonHelper.buildJsonObject("product_id", productId,
        "out_product_id", outProductId));
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }
}
