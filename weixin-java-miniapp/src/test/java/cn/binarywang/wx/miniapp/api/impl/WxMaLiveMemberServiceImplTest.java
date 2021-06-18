package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.gson.JsonArray;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 测试.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2021-02-15
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaLiveMemberServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testAddRole() throws WxErrorException {
    final String result = this.wxService.getLiveMemberService().addRole("abc", 1);
    System.out.println(result);
  }

  @Test
  public void testDeleteRole() throws WxErrorException {
    final String result = this.wxService.getLiveMemberService().deleteRole("abc", 1);
    System.out.println(result);
  }

  @Test
  public void testListByRole() throws WxErrorException {
    final JsonArray result = this.wxService.getLiveMemberService().listByRole(null, null, null, null);
    System.out.println(result);
  }
}
