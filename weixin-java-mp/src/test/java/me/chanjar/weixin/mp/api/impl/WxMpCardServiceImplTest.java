package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.bean.WxCardApiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.card.*;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * 测试代码仅供参考，未做严格测试，因原接口作者并未提供单元测试代码
 * Created by Binary Wang on 2016/7/27.
 *
 * @author binarywang (https://github.com/binarywang)
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpCardServiceImplTest {

  @Inject
  protected WxMpService wxService;
  private String cardId = "123";
  private String code = "good";
  private String openid = "abc";

  @Test
  public void testGetCardApiTicket() throws Exception {
    String cardApiTicket = this.wxService.getCardService().getCardApiTicket();
    assertNotNull(cardApiTicket);
    System.out.println(cardApiTicket);
  }

  @Test
  public void testGetCardApiTicketWithParam() throws Exception {
    String cardApiTicket = this.wxService.getCardService().getCardApiTicket(true);
    assertNotNull(cardApiTicket);
    System.out.println(cardApiTicket);
  }

  @Test
  public void testCreateCardApiSignature() throws Exception {
    //app_id, card_id, card_type, code, openid, location_id

    String[] param = {"123", this.cardId, "", this.code, this.openid, ""};
    WxCardApiSignature cardApiSignature = this.wxService.getCardService().createCardApiSignature(param);
    assertNotNull(cardApiSignature);
    System.out.println(cardApiSignature);
  }

  @Test
  public void testDecryptCardCode() throws Exception {
    String encryptCode = "pd0vTUHSHc9tMUCL2gXABcUmINm6yxqJh0y9Phsy63E=";
    String cardCode = this.wxService.getCardService().decryptCardCode(encryptCode);
    assertNotNull(cardCode);
    System.out.println(cardCode);
  }

  @Test
  public void testQueryCardCode() throws Exception {
    WxMpCardResult wxMpCardResult = this.wxService.getCardService().queryCardCode(this.cardId, this.code, false);
    assertNotNull(wxMpCardResult);
    System.out.println(wxMpCardResult);
  }

  @Test
  public void testConsumeCardCode() throws Exception {
    String result = this.wxService.getCardService().consumeCardCode(this.code);
    assertNotNull(result);
    System.out.println(result);
  }

  @Test
  public void testConsumeCardCodeWithCardId() throws Exception {
    String result = this.wxService.getCardService().consumeCardCode(this.code, this.cardId);
    assertNotNull(result);
    System.out.println(result);
  }

  @Test
  public void testMarkCardCode() throws Exception {
    this.wxService.getCardService().markCardCode(this.code, this.cardId, this.openid, true);
    System.out.println("done");
  }

  @Test
  public void testGetCardDetail() throws Exception {
    String result = this.wxService.getCardService().getCardDetail(this.cardId);
    assertNotNull(result);
    System.out.println(result);
  }

  @Test
  public void testUnavailableCardCode() throws Exception {
    String cardId = "p2iQk1luzj50RHue6yeTPQpAx_Z4";
    String code = "134905347310";
    String reason = "换成新卡了";
    String result = this.wxService.getCardService().unavailableCardCode(cardId, code, reason);
    assertNotNull(result);
    System.out.println(result);
  }

  @Test
  public void testCreateGrouponCard() throws WxErrorException {

    BaseInfo base = new BaseInfo();
    base.setLogoUrl("http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0");
    base.setBrandName("测试优惠券");
    base.setCodeType("CODE_TYPE_QRCODE");
    base.setTitle("测试标题");
    base.setColor("Color010");
    base.setNotice("测试Notice");
    base.setServicePhone("020-88888888");
    base.setDescription("不可与其他优惠同享\\n如需团购券发票，请在消费时向商户提出\\n店内均可使用，仅限堂食");
    DateInfo info = new DateInfo();
    info.setType("DATE_TYPE_FIX_TERM");
    info.setFixedBeginTerm(0);
    info.setFixedTerm(30);
    base.setDateInfo(info);
    Sku sku = new Sku();
    sku.setQuantity(100);
    base.setSku(sku);
    base.setGetLimit(1);
    base.setCanShare(true);
    base.setCanGiveFriend(true);
    base.setUseAllLocations(true);
    base.setCenterTitle("顶部居中按钮");
    base.setCenterSubTitle("按钮下方的wording");
    base.setCenterUrl("www.qq.com");
    base.setCustomUrl("http://www.qq.com");
    base.setCustomUrlName("立即使用");
    base.setCustomUrlSubTitle("副标题tip");
    base.setPromotionUrlName("更多优惠");
    base.setPromotionUrl("http://www.qq.com");
    base.setLocationIdList("1234");

    //团购券
    WxMpCardCreateMessage grouponMessage = new WxMpCardCreateMessage();
    GrouponCardCreateRequest grouponCardCreateRequest = new GrouponCardCreateRequest();
    GrouponCard grouponCard = new GrouponCard();
    grouponCard.setBaseInfo(base);
    grouponCard.setDealDetail("deal detail");

    grouponCardCreateRequest.setGroupon(grouponCard);
    grouponMessage.setCardCreateRequest(grouponCardCreateRequest);

    System.out.println(this.wxService.getCardService().createCard(grouponMessage));

    //现金券
    WxMpCardCreateMessage cashMessage = new WxMpCardCreateMessage();
    CashCardCreateRequest cashCardCreateRequest = new CashCardCreateRequest();
    CashCard cashCard = new CashCard();
    cashCard.setBaseInfo(base);
    cashCard.setLeastCost(1000);
    cashCard.setReduceCost(100);

    cashCardCreateRequest.setCash(cashCard);
    cashMessage.setCardCreateRequest(cashCardCreateRequest);

    System.out.println(this.wxService.getCardService().createCard(cashMessage));

    //折扣券
    WxMpCardCreateMessage discountMessage = new WxMpCardCreateMessage();
    DiscountCardCreateRequest discountCardCreateRequest = new DiscountCardCreateRequest();
    DiscountCard discountCard = new DiscountCard();
    discountCard.setBaseInfo(base);
    discountCard.setDiscount(30);

    discountCardCreateRequest.setDiscount(discountCard);
    discountMessage.setCardCreateRequest(discountCardCreateRequest);

    System.out.println(this.wxService.getCardService().createCard(discountMessage));

    //兑换券
    WxMpCardCreateMessage giftMessage = new WxMpCardCreateMessage();
    GiftCardCreateRequest giftCardCreateRequest = new GiftCardCreateRequest();
    GiftCard giftCard = new GiftCard();
    giftCard.setBaseInfo(base);
    giftCard.setGift("星巴克雪瑞纳咖啡大杯");

    giftCardCreateRequest.setGift(giftCard);
    giftMessage.setCardCreateRequest(giftCardCreateRequest);
    System.out.println(this.wxService.getCardService().createCard(giftMessage));

    //普通兑换券
    WxMpCardCreateMessage generalMessage = new WxMpCardCreateMessage();
    GeneralCardCreateRequest generalCardCreateRequest = new GeneralCardCreateRequest();
    GeneralCard generalCard = new GeneralCard();
    generalCard.setBaseInfo(base);
    generalCard.setDefaultDetail("音乐木盒");

    generalCardCreateRequest.setGeneralCoupon(generalCard);
    generalMessage.setCardCreateRequest(generalCardCreateRequest);
    System.out.println(this.wxService.getCardService().createCard(generalMessage));
  }

  @Test
  public void testDeleteCard() throws Exception {
    String cardId = "pwkrWjtw7W4_l50kCQcZ1in1yS6g";
    WxMpCardDeleteResult result = this.wxService.getCardService().deleteCard(cardId);
    assertEquals(result.isSuccess(), true);
    System.out.println(result);
  }

}
