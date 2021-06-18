package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideCardMaterialInfo;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideImgMaterialInfoList;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideWordMaterialInfoList;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/13/013
 */

@Guice(modules = ApiTestModule.class)
public class WxMpGuideMaterialServiceImplTest {
  @Inject
  protected WxMpService wxService;

  /**
   * 图片路径
   */
  private static final String IMG_URL = "C:\\Users\\Administrator\\Desktop\\imgText.png";


  @Test
  public void testSetGuideCardMaterial() throws WxErrorException {
    WxMediaUploadResult wxMediaUploadResult = this.wxService.getMaterialService()
      .mediaUpload(WxConsts.MediaFileType.IMAGE, new File(IMG_URL));
    this.wxService.getGuideMaterialService().setGuideCardMaterial(wxMediaUploadResult.getMediaId(), 0, "小程序素材标题", "pages/login-type/index.html", "wx4f793c04fd3be5a8");
  }

  @Test
  public void testGetGuideCardMaterial() throws WxErrorException {
    List<WxMpGuideCardMaterialInfo> guideCardMaterial = this.wxService.getGuideMaterialService().getGuideCardMaterial(0);
    assertThat(guideCardMaterial).isNotNull();
  }

  @Test
  public void testDelGuideCardMaterial() throws WxErrorException {
    this.wxService.getGuideMaterialService().delGuideCardMaterial(0, "小程序素材标题", "pages/login-type/index.html", "wx4f793c04fd3be5a8");
  }

  @Test
  public void testSetGuideImageMaterial() throws WxErrorException {
    WxMediaUploadResult wxMediaUploadResult = this.wxService.getMaterialService()
      .mediaUpload(WxConsts.MediaFileType.IMAGE, new File(IMG_URL));
    this.wxService.getGuideMaterialService().setGuideImageMaterial(wxMediaUploadResult.getMediaId(), 0);
  }

  @Test
  public void testGetGuideImageMaterial() throws WxErrorException {
    WxMpGuideImgMaterialInfoList guideImageMaterial = this.wxService.getGuideMaterialService().getGuideImageMaterial(0, 0, 20);
    assertThat(guideImageMaterial).isNotNull();
  }

  @Test
  public void testDelGuideImageMaterial() throws WxErrorException {
    this.wxService.getGuideMaterialService().delGuideImageMaterial(0, "http://mmbiz.qpic.cn/mmbiz_png/63bwCoCgX0neicbffKiaL4vqXAUChYwE1VO0ZG5b6SW3Shv7kR1ia46b3gS8zf78piaR7vk7I6MRqbVzibJVJoNtkEg/0");
  }

  @Test
  public void testSetGuideWordMaterial() throws WxErrorException {
    this.wxService.getGuideMaterialService().setGuideWordMaterial(0, "文字素材测试");
  }

  @Test
  public void testGetGuideWordMaterial() throws WxErrorException {
    WxMpGuideWordMaterialInfoList guideWordMaterial = this.wxService.getGuideMaterialService().getGuideWordMaterial(0, 0, 20);
    assertThat(guideWordMaterial).isNotNull();
  }

  @Test
  public void testDelGuideWordMaterial() throws WxErrorException {
    this.wxService.getGuideMaterialService().delGuideWordMaterial(0, "文字素材测试");
  }
}
