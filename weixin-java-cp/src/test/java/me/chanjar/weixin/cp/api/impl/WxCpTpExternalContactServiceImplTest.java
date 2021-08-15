package me.chanjar.weixin.cp.api.impl;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.external.*;
import me.chanjar.weixin.cp.bean.external.contact.WxCpExternalContactInfo;
import me.chanjar.weixin.cp.bean.external.msg.Attachment;
import me.chanjar.weixin.cp.bean.external.msg.Image;
import me.chanjar.weixin.cp.bean.external.msg.Video;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertNotNull;

@Guice(modules = ApiTestModule.class)
public class WxCpTpExternalContactServiceImplTest {
  @Inject
  private WxCpService wxCpService;
  @Inject
  protected ApiTestModule.WxXmlCpInMemoryConfigStorage configStorage;
  private final String userId = "someone" + System.currentTimeMillis();

  @Test
  public void testGetExternalContact() throws WxErrorException {
    String externalUserId = this.configStorage.getExternalUserId();
    WxCpExternalContactInfo result = this.wxCpService.getExternalContactService().getExternalContact(externalUserId);
    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  public void testAddContactWay() throws WxErrorException {

    final String concatUserId = "HuangXiaoMing";

    WxCpContactWayInfo.ContactWay wayInfo = new WxCpContactWayInfo.ContactWay();
    wayInfo.setType(WxCpContactWayInfo.TYPE.SINGLE);
    wayInfo.setScene(WxCpContactWayInfo.SCENE.QRCODE);
    wayInfo.setUsers(Lists.newArrayList(concatUserId));
    wayInfo.setRemark("CreateDate:" + DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date()));

    WxCpContactWayInfo info = new WxCpContactWayInfo();
    info.setContactWay(wayInfo);
    this.wxCpService.getExternalContactService().addContactWay(info);
  }

  @Test
  public void testGetContactWay() throws WxErrorException {
    final String configId = "39fea3d93e30faaa8c7a9edd4cfe4d08";
    WxCpContactWayInfo contactWayInfo = this.wxCpService.getExternalContactService().getContactWay(configId);
    System.out.println(contactWayInfo.toJson());
    assertNotNull(contactWayInfo);
  }

