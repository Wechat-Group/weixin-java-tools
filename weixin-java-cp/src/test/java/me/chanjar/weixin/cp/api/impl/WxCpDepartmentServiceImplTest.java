package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
    cpDepart.setParentId(1L);
    cpDepart.setOrder(1L);
    Long departId = this.wxCpService.getDepartmentService().create(cpDepart);
    System.out.println(departId);
  }

  @DataProvider
  public Object[][] departIds() {
    return new Object[][]{
      {null},
      {12L},
      {5L}
    };
  }

  @Test(dataProvider = "departIds")
  public void testList(Long id) throws Exception {
    System.out.println("=================获取部门");
    List<WxCpDepart> departList = this.wxCpService.getDepartmentService().list(id);
    assertThat(departList).isNotEmpty();
    for (WxCpDepart g : departList) {
      this.depart = g;
      System.out.println(this.depart.getId() + ":" + this.depart.getName());
      assertThat(g.getName()).isNotBlank();
    }
  }

  @Test(dependsOnMethods = {"testList", "testCreate"})
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

  @Test(dataProvider = "departIds")
  public void testSimpleList(Long id) throws WxErrorException {
    System.out.println("=================获取子部门ID列表");
    List<WxCpDepart> departList = this.wxCpService.getDepartmentService().simpleList(id);
    assertThat(departList).isNotEmpty();
    departList.forEach(System.out::println);
  }

  @Test(dataProvider = "departIds")
  public void testGet(Long id) throws WxErrorException {
    if (id == null) {
      return;
    }

    WxCpDepart depart = this.wxCpService.getDepartmentService().get(id);
    assertThat(depart).isNotNull();
    System.out.println(depart);
  }
}
