package me.chanjar.weixin.mp.bean.menu;

import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-08-05
 */
public class WxMpGetSelfMenuInfoResultTest {

  @Test
  public void testFromJson() {
    String json = "{\"is_menu_open\":1,\"selfmenu_info\":{\"button\":[{\"name\":\"学院\",\"sub_button\":{\"list\":[{\"type\":\"miniprogram\",\"name\":\"成语答题王\",\"url\":\"http:\\/\\/host\",\"appid\":\"wxf4dc5b4e7b35dcd1\",\"pagepath\":\"pages\\/index\\/index\"},{\"type\":\"miniprogram\",\"name\":\"大师课程\",\"url\":\"https:\\/\\/host\\/course\\/tutorial\",\"appid\":\"wxfd6acd566482c6cb\",\"pagepath\":\"pages\\/tutorialDetail\\/tutorialDetail\"}]}},{\"type\":\"miniprogram\",\"name\":\"学科商城\",\"url\":\"https:\\/\\/host\\/-dAEuY\",\"appid\":\"wx720f9f1301595564\",\"pagepath\":\"pages\\/home\\/dashboard\\/index\"}]}}";
    final WxMpGetSelfMenuInfoResult selfMenuInfoResult = WxMpGetSelfMenuInfoResult.fromJson(json);
    assertThat(selfMenuInfoResult).isNotNull();
    assertThat(selfMenuInfoResult.getIsMenuOpen()).isEqualTo(1);
    assertThat(selfMenuInfoResult.getSelfMenuInfo()).isNotNull();
    final List<WxMpSelfMenuInfo.WxMpSelfMenuButton> buttons = selfMenuInfoResult.getSelfMenuInfo().getButtons();
    assertThat(buttons.size()).isEqualTo(2);
    assertThat(buttons.get(1).getAppId()).isEqualTo("wx720f9f1301595564");
    assertThat(buttons.get(1).getPagePath()).isEqualTo("pages/home/dashboard/index");
  }
}
