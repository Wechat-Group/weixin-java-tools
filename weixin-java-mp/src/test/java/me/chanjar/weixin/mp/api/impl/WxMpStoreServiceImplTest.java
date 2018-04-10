package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.store.WxMpStoreBaseInfo;
import me.chanjar.weixin.mp.bean.store.WxMpStoreInfo;
import me.chanjar.weixin.mp.bean.store.WxMpStoreListResult;
import org.testng.annotations.*;

import java.math.BigDecimal;
import java.util.List;

import static org.testng.AssertJUnit.*;

/**
 * Created by Binary Wang on 2016-09-23.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpStoreServiceImplTest {
  @Inject
  private WxMpService wxMpService;

  /**
   * Test method for {@link WxMpStoreServiceImpl#add(WxMpStoreBaseInfo)}.
   */
  public void testAdd() throws WxErrorException {
    this.wxMpService.getStoreService()
      .add(WxMpStoreBaseInfo.builder()
        .businessName("haha")
        .branchName("abc")
        .province("aaa")
        .district("aaa")
        .telephone("122")
        .address("abc")
        .categories(new String[]{"美食,江浙菜"})
        .longitude(new BigDecimal("115.32375"))
        .latitude(new BigDecimal("25.097486"))
        .city("aaa")
        .build());
    // 以下运行会抛异常
    this.wxMpService.getStoreService().add(WxMpStoreBaseInfo.builder().build());
  }

  public void testUpdate() throws WxErrorException {
    this.wxMpService.getStoreService()
      .update(WxMpStoreBaseInfo.builder()
        .poiId("291503654")
        .telephone("020-12345678")
        .sid("aaa")
        .avgPrice(35)
        .openTime("8:00-20:00")
        .special("免费wifi，外卖服务")
        .introduction("麦当劳是全球大型跨国连锁餐厅，1940 年创立于美国，在世界上大约拥有3 万间分店。主要售卖汉堡包，以及薯条、炸鸡、汽水、冰品、沙拉、水果等快餐食品")
        .build());
  }

  public void testGet() throws WxErrorException {
    WxMpStoreBaseInfo result = this.wxMpService.getStoreService().get("291503654");
    assertNotNull(result);
    System.err.println(result);
  }

  public void testDelete() throws WxErrorException {
    this.wxMpService.getStoreService().delete("463558057");
  }

  public void testListCategories() throws WxErrorException {
    List<String> result = this.wxMpService.getStoreService().listCategories();
    assertNotNull(result);
    System.err.println(result);
  }

  public void testList() throws WxErrorException {
    WxMpStoreListResult result = this.wxMpService.getStoreService().list(0, 10);
    assertNotNull(result);
    System.err.println(result);
  }

  public void testListAll() throws WxErrorException {
    List<WxMpStoreInfo> list = this.wxMpService.getStoreService().listAll();
    assertNotNull(list);
    System.err.println(list.size());
    System.err.println(list);
  }

}
