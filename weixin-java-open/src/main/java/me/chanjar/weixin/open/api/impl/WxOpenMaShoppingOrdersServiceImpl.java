package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenMaShoppingOrdersService;
import me.chanjar.weixin.open.bean.result.WxOpenResult;
import me.chanjar.weixin.open.bean.shoppingOrders.*;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

@Slf4j
@AllArgsConstructor
public class WxOpenMaShoppingOrdersServiceImpl implements WxOpenMaShoppingOrdersService {

  private final WxMaService wxMaService;

  /**
   * 上传购物详情
   *
   * @param info 购物详情
   * @return WxOpenResult
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult upload(ShoppingInfo info) throws WxErrorException {
    String response = wxMaService.post(UPLOAD_SHOPPING_INFO, WxOpenGsonBuilder.create().toJson(info));
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  /**
   * 上传物流信息
   *
   * @param info 物流信息
   * @return WxOpenResult
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult upload(ShippingInfo info) throws WxErrorException {
    String response = wxMaService.post(UPLOAD_SHIPPING_INFO, WxOpenGsonBuilder.create().toJson(info));
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  /**
   * 上传合单购物详情
   *
   * @param info 购物详情
   * @return WxOpenResult
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult upload(CombinedShoppingInfo info) throws WxErrorException {
    String response = wxMaService.post(UPLOAD_COMBINED_SHOPPING_INFO, WxOpenGsonBuilder.create().toJson(info));
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  /**
   * 上传合单物流信息
   *
   * @param info 物流信息
   * @return WxOpenResult
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult upload(CombinedShippingInfo info) throws WxErrorException {
    String response = wxMaService.post(UPLOAD_COMBINED_SHIPPING_INFO, WxOpenGsonBuilder.create().toJson(info));
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  /**
   * 开通购物订单产品权限
   *
   * @return WxOpenResult
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult openShoppingOrderProductPermission() throws WxErrorException {
    String response = wxMaService.post(OPEN_SHOPPING_ORDER_PRODUCT_PERMISSION, "");
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  /**
   * 提交购物订单接入审核
   *
   * @return WxOpenShoppingOrdersConfirmResult
   * @throws WxErrorException
   */
  @Override
  public WxOpenShoppingOrdersConfirmResult confirmProductPermission() throws WxErrorException {
    String response = wxMaService.post(CONFIRM_PRODUCT_PERMISSION, "");
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenShoppingOrdersConfirmResult.class);
  }

  /**
   * 验证购物订单上传结果
   *
   * @param info 信息
   * @return WxOpenResult
   * @throws WxErrorException
   */
  @Override
  public WxOpenShoppingInfoVerifyUploadResult verifyUploadResult(ShoppingInfoVerifyUpload info) throws WxErrorException {
    String response = wxMaService.post(SHOPPING_INFO_VERIFY_UPLOAD_RESULT, WxOpenGsonBuilder.create().toJson(info));
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenShoppingInfoVerifyUploadResult.class);
  }

}
