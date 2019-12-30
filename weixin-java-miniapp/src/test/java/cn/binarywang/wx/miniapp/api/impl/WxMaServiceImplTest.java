package cn.binarywang.wx.miniapp.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.*;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaServiceImplTest {

  @Inject
  private WxMaService wxService;

  public void testRefreshAccessToken() throws WxErrorException {
    WxMaConfig configStorage = this.wxService.getWxMaConfig();
    String before = configStorage.getAccessToken();
    this.wxService.getAccessToken(false);

    String after = configStorage.getAccessToken();
    assertNotEquals(before, after);
    assertTrue(StringUtils.isNotBlank(after));
  }

  @Test(expectedExceptions = {WxErrorException.class})
  public void testGetPaidUnionId() throws WxErrorException {
    final String unionId = this.wxService.getPaidUnionId("1", null, "3", "4");
    assertThat(unionId).isNotEmpty();
  }

  @Test
  public void testPost() throws WxErrorException {
    final String postResult = this.wxService.post("https://api.weixin.qq.com/wxa/setdynamicdata", "{\n" +
      "    \"data\": \"{\\\"items\\\": [{\\\"from\\\":{\\\"city_name_cn\\\":\\\"广州市\\\"},\\\"to\\\":{\\\"city_name_cn\\\":\\\"北京市\\\"}}], \\\"attribute\\\": {\\\"count\\\": 1, \\\"totalcount\\\": 100, \\\"id\\\": \\\"1\\\", \\\"seq\\\": 0}}\",\n" +
      "    \"lifespan\": 86400,\n" +
      "    \"query\": \"{\\\"type\\\":100005}\",\n" +
      "    \"scene\": 1\n" +
      "}");

    System.out.println(postResult);
  }
}
