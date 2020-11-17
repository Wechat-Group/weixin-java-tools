package cn.binarywang.wx.miniapp.bean.code;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-26 19:54
 */
public class WxMaCodeCommitRequestTest {
  @Test
  public void testToJson() {
    WxMaCodeCommitRequest commitRequest = WxMaCodeCommitRequest.builder()
      .templateId(1L)
      .userVersion("v0.1.0")
      .userDesc("init")
      .extConfig(WxMaCodeExtConfig.builder()
        .extAppid("app123")
        .extEnable(true)
        .build())
      .build();
    assertEquals(commitRequest.toJson(), "{\"template_id\":1,\"user_version\":\"v0.1.0\",\"user_desc\":\"init\",\"ext_json\":\"{\\\"extEnable\\\":true,\\\"extAppid\\\":\\\"app123\\\"}\"}");
  }
}
