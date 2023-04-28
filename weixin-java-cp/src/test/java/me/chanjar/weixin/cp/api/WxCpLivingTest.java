package me.chanjar.weixin.cp.api;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.XmlUtils;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.living.*;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.constant.WxCpConsts;
import me.chanjar.weixin.cp.demo.WxCpDemoInMemoryConfigStorage;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import me.chanjar.weixin.cp.util.xml.XStreamTransformer;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * 企业微信直播测试类.
 * 官方文档：https://open.work.weixin.qq.com/api/doc/90000/90135/93632
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a> created on  2021-12-23
 */
@Slf4j
public class WxCpLivingTest {

  private static WxCpConfigStorage wxCpConfigStorage;
  private static WxCpService wxCpService;

  /**
   * Test.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void test() throws WxErrorException {

    InputStream inputStream = ClassLoader.getSystemResourceAsStream("test-config.xml");
    WxCpDemoInMemoryConfigStorage config = WxCpDemoInMemoryConfigStorage.fromXml(inputStream);

    wxCpConfigStorage = config;
    wxCpService = new WxCpServiceImpl();
    wxCpService.setWxCpConfigStorage(config);


    /**
     * 直播回调事件
     * 一场完整的直播，会经历 预约直播/开始直播/结束直播 等一系列状态变更。
     * 为了让企业实时获取直播的动态，当直播状态变更后，企业微信会将该变更推送到开发者配置的回调URL。
     * 只有通过接口创建的预约/立即直播才会回调。
     *
     * 请注意，只有用企业微信api创建的直播才能收到回调，且调用创建直播接口的应用，要配置好回调url。
     */
    String livingXml = "<xml>\n" +
      "   <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "   <FromUserName><![CDATA[fromUser]]></FromUserName> \n" +
      "   <CreateTime>1348831860</CreateTime>\n" +
      "   <MsgType><![CDATA[event]]></MsgType>\n" +
      "   <Event><![CDATA[living_status_change]]></Event>\n" +
      "   <LivingId><![CDATA[LivingId]]></LivingId>\n" +
      "   <Status>1</Status>\n" +
      "   <AgentID>1</AgentID>\n" +
      "</xml>";

    final WxCpXmlMessage livingXmlMsg = XStreamTransformer.fromXml(WxCpXmlMessage.class, livingXml);
    livingXmlMsg.setAllFieldsMap(XmlUtils.xml2Map(livingXml));
    log.info("livingXmlMsg:{}", WxCpGsonBuilder.create().toJson(livingXmlMsg));

    /**
     * 直播回调事件常量
     * https://developer.work.weixin.qq.com/document/path/94145
     */
    String livingStatusChange = WxCpConsts.EventType.LIVING_STATUS_CHANGE;


    /**
     * 测试创建直播
     */
    WxCpLivingCreateRequest createRequest = new WxCpLivingCreateRequest();
    createRequest.setAnchorUserid("WangKai");
    createRequest.setTheme("直播1");

    long currentTimeMillis = System.currentTimeMillis() + 3600000L;
    createRequest.setLivingStart(currentTimeMillis);
    createRequest.setLivingDuration(3600L);
    createRequest.setType(4);
    createRequest.setDescription("这是通用直播1");

    val activityDetail = new WxCpLivingCreateRequest.ActivityDetail();
    activityDetail.setDescription("活动描述，非活动类型的直播不用传");

//    String[] strings = new String[]{"MEDIA_ID_2", "MEDIA_ID_1"};
    ArrayList imageList = new ArrayList();
    imageList.add("MEDIA_ID_1");
    imageList.add("MEDIA_ID_2");

    activityDetail.setImageList(imageList);
    createRequest.setActivityDetail(activityDetail);

    String livingCreate = wxCpService.getLivingService().livingCreate(createRequest);
    log.info("返回的直播id为：{}", livingCreate);

    WxCpLivingCreateRequest thisReq = WxCpLivingCreateRequest.fromJson(createRequest.toJson());
    log.info("返回的数据：{}", thisReq.toJson());

    // 创建预约直播
    String createJson = "{\"anchor_userid\":\"ChenHu\",\"theme\":\"theme\",\"living_start\":164037820420," +
      "\"living_duration\":3600,\"description\":\"test description\",\"type\":4,\"remind_time\":60," +
      "\"activity_cover_mediaid\":\"MEDIA_ID\",\"activity_share_mediaid\":\"MEDIA_ID\"," +
      "\"activity_detail\":{\"description\":\"活动描述，非活动类型的直播不用传\",\"image_list\":[\"xxxx1\",\"xxxx1\"]}}";
    WxCpLivingCreateRequest requestData = WxCpLivingCreateRequest.fromJson(createJson);
    String thisLivingId = wxCpService.getLivingService().livingCreate(requestData);
    log.info("livingId为：{}", thisLivingId);


