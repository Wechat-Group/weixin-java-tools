package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.promoter.request.*;
import cn.binarywang.wx.miniapp.bean.promoter.response.*;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序推广员
 *
 * @author zhuangzibin
 */
public interface WxMaPromotionService {

  /**
   * 管理角色接口-新增角色
   *
   * @param request 请求参数
   * @return WxMaPromotionAddRoleResponse
   */
  WxMaPromotionAddRoleResponse addRole(WxMaPromotionAddRoleRequest request) throws WxErrorException;

  /**
   * 管理角色接口-查询角色
   *
   * @param request 请求参数
   * @return WxMaPromotionGetRoleResponse
   */
  WxMaPromotionGetRoleResponse getRole(WxMaPromotionGetRoleRequest request) throws WxErrorException;

  /**
   * 管理角色接口-修改角色
   *
   * @param request 请求参数
   * @return WxMaPromotionUpdateRoleResponse
   */
  WxMaPromotionUpdateRoleResponse updateRole(WxMaPromoterUpdateRoleRequest request) throws WxErrorException;

  /**
   * 管理推广员接口-声明推广员身份
   *
   * @param request 请求参数
   * @return WxMaPromotionAddPromoterResponse
   */
  WxMaPromotionAddPromoterResponse addPromoter(WxMaPromotionAddPromoterRequest request) throws WxErrorException;

  /**
   * 管理推广员接口-查询推广员身份
   *
   * @param request 请求参数
   * @return WxMaPromotionGetPromoterResponse
   */
  WxMaPromotionGetPromoterResponse getPromoter(WxMaPromotionGetPromoterRequest request) throws WxErrorException;

  /**
   * 管理推广员接口-修改推广员身份
   *
   * @param request 请求参数
   * @return WxMaPromotionUpdatePromoterResponse
   */
  WxMaPromotionUpdatePromoterResponse updatePromoter(WxMaPromotionUpdatePromoterRequest request) throws WxErrorException;

  /**
   * 邀请推广员-获取推广员邀请素材
   *
   * @param request 请求参数
   * @return WxMaPromotionGetInvitationMaterialResponse
   */
  WxMaPromotionGetInvitationMaterialResponse getInvitationMaterial(WxMaPromotionGetInvitationMaterialRequest request) throws WxErrorException;

  /**
   * 推广员消息管理接口-群发消息
   *
   * @param request 请求参数
   * @return WxMaPromotionSendMsgResponse
   */
  WxMaPromotionSendMsgResponse sendMsg(WxMaPromotionSendMsgRequest request) throws WxErrorException;

  /**
   * 推广员消息管理接口-单发消息
   *
   * @param request 请求参数
   * @return WxMaPromotionSingleSendMsgResponse
   */
  WxMaPromotionSingleSendMsgResponse singleSendMsg(WxMaPromotionSingleSendMsgRequest request) throws WxErrorException;

  /**
   * 推广员消息管理接口-查询送达结果
   *
   * @param request 请求参数
   * @return WxMaPromotionGetMsgResponse
   */
  WxMaPromotionGetMsgResponse getMsg(WxMaPromotionGetMsgRequest request) throws WxErrorException;

  /**
   * 推广员消息管理接口-分析点击效果
   *
   * @param request 请求参数
   * @return WxMaPromotionGetMsgClickDataResponse
   */
  WxMaPromotionGetMsgClickDataResponse getMsgClickData(WxMaPromotionGetMsgClickDataRequest request) throws WxErrorException;

  /**
   * 推广数据接口-生成推广素材
   *
   * @param request 请求参数
   * @return WxMaPromotionGetShareMaterialResponse
   */
  WxMaPromotionGetShareMaterialResponse getShareMaterial(WxMaPromotionGetShareMaterialRequest request) throws WxErrorException;

  /**
   * 推广数据接口-分析触达效果
   *
   * @param request 请求参数
   * @return WxMaPromotionGetRelationResponse
   */
  WxMaPromotionGetRelationResponse getRelation(WxMaPromotionGetRelationRequest request) throws WxErrorException;

  /**
   * 推广数据接口-查询推广订单
   *
   * @param request 请求参数
   * @return WxMaPromotionGetOrderResponse
   */
  WxMaPromotionGetOrderResponse getOrder(WxMaPromotionGetOrderRequest request) throws WxErrorException;
}
