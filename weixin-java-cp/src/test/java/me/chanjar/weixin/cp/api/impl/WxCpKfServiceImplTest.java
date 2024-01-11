package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.util.XmlUtils;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.kf.*;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import me.chanjar.weixin.cp.util.xml.XStreamTransformer;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.InputStream;

/**
 * WxCpKfServiceImpl-测试类
 * 需要用到专门的secret
 * <a href="https://developer.work.weixin.qq.com/document/path/94638">官方文档1</a>
 * <a href="https://kf.weixin.qq.com/api/doc/path/93304#secret">官方文档2</a>
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

  /**
   * 测试回调事件
   * https://developer.work.weixin.qq.com/document/path/94670
   *
   * @throws Exception
   */
  @Test(priority = 6)
  public void testEvent() throws Exception {

    String xml = "<xml>\n" +
      "   <ToUserName><![CDATA[ww12345678910]]></ToUserName>\n" +
      "   <CreateTime>1348831860</CreateTime>\n" +
      "   <MsgType><![CDATA[event]]></MsgType>\n" +
      "   <Event><![CDATA[kf_msg_or_event]]></Event>\n" +
      "   <Token><![CDATA[ENCApHxnGDNAVNY4AaSJKj4Tb5mwsEMzxhFmHVGcra996NR]]></Token>\n" +
      "   <OpenKfId><![CDATA[wkxxxxxxx]]></OpenKfId>\n" +
      "</xml>";

    WxCpXmlMessage xmlMsg = XStreamTransformer.fromXml(WxCpXmlMessage.class, xml);
    xmlMsg.setAllFieldsMap(XmlUtils.xml2Map(xml));
    System.out.println(WxCpGsonBuilder.create().toJson(xmlMsg));

    /**
     * 微信客服事件推送
     * @see WxConsts.EventType.KF_MSG_OR_EVENT
     */
    System.out.println("token：" + xmlMsg.getToken());
    System.out.println("openKfId：" + xmlMsg.getOpenKfId());
  }

}
