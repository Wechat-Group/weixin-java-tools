package me.chanjar.weixin.open.bean.result;

import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


public class WxFastMaCanSetCategoryResultTest {
  @Test
  public void testFromJson() throws Exception {
    String json = "{\n" +
      "    \"errcode\": 0,\n" +
      "    \"errmsg\": \"ok\",\n" +
      "    \"category_list\": {\n" +
      "\t\t\"categories\": [{\n" +
      "            \"children\": [\n" +
      "                874\n" +
      "            ],\n" +
      "            \"father\": 0,\n" +
      "            \"id\": 868,\n" +
      "            \"level\": 1,\n" +
      "            \"name\": \"时政信息\",\n" +
      "            \"qualify\": {\n" +
      "                \"exter_list\": [{\n" +
      "                        \"inner_list\": [{\n" +
      "                            \"name\": \"有资质证件《互联网新闻信息服务许可证》\",\n" +
      "                            \"url\": \"\"\n" +
      "                        }]\n" +
      "                    },\n" +
      "                    {\n" +
      "                        \"inner_list\": [{\n" +
      "                            \"name\": \"无资质ICP备案和《组织机构代码证》\",\n" +
      "                            \"url\": \"\"\n" +
      "                        }]\n" +
      "                    }\n" +
      "                ]\n" +
      "            },\n" +
      "            \"sensitive_type\": 1\n" +
      "        }]\n" +
      "    }\n" +
      "}";

    WxFastMaCanSetCategoryResult res = WxOpenGsonBuilder.create ().fromJson (json, WxFastMaCanSetCategoryResult.class);

    assertNotNull(res);
    assertTrue(res.getCategoryList ().getCategories ().size ()> 0);
    assertNotNull(res.getCategoryList ().getCategories ().get (0));
    assertTrue(res.getCategoryList ().getCategories ().get (0).getChildren ().size () > 0);
    assertNotNull(res.getCategoryList ().getCategories ().get (0).getName ());
    assertTrue(res.getCategoryList ().getCategories ().get (0).getQualify ().getExterList ().size ()>0);
    assertTrue(res.getCategoryList ().getCategories ().get (0).getQualify ().getExterList ().get (0).getInnerList ().size ()> 0);
    assertNotNull(res.getCategoryList ().getCategories ().get (0).getQualify ().getExterList ().get (0).getInnerList ().get (0).getUrl ());
    System.out.println(res);
  }

}
