package me.chanjar.weixin.cp.api;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.external.*;
import me.chanjar.weixin.cp.bean.external.contact.WxCpExternalContactBatchInfo;
import me.chanjar.weixin.cp.bean.external.contact.WxCpExternalContactInfo;
import me.chanjar.weixin.cp.bean.external.msg.Attachment;
import me.chanjar.weixin.cp.bean.external.msg.Image;
import me.chanjar.weixin.cp.bean.external.msg.Video;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import org.testng.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertNotNull;

/**
 * 离职继承测试类
 *
 * 官方文档：
 * https://developer.work.weixin.qq.com/document/path/92124
 */
@Slf4j
@Guice(modules = ApiTestModule.class)
public class WxCpExternalContactTest {

  @Inject
  private WxCpService wxCpService;
  @Inject
  protected ApiTestModule.WxXmlCpInMemoryConfigStorage configStorage;

  @Test
  public void testGetExternalContact() throws WxErrorException {
    String externalUserId = this.configStorage.getExternalUserId();
    WxCpUserExternalUnassignList unassignList = this.wxCpService.getExternalContactService().listUnassignedList(null, null, 100);
    log.info(unassignList.toJson());

    // test str
    String result = "{\"errcode\":0,\"errmsg\":\"ok\",\"info\":[{\"handover_userid\":\"zhangsan\",\"external_userid\":\"woAJ2GCAAAd4uL12hdfsdasassdDmAAAAA\",\"dimission_time\":1550838571},{\"handover_userid\":\"lisi\",\"external_userid\":\"wmAJ2GCAAAzLTI123ghsdfoGZNqqAAAA\",\"dimission_time\":1550661468}],\"is_last\":false,\"next_cursor\":\"aSfwejksvhToiMMfFeIGZZ\"}";
    WxCpUserExternalUnassignList json = WxCpUserExternalUnassignList.fromJson(result);
    log.info(json.toJson());

  }

}
