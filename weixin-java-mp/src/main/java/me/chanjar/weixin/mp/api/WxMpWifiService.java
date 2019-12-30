package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.wifi.WxMpWifiShopDataResult;
import me.chanjar.weixin.mp.bean.wifi.WxMpWifiShopListResult;

/**
 * <pre>
 *  微信连接WI-FI接口.
 *  Created by BinaryWang on 2018/6/10.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMpWifiService {
  /**
   * <pre>
   * 获取Wi-Fi门店列表.
   * 通过此接口获取WiFi的门店列表，该列表包括公众平台的门店信息、以及添加设备后的WiFi相关信息。创建门店方法请参考“微信门店接口”。
   * 注：微信连Wi-Fi下的所有接口中的shop_id，必需先通过此接口获取。
   *
   * http请求方式: POST
   * 请求URL：https://api.weixin.qq.com/bizwifi/shop/list?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param pageIndex 分页下标，默认从1开始
   * @param pageSize  每页的个数，默认10个，最大20个
   * @return 结果
   * @throws WxErrorException 异常
   */
  WxMpWifiShopListResult listShop(int pageIndex, int pageSize) throws WxErrorException;

  /**
   * <pre>
   * 查询门店Wi-Fi信息
   * 通过此接口查询某一门店的详细Wi-Fi信息，包括门店内的设备类型、ssid、密码、设备数量、商家主页URL、顶部常驻入口文案。
   *
   * http请求方式: POST
   * 请求URL：https://api.weixin.qq.com/bizwifi/shop/get?access_token=ACCESS_TOKEN
   * POST数据格式：JSON
   * </pre>
   *
   * @param shopId 门店ID
   * @return 结果
   * @throws WxErrorException 异常
   */
  WxMpWifiShopDataResult getShopWifiInfo(int shopId) throws WxErrorException;

  /**
   * <pre>
   * 修改门店网络信息.
   * 通过此接口修改门店的网络信息，包括网络名称（ssid）或密码。需注意：
   * 只有门店下已添加Wi-Fi网络信息，才能调用此接口修改网络信息；添加方式请参考“添加密码型设备”和"添加portal型设备”接口文档。
   * 网络信息修改后，密码型设备需同步修改所有设备的ssid或密码；portal型设备需修改所有设备的ssid，并按照《硬件鉴权协议接口》修改“第二步：改造移动端portal页面”中的ssid参数，否则将无法正常连网。
   * 文档地址：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1457435413
   * </pre>
   *
   * @param shopId   门店ID
   * @param oldSsid  旧的ssid
   * @param ssid     无线网络设备的ssid。32个字符以内；ssid支持中文，但可能因设备兼容性问题导致显示乱码，或无法连接等问题，相关风险自行承担！ 当门店下是portal型设备时，ssid必填；当门店下是密码型设备时，ssid选填，且ssid和密码必须有一个以大写字母“WX”开头
   * @param password 无线网络设备的密码。8-24个字符；不能包含中文字符； 当门店下是密码型设备时，才可填写password，且ssid和密码必须有一个以大写字母“WX”开头
   * @return 是否更新成功
   * @throws WxErrorException .
   */
  boolean updateShopWifiInfo(int shopId, String oldSsid, String ssid, String password) throws WxErrorException;
}
