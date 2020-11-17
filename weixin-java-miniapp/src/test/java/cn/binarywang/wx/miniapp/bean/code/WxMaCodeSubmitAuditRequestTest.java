package cn.binarywang.wx.miniapp.bean.code;

import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-26 19:55
 */
public class WxMaCodeSubmitAuditRequestTest {
  @Test
  public void testToJson() {
    WxMaCodeSubmitAuditRequest request = WxMaCodeSubmitAuditRequest
      .builder()
      .itemList(Arrays.asList(
        WxMaCategory
          .builder()
          .address("pages/logs/logs")
          .tag("工具 效率")
          .firstClass("工具")
          .firstId(287L)
          .secondClass("效率")
          .secondId(616L)
          .title("日志")
          .build()
      )).build();
    System.out.println(request.toJson());
  }
}
