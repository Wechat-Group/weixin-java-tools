package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.guide.*;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

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

  /**
   * 顾问微信号 guide_account
   */
  private static final String ACCOUNT = "sxc_Warm";

  @Test
  public void testAddGuide() throws WxErrorException {
    this.wxService.getGuideService().addGuide(ACCOUNT, "", null, null);
  }

  @Test
  public void testAddGuide_another() throws WxErrorException {
    this.wxService.getGuideService().addGuide(WxMpGuideInfo.builder().account(ACCOUNT).build());
  }

  @Test
  public void testGetGuide() throws WxErrorException {
    final WxMpGuideInfo guideInfo = this.wxService.getGuideService().getGuide(ACCOUNT, null);
    assertThat(guideInfo).isNotNull();
  }

  @Test
  public void testUpdateGuide() throws WxErrorException {
    this.wxService.getGuideService().updateGuide(WxMpGuideInfo.builder().account(ACCOUNT).nickName("我是谁").build());
  }

  @Test
  public void testDelGuide() throws WxErrorException {
    this.wxService.getGuideService().delGuide(ACCOUNT, null);
  }

  @Test
  public void testListGuide() throws WxErrorException {
    final WxMpGuideList guideList = this.wxService.getGuideService().listGuide(0, 10);
    assertThat(guideList).isNotNull();
  }

  @Test
  public void testCreateGuideQrCode() throws WxErrorException {
    String guideQrCode = this.wxService.getGuideService().createGuideQrCode(ACCOUNT, null, null);
    assertThat(guideQrCode).isNotNull();
  }

  @Test
  public void testGetGuideChatRecord() throws WxErrorException {
    final WxMpGuideMsgList guideChatRecord = this.wxService.getGuideService().getGuideChatRecord(ACCOUNT, null, null, null, null, 0, 10);
    assertThat(guideChatRecord).isNotNull();
  }

  @Test
  public void testSetGuideConfig() throws WxErrorException {
    List<String> list = new ArrayList<>();
    list.add("自动回复设置" + ACCOUNT);
    list.add("自动回复设置" + ACCOUNT);

    this.wxService.getGuideService().setGuideConfig(null, null, true, list,
      WxMpAddGuideAutoReply.builder().content("欢迎测试1").msgType(1).build(),
      WxMpAddGuideAutoReply.builder().content("欢迎测试2").msgType(1).build());
  }

  @Test
  public void testGetGuideConfig() throws WxErrorException {
    final WxMpGuideConfig guideConfig = this.wxService.getGuideService().getGuideConfig(ACCOUNT, null);
    assertThat(guideConfig).isNotNull();
  }

  @Test
  public void testSetGuideAcctConfig() throws WxErrorException {
    List<String> list = new ArrayList<>();
    list.add("敏感词1");
    list.add("敏感词2");
    this.wxService.getGuideService().setGuideAcctConfig(false, list, "离线自动回复");
  }

  @Test
  public void testGetGuideAcctConfig() throws WxErrorException {
    final WxMpGuideAcctConfig guideAcctConfig = this.wxService.getGuideService().getGuideAcctConfig();
    assertThat(guideAcctConfig).isNotNull();
  }

  @Test
  public void testPushShowWxaPathMenu() throws WxErrorException {
    this.wxService.getGuideService().pushShowWxaPathMenu("wx4f793c04fd3be5a8", ACCOUNT);
  }

  @Test
  public void testNewGuideGroup() throws WxErrorException {
    Long id = this.wxService.getGuideService().newGuideGroup("顾问分组名称");
    assertThat(id).isNotNull();
  }

  @Test
  public void testGetGuideGroup() throws WxErrorException {
    List<WxMpGuideGroup> guideGroupList = this.wxService.getGuideService().getGuideGroupList();
    assertThat(guideGroupList).isNotNull();
  }

  @Test
  public void testGetGroupInfo() throws WxErrorException {
    WxMpGuideGroupInfoList groupInfo = this.wxService.getGuideService().getGroupInfo(1860131524965138433L, 0, 10);
    assertThat(groupInfo).isNotNull();
  }

  @Test
  public void testAddGuide2GuideGroup() throws WxErrorException {
    this.wxService.getGuideService().addGuide2GuideGroup(1860131524965138433L, ACCOUNT);
  }

  @Test
  public void testDelGuide2GuideGroup() throws WxErrorException {
    this.wxService.getGuideService().delGuide2GuideGroup(1860131524965138433L, ACCOUNT);
  }

  @Test
  public void testGetGroupByGuide() throws WxErrorException {
    List<Long> groupByGuide = this.wxService.getGuideService().getGroupByGuide(ACCOUNT);
    assertThat(groupByGuide).isNotNull();
  }

  @Test
  public void testDelGuideGroup() throws WxErrorException {
    this.wxService.getGuideService().delGuideGroup(1860131524965138433L);
  }
}

