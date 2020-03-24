package me.chanjar.weixin.mp.bean.marketing;

import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 老板加点注释吧.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-07-14
 */
public class WxMpUserActionTest {

  @Test
  public void testListToJson() {
    assertThat(WxMpUserAction.listToJson(Lists.newArrayList(WxMpUserAction.builder()
      .actionParam(1)
      .actionTime(122)
      .actionType("haha")
      .clickId("111")
      .url("1222")
      .userActionSetId(111L)
      .build()
    ))).isEqualTo("{\"actions\":[{\"user_action_set_id\":111,\"url\":\"1222\",\"action_time\":122,\"action_type\":\"haha\",\"trace\":{\"click_id\":\"111\"},\"action_param\":{\"value\":1}}]}");
  }
}
