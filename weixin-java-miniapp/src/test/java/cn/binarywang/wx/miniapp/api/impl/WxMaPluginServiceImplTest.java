package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaPluginListResult;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

@Test
@Guice(modules = ApiTestModule.class)
public class WxMaPluginServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testApplyPlugin() throws Exception {
    this.wxService.getPluginService().applyPlugin("wx4418e3e031e551be", null);
  }

  @Test
  public void testGetPluginList() throws Exception {
    WxMaPluginListResult result = this.wxService.getPluginService().getPluginList();
    assertNotNull(result);
    System.out.println(result.toString());
  }

  @Test
  public void testUnbindPlugin() throws Exception {
    this.wxService.getPluginService().unbindPlugin("wx4418e3e031e551be");
  }

  @Test
  public void testUpdatePlugin() throws Exception {
    this.wxService.getPluginService().updatePlugin("wx4418e3e031e551be", "2.0.2");
  }
}
