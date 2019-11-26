package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.express.*;
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
   * 配置面单打印员
   */
  String UPDATE_PRINTER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/printer/update";

  /**
   * 获取打印员
   */
  String GET_PRINTER_URL = "https://api.weixin.qq.com/cgi-bin/express/business/printer/getall";

  /**
   * 获取支持的快递公司列表
   * @return  快递公司列表
   * @throws WxErrorException 获取失败时返回
   */
  List<WxMaExpressDelivery> getAllDelivery() throws WxErrorException;

  /**
   * 获取所有绑定的物流账号
   * @return 物流账号list
   * @throws WxErrorException 获取失败时返回
   */
  List<WxMaExpressAccount> getAllAccount() throws WxErrorException;

  /**
   * 绑定、解绑物流账号
   * @param wxMaExpressBindAccountRequest 物流账号对象
   * @throws WxErrorException 请求失败时返回
   */
  void bindAccount(WxMaExpressBindAccountRequest wxMaExpressBindAccountRequest) throws WxErrorException;

  /**
   * 配置面单打印员，可以设置多个，若需要使用微信打单 PC 软件，才需要调用。
   * @param wxMaExpressPrinterUpdateRequest  面单打印员对象
   * @throws WxErrorException 请求失败时返回
   */
  void updatePrinter(WxMaExpressPrinterUpdateRequest wxMaExpressPrinterUpdateRequest) throws WxErrorException;

  /**
   * 获取打印员。若需要使用微信打单 PC 软件，才需要调用
   * @throws WxErrorException 获取失败时返回
   */
  WxMaExpressPrinter getPrinter() throws WxErrorException;
}
