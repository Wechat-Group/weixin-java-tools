package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.shop.request.WxMaOrderShippingIsTradeManagedRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaOrderShippingUploadRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaOrderShippingBaseResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaOrderShippingIsTradeManagedResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * @author xzh
 * created on  2023/5/17 16:49
 */
public interface WxMaOrderShippingService {
  /**
   * 查询小程序是否已开通发货信息管理服务
   *
   * @param appId 待查询小程序的 appid，非服务商调用时仅能查询本账号
   * @return WxMaOrderShippingBaseResponse
   * @throws WxErrorException
   */
  WxMaOrderShippingIsTradeManagedResponse isTradeManaged(String appId)
    throws WxErrorException;

  /**
   * 发货信息录入接口
   *
   * @param request 请求
   * @return WxMaOrderShippingBaseResponse
   * @throws WxErrorException
   */
  WxMaOrderShippingBaseResponse upload(WxMaOrderShippingUploadRequest request)
    throws WxErrorException;
}
