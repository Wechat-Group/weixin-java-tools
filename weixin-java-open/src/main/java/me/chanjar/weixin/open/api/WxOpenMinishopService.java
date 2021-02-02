package me.chanjar.weixin.open.api;

import me.chanjar.weixin.open.bean.minishop.*;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

import java.io.File;

/**
 * 微信小商店开店接口
 * add by kelven 2021-01-29
 */
public interface WxOpenMinishopService {
   String submitMerchantInfoUrl = "https://api.weixin.qq.com/product/register/submit_merchantinfo";

   String submitBasicInfoUrl = "https://api.weixin.qq.com/product/register/submit_basicinfo";

  String checkAuditStatusUrl = "https://api.weixin.qq.com/product/register/check_audit_status";

  String uploadImageUrl = "https://api.weixin.qq.com/product/img/upload";

  String getCategoryUrl = "https://api.weixin.qq.com/product/category/get";

  String getBrandsUrl = "https://api.weixin.qq.com/product/brand/get";

  String getDeliveryUrl = "https://api.weixin.qq.com/product/delivery/get_freight_template";

  /**获取店铺的商品分类*/
  String getShopCatUrl = "https://api.weixin.qq.com/product/store/get_shopcat";


  /**
   *
   * @param appId
   * @param subjectType
   * @param busiLicense
   * @param organizationCodeInfo
   * @param idcardInfo
   * @param superAdministratorInfo
   * @param merchantShoprtName
   * @return
   */
  WxOpenResult submitMerchantInfo(String appId, String subjectType, MinishopBusiLicense busiLicense, MinishopOrganizationCodeInfo organizationCodeInfo, MinishopIdcardInfo idcardInfo, MinishopSuperAdministratorInfo superAdministratorInfo, String merchantShoprtName);


  WxOpenResult submitBasicInfo(String appId, MinishopNameInfo nameInfo, MinishopReturnInfo returnInfo);

  MinishopAuditStatus checkAuditStatus(String wxName);

  MinishopPicFile uploadImagePicFile(Integer height, Integer width, File file);

  MinishopCategories getCategory(Integer fCatId);

  MinishopBrandList getBrands();


  MinishopShopCatList getShopCat();
}
