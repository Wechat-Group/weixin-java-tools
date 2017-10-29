package me.chanjar.weixin.open.api.impl;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.device.*;
import me.chanjar.weixin.open.api.WxOpenDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import me.chanjar.weixin.open.api.WxOpenService;

/**
 * Created by keungtung on 10/12/2016.
 */
public class WxOpenDeviceServiceImpl implements WxOpenDeviceService {
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/device";
  private static Logger log = LoggerFactory.getLogger(WxOpenMenuServiceImpl.class);

  private WxOpenService wxOpenService;

  public WxOpenDeviceServiceImpl(WxOpenService wxOpenService) {
    this.wxOpenService = wxOpenService;
  }

  @Override
  public TransMsgResp transMsg(String appid, WxDeviceMsg msg) throws WxErrorException {
    String url = API_URL_PREFIX + "/transmsg";
    String response = this.wxOpenService.post(appid, url, msg.toJson());
    return TransMsgResp.fromJson(response);
  }

  @Override
  public WxDeviceQrCodeResult getQrCode(String appid, String productId) throws WxErrorException {
    String url = API_URL_PREFIX + "/getqrcode";
    String response = this.wxOpenService.get(appid, url, "product_id=" + productId);
    return WxDeviceQrCodeResult.fromJson(response);
  }

  @Override
  public WxDeviceAuthorizeResult authorize(String appid, WxDeviceAuthorize wxDeviceAuthorize) throws WxErrorException {
    String url = API_URL_PREFIX + "/authorize_device";
    String response = this.wxOpenService.post(appid, url, wxDeviceAuthorize.toJson());
    return WxDeviceAuthorizeResult.fromJson(response);
  }

  @Override
  public WxDeviceBindResult bind(String appid, WxDeviceBind wxDeviceBind) throws WxErrorException {
    String url = API_URL_PREFIX + "/bind";
    String response = this.wxOpenService.post(appid, url, wxDeviceBind.toJson());
    return WxDeviceBindResult.fromJson(response);
  }

  @Override
  public WxDeviceBindResult compelBind(String appid, WxDeviceBind wxDeviceBind) throws WxErrorException {
    String url = API_URL_PREFIX + "/compel_bind";
    String response = this.wxOpenService.post(appid, url, wxDeviceBind.toJson());
    return WxDeviceBindResult.fromJson(response);
  }

  @Override
  public WxDeviceBindResult unbind(String appid, WxDeviceBind wxDeviceBind) throws WxErrorException {
    String url = API_URL_PREFIX + "/unbind?";
    String response = this.wxOpenService.post(appid, url, wxDeviceBind.toJson());
    return WxDeviceBindResult.fromJson(response);
  }

  @Override
  public WxDeviceBindResult compelUnbind(String appid, WxDeviceBind wxDeviceBind) throws WxErrorException {
    String url = API_URL_PREFIX + "/compel_unbind?";
    String response = this.wxOpenService.post(appid, url, wxDeviceBind.toJson());
    return WxDeviceBindResult.fromJson(response);
  }

  @Override
  public WxDeviceOpenIdResult getOpenId(String appid, String deviceType, String deviceId) throws WxErrorException {
    String url = API_URL_PREFIX + "/get_openid";
    String response = this.wxOpenService.get(appid, url, "device_type=" + deviceType + "&device_id=" + deviceId);
    return WxDeviceOpenIdResult.fromJson(response);
  }

  @Override
  public WxDeviceBindDeviceResult getBindDevice(String appid, String openId) throws WxErrorException {
    String url = API_URL_PREFIX + "/get_bind_device";
    String response = this.wxOpenService.get(appid, url, "openid=" + openId);
    return WxDeviceBindDeviceResult.fromJson(response);
  }
}

