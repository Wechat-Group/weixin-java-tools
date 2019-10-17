package me.chanjar.weixin.mp.api.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpOcrService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.ocr.WxMpOcrBankCardResult;
import me.chanjar.weixin.mp.bean.ocr.WxMpOcrIdCardResult;
import me.chanjar.weixin.mp.util.requestexecuter.ocr.OcrDiscernRequestExecutor;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Ocr.BANK_CARD;
import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Ocr.FILEIDCARD;
import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Ocr.FILE_BANK_CARD;
import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Ocr.IDCARD;

/**
 * ocr 接口实现.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-06-22
 */
@RequiredArgsConstructor
public class WxMpOcrServiceImpl implements WxMpOcrService {
  private final WxMpService wxMpService;

  @Override
  public WxMpOcrIdCardResult idCard(ImageType imgType, String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.wxMpService.get(String.format(IDCARD.getUrl(this.wxMpService.getWxMpConfigStorage()),
      imgType.getType(), imgUrl), null);
    return WxMpOcrIdCardResult.fromJson(result);
  }

  @Override
  public WxMpOcrIdCardResult idCard(ImageType imgType, File imgFile) throws WxErrorException {
    String result = this.wxMpService.execute(OcrDiscernRequestExecutor.create(this.wxMpService.getRequestHttp()), String.format(FILEIDCARD.getUrl(this.wxMpService.getWxMpConfigStorage()),
      imgType.getType()), imgFile);
    return WxMpOcrIdCardResult.fromJson(result);
  }

  @Override
  public WxMpOcrBankCardResult bankCard(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      // ignore cannot happen
    }

    final String result = this.wxMpService.get(String.format(BANK_CARD.getUrl(this.wxMpService.getWxMpConfigStorage()),
      imgUrl), null);
    return WxMpOcrBankCardResult.fromJson(result);
  }

  @Override
  public WxMpOcrBankCardResult bankCard(File imgFile) throws WxErrorException {
    String result = this.wxMpService.execute(OcrDiscernRequestExecutor.create(this.wxMpService.getRequestHttp()), FILE_BANK_CARD.getUrl(this.wxMpService.getWxMpConfigStorage()), imgFile);
    return WxMpOcrBankCardResult.fromJson(result);
  }
}
