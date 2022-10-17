package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.kf.*;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.InputStream;

/**
 * WxCpKfServiceImpl-测试类
 * 需要用到专门的 secret https://kf.weixin.qq.com/api/doc/path/93304#secret
 *
 * @author Fu  created on  2022/1/19 20:12
 */
@Guice(modules = ApiTestModule.class)
public class WxCpKfServiceImplTest {

  @Inject
  private WxCpService wxService;

  private static String kfid = "wkPzhXVAAAJD9oR75LrO1DmURSOUFBIg";

  /**
   * Test account add.
   *
   * @throws Exception the exception
   */
  @Test(priority = 1)
  public void testAccountAdd() throws Exception {
    try (InputStream in = ClassLoader.getSystemResourceAsStream("mm.jpeg")) {
      WxMediaUploadResult result = this.wxService.getMediaService().upload(WxConsts.MediaFileType.IMAGE, "jpeg", in);
      String mediaId = result.getMediaId();
      WxCpKfAccountAdd add = new WxCpKfAccountAdd();
      add.setMediaId(mediaId);
      add.setName("kefu01");
      WxCpKfAccountAddResp resp = this.wxService.getKfService().addAccount(add);
      System.out.println(resp);
      kfid = resp.getOpenKfid();
    }
  }

  /**
   * Test account upd.
   *
   * @throws Exception the exception
   */
  @Test(priority = 2)
  public void testAccountUpd() throws Exception {
    WxCpKfAccountUpd upd = new WxCpKfAccountUpd();
    upd.setOpenKfid(kfid);
    upd.setName("kefu01-upd");
    WxCpBaseResp resp = this.wxService.getKfService().updAccount(upd);
    System.out.println(resp);
  }

  /**
   * Test account list.
   *
   * @throws Exception the exception
   */
  @Test(priority = 3)
  public void testAccountList() throws Exception {
    WxCpKfAccountListResp resp = this.wxService.getKfService().listAccount(0, 10);
    System.out.println(resp);
  }

  /**
   * Test account link.
   *
   * @throws Exception the exception
   */
  @Test(priority = 4)
  public void testAccountLink() throws Exception {
    WxCpKfAccountLink link = new WxCpKfAccountLink();
    link.setOpenKfid(kfid);
    link.setScene("scene");
    WxCpKfAccountLinkResp resp = this.wxService.getKfService().getAccountLink(link);
    System.out.println(resp);
  }

  /**
   * Test account del.
   *
   * @throws Exception the exception
   */
  @Test(priority = 5)
  public void testAccountDel() throws Exception {
    WxCpKfAccountDel del = new WxCpKfAccountDel();
    del.setOpenKfid(kfid);
    WxCpBaseResp resp = this.wxService.getKfService().delAccount(del);
    System.out.println(resp);
  }

}
