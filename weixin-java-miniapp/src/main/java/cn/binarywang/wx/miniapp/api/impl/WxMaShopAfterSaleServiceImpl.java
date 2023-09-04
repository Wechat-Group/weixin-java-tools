package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShopAfterSaleService;
import cn.binarywang.wx.miniapp.bean.shop.request.*;
import cn.binarywang.wx.miniapp.bean.shop.response.*;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Aftersale.*;
import static me.chanjar.weixin.common.api.WxConsts.ERR_CODE;

/**
 * @author boris
 * @author liming1019
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaShopAfterSaleServiceImpl implements WxMaShopAfterSaleService {
  private final WxMaService wxMaService;


  /**
   * 创建售后
   *
   * @param request
   * @return WxMaShopBaseResponse
   * @throws WxErrorException
   */
  @Override
  public WxMaShopAfterSaleAddResponse add(WxMaShopAfterSaleAddRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(AFTERSALE_ADD, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopAfterSaleAddResponse.class);
  }

  /**
   * 获取订单下售后单
   *
   * @param request
   * @return WxMaShopAfterSaleGetResponse
   * @throws WxErrorException
   */
  @Override
  public WxMaShopAfterSaleGetResponse get(WxMaShopAfterSaleGetRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(AFTERSALE_GET, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopAfterSaleGetResponse.class);
  }

  /**
   * 获取售后单详情(EC版)
   *
   * @param request
   * @return WxMaShopEcAfterSaleGetResponse
   * @throws WxErrorException
   */
  @Override
  public WxMaShopEcAfterSaleGetResponse get(WxMaShopEcAfterSaleGetRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(ECAFTERSALE_GET, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopEcAfterSaleGetResponse.class);
  }

  /**
   * 更新售后
   *
   * @param request
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxMaShopBaseResponse update(WxMaShopAfterSaleUpdateRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(AFTERSALE_UPDATE, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  @Override
  public WxMaShopBaseResponse update(WxMaShopEcAfterSaleUpdateRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(EC_AFTERSALE_UPDATE, request);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopBaseResponse.class);
  }

  /**
   * 用户取消售后申请
   * @param outAfterSaleId 商家自定义订单ID
   * @param afterSaleId 与out_aftersale_id二选一
   * @param openId 用户openid
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxMaShopBaseResponse cancel(String outAfterSaleId, Long afterSaleId, String openId)
    throws WxErrorException {
    JsonObject request = GsonHelper.buildJsonObject("out_aftersale_id", outAfterSaleId,
      "aftersale_id", afterSaleId, "openid", openId);
    String resp = this.wxMaService.post(AFTERSALE_CANCEL, request);
    JsonObject jsonObject = GsonParser.parse(resp);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(resp, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(resp, WxMaShopBaseResponse.class);
  }

  /**
   * 用户上传退货物流
   * @param request
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxMaShopBaseResponse uploadReturnInfo(WxMaShopAfterSaleUploadReturnInfoRequest request)
    throws WxErrorException {
    String resp = this.wxMaService.post(AFTERSALE_UPLOAD_RETURN_INFO, request);
    JsonObject jsonObject = GsonParser.parse(resp);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(resp, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(resp, WxMaShopBaseResponse.class);
  }

  /**
   * 商家同意退款
   * @param outAfterSaleId
   * @param afterSaleId
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxMaShopBaseResponse acceptRefund(String outAfterSaleId, Long afterSaleId)
    throws WxErrorException {
    JsonObject request = GsonHelper.buildJsonObject("out_aftersale_id", outAfterSaleId,
      "aftersale_id", afterSaleId);
    String resp = this.wxMaService.post(AFTERSALE_ACCEPT_REFUND, request);
    JsonObject jsonObject = GsonParser.parse(resp);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(resp, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(resp, WxMaShopBaseResponse.class);
  }

  /**
   * 商家同意退货
   * @param request
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxMaShopBaseResponse acceptReturn(WxMaShopAcceptReturnRequest request)
    throws WxErrorException {
    String resp = this.wxMaService.post(AFTERSALE_ACCEPT_RETURN, request);
    JsonObject jsonObject = GsonParser.parse(resp);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(resp, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(resp, WxMaShopBaseResponse.class);
  }

  /**
   * 商家拒绝售后
   * @param outAfterSaleId
   * @param afterSaleId
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxMaShopBaseResponse reject(String outAfterSaleId, Long afterSaleId)
    throws WxErrorException {
    JsonObject request = GsonHelper.buildJsonObject("out_aftersale_id", outAfterSaleId,
      "aftersale_id", afterSaleId);
    String resp = this.wxMaService.post(AFTERSALE_REJECT, request);
    JsonObject jsonObject = GsonParser.parse(resp);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(resp, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(resp, WxMaShopBaseResponse.class);
  }

  /**
   * 商家上传退款凭证
   * @param request
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxMaShopBaseResponse uploadCertificates(WxMaShopUploadCerficatesRequest request)
    throws WxErrorException {
    String resp = this.wxMaService.post(AFTERSALE_UPLOAD_CERTIFICATES, request);
    JsonObject jsonObject = GsonParser.parse(resp);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(resp, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(resp, WxMaShopBaseResponse.class);
  }

  /**
   * 商家更新订单售后期
   * @param outOrderId
   * @param orderId
   * @param openid
   * @param afterSaleDeadline
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxMaShopBaseResponse updateDeadline(String outOrderId, Long orderId, String openid,
    Long afterSaleDeadline) throws WxErrorException {
    JsonObject request = GsonHelper.buildJsonObject("out_order_id", outOrderId,
      "order_id", orderId, "openid", openid, "after_sale_deadline", afterSaleDeadline);
    String resp = this.wxMaService.post(AFTERSALE_UPLOAD_DEADLINE, request);
    JsonObject jsonObject = GsonParser.parse(resp);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(resp, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(resp, WxMaShopBaseResponse.class);
  }

  /**
   * 获取售后单详情
   * @param request
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxMaShopAfterSaleListResponse list(WxMaShopAfterSaleListRequest request) throws WxErrorException {
    String resp = this.wxMaService.post(AFTERSALE_GET_LIST, request);
    JsonObject jsonObject = GsonParser.parse(resp);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(resp, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(resp, WxMaShopAfterSaleListResponse.class);
  }
}
