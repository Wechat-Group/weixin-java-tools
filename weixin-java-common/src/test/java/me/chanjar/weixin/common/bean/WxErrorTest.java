package me.chanjar.weixin.common.bean;

import me.chanjar.weixin.common.bean.result.WxError;
import org.testng.*;
import org.testng.annotations.*;

@Test
public class WxErrorTest {

  public void testFromJson() {

    String json = "{ \"errcode\": 40003, \"errmsg\": \"invalid openid\" }";
    WxError wxError = WxError.fromJson(json);
    Assert.assertTrue(wxError.getErrorCode() == 40003);
    Assert.assertEquals(wxError.getErrorMsg(), "invalid openid");

  }

  public void testFromBadJson1() {

    String json = "{ \"errcode\": 40003, \"errmsg\": \"invalid openid\", \"media_id\": \"12323423dsfafsf232f\" }";
    WxError wxError = WxError.fromJson(json);
    Assert.assertTrue(wxError.getErrorCode() == 40003);
    Assert.assertEquals(wxError.getErrorMsg(), "invalid openid");

  }

  public void testFromBadJson2() {

    String json = "{\"access_token\":\"ACCESS_TOKEN\",\"expires_in\":7200}";
    WxError wxError = WxError.fromJson(json);
    Assert.assertTrue(wxError.getErrorCode() == 0);
    Assert.assertEquals(wxError.getErrorMsg(), null);

  }

}
