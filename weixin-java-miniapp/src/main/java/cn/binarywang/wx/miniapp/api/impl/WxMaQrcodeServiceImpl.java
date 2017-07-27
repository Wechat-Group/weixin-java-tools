package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaQrcode;
import cn.binarywang.wx.miniapp.bean.WxMaWxcode;
import cn.binarywang.wx.miniapp.bean.WxMaWxcodeLimit;
import cn.binarywang.wx.miniapp.util.http.QrCodeRequestExecutor;
import me.chanjar.weixin.common.exception.WxErrorException;

import java.io.File;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaQrcodeServiceImpl implements WxMaQrcodeService {
  private WxMaService wxMaService;

  public WxMaQrcodeServiceImpl(WxMaService wxMaService) {
    this.wxMaService = wxMaService;
  }

  @Override
  public File createQrcode(String path, int width) throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode";
    return this.wxMaService.execute(new QrCodeRequestExecutor(this.wxMaService.getRequestHttp()),
      url, new WxMaQrcode(path, width));
  }

  @Override
  public File createQrcode(String path) throws WxErrorException {
    return this.createQrcode(path,430);
  }

  @Override
  public File createWxCode(String path, int width, boolean autoColor, LineColor lineColor) throws WxErrorException {
    String url = "https://api.weixin.qq.com/wxa/getwxacode";
    WxMaWxcode wxMaWxcode = new WxMaWxcode();
    wxMaWxcode.setPath(path);
    wxMaWxcode.setWidth(width);
    wxMaWxcode.setAutoColor(autoColor);
    wxMaWxcode.setLineColor(lineColor);
    return this.wxMaService.execute(new QrCodeRequestExecutor(this.wxMaService.getRequestHttp()),
      url, wxMaWxcode);
  }

  @Override
  public File createWxCode(String path, int width) throws WxErrorException {
    return this.createWxCode(path,width,true,null);
  }

  @Override
  public File createWxCode(String path) throws WxErrorException {
    return this.createWxCode(path,430,true,null);
  }

  @Override
  public File createWxCodeLimit(String scene, String page, int width, boolean autoColor, LineColor lineColor) throws WxErrorException {
    String url = "http://api.weixin.qq.com/wxa/getwxacodeunlimit";
    WxMaWxcodeLimit wxMaWxcodeLimit = new WxMaWxcodeLimit();
    wxMaWxcodeLimit.setScene(scene);
    wxMaWxcodeLimit.setPage(page);
    wxMaWxcodeLimit.setWidth(width);
    wxMaWxcodeLimit.setAutoColor(autoColor);
    wxMaWxcodeLimit.setLineColor(lineColor);
    return this.wxMaService.execute(new QrCodeRequestExecutor(this.wxMaService.getRequestHttp()),
      url, wxMaWxcodeLimit);
  }

  @Override
  public File createWxCodeLimit(String scene, String page) throws WxErrorException {
    return this.createWxCodeLimit(scene,page,430,true,null);
  }

}
