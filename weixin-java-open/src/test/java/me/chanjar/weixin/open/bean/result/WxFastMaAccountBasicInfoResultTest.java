package me.chanjar.weixin.open.bean.result;

import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


public class WxFastMaAccountBasicInfoResultTest {
  @Test
  public void testFromJson() throws Exception {
    String json = "{\n" +
      "    \"errcode\": 0,\n" +
      "    \"errmsg\": \"ok\",\n" +
      "\t\"appid\": \"wxdc685123d955453\",\n" +
      "    \"account_type\": 2,\n" +
      "\t\"principal_type\": 1,\n" +
      "\t\"principal_name\": \"深圳市腾讯计算机系统有限公司\",\n" +
      "    \"realname_status\": 1,\n" +
      "    \"wx_verify_info\": {\n" +
      "        \"qualification_verify\": true,\n" +
      "        \"naming_verify\": true,\n" +
      "        \"annual_review\": true,\n" +
      "        \"annual_review_begin_time\": 1550490981,\n" +
      "        \"annual_review_end_time\": 1558266981\n" +
      "    },\n" +
      "    \"signature_info\": {\n" +
      "        \"signature\": \"功能介绍\",\n" +
      "        \"modify_used_count\": 1,\n" +
      "        \"modify_quota\": 5\n" +
      "    },\n" +
      "\t\"head_image_info\": {\n" +
      "        \"head_image_url\": \"http://mmbiz.qpic.cn/mmbiz/a5icZrUmbV8p5jb6RZ8aYfjfS2AVle8URwBt8QIu6XbGewB9wiaWYWkPwq4R7pfdsFibuLkic16UcxDSNYtB8HnC1Q/0\",\n" +
      "        \"modify_used_count\": 3,\n" +
      "        \"modify_quota\": 5\n" +
      "    }\n" +
      "}";

    WxFastMaAccountBasicInfoResult res = WxOpenGsonBuilder.create().fromJson(json, WxFastMaAccountBasicInfoResult.class);

    assertNotNull(res);
    assertNotNull(res.getAppId());
    assertNotNull(res.getSignatureInfo().getModifyQuota());
    assertNotNull(res.getHeadImageInfo().getHeadImageUrl());
    assertNotNull(res.getWxVerifyInfo().getNamingVerify());
    assertTrue(res.getWxVerifyInfo().getNamingVerify());
    System.out.println(res);
  }

}
