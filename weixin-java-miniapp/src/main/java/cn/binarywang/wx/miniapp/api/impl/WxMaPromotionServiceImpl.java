package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaPromotionService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.promoter.request.*;
import cn.binarywang.wx.miniapp.bean.promoter.response.*;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Promotion.*;
import static me.chanjar.weixin.common.api.WxConsts.ERR_CODE;

/**
 * @author zhuangzibin
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaPromotionServiceImpl implements WxMaPromotionService {

  private final WxMaService wxMaService;

  private final static Integer ERR_CODE_OF_EMPTY_LIST = 103006;

  @Override
  public WxMaPromotionAddRoleResponse addRole(WxMaPromotionAddRoleRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(PROMOTION_ADD_ROLE, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);

    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaPromotionAddRoleResponse.class);
  }

  @Override
  public WxMaPromotionGetRoleResponse getRole(WxMaPromotionGetRoleRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(PROMOTION_GET_ROLE, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);

    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaPromotionGetRoleResponse.class);
  }

  @Override
  public WxMaPromotionUpdateRoleResponse updateRole(WxMaPromoterUpdateRoleRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(PROMOTION_UPDATE_ROLE, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);

    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaPromotionUpdateRoleResponse.class);
  }

  @Override
  public WxMaPromotionAddPromoterResponse addPromoter(WxMaPromotionAddPromoterRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(PROMOTION_ADD_PROMOTER, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaPromotionAddPromoterResponse.class);
  }

  @Override
  public WxMaPromotionGetPromoterResponse getPromoter(WxMaPromotionGetPromoterRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(PROMOTION_GET_PROMOTER, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);

    if (jsonObject.get(ERR_CODE).getAsInt() != 0 || jsonObject.get(ERR_CODE).getAsInt() != ERR_CODE_OF_EMPTY_LIST) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaPromotionGetPromoterResponse.class);
  }

  @Override
  public WxMaPromotionUpdatePromoterResponse updatePromoter(WxMaPromotionUpdatePromoterRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(PROMOTION_UPDATE_PROMOTER, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);

    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaPromotionUpdatePromoterResponse.class);
  }

  @Override
  public WxMaPromotionGetInvitationMaterialResponse getInvitationMaterial(WxMaPromotionGetInvitationMaterialRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(PROMOTION_GET_INVITATION_MATERIAL, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);

    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaPromotionGetInvitationMaterialResponse.class);
  }

  @Override
  public WxMaPromotionSendMsgResponse sendMsg(WxMaPromotionSendMsgRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(PROMOTION_SEND_MSG, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);

    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaPromotionSendMsgResponse.class);
  }

  @Override
  public WxMaPromotionSingleSendMsgResponse singleSendMsg(WxMaPromotionSingleSendMsgRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(PROMOTION_SINGLE_SEND_MSG, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);

    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaPromotionSingleSendMsgResponse.class);
  }

  @Override
  public WxMaPromotionGetMsgResponse getMsg(WxMaPromotionGetMsgRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(PROMOTION_GET_MSG, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);

    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaPromotionGetMsgResponse.class);
  }

  @Override
  public WxMaPromotionGetMsgClickDataResponse getMsgClickData(WxMaPromotionGetMsgClickDataRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(PROMOTION_GET_MSG_CLICK_DATA, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);

    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaPromotionGetMsgClickDataResponse.class);
  }

  @Override
  public WxMaPromotionGetShareMaterialResponse getShareMaterial(WxMaPromotionGetShareMaterialRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(PROMOTION_GET_SHARE_MATERIAL, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);

    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaPromotionGetShareMaterialResponse.class);
  }

  @Override
  public WxMaPromotionGetRelationResponse getRelation(WxMaPromotionGetRelationRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(PROMOTION_GET_RELATION, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);

    if (jsonObject.get(ERR_CODE).getAsInt() != 0 || jsonObject.get(ERR_CODE).getAsInt() != ERR_CODE_OF_EMPTY_LIST) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaPromotionGetRelationResponse.class);
  }

  @Override
  public WxMaPromotionGetOrderResponse getOrder(WxMaPromotionGetOrderRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(PROMOTION_GET_ORDER, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);

    if (jsonObject.get(ERR_CODE).getAsInt() != 0 || jsonObject.get(ERR_CODE).getAsInt() != ERR_CODE_OF_EMPTY_LIST) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaPromotionGetOrderResponse.class);
  }
}
