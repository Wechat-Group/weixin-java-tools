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

  @Test
  public void testUploadImg2() throws WxErrorException {
    File file = new File("/Users/liming/Desktop/test.jpeg");
    WxMinishopImageUploadCustomizeResult result = wxService.getShopImgService().uploadImg(file, "1");
    assertThat(result).isNotNull();
  }

  @Test
  public void testUploadImg3() throws WxErrorException {
    String imgUrl = "https://tongmeng2020.oss-cn-hangzhou.aliyuncs.com/fariocc5yzqlkyk1hwe1.jpg";
    WxMinishopImageUploadCustomizeResult result = wxService.getShopImgService().uploadImg(imgUrl, "1");
    assertThat(result).isNotNull();
  }
}
