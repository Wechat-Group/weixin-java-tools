package com.github.binarywang.wxpay.bean.profitsharing;

import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-03-22
 */
@Test
public class ProfitSharingQueryResultTest {

  @Test
  public void testFormatReceivers() {
    ProfitSharingQueryResult result = new ProfitSharingQueryResult();
    result.setReceiversJson("[\n" +
      "{\n" +
      "\"type\": \"MERCHANT_ID\",\n" +
      "\"account\":\"190001001\",\n" +
      "\"amount\":100,\n" +
      "\"description\": \"分到商户\",\n" +
      "\"result\": \"SUCCESS\",\n" +
      "\"finish_time\": \"20180608170132\"\n" +
      "},\n" +
      "{\n" +
      "\"type\": \"PERSONAL_WECHATID\",\n" +
      "\"account\":\"86693952\",\n" +
      "\"amount\":888,\n" +
      "\"description\": \"分到个人\",\n" +
      "\"result\": \"SUCCESS\",\n" +
      "\"finish_time\": \"20180608170132\"\n" +
      "}\n" +
      "]");

    List<ProfitSharingQueryResult.Receiver> receivers = result.formatReceivers();
    assertThat(receivers).isNotEmpty();

    assertThat(receivers.get(0)).isNotNull();
    assertThat(receivers.get(0).getType()).isEqualTo("MERCHANT_ID");
    assertThat(receivers.get(0).getAccount()).isEqualTo("190001001");
    assertThat(receivers.get(0).getAmount()).isEqualTo(100);
    assertThat(receivers.get(0).getDescription()).isEqualTo("分到商户");
    assertThat(receivers.get(0).getResult()).isEqualTo("SUCCESS");
    assertThat(receivers.get(0).getFinishTime()).isEqualTo("20180608170132");

    assertThat(receivers.get(1)).isNotNull();
    assertThat(receivers.get(1).getType()).isEqualTo("PERSONAL_WECHATID");
    assertThat(receivers.get(1).getAccount()).isEqualTo("86693952");
    assertThat(receivers.get(1).getAmount()).isEqualTo(888);
    assertThat(receivers.get(1).getDescription()).isEqualTo("分到个人");
    assertThat(receivers.get(1).getResult()).isEqualTo("SUCCESS");
    assertThat(receivers.get(1).getFinishTime()).isEqualTo("20180608170132");
  }
}
