package me.chanjar.weixin.common.bean;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WxAccessTokenTest {

  public void testFromJson() {

    String json = "{\"access_token\":\"ACCESS_TOKEN\",\"expires_in\":7200}";
    WxAccessToken wxError = WxAccessToken.fromJson(json);
    Assert.assertEquals(wxError.getAccessToken(), "ACCESS_TOKEN");
    Assert.assertTrue(wxError.getExpiresIn() == 7200);

  }

}
