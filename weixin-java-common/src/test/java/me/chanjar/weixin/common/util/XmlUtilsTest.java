package me.chanjar.weixin.common.util;

import java.util.List;
import java.util.Map;

import org.testng.annotations.*;

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
    assertThat(copyrightCheckResult).isNotNull();

    assertThat(copyrightCheckResult.get("Count")).isEqualTo("2");
    assertThat(copyrightCheckResult.get("CheckState")).isEqualTo("2");

    assertThat(resultList.get(0).get("ArticleIdx")).isEqualTo("1");
    assertThat(resultList.get(0).get("UserDeclareState")).isEqualTo("0");
    assertThat(resultList.get(0).get("AuditState")).isEqualTo("2");
    assertThat(resultList.get(0).get("OriginalArticleUrl")).isEqualTo("Url_1");
    assertThat(resultList.get(0).get("OriginalArticleType")).isEqualTo("1");
    assertThat(resultList.get(0).get("CanReprint")).isEqualTo("1");
    assertThat(resultList.get(0).get("NeedReplaceContent")).isEqualTo("1");
    assertThat(resultList.get(0).get("NeedShowReprintSource")).isEqualTo("1");

    assertThat(resultList.get(1).get("ArticleIdx")).isEqualTo("2");
    assertThat(resultList.get(1).get("UserDeclareState")).isEqualTo("0");
    assertThat(resultList.get(1).get("AuditState")).isEqualTo("2");
    assertThat(resultList.get(1).get("OriginalArticleUrl")).isEqualTo("Url_2");
    assertThat(resultList.get(1).get("OriginalArticleType")).isEqualTo("1");
    assertThat(resultList.get(1).get("CanReprint")).isEqualTo("1");
    assertThat(resultList.get(1).get("NeedReplaceContent")).isEqualTo("1");
    assertThat(resultList.get(1).get("NeedShowReprintSource")).isEqualTo("1");
  }
}
