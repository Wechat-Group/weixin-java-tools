package me.chanjar.weixin.channel.api.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxLeadComponentService;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadInfoByComponentRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadsComponentIdRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadsComponentPromoteRecordRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadsInfoByRequestIdRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadsRequestIdRequest;
import me.chanjar.weixin.channel.bean.lead.component.response.GetLeadsComponentIdResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.GetLeadsComponentPromoteRecordResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.GetLeadsRequestIdResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.LeadInfoResponse;
import me.chanjar.weixin.channel.util.ResponseUtils;
import me.chanjar.weixin.common.error.WxErrorException;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.LeadComponent.GET_LEADS_COMPONENT_ID;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.LeadComponent.GET_LEADS_COMPONENT_PROMOTE_RECORD;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.LeadComponent.GET_LEADS_INFO_BY_COMPONENT_ID;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.LeadComponent.GET_LEADS_INFO_BY_REQUEST_ID;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.LeadComponent.GET_LEADS_REQUEST_ID;

/**
 * 视频号助手 留资组件管理服务
 * @author imyzt
 * @date 2024/01/27
 */
@RequiredArgsConstructor
@Slf4j
public class WxLeadComponentServiceImpl implements WxLeadComponentService {

  /** 微信商店服务 */
  private final BaseWxChannelServiceImpl shopService;
  @Override
  public LeadInfoResponse getLeadsInfoByComponentId(GetLeadInfoByComponentRequest req) throws WxErrorException {
    String resJson = shopService.post(GET_LEADS_INFO_BY_COMPONENT_ID, req);
    return ResponseUtils.decode(resJson, LeadInfoResponse.class);
  }

  @Override
  public LeadInfoResponse getLeadsInfoByRequestId(GetLeadsInfoByRequestIdRequest req) throws WxErrorException {
    String resJson = shopService.post(GET_LEADS_INFO_BY_REQUEST_ID, req);
    return ResponseUtils.decode(resJson, LeadInfoResponse.class);
  }

  @Override
  public GetLeadsRequestIdResponse getLeadsRequestId(GetLeadsRequestIdRequest req) throws WxErrorException {
    String resJson = shopService.post(GET_LEADS_REQUEST_ID, req);
    return ResponseUtils.decode(resJson, GetLeadsRequestIdResponse.class);
  }

  @Override
  public GetLeadsComponentPromoteRecordResponse getLeadsComponentPromoteRecord(GetLeadsComponentPromoteRecordRequest req) throws WxErrorException {
    String resJson = shopService.post(GET_LEADS_COMPONENT_PROMOTE_RECORD, req);
    return ResponseUtils.decode(resJson, GetLeadsComponentPromoteRecordResponse.class);
  }

  @Override
  public GetLeadsComponentIdResponse getLeadsComponentId(GetLeadsComponentIdRequest req) throws WxErrorException {
    String resJson = shopService.post(GET_LEADS_COMPONENT_ID, req);
    return ResponseUtils.decode(resJson, GetLeadsComponentIdResponse.class);
  }
}
