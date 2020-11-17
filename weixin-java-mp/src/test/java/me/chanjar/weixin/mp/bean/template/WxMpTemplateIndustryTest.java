package me.chanjar.weixin.mp.bean.template;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-02-29
 */
public class WxMpTemplateIndustryTest {

  @Test
  public void testFromJson() {
    String json = "{\"primary_industry\":{\"first_class\":\"IT科技\",\"second_class\":\"互联网|电子商务\"}," +
      "\"secondary_industry\":{\"first_class\":\"房地产\",\"second_class\":\"房地产|建筑\"}}";
    final WxMpTemplateIndustry industry = WxMpTemplateIndustry.fromJson(json);
    assertThat(industry).isNotNull();
    System.out.println(industry);
  }

  @Test
  public void testFromJson_another_example() {
    String json = "{\"primary_industry\":{\"first_class\":\"金融业\",\"second_class\":\"基金理财信托\"}," +
      "\"secondary_industry\":{\"first_class\":\"房地产\",\"second_class\":\"建筑\"}}";
    final WxMpTemplateIndustry industry = WxMpTemplateIndustry.fromJson(json);
    assertThat(industry).isNotNull();
    System.out.println(industry);
  }
}
