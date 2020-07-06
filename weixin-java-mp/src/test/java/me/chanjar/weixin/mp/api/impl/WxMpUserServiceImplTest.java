package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpUserQuery;
import me.chanjar.weixin.mp.bean.result.WxMpChangeOpenid;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.User.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 测试用户相关的接口
 *
 * @author chanjarster
 * @author Binary Wang
 */
@Test
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
    Assert.assertNotEquals(-1, wxMpUserList.getCount());
    Assert.assertNotEquals(-1, wxMpUserList.getTotal());
    Assert.assertNotEquals(-1, wxMpUserList.getOpenids().size());
    System.out.println(wxMpUserList);
  }

  public void testChangeOpenid() throws WxErrorException {
    List<String> openids = new ArrayList<>();
    openids.add(this.configProvider.getOpenid());
    List<WxMpChangeOpenid> wxMpChangeOpenidList = this.wxService.getUserService().changeOpenid("原公众号appid", openids);
    Assert.assertNotNull(wxMpChangeOpenidList);
    Assert.assertEquals(1, wxMpChangeOpenidList.size());
    WxMpChangeOpenid wxMpChangeOpenid = wxMpChangeOpenidList.get(0);
    Assert.assertNotNull(wxMpChangeOpenid);
    Assert.assertEquals(this.configProvider.getOpenid(), wxMpChangeOpenid.getOriOpenid());
    System.out.println(wxMpChangeOpenid);
  }

  public static class MockTest {
    private WxMpService wxService = mock(WxMpService.class);

    @Test
    public void testMockChangeOpenid() throws WxErrorException {
      List<String> openids = new ArrayList<>();
      openids.add("oEmYbwN-n24jxvk4Sox81qedINkQ");
      openids.add("oEmYbwH9uVd4RKJk7ZZg6SzL6tTo");
      String fromAppid = "old_appid";
      Map<String, Object> map = new HashMap<>();
      map.put("from_appid", fromAppid);
      map.put("openid_list", openids);

      String returnJson = "{\"errcode\": 0,\"errmsg\": \"ok\",\"result_list\": [{\"ori_openid\": \"oEmYbwN-n24jxvk4Sox81qedINkQ\",\"new_openid\": \"o2FwqwI9xCsVadFah_HtpPfaR-X4\",\"err_msg\": \"ok\"},{\"ori_openid\": \"oEmYbwH9uVd4RKJk7ZZg6SzL6tTo\",\"err_msg\": \"ori_openid error\"}]}";
      when(wxService.post(USER_CHANGE_OPENID_URL, WxMpGsonBuilder.create().toJson(map))).thenReturn(returnJson);
      List<WxMpChangeOpenid> wxMpChangeOpenidList = this.wxService.getUserService().changeOpenid(fromAppid, openids);
      Assert.assertNotNull(wxMpChangeOpenidList);
      Assert.assertEquals(2, wxMpChangeOpenidList.size());
      WxMpChangeOpenid wxMpChangeOpenid = wxMpChangeOpenidList.get(0);
      Assert.assertNotNull(wxMpChangeOpenid);
      Assert.assertEquals("oEmYbwN-n24jxvk4Sox81qedINkQ", wxMpChangeOpenid.getOriOpenid());
      Assert.assertEquals("o2FwqwI9xCsVadFah_HtpPfaR-X4", wxMpChangeOpenid.getNewOpenid());
      Assert.assertEquals("ok", wxMpChangeOpenid.getErrMsg());
      wxMpChangeOpenid = wxMpChangeOpenidList.get(1);
      Assert.assertNotNull(wxMpChangeOpenid);
      Assert.assertEquals("oEmYbwH9uVd4RKJk7ZZg6SzL6tTo", wxMpChangeOpenid.getOriOpenid());
      Assert.assertNull(wxMpChangeOpenid.getNewOpenid());
      Assert.assertEquals("ori_openid error", wxMpChangeOpenid.getErrMsg());
      System.out.println(wxMpChangeOpenid);
    }

  }

}
