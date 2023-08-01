package me.chanjar.weixin.open.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.result.WxOpenResult;
import me.chanjar.weixin.open.bean.shoppingOrders.*;

/**
 * @author xzh
 * created on  2023/5/17 16:49
 */
public interface WxOpenMaShoppingOrdersService {


  /**
   * 上传购物详情
   */
  String UPLOAD_SHOPPING_INFO = "https://api.weixin.qq.com/user-order/orders";

  /**
   * 上传物流信息
   */
  String UPLOAD_SHIPPING_INFO = "https://api.weixin.qq.com/user-order/orders/shippings";

  /**
   * 上传合单购物详情
   */
  String UPLOAD_COMBINED_SHOPPING_INFO = "https://api.weixin.qq.com/user-order/combine-orders";

  /**
   * 上传合单物流信息
   */
  String UPLOAD_COMBINED_SHIPPING_INFO = "https://api.weixin.qq.com/user-order/combine-orders/shippings";

  /**
   * 开通购物订单产品权限
   */
  String OPEN_SHOPPING_ORDER_PRODUCT_PERMISSION = "https://api.weixin.qq.com/user-order/orders-permission/open";

  /**
   * 提交购物订单接入审核
   */
  String CONFIRM_PRODUCT_PERMISSION = "https://api.weixin.qq.com/user-order/orders-permission/confirm";

  /**
   * 验证购物订单上传结果
   */
  String SHOPPING_INFO_VERIFY_UPLOAD_RESULT = "https://api.weixin.qq.com/user-order/shoppinginfo/verify";


  /**
   * 上传购物详情
   *
   * @param info 购物详情
   * @return WxOpenResult
   * @throws WxErrorException
   */
  WxOpenResult upload(ShoppingInfo info) throws WxErrorException;

  /**
   * 上传物流信息
   *
   * @param info 物流信息
   * @return WxOpenResult
   * @throws WxErrorException
   */
  WxOpenResult upload(ShippingInfo info) throws WxErrorException;

  /**
   * 上传合单购物详情
   *
   * @param info 购物详情
   * @return WxOpenResult
   * @throws WxErrorException
   */
  WxOpenResult upload(CombinedShoppingInfo info) throws WxErrorException;

  /**
   * 上传合单物流信息
   *
   * @param info 物流信息
   * @return WxOpenResult
   * @throws WxErrorException
   */
  WxOpenResult upload(CombinedShippingInfo info) throws WxErrorException;

  /**
   * 开通购物订单产品权限
   *
   * @return WxOpenResult
   * @throws WxErrorException
   */
  WxOpenResult openShoppingOrderProductPermission() throws WxErrorException;

  /**
   * 提交购物订单接入审核
   *
   * @return WxOpenShoppingOrdersConfirmResult
   * @throws WxErrorException
   */
  WxOpenShoppingOrdersConfirmResult confirmProductPermission() throws WxErrorException;

  /**
   * 验证购物订单上传结果
   *
   * @param info 信息
   * @return WxOpenResult
   * @throws WxErrorException
   */
  WxOpenShoppingInfoVerifyUploadResult verifyUploadResult(ShoppingInfoVerifyUpload info) throws WxErrorException;

}
