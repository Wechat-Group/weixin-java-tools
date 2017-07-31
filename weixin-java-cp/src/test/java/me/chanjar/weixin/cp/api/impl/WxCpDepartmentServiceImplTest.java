package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * <pre>
 *  Created by BinaryWang on 2017/6/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxCpDepartmentServiceImplTest {
  @Inject
  private WxCpService wxCpService;

  private WxCpDepart depart;

  @Test
  public void testCreate() throws Exception {
    WxCpDepart cpDepart = new WxCpDepart();
    cpDepart.setName("子部门" + System.currentTimeMillis());
    cpDepart.setParentId(1);
    cpDepart.setOrder(1);
    Integer departId = this.wxCpService.getDepartmentService().create(cpDepart);
    System.out.println(departId);
  }

  @Test
  public void testListAll() throws Exception {
    System.out.println("=================获取部门");
    List<WxCpDepart> departList = this.wxCpService.getDepartmentService().listAll();
    assertNotNull(departList);
    assertTrue(departList.size() > 0);
    for (WxCpDepart g : departList) {
      this.depart = g;
      System.out.println(this.depart.getId() + ":" + this.depart.getName());
      assertNotNull(g.getName());
    }
  }

  @Test(dependsOnMethods = {"testListAll", "testCreate"})
  public void testUpdate() throws Exception {
    System.out.println("=================更新部门");
    this.depart.setName("子部门改名" + System.currentTimeMillis());
    this.wxCpService.getDepartmentService().update(this.depart);
  }

  @Test(dependsOnMethods = "testUpdate")
  public void testDelete() throws Exception {
    System.out.println("=================删除部门");
    System.out.println(this.depart.getId() + ":" + this.depart.getName());
    this.wxCpService.getDepartmentService().delete(this.depart.getId());
  }

}
