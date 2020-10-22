package me.chanjar.weixin.cp.bean.external;

import me.chanjar.weixin.common.util.json.GsonParser;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 单元测试.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-09-20
 */
public class WxCpUpdateRemarkRequestTest {

  @Test
  public void testToJson() {
    String json = "{\n" +
      "   \"userid\":\"zhangsan\",\n" +
      "   \"external_userid\":\"woAJ2GCAAAd1asdasdjO4wKmE8Aabj9AAA\",\n" +
      "   \"remark\":\"备注信息\",\n" +
      "   \"description\":\"描述信息\",\n" +
      "   \"remark_company\":\"腾讯科技\",\n" +
      "   \"remark_mobiles\":[\n" +
      "        \"13800000001\",\n" +
      "        \"13800000002\"\n" +
      "   ],\n" +
      "   \"remark_pic_mediaid\":\"MEDIAID\"\n" +
      "}\n";

    WxCpUpdateRemarkRequest request = WxCpUpdateRemarkRequest.builder()
      .description("描述信息")
      .userId("zhangsan")
      .externalUserId("woAJ2GCAAAd1asdasdjO4wKmE8Aabj9AAA")
      .remark("备注信息")
      .remarkCompany("腾讯科技")
      .remarkMobiles(new String[]{"13800000001","13800000002"})
      .remarkPicMediaId("MEDIAID")
      .build();
    assertThat(request.toJson()).isEqualTo(GsonParser.parse(json).toString());
  }
}
