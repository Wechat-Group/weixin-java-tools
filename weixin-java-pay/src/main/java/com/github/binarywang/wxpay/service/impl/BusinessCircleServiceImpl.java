package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.businesscircle.BusinessCircleNotifyData;
import com.github.binarywang.wxpay.bean.businesscircle.PaidResult;
import com.github.binarywang.wxpay.bean.businesscircle.PointsNotifyRequest;
import com.github.binarywang.wxpay.bean.businesscircle.RefundResult;
import com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.BusinessCircleService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Objects;

/**
 * 微信支付-微信支付智慧商圈service
 *
 * @author thinsstar
 */
@Slf4j
@RequiredArgsConstructor
public class BusinessCircleServiceImpl implements BusinessCircleService {
  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  @Override
  public void notifyPoints(PointsNotifyRequest request) throws WxPayException {
    String url = String.format("%s/v3/businesscircle/points/notify", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
  }

  /**
   * 校验通知签名
   *
   * @param header 通知头信息
   * @param data   通知数据
   * @return true:校验通过 false:校验不通过
   */
  private boolean verifyNotifySign(SignatureHeader header, String data) {
    String beforeSign = String.format("%s%n%s%n%s%n", header.getTimeStamp(), header.getNonce(), data);
    return payService.getConfig().getVerifier().verify(header.getSerialNo(),
      beforeSign.getBytes(StandardCharsets.UTF_8), header.getSigned());
  }

  @Override
  public BusinessCircleNotifyData parseNotifyData(String data, SignatureHeader header) throws WxPayException {
    if (Objects.nonNull(header) && !this.verifyNotifySign(header, data)) {
      throw new WxPayException("非法请求，头部信息验证失败");
    }
    return GSON.fromJson(data, BusinessCircleNotifyData.class);
  }

  @Override
  public PaidResult decryptPaidNotifyDataResource(BusinessCircleNotifyData data) throws WxPayException {
    BusinessCircleNotifyData.Resource resource = data.getResource();
    String cipherText = resource.getCipherText();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key = this.payService.getConfig().getApiV3Key();
    try {
      return GSON.fromJson(AesUtils.decryptToString(associatedData, nonce, cipherText, apiV3Key), PaidResult.class);
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }

  @Override
  public RefundResult decryptRefundNotifyDataResource(BusinessCircleNotifyData data) throws WxPayException {
    BusinessCircleNotifyData.Resource resource = data.getResource();
    String cipherText = resource.getCipherText();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key = this.payService.getConfig().getApiV3Key();
    try {
      return GSON.fromJson(AesUtils.decryptToString(associatedData, nonce, cipherText, apiV3Key), RefundResult.class);
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }
}
