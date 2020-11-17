package me.chanjar.weixin.common.error;

import me.chanjar.weixin.common.enums.WxType;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

@Test
public class WxErrorTest {
  public void testFromJson() {
    String json = "{ \"errcode\": 40003, \"errmsg\": \"invalid openid\" }";
    WxError wxError = WxError.fromJson(json, WxType.MP);
    assertEquals(40003, wxError.getErrorCode());
    assertEquals(wxError.getErrorMsgEn(), "invalid openid");

  }

  public void testFromBadJson1() {
    String json = "{ \"errcode\": 40003, \"errmsg\": \"invalid openid\", \"media_id\": \"12323423dsfafsf232f\" }";
    WxError wxError = WxError.fromJson(json, WxType.MP);
    assertEquals(40003, wxError.getErrorCode());
    assertEquals(wxError.getErrorMsgEn(), "invalid openid");

  }

  public void testFromBadJson2() {
    String json = "{\"access_token\":\"ACCESS_TOKEN\",\"expires_in\":7200}";
    WxError wxError = WxError.fromJson(json, WxType.MP);
    assertEquals(0, wxError.getErrorCode());
    assertNull(wxError.getErrorMsg());

  }

}
