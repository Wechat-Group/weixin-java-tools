package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaDomainAction;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-27 15:38
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaSettingServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testModifyDomain() throws Exception {
    WxMaDomainAction domainAction = wxService.getSettingService().modifyDomain(WxMaDomainAction
      .builder()
      .action("get")
      .build());
    System.out.println(domainAction);
    assertNotNull(domainAction);

    domainAction.setAction("set");
    WxMaDomainAction result = wxService.getSettingService().modifyDomain(domainAction);
    System.out.println(result);
  }

  @Test
  public void testBindTester() throws Exception {
    wxService.getSettingService().bindTester("WeChatId");
  }

  @Test
  public void testUnbindTester() throws Exception {
    wxService.getSettingService().unbindTester("WeChatId");
  }

  @Test
  public void testSetWebViewDomain() throws Exception {
    WxMaDomainAction domainAction = wxService.getSettingService().setWebViewDomain(WxMaDomainAction
      .builder()
      .action("get")
      .build());
    System.out.println(domainAction);
  }
}
