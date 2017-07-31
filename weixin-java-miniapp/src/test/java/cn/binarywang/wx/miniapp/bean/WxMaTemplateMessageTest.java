package cn.binarywang.wx.miniapp.bean;

import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaTemplateMessageTest {
  @Test
  public void testToJson() throws Exception {
    WxMaTemplateMessage tm = WxMaTemplateMessage.newBuilder()
      .toUser("OPENID")
      //.color("aaaaa")
      .formId("FORMID")
      .page("index")
      .data(Lists.newArrayList(
        new WxMaTemplateMessage.Data("keyword1", "339208499", "#173177"),
        new WxMaTemplateMessage.Data("keyword2", "2015年01月05日12:30", "#173177"),
        new WxMaTemplateMessage.Data("keyword3", "粤海喜来登酒店", "#173177"),
        new WxMaTemplateMessage.Data("keyword4", "广州市天河区天河路208号", "#173177")))
      .templateId("TEMPLATE_ID")
      .emphasisKeyword("keyword1.DATA")
      .build();

    assertEquals(tm.toJson(), "{\"touser\":\"OPENID\",\"template_id\":\"TEMPLATE_ID\",\"page\":\"index\",\"form_id\":\"FORMID\",\"emphasis_keyword\":\"keyword1.DATA\",\"data\":{\"keyword1\":{\"value\":\"339208499\",\"color\":\"#173177\"},\"keyword2\":{\"value\":\"2015年01月05日12:30\",\"color\":\"#173177\"},\"keyword3\":{\"value\":\"粤海喜来登酒店\",\"color\":\"#173177\"},\"keyword4\":{\"value\":\"广州市天河区天河路208号\",\"color\":\"#173177\"}}}");
  }

}
