package me.chanjar.weixin.common.util;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 *
 * Created by Binary Wang on 2018/11/4.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class XmlUtilsTest {

  @Test(expectedExceptions = {RuntimeException.class})
  public void testXml2Map_xxe() {
    String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
      "<!DOCTYPE test [\n" +
      "<!ENTITY xxe SYSTEM \"file:///etc/passwd\">\n" +
      "<!ENTITY xxe2 SYSTEM \"http://localhost/test.php\">\n" +
      "]>\n" +
      "<xml></xml>";
    XmlUtils.xml2Map(xml);
  }

  @Test
  public void testXml2Map() {
    String xml = "<xml>\n" +
      "<CopyrightCheckResult>\n" +
      "<Count>2</Count>\n" +
      "<ResultList>\n" +
      "<item>\n" +
      "<ArticleIdx>1</ArticleIdx>\n" +
      "<UserDeclareState>0</UserDeclareState>\n" +
      "<AuditState>2</AuditState>\n" +
      "<OriginalArticleUrl><![CDATA[Url_1]]></OriginalArticleUrl>\n" +
      "<OriginalArticleType>1</OriginalArticleType>\n" +
      "<CanReprint>1</CanReprint>\n" +
      "<NeedReplaceContent>1</NeedReplaceContent>\n" +
      "<NeedShowReprintSource>1</NeedShowReprintSource>\n" +
      "</item>\n" +
      "<item>\n" +
      "<ArticleIdx>2</ArticleIdx>\n" +
      "<UserDeclareState>0</UserDeclareState>\n" +
      "<AuditState>2</AuditState>\n" +
      "<OriginalArticleUrl><![CDATA[Url_2]]></OriginalArticleUrl>\n" +
      "<OriginalArticleType>1</OriginalArticleType>\n" +
      "<CanReprint>1</CanReprint>\n" +
      "<NeedReplaceContent>1</NeedReplaceContent>\n" +
      "<NeedShowReprintSource>1</NeedShowReprintSource>\n" +
      "</item>\n" +
      "</ResultList>\n" +
      "<CheckState>2</CheckState>\n" +
      "</CopyrightCheckResult>\n" +
      "</xml>";

    final Map<String, Object> map = XmlUtils.xml2Map(xml);
    assertThat(map).isNotNull();
    final Map<String, Object> copyrightCheckResult = (Map<String, Object>) map.get("CopyrightCheckResult");
    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ((Map<String, Object>) copyrightCheckResult.get("ResultList")).get("item");

    assertThat(copyrightCheckResult)
      .isNotNull()
      .containsEntry("Count", "2")
      .containsEntry("CheckState", "2");

    assertThat(resultList.get(0)).containsEntry("ArticleIdx", "1")
      .containsEntry("UserDeclareState", "0")
      .containsEntry("AuditState", "2")
      .containsEntry("OriginalArticleUrl", "Url_1")
      .containsEntry("OriginalArticleType", "1")
      .containsEntry("CanReprint", "1")
      .containsEntry("NeedReplaceContent", "1")
      .containsEntry("NeedShowReprintSource", "1");

    assertThat(resultList.get(1)).containsEntry("ArticleIdx", "2")
      .containsEntry("UserDeclareState", "0")
      .containsEntry("AuditState", "2")
      .containsEntry("OriginalArticleUrl", "Url_2")
      .containsEntry("OriginalArticleType", "1")
      .containsEntry("CanReprint", "1")
      .containsEntry("NeedReplaceContent", "1")
      .containsEntry("NeedShowReprintSource", "1");
  }

  @Test
  public void testXml2Map_another() {
    String xml = "<xml> <ToUserName><![CDATA[gh_4d00ed8d6399]]></ToUserName> <FromUserName><![CDATA[oV5CrjpxgaGXNHIQigzNlgLTnwic]]></FromUserName> <CreateTime>1481013459</CreateTime> <MsgType><![CDATA[event]]></MsgType> <Event><![CDATA[PUBLISHJOBFINISH]]></Event> <PublishEventInfo> <publish_id>2247503051</publish_id> <publish_status>0</publish_status> <article_id><![CDATA[b5O2OUs25HBxRceL7hfReg-U9QGeq9zQjiDvy WP4Hq4]]></article_id> <article_detail> <count>1</count> <item> <idx>1</idx> <article_url><![CDATA[ARTICLE_URL]]></article_url> </item> <item> <idx>2</idx> <article_url><![CDATA[ARTICLE_URL_2]]></article_url> </item> </article_detail> </PublishEventInfo> </xml>";

    final Map<String, Object> map = XmlUtils.xml2Map(xml);
    assertThat(map).isNotNull()
      .containsEntry("ToUserName", "gh_4d00ed8d6399")
      .containsEntry("FromUserName", "oV5CrjpxgaGXNHIQigzNlgLTnwic")
      .containsEntry("CreateTime", "1481013459")
      .containsEntry("MsgType", "event");

    Map<String, Object> publishEventInfo = (Map<String, Object>) map.get("PublishEventInfo");
    assertThat(publishEventInfo).containsEntry("publish_id", "2247503051")
      .containsEntry("publish_status", "0")
      .containsEntry("article_id", "b5O2OUs25HBxRceL7hfReg-U9QGeq9zQjiDvy WP4Hq4");

    Map<String, Object> articleDetail = (Map<String, Object>) publishEventInfo.get("article_detail");
    assertThat(articleDetail).containsEntry("count", "1");
    List< Map<String, Object>> item = (List<Map<String, Object>>) articleDetail.get("item");
    assertThat(item.get(0)).containsEntry("idx", "1")
      .containsEntry("article_url", "ARTICLE_URL");

    assertThat(item.get(1)).containsEntry("idx", "2")
      .containsEntry("article_url", "ARTICLE_URL_2");
  }
}
