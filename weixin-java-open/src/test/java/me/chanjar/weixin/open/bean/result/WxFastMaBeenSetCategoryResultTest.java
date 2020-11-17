package me.chanjar.weixin.open.bean.result;

import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


public class WxFastMaBeenSetCategoryResultTest {
  @Test
  public void testFromJson() throws Exception {
    String json = "{\n" +
      "    \"errcode\": 0,\n" +
      "    \"errmsg\":\"ok\",\n" +
      "    \"categories\": [\n" +
      "        {\n" +
      "            \"first\": 8,\n" +
      "            \"first_name\": \"教育\",\n" +
      "            \"second\": 39,\n" +
      "            \"second_name\": \"出国移民\",\n" +
      "            \"audit_status\": 1,\n" +
      "            \"audit_reason\": \"不通过啊啊\"\n" +
      "        }\n" +
      "    ],\n" +
      "\t\"limit\": 5,\n" +
      "    \"quota\": 4,\n" +
      "    \"category_limit\": 20\n" +
      "}";

    WxFastMaBeenSetCategoryResult res = WxOpenGsonBuilder.create().fromJson(json, WxFastMaBeenSetCategoryResult.class);

    assertNotNull(res);
    assertTrue(res.getCategories().size() > 0);
    assertNotNull(res.getCategories().get(0));
    assertNotNull(res.getCategories().get(0).getFirstName());
    System.out.println(res);
  }

}
