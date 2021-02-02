package me.chanjar.weixin.open.api.impl;

import me.chanjar.weixin.open.api.WxOpenMinishopService;
import me.chanjar.weixin.open.bean.minishop.*;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

import java.io.File;

public class WxOpenMinishopServiceImpl implements WxOpenMinishopService {
  @Override
  public WxOpenResult submitMerchantInfo(String appId, String subjectType, MinishopBusiLicense busiLicense, MinishopOrganizationCodeInfo organizationCodeInfo, MinishopIdcardInfo idcardInfo, MinishopSuperAdministratorInfo superAdministratorInfo, String merchantShoprtName) {
    return null;
  }

  @Override
  public WxOpenResult submitBasicInfo(String appId, MinishopNameInfo nameInfo, MinishopReturnInfo returnInfo) {
    return null;
  }

  @Override
  public MinishopAuditStatus checkAuditStatus(String wxName) {
    return null;
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
