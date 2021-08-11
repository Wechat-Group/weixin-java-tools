package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShopImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.result.WxMinishopImageUploadCustomizeResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.MinishopUploadRequestCustomizeExecutor;

import java.io.File;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Img.IMG_UPLOAD;

/**
 * @author liming1019
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaShopImgServiceImpl implements WxMaShopImgService {
  private final WxMaService service;

  @Override
  public WxMinishopImageUploadCustomizeResult uploadImg(File file) throws WxErrorException {
    WxMinishopImageUploadCustomizeResult result = this.service.execute(
      MinishopUploadRequestCustomizeExecutor.create(this.service.getRequestHttp()), IMG_UPLOAD, file);
    return result;
  }
}
