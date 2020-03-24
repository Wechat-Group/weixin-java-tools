package me.chanjar.weixin.mp.bean.template;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

/**
 * 测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-02-29
 */
public class WxMpTemplateIndustryTest {

    @Test
    public void testFromJson() {
      String json="{\"primary_industry\":{\"first_class\":\"IT科技\",\"second_class\":\"IT软件与服务\"},\"secondary_industry\":{\"first_class\":\"房地产\",\"second_class\":\"房地产|建筑\"}}";
      final WxMpTemplateIndustry industry = WxMpTemplateIndustry.fromJson(json);
      assertThat(industry).isNotNull();
      System.out.println(industry);
    }
}
