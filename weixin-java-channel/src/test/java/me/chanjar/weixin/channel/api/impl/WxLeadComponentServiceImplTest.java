package me.chanjar.weixin.channel.api.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadInfoByComponentRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadsComponentIdRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadsComponentPromoteRecordRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadsInfoByRequestIdRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadsRequestIdRequest;
import me.chanjar.weixin.channel.bean.lead.component.response.GetLeadsComponentIdResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.GetLeadsComponentPromoteRecordResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.GetLeadsRequestIdResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.LeadInfoResponse;
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
public class WxLeadComponentServiceImplTest {

  private static final String LEADS_COMPONENT_ID = "123";
  private static final String REQUEST_ID = "123";
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  @Inject
  private WxChannelService channelService;

  @Test
  public void testGetLeadsInfoByComponentId() throws WxErrorException, JsonProcessingException {
    String lastBuffer = null;
    for (; ; ) {
      GetLeadInfoByComponentRequest req = new GetLeadInfoByComponentRequest();
      req.setStartTime(Instant.now().minus(1, ChronoUnit.DAYS).getEpochSecond());
      req.setEndTime(Instant.now().getEpochSecond());
      req.setLeadsComponentId(LEADS_COMPONENT_ID);
      req.setLastBuffer(lastBuffer);
      req.setVersion(1);
      LeadInfoResponse response = channelService.getLeadComponentService().getLeadsInfoByComponentId(req);
      System.out.println(OBJECT_MAPPER.writeValueAsString(response));
      assertNotNull(response);
      assertTrue(response.isSuccess());
      lastBuffer = response.getLastBuffer();
      if (Objects.isNull(lastBuffer)) {
        break;
      }
    }
  }

  @Test
  public void testGetLeadsInfoByRequestId() throws WxErrorException, JsonProcessingException {
    String lastBuffer = null;
    for (; ; ) {
      GetLeadsInfoByRequestIdRequest req = new GetLeadsInfoByRequestIdRequest();
      req.setLastBuffer(lastBuffer);
      req.setRequestId(REQUEST_ID);
      LeadInfoResponse response = channelService.getLeadComponentService().getLeadsInfoByRequestId(req);
      System.out.println(OBJECT_MAPPER.writeValueAsString(response));
      assertNotNull(response);
      assertTrue(response.isSuccess());
      lastBuffer = response.getLastBuffer();
      if (Objects.isNull(lastBuffer)) {
        break;
      }
    }
  }

  @Test
  public void testGetLeadsRequestId() throws WxErrorException, JsonProcessingException {
    String lastBuffer = null;
    for (; ; ) {
      GetLeadsRequestIdRequest req = new GetLeadsRequestIdRequest();
      req.setLastBuffer(lastBuffer);
      req.setLeadsComponentId(LEADS_COMPONENT_ID);
      GetLeadsRequestIdResponse response = channelService.getLeadComponentService().getLeadsRequestId(req);
      System.out.println(OBJECT_MAPPER.writeValueAsString(response));
      assertNotNull(response);
      assertTrue(response.isSuccess());
      lastBuffer = response.getLastBuffer();
      if (Objects.isNull(lastBuffer)) {
        break;
      }
    }
  }

  @Test
  public void testGetLeadsComponentPromoteRecord() throws WxErrorException, JsonProcessingException {
    String lastBuffer = null;
    for (; ; ) {
      GetLeadsComponentPromoteRecordRequest req = new GetLeadsComponentPromoteRecordRequest();
      req.setStartTime(Instant.now().minus(1, ChronoUnit.DAYS).getEpochSecond());
      req.setEndTime(Instant.now().getEpochSecond());
      req.setLeadsComponentId(LEADS_COMPONENT_ID);
      req.setLastBuffer(lastBuffer);
      GetLeadsComponentPromoteRecordResponse response = channelService.getLeadComponentService().getLeadsComponentPromoteRecord(req);
      System.out.println(OBJECT_MAPPER.writeValueAsString(response));
      assertNotNull(response);
      assertTrue(response.isSuccess());
      lastBuffer = response.getLastBuffer();
      if (Objects.isNull(lastBuffer)) {
        break;
      }
    }
  }

  @Test
  public void testGetLeadsComponentId() throws WxErrorException, JsonProcessingException {
    String lastBuffer = null;
    for (; ; ) {
      GetLeadsComponentIdRequest req = new GetLeadsComponentIdRequest();
      req.setLastBuffer(lastBuffer);
      GetLeadsComponentIdResponse response = channelService.getLeadComponentService().getLeadsComponentId(req);
      System.out.println(OBJECT_MAPPER.writeValueAsString(response));
      assertNotNull(response);
      assertTrue(response.isSuccess());
      lastBuffer = response.getLastBuffer();
      if (Objects.isNull(lastBuffer)) {
        break;
      }
    }
  }

}
