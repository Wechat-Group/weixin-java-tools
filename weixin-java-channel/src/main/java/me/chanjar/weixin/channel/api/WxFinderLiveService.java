package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.lead.component.request.GetFinderLiveDataListRequest;
import me.chanjar.weixin.channel.bean.lead.component.request.GetFinderLiveLeadsDataRequest;
import me.chanjar.weixin.channel.bean.lead.component.response.FinderAttrResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.GetFinderLiveDataListResponse;
import me.chanjar.weixin.channel.bean.lead.component.response.GetFinderLiveLeadsDataResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号助手 留资服务的直播数据服务
 *
 * @author <a href="https://github.com/imyzt">imyzt</a>
 */
public interface WxFinderLiveService {

  /**
   * <a href="https://developers.weixin.qq.com/doc/channels/API/live/get_finder_attr_by_appid.html">获取视频号账号信息</a>
   *
   * @return 视频号账号信息
   */
  FinderAttrResponse getFinderAttrByAppid() throws WxErrorException;

  /**
   * <a href="https://developers.weixin.qq.com/doc/channels/API/live/get_finder_live_data_list.html">获取留资直播间数据详情</a>
   *
   * @param req 留资组件信息
   * @return 留资信息详情
   */
  GetFinderLiveDataListResponse getFinderLiveDataList(GetFinderLiveDataListRequest req) throws WxErrorException;

  /**
   * <a href="https://developers.weixin.qq.com/doc/channels/API/live/get_finder_live_leads_data.html">获取账号收集的留资数量</a>
   * 说明：该接口只统计2023.9.13号起的数据，所以start_time应大于等于1694534400
   *
   * @param req 留资组件信息
   * @return 留资信息列表
   */
  GetFinderLiveLeadsDataResponse getFinderLiveLeadsData(GetFinderLiveLeadsDataRequest req) throws WxErrorException;

}
