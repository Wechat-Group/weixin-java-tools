package cn.binarywang.wx.miniapp.bean.analysis;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-28
 */
public class WxMaUserPortraitTest {
  @Test
  public void testFromJson() throws Exception {
    String json = "{\"ref_date\":\"20170611\",\"visit_uv_new\":{\"province\":[{\"id\":31,\"name\":\"广东省\",\"value\":215}],\"city\":[{\"id\":3102,\"name\":\"广州\",\"value\":78}],\"genders\":[{\"id\":1,\"name\":\"男\",\"value\":2146}],\"platforms\":[{\"id\":1,\"name\":\"iPhone\",\"value\":27642}],\"devices\":[{\"name\":\"OPPO R9\",\"value\":61}],\"ages\":[{\"id\":1,\"name\":\"17岁以下\",\"value\":151}]},\"visit_uv\":{\"province\":[{\"id\":31,\"name\":\"广东省\",\"value\":1341}],\"city\":[{\"id\":3102,\"name\":\"广州\",\"value\":234}],\"genders\":[{\"id\":1,\"name\":\"男\",\"value\":14534}],\"platforms\":[{\"id\":1,\"name\":\"iPhone\",\"value\":21750}],\"devices\":[{\"name\":\"OPPO R9\",\"value\":617}],\"ages\":[{\"id\":1,\"name\":\"17岁以下\",\"value\":3156}]}}\n";
    WxMaUserPortrait portrait = WxMaUserPortrait.fromJson(json);
    System.out.println(portrait);
    assertNotNull(portrait);
  }
}
