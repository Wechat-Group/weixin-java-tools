package me.chanjar.weixin.mp.api.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpOcrService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.ocr.WxMpOcrIdCardResult;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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
  public WxMpOcrIdCardResult idCard(ImageType imgType, File imgFile) {
    return null;
  }
}