    /**
     * other api
     */
    String livingCode = wxCpService.getLivingService().getLivingCode("o50by5NezHciWnoexJsrI49ILNqI",
      "lvOQpTDwAAD2MYuOq9y_bmLNMJfbbdGw");
    log.info(WxCpGsonBuilder.create().toJson(livingCode));

    // 直播详情
    WxCpLivingInfo livingInfo = wxCpService.getLivingService().getLivingInfo("lvOQpTDwAAcP9wNOSSxTwpbni-TMPNSg");
    log.info(livingInfo.toJson());

    // 直播观看明细
    WxCpWatchStat watchStat = wxCpService.getLivingService().getWatchStat("lvOQpTDwAAcP9wNOSSxTwpbni-TMPNSg", "0");
    log.info(watchStat.toJson());

    final String watchStateJson = "{\"errcode\":0,\"errmsg\":\"ok\",\"ending\":1,\"next_key\":\"NEXT_KEY\"," +
      "\"stat_info\":{\"users\":[{\"userid\":\"userid\",\"watch_time\":30,\"is_comment\":1,\"is_mic\":1}]," +
      "\"external_users\":[{\"external_userid\":\"external_userid1\",\"type\":1,\"name\":\"user name\"," +
      "\"watch_time\":30,\"is_comment\":1,\"is_mic\":1},{\"external_userid\":\"external_userid2\",\"type\":2," +
      "\"name\":\"user_name\",\"watch_time\":30,\"is_comment\":1,\"is_mic\":1}]}}";

    WxCpWatchStat wxCpWatchStat = WxCpWatchStat.fromJson(watchStateJson);
    log.info(wxCpWatchStat.toJson());

    // 直播观众信息
    final String livingShareInfo = "{\"errcode\":0,\"errmsg\":\"ok\",\"livingid\":\"livingid\"," +
      "\"viewer_userid\":\"viewer_userid\",\"viewer_external_userid\":\"viewer_external_userid\"," +
      "\"invitor_userid\":\"invitor_userid\",\"invitor_external_userid\":\"invitor_external_userid\"}";

    WxCpLivingShareInfo wxCpLivingShareInfo = WxCpLivingShareInfo.fromJson(livingShareInfo);
    log.info(wxCpLivingShareInfo.toJson());

    // 获取成员直播ID列表
    WxCpLivingResult.LivingIdResult livingResult = wxCpService.getLivingService().getUserAllLivingId("ChenHu", null,
      null);
    log.info(livingResult.toJson());

    String livinglist = "{\"errcode\":0,\"errmsg\":\"ok\",\"next_cursor\":\"next_cursor\"," +
      "\"livingid_list\":[\"livingid1\",\"livingid2\"]}";
    WxCpLivingResult.LivingIdResult livingIdResult = WxCpLivingResult.LivingIdResult.fromJson(livinglist);
    log.info(livingIdResult.toJson());


    log.info("{}", new Date().getTime());
    // 创建预约直播
    String create = "{\"anchor_userid\":\"ChenHu\",\"theme\":\"theme\",\"living_start\":164037820420," +
      "\"living_duration\":3600,\"description\":\"test description\",\"type\":4,\"remind_time\":60," +
      "\"activity_cover_mediaid\":\"MEDIA_ID\",\"activity_share_mediaid\":\"MEDIA_ID\"," +
      "\"activity_detail\":{\"description\":\"活动描述，非活动类型的直播不用传\",\"image_list\":[\"xxxx1\",\"xxxx1\"]}}";
    WxCpLivingCreateRequest request = WxCpLivingCreateRequest.fromJson(create);
    String livingId = wxCpService.getLivingService().livingCreate(request);
    log.info("livingId为：{}", livingId);


    String modify = "{\"livingid\": \"" + livingId + "\",\"theme\":\"theme\",\"living_start\":164047820420," +
      "\"living_duration\":3600,\"description\":\"描述：description\",\"type\":1,\"remind_time\":60}";
    WxCpLivingModifyRequest modifyReq = WxCpLivingModifyRequest.fromJson(modify);
    WxCpLivingResult result = wxCpService.getLivingService().livingModify(modifyReq);
    log.info("result：{}", result.toJson());


    // 取消预约直播
//    WxCpLivingResult result2 = wxCpService.getLivingService().livingCancel("lvOQpTDwAA0KyLmWZhf_LIENzYIBVD2g");
    WxCpLivingResult result2 = wxCpService.getLivingService().livingCancel(livingId);
    log.info("取消预约直播为：{}", result2.toJson());


    // 删除直播回放
//    WxCpLivingResult response = wxCpService.getLivingService().deleteReplayData("lvOQpTDwAAVdCHyXMgSK63TKPfKDII7w");
//    log.info(response.toJson());


  }

  /**
   * Main.
   *
   * @param args the args
   */
  public static void main(String[] args) {
    log.info("{}", new Date().getTime());
  }

}
