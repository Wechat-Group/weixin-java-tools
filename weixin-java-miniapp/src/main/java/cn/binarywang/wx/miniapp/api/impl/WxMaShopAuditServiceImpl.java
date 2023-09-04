package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShopAuditService;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAuditBrandRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAuditCategoryRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAuditBrandResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAuditCategoryResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAuditResultResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Audit.*;
import static me.chanjar.weixin.common.api.WxConsts.ERR_CODE;

/**
 * 小程序交易组件-接入商品前必需接口(审核相关接口)
 *
 * @author liming1019
 * created on  2021/8/12
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaShopAuditServiceImpl implements WxMaShopAuditService {
  private final WxMaService wxMaService;

  /**
   * 上传品牌信息(品牌审核)
   *
   * @param request
   * @return WxMaShopAuditBrandResponse
   * @throws WxErrorException
   */
  @Override
  public WxMaShopAuditBrandResponse auditBrand(WxMaShopAuditBrandRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(AUDIT_BRAND, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopAuditBrandResponse.class);
  }

  /**
   * 上传类目资质(类目审核)
   *
   * @param request
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxMaShopAuditCategoryResponse auditCategory(WxMaShopAuditCategoryRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(AUDIT_CATEGORY, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopAuditCategoryResponse.class);
  }

  /**
   * 获取审核结果
   *
   * @param auditId
   * @return WxMaShopAuditResultResponse
   * @throws WxErrorException
   */
  @Override
  public WxMaShopAuditResultResponse getAuditResult(String auditId) throws WxErrorException {
    String responseContent = this.wxMaService.post(AUDIT_RESULT, GsonHelper.buildJsonObject("audit_id", auditId));
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopAuditResultResponse.class);
  }

  /**
   * 获取小程序提交过的入驻资质信息
   *
   * @param reqType
   * @return JsonObject
   * @throws WxErrorException
   */
  @Override
  public JsonObject getMiniappCertificate(int reqType) throws WxErrorException {
    String responseContent = this.wxMaService.post(GET_MINIAPP_CERTIFICATE, GsonHelper.buildJsonObject("req_type", reqType));
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, JsonObject.class);
  }
}
