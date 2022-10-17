package me.chanjar.weixin.cp.api;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.external.WxCpUserExternalUnassignList;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 离职继承测试类
 * <p>
 * 官方文档：
 * https://developer.work.weixin.qq.com/document/path/92124
 */
@Slf4j
@Guice(modules = ApiTestModule.class)
public class WxCpExternalContactTest {

  @Inject
  private WxCpService wxCpService;
  /**
   * The Config storage.
   */
  @Inject
  protected ApiTestModule.WxXmlCpInMemoryConfigStorage configStorage;

  /**
   * Test get external contact.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetExternalContact() throws WxErrorException {
    String externalUserId = this.configStorage.getExternalUserId();
    WxCpUserExternalUnassignList unassignList = this.wxCpService.getExternalContactService().listUnassignedList(null,
      null, 100);
    log.info(unassignList.toJson());

    // test str
    String result = "{\"errcode\":0,\"errmsg\":\"ok\",\"info\":[{\"handover_userid\":\"zhangsan\"," +
      "\"external_userid\":\"woAJ2GCAAAd4uL12hdfsdasassdDmAAAAA\",\"dimission_time\":1550838571}," +
      "{\"handover_userid\":\"lisi\",\"external_userid\":\"wmAJ2GCAAAzLTI123ghsdfoGZNqqAAAA\"," +
      "\"dimission_time\":1550661468}],\"is_last\":false,\"next_cursor\":\"aSfwejksvhToiMMfFeIGZZ\"}";
    WxCpUserExternalUnassignList json = WxCpUserExternalUnassignList.fromJson(result);
    log.info(json.toJson());

  }

}
