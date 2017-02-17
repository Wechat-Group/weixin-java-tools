package me.chanjar.weixin.cp.api;

import com.google.inject.Inject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import me.chanjar.weixin.cp.bean.WxCpUser;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 测试用户接口
 *
 * @author Daniel Qian
 */
@Test(groups = "userAPI", dependsOnGroups = "baseAPI")
@Guice(modules = ApiTestModule.class)
public class WxCpUserAPITest {

  @Inject
  protected WxCpServiceImpl wxCpService;

  protected WxCpDepart depart;

  public void testUserCreate() throws WxErrorException {
    WxCpUser user = new WxCpUser();
    user.setUserId("some.woman");
    user.setName("Some Woman");
    user.setDepartIds(new Integer[]{9, 8});
    user.setEmail("none@none.com");
    user.setGender("女");
    user.setMobile("13560084979");
    user.setPosition("woman");
    user.setTel("3300393");
    user.addExtAttr("爱好", "table");
    this.wxCpService.userCreate(user);
  }

  @Test(dependsOnMethods = "testUserCreate")
  public void testUserUpdate() throws WxErrorException {
    WxCpUser user = new WxCpUser();
    user.setUserId("some.woman");
    user.setName("Some Woman");
    user.addExtAttr("爱好", "table2");
    this.wxCpService.userUpdate(user);
  }

  @Test(dependsOnMethods = "testUserUpdate")
  public void testUserGet() throws WxErrorException {
    WxCpUser user = this.wxCpService.userGet("some.woman");
    Assert.assertNotNull(user);
  }

  @Test(dependsOnMethods = "testUserGet")
  public void testDepartGetUsers() throws WxErrorException {
    List<WxCpUser> users = this.wxCpService.departGetUsers(1, true, 0);
    Assert.assertNotEquals(users.size(), 0);
  }

  @Test(dependsOnMethods = "testDepartGetUsers")
  public void testUserDelete() throws WxErrorException {
    this.wxCpService.userDelete("some.woman");
  }
}
