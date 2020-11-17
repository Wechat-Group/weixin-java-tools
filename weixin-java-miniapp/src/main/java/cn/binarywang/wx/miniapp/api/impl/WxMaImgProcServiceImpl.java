package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.service.WxImgProcService;
import me.chanjar.weixin.common.bean.imgproc.WxImgProcAiCropResult;
import me.chanjar.weixin.common.bean.imgproc.WxImgProcQrCodeResult;
import me.chanjar.weixin.common.bean.imgproc.WxImgProcSuperResolutionResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.requestexecuter.ocr.OcrDiscernRequestExecutor;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


/**
 * 图像处理接口实现.
 *
 * @author Theo Nie
 */
@RequiredArgsConstructor
public class WxMaImgProcServiceImpl implements WxImgProcService {
  /**
   * 二维码/条码识别
   */
  private static final String QRCODE = "/cv/img/qrcode?img_url=%s";

  /**
   * 二维码/条码识别(文件)
   */
  private static final String FILE_QRCODE = "/cv/img/qrcode";

  /**
   * 图片高清化
   */
  private static final String SUPER_RESOLUTION = "/cv/img/superresolution?img_url=%s";

  /**
   * 图片高清化(文件)
   */
  private static final String FILE_SUPER_RESOLUTION = "/cv/img/superresolution";

  /**
   * 图片智能裁剪
   */
  private static final String AI_CROP = "/cv/img/aicrop?img_url=%s&ratios=%s";

  /**
   * 图片智能裁剪(文件)
   */
  private static final String FILE_AI_CROP = "/cv/img/aicrop?ratios=%s";
  private final WxMaService service;

  @Override
  public WxImgProcQrCodeResult qrCode(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      //ignore
    }

    final String result = this.service.get(String.format(QRCODE, imgUrl), null);
    return WxImgProcQrCodeResult.fromJson(result);
  }

  @Override
  public WxImgProcQrCodeResult qrCode(File imgFile) throws WxErrorException {
    String result = this.service.execute(OcrDiscernRequestExecutor.create(this.service.getRequestHttp()),
      FILE_QRCODE, imgFile);
    return WxImgProcQrCodeResult.fromJson(result);
  }

  @Override
  public WxImgProcSuperResolutionResult superResolution(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      //ignore
    }

    final String result = this.service.get(String.format(SUPER_RESOLUTION, imgUrl), null);
    return WxImgProcSuperResolutionResult.fromJson(result);
  }

  @Override
  public WxImgProcSuperResolutionResult superResolution(File imgFile) throws WxErrorException {
    String result = this.service.execute(OcrDiscernRequestExecutor.create(this.service.getRequestHttp()),
      FILE_SUPER_RESOLUTION, imgFile);
    return WxImgProcSuperResolutionResult.fromJson(result);
  }

  @Override
  public WxImgProcAiCropResult aiCrop(String imgUrl) throws WxErrorException {
    return this.aiCrop(imgUrl, "");
  }

  @Override
  public WxImgProcAiCropResult aiCrop(String imgUrl, String ratios) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      //ignore
    }

    if (StringUtils.isEmpty(ratios)) {
      ratios = "";
    }

    final String result = this.service.get(String.format(AI_CROP, imgUrl, ratios), null);
    return WxImgProcAiCropResult.fromJson(result);
  }

  @Override
  public WxImgProcAiCropResult aiCrop(File imgFile) throws WxErrorException {
    return this.aiCrop(imgFile, "");
  }

  @Override
  public WxImgProcAiCropResult aiCrop(File imgFile, String ratios) throws WxErrorException {
    if (StringUtils.isEmpty(ratios)) {
      ratios = "";
    }

    String result = this.service.execute(OcrDiscernRequestExecutor.create(this.service.getRequestHttp()),
      String.format(FILE_AI_CROP, ratios), imgFile);
    return WxImgProcAiCropResult.fromJson(result);
  }
}
