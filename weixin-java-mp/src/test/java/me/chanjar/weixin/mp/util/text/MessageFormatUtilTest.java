package me.chanjar.weixin.mp.util.text;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * 文本格式化工具--测试类
 *
 * @Created by WcJun
 * @date 2018/04/13
 */
public class MessageFormatUtilTest {

  /**
   * 测试format()方法
   *
   * @throws Exception
   */
  @Test
  public void testFormat() throws Exception {
    //格式化文本内容
    //格式采用 {index} 的方式 index 从0开始
    //类似于 String.format()的 %s || %d 一样
    String content = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code&grant_type=authorization_code";
    //格式化后的文本内容
    String contentFormat = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxb81e8c51e4ac972d&secret=wx12345678987654321&code=wx12345678987654321&grant_type=authorization_code&grant_type=authorization_code";
    //格式化填充的三个参数
    String appId = "wxb81e8c51e4ac972d";
    String appSecret = "wx12345678987654321";
    String code = "wx12345678987654321";
    assertEquals(contentFormat, MessageFormatUtil.format(content, appId, appSecret, code));
  }

  /**
   * 测试参数为空的情形
   */
  @Test(expectedExceptions = NullPointerException.class)
  public void testFormatWithNullParam() {
    String appId = "wxb81e8c51e4ac972d";
    String appSecret = "wx12345678987654321";
    MessageFormatUtil.format(null, appId, appSecret);
  }
}
