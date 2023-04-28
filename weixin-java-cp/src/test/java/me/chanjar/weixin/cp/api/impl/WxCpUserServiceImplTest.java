package me.chanjar.weixin.cp.api.impl;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.Gender;
import me.chanjar.weixin.cp.bean.WxCpInviteResult;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.user.WxCpDeptUserResult;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

/**
 * <pre>
 *  Created by BinaryWang on 2017/6/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Slf4j
@Guice(modules = ApiTestModule.class)
public class WxCpUserServiceImplTest {
  @Inject
  private WxCpService wxCpService;
  private final String userId = "someone" + System.currentTimeMillis();

  /**
   * Test authenticate.
   *
   * @throws Exception the exception
   */
  @Test
  public void testAuthenticate() throws Exception {
    this.wxCpService.getUserService().authenticate("abc");
  }

  /**
   * Test create.
   *
   * @throws Exception the exception
   */
  @Test
  public void testCreate() throws Exception {
    WxCpUser user = new WxCpUser();
    user.setUserId(userId);
    user.setName("Some Woman");
    user.setDepartIds(new Long[]{2L});
    user.setEmail("none@none.com");
    user.setGender(Gender.FEMALE);
    user.setMobile("13560084979");
    user.setPosition("woman");
    user.setTelephone("3300393");
    user.addExtAttr("爱好", "table");
    this.wxCpService.getUserService().create(user);
  }

  /**
   * Test update.
   *
   * @throws Exception the exception
   */
  @Test(dependsOnMethods = "testCreate")
  public void testUpdate() throws Exception {
    WxCpUser user = new WxCpUser();
    user.setUserId(userId);
    user.setName("Some Woman");
    user.addExtAttr("爱好", "table2");
    this.wxCpService.getUserService().update(user);
  }

  /**
   * Test delete.
   *
   * @throws Exception the exception
   */
  @Test(dependsOnMethods = {"testCreate", "testUpdate"})
  public void testDelete() throws Exception {
    this.wxCpService.getUserService().delete(userId);
  }

  /**
   * Test get by id.
   *
   * @throws Exception the exception
   */
  @Test(dependsOnMethods = "testUpdate")
  public void testGetById() throws Exception {
    WxCpUser user = this.wxCpService.getUserService().getById(userId);
    assertNotNull(user);
  }

  /**
   * Test list by department.
   *
   * @throws Exception the exception
   */
  @Test
  public void testListByDepartment() throws Exception {
    List<WxCpUser> users = this.wxCpService.getUserService().listByDepartment(2L, true, 0);
    assertNotEquals(users.size(), 0);
    for (WxCpUser user : users) {
      System.out.println(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
    }
  }

  /**
   * Test list simple by department.
   *
   * @throws Exception the exception
   */
  @Test
  public void testListSimpleByDepartment() throws Exception {
    List<WxCpUser> users = this.wxCpService.getUserService().listSimpleByDepartment(1L, true, 0);
    assertNotEquals(users.size(), 0);
    for (WxCpUser user : users) {
      System.out.println(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
    }
  }

  /**
   * Test invite.
   *
   * @throws Exception the exception
   */
  @Test
  public void testInvite() throws Exception {
    WxCpInviteResult result = this.wxCpService.getUserService().invite(
      Lists.newArrayList(userId), null, null);
    System.out.println(result);
  }

  /**
   * Test user id 2 openid.
   *
   * @throws Exception the exception
   */
  @Test
  public void testUserId2Openid() throws Exception {
    Map<String, String> result = this.wxCpService.getUserService().userId2Openid(userId, null);
    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test openid 2 user id.
   *
   * @throws Exception the exception
   */
  @Test
  public void testOpenid2UserId() throws Exception {
    String result = this.wxCpService.getUserService().openid2UserId(userId);
    System.out.println(result);
    assertNotNull(result);
  }


  /**
   * Test get user id.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetUserId() throws WxErrorException {
    String result = this.wxCpService.getUserService().getUserId("xxx");
    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test get user id by email.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetUserIdByEmail() throws WxErrorException {
    String result = this.wxCpService.getUserService().getUserIdByEmail("xxx",1);
    System.out.println(result);
    assertNotNull(result);
  }

  /**
   * Test get external contact.
   */
  @Test
  public void testGetExternalContact() {
  }

  /**
   * Test get active stat.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetActiveStat() throws WxErrorException {
    Integer activeStat = this.wxCpService.getUserService().getActiveStat(new Date());
    System.out.printf("active stat: %d", activeStat);
    assertNotNull(activeStat);
  }

  /**
   * 获取成员ID列表
   * 获取企业成员的userid与对应的部门ID列表，预计于2022年8月8号发布。若需要获取其他字段，参见「适配建议」。
   * <p>
   * https://developer.work.weixin.qq.com/document/40856
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetUserListId() throws WxErrorException {
    WxCpDeptUserResult result = this.wxCpService.getUserService().getUserListId(null, 10);
    log.info("返回结果为：{}", result.toJson());
    assertNotNull(result);
  }

}
