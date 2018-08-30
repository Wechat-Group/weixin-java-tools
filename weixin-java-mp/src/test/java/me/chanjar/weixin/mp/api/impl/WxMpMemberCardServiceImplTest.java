package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.mp.api.WxMpCardService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.card.*;
import me.chanjar.weixin.mp.bean.card.enums.CardSceneType;
import me.chanjar.weixin.mp.bean.membercard.*;
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
  private String cardId = "p2iQk1kUixiypVJ1lJYIT-_fMdUg";
  private String code = "201808290001";
  private String openId = "o2iQk1u5X-XIJkatmAK1Q8VVuS90";

  @Test
  public void createMemberCard()throws Exception{
//    String json = "{\"card\":{\"card_type\":\"MEMBER_CARD\",\"member_card\":{\"advanced_info\":{\"business_service\":\"BIZ_SERVICE_FREE_PARK,BIZ_SERVICE_WITH_PET,BIZ_SERVICE_FREE_WIFI\",\"text_image_list\":[{\"image_url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/upuF1LhUF8LjCLCFcQicgEiazFeonwDllGkENppDhyqhR8bz5BiaJkPT7e6bPVcfBx5cAOLro2N3U989n8WJltkjQ/0\",\"text\":\"8月8日随机免单\"}]},\"auto_activate\":false,\"background_pic_url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/upuF1LhUF8LjCLCFcQicgEiazFeonwDllGl6ibk4v5iaJDAbs7dGJU7iclOJ6nh7Hnz6ZsfDL8tGEeQVJyuhKsMFxUQ/0\",\"base_info\":{\"bind_openid\":false,\"brand_name\":\"商户名称\",\"can_give_friend\":false,\"can_share\":false,\"center_sub_title\":\"点击进入\",\"center_title\":\"商城首页\",\"center_url\":\"http://www.baidu.com\",\"code_type\":\"CODE_TYPE_QRCODE\",\"color\":\"Color090\",\"date_info\":{\"type\":\"DATE_TYPE_PERMANENT\"},\"description\":\"使用须知\",\"need_push_on_view\":false,\"notice\":\"测试会员卡\",\"service_phone\":\"4008803016\",\"title\":\"终生铂金卡\",\"use_all_locations\":true,\"use_custom_code\":false},\"prerogative\":\"享有特权说明\",\"supply_balance\":true,\"supply_bonus\":true,\"wx_activate\":false}}}";
//    WxMpMemberCardCreateMessage createMessage = WxMpMemberCardCreateMessage.fromJson(json);

    //基本卡券创建
    WxMpMemberCardCreateMessage createMessage = new WxMpMemberCardCreateMessage();
    MemberCardCreateRequest cardCreateRequest = new MemberCardCreateRequest();
    MemberCard memberCard = new MemberCard();
    memberCard.setPrerogative("特权说明");
    //激活方式
    memberCard.setAutoActivate(true);//自动激活
//    memberCard.setActivateUrl("http://www.qq.com");
//    memberCard.setWxActivate(false);//微信激活
    memberCard.setSupplyBonus(true);
    memberCard.setSupplyBalance(false);
    memberCard.setBackgroundPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/upuF1LhUF8LjCLCFcQicgEiazFeonwDllGl6ibk4v5iaJDAbs7dGJU7iclOJ6nh7Hnz6ZsfDL8tGEeQVJyuhKsMFxUQ/0");
    memberCard.setDiscount(0);

    BaseInfo baseInfo = new BaseInfo();
    baseInfo.setLogoUrl("http://wx.qlogo.cn/mmopen/A6hCic476picOEWOJ7NsL7uWhRuh1LibrMC6byhCO6TV1lelyK9iaXbn8nAgFREibPJQTWDeKpicePt88ZHRc8wuicEM0qOllsMXic6O/0");
    baseInfo.setCodeType("CODE_TYPE_QRCODE");
    baseInfo.setBrandName("信舟科技");
    baseInfo.setTitle("铂金用户贵宾卡");
    baseInfo.setColor("Color010");
    baseInfo.setNotice("卡券使用提醒");
    baseInfo.setDescription("卡券使用说明");
    baseInfo.setServicePhone("4008803016");
    //商品信息
    Sku sku = new Sku();
    baseInfo.setSku(sku);
    //使用日期
    DateInfo dateInfo = new DateInfo();
    baseInfo.setDateInfo(dateInfo);

    memberCard.setBaseInfo(baseInfo);

    cardCreateRequest.setMemberCard(memberCard);
    createMessage.setCardCreateRequest(cardCreateRequest);

    WxMpCardCreateResult response = this.wxService.getMemberCardService().createMemberCard(createMessage);
    assertNotNull(response);
    System.out.println(response);
  }

  @Test
  public void testActivateMemberCard() throws Exception {
    WxMpMemberCardActivatedMessage activatedMessage = new WxMpMemberCardActivatedMessage();
    activatedMessage.setMembershipNumber(openId);
//    activatedMessage.setCode(code);
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

  /**
   * 测试添加测试openid白名单
   * @throws Exception
   */
  @Test
  public void testAddTestWhiteList()throws Exception {
    WxMpCardService cardService = this.wxService.getCardService();
    String response = cardService.addTestWhiteList(openId);
    System.out.println(response);
  }

  /**
   * 测试创建会员卡投放二维码
   * @throws Exception
   */
  @Test
  public void testCreateQrcodeMemberCard()throws Exception{
    WxMpCardService cardService = this.wxService.getCardService();
    WxMpCardQrcodeCreateResult response = cardService.createQrcodeCard(cardId,"test");
    System.out.println(response);
  }

  /**
   * 测试创建货架接口
   * @throws Exception
   */
  @Test
  public void testCreateLandingPage()throws Exception{
    WxMpCardService cardService = this.wxService.getCardService();
    WxMpCardLandingPageCreateRequest request = new WxMpCardLandingPageCreateRequest();
    request.setBanner("http://mmbiz.qpic.cn/mmbiz_jpg/upuF1LhUF8LjCLCFcQicgEiazFeonwDllGl6ibk4v5iaJDAbs7dGJU7iclOJ6nh7Hnz6ZsfDL8tGEeQVJyuhKsMFxUQ/0");
    request.setTitle("会员卡1");
    request.setScene(CardSceneType.SCENE_H5.name());
    request.addCard(cardId,"http://mmbiz.qpic.cn/mmbiz_jpg/upuF1LhUF8LjCLCFcQicgEiazFeonwDllGl6ibk4v5iaJDAbs7dGJU7iclOJ6nh7Hnz6ZsfDL8tGEeQVJyuhKsMFxUQ/0");
    WxMpCardLandingPageCreateResult response = cardService.createLandingPage(request);
    System.out.println(response);
  }
}
