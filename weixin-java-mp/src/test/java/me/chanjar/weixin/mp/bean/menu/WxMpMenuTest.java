package me.chanjar.weixin.mp.bean.menu;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 单元测试.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-11-05
 */
public class WxMpMenuTest {

  @Test
  public void testFromJson() {
    String json = "{\n" +
      "    \"menu\": {\n" +
      "        \"button\": [\n" +
      "            {\n" +
      "                \"type\": \"view\",\n" +
      "                \"name\": \"阅读记录\",\n" +
      "                \"sub_button\": []\n" +
      "            },\n" +
      "            {\n" +
      "                \"type\": \"view\",\n" +
      "                \"name\": \"\uD83D\uDC95秦枫\uD83D\uDC95\",\n" +
      "                \"sub_button\": []\n" +
      "            },\n" +
      "            {\n" +
      "                \"name\": \"签到送礼\",\n" +
      "                \"sub_button\": [\n" +
      "                    {\n" +
      "                        \"type\": \"view\",\n" +
      "                        \"name\": \"书城首页\",\n" +
      "                        \"sub_button\": []\n" +
      "                    },\n" +
      "                    {\n" +
      "                        \"type\": \"view\",\n" +
      "                        \"name\": \"我要充值\",\n" +
      "                        \"sub_button\": []\n" +
      "                    },\n" +
      "                    {\n" +
      "                        \"type\": \"view\",\n" +
      "                        \"name\": \"个人中心\",\n" +
      "                        \"sub_button\": []\n" +
      "                    },\n" +
      "                    {\n" +
      "                        \"type\": \"view\",\n" +
      "                        \"name\": \"签到送礼\",\n" +
      "                        \"sub_button\": []\n" +
      "                    }\n" +
      "                ]\n" +
      "            }\n" +
      "        ],\n" +
      "        \"menuid\": 449778320\n" +
      "    },\n" +
      "    \"conditionalmenu\": [\n" +
      "        {\n" +
      "            \"button\": [\n" +
      "                {\n" +
      "                    \"type\": \"view\",\n" +
      "                    \"name\": \"阅读记录\",\n" +
      "                    \"sub_button\": []\n" +
      "                },\n" +
      "                {\n" +
      "                    \"type\": \"view\",\n" +
      "                    \"name\": \"\uD83D\uDC95秦枫\uD83D\uDC95\",\n" +
      "                    \"sub_button\": []\n" +
      "                },\n" +
      "                {\n" +
      "                    \"name\": \"签到送礼\",\n" +
      "                    \"sub_button\": [\n" +
      "                        {\n" +
      "                            \"type\": \"view\",\n" +
      "                            \"name\": \"书城首页\",\n" +
      "                            \"sub_button\": []\n" +
      "                        },\n" +
      "                        {\n" +
      "                            \"type\": \"view\",\n" +
      "                            \"name\": \"我要看书\",\n" +
      "                            \"sub_button\": []\n" +
      "                        },\n" +
      "                        {\n" +
      "                            \"type\": \"view\",\n" +
      "                            \"name\": \"个人中心\",\n" +
      "                            \"sub_button\": []\n" +
      "                        },\n" +
      "                        {\n" +
      "                            \"type\": \"view\",\n" +
      "                            \"name\": \"签到送礼\",\n" +
      "                            \"sub_button\": []\n" +
      "                        }\n" +
      "                    ]\n" +
      "                }\n" +
      "            ],\n" +
      "            \"matchrule\": {\n" +
      "                \"client_platform_type\": \"1\"\n" +
      "            },\n" +
      "            \"menuid\": 449778326\n" +
      "        },\n" +
      "        {\n" +
      "            \"button\": [\n" +
      "                {\n" +
      "                    \"type\": \"view\",\n" +
      "                    \"name\": \"阅读记录\",\n" +
      "                    \"sub_button\": []\n" +
      "                },\n" +
      "                {\n" +
      "                    \"type\": \"view\",\n" +
      "                    \"name\": \"\uD83D\uDC95秦枫\uD83D\uDC95\",\n" +
      "                    \"sub_button\": []\n" +
      "                },\n" +
      "                {\n" +
      "                    \"name\": \"签到送礼\",\n" +
      "                    \"sub_button\": [\n" +
      "                        {\n" +
      "                            \"type\": \"view\",\n" +
      "                            \"name\": \"书城首页\",\n" +
      "                            \"sub_button\": []\n" +
      "                        },\n" +
      "                        {\n" +
      "                            \"type\": \"view\",\n" +
      "                            \"name\": \"我要充值\",\n" +
      "                            \"sub_button\": []\n" +
      "                        },\n" +
      "                        {\n" +
      "                            \"type\": \"view\",\n" +
      "                            \"name\": \"个人中心\",\n" +
      "                            \"sub_button\": []\n" +
      "                        },\n" +
      "                        {\n" +
      "                            \"type\": \"view\",\n" +
      "                            \"name\": \"签到送礼\",\n" +
      "                            \"sub_button\": []\n" +
      "                        }\n" +
      "                    ]\n" +
      "                }\n" +
      "            ],\n" +
      "            \"matchrule\": {\n" +
      "                \"client_platform_type\": \"2\"\n" +
      "            },\n" +
      "            \"menuid\": 449778324\n" +
      "        }\n" +
      "    ]\n" +
      "}";

    final WxMpMenu menu = WxMpMenu.fromJson(json);
    assertThat(menu).isNotNull();
    assertThat(menu.getConditionalMenu().get(0).getRule().getClientPlatformType()).isEqualTo("1");
  }
}
