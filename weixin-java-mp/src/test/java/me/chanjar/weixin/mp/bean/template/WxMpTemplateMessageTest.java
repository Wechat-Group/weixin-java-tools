package me.chanjar.weixin.mp.bean.template;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * <pre>
 * Created by Binary Wang on 2017-3-30.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMpTemplateMessageTest {
  @Test
  public void testToJson() {
    WxMpTemplateMessage tm = WxMpTemplateMessage.builder()
      .toUser("OPENID")
      .templateId("ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY")
      .miniProgram(new WxMpTemplateMessage.MiniProgram("xiaochengxuappid12345", "index?foo=bar",true))
      .url("http://weixin.qq.com/download")
      .build();

    tm.addData(
      new WxMpTemplateData("first", "haahah", "#FF00FF"));
    tm.addData(
      new WxMpTemplateData("remark", "heihei", "#FF00FF"));

    assertEquals(tm.toJson(), "{\"touser\":\"OPENID\",\"template_id\":\"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY\",\"url\":\"http://weixin.qq.com/download\",\"miniprogram\":{\"appid\":\"xiaochengxuappid12345\",\"pagepath\":\"index?foo=bar\"},\"data\":{\"first\":{\"value\":\"haahah\",\"color\":\"#FF00FF\"},\"remark\":{\"value\":\"heihei\",\"color\":\"#FF00FF\"}}}");
  }

}
