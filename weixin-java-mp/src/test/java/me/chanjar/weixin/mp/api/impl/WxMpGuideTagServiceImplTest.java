package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideBuyerResp;
import me.chanjar.weixin.mp.bean.guide.WxMpGuideTagInfo;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/13/013
 */

@Guice(modules = ApiTestModule.class)
public class WxMpGuideTagServiceImplTest {
  @Inject
  protected WxMpService wxService;

  /**
   * 顾问微信号 guide_account
   */
  private static final String ACCOUNT = "sxc_Warm";

  @Test
  public void testNewGuideTagOption() throws WxErrorException {
    List<String> list = new ArrayList<>();
    list.add("分类一");
    list.add("分类二");
    list.add("分类三");
    this.wxService.getGuideTagService().newGuideTagOption("A组", list);
  }

  @Test
  public void testDelGuideTagOption() throws WxErrorException {
    this.wxService.getGuideTagService().delGuideTagOption("A组");
  }

  @Test
  public void testAddGuideTagOption() throws WxErrorException {
    List<String> list = new ArrayList<>();
    list.add("分类四");
    this.wxService.getGuideTagService().addGuideTagOption("A组", list);
  }

  @Test
  public void testGetGuideTagOption() throws WxErrorException {
    List<WxMpGuideTagInfo> guideTagOption = this.wxService.getGuideTagService().getGuideTagOption();
    assertThat(guideTagOption).isNotNull();
  }

  @Test
  public void testAddGuideBuyerTag() throws WxErrorException {
    List<String> list = new ArrayList<>();
    list.add("oqlk8v0uTJgRnn5eEskNruD4-bc8");
    list.add("oqlk8vybPMWapMwOfFTFVYqWpGM0");
    List<WxMpGuideBuyerResp> wxMpGuideBuyerResps = this.wxService.getGuideTagService().addGuideBuyerTag(ACCOUNT, null, "分类一", list);
    assertThat(wxMpGuideBuyerResps).isNotNull();
  }

  @Test
  public void testAddGuideBuyerTagOnce() throws WxErrorException {
    this.wxService.getGuideTagService().addGuideBuyerTag(ACCOUNT, null, "分类二", "oqlk8v0uTJgRnn5eEskNruD4-bc8");
  }

  @Test
  public void testGetGuideBuyerTag() throws WxErrorException {
    List<String> guideBuyerTag = this.wxService.getGuideTagService().getGuideBuyerTag(ACCOUNT, null, "oqlk8v0uTJgRnn5eEskNruD4-bc8", true);
    assertThat(guideBuyerTag).isNotNull();
  }

  @Test
  public void testQueryGuideBuyerByTag() throws WxErrorException {
    List<String> list = new ArrayList<>();
    list.add("分类一");
    List<String> list1 = this.wxService.getGuideTagService().queryGuideBuyerByTag(ACCOUNT, null, 0, list);
    assertThat(list1).isNotNull();
  }

  @Test
  public void testdelGuideBuyerTag() throws WxErrorException {
    List<String> list = new ArrayList<>();
    list.add("oqlk8v0uTJgRnn5eEskNruD4-bc8");
    list.add("oqlk8vybPMWapMwOfFTFVYqWpGM0");
    List<WxMpGuideBuyerResp> respList = this.wxService.getGuideTagService().delGuideBuyerTag(ACCOUNT, null, "分类一", list);
    assertThat(respList).isNotNull();
  }

  @Test
  public void testDelGuideBuyerTagOnce() throws WxErrorException {
    this.wxService.getGuideTagService().delGuideBuyerTag(ACCOUNT, null, "分类一", "oqlk8v0uTJgRnn5eEskNruD4-bc8");
  }

  @Test
  public void testAddGuideBuyerDisplayTag() throws WxErrorException {
    List<String> list = new ArrayList<>();
    list.add("自定义信息1");
    list.add("自定义信息2");
    this.wxService.getGuideTagService().addGuideBuyerDisplayTag(ACCOUNT, null, "oqlk8v0uTJgRnn5eEskNruD4-bc8", list);
  }

  @Test
  public void testGetGuideBuyerDisplayTag() throws WxErrorException {
    List<String> list = this.wxService.getGuideTagService().getGuideBuyerDisplayTag(ACCOUNT, null, "oqlk8v0uTJgRnn5eEskNruD4-bc8");
    assertThat(list).isNotNull();
  }

}
