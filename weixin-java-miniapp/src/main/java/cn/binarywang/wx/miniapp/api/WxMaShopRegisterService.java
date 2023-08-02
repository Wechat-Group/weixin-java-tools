package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopRegisterApplySceneRequest;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopRegisterFinishAccessInfoRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopRegisterCheckResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序交易组件-申请接入服务
 *
 * @author liming1019
 */
public interface WxMaShopRegisterService {
  /**
   * 接入申请
   *
   * @return WxMaShopBaseResponse
   * @throws WxErrorException
   */
  WxMaShopBaseResponse registerApply() throws WxErrorException;

  /**
   * 获取接入状态
   *
   * @return WxMaShopRegisterCheckResponse
   * @throws WxErrorException
   */
  WxMaShopRegisterCheckResponse registerCheck() throws WxErrorException;

  /**
   * 完成接入任务
   *
   * @return WxMaShopBaseResponse
   * @throws WxErrorException
   */
  WxMaShopBaseResponse registerFinishAccessInfo(WxMaShopRegisterFinishAccessInfoRequest request) throws WxErrorException;

  /**
   * 场景接入申请
   *
   * @return WxMaShopBaseResponse
   * @throws WxErrorException
   */
  WxMaShopBaseResponse registerApplyScene(WxMaShopRegisterApplySceneRequest request) throws WxErrorException;
}
