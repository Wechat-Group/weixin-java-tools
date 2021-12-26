package me.chanjar.weixin.cp.api;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.living.*;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.demo.WxCpDemoInMemoryConfigStorage;
import org.eclipse.jetty.util.ajax.JSON;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.Date;

/**
 * 企业微信直播测试类.
 * 官方文档：https://open.work.weixin.qq.com/api/doc/90000/90135/93632
 *
 * @author Wang_Wong
 */
@Slf4j
public class WxCpLivingTest {

  private static WxCpConfigStorage wxCpConfigStorage;
  private static WxCpService wxCpService;

  @Test
  public void test() throws WxErrorException {

    InputStream inputStream = ClassLoader.getSystemResourceAsStream("test-config.xml");
    WxCpDemoInMemoryConfigStorage config = WxCpDemoInMemoryConfigStorage.fromXml(inputStream);

    wxCpConfigStorage = config;
    wxCpService = new WxCpServiceImpl();
    wxCpService.setWxCpConfigStorage(config);

    String livingCode = wxCpService.getLivingService().getLivingCode("o50by5NezHciWnoexJsrI49ILNqI", "lvOQpTDwAAD2MYuOq9y_bmLNMJfbbdGw");
    log.info(JSON.toString(livingCode));

    // 直播详情
    WxCpLivingInfo livingInfo = wxCpService.getLivingService().getLivingInfo("lvOQpTDwAAcP9wNOSSxTwpbni-TMPNSg");
    log.info(livingInfo.toJson());

    // 直播观看明细
    WxCpWatchStat watchStat = wxCpService.getLivingService().getWatchStat("lvOQpTDwAAcP9wNOSSxTwpbni-TMPNSg", 0);
    log.info(watchStat.toJson());

    final String watchStateJson = "{\"errcode\":0,\"errmsg\":\"ok\",\"ending\":1,\"next_key\":\"NEXT_KEY\",\"stat_info\":{\"users\":[{\"userid\":\"userid\",\"watch_time\":30,\"is_comment\":1,\"is_mic\":1}],\"external_users\":[{\"external_userid\":\"external_userid1\",\"type\":1,\"name\":\"user name\",\"watch_time\":30,\"is_comment\":1,\"is_mic\":1},{\"external_userid\":\"external_userid2\",\"type\":2,\"name\":\"user_name\",\"watch_time\":30,\"is_comment\":1,\"is_mic\":1}]}}";

    WxCpWatchStat wxCpWatchStat = WxCpWatchStat.fromJson(watchStateJson);
    log.info(wxCpWatchStat.toJson());

    // 直播观众信息
    final String livingShareInfo = "{\"errcode\":0,\"errmsg\":\"ok\",\"livingid\":\"livingid\",\"viewer_userid\":\"viewer_userid\",\"viewer_external_userid\":\"viewer_external_userid\",\"invitor_userid\":\"invitor_userid\",\"invitor_external_userid\":\"invitor_external_userid\"}";

    WxCpLivingShareInfo wxCpLivingShareInfo = WxCpLivingShareInfo.fromJson(livingShareInfo);
    log.info(wxCpLivingShareInfo.toJson());

    // 获取成员直播ID列表
    WxCpLivingResult.LivingIdResult livingResult = wxCpService.getLivingService().getUserAllLivingId("ChenHu", null, null);
    log.info(livingResult.toJson());

    String livinglist = "{\"errcode\":0,\"errmsg\":\"ok\",\"next_cursor\":\"next_cursor\",\"livingid_list\":[\"livingid1\",\"livingid2\"]}";
    WxCpLivingResult.LivingIdResult livingIdResult = WxCpLivingResult.LivingIdResult.fromJson(livinglist);
    log.info(livingIdResult.toJson());


    log.info("{}", new Date().getTime());
    // 创建预约直播
    String create = "{\"anchor_userid\":\"ChenHu\",\"theme\":\"theme\",\"living_start\":164037820420,\"living_duration\":3600,\"description\":\"test description\",\"type\":4,\"remind_time\":60,\"activity_cover_mediaid\":\"MEDIA_ID\",\"activity_share_mediaid\":\"MEDIA_ID\",\"activity_detail\":{\"description\":\"活动描述，非活动类型的直播不用传\",\"image_list\":[\"xxxx1\",\"xxxx1\"]}}";
    WxCpLivingCreateRequest request = WxCpLivingCreateRequest.fromJson(create);
    String livingId = wxCpService.getLivingService().livingCreate(request);
    log.info("livingId为：{}", livingId);


    String modify = "{\"livingid\": \""+ livingId +"\",\"theme\":\"theme\",\"living_start\":164047820420,\"living_duration\":3600,\"description\":\"描述：description\",\"type\":1,\"remind_time\":60}";
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

  public static void main(String[] args){
    log.info("{}", new Date().getTime());
  }

}
