package me.chanjar.weixin.cp.bean.external.contact;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WxCpGroupMsgResultTest {

  @Test
  public void testToJson() {
    /*
      @see https://work.weixin.qq.com/api/doc/16251
     */
    String json = "{ " +
      "\"errcode\": 0, " +
      "\"errmsg\": \"ok\", " +
      "\"detail_list\": [ " +
      "   { " +
      "     \"external_userid\": \"wmqfasd1e19278asdasAAAA\", " +
      "     \"chat_id\":\"wrOgQhDgAAMYQiS5ol9G7gK9JVAAAA\", " +
      "     \"userid\": \"zhangsan\", " +
      "     \"status\": 1, " +
      "     \"send_time\": 1552536375 " +
      "   } " +
      " ] " +
      "}";

    WxCpGroupMsgResult result = WxCpGroupMsgResult.fromJson(json);
    assertThat(result.getDetailList().size()).isEqualTo(1);
    WxCpGroupMsgResult.ExternalContactGroupMsgDetailInfo detail = result.getDetailList().get(0);
    assertThat(detail.getChatId()).isEqualTo("wrOgQhDgAAMYQiS5ol9G7gK9JVAAAA");
    assertThat(detail.getExternalUserId()).isEqualTo("wmqfasd1e19278asdasAAAA");
    assertThat(detail.getUserId()).isEqualTo("zhangsan");
    assertThat(detail.getStatus()).isEqualTo(1);
    assertThat(detail.getSendTime()).isEqualTo(1552536375L);
  }
}
