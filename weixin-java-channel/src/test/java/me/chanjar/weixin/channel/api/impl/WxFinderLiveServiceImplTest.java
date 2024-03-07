package me.chanjar.weixin.channel.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.lead.component.request.GetFinderLiveDataListRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetFinderLiveLeadsDataRequest;
import me.chanjar.weixin.channel.bean.lead.component.response.FinderAttrResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.GetFinderLiveDataListResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.GetFinderLiveLeadsDataResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author <a href="https://github.com/imyzt">imyzt</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxFinderLiveServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testGetFinderAttrByAppid() throws WxErrorException {
    FinderAttrResponse response = channelService.getFinderLiveService().getFinderAttrByAppid();
    System.out.println(response);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetFinderLiveDataList() throws WxErrorException {
    String lastBuffer = null;
    for (; ; ) {
      GetFinderLiveDataListRequest req = new GetFinderLiveDataListRequest();
      req.setLastBuffer(lastBuffer);
      GetFinderLiveDataListResponse response = channelService.getFinderLiveService().getFinderLiveDataList(req);
      System.out.println(response);
      assertNotNull(response);
      assertTrue(response.isSuccess());
      lastBuffer = response.getLastBuffer();
      if (Objects.isNull(lastBuffer)) {
        break;
      }
    }
  }

  @Test
  public void testGetFinderLiveLeadsData() throws WxErrorException {
    GetFinderLiveLeadsDataRequest req = new GetFinderLiveLeadsDataRequest();
    req.setStartTime(Instant.now().minus(1, ChronoUnit.DAYS).getEpochSecond());
    req.setEndTime(Instant.now().getEpochSecond());
    GetFinderLiveLeadsDataResponse response = channelService.getFinderLiveService().getFinderLiveLeadsData(req);
    assertNotNull(response);
    assertTrue(response.isSuccess());
    for (GetFinderLiveLeadsDataResponse.LeadCountItem item : response.getItems()) {
      System.out.println(item.toString());
    }
  }
}
