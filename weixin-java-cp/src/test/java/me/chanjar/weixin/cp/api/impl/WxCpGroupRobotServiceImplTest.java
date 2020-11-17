package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpGroupRobotService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 微信群机器人消息发送api 单元测试
 *
 * @author yr
 * @date 2020-08-20
 */
@Slf4j
@Guice(modules = ApiTestModule.class)
public class WxCpGroupRobotServiceImplTest {
  @Inject
  protected WxCpService wxService;

  private WxCpGroupRobotService robotService;

  @BeforeTest
  public void setup() {
    robotService = wxService.getGroupRobotService();
  }

  @Test
  public void testSendText() throws WxErrorException {
    robotService.sendText("Hello World", null, null);
  }

  @Test
  public void testSendMarkDown() throws WxErrorException {
    String content = "实时新增用户反馈<font color=\"warning\">132例</font>，请相关同事注意。\n" +
      ">类型:<font color=\"comment\">用户反馈</font> \n" +
      ">普通用户反馈:<font color=\"comment\">117例</font> \n" +
      ">VIP用户反馈:<font color=\"comment\">15例</font>";
    robotService.sendMarkdown(content);
  }

  @Test
  public void testSendImage() throws WxErrorException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mm.jpeg");
    assert inputStream != null;
    String base64 = FileUtils.imageToBase64ByStream(inputStream);
    String md5 = "1cb2e787063d66e24f5f89e7fc267a4d";
    robotService.sendImage(base64, md5);
  }

  @Test
  public void testSendNews() throws WxErrorException {
    NewArticle article = new NewArticle("图文消息测试", "hello world", "http://www.baidu.com",
      "http://res.mail.qq.com/node/ww/wwopenmng/images/independent/doc/test_pic_msg1.png", null);
    robotService.sendNews(Stream.of(article).collect(Collectors.toList()));
  }
}
