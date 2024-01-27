package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadInfoByComponentRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadsComponentIdRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadsComponentPromoteRecordRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadsInfoByRequestIdRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetLeadsRequestIdRequest;
import me.chanjar.weixin.channel.bean.lead.component.response.GetLeadsComponentIdResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.GetLeadsComponentPromoteRecordResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.GetLeadsRequestIdResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.LeadInfoResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号助手 留资组件管理服务
 *
 * @author <a href="https://github.com/imyzt">imyzt</a>
 */
public interface WxLeadComponentService {

  /**
   * <a href="https://developers.weixin.qq.com/doc/channels/API/leads/get_leads_info_by_component_id.html">按时间获取留资信息详情</a>
   *
   * @param req 留资组件信息
   * @return 留资信息详情
   */
  LeadInfoResponse getLeadsInfoByComponentId(GetLeadInfoByComponentRequest req) throws WxErrorException;

  /**
   * <a href="https://developers.weixin.qq.com/doc/channels/API/leads/get_leads_info_by_request_id.html">按直播场次获取留资信息详情</a>
   *
   * @param req 留资组件信息
   * @return 留资信息详情
   */
  LeadInfoResponse getLeadsInfoByRequestId(GetLeadsInfoByRequestIdRequest req) throws WxErrorException;

  /**
   * <a href="https://developers.weixin.qq.com/doc/channels/API/leads/get_leads_request_id.html">获取留资request_id列表详情</a>
   *
   * @param req 留资组件信息
   * @return 留资信息列表
   */
  GetLeadsRequestIdResponse getLeadsRequestId(GetLeadsRequestIdRequest req) throws WxErrorException;

  /**
   * <a href="https://developers.weixin.qq.com/doc/channels/API/leads/get_leads_component_promote_record.html">获取留资组件直播推广记录信息详情</a>
   *
   * @param req 留资组件信息
   * @return 留资组件直播推广记录信息详情
   */
  GetLeadsComponentPromoteRecordResponse getLeadsComponentPromoteRecord(GetLeadsComponentPromoteRecordRequest req) throws WxErrorException;

  /**
   * <a href="https://developers.weixin.qq.com/doc/channels/API/leads/get_leads_component_id.html">获取留资组件Id列表详情</a>
   *
   * @param req 留资组件信息
   * @return 留资组件Id列表
   */
  GetLeadsComponentIdResponse getLeadsComponentId(GetLeadsComponentIdRequest req) throws WxErrorException;
}
