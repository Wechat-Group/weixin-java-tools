package cn.binarywang.wx.miniapp.api.impl;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Sharer;
import static me.chanjar.weixin.common.api.WxConsts.ERR_CODE;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShopSharerService;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopSearchSharerResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopSharerBindResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopSharerDataSummaryResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopSharerListResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopSharerLiveOrderListResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopSharerLiveSummaryListResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopSharerUnbindResponse;
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
 * @author leiin
 * created on  2022/6/18 3:38 下午
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaShopSharerServiceImpl implements WxMaShopSharerService {
  private final WxMaService wxMaService;

  @Override
  public WxMaShopSharerBindResponse bindSharer(String[] openids) throws WxErrorException {
    JsonObject json = GsonHelper.buildJsonObject("openids", openids);
    String responseContent = this.wxMaService.post(Sharer.BIND, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopSharerBindResponse.class);
  }

  @Override
  public WxMaShopSharerDataSummaryResponse getSharerDataSummary(String openid) throws WxErrorException {
    JsonObject json = GsonHelper.buildJsonObject("openid", openid);
    String responseContent = this.wxMaService.post(Sharer.GET_SHARER_DATA_SUMMARY, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopSharerDataSummaryResponse.class);
  }

  @Override
  public WxMaShopSharerListResponse getSharerList(Integer page, Integer pageSize) throws WxErrorException {
    JsonObject json = GsonHelper.buildJsonObject("page", page, "page_size", pageSize);
    String responseContent = this.wxMaService.post(Sharer.GET_SHARER_LIST, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopSharerListResponse.class);
  }

  @Override
  public WxMaShopSharerLiveOrderListResponse getSharerLiveOrderList(String openid, String liveExportId,
    Integer page, Integer pageSize) throws WxErrorException {
    JsonObject json = GsonHelper.buildJsonObject("openid", openid, "live_export_id", liveExportId,
      "page", page, "page_size", pageSize);
    String responseContent = this.wxMaService.post(Sharer.GET_SHARER_LIVE_ORDER_LIST, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopSharerLiveOrderListResponse.class);
  }

  @Override
  public WxMaShopSharerLiveSummaryListResponse getSharerLiveSummaryList(String openid,
    Integer page, Integer pageSize) throws WxErrorException {
    JsonObject json = GsonHelper.buildJsonObject("openid", openid, "page", page, "page_size", pageSize);
    String responseContent = this.wxMaService.post(Sharer.GET_SHARER_LIVE_SUMMARY_LIST, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopSharerLiveSummaryListResponse.class);
  }

  @Override
  public WxMaShopSearchSharerResponse searchSharer(String openid) throws WxErrorException {
    JsonObject json = GsonHelper.buildJsonObject("openid", openid);
    String responseContent = this.wxMaService.post(Sharer.SEARCH_SHARER, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopSearchSharerResponse.class);
  }

  @Override
  public WxMaShopSharerUnbindResponse unbindSharer(String[] openids) throws WxErrorException {
    JsonObject json = GsonHelper.buildJsonObject("openids", openids);
    String responseContent = this.wxMaService.post(Sharer.UNBIND, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopSharerUnbindResponse.class);
  }
}
