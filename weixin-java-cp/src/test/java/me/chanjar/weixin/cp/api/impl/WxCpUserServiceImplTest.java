package me.chanjar.weixin.cp.api.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.testng.annotations.*;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.Gender;
import me.chanjar.weixin.cp.bean.WxCpInviteResult;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.WxCpUserExternalContactInfo;

import static org.testng.Assert.*;

/**
 * <pre>
 *  Created by BinaryWang on 2017/6/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxCpUserServiceImplTest {
  @Inject
  private WxCpService wxCpService;
  private String userId = "someone" + System.currentTimeMillis();

  @Test
  public void testAuthenticate() throws Exception {
    this.wxCpService.getUserService().authenticate("abc");
  }

  @Test
  public void testCreate() throws Exception {
    WxCpUser user = new WxCpUser();
    user.setUserId(userId);
    user.setName("Some Woman");
    user.setDepartIds(new Integer[]{2});
    user.setEmail("none@none.com");
    user.setGender(Gender.FEMALE);
    user.setMobile("13560084979");
    user.setPosition("woman");
    user.setTelephone("3300393");
    user.addExtAttr("爱好", "table");
    this.wxCpService.getUserService().create(user);
  }

  @Test(dependsOnMethods = "testCreate")
  public void testUpdate() throws Exception {
    WxCpUser user = new WxCpUser();
    user.setUserId(userId);
    user.setName("Some Woman");
    user.addExtAttr("爱好", "table2");
    this.wxCpService.getUserService().update(user);
  }

  @Test(dependsOnMethods = {"testCreate", "testUpdate"})
  public void testDelete() throws Exception {
    this.wxCpService.getUserService().delete(userId);
  }

  @Test(dependsOnMethods = "testUpdate")
  public void testGetById() throws Exception {
    WxCpUser user = this.wxCpService.getUserService().getById(userId);
    assertNotNull(user);
  }

  @Test
  public void testListByDepartment() throws Exception {
    List<WxCpUser> users = this.wxCpService.getUserService().listByDepartment(1, true, 0);
    assertNotEquals(users.size(), 0);
    for (WxCpUser user : users) {
      System.out.println(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
    }
  }

  @Test
  public void testListSimpleByDepartment() throws Exception {
    List<WxCpUser> users = this.wxCpService.getUserService().listSimpleByDepartment(1, true, 0);
    assertNotEquals(users.size(), 0);
    for (WxCpUser user : users) {
      System.out.println(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
    }
  }

  @Test
  public void testInvite() throws Exception {
    WxCpInviteResult result = this.wxCpService.getUserService().invite(
      Lists.newArrayList(userId), null,null);
    System.out.println(result);
  }

  @Test
  public void testUserId2Openid() throws Exception {
    Map<String, String> result = this.wxCpService.getUserService().userId2Openid(userId, null);
    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  public void testOpenid2UserId() throws Exception {
    String result = this.wxCpService.getUserService().openid2UserId(userId);
    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  public void testGetExternalContact() throws WxErrorException {
    WxCpUserExternalContactInfo result = this.wxCpService.getUserService().getExternalContact(userId);
    System.out.println(result);
    assertNotNull(result);
  }
}
