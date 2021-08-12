package me.chanjar.weixin.mp.bean.result;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * <pre>
 * Created by Binary Wang on 2017-7-8.
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * </pre>
 */
public class WxMpCurrentAutoReplyInfoTest {
  @Test
  public void testFromJson() throws Exception {
    String json = "{ \n" +
      "  \"is_add_friend_reply_open\": 1, \n" +
      "  \"is_autoreply_open\": 1, \n" +
      "  \"add_friend_autoreply_info\": { \n" +
      "      \"type\": \"text\", \n" +
      "      \"content\": \"Thanks for your attention!\"\n" +
      "  }, \n" +
      "  \"message_default_autoreply_info\": { \n" +
      "      \"type\": \"text\", \n" +
      "      \"content\": \"Hello, this is autoreply!\"\n" +
      "  }, \n" +
      "  \"keyword_autoreply_info\": { \n" +
      "      \"list\": [ \n" +
      "          { \n" +
      "              \"rule_name\": \"autoreply-news\", \n" +
      "              \"create_time\": 1423028166, \n" +
      "              \"reply_mode\": \"reply_all\", \n" +
      "              \"keyword_list_info\": [ \n" +
      "                  { \n" +
      "                      \"type\": \"text\", \n" +
      "                      \"match_mode\": \"contain\", \n" +
      "                      \"content\": \"news测试\"//此处content即为关键词内容\n" +
      "                  }\n" +
      "              ], \n" +
      "              \"reply_list_info\": [ \n" +
      "                  { \n" +
      "                      \"type\": \"news\", \n" +
      "                      \"news_info\": { \n" +
      "                          \"list\": [ \n" +
      "                              { \n" +
      "                                  \"title\": \"it's news\", \n" +
      "                                  \"author\": \"jim\", \n" +
      "                                  \"digest\": \"it's digest\", \n" +
      "                                  \"show_cover\": 1,  \"cover_url\": \"http://mmbiz.qpic.cn/mmbiz/GE7et87vE9vicuCibqXsX9GPPLuEtBfXfKbE8sWdt2DDcL0dMfQWJWTVn1N8DxI0gcRmrtqBOuwQH\n" +
      "  euPKmFLK0ZQ/0\", \n" +
      "                                  \"content_url\": \"http://mp.weixin.qq.com/s?__biz=MjM5ODUwNTM3Ng==&mid=203929886&idx=1&sn=628f964cf0c6d84c026881b6959aea8b#rd\", \n" +
      "                                  \"source_url\": \"http://www.url.com\"\n" +
      "                              }\n" +
      "                          ]\n" +
      "                      }\n" +
      "                  }, \n" +
      "                  { \n" +
      "                      \"type\": \"news\",\n" +
      "                      \"content\":\"KQb_w_Tiz-nSdVLoTV35Psmty8hGBulGhEdbb9SKs-o\", \n" +
      "                      \"news_info\": { \n" +
      "                          \"list\": [ \n" +
      "                              { \n" +
      "                                  \"title\": \"MULTI_NEWS\", \n" +
      "                                  \"author\": \"JIMZHENG\", \n" +
      "                                  \"digest\": \"text\", \n" +
      "                                  \"show_cover\": 0, \n" +
      "                                  \"cover_url\": \"http://mmbiz.qpic.cn/mmbiz/GE7et87vE9vicuCibqXsX9GPPLuEtBfXfK0HKuBIa1A1cypS0uY1wickv70iaY1gf3I1DTszuJoS3lAVLv\n" +
      "hTcm9sDA/0\", \n" +
      "                                  \"content_url\": \"http://mp.weixin.qq.com/s?__biz=MjM5ODUwNTM3Ng==&mid=204013432&idx=1&sn=80ce6d9abcb832237bf86c87e50fda15#rd\", \n" +
      "                                  \"source_url\": \"\"\n" +
      "                              },\n" +
      "                              { \n" +
      "                                  \"title\": \"MULTI_NEWS4\", \n" +
      "                                  \"author\": \"JIMZHENG\", \n" +
      "                                  \"digest\": \"MULTI_NEWSMULTI_NEWSMULTI_NEWSMULTI_NEWSMULTI_NEWSMULT\", \n" +
      "                                  \"show_cover\": 1, \n" +
      "\"cover_url\": \"http://mmbiz.qpic.cn/mmbiz/GE7et87vE9vicuCibqXsX9GPPLuEtBfXfKbE8sWdt2DDcL0dMfQWJWTVn1N8DxI0gcRmrtqBOuwQ\n" +
      "HeuPKmFLK0ZQ/0\", \n" +
      "                                  \"content_url\": \"http://mp.weixin.qq.com/s?__biz=MjM5ODUwNTM3Ng==&mid=204013432&idx=5&sn=b4ef73a915e7c2265e437096582774af#rd\", \n" +
      "                                  \"source_url\": \"\"\n" +
      "                              }\n" +
      "                          ]\n" +
      "                      }\n" +
      "                  }\n" +
      "              ]\n" +
      "          }, \n" +
      "          { \n" +
      "              \"rule_name\": \"autoreply-voice\", \n" +
      "              \"create_time\": 1423027971, \n" +
      "              \"reply_mode\": \"random_one\", \n" +
      "              \"keyword_list_info\": [ \n" +
      "                  { \n" +
      "                      \"type\": \"text\", \n" +
      "                      \"match_mode\": \"contain\", \n" +
      "                      \"content\": \"voice测试\"\n" +
      "                  }\n" +
      "              ], \n" +
      "              \"reply_list_info\": [ \n" +
      "                  { \n" +
      "                      \"type\": \"voice\", \n" +
      "                      \"content\": \"NESsxgHEvAcg3egJTtYj4uG1PTL6iPhratdWKDLAXYErhN6oEEfMdVyblWtBY5vp\"\n" +
      "                  }\n" +
      "              ]\n" +
      "          }, \n" +
      "          { \n" +
      "              \"rule_name\": \"autoreply-text\", \n" +
      "              \"create_time\": 1423027926, \n" +
      "              \"reply_mode\": \"random_one\", \n" +
      "              \"keyword_list_info\": [ \n" +
      "                  { \n" +
      "                      \"type\": \"text\", \n" +
      "                      \"match_mode\": \"contain\", \n" +
      "                      \"content\": \"text测试\"\n" +
      "                  }\n" +
      "              ], \n" +
      "              \"reply_list_info\": [ \n" +
      "                  { \n" +
      "                      \"type\": \"text\", \n" +
      "                      \"content\": \"hello!text!\"\n" +
      "                  }\n" +
      "              ]\n" +
      "          }, \n" +
      "          { \n" +
      "              \"rule_name\": \"autoreply-video\", \n" +
      "              \"create_time\": 1423027801, \n" +
      "              \"reply_mode\": \"random_one\", \n" +
      "              \"keyword_list_info\": [ \n" +
      "                  { \n" +
      "                      \"type\": \"text\", \n" +
      "                      \"match_mode\": \"equal\", \n" +
      "                      \"content\": \"video测试\"\n" +
      "                  }\n" +
      "              ], \n" +
      "              \"reply_list_info\": [ \n" +
      "                  { \n" +
      "                 \"type\": \"video\", \n" +
      "\"content\": \"http://61.182.133.153/vweixinp.tc.qq.com/1007_114bcede9a2244eeb5ab7f76d951df5f.f10.mp4?vkey=7183E5C952B16C3AB1991BA8138673DE1037CB82A29801A504B64A77F691BF9DF7AD054A9B7FE683&sha=0&save=1\"\n" +
      "                  }\n" +
      "              ]\n" +
      "          }\n" +
      "      ]\n" +
      "  }\n" +
      "}";

    WxMpCurrentAutoReplyInfo autoReplyInfo = WxMpCurrentAutoReplyInfo.fromJson(json);

    assertNotNull(autoReplyInfo);
    assertTrue(autoReplyInfo.getIsAddFriendReplyOpen());
    assertTrue(autoReplyInfo.getIsAutoReplyOpen());
    assertNotNull(autoReplyInfo.getAddFriendAutoReplyInfo());
    assertNotNull(autoReplyInfo.getMessageDefaultAutoReplyInfo());
    assertTrue(autoReplyInfo.getKeywordAutoReplyInfo().getList().size() > 0);

    System.out.println(autoReplyInfo);
  }

}
