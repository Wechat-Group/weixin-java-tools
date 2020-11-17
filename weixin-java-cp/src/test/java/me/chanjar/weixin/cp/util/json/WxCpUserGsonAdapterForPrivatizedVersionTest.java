package me.chanjar.weixin.cp.util.json;

import me.chanjar.weixin.cp.bean.WxCpUser;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 * 企业微信（私有化版本getUser兼容测试）
 * </pre>
 *
 * @author 庄壮壮
 * @since 2020-06-16 09:36
 */
public class WxCpUserGsonAdapterForPrivatizedVersionTest {

  @Test
  public void testDeserialize() {
    final String userJson = "{\n" +
      "   \"errcode\": 0,\n" +
      "   \"errmsg\": \"ok\",\n" +
      "   \"userid\": \"zhangsan\",\n" +
      "   \"name\": \"李四\",\n" +
      "   \"department\": [1, 2],\n" +
      "   \"order\": [2, 10],\n" +
      "   \"position\": \"后台工程师1\",\n" +
      "   \"positions\": [\"后台工程师1\",\"后台工程师2\"],\n" +
      "   \"mobile\": \"15913215421\",\n" +
      "   \"hide_mobile\": 0,\n" +
      "   \"gender\": \"1\",\n" +
      "   \"email\": \"zhangsan@gzdev.com\",\n" +
      "   \"is_leader_in_dept\": [1, 0],\n" +
      "   \"avatar\": \"http://wx.qlogo.cn/mmopen/ajNVdqHZLLA3WJ6DSZUfiakYe37PKnQhBIeOQBO4czqrnZDS79FH5Wm5m4X69TBicnHFlhiafvDwklOpZeXYQQ2icg/0\",\n" +
      "   \"telephone\": \"020-123456\",\n" +
      "   \"english_name\": \"jackzhang\",\n" +
      "   \"extattr\": {\"attrs\":[{\"name\":\"爱好\",\"value\":\"旅游\"},{\"name\":\"卡号\",\"value\":\"1234567234\"}]},\n" +
      "   \"status\": 1,\n" +
      "   \"enable\": 0,\n" +
      "   \"qr_code\": \"https://wwlocal.qq.com/wework_admin/userQRCode?vcode=vc2140a8b3c6207c74&lvc=vcf6f1acfdc4b45088\"\n" +
      "}";

    final WxCpUser user = WxCpUser.fromJson(userJson);
    assertThat(user).isNotNull();

    // test order
    assertThat(user.getOrders()).isNotEmpty();
    assertThat(user.getOrders().length).isEqualTo(2);
    assertThat(user.getOrders()[0]).isEqualTo(2);
    assertThat(user.getOrders()[1]).isEqualTo(10);

    // test english name
    assertThat(user.getEnglishName()).isEqualTo("jackzhang");

    // test extattrs
    assertThat(user.getExtAttrs()).isNotEmpty();
    final WxCpUser.Attr extraAttr1 = user.getExtAttrs().get(0);
    assertThat(extraAttr1.getName()).isEqualTo("爱好");
    assertThat(extraAttr1.getTextValue()).isEqualTo("旅游");
    final WxCpUser.Attr extraAttr2 = user.getExtAttrs().get(1);
    assertThat(extraAttr2.getName()).isEqualTo("卡号");
    assertThat(extraAttr2.getTextValue()).isEqualTo("1234567234");

    // test position
    assertThat(user.getPosition()).isEqualTo("后台工程师1");
    // test positions
    assertThat(user.getPositions()).isNotEmpty();
    assertThat(user.getPositions().length).isEqualTo(2);
    assertThat(user.getPositions()[0]).isEqualTo("后台工程师1");
    assertThat(user.getPositions()[1]).isEqualTo("后台工程师2");
  }

  @Test
  public void testSerialize() {
    WxCpUser user = new WxCpUser();
    user.setOrders(new Integer[]{1, 2});
    user.setPositions(new String[]{"后台工程师1", "后台工程师2"});
    user.setEnglishName("jackson");
    WxCpUser.Attr attr1 = new WxCpUser.Attr();
    attr1.setName("爱好").setTextValue("旅游");
    WxCpUser.Attr attr2 = new WxCpUser.Attr();
    attr2.setName("卡号").setTextValue("1234567234");

    user.addExtAttr(attr1);
    user.addExtAttr(attr2);

    assertThat(user.toJson()).isEqualTo("{\"order\":[1,2],\"positions\":[\"后台工程师1\",\"后台工程师2\"],\"english_name\":\"jackson\",\"extattr\":{\"attrs\":[{\"name\":\"爱好\",\"value\":\"旅游\"},{\"name\":\"卡号\",\"value\":\"1234567234\"}]},\"external_profile\":{}}");
  }
}
