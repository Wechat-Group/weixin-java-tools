package cn.binarywang.wx.miniapp.util.json;

import cn.binarywang.wx.miniapp.bean.WxMaTemplateData;
import cn.binarywang.wx.miniapp.bean.WxMaUniformMessage;
import com.google.gson.JsonParser;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 *
 * Created by Binary Wang on 2018/9/23.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaUniformMessageGsonAdapterTest {

  @Test
  public void testSerialize_mp() {
    WxMaUniformMessage message = WxMaUniformMessage.builder()
      .isMpTemplateMsg(true)
      .toUser("OPENID")
      .appid("APPID")
      .templateId("TEMPLATE_ID")
      .url("http://weixin.qq.com/download")
      .miniProgram(new WxMaUniformMessage.MiniProgram("xiaochengxuappid12345", "index?foo=bar", false, false))
      .build();
    message.addData(new WxMaTemplateData("first", "恭喜你购买成功！", "#173177"))
      .addData(new WxMaTemplateData("keyword1", "巧克力", "#173177"))
      .addData(new WxMaTemplateData("keyword2", "39.8元", "#173177"))
      .addData(new WxMaTemplateData("keyword3", "2014年9月22日", "#173177"))
      .addData(new WxMaTemplateData("remark", "欢迎再次购买！", "#173177"));

    assertThat(message.toJson()).isEqualTo(new JsonParser().parse("{\n" +
      "    \"touser\":\"OPENID\",\n" +
      "    \"mp_template_msg\":{\n" +
      "        \"appid\":\"APPID\",\n" +
      "        \"template_id\":\"TEMPLATE_ID\",\n" +
      "        \"url\":\"http://weixin.qq.com/download\",\n" +
      "        \"miniprogram\":{\n" +
      "            \"appid\":\"xiaochengxuappid12345\",\n" +
      "            \"pagepath\":\"index?foo=bar\"\n" +
      "        },\n" +
      "        \"data\":{\n" +
      "            \"first\":{\n" +
      "                \"value\":\"恭喜你购买成功！\",\n" +
      "                \"color\":\"#173177\"\n" +
      "            },\n" +
      "            \"keyword1\":{\n" +
      "                \"value\":\"巧克力\",\n" +
      "                \"color\":\"#173177\"\n" +
      "            },\n" +
      "            \"keyword2\":{\n" +
      "                \"value\":\"39.8元\",\n" +
      "                \"color\":\"#173177\"\n" +
      "            },\n" +
      "            \"keyword3\":{\n" +
      "                \"value\":\"2014年9月22日\",\n" +
      "                \"color\":\"#173177\"\n" +
      "            },\n" +
      "            \"remark\":{\n" +
      "                \"value\":\"欢迎再次购买！\",\n" +
      "                \"color\":\"#173177\"\n" +
      "            }\n" +
      "        }\n" +
      "    }\n" +
      "}").getAsJsonObject().toString());
  }

  @Test
  public void testSerialize_ma() {
    WxMaUniformMessage message = WxMaUniformMessage.builder()
      .isMpTemplateMsg(false)
      .toUser("OPENID")
      .page("page/page/index")
      .templateId("TEMPLATE_ID")
      .formId("FORMID")
      .emphasisKeyword("keyword1.DATA")
      .build();
    message.addData(new WxMaTemplateData("keyword1", "339208499"))
      .addData(new WxMaTemplateData("keyword2", "2015年01月05日 12:30"))
      .addData(new WxMaTemplateData("keyword3", "腾讯微信总部"))
      .addData(new WxMaTemplateData("keyword4", "广州市海珠区新港中路397号"));

    assertThat(message.toJson()).isEqualTo(new JsonParser().parse("{\n" +
      "    \"touser\":\"OPENID\",\n" +
      "    \"weapp_template_msg\":{\n" +
      "        \"template_id\":\"TEMPLATE_ID\",\n" +
      "        \"page\":\"page/page/index\",\n" +
      "        \"form_id\":\"FORMID\",\n" +
      "        \"data\":{\n" +
      "            \"keyword1\":{\n" +
      "                \"value\":\"339208499\"\n" +
      "            },\n" +
      "            \"keyword2\":{\n" +
      "                \"value\":\"2015年01月05日 12:30\"\n" +
      "            },\n" +
      "            \"keyword3\":{\n" +
      "                \"value\":\"腾讯微信总部\"\n" +
      "            },\n" +
      "            \"keyword4\":{\n" +
      "                \"value\":\"广州市海珠区新港中路397号\"\n" +
      "            }\n" +
      "        },\n" +
      "        \"emphasis_keyword\":\"keyword1.DATA\"\n" +
      "    }\n" +
      "}").getAsJsonObject().toString());
  }
}
