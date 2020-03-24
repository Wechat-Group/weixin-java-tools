package me.chanjar.weixin.mp.bean.result;

import me.chanjar.weixin.mp.bean.marketing.WxMpAdLeadResult;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxMpAdLeadResultTest {
  @Test
  public void testFromJson() throws Exception {
    String json = "{\n" +
      "\t\"data\": {\n" +
      "\t\t\"page_info\": {\n" +
      "\t\t\t\"total_number\": 39,\n" +
      "\t\t\t\"page\": 1,\n" +
      "\t\t\t\"page_size\": 100,\n" +
      "\t\t\t\"total_page\": 1\n" +
      "\t\t},\n" +
      "\t\t\"list\": [{\n" +
      "\t\t\t\"click_id\": \"<click_id1>\",\n" +
      "\t\t\t\"adgroup_name\": \"<adgroup_name>\",\n" +
      "\t\t\t\"campaign_id\": 1800000001,\n" +
      "\t\t\t\"leads_info\": [{\n" +
      "\t\t\t\t\"value\": \"13800138000\",\n" +
      "\t\t\t\t\"key\": \"电话号码\"\n" +
      "\t\t\t}, {\n" +
      "\t\t\t\t\"value\": \"2019-03-14 00:54:34\",\n" +
      "\t\t\t\t\"key\": \"提交时间\"\n" +
      "\t\t\t}, {\n" +
      "\t\t\t\t\"value\": \"123\",\n" +
      "\t\t\t\t\"key\": \"自定义问题\"\n" +
      "\t\t\t}],\n" +
      "\t\t\t\"agency_name\": \"\",\n" +
      "\t\t\t\"agency_id\": \"\",\n" +
      "\t\t\t\"campaign_name\": \"<campaign_name>\",\n" +
      "\t\t\t\"adgroup_id\": 1800000002\n" +
      "\t\t}, {\n" +
      "\t\t\t\"click_id\": \"<click_id2>\",\n" +
      "\t\t\t\"adgroup_name\": \"<adgroup_name>\",\n" +
      "\t\t\t\"campaign_id\": 1800000001,\n" +
      "\t\t\t\"leads_info\": [{\n" +
      "\t\t\t\t\"value\": \"13800138001\",\n" +
      "\t\t\t\t\"key\": \"电话号码\"\n" +
      "\t\t\t}, {\n" +
      "\t\t\t\t\"value\": \"2019-03-14 02:10:39\",\n" +
      "\t\t\t\t\"key\": \"提交时间\"\n" +
      "\t\t\t}, {\n" +
      "\t\t\t\t\"value\": \"321\",\n" +
      "\t\t\t\t\"key\": \"自定义问题\"\n" +
      "\t\t\t}],\n" +
      "\t\t\t\"agency_name\": \"\",\n" +
      "\t\t\t\"agency_id\": \"\",\n" +
      "\t\t\t\"campaign_name\": \"<campaign_name>\",\n" +
      "\t\t\t\"adgroup_id\": 1800000002\n" +
      "\t\t}]\n" +
      "\t},\n" +
      "\t\"errcode\": 0,\n" +
      "\t\"errmsg\": \"\"\n" +
      "}";

    WxMpAdLeadResult adLeadResult = WxMpAdLeadResult.fromJson(json);

    assertNotNull(adLeadResult);
    assertNotNull(adLeadResult.getPageInfo());
    assertNotNull(adLeadResult.getAdLeads());
    assertTrue(adLeadResult.getAdLeads().size() > 0);

    System.out.println(adLeadResult);
  }

}
