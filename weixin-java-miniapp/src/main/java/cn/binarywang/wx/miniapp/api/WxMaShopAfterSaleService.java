package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAfterSaleAddRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAfterSaleGetRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAfterSaleUpdateRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAfterSaleGetResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序交易组件-售后服务
 *
 * @author boris
 * @author liming1019
 */
public interface WxMaShopAfterSaleService {
  /**
   * 创建售后
   *
   * @param request
   * @return WxMaShopBaseResponse
   * @throws WxErrorException
   */
  WxMaShopBaseResponse add(WxMaShopAfterSaleAddRequest request) throws WxErrorException;

  /**
   * 获取订单下售后单
   *
   * @param request
   * @return WxMaShopAfterSaleGetResponse
   * @throws WxErrorException
   */
  WxMaShopAfterSaleGetResponse get(WxMaShopAfterSaleGetRequest request) throws WxErrorException;

  /**
   * 更新售后
   *
   * @param request
   * @return WxMaShopBaseResponse
   * @throws WxErrorException
   */
  WxMaShopBaseResponse update(WxMaShopAfterSaleUpdateRequest request) throws WxErrorException;

}
