package cn.binarywang.wx.miniapp.bean.code;

import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-26 19:58
 */
public class WxMaCodeVersionDistributionTest {
  @Test
  public void testFromJson() {
    String json = "{\"errcode\":0,\"errmsg\":\"ok\",\"now_version\":\"1.2.0\",\"uv_info\":{\"items\":[{\"version\":\"0.0.0\",\"percentage\":0},{\"version\":\"1.0.0\",\"percentage\":0},{\"version\":\"1.0.1\",\"percentage\":0},{\"version\":\"1.1.0\",\"percentage\":0},{\"version\":\"1.1.1\",\"percentage\":0},{\"version\":\"1.2.0\",\"percentage\":0},{\"version\":\"1.2.1\",\"percentage\":0},{\"version\":\"1.2.2\",\"percentage\":0},{\"version\":\"1.2.3\",\"percentage\":0},{\"version\":\"1.2.4\",\"percentage\":0},{\"version\":\"1.2.5\",\"percentage\":0},{\"version\":\"1.2.6\",\"percentage\":0},{\"version\":\"1.3.0\",\"percentage\":0},{\"version\":\"1.4.0\",\"percentage\":0},{\"version\":\"1.4.1\",\"percentage\":0},{\"version\":\"1.4.2\",\"percentage\":0},{\"version\":\"1.4.3\",\"percentage\":0},{\"version\":\"1.4.4\",\"percentage\":0},{\"version\":\"1.5.0\",\"percentage\":0},{\"version\":\"1.5.1\",\"percentage\":0},{\"version\":\"1.5.2\",\"percentage\":0},{\"version\":\"1.5.3\",\"percentage\":0},{\"version\":\"1.5.4\",\"percentage\":0},{\"version\":\"1.5.5\",\"percentage\":0},{\"version\":\"1.5.6\",\"percentage\":0},{\"version\":\"1.5.7\",\"percentage\":0},{\"version\":\"1.5.8\",\"percentage\":0},{\"version\":\"1.6.0\",\"percentage\":0.0132},{\"version\":\"1.6.1\",\"percentage\":0.0132},{\"version\":\"1.6.2\",\"percentage\":0.0132},{\"version\":\"1.6.3\",\"percentage\":0.0132},{\"version\":\"1.6.4\",\"percentage\":0.0132},{\"version\":\"1.6.5\",\"percentage\":0.0132},{\"version\":\"1.6.6\",\"percentage\":0.0132},{\"version\":\"1.6.7\",\"percentage\":0.1711},{\"version\":\"1.6.8\",\"percentage\":0.1711},{\"version\":\"1.7.0\",\"percentage\":0.1842},{\"version\":\"1.7.1\",\"percentage\":0.25},{\"version\":\"1.7.2\",\"percentage\":0.5263},{\"version\":\"1.7.3\",\"percentage\":0.5263},{\"version\":\"1.7.4\",\"percentage\":0.6711}]}}\n";
    System.out.println(WxMaCodeVersionDistribution.fromJson(json));
  }
}
