package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConfigStorage;
import me.chanjar.weixin.mp.bean.tag.WxTagListUser;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.testng.*;
import org.testng.annotations.*;

import java.util.List;

/**
 * Created by Binary Wang on 2016/9/2.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpUserTagServiceImplTest {
  @Inject
  protected WxMpService wxService;

  private Long tagId = 2L;

  @Test
  public void testTagCreate() throws Exception {
    String tagName = "测试标签" + System.currentTimeMillis();
    WxUserTag res = this.wxService.getUserTagService().tagCreate(tagName);
    System.out.println(res);
    this.tagId = res.getId();
    Assert.assertEquals(tagName, res.getName());
  }

  @Test
  public void testTagGet() throws Exception {
    List<WxUserTag> res = this.wxService.getUserTagService().tagGet();
    System.out.println(res);
    Assert.assertNotNull(res);
  }

  @Test(dependsOnMethods = {"testTagCreate"})
  public void testTagUpdate() throws Exception {
    String tagName = "修改标签" + System.currentTimeMillis();
    Boolean res = this.wxService.getUserTagService().tagUpdate(this.tagId, tagName);
    System.out.println(res);
    Assert.assertTrue(res);
  }

  @Test(dependsOnMethods = {"testTagCreate"})
  public void testTagDelete() throws Exception {
    Boolean res = this.wxService.getUserTagService().tagDelete(this.tagId);
    System.out.println(res);
    Assert.assertTrue(res);
  }

  @Test
  public void testTagListUser() throws Exception {
    WxTagListUser res = this.wxService.getUserTagService().tagListUser(this.tagId, null);
    System.out.println(res);
    Assert.assertNotNull(res);
  }

  @Test
  public void testBatchTagging() throws Exception {
    String[] openids = new String[]{((TestConfigStorage) this.wxService.getWxMpConfigStorage()).getOpenid()};
    boolean res = this.wxService.getUserTagService().batchTagging(this.tagId, openids);
    System.out.println(res);
    Assert.assertTrue(res);
  }

  @Test
  public void testBatchUntagging() throws Exception {
    String[] openids = new String[]{((TestConfigStorage) this.wxService.getWxMpConfigStorage()).getOpenid()};
    boolean res = this.wxService.getUserTagService().batchUntagging(this.tagId, openids);
    System.out.println(res);
    Assert.assertTrue(res);
  }

  @Test
  public void testUserTagList() throws Exception {
    List<Long> res = this.wxService.getUserTagService().userTagList(
      ((TestConfigStorage) this.wxService.getWxMpConfigStorage()).getOpenid());
    System.out.println(res);
    Assert.assertNotNull(res);
  }
}
