package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.WxMpShakeInfoResult;
import me.chanjar.weixin.mp.bean.WxMpShakeQuery;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 测试摇一摇周边相关的接口
 *
 * @author rememberber
 */
@Test(groups = "userAPI")
@Guice(modules = ApiTestModule.class)
public class WxMpShakeServiceImplTest {
  @Inject
  private WxMpService wxService;

  public void testGetShakeInfo() throws Exception {
    WxMpShakeQuery wxMpShakeQuery = new WxMpShakeQuery();
    wxMpShakeQuery.setTicket("b87db7df490e5cbe4f598272f77f46be");
    wxMpShakeQuery.setNeedPoi(1);
    WxMpShakeInfoResult wxMpShakeInfoResult = this.wxService.getShakeService().getShakeInfo(wxMpShakeQuery);

    System.out.println();
  }

}
