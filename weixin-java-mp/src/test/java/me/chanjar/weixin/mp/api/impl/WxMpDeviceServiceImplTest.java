package me.chanjar.weixin.mp.api.impl;


import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.device.WxDeviceQrCodeResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * Created by keungtung on 14/12/2016.
 */
@Test(groups = "deviceApi")
@Guice(modules = ApiTestModule.class)
public class WxMpDeviceServiceImplTest {
  @Inject
  protected WxMpService wxService;

  @Test(dataProvider = "productId")
  public void testGetQrcode(String productId) {
    try {
      WxDeviceQrCodeResult result = wxService.getDeviceService().getQrCode(productId);
      println(result.toJson());
    } catch (WxErrorException e) {
      println(e.getMessage());
    }
  }

  private void println(String content) {
    System.out.println(content);
  }

  @DataProvider(name = "productId")
  public Object[][] getProductId() {
    return new Object[][]{new Object[]{"25639"}};
  }
}
