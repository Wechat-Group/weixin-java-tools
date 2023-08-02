package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.shop.request.*;
import cn.binarywang.wx.miniapp.bean.shop.response.*;
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
  WxMaShopAfterSaleAddResponse add(WxMaShopAfterSaleAddRequest request) throws WxErrorException;

  /**
   * 获取订单下售后单
   *
   * @param request
   * @return WxMaShopAfterSaleGetResponse
   * @throws WxErrorException
   */
  WxMaShopAfterSaleGetResponse get(WxMaShopAfterSaleGetRequest request) throws WxErrorException;

  /**
   * 获取售后单详情(EC版)
   *
   * @param request
   * @return WxMaShopEcAfterSaleGetResponse
   * @throws WxErrorException
   */
  WxMaShopEcAfterSaleGetResponse get(WxMaShopEcAfterSaleGetRequest request) throws WxErrorException;

  /**
   * 更新售后
   *
   * @param request
   * @return WxMaShopBaseResponse
   * @throws WxErrorException
   */
  WxMaShopBaseResponse update(WxMaShopAfterSaleUpdateRequest request) throws WxErrorException;

  /**
   * 更新售后(EC版)
   *
   * @param request
   * @return WxMaShopBaseResponse
   * @throws WxErrorException
   */
  WxMaShopBaseResponse update(WxMaShopEcAfterSaleUpdateRequest request) throws WxErrorException;


  /**
   * 用户取消售后申请
   * @param outAfterSaleId 商家自定义订单ID
   * @param afterSaleId 与out_aftersale_id二选一
   * @param openId 用户openid
   * @return
   * @throws WxErrorException
   */
  WxMaShopBaseResponse cancel(String outAfterSaleId, Long afterSaleId, String openId)
    throws WxErrorException;

  /**
   * 用户上传退货物流
   * @param request
   * @return
   * @throws WxErrorException
   */
  WxMaShopBaseResponse uploadReturnInfo(WxMaShopAfterSaleUploadReturnInfoRequest request)
    throws WxErrorException;

  /**
   * 商家同意退款
   * @param outAfterSaleId
   * @param afterSaleId
   * @return
   * @throws WxErrorException
   */
  WxMaShopBaseResponse acceptRefund(String outAfterSaleId, Long afterSaleId)
    throws WxErrorException;

  /**
   * 商家同意退货
   * @param request
   * @return
   * @throws WxErrorException
   */
  WxMaShopBaseResponse acceptReturn(WxMaShopAcceptReturnRequest request)
    throws WxErrorException;

  /**
   * 商家拒绝售后
   * @param outAfterSaleId
   * @param afterSaleId
   * @return
   * @throws WxErrorException
   */
  WxMaShopBaseResponse reject(String outAfterSaleId, Long afterSaleId)
    throws WxErrorException;

  /**
   * 商家上传退款凭证
   * @param request
   * @return
   * @throws WxErrorException
   */
  WxMaShopBaseResponse uploadCertificates(WxMaShopUploadCerficatesRequest request)
    throws WxErrorException;

  /**
   * 商家更新订单售后期
   * @param outOrderId
   * @param orderId
   * @param openid
   * @param afterSaleDeadline
   * @return
   * @throws WxErrorException
   */
  WxMaShopBaseResponse updateDeadline(String outOrderId, Long orderId, String openid,
    Long afterSaleDeadline) throws WxErrorException;

  /**
   * 获取售后单详情
   * @param request
   * @return
   * @throws WxErrorException
   */
  WxMaShopAfterSaleListResponse list(WxMaShopAfterSaleListRequest request) throws WxErrorException;
}
