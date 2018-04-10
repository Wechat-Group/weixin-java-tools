package me.chanjar.weixin.mp.bean.subscribe;

import org.testng.annotations.*;

import static org.testng.AssertJUnit.*;

/**
 * @author Mklaus
 * @date 2018-01-22 下午1:41
 */
public class WxMpSubscribeMessageTest {
  @Test
  public void testToJson() {
    String actual = "{" +
        "\"touser\":\"OPENID\"," +
        "\"template_id\":\"TEMPLATE_ID\"," +
        "\"url\":\"URL\"," +
        "\"miniprogram\":{" +
          "\"appid\":\"xiaochengxuappid12345\"," +
          "\"pagepath\":\"index?foo=bar\"" +
        "}," +
        "\"scene\":\"SCENE\"," +
        "\"title\":\"TITLE\"," +
        "\"data\":{" +
          "\"content\":{" +
            "\"value\":\"VALUE\"," +
            "\"color\":\"COLOR\"" +
          "}" +
        "}" +
      "}";

    WxMpSubscribeMessage message = WxMpSubscribeMessage.builder()
      .toUser("OPENID")
      .templateId("TEMPLATE_ID")
      .url("URL")
      .miniProgram(new WxMpSubscribeMessage.MiniProgram("xiaochengxuappid12345", "index?foo=bar"))
      .scene("SCENE")
      .title("TITLE")
      .contentValue("VALUE")
      .contentColor("COLOR")
      .build();

    assertEquals(message.toJson(), actual);

  }
}
