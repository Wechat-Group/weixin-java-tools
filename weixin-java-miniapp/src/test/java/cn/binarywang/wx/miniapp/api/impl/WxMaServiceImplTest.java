package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

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

  @Test
  public void testGetRequestHttpClient() {
  }

  @Test
  public void testGetRequestHttpProxy() {
  }

  @Test
  public void testGetRequestType() {
  }

  @Test
  public void testInitHttp() {
  }

  @Test
  public void testGetRequestHttp() {
  }

  @Test
  public void testGetAccessToken() {
  }

  @Test
  public void testJsCode2SessionInfo() {
  }

  @Test
  public void testSetDynamicData() throws WxErrorException {
    this.wxService.setDynamicData(86400, "1000001", 1,
      "{\"items\":[{\"stock_name\":\"腾讯控股\", \"stock_code\":\"00700\", \"stock_market\":\"hk\"}], \"attribute\":{\"count\":2, \"totalcount\": 100, \"id\": \"XXX\", \"seq\": 0}}");
  }

  @Test
  public void testCheckSignature() {
  }

  @Test
  public void testTestGetAccessToken() {
  }

  @Test
  public void testGet() {
  }

  @Test
  public void testExecute() {
  }

  @Test
  public void testGetWxMaConfig() {
  }

  @Test
  public void testSetWxMaConfig() {
  }

  @Test
  public void testSetRetrySleepMillis() {
  }

  @Test
  public void testSetMaxRetryTimes() {
  }

  @Test
  public void testGetMsgService() {
  }

  @Test
  public void testGetMediaService() {
  }

  @Test
  public void testGetUserService() {
  }

  @Test
  public void testGetQrcodeService() {
  }

  @Test
  public void testGetTemplateService() {
  }

  @Test
  public void testGetSubscribeService() {
  }

  @Test
  public void testGetAnalysisService() {
  }

  @Test
  public void testGetCodeService() {
  }

  @Test
  public void testGetJsapiService() {
  }

  @Test
  public void testGetSettingService() {
  }

  @Test
  public void testGetShareService() {
  }

  @Test
  public void testGetRunService() {
  }

  @Test
  public void testGetSecCheckService() {
  }

  @Test
  public void testGetPluginService() {
  }

  @Test
  public void testGetExpressService() {
  }

  @Test
  public void testGetCloudService() {
  }
}
