package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.template.WxMaTemplateAddResult;
import cn.binarywang.wx.miniapp.bean.template.WxMaTemplateLibraryGetResult;
import cn.binarywang.wx.miniapp.bean.template.WxMaTemplateLibraryListResult;
import cn.binarywang.wx.miniapp.bean.template.WxMaTemplateListResult;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import org.assertj.core.util.Lists;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

@Test
@Guice(modules = ApiTestModule.class)
public class WxMaTemplateServiceImplTest {
  @Inject
  protected WxMaService wxService;

  @Test
  public void testFindTemplateLibraryList() throws Exception {
    WxMaTemplateLibraryListResult result = this.wxService.getTemplateService().findTemplateLibraryList(0, 20);
    Assert.assertEquals(20, result.getList().size());
  }

  @Test
  public void testFindTemplateLibraryKeywordList() throws Exception {
    WxMaTemplateLibraryGetResult result = this.wxService.getTemplateService().findTemplateLibraryKeywordList("AT0004");
    Assert.assertEquals("AT0004", result.getId());
    Assert.assertEquals("交易提醒", result.getTitle());
    Assert.assertEquals(100, result.getKeywordList().size());
  }

  @Test
  public void testAddTemplate() throws Exception {
    List<Integer> list = Lists.newArrayList();
    list.add(1);
    list.add(20);
    list.add(84);

    WxMaTemplateAddResult result = this.wxService.getTemplateService().addTemplate("AT0004", list);
    Assert.assertNotNull(result.getTemplateId());
    System.out.println(result);
  }

  @Test
  public void testFindTemplateList() throws Exception {
    WxMaTemplateListResult result = this.wxService.getTemplateService().findTemplateList(0, 20);
    System.out.println(result);
  }

  @Test
  public void testDelTemplate() throws Exception {

    //add
    List<Integer> list = Lists.newArrayList();
    list.add(1);
    list.add(20);
    list.add(84);

    WxMaTemplateAddResult result = this.wxService.getTemplateService().addTemplate("AT0004", list);

    //delete
    this.wxService.getTemplateService().delTemplate(result.getTemplateId());
  }
}
