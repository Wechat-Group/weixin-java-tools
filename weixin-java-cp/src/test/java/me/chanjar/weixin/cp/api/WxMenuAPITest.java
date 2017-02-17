package me.chanjar.weixin.cp.api;

import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 测试菜单
 *
 * @author Daniel Qian
 */
@Test(groups = "menuAPI", dependsOnGroups = "baseAPI")
@Guice(modules = ApiTestModule.class)
public class WxMenuAPITest {

  @Inject
  protected WxCpServiceImpl wxService;

  @Test(dataProvider = "menu")
  public void testCreateMenu(WxMenu wxMenu) throws WxErrorException {
    this.wxService.menuCreate(wxMenu);
  }

  @Test(dependsOnMethods = {"testCreateMenu"})
  public void testGetMenu() throws WxErrorException {
    Assert.assertNotNull(this.wxService.menuGet());
  }

  @Test(dependsOnMethods = {"testGetMenu"})
  public void testDeleteMenu() throws WxErrorException {
    this.wxService.menuDelete();
  }

  @DataProvider(name = "menu")
  public Object[][] getMenu() {
    WxMenu menu = new WxMenu();
    WxMenuButton button1 = new WxMenuButton();
    button1.setType(WxConsts.BUTTON_CLICK);
    button1.setName("今日歌曲");
    button1.setKey("V1001_TODAY_MUSIC");

    WxMenuButton button2 = new WxMenuButton();
    button2.setType(WxConsts.BUTTON_CLICK);
    button2.setName("歌手简介");
    button2.setKey("V1001_TODAY_SINGER");

    WxMenuButton button3 = new WxMenuButton();
    button3.setName("菜单");

    menu.getButtons().add(button1);
    menu.getButtons().add(button2);
    menu.getButtons().add(button3);

    WxMenuButton button31 = new WxMenuButton();
    button31.setType(WxConsts.BUTTON_VIEW);
    button31.setName("搜索");
    button31.setUrl("http://www.soso.com/");

    WxMenuButton button32 = new WxMenuButton();
    button32.setType(WxConsts.BUTTON_VIEW);
    button32.setName("视频");
    button32.setUrl("http://v.qq.com/");

    WxMenuButton button33 = new WxMenuButton();
    button33.setType(WxConsts.BUTTON_CLICK);
    button33.setName("赞一下我们");
    button33.setKey("V1001_GOOD");

    button3.getSubButtons().add(button31);
    button3.getSubButtons().add(button32);
    button3.getSubButtons().add(button33);

    return new Object[][]{
            new Object[]{
                    menu
            }
    };

  }


}
