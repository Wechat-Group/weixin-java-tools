package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConfigStorage;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.kefu.request.WxMpKfAccountRequest;
import me.chanjar.weixin.mp.bean.kefu.result.*;
import org.joda.time.DateTime;
import org.testng.*;
import org.testng.annotations.*;

import java.io.File;
import java.util.Date;

/**
 * 测试客服相关接口
 *
 * @author Binary Wang
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpKefuServiceImplTest {

  @Inject
  protected WxMpService wxService;

  public void testSendKefuMpNewsMessage() throws WxErrorException {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();
    WxMpKefuMessage message = new WxMpKefuMessage();
    message.setMsgType(WxConsts.CUSTOM_MSG_MPNEWS);
    message.setToUser(configStorage.getOpenid());
    message.setMpNewsMediaId("52R6dL2FxDpM9N1rCY3sYBqHwq-L7K_lz1sPI71idMg");

    this.wxService.getKefuService().sendKefuMessage(message);
  }

  public void testSendKefuMessage() throws WxErrorException {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();
    WxMpKefuMessage message = new WxMpKefuMessage();
    message.setMsgType(WxConsts.CUSTOM_MSG_TEXT);
    message.setToUser(configStorage.getOpenid());
    message.setContent(
      "欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>");

    this.wxService.getKefuService().sendKefuMessage(message);
  }

  public void testSendKefuMessageWithKfAccount() throws WxErrorException {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();
    WxMpKefuMessage message = new WxMpKefuMessage();
    message.setMsgType(WxConsts.CUSTOM_MSG_TEXT);
    message.setToUser(configStorage.getOpenid());
    message.setKfAccount(configStorage.getKfAccount());
    message.setContent(
      "欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>");

    this.wxService.getKefuService().sendKefuMessage(message);
  }

  public void testKfList() throws WxErrorException {
    WxMpKfList kfList = this.wxService.getKefuService().kfList();
    Assert.assertNotNull(kfList);
    for (WxMpKfInfo k : kfList.getKfList()) {
      System.err.println(k);
    }
  }

  public void testKfOnlineList() throws WxErrorException {
    WxMpKfOnlineList kfOnlineList = this.wxService.getKefuService()
      .kfOnlineList();
    Assert.assertNotNull(kfOnlineList);
    for (WxMpKfInfo k : kfOnlineList.getKfOnlineList()) {
      System.err.println(k);
    }
  }

  @DataProvider
  public Object[][] getKfAccount() {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();
    return new Object[][]{{configStorage.getKfAccount()}};
  }

  @Test(dataProvider = "getKfAccount")
  public void testKfAccountAdd(String kfAccount) throws WxErrorException {
    WxMpKfAccountRequest request = WxMpKfAccountRequest.builder()
      .kfAccount(kfAccount).nickName("我晕").build();
    Assert.assertTrue(this.wxService.getKefuService().kfAccountAdd(request));
  }

  @Test(dependsOnMethods = {
    "testKfAccountAdd"}, dataProvider = "getKfAccount")
  public void testKfAccountUpdate(String kfAccount) throws WxErrorException {
    WxMpKfAccountRequest request = WxMpKfAccountRequest.builder()
      .kfAccount(kfAccount).nickName("我晕").build();
    Assert.assertTrue(this.wxService.getKefuService().kfAccountUpdate(request));
  }

  @Test(dependsOnMethods = {
    "testKfAccountAdd"}, dataProvider = "getKfAccount")
  public void testKfAccountInviteWorker(String kfAccount) throws WxErrorException {
    WxMpKfAccountRequest request = WxMpKfAccountRequest.builder()
      .kfAccount(kfAccount).inviteWx("    ").build();
    Assert.assertTrue(this.wxService.getKefuService().kfAccountInviteWorker(request));
  }

  @Test(dependsOnMethods = {
    "testKfAccountUpdate"}, dataProvider = "getKfAccount")
  public void testKfAccountUploadHeadImg(String kfAccount)
    throws WxErrorException {
    File imgFile = new File("src\\test\\resources\\mm.jpeg");
    boolean result = this.wxService.getKefuService()
      .kfAccountUploadHeadImg(kfAccount, imgFile);
    Assert.assertTrue(result);
  }

  @Test(dataProvider = "getKfAccount")
  public void testKfAccountDel(String kfAccount) throws WxErrorException {
    boolean result = this.wxService.getKefuService().kfAccountDel(kfAccount);
    Assert.assertTrue(result);
  }

  @DataProvider
  public Object[][] getKfAccountAndOpenid() {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();
    return new Object[][]{
      {configStorage.getKfAccount(), configStorage.getOpenid()}};
  }

  @Test(dataProvider = "getKfAccountAndOpenid")
  public void testKfSessionCreate(String kfAccount, String openid)
    throws WxErrorException {
    boolean result = this.wxService.getKefuService().kfSessionCreate(openid,
      kfAccount);
    Assert.assertTrue(result);
  }

  @Test(dataProvider = "getKfAccountAndOpenid")
  public void testKfSessionClose(String kfAccount, String openid)
    throws WxErrorException {
    boolean result = this.wxService.getKefuService().kfSessionClose(openid,
      kfAccount);
    Assert.assertTrue(result);
  }

  @Test(dataProvider = "getKfAccountAndOpenid")
  public void testKfSessionGet(@SuppressWarnings("unused") String kfAccount,
                               String openid) throws WxErrorException {
    WxMpKfSessionGetResult result = this.wxService.getKefuService()
      .kfSessionGet(openid);
    Assert.assertNotNull(result);
    System.err.println(result);
  }

  @Test(dataProvider = "getKfAccount")
  public void testKfSessionList(String kfAccount) throws WxErrorException {
    WxMpKfSessionList result = this.wxService.getKefuService()
      .kfSessionList(kfAccount);
    Assert.assertNotNull(result);
    System.err.println(result);
  }

  @Test
  public void testKfSessionGetWaitCase() throws WxErrorException {
    WxMpKfSessionWaitCaseList result = this.wxService.getKefuService()
      .kfSessionGetWaitCase();
    Assert.assertNotNull(result);
    System.err.println(result);
  }

  @Test
  public void testKfMsgList() throws WxErrorException {
    Date startTime = DateTime.now().minusDays(1).toDate();
    Date endTime = DateTime.now().minusDays(0).toDate();
    WxMpKfMsgList result = this.wxService.getKefuService().kfMsgList(startTime, endTime, 1L, 50);
    Assert.assertNotNull(result);
    System.err.println(result);
  }

  @Test
  public void testKfMsgListAll() throws WxErrorException {
    Date startTime = DateTime.now().minusDays(1).toDate();
    Date endTime = DateTime.now().minusDays(0).toDate();
    WxMpKfMsgList result = this.wxService.getKefuService().kfMsgList(startTime, endTime);
    Assert.assertNotNull(result);
    System.err.println(result);
  }
}
