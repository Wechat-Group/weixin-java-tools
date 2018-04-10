package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.membercard.WxMpMemberCardActivatedMessage;
import me.chanjar.weixin.mp.bean.membercard.WxMpMemberCardUpdateMessage;
import me.chanjar.weixin.mp.bean.membercard.WxMpMemberCardUpdateResult;
import me.chanjar.weixin.mp.bean.membercard.WxMpMemberCardUserInfoResult;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;

/**
 * 会员卡相关接口的测试类。
 * 数据均为测试数据，由于直接与调用微信的接口，需要填写真实数据进行测试才能通过。
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpMemberCardServiceImplTest {

  @Inject
  protected WxMpService wxService;
  private String cardId = "abc";
  private String code = "123";
  private String openId = "xyz";

  @Test
  public void testActivateMemberCard() throws Exception {
    WxMpMemberCardActivatedMessage activatedMessage = new WxMpMemberCardActivatedMessage();
    activatedMessage.setMembershipNumber(openId);
    activatedMessage.setCode(code);
    activatedMessage.setCardId(cardId);
    activatedMessage.setInitBonus(2000);
    activatedMessage.setInitBonusRecord("测试激活送积分");
    String response = this.wxService.getMemberCardService().activateMemberCard(activatedMessage);
    assertNotNull(response);
    System.out.println(response);
  }

  @Test
  public void testGetUserInfo() throws Exception {
    WxMpMemberCardUserInfoResult result = this.wxService.getMemberCardService().getUserInfo(cardId, code);
    assertNotNull(result);
    System.out.println(result);
  }

  @Test
  public void testUpdateUserMemberCard() throws Exception {
    WxMpMemberCardUpdateMessage updateMessage = new WxMpMemberCardUpdateMessage();
    updateMessage.setAddBounus(100);
    updateMessage.setBonus(1000);
    updateMessage.setCardId(cardId);
    updateMessage.setCode(code);
    WxMpMemberCardUpdateResult result = this.wxService.getMemberCardService().updateUserMemberCard(updateMessage);
    assertNotNull(result);
    System.out.println(result);
  }

}
