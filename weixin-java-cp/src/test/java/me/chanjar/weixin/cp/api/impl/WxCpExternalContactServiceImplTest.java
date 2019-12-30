package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpUserExternalContactInfo;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertNotNull;

@Guice(modules = ApiTestModule.class)
public class WxCpExternalContactServiceImplTest {
  @Inject
  private WxCpService wxCpService;
  @Inject
  protected ApiTestModule.WxXmlCpInMemoryConfigStorage configStorage;
  private String userId = "someone" + System.currentTimeMillis();

  @Test
  public void testGetExternalContact() throws WxErrorException {
    String externalUserId = this.configStorage.getExternalUserId();
    WxCpUserExternalContactInfo result = this.wxCpService.getExternalContactService().getExternalContact(externalUserId);
    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  public void testListExternalContacts() throws WxErrorException {
    String userId = this.configStorage.getUserId();
    List<String> ret = this.wxCpService.getExternalContactService().listExternalContacts(userId);
    System.out.println(ret);
    assertNotNull(ret);
  }

  @Test
  public void testListExternalWithPermission() throws WxErrorException {
    List<String> ret = this.wxCpService.getExternalContactService().listFollowUser();
    System.out.println(ret);
    assertNotNull(ret);
  }
}
