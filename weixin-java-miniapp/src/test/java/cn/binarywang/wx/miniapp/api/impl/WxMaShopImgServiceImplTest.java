package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.bean.result.WxMinishopImageUploadCustomizeResult;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author liming1019
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaShopImgServiceImplTest {

  @Inject
  private WxMaService wxService;

  @Test
  public void testUploadImg() throws WxErrorException {
    File file = new File("/Users/liming/Desktop/test.jpeg");
    WxMinishopImageUploadCustomizeResult result = wxService.getShopImgService().uploadImg(file);
    assertThat(result).isNotNull();
  }
}
