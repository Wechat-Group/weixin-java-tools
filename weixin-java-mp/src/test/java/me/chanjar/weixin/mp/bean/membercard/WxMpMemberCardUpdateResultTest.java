package me.chanjar.weixin.mp.bean.membercard;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author YuJian
 * @version 2017/7/15
 */
public class WxMpMemberCardUpdateResultTest {

  @Test
  public void testFromJson() throws Exception {
    String json = "{\n" +
      "    \"errcode\": 0,\n" +
      "    \"errmsg\": \"ok\",\n" +
      "    \"result_bonus\": 100,\n" +
      "    \"result_balance\": 200,\n" +
      "    \"openid\": \"oFS7Fjl0WsZ9AMZqrI80nbIq8xrA\"\n" +
      "}";

    WxMpMemberCardUpdateResult result = WxMpMemberCardUpdateResult.fromJson(json);

    assertNotNull(result);
    assertTrue(result.getErrorCode().equalsIgnoreCase("0"));

    System.out.println(result);
  }
}
