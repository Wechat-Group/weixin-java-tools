package me.chanjar.weixin.cp.api;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.google.inject.Inject;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpDepart;

/**
 * 测试部门接口
 *
 * @author Daniel Qian
 */
@Test(groups = "departAPI", dependsOnGroups = "baseAPI")
@Guice(modules = ApiTestModule.class)
public class WxCpDepartAPITest {

  @Inject
  protected WxCpServiceImpl wxCpService;

  protected WxCpDepart depart;

  public void testDepartCreate() throws WxErrorException {
    WxCpDepart cpDepart = new WxCpDepart();
    cpDepart.setName("子部门" + System.currentTimeMillis());
    cpDepart.setParentId(1);
    cpDepart.setOrder(1);
    Integer departId = this.wxCpService.departCreate(cpDepart);
    System.out.println(departId);
  }

  @Test(dependsOnMethods = "testDepartCreate")
  public void testDepartGet() throws WxErrorException {
    System.out.println("=================获取部门");
    List<WxCpDepart> departList = this.wxCpService.departGet();
    Assert.assertNotNull(departList);
    Assert.assertTrue(departList.size() > 0);
    for (WxCpDepart g : departList) {
      this.depart = g;
      System.out.println(this.depart.getId() + ":" + this.depart.getName());
      Assert.assertNotNull(g.getName());
    }
  }

  @Test(dependsOnMethods = {"testDepartGet", "testDepartCreate"})
  public void testDepartUpdate() throws WxErrorException {
    System.out.println("=================更新部门");
    this.depart.setName("子部门改名" + System.currentTimeMillis());
    this.wxCpService.departUpdate(this.depart);
  }

  @Test(dependsOnMethods = "testDepartUpdate")
  public void testDepartDelete() throws WxErrorException {
    System.out.println("=================删除部门");
    System.out.println(this.depart.getId() + ":" + this.depart.getName());
    this.wxCpService.departDelete(this.depart.getId());
  }

}
