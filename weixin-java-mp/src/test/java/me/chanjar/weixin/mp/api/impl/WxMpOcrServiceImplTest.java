package me.chanjar.weixin.mp.api.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.mp.api.WxMpOcrService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConstants;
import me.chanjar.weixin.mp.bean.ocr.WxMpOcrBankCardResult;
import me.chanjar.weixin.mp.bean.ocr.WxMpOcrIdCardResult;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-06-22
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpOcrServiceImplTest {
  @Inject
  private WxMpService mpService;

  @Test
  public void testIdCard() throws WxErrorException {
    final WxMpOcrIdCardResult result = this.mpService.getOcrService().idCard(WxMpOcrService.ImageType.PHOTO,
      "http://www.baidu.com");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testBankCard() throws WxErrorException {
    final WxMpOcrBankCardResult result = this.mpService.getOcrService().bankCard("https://res.wx.qq.com/op_res/eP7PObYbBJj-_19EbGBL4PWe_zQ1NwET5NXSugjEWc-4ayns4Q-HFJrp-AOog8ih");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testBankCard2() throws Exception {
    InputStream inputStream = this.getImageStream("https://res.wx.qq.com/op_res/eP7PObYbBJj-_19EbGBL4PWe_zQ1NwET5NXSugjEWc-4ayns4Q-HFJrp-AOog8ih");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxMpOcrBankCardResult result = this.mpService.getOcrService().bankCard(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  private InputStream getImageStream(String url) {
    try {
      HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
      connection.setReadTimeout(5000);
      connection.setConnectTimeout(5000);
      connection.setRequestMethod("GET");
      if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
        return connection.getInputStream();
      }
    } catch (IOException e) {
      System.out.println("获取网络图片出现异常，图片路径为：" + url);
    }
    return null;
  }

  public static class MockTest {
    private WxMpService wxService = mock(WxMpService.class);

    @Test
    public void testIdCard() throws Exception {
      String returnJson = "{\"type\":\"Back\",\"name\":\"张三\",\"id\":\"110101199909090099\",\"valid_date\":\"20110101-20210201\"}";

      when(wxService.get(anyString(), anyString())).thenReturn(returnJson);
      final WxMpOcrServiceImpl wxMpOcrService = new WxMpOcrServiceImpl(wxService);

      final WxMpOcrIdCardResult result = wxMpOcrService.idCard(WxMpOcrService.ImageType.PHOTO, "abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }

    @Test
    public void testBankCard() throws Exception {
      String returnJson = "{\"number\":\"24234234345234\"}";

      when(wxService.get(anyString(), anyString())).thenReturn(returnJson);
      final WxMpOcrServiceImpl wxMpOcrService = new WxMpOcrServiceImpl(wxService);

      final WxMpOcrBankCardResult result = wxMpOcrService.bankCard("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }
  }
}
