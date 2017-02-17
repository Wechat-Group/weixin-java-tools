package me.chanjar.weixin.mp.api;

import com.google.inject.Inject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import org.testng.*;
import org.testng.annotations.*;

import java.util.Arrays;

/**
 * @author chanjarster
 */
@Test(groups = "miscAPI")
@Guice(modules = ApiTestModule.class)
public class WxMpMiscAPITest {

  @Inject
  protected WxMpService wxService;

  @Test
  public void testGetCallbackIP() throws WxErrorException {
    String[] ipArray = this.wxService.getCallbackIP();
    System.out.println(Arrays.toString(ipArray));
    Assert.assertNotNull(ipArray);
    Assert.assertNotEquals(ipArray.length, 0);
  }

}
