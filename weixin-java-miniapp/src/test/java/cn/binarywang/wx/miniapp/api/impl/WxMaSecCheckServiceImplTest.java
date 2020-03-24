package cn.binarywang.wx.miniapp.api.impl;

import java.io.File;

import org.testng.annotations.*;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

/**
 * <pre>
 *
 * Created by Binary Wang on 2018/11/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaSecCheckServiceImplTest {
  @Inject
  private WxMaService wxService;

  @Test
  public void testCheckImage() throws WxErrorException {
    boolean result = this.wxService.getSecCheckService()
      .checkImage(new File(ClassLoader.getSystemResource("tmp.png").getFile()));
    assertTrue(result);
  }

  @DataProvider
  public Object[][] secData() {
    return new Object[][]{
      {"特3456书yuuo莞6543李zxcz蒜7782法fgnv级", false},
      {"完2347全dfji试3726测asad感3847知qwez到", false},
      {"提现&下载&棋牌游戏&网页", false},
      {"hello world!", true}
    };
  }

  @Test(dataProvider = "secData")
  public void testCheckMessage(String msg, boolean result) throws WxErrorException {
    assertThat(this.wxService.getSecCheckService()
      .checkMessage(msg))
      .isEqualTo(result);
  }
}
