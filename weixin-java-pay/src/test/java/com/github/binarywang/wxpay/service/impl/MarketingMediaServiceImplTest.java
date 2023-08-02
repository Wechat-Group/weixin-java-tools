package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.media.MarketingImageUploadResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * <pre>
 *  营销专用媒体文件上传测试类
 * </pre>
 *
 * @author thinsstar
 */
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class MarketingMediaServiceImplTest {

  @Inject
  private WxPayService wxPayService;

  @Test
  public void testMarketingImageUploadV3() throws WxPayException, IOException {
    String filePath = "你的图片文件的路径地址";

    File file = new File(filePath);

    MarketingImageUploadResult imageUploadResult = wxPayService.getMarketingMediaService().imageUploadV3(file);
    String mediaUrl = imageUploadResult.getMediaUrl();

    log.info("mediaUrl：[{}]", mediaUrl);

    File file2 = new File(filePath);

    MarketingImageUploadResult imageUploadResult2 = wxPayService.getMarketingMediaService().imageUploadV3(file2);
    String mediaUrl2 = imageUploadResult2.getMediaUrl();

    log.info("mediaUrl2：[{}]", mediaUrl2);

  }
}
