package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.service.WxOcrService;
import me.chanjar.weixin.common.bean.ocr.*;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.requestexecuter.ocr.OcrDiscernRequestExecutor;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * ocr 接口实现.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-06-22
 */
@RequiredArgsConstructor
public class WxMaOcrServiceImpl implements WxOcrService {
  private static final String IDCARD = "https://api.weixin.qq.com/cv/ocr/idcard?img_url=%s";
  private static final String FILEIDCARD = "https://api.weixin.qq.com/cv/ocr/idcard";
  private static final String BANK_CARD = "https://api.weixin.qq.com/cv/ocr/bankcard?img_url=%s";
  private static final String FILE_BANK_CARD = "https://api.weixin.qq.com/cv/ocr/bankcard";
  private static final String DRIVING = "https://api.weixin.qq.com/cv/ocr/driving?img_url=%s";
  private static final String FILE_DRIVING = "https://api.weixin.qq.com/cv/ocr/driving";
  private static final String DRIVING_LICENSE = "https://api.weixin.qq.com/cv/ocr/drivinglicense?img_url=%s";
  private static final String FILE_DRIVING_LICENSE = "https://api.weixin.qq.com/cv/ocr/drivinglicense";
  private static final String BIZ_LICENSE = "https://api.weixin.qq.com/cv/ocr/bizlicense?img_url=%s";
  private static final String FILE_BIZ_LICENSE = "https://api.weixin.qq.com/cv/ocr/bizlicense";
  private static final String COMM = "https://api.weixin.qq.com/cv/ocr/comm?img_url=%s";
  private static final String FILE_COMM = "https://api.weixin.qq.com/cv/ocr/comm";

  private final WxMaService mainService;

  @Override
  public WxOcrIdCardResult idCard(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.mainService.post(String.format(IDCARD, imgUrl), (String) null);
    return WxOcrIdCardResult.fromJson(result);
  }

  @Override
  public WxOcrIdCardResult idCard(File imgFile) throws WxErrorException {
    String result = this.mainService.execute(OcrDiscernRequestExecutor.create(this.mainService.getRequestHttp()),
      FILEIDCARD, imgFile);
    return WxOcrIdCardResult.fromJson(result);
  }

  @Override
  public WxOcrBankCardResult bankCard(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.mainService.post(String.format(BANK_CARD, imgUrl), (String) null);
    return WxOcrBankCardResult.fromJson(result);
  }

  @Override
  public WxOcrBankCardResult bankCard(File imgFile) throws WxErrorException {
    String result = this.mainService.execute(OcrDiscernRequestExecutor.create(this.mainService.getRequestHttp()),
      FILE_BANK_CARD, imgFile);
    return WxOcrBankCardResult.fromJson(result);
  }

  @Override
  public WxOcrDrivingResult driving(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.mainService.post(String.format(DRIVING, imgUrl), (String) null);
    return WxOcrDrivingResult.fromJson(result);
  }

  @Override
  public WxOcrDrivingResult driving(File imgFile) throws WxErrorException {
    String result = this.mainService.execute(OcrDiscernRequestExecutor.create(this.mainService.getRequestHttp()),
      FILE_DRIVING, imgFile);
    return WxOcrDrivingResult.fromJson(result);
  }

  @Override
  public WxOcrDrivingLicenseResult drivingLicense(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.mainService.post(String.format(DRIVING_LICENSE, imgUrl), (String) null);
    return WxOcrDrivingLicenseResult.fromJson(result);
  }

  @Override
  public WxOcrDrivingLicenseResult drivingLicense(File imgFile) throws WxErrorException {
    String result = this.mainService.execute(OcrDiscernRequestExecutor.create(this.mainService.getRequestHttp()),
      FILE_DRIVING_LICENSE, imgFile);
    return WxOcrDrivingLicenseResult.fromJson(result);
  }

  @Override
  public WxOcrBizLicenseResult bizLicense(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.mainService.post(String.format(BIZ_LICENSE, imgUrl), (String) null);
    return WxOcrBizLicenseResult.fromJson(result);
  }

  @Override
  public WxOcrBizLicenseResult bizLicense(File imgFile) throws WxErrorException {
    String result = this.mainService.execute(OcrDiscernRequestExecutor.create(this.mainService.getRequestHttp()),
      FILE_BIZ_LICENSE, imgFile);
    return WxOcrBizLicenseResult.fromJson(result);
  }

  @Override
  public WxOcrCommResult comm(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.mainService.post(String.format(COMM, imgUrl), (String) null);
    return WxOcrCommResult.fromJson(result);
  }

  @Override
  public WxOcrCommResult comm(File imgFile) throws WxErrorException {
    String result = this.mainService.execute(OcrDiscernRequestExecutor.create(this.mainService.getRequestHttp()),
      FILE_COMM, imgFile);
    return WxOcrCommResult.fromJson(result);
  }
}
