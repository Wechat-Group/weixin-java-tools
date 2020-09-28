package me.chanjar.weixin.cp.bean.oa.calendar;

import me.chanjar.weixin.common.util.json.GsonParser;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 单元测试.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-09-20
 */
public class WxCpOaCalendarTest {

  @Test
  public void testToJson() {
    String json = "{\n" +
      "    \"calendar\" : {\n" +
      "        \"organizer\" : \"userid1\",\n" +
      "        \"readonly\" : 1,\n" +
      "        \"set_as_default\" : 1,\n" +
      "        \"summary\" : \"test_summary\",\n" +
      "        \"color\" : \"#FF3030\",\n" +
      "        \"description\" : \"test_describe\",\n" +
      "        \"shares\" : [\n" +
      "            {\n" +
      "                \"userid\" : \"userid2\"\n" +
      "            },\n" +
      "            {\n" +
      "                \"userid\" : \"userid3\",\n" +
      "                \"readonly\" : 1\n" +
      "            }\n" +
      "        ]\n" +
      "    }\n" +
      "}\n";

    assertThat(WxCpOaCalendar.builder()
      .organizer("userid1")
      .readonly(1)
      .setAsDefault(1)
      .summary("test_summary")
      .color("#FF3030")
      .description("test_describe")
      .shares(Arrays.asList(new WxCpOaCalendar.ShareInfo("userid2", null),
        new WxCpOaCalendar.ShareInfo("userid3", 1)))
      .build().toJson())
      .isEqualTo(GsonParser.parse(json).toString());
  }
}
