package me.chanjar.weixin.mp.api.impl;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConfigStorage;
import me.chanjar.weixin.mp.bean.result.WxMpUserBlacklistGetResult;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author miller
 */
@Test(groups = "userAPI")
@Guice(modules = ApiTestModule.class)
public class WxMpUserBlacklistServiceImplTest {
  @Inject
  protected WxMpService wxService;

  @Test
  public void testGetBlacklist() throws Exception {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();
    WxMpUserBlacklistGetResult wxMpUserBlacklistGetResult = this.wxService.getBlackListService().getBlacklist(configStorage.getOpenid());
    Assert.assertNotNull(wxMpUserBlacklistGetResult);
    Assert.assertFalse(wxMpUserBlacklistGetResult.getCount() == -1);
    Assert.assertFalse(wxMpUserBlacklistGetResult.getTotal() == -1);
    Assert.assertFalse(wxMpUserBlacklistGetResult.getOpenidList().size() == -1);
    System.out.println(wxMpUserBlacklistGetResult);
  }

  @Test
  public void testPushToBlacklist() throws Exception {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();
    List<String> openidList = new ArrayList<>();
    openidList.add(configStorage.getOpenid());
    this.wxService.getBlackListService().pushToBlacklist(openidList);
  }

  @Test
  public void testPullFromBlacklist() throws Exception {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();
    List<String> openidList = new ArrayList<>();
    openidList.add(configStorage.getOpenid());
    this.wxService.getBlackListService().pullFromBlacklist(openidList);
  }

}
