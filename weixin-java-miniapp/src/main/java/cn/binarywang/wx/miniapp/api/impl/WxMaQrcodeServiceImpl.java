package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaQrcode;
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

}
