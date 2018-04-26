package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaCodeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.code.WxMaCategory;
import cn.binarywang.wx.miniapp.bean.code.WxMaCodeAuditStatus;
import cn.binarywang.wx.miniapp.bean.code.WxMaCodeCommitRequest;
import cn.binarywang.wx.miniapp.bean.code.WxMaCodeExtConfig;
import cn.binarywang.wx.miniapp.bean.code.WxMaCodeSubmitAuditRequest;
import cn.binarywang.wx.miniapp.bean.code.WxMaCodeVersionDistribution;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.inject.Inject;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-26 20:18
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaCodeServiceImplTest {
  @Inject
  private WxMaService wxService;
  @Inject
  private WxMaConfig wxMaConfig;

  @Test
  public void testGetCategory() throws Exception {
    List<WxMaCategory> categories = wxService.getCodeService().getCategory();
    System.out.println(String.valueOf(categories));
  }

  @Test
  public void testCommit() throws Exception {
    String themeColor = "#0074d9";
    String themeFontColor = "#ffffff";

    Map<String, Object> ext = new HashMap<>();
    ext.put("appName", "xxx");
    ext.put("verified", true);
    ext.put("navigationBarBackgroundColor", themeColor);
    ext.put("navigationBarTextStyle", themeFontColor);
    ext.put("companyId", 4128);
    ext.put("companyFullName", "xxx有限公司");

    WxMaCodeService wxMaCodeService = wxService.getCodeService();
    WxMaCodeCommitRequest commitRequest = WxMaCodeCommitRequest
      .builder()
      .templateId(6L)
      .userVersion("v0.1.0")
      .userDesc("init")
      .extConfig(WxMaCodeExtConfig.builder()
        .extAppid(wxMaConfig.getAppid())
        .extEnable(true)
        .ext(ext)
        .window(
          WxMaCodeExtConfig.PageConfig
            .builder()
            .navigationBarBackgroundColor(themeColor)
            .navigationBarTextStyle(themeFontColor)
            .build()
        )
        .build())
      .build();
    wxMaCodeService.commit(commitRequest);
  }

  @Test
  public void testGetQrCode() throws Exception {
    byte[] qrCode = wxService.getCodeService().getQrCode();
    assertTrue(qrCode.length > 0);
  }

  @Test
  public void testGetPage() throws Exception {
    List<String> pageList = wxService.getCodeService().getPage();
    System.out.println(String.valueOf(pageList));
  }

  @Test
  public void testSubmitAudit() throws Exception {
    WxMaCodeSubmitAuditRequest auditRequest = WxMaCodeSubmitAuditRequest
      .builder()
      .itemList(Arrays.asList(
        WxMaCategory
          .builder()
          .address("pages/logs/logs")
          .tag("工具 效率")
          .firstClass("工具")
          .firstId(287L)
          .secondClass("效率")
          .secondId(616L)
          .title("日志")
          .build()
      )).build();
    long auditId = wxService.getCodeService().submitAudit(auditRequest);
    assertTrue(auditId > 0);
    // 421937937
    System.out.println(auditId);
  }

  @Test
  public void testGetAuditStatus() throws Exception {
    WxMaCodeAuditStatus auditStatus = wxService.getCodeService().getAuditStatus(421937937L);
    System.out.println(auditStatus);
    assertNotNull(auditStatus);
  }

  @Test
  public void testGetLatestAuditStatus() throws Exception {
    WxMaCodeAuditStatus auditStatus = wxService.getCodeService().getLatestAuditStatus();
    System.out.println(auditStatus);
    assertNotNull(auditStatus);
  }

  @Test
  public void testRelease() throws Exception {
    wxService.getCodeService().release();
  }

  @Test
  public void testChangeVisitStatus() throws Exception {
    wxService.getCodeService().changeVisitStatus("open");
  }

  @Test
  public void testRevertCodeRelease() throws Exception {
    wxService.getCodeService().revertCodeRelease();
  }

  @Test
  public void testGetSupportVersion() throws Exception {
    WxMaCodeVersionDistribution distribution = wxService.getCodeService().getSupportVersion();
    System.out.println(distribution);
  }

  @Test
  public void testSetSupportVersion() throws Exception {
    wxService.getCodeService().setSupportVersion("1.2.0");
  }

  @Test
  public void testUndoCodeAudit() throws Exception {

  }
}
