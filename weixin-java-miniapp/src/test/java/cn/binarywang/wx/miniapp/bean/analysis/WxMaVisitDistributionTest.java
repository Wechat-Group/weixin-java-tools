package cn.binarywang.wx.miniapp.bean.analysis;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-28
 */
public class WxMaVisitDistributionTest {
  @Test
  public void testFromJson() throws Exception {
    String json = "{\"ref_date\":\"20170313\",\"list\":[{\"index\":\"access_source_session_cnt\",\"item_list\":[{\"key\":10,\"value\":5},{\"key\":8,\"value\":687},{\"key\":7,\"value\":10740},{\"key\":6,\"value\":1961},{\"key\":5,\"value\":677},{\"key\":4,\"value\":653},{\"key\":3,\"value\":1120},{\"key\":2,\"value\":10243},{\"key\":1,\"value\":116578}]},{\"index\":\"access_staytime_info\",\"item_list\":[{\"key\":8,\"value\":16329},{\"key\":7,\"value\":19322},{\"key\":6,\"value\":21832},{\"key\":5,\"value\":19539},{\"key\":4,\"value\":29670},{\"key\":3,\"value\":19667},{\"key\":2,\"value\":11794},{\"key\":1,\"value\":4511}]},{\"index\":\"access_depth_info\",\"item_list\":[{\"key\":5,\"value\":217},{\"key\":4,\"value\":3259},{\"key\":3,\"value\":32445},{\"key\":2,\"value\":63542},{\"key\":1,\"value\":43201}]}]}\n";
    WxMaVisitDistribution distribution = WxMaVisitDistribution.fromJson(json);
    assertNotNull(distribution);
    assertEquals("20170313", distribution.getRefDate());
    assertTrue(distribution.getList().containsKey("access_source_session_cnt"));
    assertTrue(distribution.getList().containsKey("access_staytime_info"));
    assertTrue(distribution.getList().containsKey("access_depth_info"));
    System.out.println(distribution);
  }
}
