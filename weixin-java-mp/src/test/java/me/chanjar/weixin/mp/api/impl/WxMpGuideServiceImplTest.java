package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideInfo;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideList;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 单元测试.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-10-06
 */
@Guice(modules = ApiTestModule.class)
public class WxMpGuideServiceImplTest {
  @Inject
  protected WxMpService wxService;

  @Test
  public void testAddGuide() throws WxErrorException {
    this.wxService.getGuideService().addGuide("wx1java", "", null, null);
  }

  @Test
  public void testAddGuide_another() throws WxErrorException {
    this.wxService.getGuideService().addGuide(WxMpGuideInfo.builder().account("wx1java").build());
  }

  @Test
  public void testGetGuide() throws WxErrorException {
    final WxMpGuideInfo guideInfo = this.wxService.getGuideService().getGuide("wx1java", null);
    assertThat(guideInfo).isNotNull();
  }

  @Test
  public void testUpdateGuide() throws WxErrorException {
    this.wxService.getGuideService().updateGuide(WxMpGuideInfo.builder().account("wx1java").nickName("我是谁").build());
  }

  @Test
  public void testDelGuide() throws WxErrorException {
    this.wxService.getGuideService().delGuide("wx1java", null);
  }

  @Test
  public void testListGuide() throws WxErrorException {
    final WxMpGuideList guideList = this.wxService.getGuideService().listGuide(0, 10);
    assertThat(guideList).isNotNull();
  }
}
