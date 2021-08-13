package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAuditBrandRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAuditCategoryRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAuditBrandResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAuditCategoryResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAuditResultResponse;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序交易组件-接入商品前必需接口(审核相关接口)
 *
 * @author liming1019
 * @date 2021/8/12
 */
public interface WxMaShopAuditService {
  /**
   * 上传品牌信息(品牌审核)
   *
   * @param request
   * @return WxMaShopAuditBrandResponse
   * @throws WxErrorException
   */
  WxMaShopAuditBrandResponse auditBrand(WxMaShopAuditBrandRequest request) throws WxErrorException;

  /**
   * 上传类目资质(类目审核)
   *
   * @param request
   * @return
   * @throws WxErrorException
   */
  WxMaShopAuditCategoryResponse auditCategory(WxMaShopAuditCategoryRequest request) throws WxErrorException;

  /**
   * 获取审核结果
   *
   * @param auditId
   * @return WxMaShopAuditResultResponse
   * @throws WxErrorException
   */
  WxMaShopAuditResultResponse getAuditResult(String auditId) throws WxErrorException;

  /**
   * 获取小程序提交过的入驻资质信息
   *
   * @param reqType
   * @return JsonObject
   * @throws WxErrorException
   */
  JsonObject getMiniappCertificate(int reqType) throws WxErrorException;
}
