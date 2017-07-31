package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpUserQuery;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.testng.*;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试用户相关的接口
 *
 * @author chanjarster
 * @author Binary Wang
 */
@Test(groups = "userAPI")
@Guice(modules = ApiTestModule.class)
public class WxMpUserServiceImplTest {

  @Inject
  private WxMpService wxService;

  private TestConfigStorage configProvider;

  @BeforeTest
  public void setup() {
    this.configProvider = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();
  }

  public void testUserUpdateRemark() throws WxErrorException {
    this.wxService.getUserService()
      .userUpdateRemark(this.configProvider.getOpenid(), "测试备注名");
  }

  public void testUserInfo() throws WxErrorException {
    WxMpUser user = this.wxService.getUserService()
      .userInfo(this.configProvider.getOpenid(), null);
    Assert.assertNotNull(user);
    System.out.println(user);
  }

  public void testUserInfoList() throws WxErrorException {
    List<String> openids = new ArrayList<>();
    openids.add(this.configProvider.getOpenid());
    List<WxMpUser> userList = this.wxService.getUserService()
      .userInfoList(openids);
    Assert.assertEquals(userList.size(), 1);
    System.out.println(userList);
  }

  public void testUserInfoListByWxMpUserQuery() throws WxErrorException {
    WxMpUserQuery query = new WxMpUserQuery();
    query.add(this.configProvider.getOpenid(), "zh_CN");
    List<WxMpUser> userList = this.wxService.getUserService()
      .userInfoList(query);
    Assert.assertEquals(userList.size(), 1);
    System.out.println(userList);
  }

  public void testUserList() throws WxErrorException {
    WxMpUserList wxMpUserList = this.wxService.getUserService().userList(null);
    Assert.assertNotNull(wxMpUserList);
    Assert.assertFalse(wxMpUserList.getCount() == -1);
    Assert.assertFalse(wxMpUserList.getTotal() == -1);
    Assert.assertFalse(wxMpUserList.getOpenids().size() == -1);
    System.out.println(wxMpUserList);
  }

}
