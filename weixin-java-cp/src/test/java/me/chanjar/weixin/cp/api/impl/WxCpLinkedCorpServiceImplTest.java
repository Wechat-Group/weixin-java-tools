package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.linkedcorp.WxCpLinkedCorpAgentPerm;
import me.chanjar.weixin.cp.bean.linkedcorp.WxCpLinkedCorpDepartment;
import me.chanjar.weixin.cp.bean.linkedcorp.WxCpLinkedCorpUser;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertNotNull;

/**
 * @Project: WxJava
 * @Package: me.chanjar.weixin.cp.api.impl
 * @Description:
 * @Author: libo
 * @Email: 422423229@qq.com
 * @Date: 28/2/2023 7:06 PM
 */
@Guice(modules = ApiTestModule.class)
public class WxCpLinkedCorpServiceImplTest {
  @Inject
  private WxCpService wxService;

  @Test
  public void testGetLinkedCorpAgentPerm() throws WxErrorException {
    WxCpLinkedCorpAgentPerm resp = wxService.getLinkedCorpService().getLinkedCorpAgentPerm();
    assertNotNull(resp);
  }

  @Test
  public void testGetLinkedCorpUser() throws WxErrorException {
    String userId = "ww7a964c28b8187ec4/LIBO";
    WxCpLinkedCorpUser resp = wxService.getLinkedCorpService().getLinkedCorpUser(userId);
    assertNotNull(resp);
  }

  @Test
  public void testGetLinkedCorpSimpleUserList() throws WxErrorException {
    String departmentId = "";
    List<WxCpLinkedCorpUser> resp = wxService.getLinkedCorpService().getLinkedCorpSimpleUserList(departmentId);
    assertNotNull(resp);
  }

  @Test
  public void testGetLinkedCorpUserList() throws WxErrorException {
    String departmentId = "";
    List<WxCpLinkedCorpUser> resp = wxService.getLinkedCorpService().getLinkedCorpUserList(departmentId);
    assertNotNull(resp);
  }

  @Test
  public void testGetLinkedCorpDepartmentList() throws WxErrorException {
    String departmentId = "";
    List<WxCpLinkedCorpDepartment> resp = wxService.getLinkedCorpService().getLinkedCorpDepartmentList(departmentId);
    assertNotNull(resp);
  }

}
