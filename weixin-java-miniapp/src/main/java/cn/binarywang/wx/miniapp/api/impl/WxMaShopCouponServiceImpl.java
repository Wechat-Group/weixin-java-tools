package cn.binarywang.wx.miniapp.api.impl;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShopCouponService;
import cn.binarywang.wx.miniapp.bean.shop.WxMaShopCouponInfo;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopCouponListResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopCouponResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopUserCouponListResponse;
import cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Coupon;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;

import static me.chanjar.weixin.common.api.WxConsts.ERR_CODE;

/**
 * @author leiin
 * created on  2022/7/1 2:49 下午
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaShopCouponServiceImpl implements WxMaShopCouponService {
  private final WxMaService wxMaService;

  @Override
  public WxMaShopBaseResponse addCoupon(WxMaShopCouponInfo couponInfo) throws WxErrorException {
    JsonObject json = GsonHelper.buildJsonObject("coupon", couponInfo);
    String responseContent = this.wxMaService.post(Coupon.ADD_COUPON, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMaShopCouponResponse getCoupon(String outCouponId) throws WxErrorException {
    JsonObject json = GsonHelper.buildJsonObject("out_coupon_id", outCouponId);
    String responseContent = this.wxMaService.post(Coupon.GET_COUPON, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopCouponResponse.class);
  }

  @Override
  public WxMaShopCouponListResponse getCouponList(Integer pageSize, Integer offset) throws WxErrorException {
    JsonObject json = GsonHelper.buildJsonObject("page_size", pageSize, "offset", offset);
    String responseContent = this.wxMaService.post(Coupon.GET_COUPON_LIST, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopCouponListResponse.class);
  }

  @Override
  public WxMaShopBaseResponse updateCoupon(WxMaShopCouponInfo couponInfo) throws WxErrorException {
    JsonObject json = GsonHelper.buildJsonObject("coupon", couponInfo);
    String responseContent = this.wxMaService.post(Coupon.UPDATE_COUPON, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMaShopBaseResponse updateCouponStatus(String outCouponId, Integer status) throws WxErrorException {
    JsonObject json = GsonHelper.buildJsonObject("out_coupon_id", outCouponId, "status", status);
    String responseContent = this.wxMaService.post(Coupon.UPDATE_COUPON_STATUS, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMaShopBaseResponse updateCouponStock(String outCouponId, Integer isUsedNum, Integer receiveNum) throws WxErrorException {
    JsonObject stockInfo = GsonHelper.buildJsonObject("issued_num", isUsedNum, "receive_num", receiveNum);
    JsonObject stock = GsonHelper.buildJsonObject("out_coupon_id", outCouponId, "stock_info", stockInfo);
    JsonObject json = GsonHelper.buildJsonObject("coupon_stock", stock);

    String responseContent = this.wxMaService.post(Coupon.UPDATE_COUPON_STOCK, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMaShopBaseResponse addUserCoupon(String openid, String outUserCouponId,
                                            String outCouponId, Integer status, Long recvTime) throws WxErrorException {
    JsonObject userCoupon = GsonHelper.buildJsonObject("out_user_coupon_id", outUserCouponId,
      "out_coupon_id", outCouponId,
      "status", status);
    JsonObject json = GsonHelper.buildJsonObject("openid", openid, "user_coupon", userCoupon,
      "recv_time", recvTime);

    String responseContent = this.wxMaService.post(Coupon.ADD_USER_COUPON, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMaShopUserCouponListResponse getUserCouponList(Integer pageSize, Integer offset, String openid) throws WxErrorException {
    JsonObject json = GsonHelper.buildJsonObject("page_size", pageSize, "offset", offset,
      "openid", openid);
    String responseContent = this.wxMaService.post(Coupon.GET_USER_COUPON_LIST, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopUserCouponListResponse.class);
  }

  @Override
  public WxMaShopBaseResponse updateUserCoupon(String openid, String outUserCouponId,
                                               String outCouponId, Long useTime, Long recvTime) throws WxErrorException {
    JsonObject extInfo = GsonHelper.buildJsonObject("use_time", useTime);

    JsonObject userCoupon = GsonHelper.buildJsonObject("out_user_coupon_id", outUserCouponId,
      "out_coupon_id", outCouponId, "ext_info", extInfo);

    JsonObject json = GsonHelper.buildJsonObject("openid", openid, "user_coupon", userCoupon,
      "recv_time", recvTime);

    String responseContent = this.wxMaService.post(Coupon.UPDATE_USER_COUPON, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMaShopBaseResponse updateUserCouponStatus(String openid, String outUserCouponId,
                                                     String outCouponId, Integer status) throws WxErrorException {

    JsonObject json = GsonHelper.buildJsonObject("openid", openid,
      "out_user_coupon_id", outUserCouponId,
      "out_coupon_id", outCouponId,
      "status", status);

    String responseContent = this.wxMaService.post(Coupon.UPDATE_USER_COUPON_STATUS, json);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }
}
