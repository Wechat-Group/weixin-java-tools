package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAccountUpdateInfoRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAccountGetBrandListResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAccountGetCategoryListResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAccountGetInfoResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序交易组件-商家入驻接口
 *
 * @author liming1019
 */
public interface WxMaShopAccountService {
  /**
   * 获取商家类目列表
   *
   * @return WxMaShopAccountGetCategoryListResponse
   * @throws WxErrorException
   */
  WxMaShopAccountGetCategoryListResponse getCategoryList() throws WxErrorException;

  /**
   * 获取商家品牌列表
   *
   * @return WxMaShopAccountGetBrandListResponse
   * @throws WxErrorException
   */
  WxMaShopAccountGetBrandListResponse getBrandList() throws WxErrorException;

  /**
   * 更新商家信息
   *
   * @param request
   * @return WxMaShopBaseResponse
   * @throws WxErrorException
   */
  WxMaShopBaseResponse updateInfo(WxMaShopAccountUpdateInfoRequest request) throws WxErrorException;

  /**
   * 获取商家信息
   *
   * @return WxMaShopAccountGetInfoResponse
   * @throws WxErrorException
   */
  WxMaShopAccountGetInfoResponse getInfo() throws WxErrorException;
}
