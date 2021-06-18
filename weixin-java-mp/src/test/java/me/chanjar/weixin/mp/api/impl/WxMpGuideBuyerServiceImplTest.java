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
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/13/013
 */

@Guice(modules = ApiTestModule.class)
public class WxMpGuideBuyerServiceImplTest {
  @Inject
  protected WxMpService wxService;

  /**
   * 顾问微信号 guide_account
   */
  private static final String ACCOUNT = "sxc_Warm";

  @Test
  public void testAddGuideBuyerRelation() throws WxErrorException {
    List<WxMpAddGuideBuyerInfo> list = new ArrayList<>();
    list.add(WxMpAddGuideBuyerInfo.builder().nickname("小执花").openid("oqlk8v0uTJgRnn5eEskNruD4-bc8").build());
    List<WxMpGuideBuyerResp> wxMpGuideBuyerResps = this.wxService.getGuideBuyerService().addGuideBuyerRelation(ACCOUNT, null, list);
    assertThat(wxMpGuideBuyerResps).isNotNull();
  }

  @Test
  public void testAddGuideBuyerRelationOnce() throws WxErrorException {
    this.wxService.getGuideBuyerService().addGuideBuyerRelation(ACCOUNT, null, "oqlk8v0uTJgRnn5eEskNruD4-bc8", "小执花");
  }

  @Test
  public void testDelGuideBuyerRelation() throws WxErrorException {
    List<String> list = new ArrayList<>();
    list.add("oqlk8v0uTJgRnn5eEskNruD4-bc8");
    List<WxMpGuideBuyerResp> wxMpGuideBuyerResps = this.wxService.getGuideBuyerService().delGuideBuyerRelation(ACCOUNT, null, list);
    assertThat(wxMpGuideBuyerResps).isNotNull();
  }

  @Test
  public void testDelGuideBuyerRelationOnce() throws WxErrorException {
    this.wxService.getGuideBuyerService().delGuideBuyerRelation(ACCOUNT, null, "oqlk8v0uTJgRnn5eEskNruD4-bc8");
  }

  @Test
  public void testGetGuideBuyerRelationList() throws WxErrorException {
    WxMpGuideBuyerInfoList list = this.wxService.getGuideBuyerService().getGuideBuyerRelationList(ACCOUNT, null, 0, 10);
    assertThat(list).isNotNull();
  }

  @Test
  public void testRebindGuideAcctForBuyer() throws WxErrorException {
    List<String> list = new ArrayList<>();
    list.add("oqlk8v0uTJgRnn5eEskNruD4-bc8");
    list.add("oqlk8vybPMWapMwOfFTFVYqWpGM0");
    List<WxMpGuideBuyerResp> enemytriplekill = this.wxService.getGuideBuyerService().rebindGuideAcctForBuyer(ACCOUNT, null, "enemytriplekill", null, list);
    assertThat(enemytriplekill).isNotNull();
  }

  @Test
  public void testRebindGuideAcctForBuyerOnce() throws WxErrorException {
    this.wxService.getGuideBuyerService().rebindGuideAcctForBuyer(ACCOUNT, null, "enemytriplekill", null, "oqlk8v0uTJgRnn5eEskNruD4-bc8");
  }

  @Test
  public void testUpdateGuideBuyerRelation() throws WxErrorException {
    this.wxService.getGuideBuyerService().updateGuideBuyerRelation(ACCOUNT, null, "oqlk8v0uTJgRnn5eEskNruD4-bc8", "微信文档有坑");
  }

  @Test
  public void testGetGuideBuyerRelationByBuyer() throws WxErrorException {
    WxMpGuideBuyerRelation guideBuyerRelationByBuyer = this.wxService.getGuideBuyerService().getGuideBuyerRelationByBuyer("oqlk8v0uTJgRnn5eEskNruD4-bc8");
    assertThat(guideBuyerRelationByBuyer).isNotNull();
  }

  @Test
  public void testGetGuideBuyerRelation() throws WxErrorException {
    WxMpGuideBuyerInfo guideBuyerRelation = this.wxService.getGuideBuyerService().getGuideBuyerRelation(ACCOUNT, null, "oqlk8v0uTJgRnn5eEskNruD4-bc8");
    assertThat(guideBuyerRelation).isNotNull();
  }

}
