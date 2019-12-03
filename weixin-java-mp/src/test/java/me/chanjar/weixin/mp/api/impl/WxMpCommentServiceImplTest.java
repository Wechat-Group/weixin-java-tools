package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpCommentService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.comment.WxMpCommentListVo;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Comment.LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * 测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-06-16
 */

@Test
@Guice(modules = ApiTestModule.class)
public class WxMpCommentServiceImplTest {
  @Inject
  private WxMpService wxService;

  @Test
  public void testOpen() throws WxErrorException {
    this.wxService.getCommentService().open(1, null);
    this.wxService.getCommentService().open(1, 0);
  }

  @Test
  public void testClose() throws WxErrorException {
    this.wxService.getCommentService().close(1000000001, null);
    this.wxService.getCommentService().close(1, 0);
  }

  @Test
  public void testList() throws WxErrorException {
    String expectedResponse = "{\n" +
      "    \"errcode\": 0,\n" +
      "    \"errmsg\": \"ok\",\n" +
      "    \"total\": 1,\n" +
      "    \"comment\": [\n" +
      "        {\n" +
      "            \"user_comment_id\": 1,\n" +
      "            \"openid\": \"OPENID\",\n" +
      "            \"create_time\": \"CREATE_TIME\",\n" +
      "            \"content\": \"CONTENT\",\n" +
      "            \"comment_type\": 1,\n" +
      "            \"reply\": {\n" +
      "                \"content\": \"CONTENT\",\n" +
      "                \"create_time\": \"CREATE_TIME\"\n" +
      "            }\n" +
      "        }\n" +
      "    ]\n" +
      "}";

    wxService = spy(wxService);
    WxMpCommentService commentService = new WxMpCommentServiceImpl(wxService);
    doReturn(expectedResponse).when(wxService).post(anyString(), anyString());

    final WxMpCommentListVo commentListVo = commentService.list(1, 1, 1, 1, 1);
    assertThat(commentListVo).isNotNull();
    System.out.println(commentListVo);
    assertThat(commentListVo.getTotal()).isEqualTo(1);
    assertThat(commentListVo.getComment()).isNotEmpty();

    assertThat(commentListVo.getComment().get(0).getReply()).isNotNull();
  }
}
