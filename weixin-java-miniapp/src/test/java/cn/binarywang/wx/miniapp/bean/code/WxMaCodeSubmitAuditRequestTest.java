package cn.binarywang.wx.miniapp.bean.code;

import me.chanjar.weixin.common.util.json.GsonParser;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-26 19:55
 */
public class WxMaCodeSubmitAuditRequestTest {
  @Test
  public void testToJson() {
    WxMaCodeSubmitAuditRequest request = WxMaCodeSubmitAuditRequest.builder()
      .itemList(Arrays.asList(
        WxMaCategory.builder()
          .address("index")
          .tag("学习 生活")
          .firstClass("文娱")
          .firstId(1L)
          .secondClass("资讯")
          .secondId(2L)
          .title("首页")
          .build(),
        WxMaCategory.builder()
          .address("page/logs/logs")
          .tag("学习 工作")
          .firstClass("教育")
          .firstId(3L)
          .secondClass("学历教育")
          .secondId(4L)
          .thirdClass("高等")
          .thirdId(5L)
          .title("日志")
          .build()
      ))
      .feedbackInfo("blablabla")
      .feedbackStuff("xx|yy|zz")
      .previewInfo(new WxMaCodeSubmitAuditRequest.PreviewInfo().setVideoIdList(Arrays.asList("xxxx"))
        .setPicIdList(Arrays.asList("xxxx", "yyyy", "zzzz")))
      .versionDesc("blablabla")
      .ugcDeclare(new WxMaCodeSubmitAuditRequest.UgcDeclare()
        .setAuditDesc("blablabla")
        .setHasAuditTeam(1)
        .setMethod(new Integer[]{1})
        .setScene(new Integer[]{1, 2})
      ).build();

    String expectedJson = "{\n" +
      "\t\"item_list\": [\n" +
      "\t{\n" +
      "\t\t\"address\":\"index\",\n" +
      "\t\t\"tag\":\"学习 生活\",\n" +
      "\t\t\"first_class\": \"文娱\",\n" +
      "\t\t\"second_class\": \"资讯\",\n" +
      "\t\t\"first_id\":1,\n" +
      "\t\t\"second_id\":2,\n" +
      "\t\t\"title\": \"首页\"\n" +
      "\t},\n" +
      "\t{\n" +
      "\t\t\"address\":\"page/logs/logs\",\n" +
      "\t\t\"tag\":\"学习 工作\",\n" +
      "\t\t\"first_class\": \"教育\",\n" +
      "\t\t\"second_class\": \"学历教育\",\n" +
      "\t\t\"third_class\": \"高等\",\n" +
      "\t\t\"first_id\":3,\n" +
      "\t\t\"second_id\":4,\n" +
      "\t\t\"third_id\":5,\n" +
      "\t\t\"title\": \"日志\"\n" +
      "\t}\n" +
      "\t],\n" +
      "\t\"feedback_info\": \"blablabla\",\n" +
      "    \"feedback_stuff\": \"xx|yy|zz\",\n" +
      "    \"preview_info\" : {\n" +
      "        \"video_id_list\": [\"xxxx\"],\n" +
      "        \"pic_id_list\": [\"xxxx\", \"yyyy\", \"zzzz\" ]\n" +
      "    },\n" +
      "    \"version_desc\":\"blablabla\",\n" +
      "    \"ugc_declare\": {\n" +
      "        \"scene\": [\n" +
      "            1,\n" +
      "            2\n" +
      "        ],\n" +
      "        \"method\": [\n" +
      "            1\n" +
      "        ],\n" +
      "        \"has_audit_team\": 1,\n" +
      "        \"audit_desc\": \"blablabla\"\n" +
      "    }\n" +
      "}\n" +
      "";
    assertThat(request.toJson().replace("\n", "")).isEqualTo(GsonParser.parse(expectedJson).toString());
  }
}
