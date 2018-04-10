package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.bean.WxCardApiSignature;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.result.WxMpCardResult;
import org.testng.annotations.*;

import static org.testng.AssertJUnit.*;

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

}
