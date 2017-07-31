package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import org.testng.annotations.*;

import static org.testng.Assert.*;

/**
 * <pre>
 *
 * Created by Binary Wang on 2017-6-25.
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * </pre>
 */
@Guice(modules = ApiTestModule.class)
public class WxCpMenuServiceImplTest {
  @Inject
  protected WxCpService wxService;

  @DataProvider
  public Object[][] menuData() {
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

  @Test(dataProvider = "menuData")
  public void testCreate(WxMenu wxMenu) throws Exception {
    this.wxService.getMenuService().create(wxMenu);
  }

  @Test(dependsOnMethods = "testCreate")
  public void testGet() throws Exception {
    WxMenu menu = this.wxService.getMenuService().get();
    assertNotNull(menu);
    System.out.println(menu.toJson());
  }

  @Test(dependsOnMethods = {"testGet", "testCreate"})
  public void testDelete() throws Exception {
    this.wxService.getMenuService().delete();
  }

}
