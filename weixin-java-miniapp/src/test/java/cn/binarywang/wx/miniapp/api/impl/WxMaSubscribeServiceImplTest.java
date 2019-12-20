package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaSubscribeService;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-12-15
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaSubscribeServiceImplTest {
  @Inject
  protected WxMaService wxService;

  @Test
  public void testGetPubTemplateTitleList() throws WxErrorException {
    this.wxService.getSubscribeService().getPubTemplateTitleList(new String[]{"2", "616"}, 0, 1);
  }

  @Test
  public void testGetPubTemplateKeyWordsById() throws WxErrorException {
    final List<WxMaSubscribeService.PubTemplateKeyword> result = this.wxService.getSubscribeService().getPubTemplateKeyWordsById("99");
    System.out.println(result);
  }

  @Test
  public void testAddTemplate() throws WxErrorException {
    final String templateId = this.wxService.getSubscribeService().addTemplate("401", Lists.newArrayList(1, 2), "测试数据");
    System.out.println(templateId);
  }

  @Test
  public void testGetTemplateList() throws WxErrorException {
    final List<WxMaSubscribeService.TemplateInfo> templateList = this.wxService.getSubscribeService().getTemplateList();
    System.out.println(templateList);
  }

  @Test
  public void testDelTemplate() throws WxErrorException {
    this.wxService.getSubscribeService().delTemplate("priTmplId");
  }

  @Test
  public void testGetCategory() throws WxErrorException {
    final List<WxMaSubscribeService.CategoryData> categoryData = this.wxService.getSubscribeService().getCategory();
    assertThat(categoryData).isNotNull();
    System.out.println(categoryData);
  }
}
