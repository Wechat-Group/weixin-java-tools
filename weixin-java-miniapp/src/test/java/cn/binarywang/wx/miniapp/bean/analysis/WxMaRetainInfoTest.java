package cn.binarywang.wx.miniapp.bean.analysis;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-28
 */
public class WxMaRetainInfoTest {
  @Test
  public void testFromJson() throws Exception {
    String json = "{\"ref_date\":\"20170313\",\"visit_uv_new\":[{\"key\":0,\"value\":5464}],\"visit_uv\":[{\"key\":0,\"value\":55500}]}\n";
    WxMaRetainInfo retainInfo = WxMaRetainInfo.fromJson(json);
    assertNotNull(retainInfo);
    assertEquals("20170313", retainInfo.getRefDate());
    assertTrue(retainInfo.getVisitUv().containsKey(0));
    assertTrue(retainInfo.getVisitUvNew().containsKey(0));
    System.out.println(retainInfo);
  }
}
