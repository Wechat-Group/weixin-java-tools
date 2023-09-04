package com.github.binarywang.wxpay.bean.profitsharing;

import com.github.binarywang.wxpay.bean.profitsharing.result.ProfitSharingResult;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitSharingV3ResultTest {

  @Test
  public void testGetReceiverList() {
    ProfitSharingResult profitSharingResult = ProfitSharingResult.fromXML("<xml>\n" +
      "\t<return_code>\n" +
      "\t\t<![CDATA[SUCCESS]]>\n" +
      "\t</return_code>\n" +
      "\t<result_code>\n" +
      "\t\t<![CDATA[SUCCESS]]>\n" +
      "\t</result_code>\n" +
      "\t<mch_id>\n" +
      "\t\t<![CDATA[aseedwq]]>\n" +
      "\t</mch_id>\n" +
      "\t<appid>\n" +
      "\t\t<![CDATA[qweqweq]]>\n" +
      "\t</appid>\n" +
      "\t<nonce_str>\n" +
      "\t\t<![CDATA[e2qedawws]]>\n" +
      "\t</nonce_str>\n" +
      "\t<sign>\n" +
      "\t\t<![CDATA[eqwdsqwsq\t]]>\n" +
      "\t</sign>\n" +
      "\t<transaction_id>\n" +
      "\t\t<![CDATA[eqwqwsq]]>\n" +
      "\t</transaction_id>\n" +
      "\t<out_order_no>\n" +
      "\t\t<![CDATA[wqdqwdw]]>\n" +
      "\t</out_order_no>\n" +
      "\t<order_id>\n" +
      "\t\t<![CDATA[dqdwedewee]]>\n" +
      "\t</order_id>\n" +
      "\t<receivers>\n" +
      "\t\t<![CDATA[[\n" +
      "    {\n" +
      "        \"account\": \"123423121\",\n" +
      "        \"amount\": 7,\n" +
      "        \"description\": \"解冻给分账方\",\n" +
      "        \"detail_id\": \"360002qwq3006254484\",\n" +
      "        \"finish_time\": \"\t360002qwq3006254484wq\",\n" +
      "        \"result\": \"PENDING\",\n" +
      "        \"type\": \"MERCHANT_ID\"\n" +
      "    },\n" +
      "    {\n" +
      "        \"account\": \"wqwqeeqe\",\n" +
      "        \"amount\": 3,\n" +
      "        \"description\": \"qwwqw\",\n" +
      "        \"detail_id\": \"3600020wqwqw06254482\",\n" +
      "        \"finish_time\": \"q2wqewqwq\",\n" +
      "        \"result\": \"PENDING\",\n" +
      "        \"type\": \"MERCHANT_ID\"\n" +
      "    }\n" +
      "]]]>\n" +
      "\t</receivers>\n" +
      "\t<status>\n" +
      "\t\t<![CDATA[PROCESSING]]>\n" +
      "\t</status>\n" +
      "</xml>", ProfitSharingResult.class);

    List<ProfitSharingResult.Receiver> receiverList = profitSharingResult.getReceiverList();
    assertThat(receiverList).isNotEmpty();
  }
}