  @Test
  public void testUpdateContactWay() throws WxErrorException {
    final String configId = "2d7a68c657663afbd1d90db19a4b5ee9";
    final String concatUserId = "符合要求的userId";
    WxCpContactWayInfo.ContactWay wayInfo = new WxCpContactWayInfo.ContactWay();
    wayInfo.setConfigId(configId);
    wayInfo.setUsers(Lists.newArrayList(concatUserId));
    wayInfo.setRemark("CreateDate:" + DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date()));
    WxCpContactWayInfo info = new WxCpContactWayInfo();
    info.setContactWay(wayInfo);
    WxCpBaseResp resp = this.wxCpService.getExternalContactService().updateContactWay(info);
    System.out.println(resp);
    assertNotNull(resp);
  }

  @Test
  public void testDelContactWay() throws WxErrorException {
    final String configId = "2d7a68c657663afbd1d90db19a4b5ee9";
    WxCpBaseResp resp = this.wxCpService.getExternalContactService().deleteContactWay(configId);
    System.out.println(resp);
    assertNotNull(resp);
  }

  @Test
  public void testCloseTempChat() throws WxErrorException {
    final String externalUserId = "externalUserId";
    WxCpBaseResp resp = this.wxCpService.getExternalContactService().closeTempChat(userId, externalUserId);
    System.out.println(resp);
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
    List<String> ret = this.wxCpService.getExternalContactService().listFollowers();
    System.out.println(ret);
    assertNotNull(ret);
  }

  @Test
  public void testGetContactDetail() throws WxErrorException {
    String externalUserId = this.configStorage.getExternalUserId();
    WxCpExternalContactInfo result = this.wxCpService.getExternalContactService().getContactDetail(externalUserId);
    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  public void testGetCorpTagList() throws WxErrorException {
    String[] tag = {};
    WxCpUserExternalTagGroupList result = this.wxCpService.getExternalContactService().getCorpTagList(null);
    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  public void testAddCorpTag() throws WxErrorException {

    List<WxCpUserExternalTagGroupInfo.Tag> list = new ArrayList<>();
    WxCpUserExternalTagGroupInfo.Tag tag = new WxCpUserExternalTagGroupInfo.Tag();
    tag.setName("测试标签20");
    tag.setOrder(1);
    list.add(tag);

    WxCpUserExternalTagGroupInfo tagGroupInfo = new WxCpUserExternalTagGroupInfo();
    WxCpUserExternalTagGroupInfo.TagGroup tagGroup = new WxCpUserExternalTagGroupInfo.TagGroup();
    tagGroup.setGroupName("其他");
    tagGroup.setOrder(1);
    tagGroup.setTag(list);
    tagGroupInfo.setTagGroup(tagGroup);

    WxCpUserExternalTagGroupInfo result = this.wxCpService.getExternalContactService().addCorpTag(tagGroupInfo);

    System.out.println(result.toJson());
    assertNotNull(result);
  }

  @Test
  public void testEditCorpTag() throws WxErrorException {

    WxCpBaseResp result = this.wxCpService.getExternalContactService().editCorpTag("et2omCCwAA6PtGsfeEOQMENl3Ub1FA6A", "未知6", 2);

    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  public void testDelCorpTag() throws WxErrorException {

    String[] tagId = {};
    String[] groupId = {"et2omCCwAAM3WzL00QpK9xARab3HGkAg"};

    WxCpBaseResp result = this.wxCpService.getExternalContactService().delCorpTag(tagId, groupId);

    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  public void testMarkTag() throws WxErrorException {

    String userid = "HuangXiaoMing";
    String externalUserid = "wo2omCCwAAzR0Rt1omz-90o_XJkPGXIQ";
    String[] addTag = {"et2omCCwAAzdcSK-RV80YS9sbpCXlNlQ"};
    String[] removeTag = {};

    WxCpBaseResp result = this.wxCpService.getExternalContactService().markTag(userid, externalUserid, addTag, removeTag);

    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  public void testDeleteContactWay() {
  }

  @Test
  public void testListFollowers() {
  }

  @Test
  public void testListUnassignedList() {
  }

  @Test
  public void testTransferExternalContact() {
  }

  @Test
  public void testTransferCustomer() throws WxErrorException {
    WxCpUserTransferCustomerReq req = new WxCpUserTransferCustomerReq();
    req.setExternalUserid(Collections.emptyList());
    req.setHandOverUserid("123");
    req.setTakeOverUserid("234");
    WxCpBaseResp result = this.wxCpService.getExternalContactService().transferCustomer(req);

    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  public void testTrnsferResult() throws WxErrorException {
    WxCpUserTransferResultResp result = this.wxCpService.getExternalContactService().transferResult("123", "234", "");
    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  public void testresignedTransferCustomer() throws WxErrorException {
    WxCpUserTransferCustomerReq req = new WxCpUserTransferCustomerReq();
    req.setExternalUserid(Collections.emptyList());
    req.setHandOverUserid("123");
    req.setTakeOverUserid("234");
    WxCpBaseResp result = this.wxCpService.getExternalContactService().resignedTransferCustomer(req);

    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  public void testresignedTrnsferResult() throws WxErrorException {
    WxCpUserTransferResultResp result = this.wxCpService.getExternalContactService().resignedTransferResult("123", "234", "");
    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  public void testListGroupChat() throws WxErrorException {
    WxCpUserExternalGroupChatList result = this.wxCpService.getExternalContactService().listGroupChat(0, 100 ,0,new String[1],new String[1]);
    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  public void testListGroupChatV3() throws WxErrorException {
    WxCpUserExternalGroupChatList result = this.wxCpService.getExternalContactService().listGroupChat(100, "" ,0,new String[1]);
    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  public void testGetGroupChat() {
  }

  @Test
  public void testTransferGroupChat() throws WxErrorException {
    String[] str = {"wri1_QEAAATfnZl_VJ4hlQda0e4Mgf1A"};
    WxCpUserExternalGroupChatTransferResp result = this.wxCpService.getExternalContactService().transferGroupChat(str, "123");
    System.out.println(result);
    assertNotNull(result);
  }

  @Test
  public void testGetUserBehaviorStatistic() {
  }

  @Test
  public void testGetGroupChatStatistic() {
  }

  @Test
  public void testAddMsgTemplate() {
  }

  @Test
  public void testSendWelcomeMsg() throws WxErrorException {
    Image image = new Image();
    image.setMediaId("123123");
    Attachment attachment = new Attachment();
    attachment.setImage(image);

    Video video = new Video();
    video.setMediaId("video_media_id");
    Attachment attachment2 = new Attachment();
    attachment2.setVideo(video);

    List<Attachment> attachments = new ArrayList<>();
    attachments.add(attachment);
    attachments.add(attachment2);
    this.wxCpService.getExternalContactService().sendWelcomeMsg(WxCpWelcomeMsg.builder()
      .welcomeCode("abc")
      .attachments(attachments)
      .build());
  }

  @Test
  public void testUpdateRemark() throws WxErrorException {
    this.wxCpService.getExternalContactService().updateRemark(WxCpUpdateRemarkRequest.builder()
      .description("abc")
      .userId("aaa")
      .externalUserId("aaa")
      .remark("aa")
      .remarkCompany("aaa")
      .remarkMobiles(new String[]{"111","222"})
      .remarkPicMediaId("aaa")
      .build());
  }
}
