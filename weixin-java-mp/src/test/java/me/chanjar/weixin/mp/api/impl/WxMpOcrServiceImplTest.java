package me.chanjar.weixin.mp.api.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpOcrService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.ocr.WxMpOcrIdCardResult;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

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
  }
}
