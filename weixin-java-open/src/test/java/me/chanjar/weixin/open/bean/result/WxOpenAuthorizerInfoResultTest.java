package me.chanjar.weixin.open.bean.result;

import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.testng.annotations.Test;

/**
 * @title: 获取授权帐号详情 信息反序列化测试
 * @author: trifolium
 * created on : 2022/6/7
 * @modified :
 */
public class WxOpenAuthorizerInfoResultTest {

  @Test
  public void testDeserialization() {

    String json = "{\n" +
      "  \"authorizer_info\": {\n" +
      "    \"nick_name\": \"美妆饰品\",\n" +
      "    \"head_img\": \"http:\\/\\/wx.qlogo.cn\\/mmopen\\/jJSbu4Te5iuiaM0dFnKVUEE83n2yH5cQStb\\/0\",\n" +
      "    \"service_type_info\": {\n" +
      "      \"id\": 0\n" +
      "    },\n" +
      "    \"verify_type_info\": {\n" +
      "      \"id\": -1\n" +
      "    },\n" +
      "    \"user_name\": \"gh_c43395cb652e\",\n" +
      "    \"alias\": \"\",\n" +
      "    \"qrcode_url\": \"http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/kPmmhe6g\\/0\",\n" +
      "    \"business_info\": {\n" +
      "      \"open_pay\": 0,\n" +
      "      \"open_shake\": 0,\n" +
      "      \"open_scan\": 0,\n" +
      "      \"open_card\": 0,\n" +
      "      \"open_store\": 0\n" +
      "    },\n" +
      "    \"idc\": 1,\n" +
      "    \"principal_name\": \"个人\",\n" +
      "    \"signature\": \"做美装，精美饰品等搭配教学\",\n" +
      "    \"MiniProgramInfo\": {\n" +
      "      \"network\": {\n" +
      "        \"RequestDomain\": [\"https:\\/\\/weixin.qq.com\"],\n" +
      "        \"WsRequestDomain\": [\"wss:\\/\\/weixin.qq.com\"],\n" +
      "        \"UploadDomain\": [\"https:\\/\\/weixin.qq.com\"],\n" +
      "        \"DownloadDomain\": [\"https:\\/\\/weixin.qq.com\"],\n" +
      "        \"BizDomain\": [],\n" +
      "        \"UDPDomain\": [],\n" +
      "        \"TCPDomain\": [],\n" +
      "        \"PrefetchDNSDomain\": [],\n" +
      "        \"NewRequestDomain\": [],\n" +
      "        \"NewWsRequestDomain\": [],\n" +
      "        \"NewUploadDomain\": [],\n" +
      "        \"NewDownloadDomain\": [],\n" +
      "        \"NewBizDomain\": [],\n" +
      "        \"NewUDPDomain\": [],\n" +
      "        \"NewTCPDomain\": [],\n" +
      "        \"NewPrefetchDNSDomain\": []\n" +
      "      },\n" +
      "      \"categories\": [{\n" +
      "        \"first\": \"生活服务\",\n" +
      "        \"second\": \"丽人服务\"\n" +
      "      }, {\n" +
      "        \"first\": \"旅游服务\",\n" +
      "        \"second\": \"旅游资讯\"\n" +
      "      }, {\n" +
      "        \"first\": \"物流服务\",\n" +
      "        \"second\": \"查件\"\n" +
      "      }],\n" +
      "      \"visit_status\": 0\n" +
      "    },\n" +
      "    \"register_type\": 0,\n" +
      "    \"account_status\": 1,\n" +
      "    \"basic_config\": {\n" +
      "      \"is_phone_configured\": true,\n" +
      "      \"is_email_configured\": true\n" +
      "    }\n" +
      "  },\n" +
      "  \"authorization_info\": {\n" +
      "    \"authorizer_appid\": \"wx326eecacf7370d4e\",\n" +
      "    \"authorizer_refresh_token\": \"refreshtoken@@@RU0Sgi7bD6apS7frS9gj8Sbws7OoDejK9Z-cm0EnCzg\",\n" +
      "    \"func_info\": [{\n" +
      "      \"funcscope_category\": {\n" +
      "        \"id\": 3\n" +
      "      },\n" +
      "      \"confirm_info\": {\n" +
      "        \"need_confirm\": 0,\n" +
      "        \"already_confirm\": 0,\n" +
      "        \"can_confirm\": 0\n" +
      "      }\n" +
      "    }, {\n" +
      "      \"funcscope_category\": {\n" +
      "        \"id\": 7\n" +
      "      }\n" +
      "    }, {\n" +
      "      \"funcscope_category\": {\n" +
      "        \"id\": 17\n" +
      "      }\n" +
      "    }, {\n" +
      "      \"funcscope_category\": {\n" +
      "        \"id\": 18\n" +
      "      },\n" +
      "      \"confirm_info\": {\n" +
      "        \"need_confirm\": 0,\n" +
      "        \"already_confirm\": 0,\n" +
      "        \"can_confirm\": 0\n" +
      "      }\n" +
      "    }, {\n" +
      "      \"funcscope_category\": {\n" +
      "        \"id\": 19\n" +
      "      }\n" +
      "    }, {\n" +
      "      \"funcscope_category\": {\n" +
      "        \"id\": 30\n" +
      "      },\n" +
      "      \"confirm_info\": {\n" +
      "        \"need_confirm\": 0,\n" +
      "        \"already_confirm\": 0,\n" +
      "        \"can_confirm\": 0\n" +
      "      }\n" +
      "    }, {\n" +
      "      \"funcscope_category\": {\n" +
      "        \"id\": 115\n" +
      "      }\n" +
      "    }]\n" +
      "  }\n" +
      "}\n";

    System.out.println(WxOpenGsonBuilder.create().fromJson(json, WxOpenAuthorizerInfoResult.class));
  }

}
