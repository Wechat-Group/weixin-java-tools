package me.chanjar.weixin.mp.bean.subscribe;

import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

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
      .miniProgram(new WxMpTemplateMessage.MiniProgram("xiaochengxuappid12345", "index?foo=bar"))
      .scene("SCENE")
      .title("TITLE")
      .contentValue("VALUE")
      .contentColor("COLOR")
      .build();

    assertEquals(message.toJson(), actual);

  }
}
