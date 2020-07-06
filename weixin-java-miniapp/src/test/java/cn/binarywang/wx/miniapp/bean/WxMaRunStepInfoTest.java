package cn.binarywang.wx.miniapp.bean;

import java.util.List;

import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 *
 * Created by Binary Wang on 2018/11/4.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaRunStepInfoTest {

  @Test
  public void testFromJson() {
    // 数据来源：https://developers.weixin.qq.com/miniprogram/dev/api/open-api/werun/wx.getWeRunData.html
    String json = "{\n" +
      "  \"stepInfoList\": [\n" +
      "    {\n" +
      "      \"timestamp\": 1445866601,\n" +
      "      \"step\": 100\n" +
      "    },\n" +
      "    {\n" +
      "      \"timestamp\": 1445876601,\n" +
      "      \"step\": 120\n" +
      "    }\n" +
      "  ]\n" +
      "}";

    final List<WxMaRunStepInfo> stepInfoList = WxMaRunStepInfo.fromJson(json);
    assertThat(stepInfoList).isNotEmpty();
    assertThat(stepInfoList.get(0).getStep()).isEqualTo(100);
    assertThat(stepInfoList.get(0).getTimestamp()).isEqualTo(1445866601);
    assertThat(stepInfoList.get(1).getStep()).isEqualTo(120);
    assertThat(stepInfoList.get(1).getTimestamp()).isEqualTo(1445876601);
  }
}
