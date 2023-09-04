package me.chanjar.weixin.channel.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import me.chanjar.weixin.channel.bean.shop.ShopInfoResponse;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public class ResponseUtilsTest {

  @Test
  public void testDecode() {
    String json = "{\"errcode\":0,\"errmsg\":\"ok\",\"info\":{\"nickname\":\"某某视频号\","
      + "\"headimg_url\":\"http://wx.qlogo.cn/xxx\",\"subject_type\":\"企业\"}}";
    ShopInfoResponse response = ResponseUtils.decode(json, ShopInfoResponse.class);
    assertNotNull(response);
    assertEquals(response.getErrCode(), 0);
    assertEquals(response.getErrMsg(), "ok");
    assertEquals(response.getInfo().getNickname(), "某某视频号");
    assertEquals(response.getInfo().getHeadImgUrl(), "http://wx.qlogo.cn/xxx");
    assertEquals(response.getInfo().getSubjectType(), "企业");
  }

  @Test
  public void testInternalError() {
    ShopInfoResponse response = ResponseUtils.internalError(ShopInfoResponse.class);
    assertNotNull(response);
    assertEquals(response.getErrCode(), -99);
  }
}
