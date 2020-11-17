package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.live.WxMaLiveGoodInfo;
import cn.binarywang.wx.miniapp.bean.live.WxMaLiveResult;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;

import static org.testng.Assert.assertNotNull;

/**
 * 测试直播商品管理相关的接口
 *
 * @author <a href="https://github.com/lipengjun92">lipengjun (939961241@qq.com)</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaLiveGoodsServiceImplTest {

  @Inject
  private WxMaService wxService;

  @Test
  public void addGoods() throws Exception {
    //上传临时素材
    WxMediaUploadResult mediaUpload = this.wxService.getMediaService().uploadMedia("image", new File("E:\\1.png"));

    WxMaLiveGoodInfo goods = new WxMaLiveGoodInfo();
    goods.setCoverImgUrl(mediaUpload.getMediaId());
    goods.setName("宫廷奢华真丝四件套");
    goods.setPrice("1599");
    goods.setPrice2("0");
    goods.setPriceType(1);
    goods.setUrl("pages/goods/goods?id=b7c4fbf95493bd294054fe4296d0d9ad");
    WxMaLiveResult liveResult = this.wxService.getLiveGoodsService().addGoods(goods);
    assertNotNull(liveResult);
    System.out.println(liveResult.toString());
  }

  @Test
  public void resetAudit() throws Exception {
    boolean result = this.wxService.getLiveGoodsService().resetAudit(715138516, 9);
    System.out.println(result);
  }

  @Test
  public void auditGoods() throws Exception {
    String result = this.wxService.getLiveGoodsService().auditGoods(9);
    System.out.println(result);
  }

  @Test
  public void deleteGoods() throws Exception {
    boolean result = this.wxService.getLiveGoodsService().deleteGoods(9);
    System.out.println(result);
  }

  @Test
  public void updateGoods() throws Exception {

    WxMaLiveGoodInfo goods = new WxMaLiveGoodInfo();
    goods.setGoodsId(8);
    goods.setName("宫廷奢华真丝四件套");
    goods.setCoverImgUrl("http://mmbiz.qpic.cn/mmbiz_png/omYktZNGamuUQE0WPVfqdnLV61JDhluXOac7PiaoZeticFpcR7wvicC0aXUC2VXkl7r1gN0QSKosv2satn6oCFeiaQ/0");
    goods.setPrice("2299");
    goods.setPrice2("0");
    goods.setPriceType(1);
    goods.setUrl("pages/goods/goods?id=b7c4fbf95493bd294054fe4296d0d9ad");
    boolean maLiveInfo = this.wxService.getLiveGoodsService().updateGoods(goods);
    System.out.println(maLiveInfo);
  }

  @Test
  public void getGoodsWareHouse() throws Exception {
    WxMaLiveResult liveResult = this.wxService.getLiveGoodsService().getGoodsWareHouse(Arrays.asList(1, 2));
    assertNotNull(liveResult);
    System.out.println(liveResult.toString());
  }

  @Test
  public void getApprovedGoods() throws Exception {
    WxMaLiveResult liveResult = this.wxService.getLiveGoodsService().getApprovedGoods(0, 4, 2);
    assertNotNull(liveResult);
    System.out.println(liveResult.toString());
  }
}
