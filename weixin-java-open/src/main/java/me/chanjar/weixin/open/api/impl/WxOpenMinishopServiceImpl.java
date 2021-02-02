package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.api.WxOpenMinishopService;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.bean.minishop.*;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

import java.io.File;

public class WxOpenMinishopServiceImpl extends WxMaServiceImpl implements WxOpenMinishopService {
  private final WxOpenComponentService wxOpenComponentService;
  private final WxMaConfig wxMaConfig;
  private final String appId;

  public WxOpenMinishopServiceImpl(WxOpenComponentService wxOpenComponentService, String appId, WxMaConfig wxMaConfig) {
    this.wxOpenComponentService = wxOpenComponentService;
    this.appId = appId;
    this.wxMaConfig = wxMaConfig;
    initHttp();
  }

  @Override
  public WxOpenResult submitMerchantInfo(String subjectType, MinishopBusiLicense busiLicense, MinishopOrganizationCodeInfo organizationCodeInfo, MinishopIdcardInfo idcardInfo, MinishopSuperAdministratorInfo superAdministratorInfo, String merchantShoprtName) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("app_id", appId);
    jsonObject.addProperty("subject_type", subjectType);
    jsonObject.add("busi_license", busiLicense.toJsonObject());
    jsonObject.add("organization_code_info", organizationCodeInfo.toJsonObject());
    jsonObject.add("id_card_info", idcardInfo.toJsonObject());
    jsonObject.add("super_administrator_info", superAdministratorInfo.toJsonObject());
    String response = post(submitMerchantInfoUrl, jsonObject.toString());
    return null;
  }

  @Override
  public WxOpenResult submitBasicInfo(MinishopNameInfo nameInfo, MinishopReturnInfo returnInfo) {
    return null;
  }

  @Override
  public MinishopAuditStatus checkAuditStatus(String wxName) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    String response = post(checkAuditStatusUrl, jsonObject.toString());
    return GSON.fromJson(response, MinishopAuditStatus.class);
  }

  @Override
  public MinishopPicFile uploadImagePicFile(Integer height, Integer width, File file) {
    return null;
  }

  @Override
  public MinishopCategories getCategory(Integer fCatId) {
    return null;
  }

  @Override
  public MinishopBrandList getBrands() {
    return null;
  }

  @Override
  public MinishopShopCatList getShopCat() {
    return null;
  }
}
