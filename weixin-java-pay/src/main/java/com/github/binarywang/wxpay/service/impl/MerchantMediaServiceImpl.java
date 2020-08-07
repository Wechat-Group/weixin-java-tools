package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.media.ImageUploadResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MerchantMediaService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.WechatPayUploadHttpPost;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * 微信支付-媒体文件上传service
 * @author zhouyongshen
 */
@Slf4j
@RequiredArgsConstructor
public class MerchantMediaServiceImpl implements MerchantMediaService {

  private final WxPayService payService;

  @Override
  public ImageUploadResult imageUploadV3(File imageFile) throws WxPayException,IOException {
    String url = String.format("%s/v3/merchant/media/upload", this.payService.getPayBaseUrl());

    try (FileInputStream s1 = new FileInputStream(imageFile)) {
      String sha256 = DigestUtils.sha256Hex(s1);
      try (InputStream s2 = new FileInputStream(imageFile)) {
        WechatPayUploadHttpPost request = new WechatPayUploadHttpPost.Builder(URI.create(url))
          .withImage(imageFile.getName(), sha256, s2)
          .build();
        String result = this.payService.postV3(url, request);
        return ImageUploadResult.fromJson(result);
      }
    }
  }

}
