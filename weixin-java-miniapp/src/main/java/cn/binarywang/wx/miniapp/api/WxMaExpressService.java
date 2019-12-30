package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.express.WxMaExpressAccount;
import cn.binarywang.wx.miniapp.bean.express.WxMaExpressDelivery;
import cn.binarywang.wx.miniapp.bean.express.WxMaExpressPath;
import cn.binarywang.wx.miniapp.bean.express.WxMaExpressPrinter;
import cn.binarywang.wx.miniapp.bean.express.request.*;
import cn.binarywang.wx.miniapp.bean.express.result.WxMaExpressOrderInfoResult;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

/**
 * 小程序物流助手
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
public interface WxMaExpressService {
  /**
   * 获取支持的快递公司列表
   */
  String ALL_DELIVERY_URL = "https://api.weixin.qq.com/cgi-bin/express/business/delivery/getall";

  /**
   * 获取所有绑定的物流账号
   */
  String ALL_ACCOUNT_URL = "https://api.weixin.qq.com/cgi-bin/express/business/account/getall";

  /**
   * 绑定、解绑物流账号
   */
  String BIND_ACCOUNT_URL = "https://api.weixin.qq.com/cgi-bin/express/business/account/bind";

  /**
   * 获取电子面单余额
   */
  String GET_QUOTA_URL = "https://api.weixin.qq.com/cgi-bin/express/business/quota/get";

  /**
   * 配置面单打印员
   */
  String UPDATE_PRINTER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/printer/update";

  /**
   * 获取打印员
   */
  String GET_PRINTER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/printer/getall";

  /**
   * 生成运单
   */
  String ADD_ORDER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/order/add";

  /**
   * 批量获取运单数据
   */
  String BATCH_GET_ORDER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/order/batchget";

  /**
   * 取消运单
   */
  String CANCEL_ORDER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/order/cancel";

  /**
   * 获取运单数据
   */
  String GET_ORDER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/order/get";

  /**
   * 查询运单轨迹
   */
  String GET_PATH_URL = "https://api.weixin.qq.com/cgi-bin/express/business/path/get";

  /**
   * 模拟快递公司更新订单状态
   */
  String TEST_UPDATE_ORDER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/test_update_order";

  /**
   * 获取支持的快递公司列表
   * @return  快递公司列表
   * @throws WxErrorException 获取失败时返回
   * <pre>
   *   <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.getAllDelivery.html">查看文档</a>
   * </pre>
   */
  List<WxMaExpressDelivery> getAllDelivery() throws WxErrorException;

  /**
   * 获取所有绑定的物流账号
   * @return 物流账号list
   * @throws WxErrorException 获取失败时返回
   * <pre>
   *   <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.getAllAccount.html">查看文档</a>
   * </pre>
   */
  List<WxMaExpressAccount> getAllAccount() throws WxErrorException;

  /**
   * 绑定、解绑物流账号
   * @param wxMaExpressBindAccountRequest 物流账号对象
   * @throws WxErrorException 请求失败时返回
   * <pre>
   *   <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.bindAccount.html">查看文档</a>
   * </pre>
   */
  void bindAccount(WxMaExpressBindAccountRequest wxMaExpressBindAccountRequest) throws WxErrorException;

  /**
   * 获取电子面单余额。仅在使用加盟类快递公司时，才可以调用。
   * @param wxMaExpressBindAccountRequest 物流账号对象
   * @return 电子面单余额
   * @throws WxErrorException 获取失败时返回
   * <pre>
   *   <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.getQuota.html">查看文档</a>
   * </pre>
   */
  Integer getQuota(WxMaExpressBindAccountRequest wxMaExpressBindAccountRequest) throws WxErrorException;

  /**
   * 配置面单打印员，可以设置多个，若需要使用微信打单 PC 软件，才需要调用。
   * @param wxMaExpressPrinterUpdateRequest  面单打印员对象
   * @throws WxErrorException 请求失败时返回
   * <pre>
   *   <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.updatePrinter.html">查看文档</a>
   * </pre>
   */
  void updatePrinter(WxMaExpressPrinterUpdateRequest wxMaExpressPrinterUpdateRequest) throws WxErrorException;

  /**
   * 获取打印员。若需要使用微信打单 PC 软件，才需要调用
   * @return 打印员
   * @throws WxErrorException 获取失败时返回
   * <pre>
   *   <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.getPrinter.html">查看文档</a>
   * </pre>
   */
  WxMaExpressPrinter getPrinter() throws WxErrorException;

  /**
   * 生成运单
   * @param wxMaExpressAddOrderRequest 生成运单请求对象
   * @return 生成运单结果
   * @throws WxErrorException 请求失败时返回
   * <pre>
   *   <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.addOrder.html">查看文档</a>
   * </pre>
   */
  WxMaExpressOrderInfoResult addOrder(WxMaExpressAddOrderRequest wxMaExpressAddOrderRequest) throws WxErrorException;

  /**
   * 批量获取运单数据
   * @param requests 获取运单请求对象集合，最多不能超过1000个
   * @return 运单信息集合
   * @throws WxErrorException 获取失败时返回
   * <pre>
   *   <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.batchGetOrder.html">查看文档</a>
   * </pre>
   */
  List<WxMaExpressOrderInfoResult> batchGetOrder(List<WxMaExpressGetOrderRequest> requests) throws WxErrorException;

  /**
   * 取消运单
   * @param wxMaExpressGetOrderRequest 运单信息请求对象
   * @throws WxErrorException 取消失败时返回
   * <pre>
   *   <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.cancelOrder.html">查看文档</a>
   * </pre>
   */
  void cancelOrder(WxMaExpressGetOrderRequest wxMaExpressGetOrderRequest) throws WxErrorException;

  /**
   * 获取运单数据
   * @param wxMaExpressGetOrderRequest 运单信息请求对象
   * @return 运单信息
   * @throws WxErrorException 获取失败时返回
   * <pre>
   *   <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.getOrder.html">查看文档</a>
   * </pre>
   */
  WxMaExpressOrderInfoResult getOrder(WxMaExpressGetOrderRequest wxMaExpressGetOrderRequest) throws WxErrorException;

  /**
   * 查询运单轨迹
   * @param wxMaExpressGetOrderRequest 运单信息请求对象
   * @return 运单轨迹对象
   * @throws WxErrorException 查询失败时返回
   * <pre>
   *   <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.getPath.html">查看文档</a>
   * </pre>
   */
  WxMaExpressPath getPath(WxMaExpressGetOrderRequest wxMaExpressGetOrderRequest) throws WxErrorException;

  /**
   * 模拟快递公司更新订单状态, 该接口只能用户测试
   * @param wxMaExpressTestUpdateOrderRequest  模拟快递公司更新订单状态请求对象
   * @throws WxErrorException 模拟更新订单状态失败时返回
   * <pre>
   *   <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/express/by-business/logistics.testUpdateOrder.html">查看文档</a>
   * </pre>
   */
  void testUpdateOrder(WxMaExpressTestUpdateOrderRequest wxMaExpressTestUpdateOrderRequest) throws WxErrorException;
}
