package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import cn.binarywang.wx.miniapp.bean.WxMaQrcode;
import cn.binarywang.wx.miniapp.bean.WxaCode;
import cn.binarywang.wx.miniapp.bean.WxaCodeUnlimit;
import cn.binarywang.wx.miniapp.executor.QrcodeBytesRequestExecutor;
import cn.binarywang.wx.miniapp.executor.QrcodeRequestExecutor;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Qrcode.*;
import static cn.binarywang.wx.miniapp.constant.WxMaConstants.DEFAULT_ENV_VERSION;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxMaQrcodeServiceImpl implements WxMaQrcodeService {
  private final WxMaService service;

  @Override
  public byte[] createQrcodeBytes(String path, int width) throws WxErrorException {
    return this.service.execute(QrcodeBytesRequestExecutor.create(this.service.getRequestHttp()), CREATE_QRCODE_URL,
      new WxMaQrcode(path, width));
  }

  @Override
  public File createQrcode(String path, int width) throws WxErrorException {
    return this.service.execute(QrcodeRequestExecutor.create(this.service.getRequestHttp()), CREATE_QRCODE_URL,
      new WxMaQrcode(path, width));
  }

  @Override
  public File createQrcode(String path) throws WxErrorException {
    return this.createQrcode(path, 430);
  }

  @Override
  public byte[] createWxaCodeBytes(String path, String envVersion, int width, boolean autoColor,
                                   WxMaCodeLineColor lineColor, boolean isHyaline) throws WxErrorException {
    return this.service.execute(QrcodeBytesRequestExecutor.create(this.service.getRequestHttp()), GET_WXACODE_URL,
      WxaCode.builder()
        .path(path)
        .envVersion(StringUtils.defaultIfEmpty(envVersion, DEFAULT_ENV_VERSION))
        .width(width)
        .autoColor(autoColor)
        .lineColor(lineColor)
        .isHyaline(isHyaline)
        .build());
  }

  @Override
  public File createWxaCode(String path, String envVersion, int width, boolean autoColor, WxMaCodeLineColor lineColor,
                            boolean isHyaline) throws WxErrorException {
    return this.service.execute(QrcodeRequestExecutor.create(this.service.getRequestHttp()), GET_WXACODE_URL,
      WxaCode.builder()
        .path(path)
        .envVersion(StringUtils.defaultIfEmpty(envVersion, DEFAULT_ENV_VERSION))
        .width(width)
        .autoColor(autoColor)
        .lineColor(lineColor)
        .isHyaline(isHyaline)
        .build());
  }

  @Override
  public File createWxaCode(String path, int width) throws WxErrorException {
    return this.createWxaCode(path, null, width, true, null, false);
  }

  @Override
  public File createWxaCode(String path) throws WxErrorException {
    return this.createWxaCode(path, 430);
  }

  @Override
  public byte[] createWxaCodeUnlimitBytes(String scene, String page, boolean checkPath, String envVersion, int width,
                                          boolean autoColor, WxMaCodeLineColor lineColor, boolean isHyaline)
    throws WxErrorException {
    return this.service.execute(QrcodeBytesRequestExecutor.create(this.service.getRequestHttp()),
      GET_WXACODE_UNLIMIT_URL, this.buildWxaCodeUnlimit(scene, page, checkPath, envVersion, width, autoColor, lineColor, isHyaline));
  }

  @Override
  public File createWxaCodeUnlimit(String scene, String page, boolean checkPath, String envVersion, int width,
                                   boolean autoColor, WxMaCodeLineColor lineColor, boolean isHyaline) throws WxErrorException {
    return this.service.execute(QrcodeRequestExecutor.create(this.service.getRequestHttp()),
      GET_WXACODE_UNLIMIT_URL, this.buildWxaCodeUnlimit(scene, page, checkPath, envVersion, width, autoColor, lineColor, isHyaline));
  }

  private WxaCodeUnlimit buildWxaCodeUnlimit(String scene, String page, boolean checkPath, String envVersion, int width,
                                             boolean autoColor, WxMaCodeLineColor lineColor, boolean isHyaline) {
    WxaCodeUnlimit wxaCodeUnlimit = new WxaCodeUnlimit();
    wxaCodeUnlimit.setScene(scene);
    wxaCodeUnlimit.setPage(page);
    wxaCodeUnlimit.setCheckPath(checkPath);
    wxaCodeUnlimit.setEnvVersion(envVersion);
    wxaCodeUnlimit.setWidth(width);
    wxaCodeUnlimit.setAutoColor(autoColor);
    wxaCodeUnlimit.setLineColor(lineColor);
    wxaCodeUnlimit.setHyaline(isHyaline);

    return wxaCodeUnlimit;
  }

  @Override
  public File createWxaCodeUnlimit(String scene, String page) throws WxErrorException {
    return this.createWxaCodeUnlimit(scene, page, true, DEFAULT_ENV_VERSION, 430, true, null, false);
  }

  @Override
  public File createQrcode(String path, int width, String filePath) throws WxErrorException {
    return this.service.execute(QrcodeRequestExecutor.create(this.service.getRequestHttp(), filePath),
      CREATE_QRCODE_URL, new WxMaQrcode(path, width));
  }

  @Override
  public File createQrcode(String path, String filePath) throws WxErrorException {
    return createQrcode(path, 430, filePath);
  }

  @Override
  public File createWxaCode(String path, String envVersion, int width, String filePath, boolean autoColor,
                            WxMaCodeLineColor lineColor, boolean isHyaline) throws WxErrorException {
    return this.service.execute(QrcodeRequestExecutor.create(this.service.getRequestHttp(), filePath), GET_WXACODE_URL,
      WxaCode.builder()
        .path(path)
        .envVersion(StringUtils.defaultIfEmpty(envVersion, DEFAULT_ENV_VERSION))
        .width(width)
        .autoColor(autoColor)
        .lineColor(lineColor)
        .isHyaline(isHyaline)
        .build());
  }

  @Override
  public File createWxaCode(String path, int width, String filePath) throws WxErrorException {
    return this.createWxaCode(path, null, width, filePath, true, null, false);
  }

  @Override
  public File createWxaCode(String path, String filePath) throws WxErrorException {
    return this.createWxaCode(path, 430, filePath);
  }

  @Override
  public File createWxaCodeUnlimit(String scene, String page, String filePath, boolean checkPath, String envVersion,
                                   int width, boolean autoColor, WxMaCodeLineColor lineColor, boolean isHyaline)
    throws WxErrorException {
    return this.service.execute(QrcodeRequestExecutor.create(this.service.getRequestHttp(), filePath),
      GET_WXACODE_UNLIMIT_URL, this.buildWxaCodeUnlimit(scene, page, checkPath, envVersion, width, autoColor, lineColor, isHyaline));
  }

  @Override
  public File createWxaCodeUnlimit(String scene, String page, String filePath) throws WxErrorException {
    return this.createWxaCodeUnlimit(scene, page, filePath, true, DEFAULT_ENV_VERSION, 430, true, null, false);
  }

}
